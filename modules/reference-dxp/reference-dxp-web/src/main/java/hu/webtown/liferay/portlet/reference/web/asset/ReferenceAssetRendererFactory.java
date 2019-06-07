
package hu.webtown.liferay.portlet.reference.web.asset;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;
import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import hu.webtown.liferay.portlet.reference.constants.ReferenceActionKeys;
import hu.webtown.liferay.portlet.reference.constants.ReferenceConstants;
import hu.webtown.liferay.portlet.reference.constants.ReferencePortletKeys;
import hu.webtown.liferay.portlet.reference.model.Reference;
import hu.webtown.liferay.portlet.reference.model.ReferenceResource;
import hu.webtown.liferay.portlet.reference.service.ReferenceLocalService;
import hu.webtown.liferay.portlet.reference.service.ReferenceResourceLocalService;
import hu.webtown.liferay.portlet.reference.service.ReferenceService;

@Component(
    immediate = true,
    property = {
        "javax.portlet.name=" + ReferencePortletKeys.REFERENCE_DXP_PORTLET
    },
    service = AssetRendererFactory.class
)
public class ReferenceAssetRendererFactory
    extends BaseAssetRendererFactory<Reference> {

    public static final String CLASS_NAME = Reference.class.getName();

    public static final String TYPE = "reference";
    
    @org.osgi.service.component.annotations.Reference(
        target = "(model.class.name=hu.webtown.liferay.portlet.reference.model.Reference)"
    )
    private ModelResourcePermission<Reference> referenceModelResourcePermission;

    private ReferenceLocalService referenceLocalService;
    private ReferenceService referenceService;
    private ReferenceResourceLocalService referenceResourceLocalService;
    private ServletContext servletContext;
    
    @org.osgi.service.component.annotations.Reference
    private Portal portal;

    @org.osgi.service.component.annotations.Reference(
        target = "(resource.name=" + ReferenceConstants.RESOURCE_NAME + ")"
    )
    private PortletResourcePermission portletResourcePermission;

    public ReferenceAssetRendererFactory() {
        setClassName(Reference.class.getName());
        setLinkable(true);
        setPortletId(ReferencePortletKeys.REFERENCE_DXP_PORTLET);
        setSearchable(true);
    }
    
    @Override
    public AssetRenderer<Reference> getAssetRenderer(long classPK, int type)
        throws PortalException {

        Reference reference = referenceLocalService.fetchReference(classPK);

        if (reference == null) {

            ReferenceResource referenceResource =
                referenceResourceLocalService.getReferenceResource(classPK);

            reference = referenceLocalService.fetchLatestReference(
                referenceResource.getGroupId(),
                referenceResource.getReferenceId(),
                WorkflowConstants.STATUS_ANY);

            if ((reference == null) && (type == TYPE_LATEST)) {
                reference = referenceLocalService.fetchLatestReference(
                    classPK, WorkflowConstants.STATUS_ANY);
            }
        }

        ReferenceAssetRenderer renderer = getReferenceAssetRenderer(reference);

        renderer.setAssetRendererType(type);

        return renderer;
    }

    @Override
    public AssetRenderer<Reference> getAssetRenderer(
        long groupId, String urlTitle)
        throws PortalException {

        Reference reference =
            referenceService.getReferenceByUrlTitle(groupId, urlTitle);

        return new ReferenceAssetRenderer(reference);
    }

    @Override
    public AssetRenderer<Reference> getAssetRenderer(Reference entry, int type)
        throws PortalException {

        ReferenceAssetRenderer referenceAssetRenderer =
            getReferenceAssetRenderer(entry);

        referenceAssetRenderer.setAssetRendererType(type);

        return super.getAssetRenderer(entry, type);
    }

    @Override
    public String getClassName() {

        return CLASS_NAME;
    }

    @Override
    public String getType() {

        return TYPE;
    }

    @Override
    public PortletURL getURLAdd(
        LiferayPortletRequest liferayPortletRequest,
        LiferayPortletResponse liferayPortletResponse)
        throws PortalException {

        PortletURL portletURL = portal.getControlPanelPortletURL(
            liferayPortletRequest, getGroup(liferayPortletRequest),
            ReferencePortletKeys.REFERENCE_DXP_PORTLET, 0, 0,
            PortletRequest.RENDER_PHASE);
        
        portletURL.setParameter(
            "hideDefaultSuccessMessage", Boolean.TRUE.toString());

        portletURL.setParameter("mvcPath", "/edit_reference.jsp");

        return portletURL;
    }

    @Override
    public PortletURL getURLView(
        LiferayPortletResponse liferayPortletResponse, WindowState windowState)
        throws PortalException {

        LiferayPortletURL liferayPortletURL =
            liferayPortletResponse.createLiferayPortletURL(
                ReferencePortletKeys.REFERENCE_DXP_PORTLET,
                PortletRequest.RENDER_PHASE);

        try {

            liferayPortletURL.setWindowState(windowState);
        } catch (WindowStateException wse) {
        }

        return liferayPortletURL;
    }

    @Override
    public boolean hasAddPermission(
        PermissionChecker permissionChecker, long groupId, long classTypeId)
        throws Exception {

        return portletResourcePermission.contains(
            permissionChecker, groupId, ReferenceActionKeys.ADD_REFERENCE);
    }

    @Override
    public boolean hasPermission(
        PermissionChecker permissionChecker, long classPK, String actionId)
        throws Exception {

        return referenceModelResourcePermission.contains(
            permissionChecker, classPK, actionId);
    }

    @org.osgi.service.component.annotations.Reference(
        target = "(osgi.web.symbolicname=reference.dxp.web)",
        unbind = "-"
    )
    public void setServletContext(ServletContext servletContext) {

        this.servletContext = servletContext;
    }

    protected ReferenceAssetRenderer getReferenceAssetRenderer(
        Reference reference) {

        ReferenceAssetRenderer referenceAssetRenderer =
            new ReferenceAssetRenderer(reference);

        referenceAssetRenderer.setServletContext(servletContext);

        return referenceAssetRenderer;
    }

    @org.osgi.service.component.annotations.Reference(unbind = "-")
    protected void setReferenceLocalService(
        ReferenceLocalService referenceLocalService) {

        this.referenceLocalService = referenceLocalService;
    }

    @org.osgi.service.component.annotations.Reference(unbind = "-")
    protected void setReferenceResourceLocalService(
        ReferenceResourceLocalService referenceResourceLocalService) {

        this.referenceResourceLocalService = referenceResourceLocalService;
    }

    @org.osgi.service.component.annotations.Reference(unbind = "-")
    protected void setReferenceService(ReferenceService referenceService) {

        this.referenceService = referenceService;
    }

}
