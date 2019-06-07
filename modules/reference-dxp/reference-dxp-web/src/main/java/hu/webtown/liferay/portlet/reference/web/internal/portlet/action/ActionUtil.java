
package hu.webtown.liferay.portlet.reference.web.internal.portlet.action;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import hu.webtown.liferay.portlet.reference.model.Reference;
import hu.webtown.liferay.portlet.reference.service.ReferenceLocalServiceUtil;
import hu.webtown.liferay.portlet.reference.service.ReferenceServiceUtil;
import hu.webtown.liferay.portlet.reference.web.internal.portlet.ReferenceDxpPortlet;

public class ActionUtil {

    /**
     * Private constructor. Prevents instantiation from other classes.
     */
    private ActionUtil() {

    }

    public static void deleteReference(
        ActionRequest actionRequest, String deleteReferenceId)
        throws Exception {

        long groupId = ParamUtil.getLong(actionRequest, "groupId");
        String referenceId = deleteReferenceId;
        double version = 0;

        ServiceContext serviceContext = ServiceContextFactory.getInstance(
            Reference.class.getName(), actionRequest);

        int pos = deleteReferenceId.lastIndexOf(
            ReferenceDxpPortlet.VERSION_SEPARATOR);

        if (pos == -1) {
            ReferenceServiceUtil.deleteReference(
                groupId, referenceId, serviceContext);
        } else {
            referenceId = referenceId.substring(0, pos);
            version = GetterUtil.getDouble(
                deleteReferenceId.substring(
                    pos + ReferenceDxpPortlet.VERSION_SEPARATOR.length()));

            ReferenceServiceUtil.deleteReference(
                groupId, referenceId, version, serviceContext);
        }
    }

    public static Reference getReference(HttpServletRequest request)
        throws PortalException {

        String actionName =
            ParamUtil.getString(request, ActionRequest.ACTION_NAME);

        ThemeDisplay themeDisplay =
            (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        long resourcePrimKey = ParamUtil.getLong(request, "resourcePrimKey");
        long groupId = ParamUtil.getLong(
            request, "groupId", themeDisplay.getScopeGroupId());
        String referenceId = ParamUtil.getString(request, "referenceId");
        int status = ParamUtil.getInteger(
            request, "status", WorkflowConstants.STATUS_ANY);

        Reference reference = null;

        if ("addReference".equals(actionName) && (resourcePrimKey != 0)) {

            reference =
                ReferenceLocalServiceUtil.getLatestReference(resourcePrimKey);
        } else if (!"addReference".equals(actionName) &&
            Validator.isNotNull(referenceId)) {

            reference = ReferenceLocalServiceUtil.getLatestReference(
                groupId, referenceId, status);
        }

        return reference;
    }

    public static Reference getReference(PortletRequest portletRequest)
        throws PortalException {

        HttpServletRequest request =
            PortalUtil.getHttpServletRequest(portletRequest);

        Reference reference = getReference(request);

        return reference;
    }

    public static List<Reference> getReferences(ResourceRequest request)
        throws PortalException {

        long groupId = ParamUtil.getLong(request, "groupId");

        String[] referenceIds =
            ParamUtil.getStringValues(request, "rowIdsReference");

        List<Reference> references = new ArrayList<>();

        for (String curReferenceId : referenceIds) {
            Reference curReference =
                ReferenceServiceUtil.getReference(groupId, curReferenceId);

            references.add(curReference);
        }

        return references;
    }

    public static boolean hasReference(ActionRequest actionRequest)
        throws Exception {

        ThemeDisplay themeDisplay =
            (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        String referenceId = ParamUtil.getString(actionRequest, "referenceId");

        if (Validator.isNull(referenceId)) {

            String[] referenceIds =
                StringUtil.split(ParamUtil.getString(actionRequest, "rowIds"));

            if (referenceIds.length <= 0) {

                return false;
            }

            referenceId = referenceIds[0];
        }

        int pos =
            referenceId.lastIndexOf(ReferenceDxpPortlet.VERSION_SEPARATOR);

        if (pos != -1) {

            referenceId = referenceId.substring(0, pos);
        }

        Reference reference = ReferenceLocalServiceUtil.fetchReference(
            themeDisplay.getScopeGroupId(), referenceId);

        return reference != null;
    }

}
