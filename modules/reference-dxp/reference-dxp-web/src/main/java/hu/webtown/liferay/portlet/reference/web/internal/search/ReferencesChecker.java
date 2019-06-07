
package hu.webtown.liferay.portlet.reference.web.internal.search;

import javax.servlet.http.HttpServletRequest;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.trash.TrashHelper;

import hu.webtown.liferay.portlet.reference.model.Reference;
import hu.webtown.liferay.portlet.reference.service.ReferenceLocalServiceUtil;
import hu.webtown.liferay.portlet.reference.web.internal.display.context.ReferenceDisplayContext;
import hu.webtown.liferay.portlet.reference.web.internal.security.permission.resource.ReferencePermission;

public class ReferencesChecker extends EmptyOnClickRowChecker {

    private static final Log LOG =
        LogFactoryUtil.getLog(ReferencesChecker.class.getName());

    private final ReferenceDisplayContext referenceDisplayContext;
    private final LiferayPortletResponse liferayPortletResponse;
    private final PermissionChecker permissionChecker;

    public ReferencesChecker(
        LiferayPortletRequest liferayPortletRequest,
        LiferayPortletResponse liferayPortletResponse,
        TrashHelper trashHelper) {

        super(liferayPortletResponse);

        referenceDisplayContext = new ReferenceDisplayContext(
            PortalUtil.getHttpServletRequest(liferayPortletRequest),
            liferayPortletRequest, liferayPortletResponse,
            liferayPortletRequest.getPreferences(), trashHelper);

        this.liferayPortletResponse = liferayPortletResponse;

        ThemeDisplay themeDisplay =
            (ThemeDisplay) liferayPortletRequest.getAttribute(
                WebKeys.THEME_DISPLAY);

        permissionChecker = themeDisplay.getPermissionChecker();
    }

    @Override
    public String getAllRowsCheckBox() {

        return null;
    }

    @Override
    public String getAllRowsCheckBox(HttpServletRequest request) {

        return null;
    }

    @Override
    public String getRowCheckBox(
        HttpServletRequest request, boolean checked, boolean disabled,
        String primaryKey) {

        if (!referenceDisplayContext.isShowEditActions()) {

            return StringPool.BLANK;
        }

        ThemeDisplay themeDisplay =
            (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        String referenceId = GetterUtil.getString(primaryKey);

        Reference reference = ReferenceLocalServiceUtil.fetchReference(
            themeDisplay.getScopeGroupId(), referenceId);

        boolean showInput = false;

        if (reference != null) {

            try {

                showInput = ReferencePermission.contains(
                    permissionChecker, reference, ActionKeys.DELETE);
            } catch (PortalException e) {

                if (LOG.isDebugEnabled()) {

                    LOG.debug(e.getMessage(), e);
                }
            }
        }

        if (!showInput) {

            return StringPool.BLANK;
        }

        StringBundler sb = new StringBundler(5);

        sb.append("['");
        sb.append(liferayPortletResponse.getNamespace());
        sb.append(RowChecker.ROW_IDS);
        sb.append(Reference.class.getSimpleName());
        sb.append("']");

        String checkBoxRowIds = sb.toString();

        return getRowCheckBox(
            request, checked, disabled,
            StringBundler.concat(
                liferayPortletResponse.getNamespace(), RowChecker.ROW_IDS,
                Reference.class.getSimpleName(), StringPool.BLANK),
            primaryKey, checkBoxRowIds, "'#" + getAllRowIds() + "'",
            StringPool.BLANK);
    }
}
