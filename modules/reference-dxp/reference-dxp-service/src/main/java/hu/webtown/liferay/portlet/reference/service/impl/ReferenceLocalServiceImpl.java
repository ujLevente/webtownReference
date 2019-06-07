/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package hu.webtown.liferay.portlet.reference.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.xml.ws.WebServiceRefs;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.asset.kernel.model.AssetLinkConstants;
import com.liferay.expando.kernel.util.ExpandoBridgeUtil;
import com.liferay.exportimport.kernel.lar.ExportImportThreadLocal;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.systemevent.SystemEventHierarchyEntryThreadLocal;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MathUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.trash.TrashHelper;
import com.liferay.trash.exception.RestoreEntryException;
import com.liferay.trash.exception.TrashEntryException;
import com.liferay.trash.kernel.model.TrashEntry;
import com.liferay.trash.kernel.model.TrashVersion;

import aQute.bnd.annotation.ProviderType;
import hu.webtown.liferay.portlet.reference.constants.ReferenceConstants;
import hu.webtown.liferay.portlet.reference.constants.ReferenceImageType;
import hu.webtown.liferay.portlet.reference.exception.DuplicateUrlTitleException;
import hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException;
import hu.webtown.liferay.portlet.reference.exception.ReferenceDescriptionException;
import hu.webtown.liferay.portlet.reference.exception.ReferenceNameException;
import hu.webtown.liferay.portlet.reference.exception.ReferenceRealizationDateException;
import hu.webtown.liferay.portlet.reference.exception.ReferenceShortDescriptionException;
import hu.webtown.liferay.portlet.reference.exception.ReferenceUrlTitleException;
import hu.webtown.liferay.portlet.reference.exception.ReferenceVersionException;
import hu.webtown.liferay.portlet.reference.model.Reference;
import hu.webtown.liferay.portlet.reference.model.ReferenceResource;
import hu.webtown.liferay.portlet.reference.service.base.ReferenceLocalServiceBaseImpl;
import hu.webtown.liferay.portlet.reference.util.ReferenceUtil;
import hu.webtown.liferay.portlet.reference.util.comparator.ReferenceIDComparator;
import hu.webtown.liferay.portlet.reference.util.comparator.ReferenceVersionComparator;

/**
 * The implementation of the reference local service. <p> All custom service
 * methods should be put in this class. Whenever methods are added, rerun
 * ServiceBuilder to copy their definitions into the
 * {@link hu.webtown.liferay.portlet.reference.service.ReferenceLocalService}
 * interface. <p> This is a local service. Methods of this service will not have
 * security checks based on the propagated JAAS credentials because this service
 * can only be accessed from within the same VM. </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReferenceLocalServiceBaseImpl
 * @see hu.webtown.liferay.portlet.reference.service.ReferenceLocalServiceUtil
 */
