
package hu.webtown.liferay.portlet.reference.web.internal.portlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import com.liferay.document.library.kernel.exception.DuplicateFileException;
import com.liferay.document.library.kernel.exception.FileNameException;
import com.liferay.document.library.kernel.exception.FileSizeException;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.model.TrashedModel;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.trash.TrashHelper;
import com.liferay.trash.kernel.service.TrashEntryService;
import com.liferay.trash.util.TrashWebKeys;

import hu.webtown.liferay.portlet.reference.constants.ReferencePortletKeys;
import hu.webtown.liferay.portlet.reference.exception.DuplicateUrlTitleException;
import hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException;
import hu.webtown.liferay.portlet.reference.exception.ReferenceDescriptionException;
import hu.webtown.liferay.portlet.reference.exception.ReferenceNameException;
import hu.webtown.liferay.portlet.reference.exception.ReferenceRealizationDateException;
import hu.webtown.liferay.portlet.reference.exception.ReferenceShortDescriptionException;
import hu.webtown.liferay.portlet.reference.exception.ReferenceUrlTitleException;
import hu.webtown.liferay.portlet.reference.model.Reference;
import hu.webtown.liferay.portlet.reference.service.ReferenceService;
import hu.webtown.liferay.portlet.reference.web.asset.ReferenceAssetRenderer;
import hu.webtown.liferay.portlet.reference.web.configuration.ReferenceWebConfiguration;
import hu.webtown.liferay.portlet.reference.web.internal.portlet.action.ActionUtil;

@Component(
    configurationPid = "hu.webtown.liferay.portlet.reference.web.configuration.ReferenceWebConfiguration",
    configurationPolicy = ConfigurationPolicy.OPTIONAL,
    immediate = true,
    property = {
        "com.liferay.portlet.add-default-resource=true",
        "com.liferay.portlet.css-class-wrapper=portlet-reference-dxp",
        "com.liferay.portlet.display-category=category.hidden",
        "com.liferay.portlet.header-portlet-css=/css/main.css",
        "com.liferay.portlet.instanceable=false",
        "com.liferay.portlet.preferences-owned-by-group=true",
        "javax.portlet.display-name=Reference DXP Portlet",
        "javax.portlet.init-param.mvc-action-command-package-prefix=hu.webtown.liferay.portlet.reference.web.portlet.action",
        "javax.portlet.init-param.template-path=/",
        "javax.portlet.init-param.view-template=/view.jsp",
        "javax.portlet.name=" + ReferencePortletKeys.REFERENCE_DXP_PORTLET,
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=power-user,user"
    },
    service = {
        ReferenceDxpPortlet.class, Portlet.class
    }
)
public class ReferenceDxpPortlet extends MVCPortlet {

    public static final String VERSION_SEPARATOR = "_version_";

    @org.osgi.service.component.annotations.Reference
    private Http http;

    @org.osgi.service.component.annotations.Reference
    private Portal portal;

    @org.osgi.service.component.annotations.Reference
    private ReferenceService referenceService;

    private volatile ReferenceWebConfiguration referenceWebConfiguration;

    @org.osgi.service.component.annotations.Reference
    private TrashEntryService trashEntryService;

    @org.osgi.service.component.annotations.Reference
    private TrashHelper trashHelper;

    public void addReference(
        ActionRequest actionRequest, ActionResponse actionResponse)
        throws Exception {

        updateReference(actionRequest, actionResponse);
    }

    public void deleteReference(
        ActionRequest actionRequest, ActionResponse actionResponse)
        throws Exception {

        deleteReferences(actionRequest, actionResponse, false);
    }

    public void deleteReferences(
        ActionRequest actionRequest, ActionResponse actionResponse)
        throws Exception {

        deleteReferences(actionRequest, actionResponse, false);
    }

    public void moveReferencesToTrash(
        ActionRequest actionRequest, ActionResponse actionResponse)
        throws Exception {

        deleteReferences(actionRequest, actionResponse, true);
    }

    public void moveToTrash(
        ActionRequest actionRequest, ActionResponse actionResponse)
        throws Exception {

        deleteReferences(actionRequest, actionResponse, true);
    }

