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
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import aQute.bnd.annotation.ProviderType;
import hu.webtown.liferay.portlet.reference.constants.ReferenceActionKeys;
import hu.webtown.liferay.portlet.reference.constants.ReferenceConstants;
import hu.webtown.liferay.portlet.reference.model.Reference;
import hu.webtown.liferay.portlet.reference.service.base.ReferenceServiceBaseImpl;

/**
 * The implementation of the reference remote service. <p> All custom service
 * methods should be put in this class. Whenever methods are added, rerun
 * ServiceBuilder to copy their definitions into the
 * {@link hu.webtown.liferay.portlet.reference.service.ReferenceService}
 * interface. <p> This is a remote service. Methods of this service are expected
 * to have security checks based on the propagated JAAS credentials because this
 * service can be accessed remotely. </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReferenceServiceBaseImpl
 * @see hu.webtown.liferay.portlet.reference.service.ReferenceServiceUtil
 */
@ProviderType
public class ReferenceServiceImpl extends ReferenceServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS: Never reference this class directly. Always use
     * {@link hu.webtown.liferay.portlet.reference.service.ReferenceServiceUtil}
     * to access the reference remote service.
     */

    private static volatile ModelResourcePermission<Reference> referenceModelResourcePermission =
        ModelResourcePermissionFactory.getInstance(
            ReferenceServiceImpl.class, "referenceModelResourcePermission",
            Reference.class);
    
    private static volatile PortletResourcePermission referencePortletResourcePermission =
        PortletResourcePermissionFactory.getInstance(
            ReferenceServiceImpl.class, "referencePortletResourcePermission",
            ReferenceConstants.RESOURCE_NAME);

    /**
     * Adds a reference with additional parameters.
     * 
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
     * @param autoReferenceId
     *            whether to auto generate the reference ID
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
    @Override
    public Reference addReference(
        long groupId, String referenceId, boolean autoReferenceId, String name,
        String shortDescription, String description, int realizationDateMonth,
        int realizationDateDay, int realizationDateYear, boolean emphasized,
        String overlayText, String overlayUrl, String urlTitle,
        String smallImage, String largeImage, String[] otherImages,
        ServiceContext serviceContext)
        throws PortalException {

        referencePortletResourcePermission.check(
            getPermissionChecker(), groupId, ReferenceActionKeys.ADD_REFERENCE);

        return referenceLocalService.addReference(
            getUserId(), groupId, referenceId, autoReferenceId,
            ReferenceConstants.VERSION_DEFAULT, name, shortDescription,
            description, realizationDateMonth, realizationDateDay,
            realizationDateYear, emphasized, overlayText, overlayUrl, urlTitle,
            smallImage, largeImage, otherImages, serviceContext);
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
            referenceLocalService.getReference(groupId, referenceId, version);

        referenceModelResourcePermission.check(
            getPermissionChecker(), reference, ReferenceActionKeys.DELETE);

        return referenceLocalService.deleteReference(
            groupId, referenceId, version, serviceContext);
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

        Reference reference =
            referenceLocalService.getReference(groupId, referenceId);

        referenceModelResourcePermission.check(
            getPermissionChecker(), reference, ReferenceActionKeys.DELETE);

        referenceLocalService.deleteReference(
            groupId, referenceId, serviceContext);
    }

    @Override
    public Reference fetchReference(long groupId, String referenceId)
        throws PortalException {

        Reference reference =
            referenceLocalService.fetchReference(groupId, referenceId);

        if (reference != null) {
            referenceModelResourcePermission.check(
                getPermissionChecker(), reference, ReferenceActionKeys.VIEW);
        }

        return reference;
    }

    /**
     * Returns an ordered range of all the references matching the group, and
     * status.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param status
     *            the reference's workflow status. For more information see
     *            {@link WorkflowConstants} for constants starting with the
     *            "STATUS_" prefix.
     * @param start
     *            the lower bound of the range of references to return
     * @param end
     *            the upper bound of the range of references to return (not
     *            inclusive)
     * @param orderByComparator
     *            the comparator to order the references
     * @return the range of matching references ordered by the comparator
     */
    @Override
    public List<Reference> getGroupReferences(
        long groupId, int status, int start, int end,
        OrderByComparator<Reference> orderByComparator) {

        QueryDefinition<Reference> queryDefinition =
            new QueryDefinition<>(status, start, end, orderByComparator);

        return referenceFinder.filterFindByGroupId(groupId, queryDefinition);
    }

    /**
     * Returns the number of references matching the group, and status.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param status
     *            the reference's workflow status. For more information see
     *            {@link WorkflowConstants} for constants starting with the
     *            "STATUS_" prefix.
     * @return the range of matching references ordered by the comparator
     */
    @Override
    public int getGroupReferencesCount(long groupId, int status) {

        QueryDefinition<Reference> queryDefinition =
            new QueryDefinition<>(status);

        return referenceFinder.filterCountByGroupId(groupId, queryDefinition);
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

        referenceModelResourcePermission.check(
            getPermissionChecker(), resourcePrimKey, ReferenceActionKeys.VIEW);

        return referenceLocalService.getLatestReference(resourcePrimKey);
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

        Reference reference = referenceLocalService.getLatestReference(
            groupId, referenceId, status);

        referenceModelResourcePermission.check(
            getPermissionChecker(), reference, ReferenceActionKeys.VIEW);

        return referenceLocalService.getLatestReference(
            groupId, referenceId, status);
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

        Reference reference = referenceLocalService.getReference(id);

        referenceModelResourcePermission.check(
            getPermissionChecker(), reference, ReferenceActionKeys.VIEW);

        return reference;
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

        Reference reference =
            referenceLocalService.getReference(groupId, referenceId);

        referenceModelResourcePermission.check(
            getPermissionChecker(), reference, ReferenceActionKeys.VIEW);

        return referenceLocalService.getReference(groupId, referenceId);
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

        Reference reference =
            referenceLocalService.getReference(groupId, referenceId, version);

        referenceModelResourcePermission.check(
            getPermissionChecker(), reference, ReferenceActionKeys.VIEW);

        return referenceLocalService.getReference(
            groupId, referenceId, version);
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

        Reference reference =
            referenceLocalService.getReferenceByUrlTitle(groupId, urlTitle);

        referenceModelResourcePermission.check(
            getPermissionChecker(), reference, ReferenceActionKeys.VIEW);

        return reference;
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

        return referencePersistence.filterFindByGroupId(
            groupId, start, end, obc);
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

        return referencePersistence.filterCountByGroupId(groupId);
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

        return referenceLocalService.getUniqueUrlTitle(groupId, name);
    }

    /**
     * Moves the reference from the Recycle Bin.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param resourcePrimKey
     *            the primary key of the resource instance
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
    @Override
    public Reference moveReferenceFromTrash(
        long groupId, long resourcePrimKey, ServiceContext serviceContext)
        throws PortalException {

        Reference reference = getLatestReference(resourcePrimKey);

        referenceModelResourcePermission.check(
            getPermissionChecker(), reference, ReferenceActionKeys.UPDATE);

        return referenceLocalService.moveReferenceFromTrash(
            getUserId(), groupId, reference, serviceContext);
    }

    /**
     * Moves the reference from the Recycle Bin.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
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
    @Override
    public Reference moveReferenceFromTrash(
        long groupId, String referenceId, ServiceContext serviceContext)
        throws PortalException {

        Reference reference = getLatestReference(
            groupId, referenceId, WorkflowConstants.STATUS_IN_TRASH);

        referenceModelResourcePermission.check(
            getPermissionChecker(), reference, ReferenceActionKeys.UPDATE);

        return referenceLocalService.moveReferenceFromTrash(
            getUserId(), groupId, reference, serviceContext);
    }

    /**
     * Moves the latest version of the reference matching the group and
     * reference ID to the recycle bin.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
     * @return the moved reference or <code>null</code> if no matching reference
     *         was found
     */
    @Override
    public Reference moveReferenceToTrash(long groupId, String referenceId)
        throws PortalException {

        Reference reference =
            referenceLocalService.getReference(groupId, referenceId);

        referenceModelResourcePermission.check(
            getPermissionChecker(), reference, ReferenceActionKeys.DELETE);

        return referenceLocalService.moveReferenceToTrash(
            getUserId(), groupId, referenceId);
    }

    /**
     * Restores the reference associated with the resource primary key from the
     * Recycle Bin.
     *
     * @param resourcePrimKey
     *            the primary key of the resource instance
     */
    @Override
    public void restoreReferenceFromTrash(long resourcePrimKey)
        throws PortalException {

        Reference reference = getLatestReference(resourcePrimKey);

        referenceModelResourcePermission.check(
            getPermissionChecker(), reference, ReferenceActionKeys.DELETE);

        referenceLocalService.restoreReferenceFromTrash(getUserId(), reference);
    }

    /**
     * Restores the reference from the Recycle Bin.
     *
     * @param groupId
     *            the primary key of the reference's group
     * @param referenceId
     *            the primary key of the reference
     */
    @Override
    public void restoreReferenceFromTrash(long groupId, String referenceId)
        throws PortalException {

        Reference reference = getLatestReference(
            groupId, referenceId, WorkflowConstants.STATUS_IN_TRASH);

        restoreReferenceFromTrash(reference.getResourcePrimKey());
    }

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

        return referenceFinder.filterFindByKeywords(
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

        return referenceFinder.filterFindByC_G_R_V_N_S_D_R_S(
            companyId, groupId, referenceId, version, name, shortDescription,
            description, realizationDateGT, realizationDateLT, status,
            andOperator, start, end, obc);
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

        return referenceFinder.filterCountByKeywords(
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

        return referenceFinder.filterCountByC_G_R_V_N_S_D_R_S(
            companyId, groupId, referenceId, version, name, shortDescription,
            description, relizationDateGT, realizationDateLT, status,
            andOperator);
    }

    /**
     * Updates the reference with additional parameters.
     *
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
        long groupId, String currentReferenceId, double version, String name,
        String shortDescription, String description, int realizationDateMonth,
        int realizationDateDay, int realizationDateYear, boolean emphasized,
        String overlayText, String overlayUrl, String urlTitle,
        String smallImage, String largeImage, String[] otherImages,
        ServiceContext serviceContext)
        throws PortalException {

        Reference reference = referenceLocalService.getReference(
            groupId, currentReferenceId, version);

        referenceModelResourcePermission.check(
            getPermissionChecker(), reference, ReferenceActionKeys.UPDATE);

        return referenceLocalService.updateReference(
            getUserId(), groupId, currentReferenceId, version, name,
            shortDescription, description, realizationDateMonth,
            realizationDateDay, realizationDateYear, emphasized, overlayText,
            overlayUrl, urlTitle, smallImage, largeImage, otherImages,
            serviceContext);
    }

    /**
     * Updates the workflow status of the reference matching the group,
     * reference ID, and version.
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
     * @param serviceContext
     *            the service context to be applied. Can set the modification
     *            date, portlet preferences, and can set whether to add the
     *            default command update for the reference.
     * @return the updated reference
     */
    @Override
    public Reference updateStatus(
        long groupId, String referenceId, double version, int status,
        ServiceContext serviceContext)
        throws PortalException {

        Reference reference =
            referenceLocalService.getReference(groupId, referenceId, version);

        referenceModelResourcePermission.check(
            getPermissionChecker(), reference, ReferenceActionKeys.UPDATE);

        return referenceLocalService.updateStatus(
            getUserId(), groupId, referenceId, version, status,
            new HashMap<String, Serializable>(), serviceContext);
    }

}
