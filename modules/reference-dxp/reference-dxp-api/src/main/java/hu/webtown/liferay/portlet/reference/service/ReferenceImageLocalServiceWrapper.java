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
 * Provides a wrapper for {@link ReferenceImageLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ReferenceImageLocalService
 * @generated
 */
@ProviderType
public class ReferenceImageLocalServiceWrapper
	implements ReferenceImageLocalService,
		ServiceWrapper<ReferenceImageLocalService> {
	public ReferenceImageLocalServiceWrapper(
		ReferenceImageLocalService referenceImageLocalService) {
		_referenceImageLocalService = referenceImageLocalService;
	}

	@Override
	public hu.webtown.liferay.portlet.reference.model.ReferenceImage addReferenceImage(
		long groupId, String referenceId, double version,
		int referenceImageType, String image) {
		return _referenceImageLocalService.addReferenceImage(groupId,
			referenceId, version, referenceImageType, image);
	}

	/**
	* Adds the reference image to the database. Also notifies the appropriate model listeners.
	*
	* @param referenceImage the reference image
	* @return the reference image that was added
	*/
	@Override
	public hu.webtown.liferay.portlet.reference.model.ReferenceImage addReferenceImage(
		hu.webtown.liferay.portlet.reference.model.ReferenceImage referenceImage) {
		return _referenceImageLocalService.addReferenceImage(referenceImage);
	}

	/**
	* Creates a new reference image with the primary key. Does not add the reference image to the database.
	*
	* @param referenceImageId the primary key for the new reference image
	* @return the new reference image
	*/
	@Override
	public hu.webtown.liferay.portlet.reference.model.ReferenceImage createReferenceImage(
		long referenceImageId) {
		return _referenceImageLocalService.createReferenceImage(referenceImageId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _referenceImageLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the reference image with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param referenceImageId the primary key of the reference image
	* @return the reference image that was removed
	* @throws PortalException if a reference image with the primary key could not be found
	*/
	@Override
	public hu.webtown.liferay.portlet.reference.model.ReferenceImage deleteReferenceImage(
		long referenceImageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _referenceImageLocalService.deleteReferenceImage(referenceImageId);
	}

	/**
	* Deletes the reference image from the database. Also notifies the appropriate model listeners.
	*
	* @param referenceImage the reference image
	* @return the reference image that was removed
	*/
	@Override
	public hu.webtown.liferay.portlet.reference.model.ReferenceImage deleteReferenceImage(
		hu.webtown.liferay.portlet.reference.model.ReferenceImage referenceImage) {
		return _referenceImageLocalService.deleteReferenceImage(referenceImage);
	}

	@Override
	public void deleteReferenceImages(long groupId, String referenceId,
		double version) {
		_referenceImageLocalService.deleteReferenceImages(groupId, referenceId,
			version);
	}

	@Override
	public void deleteReferenceImages(long groupId, String referenceId,
		double version, int referenceImageType) {
		_referenceImageLocalService.deleteReferenceImages(groupId, referenceId,
			version, referenceImageType);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _referenceImageLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _referenceImageLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _referenceImageLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _referenceImageLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _referenceImageLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _referenceImageLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public hu.webtown.liferay.portlet.reference.model.ReferenceImage fetchReferenceImage(
		long referenceImageId) {
		return _referenceImageLocalService.fetchReferenceImage(referenceImageId);
	}

	/**
	* Returns the reference image matching the UUID and group.
	*
	* @param uuid the reference image's UUID
	* @param groupId the primary key of the group
	* @return the matching reference image, or <code>null</code> if a matching reference image could not be found
	*/
	@Override
	public hu.webtown.liferay.portlet.reference.model.ReferenceImage fetchReferenceImageByUuidAndGroupId(
		String uuid, long groupId) {
		return _referenceImageLocalService.fetchReferenceImageByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _referenceImageLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _referenceImageLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _referenceImageLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _referenceImageLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the reference image with the primary key.
	*
	* @param referenceImageId the primary key of the reference image
	* @return the reference image
	* @throws PortalException if a reference image with the primary key could not be found
	*/
	@Override
	public hu.webtown.liferay.portlet.reference.model.ReferenceImage getReferenceImage(
		long referenceImageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _referenceImageLocalService.getReferenceImage(referenceImageId);
	}

	/**
	* Returns the reference image matching the UUID and group.
	*
	* @param uuid the reference image's UUID
	* @param groupId the primary key of the group
	* @return the matching reference image
	* @throws PortalException if a matching reference image could not be found
	*/
	@Override
	public hu.webtown.liferay.portlet.reference.model.ReferenceImage getReferenceImageByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _referenceImageLocalService.getReferenceImageByUuidAndGroupId(uuid,
			groupId);
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
	@Override
	public java.util.List<hu.webtown.liferay.portlet.reference.model.ReferenceImage> getReferenceImages(
		int start, int end) {
		return _referenceImageLocalService.getReferenceImages(start, end);
	}

	@Override
	public java.util.List<hu.webtown.liferay.portlet.reference.model.ReferenceImage> getReferenceImages(
		long groupId, String referenceId, double version) {
		return _referenceImageLocalService.getReferenceImages(groupId,
			referenceId, version);
	}

	@Override
	public java.util.List<hu.webtown.liferay.portlet.reference.model.ReferenceImage> getReferenceImages(
		long groupId, String referenceId, double version, int referenceImageType) {
		return _referenceImageLocalService.getReferenceImages(groupId,
			referenceId, version, referenceImageType);
	}

	/**
	* Returns all the reference images matching the UUID and company.
	*
	* @param uuid the UUID of the reference images
	* @param companyId the primary key of the company
	* @return the matching reference images, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<hu.webtown.liferay.portlet.reference.model.ReferenceImage> getReferenceImagesByUuidAndCompanyId(
		String uuid, long companyId) {
		return _referenceImageLocalService.getReferenceImagesByUuidAndCompanyId(uuid,
			companyId);
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
	@Override
	public java.util.List<hu.webtown.liferay.portlet.reference.model.ReferenceImage> getReferenceImagesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.ReferenceImage> orderByComparator) {
		return _referenceImageLocalService.getReferenceImagesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of reference images.
	*
	* @return the number of reference images
	*/
	@Override
	public int getReferenceImagesCount() {
		return _referenceImageLocalService.getReferenceImagesCount();
	}

	/**
	* Updates the reference image in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param referenceImage the reference image
	* @return the reference image that was updated
	*/
	@Override
	public hu.webtown.liferay.portlet.reference.model.ReferenceImage updateReferenceImage(
		hu.webtown.liferay.portlet.reference.model.ReferenceImage referenceImage) {
		return _referenceImageLocalService.updateReferenceImage(referenceImage);
	}

	@Override
	public ReferenceImageLocalService getWrappedService() {
		return _referenceImageLocalService;
	}

	@Override
	public void setWrappedService(
		ReferenceImageLocalService referenceImageLocalService) {
		_referenceImageLocalService = referenceImageLocalService;
	}

	private ReferenceImageLocalService _referenceImageLocalService;
}