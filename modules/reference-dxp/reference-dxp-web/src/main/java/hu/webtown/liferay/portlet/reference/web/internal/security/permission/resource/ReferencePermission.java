
package hu.webtown.liferay.portlet.reference.web.internal.security.permission.resource;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import hu.webtown.liferay.portlet.reference.model.Reference;

@Component(immediate = true)
public class ReferencePermission {

    private static ModelResourcePermission<Reference> referenceModelResourcePermission;

    public static boolean contains(
        PermissionChecker permissionChecker, Reference reference,
        String actionId)
        throws PortalException {

        return referenceModelResourcePermission.contains(
            permissionChecker, reference, actionId);
    }

    public static boolean contains(
        PermissionChecker permissionChecker, long classPK, String actionId)
        throws PortalException {

        return referenceModelResourcePermission.contains(
            permissionChecker, classPK, actionId);
    }

    @org.osgi.service.component.annotations.Reference(
        target = "(model.class.name=hu.webtown.liferay.portlet.reference.model.Reference)",
        unbind = "-"
    )
    protected void setModelResourcePermission(
        ModelResourcePermission<Reference> modelResourcePermission) {

        referenceModelResourcePermission = modelResourcePermission;
    }
}