    @Override
    public void render(
        RenderRequest renderRequest, RenderResponse renderResponse)
        throws IOException, PortletException {

        renderRequest.setAttribute(TrashWebKeys.TRASH_HELPER, trashHelper);

        renderRequest.setAttribute(
            ReferenceWebConfiguration.class.getName(),
            referenceWebConfiguration);

        super.render(renderRequest, renderResponse);
    }

    public void restoreTrashEntries(
        ActionRequest actionRequest, ActionResponse actionResponse)
        throws Exception {

        long[] restoreTrashEntryIds = StringUtil.split(
            ParamUtil.getString(actionRequest, "restoreTrashEntryIds"), 0L);

        for (long restoreTrashEntryId : restoreTrashEntryIds) {

            trashEntryService.restoreEntry(restoreTrashEntryId);
        }
    }

    public void updateReference(
        ActionRequest actionRequest, ActionResponse actionResponse)
        throws Exception {

        String actionName =
            ParamUtil.getString(actionRequest, ActionRequest.ACTION_NAME);

        long groupId = ParamUtil.getLong(actionRequest, "groupId");

        String referenceId = ParamUtil.getString(actionRequest, "referenceId");

        String name = ParamUtil.getString(actionRequest, "name");

        int realizationDateYear =
            ParamUtil.getInteger(actionRequest, "realizationDateYear");

        int realizationDateMonth =
            ParamUtil.getInteger(actionRequest, "realizationDateMonth");

        int realizationDateDay =
            ParamUtil.getInteger(actionRequest, "realizationDateDay");

        boolean emphasized = ParamUtil.getBoolean(actionRequest, "emphasized");

        String overlayText = ParamUtil.getString(actionRequest, "overlayText");

        String overlayUrl = ParamUtil.getString(actionRequest, "overlayUrl");

        String urlTitle = ParamUtil.getString(actionRequest, "urlTitle");

        String shortDescription =
            ParamUtil.getString(actionRequest, "shortDescription");

        String description = ParamUtil.getString(actionRequest, "description");

        String smallImage = ParamUtil.getString(actionRequest, "smallImage");

        String largeImage = ParamUtil.getString(actionRequest, "largeImage");

        String otherImageIndexesParam =
            ParamUtil.getString(actionRequest, "otherImageIndexes");

        int[] otherImageIndexes = new int[0];

        if (Validator.isNotNull(otherImageIndexesParam)) {

            otherImageIndexes = StringUtil.split(otherImageIndexesParam, 0);
        }

        String[] otherImages = new String[0];

        for (int i = 0; i < otherImageIndexes.length; i++) {

            String paramName = "otherImage" + otherImageIndexes[i];

            String otherImage = ParamUtil.getString(actionRequest, paramName);

            if (Validator.isNotNull(otherImage)) {

                otherImages = ArrayUtil.append(otherImages, otherImage);
            }
        }

        String categories = ParamUtil.getString(actionRequest, "categories");

        String tags = ParamUtil.getString(actionRequest, "tags");

        double version = ParamUtil.getDouble(actionRequest, "version");

        ServiceContext serviceContext = ServiceContextFactory.getInstance(
            Reference.class.getName(), actionRequest);

        if (Validator.isNotNull(categories)) {

            serviceContext.setAssetCategoryIds(
                StringUtil.split(categories, 0L));
        }

        if (Validator.isNotNull(tags)) {

            serviceContext.setAssetTagNames(StringUtil.split(tags));
        }

        Reference reference = null;
        String oldUrlTitle = StringPool.BLANK;

        if ("addReference".equals(actionName)) {

            reference = referenceService.addReference(
                groupId, StringPool.BLANK, true, name, shortDescription,
                description, realizationDateMonth, realizationDateDay,
                realizationDateYear, emphasized, overlayText, overlayUrl,
                urlTitle, smallImage, largeImage, otherImages, serviceContext);
        } else {

            reference =
                referenceService.getReference(groupId, referenceId, version);

            String tempOldUrlTitle = reference.getUrlTitle();

            if ("updateReference".equals(actionName)) {

                reference = referenceService.updateReference(
                    groupId, referenceId, version, name, shortDescription,
                    description, realizationDateMonth, realizationDateDay,
                    realizationDateYear, emphasized, overlayText, overlayUrl,
                    urlTitle, smallImage, largeImage, otherImages,
                    serviceContext);
            }

            if (!tempOldUrlTitle.equals(reference.getUrlTitle())) {

                oldUrlTitle = tempOldUrlTitle;
            }
        }

        sendEditReferenceRedirect(
            actionRequest, actionResponse, reference, oldUrlTitle);

        boolean hideDefaultSuccessMessage =
            ParamUtil.getBoolean(actionRequest, "hideDefaultSuccessMessage");

        if (hideDefaultSuccessMessage) {
            hideDefaultSuccessMessage(actionRequest);
        }
    }

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {

        referenceWebConfiguration = ConfigurableUtil.createConfigurable(
            ReferenceWebConfiguration.class, properties);
    }

