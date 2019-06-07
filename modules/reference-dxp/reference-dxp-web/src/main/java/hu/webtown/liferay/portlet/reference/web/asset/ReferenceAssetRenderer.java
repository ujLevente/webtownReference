
package hu.webtown.liferay.portlet.reference.web.asset;

import java.util.Locale;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.asset.display.page.model.AssetDisplayPageEntry;
import com.liferay.asset.display.page.service.AssetDisplayPageEntryLocalServiceUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.trash.TrashRenderer;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import hu.webtown.liferay.portlet.reference.constants.ReferenceActionKeys;
import hu.webtown.liferay.portlet.reference.constants.ReferenceConstants;
import hu.webtown.liferay.portlet.reference.constants.ReferencePortletKeys;
import hu.webtown.liferay.portlet.reference.model.Reference;
import hu.webtown.liferay.portlet.reference.util.ReferenceUtil;
import hu.webtown.liferay.portlet.reference.web.internal.security.permission.resource.ReferencePermission;

public class ReferenceAssetRenderer extends BaseJSPAssetRenderer<Reference>
    implements TrashRenderer {

    public static final String TYPE = "reference";

    public static long getClassPK(Reference reference) {

        if ((reference.isDraft() || reference.isPending()) &&
            (!ReferenceUtil.isDoublesEquals(
                reference.getVersion(), ReferenceConstants.VERSION_DEFAULT))) {

            return reference.getPrimaryKey();
        } else {

            return reference.getResourcePrimKey();
        }
    }

    private final Reference reference;

    public ReferenceAssetRenderer(Reference reference) {
        this.reference = reference;
    }

    @Override
    public Reference getAssetObject() {

        return reference;
    }

    @Override
    public String getClassName() {

        return Reference.class.getName();
    }

    @Override
    public long getClassPK() {

        return getClassPK(reference);
    }

    @Override
    public long getGroupId() {

        return reference.getGroupId();
    }

    @Override
    public String getJspPath(HttpServletRequest request, String template) {
        
        if (reference.isInTrash() && TEMPLATE_FULL_CONTENT.equals(template)) {
            
            return "/trash/" + template + ".jsp";
        }

        if (TEMPLATE_ABSTRACT.equals(template) ||
                TEMPLATE_FULL_CONTENT.equals(template)) {

            return "/asset/" + template + ".jsp";
        }

        return null;
    }

    @Override
    public String getPortletId() {

        return ReferencePortletKeys.REFERENCE_DXP_PORTLET;
    }

    public Reference getReference() {

        return reference;
    }
    
    @Override
    public int getStatus() {
    
        return reference.getStatus();
    }

    @Override
    public String getSummary(
        PortletRequest portletRequest, PortletResponse portletResponse) {

        return HtmlUtil.stripHtml(reference.getDescription());
    }

    @Override
    public String getTitle(Locale locale) {

        return reference.getName();
    }

    @Override
    public String getType() {

        return TYPE;
    }

    @Override
    public PortletURL getURLEdit(
        LiferayPortletRequest liferayPortletRequest,
        LiferayPortletResponse liferayPortletResponse)
        throws Exception {

        PortletURL portletURL = PortalUtil.getControlPanelPortletURL(
            liferayPortletRequest, ReferencePortletKeys.REFERENCE_DXP_PORTLET,
            PortletRequest.RENDER_PHASE);

        portletURL.setParameter("mvcPath", "/edit_reference.jsp");
        portletURL.setParameter(
            "groupId", String.valueOf(reference.getGroupId()));
        portletURL.setParameter("referenceId", reference.getReferenceId());
        portletURL.setParameter(
            "version", String.valueOf(reference.getVersion()));

        return portletURL;
    }

    @Override
    public PortletURL getURLExport(
        LiferayPortletRequest liferayPortletRequest,
        LiferayPortletResponse liferayPortletResponse)
        throws Exception {
        
        ThemeDisplay themeDisplay =
            (ThemeDisplay) liferayPortletRequest.getAttribute(
                WebKeys.THEME_DISPLAY);

        PortletURL portletURL = PortletURLFactoryUtil.create(
            liferayPortletRequest, ReferencePortletKeys.REFERENCE_DXP_PORTLET,
            themeDisplay.getPlid(), PortletRequest.RESOURCE_PHASE);

        LiferayPortletURL liferayPortletURL = (LiferayPortletURL) portletURL;

        liferayPortletURL.setParameter(
            "groupId", String.valueOf(reference.getGroupId()));
        liferayPortletURL.setParameter(
            "referenceId", reference.getReferenceId());
        liferayPortletURL.setResourceID("exportReference");

        return liferayPortletURL;
    }

    @Override
    public String getUrlTitle() {

        return reference.getUrlTitle();
    }

    @Override
    public String getURLViewInContext(
        LiferayPortletRequest liferayPortletRequest,
        LiferayPortletResponse liferayPortletResponse,
        String noSuchEntryRedirect)
        throws Exception {

        ThemeDisplay themeDisplay =
            (ThemeDisplay) liferayPortletRequest.getAttribute(
                WebKeys.THEME_DISPLAY);

        Layout layout = themeDisplay.getLayout();

        String portletId =
            (String) liferayPortletRequest.getAttribute(WebKeys.PORTLET_ID);

        PortletPreferences portletSetup =
            PortletPreferencesFactoryUtil.getStrictLayoutPortletSetup(
                layout, portletId);

        String linkToLayoutUuid = GetterUtil.getString(
            portletSetup.getValue("portletSetupLinkToLayoutUuid", null));
        
        AssetRendererFactory<Reference> assetRendererFactory = getAssetRendererFactory();

        AssetEntry assetEntry = assetRendererFactory.getAssetEntry(
            Reference.class.getName(), getClassPK());
        
        AssetDisplayPageEntry assetDisplayPageEntry =
            AssetDisplayPageEntryLocalServiceUtil.fetchAssetDisplayPageEntry(
                assetEntry.getGroupId(), assetEntry.getClassNameId(),
                getClassPK());
        
        Group group = themeDisplay.getScopeGroup();
        
        LayoutPageTemplateEntry defaultLayoutPageTemplateEntry = null;
        
        if (assetDisplayPageEntry == null) {
            defaultLayoutPageTemplateEntry =
                LayoutPageTemplateEntryServiceUtil.fetchDefaultLayoutPageTemplateEntry(
                    group.getGroupId(), assetEntry.getClassNameId(),
                    assetEntry.getClassTypeId());
        }

        if ((assetDisplayPageEntry != null || defaultLayoutPageTemplateEntry != null) && Validator.isNull(linkToLayoutUuid)) {

            if (group.getGroupId() != reference.getGroupId()) {

                group = GroupLocalServiceUtil.getGroup(reference.getGroupId());
            }
            
            String groupFriendlyURL = PortalUtil.getGroupFriendlyURL(
                LayoutSetLocalServiceUtil.getLayoutSet(
                    group.getGroupId(), layout.isPrivateLayout()),
                themeDisplay);

//            String layoutFriendlyURL =
//                PortalUtil.getLayoutFriendlyURL(layout, themeDisplay);
//
//            Portlet portlet = PortletLocalServiceUtil.getPortletById(
//                themeDisplay.getCompanyId(),
//                ReferencePortletKeys.REFERENCE_DXP_PORTLET);

            return PortalUtil.addPreservedParameters(
                themeDisplay,
                groupFriendlyURL.concat(
                    ReferenceConstants.CANONICAL_URL_SEPARATOR).concat(
                                HtmlUtil.escape(reference.getUrlTitle())));
        }

        return noSuchEntryRedirect;
    }

    @Override
    public long getUserId() {

        return reference.getUserId();
    }

    @Override
    public String getUserName() {

        return reference.getUserName();
    }

    @Override
    public String getUuid() {

        return reference.getUuid();
    }
    
    @Override
    public boolean hasEditPermission(PermissionChecker permissionChecker)
        throws PortalException {

        return ReferencePermission.contains(
            permissionChecker, reference, ReferenceActionKeys.UPDATE);
    }

    @Override
    public boolean hasViewPermission(PermissionChecker permissionChecker)
        throws PortalException {

        return ReferencePermission.contains(
            permissionChecker, reference, ReferenceActionKeys.VIEW);
    }

    @Override
    public boolean include(
        HttpServletRequest request, HttpServletResponse response,
        String template)
        throws Exception {

        request.setAttribute("REFERENCE", reference);

        return super.include(request, response, template);
    }
    
    @Override
    public boolean isDisplayable() {
    
        return true;
    }
    
}
