
package hu.webtown.liferay.portlet.reference.list.web.internal.portlet.action;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.exception.AssetTagException;
import com.liferay.asset.kernel.exception.DuplicateQueryRuleException;
import com.liferay.asset.kernel.model.AssetQueryRule;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;

import hu.webtown.liferay.portlet.reference.list.web.constants.ReferenceDxpListPortletKeys;

/**
 * @author imiszucs
 */
@Component(
    immediate = true,
    property = {
        "javax.portlet.name=" + ReferenceDxpListPortletKeys.REFERENCE_DXP_LIST_PORTLET
    },
    service = ConfigurationAction.class
)
public class ReferenceDxpListConfigurationAction
    extends DefaultConfigurationAction {
    
    public static final String QUERY_AND_OPERATOR = "queryAndOperator";
    public static final String QUERY_CATEGORY_IDS = "queryCategoryIds";
    public static final String QUERY_CONTAINS = "queryContains";
    public static final String QUERY_LOGIC_INDEXES = "queryLogicIndexes";
    public static final String QUERY_NAME = "queryName";
    public static final String QUERY_TAG_NAMES = "queryTagNames";
    public static final String QUERY_VALUES = "queryValues";
    
    protected AssetTagLocalService assetTagLocalService;

    @Override
    public String getJspPath(HttpServletRequest request) {

        return "/configuration.jsp";
    }
    
    @Override
    public void processAction(
        PortletConfig portletConfig, ActionRequest actionRequest,
        ActionResponse actionResponse)
        throws Exception {

        String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

        PortletPreferences portletPreferences = actionRequest.getPreferences();

        if (Constants.UPDATE.equals(cmd)) {

            try {

                updateQueryLogic(actionRequest, portletPreferences);

                super.processAction(
                    portletConfig, actionRequest, actionResponse);
            } catch (AssetTagException | DuplicateQueryRuleException e) {

                SessionErrors.add(actionRequest, e.getClass(), e);
            }
        }
    }
    
    @Override
    @Reference(
        target = "(osgi.web.symbolicname=reference.dxp.list.web)",
        unbind = "-"
    )
    public void setServletContext(ServletContext servletContext) {

        super.setServletContext(servletContext);
    }
    
    protected AssetQueryRule getQueryRule(
        ActionRequest actionRequest, int index) {
        
        boolean contains =
            ParamUtil.getBoolean(actionRequest, QUERY_CONTAINS + index);
        boolean andOperator =
            ParamUtil.getBoolean(actionRequest, QUERY_AND_OPERATOR + index);

        String name = ParamUtil.getString(actionRequest, QUERY_NAME + index);

        String[] values = null;

        if ("assetTags".equals(name)) {
            
            values = StringUtil.split(
                ParamUtil.getString(actionRequest, QUERY_TAG_NAMES + index));
        } else {
            
            values = StringUtil.split(
                ParamUtil.getString(actionRequest, QUERY_CATEGORY_IDS + index));
        }

        return new AssetQueryRule(contains, andOperator, name, values);
    }
    
    @Reference(unbind = "-")
    protected void setAssetTagLocalService(
        AssetTagLocalService assetTagLocalService) {

        this.assetTagLocalService = assetTagLocalService;
    }
    
    protected void updateQueryLogic(
        ActionRequest actionRequest, PortletPreferences preferences)
        throws PortalException {

        ThemeDisplay themeDisplay =
            (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        long userId = themeDisplay.getUserId();
        long groupId = themeDisplay.getSiteGroupId();

        int[] queryRulesIndexes = StringUtil.split(
            ParamUtil.getString(actionRequest, "queryLogicIndexes"), 0);
        
        int i = 0;

        List<AssetQueryRule> queryRules = new ArrayList<>();

        for (int queryRulesIndex : queryRulesIndexes) {
            
            AssetQueryRule queryRule =
                getQueryRule(actionRequest, queryRulesIndex);

            validateQueryRule(userId, groupId, queryRules, queryRule);

            queryRules.add(queryRule);

            setPreference(
                actionRequest, QUERY_CONTAINS + i,
                String.valueOf(queryRule.isContains()));
            setPreference(
                actionRequest, QUERY_AND_OPERATOR + i,
                String.valueOf(queryRule.isAndOperator()));
            setPreference(actionRequest, QUERY_NAME + i, queryRule.getName());
            setPreference(
                actionRequest, QUERY_VALUES + i, queryRule.getValues());

            i++;
        }

        // Clear previous preferences that are now blank

        String[] values =
            preferences.getValues(QUERY_VALUES + i, new String[0]);

        while (values.length > 0) {
            
            setPreference(actionRequest, QUERY_CONTAINS + i, StringPool.BLANK);
            setPreference(
                actionRequest, QUERY_AND_OPERATOR + i, StringPool.BLANK);
            setPreference(actionRequest, QUERY_NAME + i, StringPool.BLANK);
            setPreference(actionRequest, QUERY_VALUES + i, new String[0]);

            i++;

            values = preferences.getValues(QUERY_VALUES + i, new String[0]);
        }
    }
    
    protected void validateQueryRule(
        long userId, long groupId, List<AssetQueryRule> queryRules,
        AssetQueryRule queryRule)
        throws PortalException {

        String name = queryRule.getName();

        if ("assetTags".equals(name)) {
            
            assetTagLocalService.checkTags(
                userId, groupId, queryRule.getValues());
        }

        if (queryRules.contains(queryRule)) {
            
            throw new DuplicateQueryRuleException(
                queryRule.isContains(), queryRule.isAndOperator(),
                queryRule.getName());
        }
    }
}
