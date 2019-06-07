
package hu.webtown.liferay.portlet.reference.web.internal.security.permission.resource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import hu.webtown.liferay.portlet.reference.constants.ReferenceConstants;

@Component(immediate = true)
public class ReferenceAdminPermission {

    private static PortletResourcePermission referencePortletResourcePermission;

    public static void check(
        PermissionChecker permissionChecker, Group group, String actionId)
        throws PortalException {

        referencePortletResourcePermission.check(
            permissionChecker, group, actionId);
    }

    public static boolean contains(
        PermissionChecker permissionChecker, Group group, String actionId) {

        return referencePortletResourcePermission.contains(
            permissionChecker, group, actionId);
    }

    public static boolean contains(
        PermissionChecker permissionChecker, long groupId, String actionId) {

        return referencePortletResourcePermission.contains(
            permissionChecker, groupId, actionId);
    }

    @Reference(
        target = "(resource.name=" + ReferenceConstants.RESOURCE_NAME + ")",
        unbind = "-"
    )
    protected void setPortletResourcePermission(
        PortletResourcePermission portletResourcePermission) {

        referencePortletResourcePermission = portletResourcePermission;
    }
}
