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

package hu.webtown.liferay.portlet.reference.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Reference. This utility wraps
 * {@link hu.webtown.liferay.portlet.reference.service.impl.ReferenceLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ReferenceLocalService
 * @see hu.webtown.liferay.portlet.reference.service.base.ReferenceLocalServiceBaseImpl
 * @see hu.webtown.liferay.portlet.reference.service.impl.ReferenceLocalServiceImpl
 * @generated
 */
@ProviderType
public class ReferenceLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link hu.webtown.liferay.portlet.reference.service.impl.ReferenceLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds a reference with additional parameters.
	*
	* @param userId
	the primary key of the reference's creator/owner
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	* @param autoReferenceId
	whether to auto generate the reference ID
	* @param version
	the reference's version
	* @param name
	the reference's name
	* @param shortDescription
	the reference's short description
	* @param description
	the reference's description
	* @param realizationDateMonth
	the month the reference is set to realized
	* @param realizationDateDay
	the calendar day the reference is set to realized
	* @param realizationDateYear
	the year the reference is set to realized
	* @param emphasized
	the reference's emphasized
	* @param overlayText
	the reference's overlay text
	* @param overlayUrl
	the reference's overlay url
	* @param urlTitle
	the reference's url title
	* @param smallImage
	the reference's small image
	* @param largeImage
	the reference's large image
	* @param otherImages
	the reference's other images
	* @param serviceContext
	the service context to be applied. Can set the UUID, creation
	date, modification date, expando bridge attributes, guest
	permissions, group permissions, asset category IDs, asset tag
	names, asset link entry IDs, URL title, and workflow actions
	for the reference. Can also set whether to add the default
	guest and group permissions.
	* @return the reference
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference addReference(
		long userId, long groupId, String referenceId, boolean autoReferenceId,
		double version, String name, String shortDescription,
		String description, int realizationDateMonth, int realizationDateDay,
		int realizationDateYear, boolean emphasized, String overlayText,
		String overlayUrl, String urlTitle, String smallImage,
		String largeImage, String[] otherImages,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addReference(userId, groupId, referenceId, autoReferenceId,
			version, name, shortDescription, description, realizationDateMonth,
			realizationDateDay, realizationDateYear, emphasized, overlayText,
			overlayUrl, urlTitle, smallImage, largeImage, otherImages,
			serviceContext);
	}

	/**
	* Adds the reference to the database. Also notifies the appropriate model listeners.
	*
	* @param reference the reference
	* @return the reference that was added
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference addReference(
		hu.webtown.liferay.portlet.reference.model.Reference reference) {
		return getService().addReference(reference);
	}

	public static void addReferenceImages(long groupId, String referenceId,
		double version, String smallImage, String largeImage,
		String[] otherImages) {
		getService()
			.addReferenceImages(groupId, referenceId, version, smallImage,
			largeImage, otherImages);
	}

	/**
	* Adds the resources to the most recently created reference.
	*
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	* @param addGroupPermissions
	whether to add group permissions
	* @param addGuestPermissions
	whether to add guest permissions
	*/
	public static void addReferenceResources(long groupId, String referenceId,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.addReferenceResources(groupId, referenceId, addGroupPermissions,
			addGuestPermissions);
	}

	/**
	* Adds the resources with the permissions to the most recently created
	* reference.
	*
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	* @param groupPermissions
	the group permissions to be added
	* @param guestPermissions
	the guest permissions to be added
	*/
	public static void addReferenceResources(long groupId, String referenceId,
		String[] groupPermissions, String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.addReferenceResources(groupId, referenceId, groupPermissions,
			guestPermissions);
	}

	/**
	* Adds the resources to the reference.
	*
	* @param reference
	the reference
	* @param addGroupPermissions
	whether to add group permissions
	* @param addGuestPermissions
	whether to add guest permissions
	*/
	public static void addReferenceResources(
		hu.webtown.liferay.portlet.reference.model.Reference reference,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.addReferenceResources(reference, addGroupPermissions,
			addGuestPermissions);
	}

	/**
	* Adds the model resources with the permissions to the reference.
	*
	* @param reference
	the reference to add resources to
	* @param groupPermissions
	the group permissions to be added
	* @param guestPermissions
	the guest permissions to be added
	*/
	public static void addReferenceResources(
		hu.webtown.liferay.portlet.reference.model.Reference reference,
		String[] groupPermissions, String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.addReferenceResources(reference, groupPermissions, guestPermissions);
	}

	/**
	* Creates a new reference with the primary key. Does not add the reference to the database.
	*
	* @param id the primary key for the new reference
	* @return the new reference
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference createReference(
		long id) {
		return getService().createReference(id);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the reference with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the reference
	* @return the reference that was removed
	* @throws PortalException if a reference with the primary key could not be found
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference deleteReference(
		long id) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteReference(id);
	}

	/**
	* Deletes the reference and its resources matching the group, reference ID,
	* and version.
	*
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	* @param version
	the reference's version
	* @param serviceContext
	the service context to be applied.
	* @return the deleted reference
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference deleteReference(
		long groupId, String referenceId, double version,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteReference(groupId, referenceId, version,
			serviceContext);
	}

	/**
	* Deletes all references and their resources matching the group and
	* reference ID.
	*
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	* @param serviceContext
	the service context to be applied.
	*/
	public static void deleteReference(long groupId, String referenceId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteReference(groupId, referenceId, serviceContext);
	}

	/**
	* Deletes the reference from the database. Also notifies the appropriate model listeners.
	*
	* @param reference the reference
	* @return the reference that was removed
	* @throws PortalException
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference deleteReference(
		hu.webtown.liferay.portlet.reference.model.Reference reference)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteReference(reference);
	}

	/**
	* Deletes the reference and its resources.
	*
	* @param reference
	the reference
	* @param serviceContext
	the service context to be applied (optionally
	<code>null</code>).
	* @return the deleted reference
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference deleteReference(
		hu.webtown.liferay.portlet.reference.model.Reference reference,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteReference(reference, serviceContext);
	}

	/**
	* Deletes all the group's references and resources.
	*
	* @param groupId
	the primary key of the reference's group
	*/
	public static void deleteReferences(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteReferences(groupId);
	}

	/**
	* Deletes all the group's references and resources, optionally including
	* recycled references.
	*
	* @param groupId
	the primary key of the reference's group
	* @param includeTrashedEntries
	whether to include recycled references
	*/
	public static void deleteReferences(long groupId,
		boolean includeTrashedEntries)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteReferences(groupId, includeTrashedEntries);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link hu.webtown.liferay.portlet.reference.model.impl.ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link hu.webtown.liferay.portlet.reference.model.impl.ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Returns the latest indexable reference matching the resource primary key.
	*
	* @param resourcePrimKey
	the primary key of the resource instance
	* @return the latest indexable reference matching the resource primary key,
	or <code>null</code> if no matching reference could be found
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference fetchLatestIndexableReference(
		long resourcePrimKey) {
		return getService().fetchLatestIndexableReference(resourcePrimKey);
	}

	public static hu.webtown.liferay.portlet.reference.model.Reference fetchLatestReference(
		long resourcePrimKey) {
		return getService().fetchLatestReference(resourcePrimKey);
	}

	public static hu.webtown.liferay.portlet.reference.model.Reference fetchLatestReference(
		long resourcePrimKey, int status) {
		return getService().fetchLatestReference(resourcePrimKey, status);
	}

	/**
	* Returns the latest reference matching the resource primary key and
	* workflow status, optionally preferring references with approved workflow
	* status.
	*
	* @param resourcePrimKey
	the primary key of the resource instance
	* @param status
	the reference's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @param preferApproved
	whether to prefer returning the latest matching reference that
	has workflow status {@link WorkflowConstants#STATUS_APPROVED}
	over returning one that has a different status
	* @return the latest reference matching the resource primary key and
	workflow status, optionally preferring references with an
	approved workflow status, or <code>null</code> if no matching
	reference could be found
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference fetchLatestReference(
		long resourcePrimKey, int status, boolean preferApproved) {
		return getService()
				   .fetchLatestReference(resourcePrimKey, status, preferApproved);
	}

	public static hu.webtown.liferay.portlet.reference.model.Reference fetchLatestReference(
		long resourcePrimKey, int[] statuses) {
		return getService().fetchLatestReference(resourcePrimKey, statuses);
	}

	/**
	* Returns the latest reference matching the group, reference ID, and
	* workflow status.
	*
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	* @param status
	the reference's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @return the latest matching reference, or <code>null</code> if no
	matching reference could be found
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference fetchLatestReference(
		long groupId, String referenceId, int status) {
		return getService().fetchLatestReference(groupId, referenceId, status);
	}

	public static hu.webtown.liferay.portlet.reference.model.Reference fetchLatestReferenceByUrlTitle(
		long groupId, String urlTitle, int status) {
		return getService()
				   .fetchLatestReferenceByUrlTitle(groupId, urlTitle, status);
	}

	public static hu.webtown.liferay.portlet.reference.model.Reference fetchReference(
		long id) {
		return getService().fetchReference(id);
	}

	public static hu.webtown.liferay.portlet.reference.model.Reference fetchReference(
		long groupId, String referenceId) {
		return getService().fetchReference(groupId, referenceId);
	}

	/**
	* Returns the reference matching the group, reference ID, and version.
	*
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	* @param version
	the reference's version
	* @return the reference matching the group, reference ID, and version, or
	<code>null</code> if no reference could be found
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference fetchReference(
		long groupId, String referenceId, double version) {
		return getService().fetchReference(groupId, referenceId, version);
	}

	public static hu.webtown.liferay.portlet.reference.model.Reference fetchReferenceByUrlTitle(
		long groupId, String urlTitle) {
		return getService().fetchReferenceByUrlTitle(groupId, urlTitle);
	}

	/**
	* Returns the reference matching the UUID and group.
	*
	* @param uuid the reference's UUID
	* @param groupId the primary key of the group
	* @return the matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference fetchReferenceByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchReferenceByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
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
	the primary key of the reference's company
	* @param version
	the reference's version
	* @param status
	the reference's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @param start
	the lower bound of the range of references to return
	* @param end
	the upper bound of the range of references to return (not
	inclusive)
	* @return the range of matching references ordered by reference ID
	*/
	public static java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> getCompanyReferences(
		long companyId, double version, int status, int start, int end) {
		return getService()
				   .getCompanyReferences(companyId, version, status, start, end);
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
	the primary key of the reference's company
	* @param status
	the reference's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @param start
	the lower bound of the range of references to return
	* @param end
	the upper bound of the range of references to return (not
	inclusive)
	* @return the range of matching references ordered by reference ID
	*/
	public static java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> getCompanyReferences(
		long companyId, int status, int start, int end) {
		return getService().getCompanyReferences(companyId, status, start, end);
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
	the primary key of the reference's company
	* @param version
	the reference's version
	* @param status
	the reference's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @return the number of matching references
	*/
	public static int getCompanyReferencesCount(long companyId, double version,
		int status) {
		return getService().getCompanyReferencesCount(companyId, version, status);
	}

	/**
	* Returns the number of references matching the company and workflow
	* status.
	*
	* @param companyId
	the primary key of the reference's company
	* @param status
	the reference's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @return the number of matching references
	*/
	public static int getCompanyReferencesCount(long companyId, int status) {
		return getService().getCompanyReferencesCount(companyId, status);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the latest reference matching the resource primary key,
	* preferring references with approved workflow status.
	*
	* @param resourcePrimKey
	the primary key of the resource instance
	* @return the latest reference matching the resource primary key,
	preferring references with approved workflow status
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference getLatestReference(
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLatestReference(resourcePrimKey);
	}

	/**
	* Returns the latest reference matching the resource primary key and
	* workflow status, preferring references with approved workflow status.
	*
	* @param resourcePrimKey
	the primary key of the resource instance
	* @param status
	the reference's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @return the latest reference matching the resource primary key and
	workflow status, preferring references with approved workflow
	status
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference getLatestReference(
		long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLatestReference(resourcePrimKey, status);
	}

	/**
	* Returns the latest reference matching the resource primary key and
	* workflow status, optionally preferring references with approved workflow
	* status.
	*
	* @param resourcePrimKey
	the primary key of the resource instance
	* @param status
	the reference's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @param preferApproved
	whether to prefer returning the latest matching reference that
	has workflow status {@link WorkflowConstants#STATUS_APPROVED}
	over returning one that has a different status
	* @return the latest reference matching the resource primary key and
	workflow status, optionally preferring references with approved
	workflow status
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference getLatestReference(
		long resourcePrimKey, int status, boolean preferApproved)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getLatestReference(resourcePrimKey, status, preferApproved);
	}

	/**
	* Returns the latest reference with the group and reference ID.
	*
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	* @return the latest matching reference
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference getLatestReference(
		long groupId, String referenceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLatestReference(groupId, referenceId);
	}

	/**
	* Returns the latest reference matching the group, reference ID, and
	* workflow status.
	*
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	* @param status
	the reference's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @return the latest matching reference
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference getLatestReference(
		long groupId, String referenceId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLatestReference(groupId, referenceId, status);
	}

	/**
	* Returns the latest reference matching the group, URL title, and workflow
	* status.
	*
	* @param groupId
	the primary key of the reference's group
	* @param urlTitle
	the reference's accessible URL title
	* @param status
	the reference's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @return the latest matching reference
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference getLatestReferenceByUrlTitle(
		long groupId, String urlTitle, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getLatestReferenceByUrlTitle(groupId, urlTitle, status);
	}

	/**
	* Returns the latest version number of the reference with the group and
	* reference ID.
	*
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	* @return the latest version number of the matching reference
	*/
	public static double getLatestVersion(long groupId, String referenceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLatestVersion(groupId, referenceId);
	}

	/**
	* Returns the latest version number of the reference with the group,
	* reference ID, and workflow status.
	*
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	* @param status
	the reference's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @return the latest version number of the matching reference
	*/
	public static double getLatestVersion(long groupId, String referenceId,
		int status) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLatestVersion(groupId, referenceId, status);
	}

	/**
	* Returns the oldest reference with the group and reference ID.
	*
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	* @return the oldest matching reference
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference getOldestReference(
		long groupId, String referenceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getOldestReference(groupId, referenceId);
	}

	/**
	* Returns the oldest reference matching the group, reference ID, and
	* workflow status.
	*
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	* @param status
	the reference's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @return the oldest matching reference
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference getOldestReference(
		long groupId, String referenceId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getOldestReference(groupId, referenceId, status);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<?extends com.liferay.portal.kernel.model.PersistedModel> getPersistedModel(
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(resourcePrimKey);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the previously approved version of the reference. For more
	* information on the approved workflow status, see
	* {@link WorkflowConstants#STATUS_APPROVED}.
	*
	* @param reference
	the reference
	* @return the previously approved version of the reference, or the current
	reference if there are no previously approved references
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference getPreviousApprovedReference(
		hu.webtown.liferay.portlet.reference.model.Reference reference) {
		return getService().getPreviousApprovedReference(reference);
	}

	/**
	* Returns the reference with the primary key.
	*
	* @param id the primary key of the reference
	* @return the reference
	* @throws PortalException if a reference with the primary key could not be found
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference getReference(
		long id) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getReference(id);
	}

	/**
	* Returns the latest approved reference, or the latest unapproved reference
	* if none are approved. Both approved and unapproved references must match
	* the group and reference ID.
	*
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	* @return the matching reference
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference getReference(
		long groupId, String referenceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getReference(groupId, referenceId);
	}

	/**
	* Returns the reference matching the group, reference ID, and version.
	*
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	* @param version
	the reference's version
	* @return the matching reference
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference getReference(
		long groupId, String referenceId, double version)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getReference(groupId, referenceId, version);
	}

	/**
	* Returns the latest reference that is approved, or the latest unapproved
	* reference if none are approved. Both approved and unapproved references
	* must match the group and URL title.
	*
	* @param groupId
	the primary key of the reference's group
	* @param urlTitle
	the reference's accessible URL title
	* @return the matching reference
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference getReferenceByUrlTitle(
		long groupId, String urlTitle)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getReferenceByUrlTitle(groupId, urlTitle);
	}

	/**
	* Returns the reference matching the UUID and group.
	*
	* @param uuid the reference's UUID
	* @param groupId the primary key of the group
	* @return the matching reference
	* @throws PortalException if a matching reference could not be found
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference getReferenceByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getReferenceByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns all the references present in the system.
	*
	* @return the references present in the system
	*/
	public static java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> getReferences() {
		return getService().getReferences();
	}

	/**
	* Returns a range of all the references.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link hu.webtown.liferay.portlet.reference.model.impl.ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of references
	*/
	public static java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> getReferences(
		int start, int end) {
		return getService().getReferences(start, end);
	}

	/**
	* Returns all the references belonging to the group.
	*
	* @param groupId
	the primary key of the reference's group
	* @return the references belonging to the group
	*/
	public static java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> getReferences(
		long groupId) {
		return getService().getReferences(groupId);
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
	the primary key of the reference's group
	* @param start
	the lower bound of the range of references to return
	* @param end
	the upper bound of the range of references to return (not
	inclusive)
	* @return the range of matching references
	*/
	public static java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> getReferences(
		long groupId, int start, int end) {
		return getService().getReferences(groupId, start, end);
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
	the primary key of the reference's group
	* @param start
	the lower bound of the range of references to return
	* @param end
	the upper bound of the range of references to return (not
	inclusive)
	* @param obc
	the comparator to order the references
	* @return the range of matching references ordered by the comparator
	*/
	public static java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> getReferences(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.Reference> obc) {
		return getService().getReferences(groupId, start, end, obc);
	}

	/**
	* Returns all the references matching the group and reference ID.
	*
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	* @return the matching references
	*/
	public static java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> getReferences(
		long groupId, String referenceId) {
		return getService().getReferences(groupId, referenceId);
	}

	/**
	* Returns all the references matching the group and reference ID.
	*
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	* @param start
	the lower bound of the range of references to return
	* @param end
	the upper bound of the range of references to return (not
	inclusive)
	* @param obc
	the comparator to order the references
	* @return the range of matching references ordered by the comparator
	*/
	public static java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> getReferences(
		long groupId, String referenceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.Reference> obc) {
		return getService().getReferences(groupId, referenceId, start, end, obc);
	}

	/**
	* Returns all the references matching the resource primary key.
	*
	* @param resourcePrimKey
	the primary key of the resource instance
	* @return the references matching the resource primary key
	*/
	public static java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> getReferencesByResourcePrimKey(
		long resourcePrimKey) {
		return getService().getReferencesByResourcePrimKey(resourcePrimKey);
	}

	/**
	* Returns all the references matching the UUID and company.
	*
	* @param uuid the UUID of the references
	* @param companyId the primary key of the company
	* @return the matching references, or an empty list if no matches were found
	*/
	public static java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> getReferencesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getReferencesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of references matching the UUID and company.
	*
	* @param uuid the UUID of the references
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching references, or an empty list if no matches were found
	*/
	public static java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> getReferencesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.Reference> orderByComparator) {
		return getService()
				   .getReferencesByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of references.
	*
	* @return the number of references
	*/
	public static int getReferencesCount() {
		return getService().getReferencesCount();
	}

	/**
	* Returns the number of references belonging to the group.
	*
	* @param groupId
	the primary key of the reference's group
	* @return the number of references belonging to the group
	*/
	public static int getReferencesCount(long groupId) {
		return getService().getReferencesCount(groupId);
	}

	/**
	* Returns the number of references belonging to the group and reference ID.
	*
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	* @return the number of references belonging to the group and reference ID.
	*/
	public static int getReferencesCount(long groupId, String referenceId) {
		return getService().getReferencesCount(groupId, referenceId);
	}

	/**
	* Returns the reference's unique URL title.
	*
	* @param groupId
	the primary key of the reference's group
	* @param name
	the name of the reference
	* @return the reference's unique URL title
	*/
	public static String getUniqueUrlTitle(long groupId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUniqueUrlTitle(groupId, name);
	}

	/**
	* Returns the reference's unique URL title.
	*
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	* @param urlTitle
	the reference's accessible URL title
	* @return the reference's unique URL title
	*/
	public static String getUniqueUrlTitle(long groupId, String referenceId,
		String urlTitle)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUniqueUrlTitle(groupId, referenceId, urlTitle);
	}

	/**
	* Returns <code>true</code> if the specified reference exists.
	*
	* @param groupId
	the primary key of the group
	* @param referenceId
	the primary key of the reference
	* @return <code>true</code> if the specified reference exists;
	<code>false</code> otherwise
	*/
	public static boolean hasReference(long groupId, String referenceId) {
		return getService().hasReference(groupId, referenceId);
	}

	/**
	* Returns <code>true</code> if the reference, specified by group and
	* reference ID, is the latest version.
	*
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	* @param version
	the reference's version
	* @return <code>true</code> if the specified reference is the latest
	version; <code>false</code> otherwise
	*/
	public static boolean isLatestVersion(long groupId, String referenceId,
		double version)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().isLatestVersion(groupId, referenceId, version);
	}

	/**
	* Returns <code>true</code> if the reference, specified by group, reference
	* ID, and workflow status, is the latest version.
	*
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	* @param version
	the reference's version
	* @param status
	the reference's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @return <code>true</code> if the specified reference is the latest
	version; <code>false</code> otherwise
	*/
	public static boolean isLatestVersion(long groupId, String referenceId,
		double version, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .isLatestVersion(groupId, referenceId, version, status);
	}

	public static boolean isListable(
		hu.webtown.liferay.portlet.reference.model.Reference reference) {
		return getService().isListable(reference);
	}

	/**
	* Moves the reference from the Recycle Bin.
	*
	* @param userId
	the primary key of the user updating the reference
	* @param groupId
	the primary key of the reference's group
	* @param reference
	the reference
	* @param serviceContext
	the service context to be applied. Can set the modification
	date, portlet preferences, and can set whether to add the
	default command update for the reference. With respect to
	social activities, by setting the service context's command to
	{@link Constants#UPDATE}, the invocation is considered a
	reference activity; otherwise it is considered a reference add
	activity.
	* @return the updated reference, which was moved from the Recycle Bin
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference moveReferenceFromTrash(
		long userId, long groupId,
		hu.webtown.liferay.portlet.reference.model.Reference reference,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .moveReferenceFromTrash(userId, groupId, reference,
			serviceContext);
	}

	/**
	* Moves the latest version of the reference matching the group and
	* reference ID to the recycle bin.
	*
	* @param userId
	the primary key of the user updating the reference
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	* @return the moved reference or <code>null</code> if no matching reference
	was found
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference moveReferenceToTrash(
		long userId, long groupId, String referenceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().moveReferenceToTrash(userId, groupId, referenceId);
	}

	/**
	* Moves the latest version of the reference matching the group and
	* reference ID to the recycle bin.
	*
	* @param userId
	the primary key of the user updating the reference
	* @param reference
	the reference
	* @return the updated reference, which was moved to the Recycle Bin
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference moveReferenceToTrash(
		long userId,
		hu.webtown.liferay.portlet.reference.model.Reference reference)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().moveReferenceToTrash(userId, reference);
	}

	/**
	* Restores the reference from the Recycle Bin.
	*
	* @param userId
	the primary key of the user restoring the reference
	* @param reference
	the reference
	* @return the restored reference from the Recycle Bin
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference restoreReferenceFromTrash(
		long userId,
		hu.webtown.liferay.portlet.reference.model.Reference reference)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().restoreReferenceFromTrash(userId, reference);
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
	the primary key of the reference's company
	* @param groupId
	the primary key of the group (optionally <code>0</code>)
	* @param keywords
	the keywords (space separated), which may occur in the
	reference ID, name, short description, or description
	(optionally <code>null</code>). If the keywords value is not
	<code>null</code>, the search uses the OR operator in
	connecting query criteria; otherwise it uses the AND operator.
	* @param version
	the reference's version (optionally <code>null</code>)
	* @param realizationDateGT
	the date after which a matching reference's realization date
	must be after (optionally <code>null</code>)
	* @param realizationDateLT
	the date before which a matching reference's realization date
	must be before (optionally <code>null</code>)
	* @param status
	the reference's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @param start
	the lower bound of the range of references to return
	* @param end
	the upper bound of the range of references to return (not
	inclusive)
	* @param obc
	the comparator to order the references
	* @return the range of matching references ordered by the comparator
	*/
	public static java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> search(
		long companyId, long groupId, String keywords, Double version,
		java.util.Date realizationDateGT, java.util.Date realizationDateLT,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.Reference> obc) {
		return getService()
				   .search(companyId, groupId, keywords, version,
			realizationDateGT, realizationDateLT, status, start, end, obc);
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
	the primary key of the reference's company
	* @param groupId
	the primary key of the group (optionally <code>0</code>)
	* @param referenceId
	the reference ID keywords (space separated, optionally
	<code>null</code>)
	* @param version
	the reference's version (optionally <code>null</code>)
	* @param name
	the name keywords (space separated, optionally
	<code>null</code>)
	* @param shortDescription
	the short description keywords (space separated, optionally
	<code>null</code>)
	* @param description
	the description keywords (space separated, optionally
	<code>null</code>)
	* @param realizationDateGT
	the date after which a matching reference's realization date
	must be after (optionally <code>null</code>)
	* @param realizationDateLT
	the date before which a matching reference's realization date
	must be before (optionally <code>null</code>)
	* @param status
	the reference's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @param andOperator
	whether every field must match its value or keywords, or just
	one field must match. Company, group, and status must all
	match their values.
	* @param start
	the lower bound of the range of references to return
	* @param end
	the upper bound of the range of references to return (not
	inclusive)
	* @param obc
	the comparator to order the references
	* @return the range of matching references ordered by the comparator
	*/
	public static java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> search(
		long companyId, long groupId, String referenceId, Double version,
		String name, String shortDescription, String description,
		java.util.Date realizationDateGT, java.util.Date realizationDateLT,
		int status, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.Reference> obc) {
		return getService()
				   .search(companyId, groupId, referenceId, version, name,
			shortDescription, description, realizationDateGT,
			realizationDateLT, status, andOperator, start, end, obc);
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
	the primary key of the reference's company
	* @param groupId
	the primary key of the group (optionally <code>0</code>)
	* @param keywords
	the keywords (space separated), which may occur in the
	reference ID, name, short description, or description
	(optionally <code>null</code>). If the keywords value is not
	<code>null</code>, the search uses the OR operator in
	connecting query criteria; otherwise it uses the AND operator.
	* @param params
	the finder parameters (optionally <code>null</code>)
	* @param start
	the lower bound of the range of references to return
	* @param end
	the upper bound of the range of references to return (not
	inclusive)
	* @param sort
	the field, type, and direction by which to sort (optionally
	<code>null</code>)
	* @return the matching references ordered by <code>sort</code>
	*/
	public static com.liferay.portal.kernel.search.Hits search(long companyId,
		long groupId, String keywords,
		java.util.LinkedHashMap<String, Object> params, int start, int end,
		com.liferay.portal.kernel.search.Sort sort) {
		return getService()
				   .search(companyId, groupId, keywords, params, start, end,
			sort);
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
	the primary key of the reference's company
	* @param groupId
	the primary key of the group (optionally <code>0</code>)
	* @param referenceId
	the reference ID keywords (space separated, optionally
	<code>null</code>)
	* @param name
	the name keywords (space separated, optionally
	<code>null</code>)
	* @param shortDescription
	the short description keywords (space separated, optionally
	<code>null</code>)
	* @param description
	the description keywords (space separated, optionally
	<code>null</code>)
	* @param status
	the reference's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @param params
	the finder parameters (optionally <code>null</code>). The
	<code>includeDiscussions</code> parameter can be set to
	<code>true</code> to search for the keywords in the reference
	discussions.
	* @param andSearch
	whether every field must match its value or keywords, or just
	one field must match
	* @param start
	the lower bound of the range of references to return
	* @param end
	the upper bound of the range of references to return (not
	inclusive)
	* @param sort
	the field, type, and direction by which to sort (optionally
	<code>null</code>)
	* @return the matching references ordered by <code>sort</code>
	*/
	public static com.liferay.portal.kernel.search.Hits search(long companyId,
		long groupId, String referenceId, String name, String shortDescription,
		String description, int status,
		java.util.LinkedHashMap<String, Object> params, boolean andSearch,
		int start, int end, com.liferay.portal.kernel.search.Sort sort) {
		return getService()
				   .search(companyId, groupId, referenceId, name,
			shortDescription, description, status, params, andSearch, start,
			end, sort);
	}

	/**
	* Returns the number of references matching the parameters, including a
	* keywords parameter for matching with the reference's ID, name, short
	* description, and description.
	*
	* @param companyId
	the primary key of the reference's company
	* @param groupId
	the primary key of the group (optionally <code>0</code>)
	* @param keywords
	the keywords (space separated), which may occur in the
	reference ID, name, short description, or description
	(optionally <code>null</code>). If the keywords value is not
	<code>null</code>, the search uses the OR operator in
	connecting query criteria; otherwise it uses the AND operator.
	* @param version
	the reference's version (optionally <code>null</code>)
	* @param realizationDateGT
	the date after which a matching reference's realization date
	must be after (optionally <code>null</code>)
	* @param realizationDateLT
	the date before which a matching reference's realization date
	must be before (optionally <code>null</code>)
	* @param status
	the reference's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @return the number of matching references
	*/
	public static int searchCount(long companyId, long groupId,
		String keywords, Double version, java.util.Date realizationDateGT,
		java.util.Date realizationDateLT, int status) {
		return getService()
				   .searchCount(companyId, groupId, keywords, version,
			realizationDateGT, realizationDateLT, status);
	}

	/**
	* Returns the number of references matching the parameters, including
	* keyword parameters for reference ID, name, short description, and
	* description, and an AND operator switch.
	*
	* @param companyId
	the primary key of the reference's company
	* @param groupId
	the primary key of the group (optionally <code>0</code>)
	* @param referenceId
	the reference ID keywords (space separated, optionally
	<code>null</code>)
	* @param version
	the reference's version (optionally <code>null</code>)
	* @param name
	the name keywords (space separated, optionally
	<code>null</code>)
	* @param shortDescription
	the short description keywords (space separated, optionally
	<code>null</code>)
	* @param description
	the description keywords (space separated, optionally
	<code>null</code>)
	* @param realizationDateGT
	the date after which a matching reference's realization date
	must be after (optionally <code>null</code>)
	* @param realizationDateLT
	the date before which a matching reference's realization date
	must be before (optionally <code>null</code>)
	* @param status
	the reference's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @param andOperator
	whether every field must match its value or keywords, or just
	one field must match. Company, group, and status must all
	match their values.
	* @return the number of matching references
	*/
	public static int searchCount(long companyId, long groupId,
		String referenceId, Double version, String name,
		String shortDescription, String description,
		java.util.Date relizationDateGT, java.util.Date realizationDateLT,
		int status, boolean andOperator) {
		return getService()
				   .searchCount(companyId, groupId, referenceId, version, name,
			shortDescription, description, relizationDateGT, realizationDateLT,
			status, andOperator);
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
	the primary key of the reference's company
	* @param groupId
	the primary key of the group (optionally <code>0</code>)
	* @param keywords
	the keywords (space separated), which may occur in the
	reference ID, name, short description, or description
	(optionally <code>null</code>). If the keywords value is not
	<code>null</code>, the search uses the OR operator in
	connecting query criteria; otherwise it uses the AND operator.
	* @param params
	the finder parameters (optionally <code>null</code>)
	* @param start
	the lower bound of the range of references to return
	* @param end
	the upper bound of the range of references to return (not
	inclusive)
	* @param sort
	the field, type, and direction by which to sort (optionally
	<code>null</code>)
	* @return a {@link BaseModelSearchResult} containing the total number of
	hits and an ordered range of all the matching references ordered
	by <code>sort</code>
	*/
	public static com.liferay.portal.kernel.search.BaseModelSearchResult<hu.webtown.liferay.portlet.reference.model.Reference> searchReferences(
		long companyId, long groupId, String keywords,
		java.util.LinkedHashMap<String, Object> params, int start, int end,
		com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .searchReferences(companyId, groupId, keywords, params,
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
	the primary key of the reference's company
	* @param groupId
	the primary key of the group (optionally <code>0</code>)
	* @param referenceId
	the reference ID keywords (space separated, optionally
	<code>null</code>)
	* @param name
	the name keywords (space separated, optionally
	<code>null</code>)
	* @param shortDescription
	the short description keywords (space separated, optionally
	<code>null</code>)
	* @param description
	the content keywords (space separated, optionally
	<code>null</code>)
	* @param status
	the reference's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @param params
	the finder parameters (optionally <code>null</code>).
	* @param andSearch
	whether every field must match its value or keywords, or just
	one field must match
	* @param start
	the lower bound of the range of references to return
	* @param end
	the upper bound of the range of references to return (not
	inclusive)
	* @param sort
	the field, type, and direction by which to sort (optionally
	<code>null</code>)
	* @return a {@link BaseModelSearchResult} containing the total number of
	hits and an ordered range of all the matching references ordered
	by <code>sort</code>
	*/
	public static com.liferay.portal.kernel.search.BaseModelSearchResult<hu.webtown.liferay.portlet.reference.model.Reference> searchReferences(
		long companyId, long groupId, String referenceId, String name,
		String shortDdescription, String description, int status,
		java.util.LinkedHashMap<String, Object> params, boolean andSearch,
		int start, int end, com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .searchReferences(companyId, groupId, referenceId, name,
			shortDdescription, description, status, params, andSearch, start,
			end, sort);
	}

	/**
	* Updates the reference's asset with the new asset categories, tag names,
	* and link entries, removing and adding them as necessary.
	*
	* @param userId
	the primary key of the user updating the reference's asset
	* @param reference
	the reference
	* @param assetCategoryIds
	the primary keys of the new asset categories
	* @param assetTagNames
	the new asset tag names
	* @param assetLinkEntryIds
	the primary keys of the new asset link entries
	* @param priority
	the priority of the asset
	*/
	public static void updateAsset(long userId,
		hu.webtown.liferay.portlet.reference.model.Reference reference,
		long[] assetCategoryIds, String[] assetTagNames,
		long[] assetLinkEntryIds, Double priority)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.updateAsset(userId, reference, assetCategoryIds, assetTagNames,
			assetLinkEntryIds, priority);
	}

	/**
	* Updates the reference with additional parameters.
	*
	* @param userId
	the primary key of the user updating the reference
	* @param groupId
	the primary key of the reference's group
	* @param currentReferenceId
	the primary key of the reference
	* @param version
	the reference's version
	* @param name
	the reference's name
	* @param shortDescription
	the reference's short description
	* @param description
	the reference's description
	* @param realizationDateMonth
	the month the reference is set to realized
	* @param realizationDateDay
	the calendar day the reference is set to realized
	* @param realizationDateYear
	the year the reference is set to realized
	* @param emphasized
	whether the reference is emphasized
	* @param overlayText
	the reference's overlay text
	* @param overlayUrl
	the reference's overlay URL
	* @param urlTitle
	the reference's url title
	* @param smallImage
	the reference's small image
	* @param largeImage
	the reference's large image
	* @param otherImages
	the reference's other images (optionally <code>null</code>)
	* @param serviceContext
	the service context to be applied. Can set the modification
	date, expando bridge attributes, asset category IDs, asset tag
	names, asset link entry IDs, asset priority, workflow actions,
	URL title, and can set whether to add the default command
	update for the reference. With respect to social activities,
	by setting the service context's command to
	{@link Constants#UPDATE}, the invocation is considered a
	reference update activity; otherwise it is considered a
	reference add activity.
	* @return the updated reference
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference updateReference(
		long userId, long groupId, String currentReferenceId, double version,
		String name, String shortDescription, String description,
		int realizationDateMonth, int realizationDateDay,
		int realizationDateYear, boolean emphasized, String overlayText,
		String overlayUrl, String urlTitle, String smallImage,
		String largeImage, String[] otherImages,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateReference(userId, groupId, currentReferenceId,
			version, name, shortDescription, description, realizationDateMonth,
			realizationDateDay, realizationDateYear, emphasized, overlayText,
			overlayUrl, urlTitle, smallImage, largeImage, otherImages,
			serviceContext);
	}

	/**
	* Updates the reference in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param reference the reference
	* @return the reference that was updated
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference updateReference(
		hu.webtown.liferay.portlet.reference.model.Reference reference) {
		return getService().updateReference(reference);
	}

	/**
	* Updates the workflow status of the reference matching the class PK.
	*
	* @param userId
	the primary key of the user updating the reference's status
	* @param id
	the primary key of the reference
	* @param status
	the reference's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @param workflowContext
	the reference's configured workflow
	* @param serviceContext
	the service context to be applied. Can set the modification
	date, portlet preferences, and can set whether to add the
	default command update for the reference.
	* @return the updated reference
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference updateStatus(
		long userId, long id, int status,
		java.util.Map<String, java.io.Serializable> workflowContext,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(userId, id, status, workflowContext,
			serviceContext);
	}

	/**
	* Updates the workflow status of the reference matching the group,
	* reference ID, and version.
	*
	* @param userId
	the primary key of the user updating the reference's status
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	* @param version
	the reference's version
	* @param status
	the reference's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @param workflowContext
	the reference's configured workflow
	* @param serviceContext
	the service context to be applied. Can set the modification
	date, portlet preferences, and can set whether to add the
	default command update for the reference.
	* @return the updated reference
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference updateStatus(
		long userId, long groupId, String referenceId, double version,
		int status,
		java.util.Map<String, java.io.Serializable> workflowContext,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(userId, groupId, referenceId, version, status,
			workflowContext, serviceContext);
	}

	/**
	* Updates the workflow status of the reference.
	*
	* @param userId
	the primary key of the user updating the reference's status
	* @param reference
	the reference
	* @param status
	the reference's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @param workflowContext
	the reference's configured workflow context
	* @param serviceContext
	the service context to be applied. Can set the modification
	date, status date, and portlet preferences. With respect to
	social activities, by setting the service context's command to
	{@link Constants#UPDATE}, the invocation is considered a
	reference activity; otherwise it is considered a reference add
	activity.
	* @return the updated reference
	*/
	public static hu.webtown.liferay.portlet.reference.model.Reference updateStatus(
		long userId,
		hu.webtown.liferay.portlet.reference.model.Reference reference,
		int status,
		java.util.Map<String, java.io.Serializable> workflowContext,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(userId, reference, status, workflowContext,
			serviceContext);
	}

	public static ReferenceLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ReferenceLocalService, ReferenceLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ReferenceLocalService.class);

		ServiceTracker<ReferenceLocalService, ReferenceLocalService> serviceTracker =
			new ServiceTracker<ReferenceLocalService, ReferenceLocalService>(bundle.getBundleContext(),
				ReferenceLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}