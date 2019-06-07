
package hu.webtown.liferay.portlet.reference.service.permission;

import org.osgi.service.component.annotations.Component;

import com.liferay.exportimport.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.workflow.permission.WorkflowPermissionUtil;

import hu.webtown.liferay.portlet.reference.model.Reference;
import hu.webtown.liferay.portlet.reference.service.ReferenceLocalService;

@Component(
    property = { "model.class.name=hu.webtown.liferay.portlet.reference.model.Reference" },
    service = BaseModelPermissionChecker.class
)
@Deprecated
public class ReferencePermission implements BaseModelPermissionChecker {

    private static ReferenceLocalService referenceLocalService;

    public static void check(
        PermissionChecker permissionChecker, long resourcePrimKey,
        String actionId)
        throws PortalException {

        if (!contains(permissionChecker, resourcePrimKey, actionId)) {

            throw new PrincipalException.MustHavePermission(
                permissionChecker, Reference.class.getName(), resourcePrimKey,
                actionId);
        }
    }

    public static void check(
        PermissionChecker permissionChecker, long groupId, String referenceId,
        double version, String actionId)
        throws PortalException {

        if (!contains(
            permissionChecker, groupId, referenceId, version, actionId)) {

            throw new PrincipalException.MustHavePermission(
                permissionChecker, Reference.class.getName(), referenceId,
                actionId);
        }
    }

    public static void check(
        PermissionChecker permissionChecker, long groupId, String referenceId,
        int status, String actionId)
        throws PortalException {

        if (!contains(
            permissionChecker, groupId, referenceId, status, actionId)) {

            throw new PrincipalException.MustHavePermission(
                permissionChecker, Reference.class.getName(), referenceId,
                actionId);
        }
    }

    public static void check(
        PermissionChecker permissionChecker, long groupId, String referenceId,
        String actionId)
        throws PortalException {

        if (!contains(permissionChecker, groupId, referenceId, actionId)) {

            throw new PrincipalException.MustHavePermission(
                permissionChecker, Reference.class.getName(), referenceId,
                actionId);
        }
    }

    public static void check(
        PermissionChecker permissionChecker, Reference reference,
        String actionId)
        throws PortalException {

        if (!contains(permissionChecker, reference, actionId)) {

            throw new PrincipalException.MustHavePermission(
                permissionChecker, Reference.class.getName(),
                reference.getReferenceId(), actionId);
        }
    }

    public static boolean contains(
        PermissionChecker permissionChecker, long classPK, String actionId)
        throws PortalException {

        Reference reference =
            referenceLocalService.fetchLatestReference(classPK);

        if (reference == null) {

            reference = referenceLocalService.getReference(classPK);
        }

        return contains(permissionChecker, reference, actionId);
    }

    public static boolean contains(
        PermissionChecker permissionChecker, long groupId, String referenceId,
        double version, String actionId)
        throws PortalException {

        Reference reference =
            referenceLocalService.getReference(groupId, referenceId, version);

        return contains(permissionChecker, reference, actionId);
    }

    public static boolean contains(
        PermissionChecker permissionChecker, long groupId, String referenceId,
        int status, String actionId)
        throws PortalException {

        Reference reference = referenceLocalService.getLatestReference(
            groupId, referenceId, status);

        return contains(permissionChecker, reference, actionId);
    }

    public static boolean contains(
        PermissionChecker permissionChecker, long groupId, String referenceId,
        String actionId)
        throws PortalException {

        Reference reference =
            referenceLocalService.getReference(groupId, referenceId);

        return contains(permissionChecker, reference, actionId);
    }

    public static boolean contains(
        PermissionChecker permissionChecker, Reference reference,
        String actionId)
        throws PortalException {

        String portletId = PortletProviderUtil.getPortletId(
            Reference.class.getName(), PortletProvider.Action.EDIT);

        Boolean hasPermission = StagingPermissionUtil.hasPermission(
            permissionChecker, reference.getGroupId(),
            Reference.class.getName(), reference.getResourcePrimKey(),
            portletId, actionId);

        if (hasPermission != null) {

            return hasPermission.booleanValue();
        }

        if (reference.isDraft()) {

            if (actionId.equals(ActionKeys.VIEW) &&
                !contains(permissionChecker, reference, ActionKeys.UPDATE)) {

                return false;
            }
        } else if (reference.isPending()) {

            hasPermission = WorkflowPermissionUtil.hasPermission(
                permissionChecker, reference.getGroupId(),
                Reference.class.getName(), reference.getResourcePrimKey(),
                actionId);

            if (hasPermission != null) {

                return hasPermission.booleanValue();
            }
        }

        if (permissionChecker.hasOwnerPermission(
            reference.getCompanyId(), Reference.class.getName(),
            reference.getResourcePrimKey(), reference.getUserId(), actionId)) {

            return true;
        }

        return permissionChecker.hasPermission(
            reference.getGroupId(), Reference.class.getName(),
            reference.getResourcePrimKey(), actionId);
    }

    @Override
    public void checkBaseModel(
        PermissionChecker permissionChecker, long groupId, long primaryKey,
        String actionId)
        throws PortalException {

        check(permissionChecker, primaryKey, actionId);
    }

    @org.osgi.service.component.annotations.Reference(unbind = "-")
    protected void setReferenceLocalService(
        ReferenceLocalService newReferenceLocalService) {

        referenceLocalService = newReferenceLocalService;
    }

}
