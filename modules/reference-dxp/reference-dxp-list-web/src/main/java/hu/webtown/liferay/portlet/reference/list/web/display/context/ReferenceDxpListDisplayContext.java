
package hu.webtown.liferay.portlet.reference.list.web.display.context;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.asset.publisher.util.AssetPublisherHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import hu.webtown.liferay.portlet.reference.model.Reference;

public class ReferenceDxpListDisplayContext {

    private long[] allAssetCategoryIds;
    private String[] allAssetTagNames;
    private AssetEntryQuery assetEntryQuery;
    private String displayStyle;
    private Long displayStyleGroupId;
    private PortletPreferences portletPreferences;
    private PortletRequest portletRequest;
    private PortletResponse portletResponse;
    private HttpServletRequest request;

    private static final AssetPublisherHelper assetPublisherHelper;

    static {
        Bundle bundle =
            FrameworkUtil.getBundle(ReferenceDxpListDisplayContext.class);

        BundleContext bundleContext = bundle.getBundleContext();

        ServiceReference<AssetPublisherHelper> serviceReference =
            bundleContext.getServiceReference(AssetPublisherHelper.class);

        assetPublisherHelper = bundleContext.getService(serviceReference);
    }

    public ReferenceDxpListDisplayContext(
        PortletRequest portletRequest, PortletResponse portletResponse,
        PortletPreferences portletPreferences) {

        this.request = PortalUtil.getHttpServletRequest(portletRequest);
        this.portletRequest = portletRequest;
        this.portletResponse = portletResponse;
        this.portletPreferences = portletPreferences;
    }

    public long[] getAllAssetCategoryIds() {

        if (allAssetCategoryIds != null) {
            return allAssetCategoryIds;
        }

        allAssetCategoryIds = new long[0];

        long assetCategoryId = ParamUtil.getLong(request, "categoryId");

        // allAssetCategoryIds =
        // AssetPublisherUtil.getAssetCategoryIds(portletPreferences);

        allAssetCategoryIds =
            assetPublisherHelper.getAssetCategoryIds(portletPreferences);

        if ((assetCategoryId > 0) &&
            !ArrayUtil.contains(allAssetCategoryIds, assetCategoryId)) {

            allAssetCategoryIds =
                ArrayUtil.append(allAssetCategoryIds, assetCategoryId);
        }

        return allAssetCategoryIds;
    }

    public String[] getAllAssetTagNames() {

        if (allAssetTagNames != null) {
            return allAssetTagNames;
        }

        allAssetTagNames = new String[0];

        String assetTagName = ParamUtil.getString(request, "tag");

        allAssetTagNames =
            assetPublisherHelper.getAssetTagNames(portletPreferences);

        if (Validator.isNotNull(assetTagName) &&
            !ArrayUtil.contains(allAssetTagNames, assetTagName)) {

            allAssetTagNames = ArrayUtil.append(allAssetTagNames, assetTagName);
        }

        allAssetTagNames =
            ArrayUtil.distinct(allAssetTagNames, new StringComparator());

        return allAssetTagNames;
    }

    public AssetEntryQuery getAssetEntryQuery()
        throws PortalException {

        if (assetEntryQuery != null) {
            return assetEntryQuery;
        }

        ThemeDisplay themeDisplay =
            (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        long[] groupIds = {
            themeDisplay.getScopeGroupId()
        };

        assetEntryQuery = assetPublisherHelper.getAssetEntryQuery(
            portletPreferences, themeDisplay.getScopeGroupId(),
            themeDisplay.getLayout(), getAllAssetCategoryIds(),
            getAllAssetTagNames());

        assetEntryQuery.setGroupIds(groupIds);

        AssetRendererFactory<Reference> referenceAssetRendererFactory =
            AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClass(
                Reference.class);

        assetEntryQuery.setClassNameIds(new long[] {
            referenceAssetRendererFactory.getClassNameId()
        });

        assetEntryQuery.setOrderByCol1("title");
        assetEntryQuery.setOrderByCol2("modifiedDate");
        assetEntryQuery.setOrderByType1("ASC");
        assetEntryQuery.setOrderByType2("DESC");

        return assetEntryQuery;
    }

    public String getDisplayStyle() {

        if (displayStyle == null) {

            displayStyle = GetterUtil.getString(
                portletPreferences.getValue("displayStyle", StringPool.BLANK));
        }

        return displayStyle;
    }

    public long getDisplayStyleGroupId() {

        if (displayStyleGroupId == null) {

            ThemeDisplay themeDisplay =
                (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

            displayStyleGroupId = GetterUtil.getLong(
                portletPreferences.getValue("displayStyleGroupId", null),
                themeDisplay.getScopeGroupId());
        }

        return displayStyleGroupId;
    }

    public String filterAssetTagNames(long groupId, String assetTagNames) {

        List<String> filteredAssetTagNames = new ArrayList<>();

        String[] assetTagNamesArray = StringUtil.split(assetTagNames);

        long[] assetTagIds =
            AssetTagLocalServiceUtil.getTagIds(groupId, assetTagNamesArray);

        for (long assetTagId : assetTagIds) {
            AssetTag assetTag =
                AssetTagLocalServiceUtil.fetchAssetTag(assetTagId);

            if (assetTag != null) {
                filteredAssetTagNames.add(assetTag.getName());
            }
        }

        return StringUtil.merge(filteredAssetTagNames);
    }
}
