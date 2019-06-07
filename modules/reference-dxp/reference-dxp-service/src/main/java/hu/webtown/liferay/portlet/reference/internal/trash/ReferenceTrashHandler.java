
package hu.webtown.liferay.portlet.reference.internal.trash;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.TrashedModel;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.trash.BaseTrashHandler;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.trash.TrashRenderer;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.trash.constants.TrashActionKeys;
import com.liferay.trash.exception.RestoreEntryException;
import com.liferay.trash.kernel.model.TrashEntry;

import hu.webtown.liferay.portlet.reference.constants.ReferenceActionKeys;
import hu.webtown.liferay.portlet.reference.constants.ReferenceConstants;
import hu.webtown.liferay.portlet.reference.model.Reference;
import hu.webtown.liferay.portlet.reference.model.ReferenceResource;
import hu.webtown.liferay.portlet.reference.service.ReferenceLocalService;
import hu.webtown.liferay.portlet.reference.service.ReferenceResourceLocalService;

@Component(
    property = {
        "model.class.name=hu.webtown.liferay.portlet.reference.model.Reference"
    },
    service = TrashHandler.class
)
public class ReferenceTrashHandler extends BaseTrashHandler {

    private ReferenceLocalService referenceLocalService;
    private ReferenceResourceLocalService referenceResourceLocalService;
    
    @org.osgi.service.component.annotations.Reference(
        target = "(resource.name=" + ReferenceConstants.RESOURCE_NAME + ")"
    )
    private PortletResourcePermission referencePortletResourcePermission;
    
    @org.osgi.service.component.annotations.Reference(
        target = "(model.class.name=hu.webtown.liferay.portlet.reference.model.Reference)"
    )
    private ModelResourcePermission<Reference> referenceModelResourcePermission;

    protected void checkDuplicateEntry(
        long classPK, long trashEntryId, String originalTitle, String newName)
        throws PortalException {

        Reference reference = referenceLocalService.getLatestReference(classPK);

        ReferenceResource referenceResource =
            referenceResourceLocalService.getReferenceResource(
                reference.getResourcePrimKey());

        if (Validator.isNotNull(newName)) {
            originalTitle = newName;
        }

        ReferenceResource originalReferenceResource =
            referenceResourceLocalService.fetchReferenceResource(
                reference.getGroupId(), originalTitle);

        if ((originalReferenceResource != null) &&
            (referenceResource.getPrimaryKey() != originalReferenceResource.getPrimaryKey())) {

            RestoreEntryException ree =
                new RestoreEntryException(RestoreEntryException.DUPLICATE);

            Reference duplicateReference = referenceLocalService.getReference(
                originalReferenceResource.getGroupId(), originalTitle);

            ree.setDuplicateEntryId(duplicateReference.getResourcePrimKey());
            ree.setOldName(duplicateReference.getReferenceId());
            ree.setTrashEntryId(trashEntryId);

            throw ree;
        }
    }

    protected void checkRestorableEntry(
        long classPK, long trashEntryId, long containerModelId,
        String originalTitle, String newName)
        throws PortalException {

        checkDuplicateEntry(classPK, trashEntryId, originalTitle, newName);
    }

    @Override
    public void checkRestorableEntry(
        long classPK, long containerModelId, String newName)
        throws PortalException {

        Reference reference = referenceLocalService.getLatestReference(classPK);

        checkRestorableEntry(
            classPK, 0, containerModelId, reference.getReferenceId(), newName);
    }

    @Override
    public void checkRestorableEntry(
        TrashEntry trashEntry, long containerModelId, String newName)
        throws PortalException {

        checkRestorableEntry(
            trashEntry.getClassPK(), trashEntry.getEntryId(), containerModelId,
            trashEntry.getTypeSettingsProperty("title"), newName);
    }

    @Override
    public void deleteTrashEntry(long classPK)
        throws PortalException {

        Reference reference = referenceLocalService.getLatestReference(classPK);

        referenceLocalService.deleteReference(
            reference.getGroupId(), reference.getReferenceId(), null);
    }

    @Override
    public String getClassName() {

        return Reference.class.getName();
    }
    
    @Override
    public String getRestoreContainedModelLink(
        PortletRequest portletRequest, long classPK)
        throws PortalException {
        
        PortletURL portletURL = PortletProviderUtil.getPortletURL(
            portletRequest, Reference.class.getName(),
            PortletProvider.Action.EDIT);

        return portletURL.toString();
    }

    @Override
    public TrashedModel getTrashedModel(long classPK) {

        return referenceLocalService.fetchLatestReference(classPK);
    }

    @Override
    public TrashEntry getTrashEntry(long classPK)
        throws PortalException {

        Reference reference = referenceLocalService.getLatestReference(classPK);

        return reference.getTrashEntry();
    }

    @Override
    public TrashRenderer getTrashRenderer(long classPK)
        throws PortalException {

        AssetRendererFactory<Reference> assetRendererFactory =
            AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClass(
                Reference.class);

        Reference reference = referenceLocalService.getLatestReference(classPK);

        return (TrashRenderer) assetRendererFactory.getAssetRenderer(
            reference.getId());
    }

    @Override
    protected boolean hasPermission(
        PermissionChecker permissionChecker, long classPK, String actionId)
        throws PortalException {

        return referenceModelResourcePermission.contains(
            permissionChecker, classPK, actionId);
    }

    @Override
    public boolean hasTrashPermission(
        PermissionChecker permissionChecker, long groupId, long classPK,
        String trashActionId)
        throws PortalException {

        if (TrashActionKeys.MOVE.equals(trashActionId)) {

            return referencePortletResourcePermission.contains(
                permissionChecker, groupId, ReferenceActionKeys.ADD_REFERENCE);
        }

        return super.hasTrashPermission(
            permissionChecker, groupId, classPK, trashActionId);
    }

    @Override
    public boolean isInTrash(long classPK)
        throws PortalException {

        Reference reference = referenceLocalService.getLatestReference(classPK);

        return reference.isInTrash();
    }

    @Override
    public void moveTrashEntry(
        long userId, long classPK, long containerModelId,
        ServiceContext serviceContext)
        throws PortalException {

        Reference reference = referenceLocalService.getLatestReference(classPK);

        referenceLocalService.moveReferenceFromTrash(
            userId, reference.getGroupId(), reference, serviceContext);
    }

    @Override
    public void restoreTrashEntry(long userId, long classPK)
        throws PortalException {

        Reference reference = referenceLocalService.getLatestReference(classPK);

        referenceLocalService.restoreReferenceFromTrash(userId, reference);
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

    @Override
    public void updateTitle(long classPK, String title)
        throws PortalException {

        Reference reference = referenceLocalService.getLatestReference(classPK);

        reference.setReferenceId(title);

        referenceLocalService.updateReference(reference);

        ReferenceResource referenceResource =
            referenceResourceLocalService.getReferenceResource(
                reference.getResourcePrimKey());

        referenceResource.setReferenceId(title);

        referenceResourceLocalService.updateReferenceResource(
            referenceResource);
    }

}
