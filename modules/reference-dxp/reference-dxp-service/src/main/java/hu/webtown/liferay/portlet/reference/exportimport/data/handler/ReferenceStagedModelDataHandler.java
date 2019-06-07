
package hu.webtown.liferay.portlet.reference.exportimport.data.handler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.exportimport.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelModifiedDateComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Element;

import hu.webtown.liferay.portlet.reference.constants.ReferenceConstants;
import hu.webtown.liferay.portlet.reference.constants.ReferenceImageType;
import hu.webtown.liferay.portlet.reference.model.Reference;
import hu.webtown.liferay.portlet.reference.model.ReferenceImage;
import hu.webtown.liferay.portlet.reference.model.ReferenceResource;
import hu.webtown.liferay.portlet.reference.service.ReferenceImageLocalService;
import hu.webtown.liferay.portlet.reference.service.ReferenceLocalService;
import hu.webtown.liferay.portlet.reference.service.ReferenceResourceLocalService;
import hu.webtown.liferay.portlet.reference.util.ReferenceUtil;

@Component(immediate = true, service = {
    ReferenceStagedModelDataHandler.class, StagedModelDataHandler.class
})
public class ReferenceStagedModelDataHandler
    extends BaseStagedModelDataHandler<Reference> {

    private static final String[] CLASS_NAMES = {
        Reference.class.getName()
    };

    private static final String CLASS_PK = "class-pk";

    private static final String GROUP_ID = "groupId";

    private static final Log LOG =
        LogFactoryUtil.getLog(ReferenceStagedModelDataHandler.class);

    private static final String PRELOADED = "preloaded";

    private static final String REFERENCE_ID = "reference-id";

    private static final String REFERENCE_IMAGE_TYPE = "reference-image-type";

    private static final String REFERENCE_RESOURCE_UUID =
        "reference-resource-uuid";

    private static final String UUID = "uuid";

    private static final String VERSION = "version";

    private DLAppLocalService dlAppLocalService;
    private ReferenceImageLocalService referenceImageLocalService;
    private ReferenceLocalService referenceLocalService;
    private ReferenceResourceLocalService referenceResourceLocalService;
    private UserLocalService userLocalService;

    @Override
    public void deleteStagedModel(Reference reference)
        throws PortalException {

        referenceLocalService.deleteReference(reference);
    }

    @Override
    public void deleteStagedModel(
        String uuid, long groupId, String className, String extraData)
        throws PortalException {

        ReferenceResource referenceResource =
            referenceResourceLocalService.fetchReferenceResourceByUuidAndGroupId(
                uuid, groupId);

        if (referenceResource == null) {
            return;
        }

        JSONObject extraDataJSONObject =
            JSONFactoryUtil.createJSONObject(extraData);

        if (Validator.isNotNull(extraData) && extraDataJSONObject.has(UUID)) {
            String referenceUuid = extraDataJSONObject.getString(UUID);

            Reference reference =
                fetchStagedModelByUuidAndGroupId(referenceUuid, groupId);

            deleteStagedModel(reference);
        } else {
            referenceLocalService.deleteReference(
                groupId, referenceResource.getReferenceId(), null);
        }
    }

    @Override
    public Reference fetchStagedModelByUuidAndGroupId(
        String uuid, long groupId) {

        return referenceLocalService.fetchReferenceByUuidAndGroupId(
            uuid, groupId);
    }

    @Override
    public List<Reference> fetchStagedModelsByUuidAndCompanyId(
        String uuid, long companyId) {

        return referenceLocalService.getReferencesByUuidAndCompanyId(
            uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            new StagedModelModifiedDateComparator<Reference>());
    }

    @Override
    public String[] getClassNames() {

        return CLASS_NAMES.clone();
    }

    @Override
    public String getDisplayName(Reference reference) {

        return reference.getName();
    }

    @Override
    public int[] getExportableStatuses() {

        return new int[] {
            WorkflowConstants.STATUS_APPROVED
        };
    }

    @Override
    public Map<String, String> getReferenceAttributes(
        PortletDataContext portletDataContext, Reference reference) {

        Map<String, String> referenceAttributes = new HashMap<>();

        String referenceResourceUuid = StringPool.BLANK;

        try {

            referenceResourceUuid = reference.getReferenceResourceUuid();
        } catch (Exception e) {

            if (LOG.isWarnEnabled()) {

                LOG.warn(e.getMessage(), e);
            }

            throw new IllegalStateException(
                "Unable to find reference resource for reference " +
                    reference.getReferenceId());
        }

        referenceAttributes.put(REFERENCE_RESOURCE_UUID, referenceResourceUuid);

        referenceAttributes.put(REFERENCE_ID, reference.getReferenceId());

        long defaultUserId = 0;

        try {

            defaultUserId =
                userLocalService.getDefaultUserId(reference.getCompanyId());
        } catch (Exception e) {

            if (LOG.isWarnEnabled()) {

                LOG.warn(e.getMessage(), e);
            }

            return referenceAttributes;
        }

        referenceAttributes.put(
            PRELOADED, String.valueOf(defaultUserId == reference.getUserId()));

        return referenceAttributes;
    }

    @Override
    public boolean validateReference(
        PortletDataContext portletDataContext, Element referenceElement) {

        validateMissingGroupReference(portletDataContext, referenceElement);

        String uuid = referenceElement.attributeValue(UUID);
        String referenceResourceUuid =
            referenceElement.attributeValue(REFERENCE_RESOURCE_UUID);

        Map<Long, Long> groupIds =
            (Map<Long, Long>) portletDataContext.getNewPrimaryKeysMap(
                Group.class);

        long groupId =
            GetterUtil.getLong(referenceElement.attributeValue(GROUP_ID));

        groupId = MapUtil.getLong(groupIds, groupId);

        String referenceReferenceId =
            referenceElement.attributeValue(REFERENCE_ID);
        boolean preloaded =
            GetterUtil.getBoolean(referenceElement.attributeValue(PRELOADED));

        Reference existingReference = fetchExistingReference(
            uuid, referenceResourceUuid, groupId, referenceReferenceId, null,
            0.0, preloaded);

        if (existingReference == null) {
            return false;
        }

        return true;
    }

    @Override
    protected boolean countStagedModel(
        PortletDataContext portletDataContext, Reference reference) {

        return !portletDataContext.isModelCounted(
            ReferenceResource.class.getName(), reference.getResourcePrimKey());
    }

    @Override
    protected void doExportStagedModel(
        PortletDataContext portletDataContext, Reference reference)
        throws Exception {

        Element referenceElement =
            portletDataContext.getExportDataElement(reference);

        referenceElement.addAttribute(
            REFERENCE_RESOURCE_UUID, reference.getReferenceResourceUuid());

        List<ReferenceImage> referenceImages =
            referenceImageLocalService.getReferenceImages(
                reference.getGroupId(), reference.getReferenceId(),
                reference.getVersion());

        for (ReferenceImage curReferenceImage : referenceImages) {

            exportReferenceImage(
                portletDataContext, curReferenceImage, reference,
                referenceElement);
        }

        reference.setStatusByUserUuid(reference.getStatusByUserUuid());

        long defaultUserId =
            userLocalService.getDefaultUserId(reference.getCompanyId());

        if (defaultUserId == reference.getUserId()) {

            referenceElement.addAttribute(
                PRELOADED, String.valueOf(Boolean.TRUE));
        }

        portletDataContext.addClassedModel(
            referenceElement, ExportImportPathUtil.getModelPath(reference),
            reference);
    }

    @Override
    protected void doImportMissingReference(
        PortletDataContext portletDataContext, Element referenceElement)
        throws PortletDataException {

        importMissingGroupReference(portletDataContext, referenceElement);

        String uuid = referenceElement.attributeValue(UUID);
        String referenceResourceUuid =
            referenceElement.attributeValue(REFERENCE_RESOURCE_UUID);

        Map<Long, Long> groupIds =
            (Map<Long, Long>) portletDataContext.getNewPrimaryKeysMap(
                Group.class);

        long groupId =
            GetterUtil.getLong(referenceElement.attributeValue(GROUP_ID));

        groupId = MapUtil.getLong(groupIds, groupId);

        String referenceReferenceId =
            referenceElement.attributeValue(REFERENCE_ID);
        boolean preloaded =
            GetterUtil.getBoolean(referenceElement.attributeValue(PRELOADED));

        Reference existingReference = null;

        existingReference = fetchExistingReference(
            uuid, referenceResourceUuid, groupId, referenceReferenceId, null,
            0.0, preloaded);

        Map<String, String> referenceReferenceIds =
            (Map<String, String>) portletDataContext.getNewPrimaryKeysMap(
                Reference.class + ".referenceId");

        referenceReferenceIds.put(
            referenceReferenceId, existingReference.getReferenceId());

        Map<Long, Long> referenceIds =
            (Map<Long, Long>) portletDataContext.getNewPrimaryKeysMap(
                Reference.class);

        long referenceId =
            GetterUtil.getLong(referenceElement.attributeValue(CLASS_PK));

        referenceIds.put(referenceId, existingReference.getId());
    }

    @Override
    protected void doImportStagedModel(
        PortletDataContext portletDataContext, Reference reference)
        throws Exception {

        long userId = portletDataContext.getUserId(reference.getUserUuid());

        User user = userLocalService.getUser(userId);

        String referenceId = reference.getReferenceId();

        boolean autoReferenceId = false;

        if (Validator.isNumber(referenceId) ||
            (referenceLocalService.fetchReference(
                portletDataContext.getScopeGroupId(), referenceId,
                ReferenceConstants.VERSION_DEFAULT) != null)) {

            autoReferenceId = true;
        }

        Map<String, String> referenceIds =
            (Map<String, String>) portletDataContext.getNewPrimaryKeysMap(
                Reference.class + ".referenceId");

        String newReferenceId = referenceIds.get(referenceId);

        if (Validator.isNotNull(newReferenceId)) {

            // A sibling of a different version was already assigned a new
            // reference id

            referenceId = newReferenceId;
            autoReferenceId = false;
        }

        Element referenceElement =
            portletDataContext.getImportDataStagedModelElement(reference);

        Date realizationDate = reference.getRealizationDate();

        int realizationDateMonth = 0;
        int realizationDateDay = 0;
        int realizationDateYear = 0;

        if (realizationDate != null) {

            Calendar realizationDateCal =
                CalendarFactoryUtil.getCalendar(user.getTimeZone());

            realizationDateCal.setTime(realizationDate);

            realizationDateMonth = realizationDateCal.get(Calendar.MONTH);
            realizationDateDay = realizationDateCal.get(Calendar.DATE);
            realizationDateYear = realizationDateCal.get(Calendar.YEAR);
        }

        Map<ReferenceImageType, List<String>> referenceImages =
            importReferenceImages(portletDataContext, referenceElement);

        String smallImage = getFirstValue(
            referenceImages, ReferenceImageType.SMALL_IMAGE, null);
        String largeImage = getFirstValue(
            referenceImages, ReferenceImageType.LARGE_IMAGE, null);
        String[] otherImages = getAllValuesAsArray(
            referenceImages, ReferenceImageType.OTHER_IMAGE, null);

        ServiceContext serviceContext =
            portletDataContext.createServiceContext(reference);

        serviceContext.setAddGroupPermissions(true);
        serviceContext.setAddGuestPermissions(true);

        if (reference.getStatus() != WorkflowConstants.STATUS_APPROVED) {

            serviceContext.setWorkflowAction(
                WorkflowConstants.ACTION_SAVE_DRAFT);
        }

        Reference importedReference = null;

        String referenceResourceUuid =
            referenceElement.attributeValue(REFERENCE_RESOURCE_UUID);

        if (portletDataContext.isDataStrategyMirror()) {

            serviceContext.setUuid(reference.getUuid());
            serviceContext.setAttribute(
                "referenceResourceUuid", referenceResourceUuid);
            serviceContext.setAttribute("urlTitle", reference.getUrlTitle());

            boolean preloaded = GetterUtil.getBoolean(
                referenceElement.attributeValue(PRELOADED));

            Reference existingReference = fetchExistingReference(
                referenceResourceUuid, portletDataContext.getScopeGroupId(),
                referenceId, newReferenceId, preloaded);

            Reference existingReferenceVersion = null;

            if (existingReference != null) {

                existingReferenceVersion = fetchExistingReferenceVersion(
                    reference.getUuid(), portletDataContext.getScopeGroupId(),
                    existingReference.getReferenceId(), reference.getVersion());
            }

            if ((existingReference != null) &&
                (existingReferenceVersion == null)) {

                referenceId = existingReference.getReferenceId();
                autoReferenceId = false;
            }

            if (existingReferenceVersion == null) {

                importedReference = referenceLocalService.addReference(
                    userId, portletDataContext.getScopeGroupId(), referenceId,
                    autoReferenceId, reference.getVersion(),
                    reference.getName(), reference.getShortDescription(),
                    reference.getDescription(), realizationDateMonth,
                    realizationDateDay, realizationDateYear,
                    reference.getEmphasized(), reference.getOverlayText(),
                    reference.getOverlayUrl(), reference.getUrlTitle(),
                    smallImage, largeImage, otherImages, serviceContext);
            } else {

                importedReference = referenceLocalService.updateReference(
                    userId, existingReference.getGroupId(),
                    existingReference.getReferenceId(), reference.getVersion(),
                    reference.getName(), reference.getShortDescription(),
                    reference.getDescription(), realizationDateMonth,
                    realizationDateDay, realizationDateYear,
                    reference.getEmphasized(), reference.getOverlayText(),
                    reference.getOverlayUrl(), reference.getUrlTitle(),
                    smallImage, largeImage, otherImages, serviceContext);

                String referenceUuid = reference.getUuid();
                String importedReferenceUuid = importedReference.getUuid();

                if (!referenceUuid.equals(importedReferenceUuid)) {

                    importedReference.setUuid(referenceUuid);

                    referenceLocalService.updateReference(importedReference);
                }
            }
        } else {

            importedReference = referenceLocalService.addReference(
                userId, portletDataContext.getScopeGroupId(), referenceId,
                autoReferenceId, reference.getVersion(), reference.getName(),
                reference.getShortDescription(), reference.getDescription(),
                realizationDateMonth, realizationDateDay, realizationDateYear,
                reference.getEmphasized(), reference.getOverlayText(),
                reference.getOverlayUrl(), reference.getUrlTitle(), smallImage,
                largeImage, otherImages, serviceContext);
        }

        portletDataContext.importClassedModel(reference, importedReference);

        if (Validator.isNull(newReferenceId)) {

            referenceIds.put(
                reference.getReferenceId(), importedReference.getReferenceId());
        }

        Map<Long, Long> referencePrimaryKeys =
            (Map<Long, Long>) portletDataContext.getNewPrimaryKeysMap(
                Reference.class + ".primaryKey");

        referencePrimaryKeys.put(
            reference.getPrimaryKey(), importedReference.getPrimaryKey());
    }

    @Override
    protected void doRestoreStagedModel(
        PortletDataContext portletDataContext, Reference reference)
        throws Exception {

        long userId = portletDataContext.getUserId(reference.getUserUuid());

        Element referenceElement =
            portletDataContext.getImportDataStagedModelElement(reference);

        String referenceResourceUuid =
            referenceElement.attributeValue(REFERENCE_RESOURCE_UUID);

        boolean preloaded =
            GetterUtil.getBoolean(referenceElement.attributeValue(PRELOADED));

        Reference existingReference = fetchExistingReference(
            reference.getUuid(), referenceResourceUuid,
            portletDataContext.getScopeGroupId(), reference.getReferenceId(),
            reference.getReferenceId(), reference.getVersion(), preloaded);

        if ((existingReference == null) || !existingReference.isInTrash()) {
            return;
        }

        TrashHandler trashHandler = existingReference.getTrashHandler();

        if (trashHandler.isRestorable(existingReference.getResourcePrimKey())) {

            trashHandler.restoreTrashEntry(
                userId, existingReference.getResourcePrimKey());
        }
    }

    protected void exportReferenceImage(
        PortletDataContext portletDataContext, ReferenceImage referenceImage,
        Reference reference, Element referenceElement)
        throws PortalException {

        FileEntry fileEntry = ReferenceUtil.getFileEntryFromJSON(
            portletDataContext.getScopeGroupId(), referenceImage.getImage());

        if (fileEntry != null) {

            Element fileEntryElement =
                StagedModelDataHandlerUtil.exportReferenceStagedModel(
                    portletDataContext, reference, fileEntry,
                    PortletDataContext.REFERENCE_TYPE_DEPENDENCY);

            fileEntryElement.addAttribute(
                REFERENCE_IMAGE_TYPE,
                String.valueOf(referenceImage.getImageType()));
        }
    }

    protected Reference fetchExistingReference(
        String referenceResourceUuid, long groupId, String referenceId,
        String newReferenceId, boolean preloaded) {

        Reference existingReference = null;

        if (!preloaded) {

            ReferenceResource referenceResource =
                referenceResourceLocalService.fetchReferenceResourceByUuidAndGroupId(
                    referenceResourceUuid, groupId);

            if (referenceResource == null) {

                return null;
            }

            return referenceLocalService.fetchReference(
                groupId, referenceResource.getReferenceId());
        }

        if (Validator.isNotNull(newReferenceId)) {

            existingReference =
                referenceLocalService.fetchReference(groupId, newReferenceId);
        }

        if ((existingReference == null) && Validator.isNull(newReferenceId)) {

            existingReference =
                referenceLocalService.fetchReference(groupId, referenceId);
        }

        return existingReference;
    }

    protected Reference fetchExistingReference(
        String referenceUuid, String referenceResourceUuid, long groupId,
        String referenceId, String newReferenceId, double version,
        boolean preloaded) {

        Reference reference = fetchExistingReference(
            referenceResourceUuid, groupId, referenceId, newReferenceId,
            preloaded);

        if (reference != null) {
            reference = fetchExistingReferenceVersion(
                referenceUuid, groupId, reference.getReferenceId(), version);
        }

        return reference;
    }

    protected Reference fetchExistingReferenceVersion(
        String referenceUuid, long groupId, String referenceId,
        double version) {

        Reference existingReference =
            fetchStagedModelByUuidAndGroupId(referenceUuid, groupId);

        if (existingReference != null) {

            return existingReference;
        }

        return referenceLocalService.fetchReference(
            groupId, referenceId, version);
    }

    protected Map<ReferenceImageType, List<String>> importReferenceImages(
        PortletDataContext portletDataContext, Element entityElement) {

        String elementPath = entityElement.attributeValue("path");

        StagedModel entityStagedModel =
            (StagedModel) portletDataContext.getZipEntryAsObject(
                entityElement, elementPath);

        List<Element> referenceElements =
            portletDataContext.getReferenceElements(
                entityStagedModel, DLFileEntry.class);

        Map<ReferenceImageType, List<String>> result =
            new EnumMap<>(ReferenceImageType.class);

        for (Element curReferenceElement : referenceElements) {

            long classPK = GetterUtil.getLong(
                curReferenceElement.attributeValue("class-pk"));

            try {

                StagedModelDataHandlerUtil.importReferenceStagedModel(
                    portletDataContext, entityStagedModel, DLFileEntry.class,
                    classPK);
            } catch (Exception e) {

                if (LOG.isDebugEnabled()) {

                    LOG.debug(e, e);
                } else if (LOG.isWarnEnabled()) {

                    StringBundler sb = new StringBundler(6);

                    sb.append("Unable to process file entry ");
                    sb.append(classPK);
                    sb.append(" for ");
                    sb.append(entityStagedModel.getModelClassName());
                    sb.append(" with primary key ");
                    sb.append(entityStagedModel.getPrimaryKeyObj());

                    LOG.warn(sb.toString());
                }
            }

            Map<Long, Long> dlFileEntryIds =
                (Map<Long, Long>) portletDataContext.getNewPrimaryKeysMap(
                    DLFileEntry.class + ".primaryKey");

            long fileEntryId =
                MapUtil.getLong(dlFileEntryIds, classPK, classPK);

            FileEntry importedFileEntry = null;

            try {

                importedFileEntry = dlAppLocalService.getFileEntry(fileEntryId);

                int referenceImageTypeValue = GetterUtil.getInteger(
                    curReferenceElement.attributeValue(REFERENCE_IMAGE_TYPE));

                ReferenceImageType recerenceImageType =
                    ReferenceImageType.getById(referenceImageTypeValue);

                if (recerenceImageType != null) {

                    List<String> resultValue = result.get(recerenceImageType);

                    if (resultValue == null) {

                        resultValue = new ArrayList<>();
                    }

                    JSONObject fileEntryJson =
                        JSONFactoryUtil.createJSONObject();

                    fileEntryJson.put(
                        GROUP_ID,
                        String.valueOf(importedFileEntry.getGroupId()));
                    fileEntryJson.put(UUID, importedFileEntry.getUuid());
                    fileEntryJson.put(VERSION, importedFileEntry.getVersion());

                    resultValue.add(fileEntryJson.toString());
                    result.put(recerenceImageType, resultValue);
                }
            } catch (PortalException pe) {

                if (LOG.isDebugEnabled()) {

                    LOG.debug(pe, pe);
                } else if (LOG.isWarnEnabled()) {

                    LOG.warn(pe.getMessage());
                }

                continue;
            }
        }

        return result;
    }

    @org.osgi.service.component.annotations.Reference(unbind = "-")
    protected void setDLAppLocalService(DLAppLocalService dlAppLocalService) {

        this.dlAppLocalService = dlAppLocalService;
    }

    @org.osgi.service.component.annotations.Reference(unbind = "-")
    protected void setReferenceImageLocalService(
        ReferenceImageLocalService referenceImageLocalService) {

        this.referenceImageLocalService = referenceImageLocalService;
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
    protected void setUserLocalService(UserLocalService userLocalService) {

        this.userLocalService = userLocalService;
    }

    private String[] getAllValuesAsArray(
        Map<ReferenceImageType, List<String>> map, ReferenceImageType key,
        String[] defaultValue) {

        if (map.containsKey(key)) {

            List<String> value = map.get(key);

            if (value != null && !value.isEmpty()) {

                return value.toArray(new String[value.size()]);
            }
        }

        return defaultValue;
    }

    private String getFirstValue(
        Map<ReferenceImageType, List<String>> map, ReferenceImageType key,
        String defaultValue) {

        if (map.containsKey(key)) {

            List<String> value = map.get(key);

            if (value != null && !value.isEmpty()) {

                return value.get(0);
            }
        }

        return defaultValue;
    }

}