    protected void deleteReferences(
        ActionRequest actionRequest, ActionResponse actionResponse,
        boolean moveToTrash)
        throws Exception {

        ThemeDisplay themeDisplay =
            (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        String[] deleteReferenceIds = null;

        String referenceId = ParamUtil.getString(actionRequest, "referenceId");

        if (Validator.isNotNull(referenceId)) {

            deleteReferenceIds = new String[] {
                referenceId
            };
        } else {

            deleteReferenceIds = ParamUtil.getParameterValues(
                actionRequest, "rowIds" + Reference.class.getSimpleName());
        }

        List<TrashedModel> trashedModels = new ArrayList<>();

        for (String curDeleteReferenceId : deleteReferenceIds) {

            if (moveToTrash) {

                Reference reference = referenceService.moveReferenceToTrash(
                    themeDisplay.getScopeGroupId(),
                    HtmlUtil.unescape(curDeleteReferenceId));

                trashedModels.add(reference);
            } else {

                ActionUtil.deleteReference(
                    actionRequest, HtmlUtil.unescape(curDeleteReferenceId));
            }
        }

        if (moveToTrash && !trashedModels.isEmpty()) {

            Map<String, Object> data = new HashMap<>();

            data.put("trashedModels", trashedModels);

            SessionMessages.add(
                actionRequest, portal.getPortletId(actionRequest) +
                    SessionMessages.KEY_SUFFIX_DELETE_SUCCESS_DATA,
                data);

            hideDefaultSuccessMessage(actionRequest);
        }

        sendEditReferenceRedirect(actionRequest, actionResponse);
    }

    @Override
    protected void doDispatch(
        RenderRequest renderRequest, RenderResponse renderResponse)
        throws IOException, PortletException {

        if (SessionErrors.contains(
            renderRequest, NoSuchReferenceException.class.getName()) ||
            SessionErrors.contains(
                renderRequest, PrincipalException.class.getName())) {

            include("/error.jsp", renderRequest, renderResponse);
        } else {

            super.doDispatch(renderRequest, renderResponse);
        }
    }

    protected String getSaveAndContinueRedirect(
        ActionRequest actionRequest, Reference reference, String redirect)
        throws Exception {

        ThemeDisplay themeDisplay =
            (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        String referringPortletResource =
            ParamUtil.getString(actionRequest, "referringPortletResource");

        PortletURL portletURL = PortletURLFactoryUtil.create(
            actionRequest, ReferencePortletKeys.REFERENCE_DXP_PORTLET,
            themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

        portletURL.setParameter("mvcPath", "/edit_reference.jsp");
        portletURL.setParameter("redirect", redirect);
        portletURL.setParameter(
            "referringPortletResource", referringPortletResource);
        portletURL.setParameter(
            "resourcePrimKey", String.valueOf(reference.getResourcePrimKey()));
        portletURL.setParameter(
            "groupId", String.valueOf(reference.getGroupId()));
        portletURL.setParameter("referenceId", reference.getReferenceId());
        portletURL.setParameter(
            "version", String.valueOf(reference.getVersion()));
        portletURL.setWindowState(actionRequest.getWindowState());

        return portletURL.toString();
    }

    @Override
    protected boolean isAlwaysSendRedirect() {

        return true;
    }

    @Override
    protected boolean isSessionErrorException(Throwable cause) {

        if (isFileException(cause) || isReferenceException(cause) ||
            cause instanceof PrincipalException) {

            return true;
        }

        return false;
    }

    protected void sendEditReferenceRedirect(
        ActionRequest actionRequest, ActionResponse actionResponse)
        throws Exception {

        sendEditReferenceRedirect(
            actionRequest, actionResponse, null, StringPool.BLANK);
    }

    protected void sendEditReferenceRedirect(
        ActionRequest actionRequest, ActionResponse actionResponse,
        Reference reference, String oldUrlTitle)
        throws Exception {

        String actionName =
            ParamUtil.getString(actionRequest, ActionRequest.ACTION_NAME);

        String redirect = ParamUtil.getString(actionRequest, "redirect");

        int workflowAction = ParamUtil.getInteger(
            actionRequest, "workflowAction", WorkflowConstants.ACTION_PUBLISH);

        String portletId = HttpUtil.getParameter(redirect, "p_p_id", false);

        String namespace = PortalUtil.getPortletNamespace(portletId);

        if (Validator.isNotNull(oldUrlTitle)) {

            String oldRedirectParam = namespace + "redirect";

            String oldRedirect =
                HttpUtil.getParameter(redirect, oldRedirectParam, false);

            if (Validator.isNotNull(oldRedirect)) {

                String newRedirect = http.decodeURL(oldRedirect);

                newRedirect = StringUtil.replace(
                    newRedirect, oldUrlTitle, reference.getUrlTitle());
                newRedirect = StringUtil.replace(
                    newRedirect, oldRedirectParam, "redirect");

                redirect =
                    StringUtil.replace(redirect, oldRedirect, newRedirect);
            }
        }

        if (("deleteReference".equals(actionName) ||
            "deleteReferences".equals(actionName)) &&
            !ActionUtil.hasReference(actionRequest)) {

            ThemeDisplay themeDisplay =
                (ThemeDisplay) actionRequest.getAttribute(
                    WebKeys.THEME_DISPLAY);

            PortletURL portletURL = PortletURLFactoryUtil.create(
                actionRequest, themeDisplay.getPpid(), themeDisplay.getPlid(),
                PortletRequest.RENDER_PHASE);

            redirect = portletURL.toString();
        }

        if ((reference != null) &&
            (workflowAction == WorkflowConstants.ACTION_SAVE_DRAFT)) {

            redirect =
                getSaveAndContinueRedirect(actionRequest, reference, redirect);
        } else {

            WindowState windowState = actionRequest.getWindowState();

            if (windowState.equals(LiferayWindowState.POP_UP)) {

                redirect = portal.escapeRedirect(redirect);

                if (Validator.isNotNull(redirect)) {

                    if ("addReference".equals(actionName) &&
                        (reference != null)) {

                        redirect = http.addParameter(
                            redirect, namespace + "className",
                            Reference.class.getName());
                        redirect = http.addParameter(
                            redirect, namespace + "classPK",
                            ReferenceAssetRenderer.getClassPK(reference));
                    }
                }
            }
        }

        actionRequest.setAttribute(WebKeys.REDIRECT, redirect);
    }

    private static boolean isFileException(Throwable cause) {

        if (cause instanceof DuplicateFileException ||
            cause instanceof FileNameException ||
            cause instanceof FileSizeException) {

            return true;
        }

        return false;
    }

    private static boolean isReferenceException(Throwable cause) {

        boolean result = false;

        if (cause instanceof ReferenceShortDescriptionException) {

            result = true;
        } else if (cause instanceof ReferenceDescriptionException) {

            result = true;
        } else if (cause instanceof ReferenceRealizationDateException) {

            result = true;
        } else if (cause instanceof ReferenceNameException) {

            result = true;
        } else if (cause instanceof ReferenceUrlTitleException) {

            result = true;
        } else if (cause instanceof DuplicateUrlTitleException) {

            result = true;
        } else if (cause instanceof NoSuchReferenceException) {

            result = true;
        }

        return result;
    }
}
