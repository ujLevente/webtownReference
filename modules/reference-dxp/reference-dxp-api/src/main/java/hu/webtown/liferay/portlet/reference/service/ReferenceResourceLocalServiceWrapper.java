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
 * Provides a wrapper for {@link ReferenceResourceLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ReferenceResourceLocalService
 * @generated
 */
@ProviderType
public class ReferenceResourceLocalServiceWrapper
	implements ReferenceResourceLocalService,
		ServiceWrapper<ReferenceResourceLocalService> {
	public ReferenceResourceLocalServiceWrapper(
		ReferenceResourceLocalService referenceResourceLocalService) {
		_referenceResourceLocalService = referenceResourceLocalService;
	}

	/**
	* Adds the reference resource to the database. Also notifies the appropriate model listeners.
	*
	* @param referenceResource the reference resource
	* @return the reference resource that was added
	*/
	@Override
	public hu.webtown.liferay.portlet.reference.model.ReferenceResource addReferenceResource(
		hu.webtown.liferay.portlet.reference.model.ReferenceResource referenceResource) {
		return _referenceResourceLocalService.addReferenceResource(referenceResource);
	}

	/**
	* Creates a new reference resource with the primary key. Does not add the reference resource to the database.
	*
	* @param resourcePrimKey the primary key for the new reference resource
	* @return the new reference resource
	*/
	@Override
	public hu.webtown.liferay.portlet.reference.model.ReferenceResource createReferenceResource(
		long resourcePrimKey) {
		return _referenceResourceLocalService.createReferenceResource(resourcePrimKey);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _referenceResourceLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the reference resource with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param resourcePrimKey the primary key of the reference resource
	* @return the reference resource that was removed
	* @throws PortalException if a reference resource with the primary key could not be found
	*/
	@Override
	public hu.webtown.liferay.portlet.reference.model.ReferenceResource deleteReferenceResource(
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _referenceResourceLocalService.deleteReferenceResource(resourcePrimKey);
	}

	@Override
	public void deleteReferenceResource(long groupId, String referenceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_referenceResourceLocalService.deleteReferenceResource(groupId,
			referenceId);
	}

	/**
	* Deletes the reference resource from the database. Also notifies the appropriate model listeners.
	*
	* @param referenceResource the reference resource
	* @return the reference resource that was removed
	*/
	@Override
	public hu.webtown.liferay.portlet.reference.model.ReferenceResource deleteReferenceResource(
		hu.webtown.liferay.portlet.reference.model.ReferenceResource referenceResource) {
		return _referenceResourceLocalService.deleteReferenceResource(referenceResource);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _referenceResourceLocalService.dynamicQuery();
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
		return _referenceResourceLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link hu.webtown.liferay.portlet.reference.model.impl.ReferenceResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _referenceResourceLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link hu.webtown.liferay.portlet.reference.model.impl.ReferenceResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _referenceResourceLocalService.dynamicQuery(dynamicQuery, start,
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
		return _referenceResourceLocalService.dynamicQueryCount(dynamicQuery);
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
		return _referenceResourceLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public hu.webtown.liferay.portlet.reference.model.ReferenceResource fetchReferenceResource(
		long resourcePrimKey) {
		return _referenceResourceLocalService.fetchReferenceResource(resourcePrimKey);
	}

	@Override
	public hu.webtown.liferay.portlet.reference.model.ReferenceResource fetchReferenceResource(
		long groupId, String referenceId) {
		return _referenceResourceLocalService.fetchReferenceResource(groupId,
			referenceId);
	}

	@Override
	public hu.webtown.liferay.portlet.reference.model.ReferenceResource fetchReferenceResource(
		String uuid, long groupId) {
		return _referenceResourceLocalService.fetchReferenceResource(uuid,
			groupId);
	}

	/**
	* Returns the reference resource matching the UUID and group.
	*
	* @param uuid the reference resource's UUID
	* @param groupId the primary key of the group
	* @return the matching reference resource, or <code>null</code> if a matching reference resource could not be found
	*/
	@Override
	public hu.webtown.liferay.portlet.reference.model.ReferenceResource fetchReferenceResourceByUuidAndGroupId(
		String uuid, long groupId) {
		return _referenceResourceLocalService.fetchReferenceResourceByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _referenceResourceLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _referenceResourceLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _referenceResourceLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _referenceResourceLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the reference resource with the primary key.
	*
	* @param resourcePrimKey the primary key of the reference resource
	* @return the reference resource
	* @throws PortalException if a reference resource with the primary key could not be found
	*/
	@Override
	public hu.webtown.liferay.portlet.reference.model.ReferenceResource getReferenceResource(
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _referenceResourceLocalService.getReferenceResource(resourcePrimKey);
	}

	/**
	* Returns the reference resource matching the UUID and group.
	*
	* @param uuid the reference resource's UUID
	* @param groupId the primary key of the group
	* @return the matching reference resource
	* @throws PortalException if a matching reference resource could not be found
	*/
	@Override
	public hu.webtown.liferay.portlet.reference.model.ReferenceResource getReferenceResourceByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _referenceResourceLocalService.getReferenceResourceByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public long getReferenceResourcePrimKey(long groupId, String referenceId) {
		return _referenceResourceLocalService.getReferenceResourcePrimKey(groupId,
			referenceId);
	}

	@Override
	public long getReferenceResourcePrimKey(String uuid, long groupId,
		String referenceId) {
		return _referenceResourceLocalService.getReferenceResourcePrimKey(uuid,
			groupId, referenceId);
	}

	/**
	* Returns a range of all the reference resources.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link hu.webtown.liferay.portlet.reference.model.impl.ReferenceResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of reference resources
	* @param end the upper bound of the range of reference resources (not inclusive)
	* @return the range of reference resources
	*/
	@Override
	public java.util.List<hu.webtown.liferay.portlet.reference.model.ReferenceResource> getReferenceResources(
		int start, int end) {
		return _referenceResourceLocalService.getReferenceResources(start, end);
	}

	@Override
	public java.util.List<hu.webtown.liferay.portlet.reference.model.ReferenceResource> getReferenceResources(
		long groupId) {
		return _referenceResourceLocalService.getReferenceResources(groupId);
	}

	/**
	* Returns all the reference resources matching the UUID and company.
	*
	* @param uuid the UUID of the reference resources
	* @param companyId the primary key of the company
	* @return the matching reference resources, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<hu.webtown.liferay.portlet.reference.model.ReferenceResource> getReferenceResourcesByUuidAndCompanyId(
		String uuid, long companyId) {
		return _referenceResourceLocalService.getReferenceResourcesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of reference resources matching the UUID and company.
	*
	* @param uuid the UUID of the reference resources
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of reference resources
	* @param end the upper bound of the range of reference resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching reference resources, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<hu.webtown.liferay.portlet.reference.model.ReferenceResource> getReferenceResourcesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.ReferenceResource> orderByComparator) {
		return _referenceResourceLocalService.getReferenceResourcesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of reference resources.
	*
	* @return the number of reference resources
	*/
	@Override
	public int getReferenceResourcesCount() {
		return _referenceResourceLocalService.getReferenceResourcesCount();
	}

	/**
	* Updates the reference resource in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param referenceResource the reference resource
	* @return the reference resource that was updated
	*/
	@Override
	public hu.webtown.liferay.portlet.reference.model.ReferenceResource updateReferenceResource(
		hu.webtown.liferay.portlet.reference.model.ReferenceResource referenceResource) {
		return _referenceResourceLocalService.updateReferenceResource(referenceResource);
	}

	@Override
	public ReferenceResourceLocalService getWrappedService() {
		return _referenceResourceLocalService;
	}

	@Override
	public void setWrappedService(
		ReferenceResourceLocalService referenceResourceLocalService) {
		_referenceResourceLocalService = referenceResourceLocalService;
	}

	private ReferenceResourceLocalService _referenceResourceLocalService;
}