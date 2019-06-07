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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ReferenceService}.
 *
 * @author Brian Wing Shun Chan
 * @see ReferenceService
 * @generated
 */
@ProviderType
public class ReferenceServiceWrapper implements ReferenceService,
	ServiceWrapper<ReferenceService> {
	public ReferenceServiceWrapper(ReferenceService referenceService) {
		_referenceService = referenceService;
	}

	/**
	* Adds a reference with additional parameters.
	*
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	* @param autoReferenceId
	whether to auto generate the reference ID
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
	@Override
	public hu.webtown.liferay.portlet.reference.model.Reference addReference(
		long groupId, String referenceId, boolean autoReferenceId, String name,
		String shortDescription, String description, int realizationDateMonth,
		int realizationDateDay, int realizationDateYear, boolean emphasized,
		String overlayText, String overlayUrl, String urlTitle,
		String smallImage, String largeImage, String[] otherImages,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _referenceService.addReference(groupId, referenceId,
			autoReferenceId, name, shortDescription, description,
			realizationDateMonth, realizationDateDay, realizationDateYear,
			emphasized, overlayText, overlayUrl, urlTitle, smallImage,
			largeImage, otherImages, serviceContext);
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
	@Override
	public hu.webtown.liferay.portlet.reference.model.Reference deleteReference(
		long groupId, String referenceId, double version,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _referenceService.deleteReference(groupId, referenceId, version,
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
	@Override
	public void deleteReference(long groupId, String referenceId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_referenceService.deleteReference(groupId, referenceId, serviceContext);
	}

	@Override
	public hu.webtown.liferay.portlet.reference.model.Reference fetchReference(
		long groupId, String referenceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _referenceService.fetchReference(groupId, referenceId);
	}

	/**
	* Returns an ordered range of all the references matching the group, and
	* status.
	*
	* @param groupId
	the primary key of the reference's group
	* @param status
	the reference's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @param start
	the lower bound of the range of references to return
	* @param end
	the upper bound of the range of references to return (not
	inclusive)
	* @param orderByComparator
	the comparator to order the references
	* @return the range of matching references ordered by the comparator
	*/
	@Override
	public java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> getGroupReferences(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.Reference> orderByComparator) {
		return _referenceService.getGroupReferences(groupId, status, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of references matching the group, and status.
	*
	* @param groupId
	the primary key of the reference's group
	* @param status
	the reference's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @return the range of matching references ordered by the comparator
	*/
	@Override
	public int getGroupReferencesCount(long groupId, int status) {
		return _referenceService.getGroupReferencesCount(groupId, status);
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
	@Override
	public hu.webtown.liferay.portlet.reference.model.Reference getLatestReference(
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _referenceService.getLatestReference(resourcePrimKey);
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
	@Override
	public hu.webtown.liferay.portlet.reference.model.Reference getLatestReference(
		long groupId, String referenceId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _referenceService.getLatestReference(groupId, referenceId, status);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _referenceService.getOSGiServiceIdentifier();
	}

	/**
	* Returns the reference with the ID.
	*
	* @param id
	the primary key of the reference
	* @return the reference with the ID
	*/
	@Override
	public hu.webtown.liferay.portlet.reference.model.Reference getReference(
		long id) throws com.liferay.portal.kernel.exception.PortalException {
		return _referenceService.getReference(id);
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
	@Override
	public hu.webtown.liferay.portlet.reference.model.Reference getReference(
		long groupId, String referenceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _referenceService.getReference(groupId, referenceId);
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
	@Override
	public hu.webtown.liferay.portlet.reference.model.Reference getReference(
		long groupId, String referenceId, double version)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _referenceService.getReference(groupId, referenceId, version);
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
	@Override
	public hu.webtown.liferay.portlet.reference.model.Reference getReferenceByUrlTitle(
		long groupId, String urlTitle)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _referenceService.getReferenceByUrlTitle(groupId, urlTitle);
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
	@Override
	public java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> getReferences(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.Reference> obc) {
		return _referenceService.getReferences(groupId, start, end, obc);
	}

	/**
	* Returns the number of references belonging to the group.
	*
	* @param groupId
	the primary key of the reference's group
	* @return the number of references belonging to the group
	*/
	@Override
	public int getReferencesCount(long groupId) {
		return _referenceService.getReferencesCount(groupId);
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
	@Override
	public String getUniqueUrlTitle(long groupId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _referenceService.getUniqueUrlTitle(groupId, name);
	}

	/**
	* Moves the reference from the Recycle Bin.
	*
	* @param groupId
	the primary key of the reference's group
	* @param resourcePrimKey
	the primary key of the resource instance
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
	@Override
	public hu.webtown.liferay.portlet.reference.model.Reference moveReferenceFromTrash(
		long groupId, long resourcePrimKey,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _referenceService.moveReferenceFromTrash(groupId,
			resourcePrimKey, serviceContext);
	}

	/**
	* Moves the reference from the Recycle Bin.
	*
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
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
	@Override
	public hu.webtown.liferay.portlet.reference.model.Reference moveReferenceFromTrash(
		long groupId, String referenceId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _referenceService.moveReferenceFromTrash(groupId, referenceId,
			serviceContext);
	}

	/**
	* Moves the latest version of the reference matching the group and
	* reference ID to the recycle bin.
	*
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	* @return the moved reference or <code>null</code> if no matching reference
	was found
	*/
	@Override
	public hu.webtown.liferay.portlet.reference.model.Reference moveReferenceToTrash(
		long groupId, String referenceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _referenceService.moveReferenceToTrash(groupId, referenceId);
	}

	/**
	* Restores the reference associated with the resource primary key from the
	* Recycle Bin.
	*
	* @param resourcePrimKey
	the primary key of the resource instance
	*/
	@Override
	public void restoreReferenceFromTrash(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		_referenceService.restoreReferenceFromTrash(resourcePrimKey);
	}

	/**
	* Restores the reference from the Recycle Bin.
	*
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	*/
	@Override
	public void restoreReferenceFromTrash(long groupId, String referenceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_referenceService.restoreReferenceFromTrash(groupId, referenceId);
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
	@Override
	public java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> search(
		long companyId, long groupId, String keywords, Double version,
		java.util.Date realizationDateGT, java.util.Date realizationDateLT,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.Reference> obc) {
		return _referenceService.search(companyId, groupId, keywords, version,
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
	@Override
	public java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> search(
		long companyId, long groupId, String referenceId, Double version,
		String name, String shortDescription, String description,
		java.util.Date realizationDateGT, java.util.Date realizationDateLT,
		int status, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.Reference> obc) {
		return _referenceService.search(companyId, groupId, referenceId,
			version, name, shortDescription, description, realizationDateGT,
			realizationDateLT, status, andOperator, start, end, obc);
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
	@Override
	public int searchCount(long companyId, long groupId, String keywords,
		Double version, java.util.Date realizationDateGT,
		java.util.Date realizationDateLT, int status) {
		return _referenceService.searchCount(companyId, groupId, keywords,
			version, realizationDateGT, realizationDateLT, status);
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
	@Override
	public int searchCount(long companyId, long groupId, String referenceId,
		Double version, String name, String shortDescription,
		String description, java.util.Date relizationDateGT,
		java.util.Date realizationDateLT, int status, boolean andOperator) {
		return _referenceService.searchCount(companyId, groupId, referenceId,
			version, name, shortDescription, description, relizationDateGT,
			realizationDateLT, status, andOperator);
	}

	/**
	* Updates the reference with additional parameters.
	*
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
	@Override
	public hu.webtown.liferay.portlet.reference.model.Reference updateReference(
		long groupId, String currentReferenceId, double version, String name,
		String shortDescription, String description, int realizationDateMonth,
		int realizationDateDay, int realizationDateYear, boolean emphasized,
		String overlayText, String overlayUrl, String urlTitle,
		String smallImage, String largeImage, String[] otherImages,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _referenceService.updateReference(groupId, currentReferenceId,
			version, name, shortDescription, description, realizationDateMonth,
			realizationDateDay, realizationDateYear, emphasized, overlayText,
			overlayUrl, urlTitle, smallImage, largeImage, otherImages,
			serviceContext);
	}

	/**
	* Updates the workflow status of the reference matching the group,
	* reference ID, and version.
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
	* @param serviceContext
	the service context to be applied. Can set the modification
	date, portlet preferences, and can set whether to add the
	default command update for the reference.
	* @return the updated reference
	*/
	@Override
	public hu.webtown.liferay.portlet.reference.model.Reference updateStatus(
		long groupId, String referenceId, double version, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _referenceService.updateStatus(groupId, referenceId, version,
			status, serviceContext);
	}

	@Override
	public ReferenceService getWrappedService() {
		return _referenceService;
	}

	@Override
	public void setWrappedService(ReferenceService referenceService) {
		_referenceService = referenceService;
	}

	private ReferenceService _referenceService;
}