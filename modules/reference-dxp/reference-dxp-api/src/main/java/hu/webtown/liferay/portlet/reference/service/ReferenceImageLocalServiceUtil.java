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
 * Provides the local service utility for ReferenceImage. This utility wraps
 * {@link hu.webtown.liferay.portlet.reference.service.impl.ReferenceImageLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ReferenceImageLocalService
 * @see hu.webtown.liferay.portlet.reference.service.base.ReferenceImageLocalServiceBaseImpl
 * @see hu.webtown.liferay.portlet.reference.service.impl.ReferenceImageLocalServiceImpl
 * @generated
 */
@ProviderType
public class ReferenceImageLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link hu.webtown.liferay.portlet.reference.service.impl.ReferenceImageLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static hu.webtown.liferay.portlet.reference.model.ReferenceImage addReferenceImage(
		long groupId, String referenceId, double version,
		int referenceImageType, String image) {
		return getService()
				   .addReferenceImage(groupId, referenceId, version,
			referenceImageType, image);
	}

	/**
	* Adds the reference image to the database. Also notifies the appropriate model listeners.
	*
	* @param referenceImage the reference image
	* @return the reference image that was added
	*/
	public static hu.webtown.liferay.portlet.reference.model.ReferenceImage addReferenceImage(
		hu.webtown.liferay.portlet.reference.model.ReferenceImage referenceImage) {
		return getService().addReferenceImage(referenceImage);
	}

	/**
	* Creates a new reference image with the primary key. Does not add the reference image to the database.
	*
	* @param referenceImageId the primary key for the new reference image
	* @return the new reference image
	*/
	public static hu.webtown.liferay.portlet.reference.model.ReferenceImage createReferenceImage(
		long referenceImageId) {
		return getService().createReferenceImage(referenceImageId);
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
	* Deletes the reference image with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param referenceImageId the primary key of the reference image
	* @return the reference image that was removed
	* @throws PortalException if a reference image with the primary key could not be found
	*/
	public static hu.webtown.liferay.portlet.reference.model.ReferenceImage deleteReferenceImage(
		long referenceImageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteReferenceImage(referenceImageId);
	}

	/**
	* Deletes the reference image from the database. Also notifies the appropriate model listeners.
	*
	* @param referenceImage the reference image
	* @return the reference image that was removed
	*/
	public static hu.webtown.liferay.portlet.reference.model.ReferenceImage deleteReferenceImage(
		hu.webtown.liferay.portlet.reference.model.ReferenceImage referenceImage) {
		return getService().deleteReferenceImage(referenceImage);
	}

	public static void deleteReferenceImages(long groupId, String referenceId,
		double version) {
		getService().deleteReferenceImages(groupId, referenceId, version);
	}

	public static void deleteReferenceImages(long groupId, String referenceId,
		double version, int referenceImageType) {
		getService()
			.deleteReferenceImages(groupId, referenceId, version,
			referenceImageType);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link hu.webtown.liferay.portlet.reference.model.impl.ReferenceImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link hu.webtown.liferay.portlet.reference.model.impl.ReferenceImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static hu.webtown.liferay.portlet.reference.model.ReferenceImage fetchReferenceImage(
		long referenceImageId) {
		return getService().fetchReferenceImage(referenceImageId);
	}

	/**
	* Returns the reference image matching the UUID and group.
	*
	* @param uuid the reference image's UUID
	* @param groupId the primary key of the group
	* @return the matching reference image, or <code>null</code> if a matching reference image could not be found
	*/
	public static hu.webtown.liferay.portlet.reference.model.ReferenceImage fetchReferenceImageByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchReferenceImageByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the reference image with the primary key.
	*
	* @param referenceImageId the primary key of the reference image
	* @return the reference image
	* @throws PortalException if a reference image with the primary key could not be found
	*/
	public static hu.webtown.liferay.portlet.reference.model.ReferenceImage getReferenceImage(
		long referenceImageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getReferenceImage(referenceImageId);
	}

	/**
	* Returns the reference image matching the UUID and group.
	*
	* @param uuid the reference image's UUID
	* @param groupId the primary key of the group
	* @return the matching reference image
	* @throws PortalException if a matching reference image could not be found
	*/
	public static hu.webtown.liferay.portlet.reference.model.ReferenceImage getReferenceImageByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getReferenceImageByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the reference images.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link hu.webtown.liferay.portlet.reference.model.impl.ReferenceImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of reference images
	* @param end the upper bound of the range of reference images (not inclusive)
	* @return the range of reference images
	*/
	public static java.util.List<hu.webtown.liferay.portlet.reference.model.ReferenceImage> getReferenceImages(
		int start, int end) {
		return getService().getReferenceImages(start, end);
	}

	public static java.util.List<hu.webtown.liferay.portlet.reference.model.ReferenceImage> getReferenceImages(
		long groupId, String referenceId, double version) {
		return getService().getReferenceImages(groupId, referenceId, version);
	}

	public static java.util.List<hu.webtown.liferay.portlet.reference.model.ReferenceImage> getReferenceImages(
		long groupId, String referenceId, double version, int referenceImageType) {
		return getService()
				   .getReferenceImages(groupId, referenceId, version,
			referenceImageType);
	}

	/**
	* Returns all the reference images matching the UUID and company.
	*
	* @param uuid the UUID of the reference images
	* @param companyId the primary key of the company
	* @return the matching reference images, or an empty list if no matches were found
	*/
	public static java.util.List<hu.webtown.liferay.portlet.reference.model.ReferenceImage> getReferenceImagesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getReferenceImagesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of reference images matching the UUID and company.
	*
	* @param uuid the UUID of the reference images
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of reference images
	* @param end the upper bound of the range of reference images (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching reference images, or an empty list if no matches were found
	*/
	public static java.util.List<hu.webtown.liferay.portlet.reference.model.ReferenceImage> getReferenceImagesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.ReferenceImage> orderByComparator) {
		return getService()
				   .getReferenceImagesByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of reference images.
	*
	* @return the number of reference images
	*/
	public static int getReferenceImagesCount() {
		return getService().getReferenceImagesCount();
	}

	/**
	* Updates the reference image in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param referenceImage the reference image
	* @return the reference image that was updated
	*/
	public static hu.webtown.liferay.portlet.reference.model.ReferenceImage updateReferenceImage(
		hu.webtown.liferay.portlet.reference.model.ReferenceImage referenceImage) {
		return getService().updateReferenceImage(referenceImage);
	}

	public static ReferenceImageLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ReferenceImageLocalService, ReferenceImageLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ReferenceImageLocalService.class);

		ServiceTracker<ReferenceImageLocalService, ReferenceImageLocalService> serviceTracker =
			new ServiceTracker<ReferenceImageLocalService, ReferenceImageLocalService>(bundle.getBundleContext(),
				ReferenceImageLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}