@ProviderType
public class ReferenceLocalServiceImpl extends ReferenceLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS: Never reference this class directly. Always use
     * {@link
     * hu.webtown.liferay.portlet.reference.service.ReferenceLocalServiceUtil}
     * to access the reference local service.
     */

    private static final Log LOG =
        LogFactoryUtil.getLog(ReferenceLocalServiceImpl.class);
    
    @ServiceReference(type = TrashHelper.class)
    private TrashHelper trashHelper;

    /**
     * Adds a reference with additional parameters.
     * 
     * @param userId
     *            the primary key of the reference's creator/owner
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
     * @param autoReferenceId
     *            whether to auto generate the reference ID
     * @param version
     *            the reference's version
     * @param name
     *            the reference's name
     * @param shortDescription
     *            the reference's short description
     * @param description
     *            the reference's description
     * @param realizationDateMonth
     *            the month the reference is set to realized
     * @param realizationDateDay
     *            the calendar day the reference is set to realized
     * @param realizationDateYear
     *            the year the reference is set to realized
     * @param emphasized
     *            the reference's emphasized
     * @param overlayText
     *            the reference's overlay text
     * @param overlayUrl
     *            the reference's overlay url
     * @param urlTitle
     *            the reference's url title
     * @param smallImage
     *            the reference's small image
     * @param largeImage
     *            the reference's large image
     * @param otherImages
     *            the reference's other images
     * @param serviceContext
     *            the service context to be applied. Can set the UUID, creation
     *            date, modification date, expando bridge attributes, guest
     *            permissions, group permissions, asset category IDs, asset tag
     *            names, asset link entry IDs, URL title, and workflow actions
     *            for the reference. Can also set whether to add the default
     *            guest and group permissions.
     * @return the reference
     */
    @Indexable(type = IndexableType.REINDEX)
    @Override
    public Reference addReference(
        long userId, long groupId, String referenceId, boolean autoReferenceId,
        double version, String name, String shortDescription,
        String description, int realizationDateMonth, int realizationDateDay,
        int realizationDateYear, boolean emphasized, String overlayText,
        String overlayUrl, String urlTitle, String smallImage,
        String largeImage, String[] otherImages, ServiceContext serviceContext)
        throws PortalException {

        // Reference

        User user = userPersistence.findByPrimaryKey(userId);
        String newReferenceId = StringUtil.toUpperCase(referenceId.trim());

        Date realizationDate = PortalUtil.getDate(
            realizationDateMonth, realizationDateDay, realizationDateYear, 0, 0,
            user.getTimeZone(), ReferenceRealizationDateException.class);

        Date now = new Date();

        if (autoReferenceId) {

            newReferenceId = String.valueOf(counterLocalService.increment());
        }

        validate(groupId, 0L, name, urlTitle, shortDescription, description);

        serviceContext.setAttribute("referenceId", newReferenceId);

        long id = counterLocalService.increment();

        String referenceResourceUuid = GetterUtil.getString(
            serviceContext.getAttribute("referenceResourceUuid"));

        long resourcePrimKey =
            referenceResourceLocalService.getReferenceResourcePrimKey(
                referenceResourceUuid, groupId, newReferenceId);

        Reference reference = referencePersistence.create(id);

        reference.setUuid(serviceContext.getUuid());
        reference.setResourcePrimKey(resourcePrimKey);
        reference.setGroupId(groupId);
        reference.setCompanyId(user.getCompanyId());
        reference.setUserId(user.getUserId());
        reference.setUserName(user.getFullName());
        reference.setCreateDate(serviceContext.getCreateDate(now));
        reference.setModifiedDate(serviceContext.getModifiedDate(now));
        reference.setReferenceId(newReferenceId);
        reference.setVersion(version);
        reference.setName(name);
        reference.setUrlTitle(urlTitle);
        reference.setShortDescription(shortDescription);
        reference.setDescription(description);
        reference.setRealizationDate(realizationDate);
        reference.setEmphasized(emphasized);
        reference.setOverlayText(overlayText);
        reference.setOverlayUrl(overlayUrl);

        reference.setStatus(WorkflowConstants.STATUS_DRAFT);
        reference.setStatusByUserId(userId);
        reference.setStatusDate(serviceContext.getModifiedDate(now));
        reference.setExpandoBridgeAttributes(serviceContext);

        referencePersistence.update(reference);

        // Reference images

        addReferenceImages(
            groupId, newReferenceId, version, smallImage, largeImage,
            otherImages);

        // Resources

        if (serviceContext.isAddGroupPermissions() ||
            serviceContext.isAddGuestPermissions()) {

            addReferenceResources(
                reference, serviceContext.isAddGroupPermissions(),
                serviceContext.isAddGuestPermissions());
        } else {

            addReferenceResources(
                reference, serviceContext.getGroupPermissions(),
                serviceContext.getGuestPermissions());
        }

        // Asset

        updateAsset(
            userId, reference, serviceContext.getAssetCategoryIds(),
            serviceContext.getAssetTagNames(),
            serviceContext.getAssetLinkEntryIds(),
            serviceContext.getAssetPriority());

        // Workflow

        startWorkflowInstance(userId, reference, serviceContext);

        return referencePersistence.findByPrimaryKey(reference.getId());
    }

    @Override
    public void addReferenceImages(
        long groupId, String referenceId, double version, String smallImage,
        String largeImage, String[] otherImages) {

        if (Validator.isNotNull(smallImage)) {

            referenceImageLocalService.addReferenceImage(
                groupId, referenceId, version,
                ReferenceImageType.SMALL_IMAGE.getId(), smallImage);
        }

        if (Validator.isNotNull(largeImage)) {

            referenceImageLocalService.addReferenceImage(
                groupId, referenceId, version,
                ReferenceImageType.LARGE_IMAGE.getId(), largeImage);
        }

        if (otherImages != null) {

            for (String otherImage : otherImages) {

                if (Validator.isNotNull(otherImage)) {

                    referenceImageLocalService.addReferenceImage(
                        groupId, referenceId, version,
                        ReferenceImageType.OTHER_IMAGE.getId(), otherImage);
                }
            }
        }
    }

    /**
     * Adds the resources to the most recently created reference.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
     * @param addGroupPermissions
     *            whether to add group permissions
     * @param addGuestPermissions
     *            whether to add guest permissions
     */
    @Override
    public void addReferenceResources(
        long groupId, String referenceId, boolean addGroupPermissions,
        boolean addGuestPermissions)
        throws PortalException {

        Reference reference = getLatestReference(groupId, referenceId);

        addReferenceResources(
            reference, addGroupPermissions, addGuestPermissions);
    }

    /**
     * Adds the resources with the permissions to the most recently created
     * reference.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
     * @param groupPermissions
     *            the group permissions to be added
     * @param guestPermissions
     *            the guest permissions to be added
     */
    @Override
    public void addReferenceResources(
        long groupId, String referenceId, String[] groupPermissions,
        String[] guestPermissions)
        throws PortalException {

        Reference reference = getLatestReference(groupId, referenceId);

        addReferenceResources(reference, groupPermissions, guestPermissions);
    }

    /**
     * Adds the resources to the reference.
     *
     * @param reference
     *            the reference
     * @param addGroupPermissions
     *            whether to add group permissions
     * @param addGuestPermissions
     *            whether to add guest permissions
     */
    @Override
    public void addReferenceResources(
        Reference reference, boolean addGroupPermissions,
        boolean addGuestPermissions)
        throws PortalException {

        resourceLocalService.addResources(
            reference.getCompanyId(), reference.getGroupId(),
            reference.getUserId(), Reference.class.getName(),
            reference.getResourcePrimKey(), false, addGroupPermissions,
            addGuestPermissions);
    }

    /**
     * Adds the model resources with the permissions to the reference.
     *
     * @param reference
     *            the reference to add resources to
     * @param groupPermissions
     *            the group permissions to be added
     * @param guestPermissions
     *            the guest permissions to be added
     */
    @Override
    public void addReferenceResources(
        Reference reference, String[] groupPermissions,
        String[] guestPermissions)
        throws PortalException {

        resourceLocalService.addModelResources(
            reference.getCompanyId(), reference.getGroupId(),
            reference.getUserId(), Reference.class.getName(),
            reference.getResourcePrimKey(), groupPermissions, guestPermissions);
    }

    /**
     * Deletes the reference and its resources matching the group, reference ID,
     * and version.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
     * @param version
     *            the reference's version
     * @param serviceContext
     *            the service context to be applied.
     * @return the deleted reference
     */
    @Override
    public Reference deleteReference(
        long groupId, String referenceId, double version,
        ServiceContext serviceContext)
        throws PortalException {

        Reference reference =
            referencePersistence.findByG_R_V(groupId, referenceId, version);

        return referenceLocalService.deleteReference(reference, serviceContext);
    }

    /**
     * Deletes all references and their resources matching the group and
     * reference ID.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
     * @param serviceContext
     *            the service context to be applied.
     */
    @Override
    public void deleteReference(
        long groupId, String referenceId, ServiceContext serviceContext)
        throws PortalException {

        SystemEventHierarchyEntryThreadLocal.push(Reference.class);

        ReferenceResource referenceResource =
            referenceResourceLocalService.fetchReferenceResource(
                groupId, referenceId);

        try {
            List<Reference> references = referencePersistence.findByG_R(
                groupId, referenceId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
                new ReferenceVersionComparator(true));

            for (Reference reference : references) {
                referenceLocalService.deleteReference(
                    reference, serviceContext);
            }
        } finally {
            SystemEventHierarchyEntryThreadLocal.pop(Reference.class);
        }

        if (referenceResource != null) {
            systemEventLocalService.addSystemEvent(
                0, groupId, Reference.class.getName(),
                referenceResource.getResourcePrimKey(),
                referenceResource.getUuid(), null,
                SystemEventConstants.TYPE_DELETE, StringPool.BLANK);
        }
    }

    /**
     * Deletes the reference and its resources.
     *
     * @param reference
     *            the reference
     * @return the deleted reference
     */
    @Override
    @SystemEvent(action = SystemEventConstants.ACTION_SKIP, send = false, type = SystemEventConstants.TYPE_DELETE)
    public Reference deleteReference(Reference reference)
        throws PortalException {

        return referenceLocalService.deleteReference(reference, null);
    }

    /**
     * Deletes the reference and its resources.
     *
     * @param reference
     *            the reference
     * @param serviceContext
     *            the service context to be applied (optionally
     *            <code>null</code>).
     * @return the deleted reference
     */
    @Indexable(type = IndexableType.DELETE)
    @Override
    @SystemEvent(action = SystemEventConstants.ACTION_SKIP, send = false, type = SystemEventConstants.TYPE_DELETE)
    public Reference deleteReference(
        Reference reference, ServiceContext serviceContext)
        throws PortalException {

        ReferenceResource referenceResource =
            referenceResourceLocalService.fetchReferenceResource(
                reference.getGroupId(), reference.getReferenceId());

        if (reference.isApproved() && isLatestVersion(
            reference.getGroupId(), reference.getReferenceId(),
            reference.getVersion(), WorkflowConstants.STATUS_APPROVED)) {

            updatePreviousApprovedReference(reference);
        }

        // Reference images

        String referenceId = reference.getReferenceId();

        if (reference.isInTrash()) {
            referenceId =
                trashHelper.getOriginalTitle(reference.getReferenceId());
        }

        referenceImageLocalService.deleteReferenceImages(
            reference.getGroupId(), referenceId, reference.getVersion());

        // Expando

        expandoRowLocalService.deleteRows(reference.getId());

        // Trash

        if (reference.isInTrash()) {
            TrashEntry trashEntry = reference.getTrashEntry();

            if (trashEntry != null) {
                trashVersionLocalService.deleteTrashVersion(
                    Reference.class.getName(), reference.getId());
            }
        }

        // Workflow

        if (!reference.isDraft()) {
            workflowInstanceLinkLocalService.deleteWorkflowInstanceLink(
                reference.getCompanyId(), reference.getGroupId(),
                Reference.class.getName(), reference.getId());
        }

        int referencesCount = referencePersistence.countByG_R(
            reference.getGroupId(), reference.getReferenceId());

        if (referencesCount == 1) {

            // Asset

            assetEntryLocalService.deleteEntry(
                Reference.class.getName(), reference.getResourcePrimKey());

            // Trash

            trashEntryLocalService.deleteEntry(
                Reference.class.getName(), reference.getResourcePrimKey());

            // Resources

            resourceLocalService.deleteResource(
                reference.getCompanyId(), Reference.class.getName(),
                ResourceConstants.SCOPE_INDIVIDUAL,
                reference.getResourcePrimKey());

            // Resource

            if (referenceResource != null) {
                referenceResourceLocalService.deleteReferenceResource(
                    referenceResource);
            }
        }

        // Reference

        referencePersistence.remove(reference);

        // System event

        if (referenceResource != null) {
            JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

            extraDataJSONObject.put("uuid", reference.getUuid());
            extraDataJSONObject.put("version", reference.getVersion());

            systemEventLocalService.addSystemEvent(
                0, reference.getGroupId(), reference.getModelClassName(),
                reference.getPrimaryKey(), referenceResource.getUuid(), null,
                SystemEventConstants.TYPE_DELETE,
                extraDataJSONObject.toString());
        }

        return reference;
    }

    /**
     * Deletes all the group's references and resources.
     *
     * @param groupId
     *            the primary key of the reference's group
     */
    @Override
    public void deleteReferences(long groupId)
        throws PortalException {

        SystemEventHierarchyEntryThreadLocal.push(Reference.class);

        List<ReferenceResource> referenceResources = new ArrayList<>();

        try {
            ReferenceResource referenceResource = null;

            for (Reference reference : referencePersistence.findByGroupId(
                groupId)) {

                if ((referenceResource == null) ||
                    (referenceResource.getPrimaryKey() != reference.getResourcePrimKey())) {

                    referenceResource =
                        referenceResourceLocalService.getReferenceResource(
                            reference.getResourcePrimKey());

                    referenceResources.add(referenceResource);
                }

                referenceLocalService.deleteReference(reference, null);
            }
        } finally {
            SystemEventHierarchyEntryThreadLocal.pop(Reference.class);
        }

        for (ReferenceResource referenceResource : referenceResources) {
            systemEventLocalService.addSystemEvent(
                0, groupId, Reference.class.getName(),
                referenceResource.getResourcePrimKey(),
                referenceResource.getUuid(), null,
                SystemEventConstants.TYPE_DELETE, StringPool.BLANK);
        }
    }

    /**
     * Deletes all the group's references and resources, optionally including
     * recycled references.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param includeTrashedEntries
     *            whether to include recycled references
     */
    @Override
    public void deleteReferences(long groupId, boolean includeTrashedEntries)
        throws PortalException {

        SystemEventHierarchyEntryThreadLocal.push(Reference.class);

        List<ReferenceResource> referenceResources = new ArrayList<>();

        try {
            ReferenceResource referenceResource = null;

            for (Reference reference : referencePersistence.findByGroupId(
                groupId)) {

                if ((referenceResource == null) ||
                    (referenceResource.getPrimaryKey() != reference.getResourcePrimKey())) {

                    referenceResource =
                        referenceResourceLocalService.getReferenceResource(
                            reference.getResourcePrimKey());

                    referenceResources.add(referenceResource);
                }

                if (includeTrashedEntries || !reference.isInTrashExplicitly()) {
                    referenceLocalService.deleteReference(reference, null);
                } else {
                    referenceResources.remove(referenceResource);
                }
            }
        } finally {
            SystemEventHierarchyEntryThreadLocal.pop(Reference.class);
        }

        for (ReferenceResource referenceResource : referenceResources) {
            systemEventLocalService.addSystemEvent(
                0, groupId, Reference.class.getName(),
                referenceResource.getResourcePrimKey(),
                referenceResource.getUuid(), null,
                SystemEventConstants.TYPE_DELETE, StringPool.BLANK);
        }
    }

    /**
     * Returns the latest indexable reference matching the resource primary key.
     *
     * @param resourcePrimKey
     *            the primary key of the resource instance
     * @return the latest indexable reference matching the resource primary key,
     *         or <code>null</code> if no matching reference could be found
     */
    @Override
    public Reference fetchLatestIndexableReference(long resourcePrimKey) {

        OrderByComparator<Reference> orderByComparator =
            new ReferenceVersionComparator();

        int[] statuses = new int[] {
            WorkflowConstants.STATUS_APPROVED, WorkflowConstants.STATUS_IN_TRASH
        };

        List<Reference> references = referencePersistence.findByR_S(
            resourcePrimKey, statuses, 0, 1, orderByComparator);

        if (references.isEmpty()) {
            return null;
        }

        return references.get(0);
    }

    @Override
    public Reference fetchLatestReference(long resourcePrimKey) {

        return fetchLatestReference(
            resourcePrimKey, WorkflowConstants.STATUS_ANY);
    }

    @Override
    public Reference fetchLatestReference(long resourcePrimKey, int status) {

        return fetchLatestReference(resourcePrimKey, status, true);
    }

    /**
     * Returns the latest reference matching the resource primary key and
     * workflow status, optionally preferring references with approved workflow
     * status.
     *
     * @param resourcePrimKey
     *            the primary key of the resource instance
     * @param status
     *            the reference's workflow status. For more information see
     *            {@link WorkflowConstants} for constants starting with the
     *            "STATUS_" prefix.
     * @param preferApproved
     *            whether to prefer returning the latest matching reference that
     *            has workflow status {@link WorkflowConstants#STATUS_APPROVED}
     *            over returning one that has a different status
     * @return the latest reference matching the resource primary key and
     *         workflow status, optionally preferring references with an
     *         approved workflow status, or <code>null</code> if no matching
     *         reference could be found
     */
    @Override
    public Reference fetchLatestReference(
        long resourcePrimKey, int status, boolean preferApproved) {

        Reference reference = null;

        OrderByComparator<Reference> orderByComparator =
            new ReferenceVersionComparator();

        if (status == WorkflowConstants.STATUS_ANY) {

            if (preferApproved) {

                reference = referencePersistence.fetchByR_S_First(
                    resourcePrimKey, WorkflowConstants.STATUS_APPROVED,
                    orderByComparator);
            }

            if (reference == null) {

                reference = referencePersistence.fetchByResourcePrimKey_First(
                    resourcePrimKey, orderByComparator);
            }
        } else {

            reference = referencePersistence.fetchByR_S_First(
                resourcePrimKey, status, orderByComparator);
        }

        return reference;
    }

    @Override
    public Reference fetchLatestReference(
        long resourcePrimKey, int[] statuses) {

        OrderByComparator<Reference> orderByComparator =
            new ReferenceVersionComparator();

        List<Reference> references = referencePersistence.findByR_S(
            resourcePrimKey, statuses, 0, 1, orderByComparator);

        if (!references.isEmpty()) {

            return references.get(0);
        }

        return null;
    }

    /**
     * Returns the latest reference matching the group, reference ID, and
     * workflow status.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
     * @param status
     *            the reference's workflow status. For more information see
     *            {@link WorkflowConstants} for constants starting with the
     *            "STATUS_" prefix.
     * @return the latest matching reference, or <code>null</code> if no
     *         matching reference could be found
     */
    @Override
    public Reference fetchLatestReference(
        long groupId, String referenceId, int status) {

        OrderByComparator<Reference> orderByComparator =
            new ReferenceVersionComparator();

        if (status == WorkflowConstants.STATUS_ANY) {
            return referencePersistence.fetchByG_R_NotST_First(
                groupId, referenceId, WorkflowConstants.STATUS_IN_TRASH,
                orderByComparator);
        }

        return referencePersistence.fetchByG_R_ST_First(
            groupId, referenceId, status, orderByComparator);
    }

    @Override
    public Reference fetchLatestReferenceByUrlTitle(
        long groupId, String urlTitle, int status) {

        List<Reference> references;

        OrderByComparator<Reference> orderByComparator =
            new ReferenceVersionComparator();

        if (status == WorkflowConstants.STATUS_ANY) {

            references = referencePersistence.findByG_UT(
                groupId, urlTitle, 0, 1, orderByComparator);
        } else {

            references = referencePersistence.findByG_UT_ST(
                groupId, urlTitle, status, 0, 1, orderByComparator);
        }

        if (references.isEmpty()) {

            return null;
        }

        return references.get(0);
    }

    @Override
    public Reference fetchReference(long groupId, String referenceId) {

        // Get the latest reference that is approved, if none are approved, get
        // the latest unapproved reference

        Reference reference = fetchLatestReference(
            groupId, referenceId, WorkflowConstants.STATUS_APPROVED);

        if (reference != null) {
            return reference;
        }

        return fetchLatestReference(
            groupId, referenceId, WorkflowConstants.STATUS_ANY);
    }

    /**
     * Returns the reference matching the group, reference ID, and version.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
     * @param version
     *            the reference's version
     * @return the reference matching the group, reference ID, and version, or
     *         <code>null</code> if no reference could be found
     */
    @Override
    public Reference fetchReference(
        long groupId, String referenceId, double version) {

        return referencePersistence.fetchByG_R_V(groupId, referenceId, version);
    }

    @Override
    public Reference fetchReferenceByUrlTitle(long groupId, String urlTitle) {

        Reference reference = fetchLatestReferenceByUrlTitle(
            groupId, urlTitle, WorkflowConstants.STATUS_APPROVED);

        if (reference != null) {
            return reference;
        }

        return fetchLatestReferenceByUrlTitle(
            groupId, urlTitle, WorkflowConstants.STATUS_ANY);
    }

    /**
     * Returns an ordered range of all the references matching the company,
     * version, and workflow status. <p> Useful when paginating results. Returns
     * a maximum of <code>end - start</code> instances. <code>start</code> and
     * <code>end</code> are not primary keys, they are indexes in the result
     * set. Thus, <code>0</code> refers to the first result in the set. Setting
     * both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS}
     * will return the full result set. </p>
     *
     * @param companyId
     *            the primary key of the reference's company
     * @param version
     *            the reference's version
     * @param status
     *            the reference's workflow status. For more information see
     *            {@link WorkflowConstants} for constants starting with the
     *            "STATUS_" prefix.
     * @param start
     *            the lower bound of the range of references to return
     * @param end
     *            the upper bound of the range of references to return (not
     *            inclusive)
     * @return the range of matching references ordered by reference ID
     */
    @Override
    public List<Reference> getCompanyReferences(
        long companyId, double version, int status, int start, int end) {

        if (status == WorkflowConstants.STATUS_ANY) {

            return referencePersistence.findByC_V(
                companyId, version, start, end,
                new ReferenceIDComparator(true));
        } else {

            return referencePersistence.findByC_V_ST(
                companyId, version, status, start, end,
                new ReferenceIDComparator(true));
        }
    }

    /**
     * Returns an ordered range of all the references matching the company and
     * workflow status. <p> Useful when paginating results. Returns a maximum of
     * <code>end - start</code> instances. <code>start</code> and
     * <code>end</code> are not primary keys, they are indexes in the result
     * set. Thus, <code>0</code> refers to the first result in the set. Setting
     * both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS}
     * will return the full result set. </p>
     *
     * @param companyId
     *            the primary key of the reference's company
     * @param status
     *            the reference's workflow status. For more information see
     *            {@link WorkflowConstants} for constants starting with the
     *            "STATUS_" prefix.
     * @param start
     *            the lower bound of the range of references to return
     * @param end
     *            the upper bound of the range of references to return (not
     *            inclusive)
     * @return the range of matching references ordered by reference ID
     */
    @Override
    public List<Reference> getCompanyReferences(
        long companyId, int status, int start, int end) {

        if (status == WorkflowConstants.STATUS_ANY) {

            return referencePersistence.findByCompanyId(
                companyId, start, end, new ReferenceIDComparator(true));
        } else {

            return referencePersistence.findByC_ST(
                companyId, status, start, end, new ReferenceIDComparator(true));
        }
    }

    /**
     * Returns the number of references matching the company, version, and
     * workflow status. <p> Useful when paginating results. Returns a maximum of
     * <code>end - start</code> instances. <code>start</code> and
     * <code>end</code> are not primary keys, they are indexes in the result
     * set. Thus, <code>0</code> refers to the first result in the set. Setting
     * both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS}
     * will return the full result set. </p>
     *
     * @param companyId
     *            the primary key of the reference's company
     * @param version
     *            the reference's version
     * @param status
     *            the reference's workflow status. For more information see
     *            {@link WorkflowConstants} for constants starting with the
     *            "STATUS_" prefix.
     * @return the number of matching references
     */
    @Override
    public int getCompanyReferencesCount(
        long companyId, double version, int status) {

        if (status == WorkflowConstants.STATUS_ANY) {

            return referencePersistence.countByC_V(companyId, version);
        } else {

            return referencePersistence.countByC_V_ST(
                companyId, version, status);
        }
    }

    /**
     * Returns the number of references matching the company and workflow
     * status.
     *
     * @param companyId
     *            the primary key of the reference's company
     * @param status
     *            the reference's workflow status. For more information see
     *            {@link WorkflowConstants} for constants starting with the
     *            "STATUS_" prefix.
     * @return the number of matching references
     */
    @Override
    public int getCompanyReferencesCount(long companyId, int status) {

        if (status == WorkflowConstants.STATUS_ANY) {

            return referencePersistence.countByCompanyId(companyId);
        } else {

            return referencePersistence.countByC_ST(companyId, status);
        }
    }

    /**
     * Returns the latest reference matching the resource primary key,
     * preferring references with approved workflow status.
     *
     * @param resourcePrimKey
     *            the primary key of the resource instance
     * @return the latest reference matching the resource primary key,
     *         preferring references with approved workflow status
     */
    @Override
    public Reference getLatestReference(long resourcePrimKey)
        throws PortalException {

        return getLatestReference(
            resourcePrimKey, WorkflowConstants.STATUS_ANY);
    }

    /**
     * Returns the latest reference matching the resource primary key and
     * workflow status, preferring references with approved workflow status.
     *
     * @param resourcePrimKey
     *            the primary key of the resource instance
     * @param status
     *            the reference's workflow status. For more information see
     *            {@link WorkflowConstants} for constants starting with the
     *            "STATUS_" prefix.
     * @return the latest reference matching the resource primary key and
     *         workflow status, preferring references with approved workflow
     *         status
     */
    @Override
    public Reference getLatestReference(long resourcePrimKey, int status)
        throws PortalException {

        return getLatestReference(resourcePrimKey, status, true);
    }

    /**
     * Returns the latest reference matching the resource primary key and
     * workflow status, optionally preferring references with approved workflow
     * status.
     *
     * @param resourcePrimKey
     *            the primary key of the resource instance
     * @param status
     *            the reference's workflow status. For more information see
     *            {@link WorkflowConstants} for constants starting with the
     *            "STATUS_" prefix.
     * @param preferApproved
     *            whether to prefer returning the latest matching reference that
     *            has workflow status {@link WorkflowConstants#STATUS_APPROVED}
     *            over returning one that has a different status
     * @return the latest reference matching the resource primary key and
     *         workflow status, optionally preferring references with approved
     *         workflow status
     */
    @Override
    public Reference getLatestReference(
        long resourcePrimKey, int status, boolean preferApproved)
        throws PortalException {

        List<Reference> references = null;

        OrderByComparator<Reference> orderByComparator =
            new ReferenceVersionComparator();

        if (status == WorkflowConstants.STATUS_ANY) {

            if (preferApproved) {

                references = referencePersistence.findByR_S(
                    resourcePrimKey, WorkflowConstants.STATUS_APPROVED, 0, 1,
                    orderByComparator);
            }

            if (ListUtil.isEmpty(references)) {

                references = referencePersistence.findByResourcePrimKey(
                    resourcePrimKey, 0, 1, orderByComparator);
            }
        } else {

            references = referencePersistence.findByR_S(
                resourcePrimKey, status, 0, 1, orderByComparator);
        }

        if (references == null || references.isEmpty()) {

            throw new NoSuchReferenceException(
                "No Reference exists with the key {resourcePrimKey=" +
                    resourcePrimKey + "}");
        }

        return references.get(0);
    }

    /**
     * Returns the latest reference with the group and reference ID.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
     * @return the latest matching reference
     */
    @Override
    public Reference getLatestReference(long groupId, String referenceId)
        throws PortalException {

        return getLatestReference(
            groupId, referenceId, WorkflowConstants.STATUS_ANY);
    }

    /**
     * Returns the latest reference matching the group, reference ID, and
     * workflow status.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
     * @param status
     *            the reference's workflow status. For more information see
     *            {@link WorkflowConstants} for constants starting with the
     *            "STATUS_" prefix.
     * @return the latest matching reference
     */
    @Override
    public Reference getLatestReference(
        long groupId, String referenceId, int status)
        throws PortalException {

        return getFirstReference(
            groupId, referenceId, status, new ReferenceVersionComparator());
    }

    /**
     * Returns the latest reference matching the group, URL title, and workflow
     * status.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param urlTitle
     *            the reference's accessible URL title
     * @param status
     *            the reference's workflow status. For more information see
     *            {@link WorkflowConstants} for constants starting with the
     *            "STATUS_" prefix.
     * @return the latest matching reference
     */
    @Override
    public Reference getLatestReferenceByUrlTitle(
        long groupId, String urlTitle, int status)
        throws PortalException {

        Reference reference =
            fetchLatestReferenceByUrlTitle(groupId, urlTitle, status);

        if (reference == null) {

            throw new NoSuchReferenceException(
                "No Reference exists with the key {groupId=" + groupId +
                    ", urlTitle=" + urlTitle + ", status=" + status + "}");
        }

        return reference;
    }

    /**
     * Returns the latest version number of the reference with the group and
     * reference ID.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
     * @return the latest version number of the matching reference
     */
    @Override
    public double getLatestVersion(long groupId, String referenceId)
        throws PortalException {

        Reference reference = getLatestReference(groupId, referenceId);

        return reference.getVersion();
    }

    /**
     * Returns the latest version number of the reference with the group,
     * reference ID, and workflow status.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
     * @param status
     *            the reference's workflow status. For more information see
     *            {@link WorkflowConstants} for constants starting with the
     *            "STATUS_" prefix.
     * @return the latest version number of the matching reference
     */
    @Override
    public double getLatestVersion(long groupId, String referenceId, int status)
        throws PortalException {

        Reference reference = getLatestReference(groupId, referenceId, status);

        return reference.getVersion();
    }

    /**
     * Returns the oldest reference with the group and reference ID.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
     * @return the oldest matching reference
     */
    @Override
    public Reference getOldestReference(long groupId, String referenceId)
        throws PortalException {

        return getOldestReference(
            groupId, referenceId, WorkflowConstants.STATUS_ANY);
    }

    /**
     * Returns the oldest reference matching the group, reference ID, and
     * workflow status.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
     * @param status
     *            the reference's workflow status. For more information see
     *            {@link WorkflowConstants} for constants starting with the
     *            "STATUS_" prefix.
     * @return the oldest matching reference
     */
    @Override
    public Reference getOldestReference(
        long groupId, String referenceId, int status)
        throws PortalException {

        return getFirstReference(
            groupId, referenceId, status,
            new ReferenceVersionComparator(false));
    }

    /**
     * Returns the previously approved version of the reference. For more
     * information on the approved workflow status, see
     * {@link WorkflowConstants#STATUS_APPROVED}.
     *
     * @param reference
     *            the reference
     * @return the previously approved version of the reference, or the current
     *         reference if there are no previously approved references
     */
    @Override
    public Reference getPreviousApprovedReference(Reference reference) {

        List<Reference> approvedReferences = referencePersistence.findByG_R_ST(
            reference.getGroupId(), reference.getReferenceId(),
            WorkflowConstants.STATUS_APPROVED, 0, 2);

        if (approvedReferences.isEmpty() || ((approvedReferences.size() == 1) &&
            (reference.getStatus() == WorkflowConstants.STATUS_APPROVED))) {

            return reference;
        }

        Reference previousApprovedReference = approvedReferences.get(0);

        if (reference.getStatus() == WorkflowConstants.STATUS_APPROVED) {
            previousApprovedReference = approvedReferences.get(1);
        }

        return previousApprovedReference;
    }

    /**
     * Returns the reference with the ID.
     *
     * @param id
     *            the primary key of the reference
     * @return the reference with the ID
     */
    @Override
    public Reference getReference(long id)
        throws PortalException {

        return referencePersistence.findByPrimaryKey(id);
    }

    /**
     * Returns the latest approved reference, or the latest unapproved reference
     * if none are approved. Both approved and unapproved references must match
     * the group and reference ID.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
     * @return the matching reference
     */
    @Override
    public Reference getReference(long groupId, String referenceId)
        throws PortalException {

        // Get the latest reference that is approved, if none are approved, get
        // the latest unapproved reference

        Reference reference = fetchLatestReference(
            groupId, referenceId, WorkflowConstants.STATUS_APPROVED);

        if (reference != null) {
            return reference;
        }

        return getLatestReference(
            groupId, referenceId, WorkflowConstants.STATUS_ANY);
    }

    /**
     * Returns the reference matching the group, reference ID, and version.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
     * @param version
     *            the reference's version
     * @return the matching reference
     */
    @Override
    public Reference getReference(
        long groupId, String referenceId, double version)
        throws PortalException {

        return referencePersistence.findByG_R_V(groupId, referenceId, version);
    }

    /**
     * Returns the latest reference that is approved, or the latest unapproved
     * reference if none are approved. Both approved and unapproved references
     * must match the group and URL title.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param urlTitle
     *            the reference's accessible URL title
     * @return the matching reference
     */
    @Override
    public Reference getReferenceByUrlTitle(long groupId, String urlTitle)
        throws PortalException {

        // Get the latest reference that is approved, if none are approved, get
        // the latest unapproved reference

        Reference reference = fetchLatestReferenceByUrlTitle(
            groupId, urlTitle, WorkflowConstants.STATUS_APPROVED);

        if (reference != null) {

            return reference;
        }

        return getLatestReferenceByUrlTitle(
            groupId, urlTitle, WorkflowConstants.STATUS_ANY);
    }

    /**
     * Returns all the references present in the system.
     *
     * @return the references present in the system
     */
    @Override
    public List<Reference> getReferences() {

        return referencePersistence.findAll();
    }

    /**
     * Returns all the references belonging to the group.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @return the references belonging to the group
     */
    @Override
    public List<Reference> getReferences(long groupId) {

        return referencePersistence.findByGroupId(groupId);
    }

    /**
     * Returns a range of all the references belonging to the group. <p> Useful
     * when paginating results. Returns a maximum of <code>end - start</code>
     * instances. <code>start</code> and <code>end</code> are not primary keys,
     * they are indexes in the result set. Thus, <code>0</code> refers to the
     * first result in the set. Setting both <code>start</code> and
     * <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result
     * set. </p>
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param start
     *            the lower bound of the range of references to return
     * @param end
     *            the upper bound of the range of references to return (not
     *            inclusive)
     * @return the range of matching references
     */
    @Override
    public List<Reference> getReferences(long groupId, int start, int end) {

        return referencePersistence.findByGroupId(groupId, start, end);
    }

    /**
     * Returns an ordered range of all the references belonging to the group.
     * <p> Useful when paginating results. Returns a maximum of <code>end -
     * start</code> instances. <code>start</code> and <code>end</code> are not
     * primary keys, they are indexes in the result set. Thus, <code>0</code>
     * refers to the first result in the set. Setting both <code>start</code>
     * and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
     * result set. </p>
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param start
     *            the lower bound of the range of references to return
     * @param end
     *            the upper bound of the range of references to return (not
     *            inclusive)
     * @param obc
     *            the comparator to order the references
     * @return the range of matching references ordered by the comparator
     */
    @Override
    public List<Reference> getReferences(
        long groupId, int start, int end, OrderByComparator<Reference> obc) {

        return referencePersistence.findByGroupId(groupId, start, end, obc);
    }

    /**
     * Returns all the references matching the group and reference ID.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
     * @return the matching references
     */
    @Override
    public List<Reference> getReferences(long groupId, String referenceId) {

        return referencePersistence.findByG_R(groupId, referenceId);
    }

    /**
     * Returns all the references matching the group and reference ID.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
     * @param start
     *            the lower bound of the range of references to return
     * @param end
     *            the upper bound of the range of references to return (not
     *            inclusive)
     * @param obc
     *            the comparator to order the references
     * @return the range of matching references ordered by the comparator
     */
    @Override
    public List<Reference> getReferences(
        long groupId, String referenceId, int start, int end,
        OrderByComparator<Reference> obc) {

        return referencePersistence.findByG_R(
            groupId, referenceId, start, end, obc);
    }

    /**
     * Returns all the references matching the resource primary key.
     *
     * @param resourcePrimKey
     *            the primary key of the resource instance
     * @return the references matching the resource primary key
     */
    @Override
    public List<Reference> getReferencesByResourcePrimKey(
        long resourcePrimKey) {

        return referencePersistence.findByResourcePrimKey(resourcePrimKey);
    }

    /**
     * Returns the number of references belonging to the group.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @return the number of references belonging to the group
     */
    @Override
    public int getReferencesCount(long groupId) {

        return referencePersistence.countByGroupId(groupId);
    }

    /**
     * Returns the number of references belonging to the group and reference ID.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
     * @return the number of references belonging to the group and reference ID.
     */
    @Override
    public int getReferencesCount(long groupId, String referenceId) {

        return referencePersistence.countByG_R(groupId, referenceId);
    }
    
    /**
     * Returns the reference's unique URL title.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param name
     *            the name of the reference
     * @return the reference's unique URL title
     */
    @Override
    public String getUniqueUrlTitle(long groupId, String name)
        throws PortalException {

        return getUniqueUrlTitle(0L, groupId, StringPool.BLANK, name);
    }

    /**
     * Returns the reference's unique URL title.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
     * @param urlTitle
     *            the reference's accessible URL title
     * @return the reference's unique URL title
     */
    @Override
    public String getUniqueUrlTitle(
        long groupId, String referenceId, String urlTitle)
        throws PortalException {

        String newUrlTitle = urlTitle;

        Reference reference = fetchReferenceByUrlTitle(groupId, newUrlTitle);

        int index = 1;

        while (reference != null &&
            !reference.getReferenceId().equals(referenceId)) {

            String suffix = StringPool.DASH + index;

            String prefix = newUrlTitle;

            if (newUrlTitle.length() > suffix.length()) {

                prefix = newUrlTitle.substring(
                    0, newUrlTitle.length() - suffix.length());
            }

            newUrlTitle = prefix + suffix;

            reference = fetchReferenceByUrlTitle(groupId, newUrlTitle);

            index++;
        }

        return newUrlTitle;
    }

    /**
     * Returns <code>true</code> if the specified reference exists.
     *
     * @param groupId
     *            the primary key of the group
     * @param referenceId
     *            the primary key of the reference
     * @return <code>true</code> if the specified reference exists;
     *         <code>false</code> otherwise
     */
    @Override
    public boolean hasReference(long groupId, String referenceId) {

        Reference reference = fetchReference(groupId, referenceId);

        return reference != null;
    }

    /**
     * Returns <code>true</code> if the reference, specified by group and
     * reference ID, is the latest version.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
     * @param version
     *            the reference's version
     * @return <code>true</code> if the specified reference is the latest
     *         version; <code>false</code> otherwise
     */
    @Override
    public boolean isLatestVersion(
        long groupId, String referenceId, double version)
        throws PortalException {

        return ReferenceUtil.isDoublesEquals(
            getLatestVersion(groupId, referenceId), version);
    }

    /**
     * Returns <code>true</code> if the reference, specified by group, reference
     * ID, and workflow status, is the latest version.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
     * @param version
     *            the reference's version
     * @param status
     *            the reference's workflow status. For more information see
     *            {@link WorkflowConstants} for constants starting with the
     *            "STATUS_" prefix.
     * @return <code>true</code> if the specified reference is the latest
     *         version; <code>false</code> otherwise
     */
    @Override
    public boolean isLatestVersion(
        long groupId, String referenceId, double version, int status)
        throws PortalException {

        if (ReferenceUtil.isDoublesEquals(
            getLatestVersion(groupId, referenceId, status), version)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isListable(Reference reference) {

        return (reference != null)/* && reference.isIndexable() */;
    }

    /**
     * Moves the reference from the Recycle Bin.
     *
     * @param userId
     *            the primary key of the user updating the reference
     * @param groupId
     *            the primary key of the reference's group
     * @param reference
     *            the reference
     * @param serviceContext
     *            the service context to be applied. Can set the modification
     *            date, portlet preferences, and can set whether to add the
     *            default command update for the reference. With respect to
     *            social activities, by setting the service context's command to
     *            {@link Constants#UPDATE}, the invocation is considered a
     *            reference activity; otherwise it is considered a reference add
     *            activity.
     * @return the updated reference, which was moved from the Recycle Bin
     */
    @Indexable(type = IndexableType.REINDEX)
    @Override
    public Reference moveReferenceFromTrash(
        long userId, long groupId, Reference reference,
        ServiceContext serviceContext)
        throws PortalException {

        if (!reference.isInTrash()) {
            throw new RestoreEntryException(
                RestoreEntryException.INVALID_STATUS);
        }

        if (reference.isInTrashExplicitly()) {

            restoreReferenceFromTrash(userId, reference);
        } else {

            // Reference

            TrashVersion trashVersion = trashVersionLocalService.fetchVersion(
                Reference.class.getName(), reference.getResourcePrimKey());

            int status = WorkflowConstants.STATUS_APPROVED;

            if (trashVersion != null) {

                status = trashVersion.getStatus();
            }

            updateStatus(
                userId, reference, status, new HashMap<String, Serializable>(),
                serviceContext);

            // Trash

            if (trashVersion != null) {

                trashVersionLocalService.deleteTrashVersion(trashVersion);
            }
        }

        return getReference(groupId, reference.getReferenceId());
    }

    /**
     * Moves the latest version of the reference matching the group and
     * reference ID to the recycle bin.
     *
     * @param userId
     *            the primary key of the user updating the reference
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
     * @return the moved reference or <code>null</code> if no matching reference
     *         was found
     */
    @Override
    public Reference moveReferenceToTrash(
        long userId, long groupId, String referenceId)
        throws PortalException {

        List<Reference> references = referencePersistence.findByG_R(
            groupId, referenceId, 0, 1, new ReferenceVersionComparator());

        if (!references.isEmpty()) {

            return referenceLocalService.moveReferenceToTrash(
                userId, references.get(0));
        }

        return null;
    }

    /**
     * Moves the latest version of the reference matching the group and
     * reference ID to the recycle bin.
     *
     * @param userId
     *            the primary key of the user updating the reference
     * @param reference
     *            the reference
     * @return the updated reference, which was moved to the Recycle Bin
     */
    @Indexable(type = IndexableType.REINDEX)
    @Override
    public Reference moveReferenceToTrash(long userId, Reference reference)
        throws PortalException {

        // Reference

        if (reference.isInTrash()) {
            throw new TrashEntryException();
        }

        int oldStatus = reference.getStatus();

        if (oldStatus == WorkflowConstants.STATUS_PENDING) {

            reference.setStatus(WorkflowConstants.STATUS_DRAFT);
        }

        referencePersistence.update(reference);

        List<Reference> referenceVersions = referencePersistence.findByG_R(
            reference.getGroupId(), reference.getReferenceId());

        referenceVersions =
            ListUtil.sort(referenceVersions, new ReferenceVersionComparator());

        List<ObjectValuePair<Long, Integer>> referenceVersionStatusOVPs =
            new ArrayList<>();

        if ((referenceVersions != null) && !referenceVersions.isEmpty()) {

            referenceVersionStatusOVPs =
                getReferenceVersionStatuses(referenceVersions);
        }

        reference = updateStatus(
            userId, reference.getId(), WorkflowConstants.STATUS_IN_TRASH,
            new HashMap<String, Serializable>(), new ServiceContext());

        // Trash

        ReferenceResource referenceResource =
            referenceResourceLocalService.getReferenceResource(
                reference.getResourcePrimKey());

        UnicodeProperties typeSettingsProperties = new UnicodeProperties();

        typeSettingsProperties.put("title", reference.getReferenceId());

        TrashEntry trashEntry = trashEntryLocalService.addTrashEntry(
            userId, reference.getGroupId(), Reference.class.getName(),
            reference.getResourcePrimKey(), referenceResource.getUuid(), null,
            oldStatus, referenceVersionStatusOVPs, typeSettingsProperties);

        String trashReferenceId =
            trashHelper.getTrashTitle(trashEntry.getEntryId());

        for (Reference referenceVersion : referenceVersions) {

            referenceVersion.setReferenceId(trashReferenceId);
            referenceVersion.setStatus(WorkflowConstants.STATUS_IN_TRASH);

            referencePersistence.update(referenceVersion);
        }

        referenceResource.setReferenceId(trashReferenceId);

        referenceResourcePersistence.update(referenceResource);

        reference.setReferenceId(trashReferenceId);

        reference = referencePersistence.update(reference);

        // Asset

        assetEntryLocalService.updateVisible(
            Reference.class.getName(), reference.getResourcePrimKey(), false);

        // Workflow

        if (oldStatus == WorkflowConstants.STATUS_PENDING) {

            workflowInstanceLinkLocalService.deleteWorkflowInstanceLink(
                reference.getCompanyId(), reference.getGroupId(),
                Reference.class.getName(), reference.getId());
        }

        return reference;
    }

    /**
     * Restores the reference from the Recycle Bin.
     *
     * @param userId
     *            the primary key of the user restoring the reference
     * @param reference
     *            the reference
     * @return the restored reference from the Recycle Bin
     */
    @Indexable(type = IndexableType.REINDEX)
    @Override
    public Reference restoreReferenceFromTrash(long userId, Reference reference)
        throws PortalException {

        // Reference

        if (!reference.isInTrash()) {

            throw new RestoreEntryException(
                RestoreEntryException.INVALID_STATUS);
        }

        String trashReferenceId =
            trashHelper.getOriginalTitle(reference.getReferenceId());

        List<Reference> referenceVersions = referencePersistence.findByG_R(
            reference.getGroupId(), reference.getReferenceId());

        for (Reference referenceVersion : referenceVersions) {

            referenceVersion.setReferenceId(trashReferenceId);

            referencePersistence.update(referenceVersion);
        }

        reference.setReferenceId(trashReferenceId);

        referencePersistence.update(reference);

        ReferenceResource referenceResource =
            referenceResourcePersistence.fetchByPrimaryKey(
                reference.getResourcePrimKey());

        referenceResource.setReferenceId(trashReferenceId);

        referenceResourcePersistence.update(referenceResource);

        TrashEntry trashEntry = trashEntryLocalService.getEntry(
            Reference.class.getName(), reference.getResourcePrimKey());

        ServiceContext serviceContext = new ServiceContext();

        serviceContext.setScopeGroupId(reference.getGroupId());

        updateStatus(
            userId, reference, trashEntry.getStatus(),
            new HashMap<String, Serializable>(), serviceContext);

        // Trash

        List<TrashVersion> trashVersions =
            trashVersionLocalService.getVersions(trashEntry.getEntryId());

        boolean visible = false;

        for (TrashVersion trashVersion : trashVersions) {

            Reference trashReferenceVersion =
                referencePersistence.findByPrimaryKey(
                    trashVersion.getClassPK());

            trashReferenceVersion.setStatus(trashVersion.getStatus());

            if (trashVersion.getStatus() == WorkflowConstants.STATUS_APPROVED) {

                visible = true;
            }

            referencePersistence.update(trashReferenceVersion);
        }

        trashEntryLocalService.deleteEntry(
            Reference.class.getName(), reference.getResourcePrimKey());

        if (visible) {

            assetEntryLocalService.updateVisible(
                Reference.class.getName(), reference.getResourcePrimKey(),
                true);
        }

        return reference;
    }

    // TODO: search and searchCount methods

    /**
     * Returns an ordered range of all the references matching the parameters
     * without using the indexer, including a keywords parameter for matching
     * with the reference's ID, name, short description, and description. It is
     * preferable to use the indexed version
     * {@link #search(long, long, String, LinkedHashMap, int, int, Sort)}
     * instead of this method wherever possible for performance reasons. <p>
     * Useful when paginating results. Returns a maximum of <code>end -
     * start</code> instances. <code>start</code> and <code>end</code> are not
     * primary keys, they are indexes in the result set. Thus, <code>0</code>
     * refers to the first result in the set. Setting both <code>start</code>
     * and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
     * result set. </p>
     *
     * @param companyId
     *            the primary key of the reference's company
     * @param groupId
     *            the primary key of the group (optionally <code>0</code>)
     * @param keywords
     *            the keywords (space separated), which may occur in the
     *            reference ID, name, short description, or description
     *            (optionally <code>null</code>). If the keywords value is not
     *            <code>null</code>, the search uses the OR operator in
     *            connecting query criteria; otherwise it uses the AND operator.
     * @param version
     *            the reference's version (optionally <code>null</code>)
     * @param realizationDateGT
     *            the date after which a matching reference's realization date
     *            must be after (optionally <code>null</code>)
     * @param realizationDateLT
     *            the date before which a matching reference's realization date
     *            must be before (optionally <code>null</code>)
     * @param status
     *            the reference's workflow status. For more information see
     *            {@link WorkflowConstants} for constants starting with the
     *            "STATUS_" prefix.
     * @param start
     *            the lower bound of the range of references to return
     * @param end
     *            the upper bound of the range of references to return (not
     *            inclusive)
     * @param obc
     *            the comparator to order the references
     * @return the range of matching references ordered by the comparator
     */
    @Override
    public List<Reference> search(
        long companyId, long groupId, String keywords, Double version,
        Date realizationDateGT, Date realizationDateLT, int status, int start,
        int end, OrderByComparator<Reference> obc) {

        return referenceFinder.findByKeywords(
            companyId, groupId, keywords, version, realizationDateGT,
            realizationDateLT, status, start, end, obc);
    }

    /**
     * Returns an ordered range of all the references matching the parameters
     * without using the indexer, including keyword parameters for reference ID,
     * name, short description, and description, and an AND operator switch. It
     * is preferable to use the indexed version
     * {@link #search(long, long, String, String, String, String, int, LinkedHashMap, boolean, int, int, Sort)}
     * instead of this method wherever possible for performance reasons. <p>
     * Useful when paginating results. Returns a maximum of <code>end -
     * start</code> instances. <code>start</code> and <code>end</code> are not
     * primary keys, they are indexes in the result set. Thus, <code>0</code>
     * refers to the first result in the set. Setting both <code>start</code>
     * and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
     * result set. </p>
     *
     * @param companyId
     *            the primary key of the reference's company
     * @param groupId
     *            the primary key of the group (optionally <code>0</code>)
     * @param referenceId
     *            the reference ID keywords (space separated, optionally
     *            <code>null</code>)
     * @param version
     *            the reference's version (optionally <code>null</code>)
     * @param name
     *            the name keywords (space separated, optionally
     *            <code>null</code>)
     * @param shortDescription
     *            the short description keywords (space separated, optionally
     *            <code>null</code>)
     * @param description
     *            the description keywords (space separated, optionally
     *            <code>null</code>)
     * @param realizationDateGT
     *            the date after which a matching reference's realization date
     *            must be after (optionally <code>null</code>)
     * @param realizationDateLT
     *            the date before which a matching reference's realization date
     *            must be before (optionally <code>null</code>)
     * @param status
     *            the reference's workflow status. For more information see
     *            {@link WorkflowConstants} for constants starting with the
     *            "STATUS_" prefix.
     * @param andOperator
     *            whether every field must match its value or keywords, or just
     *            one field must match. Company, group, and status must all
     *            match their values.
     * @param start
     *            the lower bound of the range of references to return
     * @param end
     *            the upper bound of the range of references to return (not
     *            inclusive)
     * @param obc
     *            the comparator to order the references
     * @return the range of matching references ordered by the comparator
     */
    @Override
    public List<Reference> search(
        long companyId, long groupId, String referenceId, Double version,
        String name, String shortDescription, String description,
        Date realizationDateGT, Date realizationDateLT, int status,
        boolean andOperator, int start, int end,
        OrderByComparator<Reference> obc) {

        return referenceFinder.findByC_G_R_V_N_S_D_R_S(
            companyId, groupId, referenceId, version, name, shortDescription,
            description, realizationDateGT, realizationDateLT, status,
            andOperator, start, end, obc);
    }

    /**
     * Returns an ordered range of all the references matching the parameters
     * using the indexer, including a keywords parameter for matching an
     * reference's ID, name, short description, or description, and a finder
     * hash map parameter. It is preferable to use this method instead of the
     * non-indexed version whenever possible for performance reasons. <p> Useful
     * when paginating results. Returns a maximum of <code>end - start</code>
     * instances. <code>start</code> and <code>end</code> are not primary keys,
     * they are indexes in the result set. Thus, <code>0</code> refers to the
     * first result in the set. Setting both <code>start</code> and
     * <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result
     * set. </p>
     *
     * @param companyId
     *            the primary key of the reference's company
     * @param groupId
     *            the primary key of the group (optionally <code>0</code>)
     * @param keywords
     *            the keywords (space separated), which may occur in the
     *            reference ID, name, short description, or description
     *            (optionally <code>null</code>). If the keywords value is not
     *            <code>null</code>, the search uses the OR operator in
     *            connecting query criteria; otherwise it uses the AND operator.
     * @param params
     *            the finder parameters (optionally <code>null</code>)
     * @param start
     *            the lower bound of the range of references to return
     * @param end
     *            the upper bound of the range of references to return (not
     *            inclusive)
     * @param sort
     *            the field, type, and direction by which to sort (optionally
     *            <code>null</code>)
     * @return the matching references ordered by <code>sort</code>
     */
    @Override
    public Hits search(
        long companyId, long groupId, String keywords,
        LinkedHashMap<String, Object> params, int start, int end, Sort sort) {

        String referenceId = null;
        String name = null;
        String shortDescription = null;
        String description = null;
        boolean andOperator = false;

        if (Validator.isNotNull(keywords)) {
            referenceId = keywords;
            name = keywords;
            description = keywords;
            shortDescription = keywords;
        } else {
            andOperator = true;
        }

        params.put("keywords", keywords);

        return search(
            companyId, groupId, referenceId, name, shortDescription,
            description, WorkflowConstants.STATUS_ANY, params, andOperator,
            start, end, sort);
    }

    /**
     * Returns an ordered range of all the references matching the parameters
     * using the indexer, including a keywords parameter for matching an
     * reference's ID, name, short description, or description, an AND operator
     * switch, and parameters for status, a finder hash map. It is preferable to
     * use this method instead of the non-indexed version whenever possible for
     * performance reasons. <p> Useful when paginating results. Returns a
     * maximum of <code>end - start</code> instances. <code>start</code> and
     * <code>end</code> are not primary keys, they are indexes in the result
     * set. Thus, <code>0</code> refers to the first result in the set. Setting
     * both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS}
     * will return the full result set. </p>
     *
     * @param companyId
     *            the primary key of the reference's company
     * @param groupId
     *            the primary key of the group (optionally <code>0</code>)
     * @param referenceId
     *            the reference ID keywords (space separated, optionally
     *            <code>null</code>)
     * @param name
     *            the name keywords (space separated, optionally
     *            <code>null</code>)
     * @param shortDescription
     *            the short description keywords (space separated, optionally
     *            <code>null</code>)
     * @param description
     *            the description keywords (space separated, optionally
     *            <code>null</code>)
     * @param status
     *            the reference's workflow status. For more information see
     *            {@link WorkflowConstants} for constants starting with the
     *            "STATUS_" prefix.
     * @param params
     *            the finder parameters (optionally <code>null</code>). The
     *            <code>includeDiscussions</code> parameter can be set to
     *            <code>true</code> to search for the keywords in the reference
     *            discussions.
     * @param andSearch
     *            whether every field must match its value or keywords, or just
     *            one field must match
     * @param start
     *            the lower bound of the range of references to return
     * @param end
     *            the upper bound of the range of references to return (not
     *            inclusive)
     * @param sort
     *            the field, type, and direction by which to sort (optionally
     *            <code>null</code>)
     * @return the matching references ordered by <code>sort</code>
     */
    @Override
    public Hits search(
        long companyId, long groupId, String referenceId, String name,
        String shortDescription, String description, int status,
        LinkedHashMap<String, Object> params, boolean andSearch, int start,
        int end, Sort sort) {

        try {
            Indexer<Reference> indexer =
                IndexerRegistryUtil.nullSafeGetIndexer(Reference.class);

            SearchContext searchContext = buildSearchContext(
                companyId, groupId, referenceId, name, shortDescription,
                description, status, params, andSearch, start, end, sort);

            return indexer.search(searchContext);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * Returns the number of references matching the parameters, including a
     * keywords parameter for matching with the reference's ID, name, short
     * description, and description.
     *
     * @param companyId
     *            the primary key of the reference's company
     * @param groupId
     *            the primary key of the group (optionally <code>0</code>)
     * @param keywords
     *            the keywords (space separated), which may occur in the
     *            reference ID, name, short description, or description
     *            (optionally <code>null</code>). If the keywords value is not
     *            <code>null</code>, the search uses the OR operator in
     *            connecting query criteria; otherwise it uses the AND operator.
     * @param version
     *            the reference's version (optionally <code>null</code>)
     * @param realizationDateGT
     *            the date after which a matching reference's realization date
     *            must be after (optionally <code>null</code>)
     * @param realizationDateLT
     *            the date before which a matching reference's realization date
     *            must be before (optionally <code>null</code>)
     * @param status
     *            the reference's workflow status. For more information see
     *            {@link WorkflowConstants} for constants starting with the
     *            "STATUS_" prefix.
     * @return the number of matching references
     */
    @Override
    public int searchCount(
        long companyId, long groupId, String keywords, Double version,
        Date realizationDateGT, Date realizationDateLT, int status) {

        return referenceFinder.countByKeywords(
            companyId, groupId, keywords, version, realizationDateGT,
            realizationDateLT, status);
    }

    /**
     * Returns the number of references matching the parameters, including
     * keyword parameters for reference ID, name, short description, and
     * description, and an AND operator switch.
     *
     * @param companyId
     *            the primary key of the reference's company
     * @param groupId
     *            the primary key of the group (optionally <code>0</code>)
     * @param referenceId
     *            the reference ID keywords (space separated, optionally
     *            <code>null</code>)
     * @param version
     *            the reference's version (optionally <code>null</code>)
     * @param name
     *            the name keywords (space separated, optionally
     *            <code>null</code>)
     * @param shortDescription
     *            the short description keywords (space separated, optionally
     *            <code>null</code>)
     * @param description
     *            the description keywords (space separated, optionally
     *            <code>null</code>)
     * @param realizationDateGT
     *            the date after which a matching reference's realization date
     *            must be after (optionally <code>null</code>)
     * @param realizationDateLT
     *            the date before which a matching reference's realization date
     *            must be before (optionally <code>null</code>)
     * @param status
     *            the reference's workflow status. For more information see
     *            {@link WorkflowConstants} for constants starting with the
     *            "STATUS_" prefix.
     * @param andOperator
     *            whether every field must match its value or keywords, or just
     *            one field must match. Company, group, and status must all
     *            match their values.
     * @return the number of matching references
     */
    @Override
    public int searchCount(
        long companyId, long groupId, String referenceId, Double version,
        String name, String shortDescription, String description,
        Date relizationDateGT, Date realizationDateLT, int status,
        boolean andOperator) {

        return referenceFinder.countByC_G_R_V_N_S_D_R_S(
            companyId, groupId, referenceId, version, name, shortDescription,
            description, relizationDateGT, realizationDateLT, status,
            andOperator);
    }

    /**
     * Returns a {@link BaseModelSearchResult} containing the total number of
     * hits and an ordered range of all the references matching the parameters
     * using the indexer, including a keywords parameter for matching a
     * reference's ID, name, short description, or description, and a finder
     * hash map parameter. It is preferable to use this method instead of the
     * non-indexed version whenever possible for performance reasons. <p> The
     * <code>start</code> and <code>end</code> parameters only affect the amount
     * of references returned as results, not the total number of hits. </p> <p>
     * Useful when paginating results. Returns a maximum of <code>end -
     * start</code> instances. <code>start</code> and <code>end</code> are not
     * primary keys, they are indexes in the result set. Thus, <code>0</code>
     * refers to the first result in the set. Setting both <code>start</code>
     * and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
     * result set. </p>
     *
     * @param companyId
     *            the primary key of the reference's company
     * @param groupId
     *            the primary key of the group (optionally <code>0</code>)
     * @param keywords
     *            the keywords (space separated), which may occur in the
     *            reference ID, name, short description, or description
     *            (optionally <code>null</code>). If the keywords value is not
     *            <code>null</code>, the search uses the OR operator in
     *            connecting query criteria; otherwise it uses the AND operator.
     * @param params
     *            the finder parameters (optionally <code>null</code>)
     * @param start
     *            the lower bound of the range of references to return
     * @param end
     *            the upper bound of the range of references to return (not
     *            inclusive)
     * @param sort
     *            the field, type, and direction by which to sort (optionally
     *            <code>null</code>)
     * @return a {@link BaseModelSearchResult} containing the total number of
     *         hits and an ordered range of all the matching references ordered
     *         by <code>sort</code>
     */
    @Override
    public BaseModelSearchResult<Reference> searchReferences(
        long companyId, long groupId, String keywords,
        LinkedHashMap<String, Object> params, int start, int end, Sort sort)
        throws PortalException {

        String referenceId = null;
        String name = null;
        String shortDescription = null;
        String description = null;
        boolean andOperator = false;

        if (Validator.isNotNull(keywords)) {
            referenceId = keywords;
            name = keywords;
            shortDescription = keywords;
            description = keywords;
        } else {
            andOperator = true;
        }

        if (params != null) {
            params.put("keywords", keywords);
        }

        return searchReferences(
            companyId, groupId, referenceId, name, shortDescription,
            description, WorkflowConstants.STATUS_ANY, params, andOperator,
            start, end, sort);
    }

    /**
     * Returns a {@link BaseModelSearchResult} containing the total number of
     * hits and an ordered range of all the references matching the parameters
     * using the indexer, including keyword parameters for reference ID, name,
     * short description, or description, an AND operator switch, and parameters
     * for type, status, and a finder hash map. It is preferable to use this
     * method instead of the non-indexed version whenever possible for
     * performance reasons. <p> The <code>start</code> and <code>end</code>
     * parameters only affect the amount of references returned as results, not
     * the total number of hits. </p> <p> Useful when paginating results.
     * Returns a maximum of <code>end - start</code> instances.
     * <code>start</code> and <code>end</code> are not primary keys, they are
     * indexes in the result set. Thus, <code>0</code> refers to the first
     * result in the set. Setting both <code>start</code> and <code>end</code>
     * to {@link QueryUtil#ALL_POS} will return the full result set. </p>
     *
     * @param companyId
     *            the primary key of the reference's company
     * @param groupId
     *            the primary key of the group (optionally <code>0</code>)
     * @param referenceId
     *            the reference ID keywords (space separated, optionally
     *            <code>null</code>)
     * @param name
     *            the name keywords (space separated, optionally
     *            <code>null</code>)
     * @param shortDescription
     *            the short description keywords (space separated, optionally
     *            <code>null</code>)
     * @param description
     *            the content keywords (space separated, optionally
     *            <code>null</code>)
     * @param status
     *            the reference's workflow status. For more information see
     *            {@link WorkflowConstants} for constants starting with the
     *            "STATUS_" prefix.
     * @param params
     *            the finder parameters (optionally <code>null</code>).
     * @param andSearch
     *            whether every field must match its value or keywords, or just
     *            one field must match
     * @param start
     *            the lower bound of the range of references to return
     * @param end
     *            the upper bound of the range of references to return (not
     *            inclusive)
     * @param sort
     *            the field, type, and direction by which to sort (optionally
     *            <code>null</code>)
     * @return a {@link BaseModelSearchResult} containing the total number of
     *         hits and an ordered range of all the matching references ordered
     *         by <code>sort</code>
     */
    @Override
    public BaseModelSearchResult<Reference> searchReferences(
        long companyId, long groupId, String referenceId, String name,
        String shortDdescription, String description, int status,
        LinkedHashMap<String, Object> params, boolean andSearch, int start,
        int end, Sort sort)
        throws PortalException {

        SearchContext searchContext = buildSearchContext(
            companyId, groupId, referenceId, name, shortDdescription,
            description, status, params, andSearch, start, end, sort);

        return searchReferences(searchContext);
    }

    /**
     * Updates the reference's asset with the new asset categories, tag names,
     * and link entries, removing and adding them as necessary.
     *
     * @param userId
     *            the primary key of the user updating the reference's asset
     * @param reference
     *            the reference
     * @param assetCategoryIds
     *            the primary keys of the new asset categories
     * @param assetTagNames
     *            the new asset tag names
     * @param assetLinkEntryIds
     *            the primary keys of the new asset link entries
     * @param priority
     *            the priority of the asset
     */
    @Override
    public void updateAsset(
        long userId, Reference reference, long[] assetCategoryIds,
        String[] assetTagNames, long[] assetLinkEntryIds, Double priority)
        throws PortalException {

        boolean visible = reference.isApproved();

        boolean addDraftAssetEntry = false;

        if (!reference.isApproved() &&
            (reference.getVersion() != ReferenceConstants.VERSION_DEFAULT)) {

            int approvedReferencesCount = referencePersistence.countByG_R_ST(
                reference.getGroupId(), reference.getReferenceId(),
                ReferenceConstants.getAssetEntryCreationStatuses());

            if (approvedReferencesCount > 0) {
                addDraftAssetEntry = true;
            }
        }

        AssetEntry assetEntry = null;

        if (addDraftAssetEntry) {

            assetEntry = assetEntryLocalService.updateEntry(
                userId, reference.getGroupId(), reference.getCreateDate(),
                reference.getModifiedDate(), Reference.class.getName(),
                reference.getPrimaryKey(), reference.getUuid(), 0,
                assetCategoryIds, assetTagNames, isListable(reference), false,
                null, null, null, null, ContentTypes.TEXT_HTML,
                reference.getName(), reference.getDescription(),
                reference.getShortDescription(), null, StringPool.BLANK, 0, 0,
                priority);
        } else {

            ReferenceResource referenceResource =
                referenceResourceLocalService.getReferenceResource(
                    reference.getResourcePrimKey());

            assetEntry = assetEntryLocalService.updateEntry(
                userId, reference.getGroupId(), reference.getCreateDate(),
                reference.getModifiedDate(), Reference.class.getName(),
                referenceResource.getResourcePrimKey(),
                referenceResource.getUuid(), 0, assetCategoryIds, assetTagNames,
                isListable(reference), visible, null, null, null, null,
                ContentTypes.TEXT_HTML, reference.getName(),
                reference.getDescription(), reference.getShortDescription(),
                null, StringPool.BLANK, 0, 0, priority);
        }

        assetLinkLocalService.updateLinks(
            userId, assetEntry.getEntryId(), assetLinkEntryIds,
            AssetLinkConstants.TYPE_RELATED);
    }

    /**
     * Updates the reference with additional parameters.
     *
     * @param userId
     *            the primary key of the user updating the reference
     * @param groupId
     *            the primary key of the reference's group
     * @param currentReferenceId
     *            the primary key of the reference
     * @param version
     *            the reference's version
     * @param name
     *            the reference's name
     * @param shortDescription
     *            the reference's short description
     * @param description
     *            the reference's description
     * @param realizationDateMonth
     *            the month the reference is set to realized
     * @param realizationDateDay
     *            the calendar day the reference is set to realized
     * @param realizationDateYear
     *            the year the reference is set to realized
     * @param emphasized
     *            whether the reference is emphasized
     * @param overlayText
     *            the reference's overlay text
     * @param overlayUrl
     *            the reference's overlay URL
     * @param urlTitle
     *            the reference's url title
     * @param smallImage
     *            the reference's small image
     * @param largeImage
     *            the reference's large image
     * @param otherImages
     *            the reference's other images (optionally <code>null</code>)
     * @param serviceContext
     *            the service context to be applied. Can set the modification
     *            date, expando bridge attributes, asset category IDs, asset tag
     *            names, asset link entry IDs, asset priority, workflow actions,
     *            URL title, and can set whether to add the default command
     *            update for the reference. With respect to social activities,
     *            by setting the service context's command to
     *            {@link Constants#UPDATE}, the invocation is considered a
     *            reference update activity; otherwise it is considered a
     *            reference add activity.
     * @return the updated reference
     */
    @Indexable(type = IndexableType.REINDEX)
    @Override
    public Reference updateReference(
        long userId, long groupId, String currentReferenceId, double version,
        String name, String shortDescription, String description,
        int realizationDateMonth, int realizationDateDay,
        int realizationDateYear, boolean emphasized, String overlayText,
        String overlayUrl, String urlTitle, String smallImage,
        String largeImage, String[] otherImages, ServiceContext serviceContext)
        throws PortalException {

        // Reference

        User user = userPersistence.findByPrimaryKey(userId);
        String referenceId = StringUtil.toUpperCase(currentReferenceId.trim());

        Reference latestReference = getLatestReference(
            groupId, referenceId, WorkflowConstants.STATUS_ANY);

        Reference reference = latestReference;

        boolean imported = ExportImportThreadLocal.isImportInProcess();

        double latestVersion = latestReference.getVersion();

        double newVersion = version;

        boolean addNewVersion = false;

        if (imported) {

            if (latestVersion > version) {

                Reference existingReference = referencePersistence.fetchByG_R_V(
                    groupId, referenceId, version);

                if (existingReference != null) {

                    reference = existingReference;
                } else {

                    addNewVersion = true;
                }
            } else if (latestVersion < version) {

                addNewVersion = true;
            }
        } else {

            if (version > 0 &&
                !ReferenceUtil.isDoublesEquals(version, latestVersion)) {

                StringBundler sb = new StringBundler(4);

                sb.append("Version ");
                sb.append(version);
                sb.append(" is not the same as ");
                sb.append(latestVersion);

                throw new ReferenceVersionException(sb.toString());
            }

            serviceContext.validateModifiedDate(
                latestReference, ReferenceVersionException.class);

            if (latestReference.isApproved() || latestReference.isExpired() ||
                latestReference.isScheduled()) {

                addNewVersion = true;

                newVersion = getNextVersion(reference);
            }
        }

        Date realizationDate = PortalUtil.getDate(
            realizationDateMonth, realizationDateDay, realizationDateYear, 0, 0,
            user.getTimeZone(), ReferenceRealizationDateException.class);

        Date now = new Date();

        validate(
            groupId, latestReference.getResourcePrimKey(), name, urlTitle,
            shortDescription, description);

        if (addNewVersion) {

            long id = counterLocalService.increment();

            reference = referencePersistence.create(id);

            reference.setResourcePrimKey(latestReference.getResourcePrimKey());
            reference.setGroupId(latestReference.getGroupId());
            reference.setCompanyId(latestReference.getCompanyId());
            reference.setUserId(user.getUserId());
            reference.setUserName(user.getFullName());
            reference.setCreateDate(latestReference.getCreateDate());
            reference.setReferenceId(referenceId);
            reference.setVersion(newVersion);
        }

        reference.setModifiedDate(serviceContext.getModifiedDate(now));
        reference.setName(name);
        // reference.setUrlTitle(getUniqueUrlTitle(
        // reference.getId(), reference.getReferenceId(), name,
        // latestReference.getUrlTitle(), serviceContext));
        reference.setUrlTitle(urlTitle);
        reference.setShortDescription(shortDescription);
        reference.setDescription(description);
        reference.setRealizationDate(realizationDate);
        reference.setEmphasized(emphasized);
        reference.setOverlayText(overlayText);
        reference.setOverlayUrl(overlayUrl);

        if (latestReference.isPending()) {

            reference.setStatus(latestReference.getStatus());
        } else {

            reference.setStatus(WorkflowConstants.STATUS_DRAFT);
        }

        ExpandoBridgeUtil.setExpandoBridgeAttributes(
            latestReference.getExpandoBridge(), reference.getExpandoBridge(),
            serviceContext);

        referencePersistence.update(reference);

        // Reference images

        if (addNewVersion) {

            addReferenceImages(
                groupId, referenceId, reference.getVersion(), smallImage,
                largeImage, otherImages);
        } else {

            referenceImageLocalService.deleteReferenceImages(
                groupId, referenceId, reference.getVersion());

            addReferenceImages(
                groupId, referenceId, reference.getVersion(), smallImage,
                largeImage, otherImages);
        }

        // Asset

        updateAsset(
            userId, reference, serviceContext.getAssetCategoryIds(),
            serviceContext.getAssetTagNames(),
            serviceContext.getAssetLinkEntryIds(),
            serviceContext.getAssetPriority());

        // Workflow

        if (serviceContext.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {

            startWorkflowInstance(userId, reference, serviceContext);
        }

        return referencePersistence.findByPrimaryKey(reference.getId());
    }

    /**
     * Updates the workflow status of the reference matching the class PK.
     *
     * @param userId
     *            the primary key of the user updating the reference's status
     * @param id
     *            the primary key of the reference
     * @param status
     *            the reference's workflow status. For more information see
     *            {@link WorkflowConstants} for constants starting with the
     *            "STATUS_" prefix.
     * @param workflowContext
     *            the reference's configured workflow
     * @param serviceContext
     *            the service context to be applied. Can set the modification
     *            date, portlet preferences, and can set whether to add the
     *            default command update for the reference.
     * @return the updated reference
     */
    @Override
    public Reference updateStatus(
        long userId, long id, int status,
        Map<String, Serializable> workflowContext,
        ServiceContext serviceContext)
        throws PortalException {

        Reference reference = getReference(id);

        return referenceLocalService.updateStatus(
            userId, reference, status, workflowContext, serviceContext);
    }

    /**
     * Updates the workflow status of the reference matching the group,
     * reference ID, and version.
     *
     * @param userId
     *            the primary key of the user updating the reference's status
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
     * @param version
     *            the reference's version
     * @param status
     *            the reference's workflow status. For more information see
     *            {@link WorkflowConstants} for constants starting with the
     *            "STATUS_" prefix.
     * @param workflowContext
     *            the reference's configured workflow
     * @param serviceContext
     *            the service context to be applied. Can set the modification
     *            date, portlet preferences, and can set whether to add the
     *            default command update for the reference.
     * @return the updated reference
     */
    @Override
    public Reference updateStatus(
        long userId, long groupId, String referenceId, double version,
        int status, Map<String, Serializable> workflowContext,
        ServiceContext serviceContext)
        throws PortalException {

        Reference reference =
            referencePersistence.findByG_R_V(groupId, referenceId, version);

        return referenceLocalService.updateStatus(
            userId, reference, status, workflowContext, serviceContext);
    }

    /**
     * Updates the workflow status of the reference.
     *
     * @param userId
     *            the primary key of the user updating the reference's status
     * @param reference
     *            the reference
     * @param status
     *            the reference's workflow status. For more information see
     *            {@link WorkflowConstants} for constants starting with the
     *            "STATUS_" prefix.
     * @param workflowContext
     *            the reference's configured workflow context
     * @param serviceContext
     *            the service context to be applied. Can set the modification
     *            date, status date, and portlet preferences. With respect to
     *            social activities, by setting the service context's command to
     *            {@link Constants#UPDATE}, the invocation is considered a
     *            reference activity; otherwise it is considered a reference add
     *            activity.
     * @return the updated reference
     */
    @Indexable(type = IndexableType.REINDEX)
    @Override
    public Reference updateStatus(
        long userId, Reference reference, int status,
        Map<String, Serializable> workflowContext,
        ServiceContext serviceContext)
        throws PortalException {

        // Reference

        User user = userPersistence.findByPrimaryKey(userId);
        Date now = new Date();

        int oldStatus = reference.getStatus();

        Date modifiedDate = serviceContext.getModifiedDate(now);

        reference.setModifiedDate(modifiedDate);

        reference.setStatus(status);
        reference.setStatusByUserId(user.getUserId());
        reference.setStatusByUserName(user.getFullName());
        reference.setStatusDate(modifiedDate);

        referencePersistence.update(reference);

        if (hasModifiedLatestApprovedVersion(
            reference.getGroupId(), reference.getReferenceId(),
            reference.getVersion())) {

            if (status == WorkflowConstants.STATUS_APPROVED) {

                updateUrlTitles(
                    reference.getGroupId(), reference.getReferenceId(),
                    reference.getUrlTitle());

                // Asset

                if ((oldStatus != WorkflowConstants.STATUS_APPROVED) &&
                    (reference.getVersion() != ReferenceConstants.VERSION_DEFAULT)) {

                    AssetEntry draftAssetEntry =
                        assetEntryLocalService.fetchEntry(
                            Reference.class.getName(),
                            reference.getPrimaryKey());

                    if (draftAssetEntry != null) {
                        long[] assetCategoryIds =
                            draftAssetEntry.getCategoryIds();
                        String[] assetTagNames = draftAssetEntry.getTagNames();

                        List<AssetLink> assetLinks =
                            assetLinkLocalService.getDirectLinks(
                                draftAssetEntry.getEntryId(),
                                AssetLinkConstants.TYPE_RELATED, false);

                        long[] assetLinkEntryIds = ListUtil.toLongArray(
                            assetLinks, AssetLink.ENTRY_ID2_ACCESSOR);

                        AssetEntry assetEntry =
                            assetEntryLocalService.updateEntry(
                                userId, reference.getGroupId(),
                                reference.getCreateDate(),
                                reference.getModifiedDate(),
                                Reference.class.getName(),
                                reference.getResourcePrimKey(),
                                reference.getUuid(), 0, assetCategoryIds,
                                assetTagNames, isListable(reference), false,
                                null, null, null, null, ContentTypes.TEXT_HTML,
                                reference.getName(), reference.getDescription(),
                                reference.getShortDescription(), null,
                                StringPool.BLANK, 0, 0,
                                draftAssetEntry.getPriority());

                        assetLinkLocalService.updateLinks(
                            userId, assetEntry.getEntryId(), assetLinkEntryIds,
                            AssetLinkConstants.TYPE_RELATED);

                        assetEntryLocalService.deleteEntry(draftAssetEntry);
                    }
                }

                assetEntryLocalService.updateEntry(
                    Reference.class.getName(), reference.getResourcePrimKey(),
                    null, null, isListable(reference), true);
            } else if (oldStatus == WorkflowConstants.STATUS_APPROVED) {
                updatePreviousApprovedReference(reference);
            }
        }

        return reference;
    }

    protected SearchContext buildSearchContext(
        long groupId, long userId, long creatorUserId, int status, int start,
        int end)
        throws PortalException {

        SearchContext searchContext = new SearchContext();

        searchContext.setAttribute(Field.STATUS, status);

        searchContext.setAttribute("paginationType", "none");

        if (creatorUserId > 0) {
            searchContext.setAttribute(
                Field.USER_ID, String.valueOf(creatorUserId));
        }

        Group group = groupLocalService.getGroup(groupId);

        searchContext.setCompanyId(group.getCompanyId());

        searchContext.setEnd(end);
        searchContext.setGroupIds(new long[] {
            groupId
        });
        searchContext.setSorts(new Sort(Field.MODIFIED_DATE, true));
        searchContext.setStart(start);
        searchContext.setUserId(userId);

        return searchContext;
    }

    protected SearchContext buildSearchContext(
        long companyId, long groupId, String referenceId, String name,
        String shortDescription, String description, int status,
        LinkedHashMap<String, Object> params, boolean andSearch, int start,
        int end, Sort sort) {

        SearchContext searchContext = new SearchContext();

        searchContext.setAndSearch(andSearch);

        Map<String, Serializable> attributes = new HashMap<>();

        attributes.put(Field.NAME, name);
        attributes.put(Field.DESCRIPTION, description);
        attributes.put(Field.STATUS, status);
        attributes.put("shortDescription", shortDescription);
        attributes.put("referenceId", referenceId);
        attributes.put("params", params);

        searchContext.setAttributes(attributes);

        searchContext.setCompanyId(companyId);
        searchContext.setEnd(end);
        searchContext.setGroupIds(new long[] {
            groupId
        });

        if (params != null) {

            String keywords = (String) params.remove("keywords");

            if (Validator.isNotNull(keywords)) {

                searchContext.setKeywords(keywords);
            }
        }

        QueryConfig queryConfig = new QueryConfig();

        queryConfig.setHighlightEnabled(false);
        queryConfig.setScoreEnabled(false);

        searchContext.setQueryConfig(queryConfig);

        if (sort != null) {
            searchContext.setSorts(sort);
        }

        searchContext.setStart(start);

        return searchContext;
    }

    protected Reference fetchLatestLiveReference(Reference reference)
        throws PortalException {

        Group group = groupLocalService.getGroup(reference.getGroupId());

        long liveGroupId = group.getLiveGroupId();

        if (liveGroupId == 0) {
            return null;
        }

        ReferenceResource referenceResource =
            referenceResourceLocalService.fetchReferenceResourceByUuidAndGroupId(
                reference.getReferenceResourceUuid(), liveGroupId);

        if (referenceResource == null) {

            return null;
        }

        return referenceLocalService.fetchLatestReference(
            referenceResource.getResourcePrimKey(),
            WorkflowConstants.STATUS_ANY, false);
    }

    protected Reference getFirstReference(
        long groupId, String referenceId, int status,
        OrderByComparator<Reference> orderByComparator)
        throws PortalException {

        if (status == WorkflowConstants.STATUS_ANY) {

            return referencePersistence.findByG_R_NotST_First(
                groupId, referenceId, WorkflowConstants.STATUS_IN_TRASH,
                orderByComparator);
        } else {

            return referencePersistence.findByG_R_ST_First(
                groupId, referenceId, status, orderByComparator);
        }
    }

    protected double getNextVersion(Reference reference)
        throws PortalException {

        double nextVersion = reference.getVersion();

        // The next version must be greater than the version of the latest live
        // reference

        Reference latestLiveReference = fetchLatestLiveReference(reference);

        if ((latestLiveReference != null) &&
            (latestLiveReference.getVersion() > nextVersion)) {

            nextVersion = latestLiveReference.getVersion();
        }

        return MathUtil.format(nextVersion + 0.1, 1, 1);
    }

    protected List<ObjectValuePair<Long, Integer>> getReferenceVersionStatuses(
        List<Reference> references) {

        List<ObjectValuePair<Long, Integer>> referenceVersionStatusOVPs =
            new ArrayList<>(references.size());

        for (Reference reference : references) {

            int status = reference.getStatus();

            if (status == WorkflowConstants.STATUS_PENDING) {

                status = WorkflowConstants.STATUS_DRAFT;
            }

            ObjectValuePair<Long, Integer> referenceVersionStatusOVP =
                new ObjectValuePair<>(reference.getId(), status);

            referenceVersionStatusOVPs.add(referenceVersionStatusOVP);
        }

        return referenceVersionStatusOVPs;
    }

    protected String getUniqueUrlTitle(
        long id, long groupId, String referenceId, String title)
        throws PortalException {

        String urlTitle = ReferenceUtil.getUrlTitle(id, title);

        return getUniqueUrlTitle(groupId, referenceId, urlTitle);
    }

    protected String getUniqueUrlTitle(
        long id, long groupId, String referenceId, String title,
        String oldUrlTitle, ServiceContext serviceContext)
        throws PortalException {

        String serviceContextUrlTitle =
            ParamUtil.getString(serviceContext, "urlTitle");

        String urlTitle = null;

        if (Validator.isNotNull(serviceContextUrlTitle)) {

            urlTitle = ReferenceUtil.getUrlTitle(id, serviceContextUrlTitle);
        } else if (Validator.isNotNull(oldUrlTitle)) {

            return oldUrlTitle;
        } else {

            urlTitle = getUniqueUrlTitle(id, groupId, referenceId, title);
        }

        Reference urlTitleReference =
            fetchReferenceByUrlTitle(groupId, urlTitle);

        if ((urlTitleReference != null) &&
            !Objects.equals(urlTitleReference.getReferenceId(), referenceId)) {

            urlTitle = getUniqueUrlTitle(id, groupId, referenceId, urlTitle);
        }

        return urlTitle;
    }

    protected boolean hasModifiedLatestApprovedVersion(
        long groupId, String referenceId, double version) {

        Reference reference = fetchLatestReference(
            groupId, referenceId, WorkflowConstants.STATUS_APPROVED);

        return (reference == null) || (reference.getVersion() <= version);
    }

    protected BaseModelSearchResult<Reference> searchReferences(
        SearchContext searchContext)
        throws PortalException {

        Indexer<Reference> indexer =
            IndexerRegistryUtil.nullSafeGetIndexer(Reference.class);

        for (int i = 0; i < 10; i++) {

            Hits hits = indexer.search(
                searchContext, ReferenceUtil.SELECTED_FIELD_NAMES);

            List<Reference> references = ReferenceUtil.getReferences(hits);

            if (references != null) {

                return new BaseModelSearchResult<>(
                    references, hits.getLength());
            }
        }

        throw new SearchException(
            "Unable to fix the search index after 10 attempts");
    }

    protected void startWorkflowInstance(
        long userId, Reference reference, ServiceContext serviceContext)
        throws PortalException {

        Map<String, Serializable> workflowContext = new HashMap<>();

        String portletId = PortletProviderUtil.getPortletId(
            Reference.class.getName(), PortletProvider.Action.EDIT);

        workflowContext.put(
            WorkflowConstants.CONTEXT_URL, PortalUtil.getControlPanelFullURL(
                reference.getGroupId(), portletId, null));

        WorkflowHandlerRegistryUtil.startWorkflowInstance(
            reference.getCompanyId(), reference.getGroupId(), userId,
            Reference.class.getName(), reference.getId(), reference,
            serviceContext, workflowContext);
    }

    protected void updatePreviousApprovedReference(Reference reference)
        throws PortalException {

        Reference previousApprovedReference =
            getPreviousApprovedReference(reference);

        if (ReferenceUtil.isDoublesEquals(
            previousApprovedReference.getVersion(), reference.getVersion())) {
            assetEntryLocalService.updateVisible(
                Reference.class.getName(), reference.getResourcePrimKey(),
                false);
        } else {
            AssetEntry assetEntry = assetEntryLocalService.updateEntry(
                Reference.class.getName(), reference.getResourcePrimKey(), null,
                null, true, true);

            assetEntry.setModifiedDate(
                previousApprovedReference.getModifiedDate());
            assetEntry.setTitle(previousApprovedReference.getName());

            assetEntryPersistence.update(assetEntry);
        }
    }

    protected void updateUrlTitles(
        long groupId, String referenceId, String urlTitle)
        throws PortalException {

        Reference firstReference = referencePersistence.findByG_R_First(
            groupId, referenceId, new ReferenceVersionComparator(false));

        if (firstReference.getUrlTitle().equals(urlTitle)) {
            return;
        }

        List<Reference> references =
            referencePersistence.findByG_R(groupId, referenceId);

        for (Reference reference : references) {

            if (!reference.getUrlTitle().equals(urlTitle)) {

                reference.setUrlTitle(urlTitle);

                referencePersistence.update(reference);
            }
        }
    }

    protected void validate(
        long groupId, long resourcePrimKey, String name, String urlTitle,
        String shortDescription, String description)
        throws PortalException {

        if (Validator.isNull(name)) {

            throw new ReferenceNameException();
        }

        validateUrlTitle(groupId, resourcePrimKey, urlTitle);

        validateShortDescription(shortDescription);

        validateDescription(description);
    }

    protected void validateContent(String content, PortalException e)
        throws PortalException {

        if (Validator.isNull(content)) {

            throw e;
        }
    }

    protected void validateDescription(String description)
        throws PortalException {

        validateContent(description, new ReferenceDescriptionException());
    }

    protected void validateShortDescription(String shortDescription)
        throws PortalException {

        validateContent(
            shortDescription, new ReferenceShortDescriptionException());
    }

    protected void validateUrlTitle(
        long groupId, long resourcePrimKey, String urlTitle)
        throws PortalException {

        if (Validator.isNull(urlTitle)) {

            throw new ReferenceUrlTitleException();
        }

        if (!ExportImportThreadLocal.isImportInProcess()) {

            Reference reference = null;

            try {

                reference = getReferenceByUrlTitle(groupId, urlTitle);
            } catch (NoSuchReferenceException nsre) {

                if (LOG.isDebugEnabled()) {

                    LOG.debug(nsre.getMessage(), nsre);
                }
            }

            if (reference != null && (Validator.isNull(resourcePrimKey) ||
                reference.getResourcePrimKey() != resourcePrimKey)) {

                throw new DuplicateUrlTitleException();
            }
        }
    }
}
