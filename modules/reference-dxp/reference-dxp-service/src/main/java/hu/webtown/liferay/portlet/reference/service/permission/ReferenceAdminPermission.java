
package hu.webtown.liferay.portlet.reference.service.permission;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseResourcePermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourcePermissionChecker;

import hu.webtown.liferay.portlet.reference.model.Reference;

@Component(
    immediate = true,
    property = { "resource.name=" + ReferenceAdminPermission.RESOURCE_NAME },
    service = ResourcePermissionChecker.class
)
@Deprecated
public class ReferenceAdminPermission extends BaseResourcePermissionChecker {

    public static final String RESOURCE_NAME =
        "hu.webtown.liferay.portlet.reference";

    public static void check(
        PermissionChecker permissionChecker, long groupId, String actionId)
        throws PortalException {

        if (!contains(permissionChecker, groupId, actionId)) {
            throw new PrincipalException.MustHavePermission(
                permissionChecker, RESOURCE_NAME, groupId, actionId);
        }
    }

    public static boolean contains(
        PermissionChecker permissionChecker, long classPK, String actionId) {

        String portletId = PortletProviderUtil.getPortletId(
            Reference.class.getName(), PortletProvider.Action.EDIT);

        return contains(
            permissionChecker, RESOURCE_NAME, portletId, classPK, actionId);
    }

    @Override
    public Boolean checkResource(
        PermissionChecker permissionChecker, long classPK, String actionId) {

        return contains(permissionChecker, classPK, actionId);
    }

}
