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

package hu.webtown.liferay.portlet.reference.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import hu.webtown.liferay.portlet.reference.service.ReferenceServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link ReferenceServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link hu.webtown.liferay.portlet.reference.model.ReferenceSoap}.
 * If the method in the service utility returns a
 * {@link hu.webtown.liferay.portlet.reference.model.Reference}, that is translated to a
 * {@link hu.webtown.liferay.portlet.reference.model.ReferenceSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReferenceServiceHttp
 * @see hu.webtown.liferay.portlet.reference.model.ReferenceSoap
 * @see ReferenceServiceUtil
 * @generated
 */
@ProviderType
public class ReferenceServiceSoap {
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
	public static hu.webtown.liferay.portlet.reference.model.ReferenceSoap addReference(
		long groupId, String referenceId, boolean autoReferenceId, String name,
		String shortDescription, String description, int realizationDateMonth,
		int realizationDateDay, int realizationDateYear, boolean emphasized,
		String overlayText, String overlayUrl, String urlTitle,
		String smallImage, String largeImage, String[] otherImages,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			hu.webtown.liferay.portlet.reference.model.Reference returnValue = ReferenceServiceUtil.addReference(groupId,
					referenceId, autoReferenceId, name, shortDescription,
					description, realizationDateMonth, realizationDateDay,
					realizationDateYear, emphasized, overlayText, overlayUrl,
					urlTitle, smallImage, largeImage, otherImages,
					serviceContext);

			return hu.webtown.liferay.portlet.reference.model.ReferenceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static hu.webtown.liferay.portlet.reference.model.ReferenceSoap deleteReference(
		long groupId, String referenceId, double version,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			hu.webtown.liferay.portlet.reference.model.Reference returnValue = ReferenceServiceUtil.deleteReference(groupId,
					referenceId, version, serviceContext);

			return hu.webtown.liferay.portlet.reference.model.ReferenceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
		throws RemoteException {
		try {
			ReferenceServiceUtil.deleteReference(groupId, referenceId,
				serviceContext);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static hu.webtown.liferay.portlet.reference.model.ReferenceSoap fetchReference(
		long groupId, String referenceId) throws RemoteException {
		try {
			hu.webtown.liferay.portlet.reference.model.Reference returnValue = ReferenceServiceUtil.fetchReference(groupId,
					referenceId);

			return hu.webtown.liferay.portlet.reference.model.ReferenceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static hu.webtown.liferay.portlet.reference.model.ReferenceSoap[] getGroupReferences(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.Reference> orderByComparator)
		throws RemoteException {
		try {
			java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> returnValue =
				ReferenceServiceUtil.getGroupReferences(groupId, status, start,
					end, orderByComparator);

			return hu.webtown.liferay.portlet.reference.model.ReferenceSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static int getGroupReferencesCount(long groupId, int status)
		throws RemoteException {
		try {
			int returnValue = ReferenceServiceUtil.getGroupReferencesCount(groupId,
					status);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static hu.webtown.liferay.portlet.reference.model.ReferenceSoap getLatestReference(
		long resourcePrimKey) throws RemoteException {
		try {
			hu.webtown.liferay.portlet.reference.model.Reference returnValue = ReferenceServiceUtil.getLatestReference(resourcePrimKey);

			return hu.webtown.liferay.portlet.reference.model.ReferenceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static hu.webtown.liferay.portlet.reference.model.ReferenceSoap getLatestReference(
		long groupId, String referenceId, int status) throws RemoteException {
		try {
			hu.webtown.liferay.portlet.reference.model.Reference returnValue = ReferenceServiceUtil.getLatestReference(groupId,
					referenceId, status);

			return hu.webtown.liferay.portlet.reference.model.ReferenceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Returns the reference with the ID.
	*
	* @param id
	the primary key of the reference
	* @return the reference with the ID
	*/
	public static hu.webtown.liferay.portlet.reference.model.ReferenceSoap getReference(
		long id) throws RemoteException {
		try {
			hu.webtown.liferay.portlet.reference.model.Reference returnValue = ReferenceServiceUtil.getReference(id);

			return hu.webtown.liferay.portlet.reference.model.ReferenceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static hu.webtown.liferay.portlet.reference.model.ReferenceSoap getReference(
		long groupId, String referenceId) throws RemoteException {
		try {
			hu.webtown.liferay.portlet.reference.model.Reference returnValue = ReferenceServiceUtil.getReference(groupId,
					referenceId);

			return hu.webtown.liferay.portlet.reference.model.ReferenceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static hu.webtown.liferay.portlet.reference.model.ReferenceSoap getReference(
		long groupId, String referenceId, double version)
		throws RemoteException {
		try {
			hu.webtown.liferay.portlet.reference.model.Reference returnValue = ReferenceServiceUtil.getReference(groupId,
					referenceId, version);

			return hu.webtown.liferay.portlet.reference.model.ReferenceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static hu.webtown.liferay.portlet.reference.model.ReferenceSoap getReferenceByUrlTitle(
		long groupId, String urlTitle) throws RemoteException {
		try {
			hu.webtown.liferay.portlet.reference.model.Reference returnValue = ReferenceServiceUtil.getReferenceByUrlTitle(groupId,
					urlTitle);

			return hu.webtown.liferay.portlet.reference.model.ReferenceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static hu.webtown.liferay.portlet.reference.model.ReferenceSoap[] getReferences(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.Reference> obc)
		throws RemoteException {
		try {
			java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> returnValue =
				ReferenceServiceUtil.getReferences(groupId, start, end, obc);

			return hu.webtown.liferay.portlet.reference.model.ReferenceSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Returns the number of references belonging to the group.
	*
	* @param groupId
	the primary key of the reference's group
	* @return the number of references belonging to the group
	*/
	public static int getReferencesCount(long groupId)
		throws RemoteException {
		try {
			int returnValue = ReferenceServiceUtil.getReferencesCount(groupId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
		throws RemoteException {
		try {
			String returnValue = ReferenceServiceUtil.getUniqueUrlTitle(groupId,
					name);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static hu.webtown.liferay.portlet.reference.model.ReferenceSoap moveReferenceFromTrash(
		long groupId, long resourcePrimKey,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			hu.webtown.liferay.portlet.reference.model.Reference returnValue = ReferenceServiceUtil.moveReferenceFromTrash(groupId,
					resourcePrimKey, serviceContext);

			return hu.webtown.liferay.portlet.reference.model.ReferenceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static hu.webtown.liferay.portlet.reference.model.ReferenceSoap moveReferenceFromTrash(
		long groupId, String referenceId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			hu.webtown.liferay.portlet.reference.model.Reference returnValue = ReferenceServiceUtil.moveReferenceFromTrash(groupId,
					referenceId, serviceContext);

			return hu.webtown.liferay.portlet.reference.model.ReferenceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static hu.webtown.liferay.portlet.reference.model.ReferenceSoap moveReferenceToTrash(
		long groupId, String referenceId) throws RemoteException {
		try {
			hu.webtown.liferay.portlet.reference.model.Reference returnValue = ReferenceServiceUtil.moveReferenceToTrash(groupId,
					referenceId);

			return hu.webtown.liferay.portlet.reference.model.ReferenceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Restores the reference associated with the resource primary key from the
	* Recycle Bin.
	*
	* @param resourcePrimKey
	the primary key of the resource instance
	*/
	public static void restoreReferenceFromTrash(long resourcePrimKey)
		throws RemoteException {
		try {
			ReferenceServiceUtil.restoreReferenceFromTrash(resourcePrimKey);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Restores the reference from the Recycle Bin.
	*
	* @param groupId
	the primary key of the reference's group
	* @param referenceId
	the primary key of the reference
	*/
	public static void restoreReferenceFromTrash(long groupId,
		String referenceId) throws RemoteException {
		try {
			ReferenceServiceUtil.restoreReferenceFromTrash(groupId, referenceId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static hu.webtown.liferay.portlet.reference.model.ReferenceSoap[] search(
		long companyId, long groupId, String keywords, Double version,
		java.util.Date realizationDateGT, java.util.Date realizationDateLT,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.Reference> obc)
		throws RemoteException {
		try {
			java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> returnValue =
				ReferenceServiceUtil.search(companyId, groupId, keywords,
					version, realizationDateGT, realizationDateLT, status,
					start, end, obc);

			return hu.webtown.liferay.portlet.reference.model.ReferenceSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static hu.webtown.liferay.portlet.reference.model.ReferenceSoap[] search(
		long companyId, long groupId, String referenceId, Double version,
		String name, String shortDescription, String description,
		java.util.Date realizationDateGT, java.util.Date realizationDateLT,
		int status, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.Reference> obc)
		throws RemoteException {
		try {
			java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> returnValue =
				ReferenceServiceUtil.search(companyId, groupId, referenceId,
					version, name, shortDescription, description,
					realizationDateGT, realizationDateLT, status, andOperator,
					start, end, obc);

			return hu.webtown.liferay.portlet.reference.model.ReferenceSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
		java.util.Date realizationDateLT, int status) throws RemoteException {
		try {
			int returnValue = ReferenceServiceUtil.searchCount(companyId,
					groupId, keywords, version, realizationDateGT,
					realizationDateLT, status);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
		int status, boolean andOperator) throws RemoteException {
		try {
			int returnValue = ReferenceServiceUtil.searchCount(companyId,
					groupId, referenceId, version, name, shortDescription,
					description, relizationDateGT, realizationDateLT, status,
					andOperator);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static hu.webtown.liferay.portlet.reference.model.ReferenceSoap updateReference(
		long groupId, String currentReferenceId, double version, String name,
		String shortDescription, String description, int realizationDateMonth,
		int realizationDateDay, int realizationDateYear, boolean emphasized,
		String overlayText, String overlayUrl, String urlTitle,
		String smallImage, String largeImage, String[] otherImages,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			hu.webtown.liferay.portlet.reference.model.Reference returnValue = ReferenceServiceUtil.updateReference(groupId,
					currentReferenceId, version, name, shortDescription,
					description, realizationDateMonth, realizationDateDay,
					realizationDateYear, emphasized, overlayText, overlayUrl,
					urlTitle, smallImage, largeImage, otherImages,
					serviceContext);

			return hu.webtown.liferay.portlet.reference.model.ReferenceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static hu.webtown.liferay.portlet.reference.model.ReferenceSoap updateStatus(
		long groupId, String referenceId, double version, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			hu.webtown.liferay.portlet.reference.model.Reference returnValue = ReferenceServiceUtil.updateStatus(groupId,
					referenceId, version, status, serviceContext);

			return hu.webtown.liferay.portlet.reference.model.ReferenceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ReferenceServiceSoap.class);
}