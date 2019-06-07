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
 * Provides the local service utility for ReferenceResource. This utility wraps
 * {@link hu.webtown.liferay.portlet.reference.service.impl.ReferenceResourceLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ReferenceResourceLocalService
 * @see hu.webtown.liferay.portlet.reference.service.base.ReferenceResourceLocalServiceBaseImpl
 * @see hu.webtown.liferay.portlet.reference.service.impl.ReferenceResourceLocalServiceImpl
 * @generated
 */
@ProviderType
public class ReferenceResourceLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link hu.webtown.liferay.portlet.reference.service.impl.ReferenceResourceLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the reference resource to the database. Also notifies the appropriate model listeners.
	*
	* @param referenceResource the reference resource
	* @return the reference resource that was added
	*/
	public static hu.webtown.liferay.portlet.reference.model.ReferenceResource addReferenceResource(
		hu.webtown.liferay.portlet.reference.model.ReferenceResource referenceResource) {
		return getService().addReferenceResource(referenceResource);
	}

	/**
	* Creates a new reference resource with the primary key. Does not add the reference resource to the database.
	*
	* @param resourcePrimKey the primary key for the new reference resource
	* @return the new reference resource
	*/
	public static hu.webtown.liferay.portlet.reference.model.ReferenceResource createReferenceResource(
		long resourcePrimKey) {
		return getService().createReferenceResource(resourcePrimKey);
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
	* Deletes the reference resource with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param resourcePrimKey the primary key of the reference resource
	* @return the reference resource that was removed
	* @throws PortalException if a reference resource with the primary key could not be found
	*/
	public static hu.webtown.liferay.portlet.reference.model.ReferenceResource deleteReferenceResource(
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteReferenceResource(resourcePrimKey);
	}

	public static void deleteReferenceResource(long groupId, String referenceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteReferenceResource(groupId, referenceId);
	}

	/**
	* Deletes the reference resource from the database. Also notifies the appropriate model listeners.
	*
	* @param referenceResource the reference resource
	* @return the reference resource that was removed
	*/
	public static hu.webtown.liferay.portlet.reference.model.ReferenceResource deleteReferenceResource(
		hu.webtown.liferay.portlet.reference.model.ReferenceResource referenceResource) {
		return getService().deleteReferenceResource(referenceResource);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link hu.webtown.liferay.portlet.reference.model.impl.ReferenceResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link hu.webtown.liferay.portlet.reference.model.impl.ReferenceResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static hu.webtown.liferay.portlet.reference.model.ReferenceResource fetchReferenceResource(
		long resourcePrimKey) {
		return getService().fetchReferenceResource(resourcePrimKey);
	}

	public static hu.webtown.liferay.portlet.reference.model.ReferenceResource fetchReferenceResource(
		long groupId, String referenceId) {
		return getService().fetchReferenceResource(groupId, referenceId);
	}

	public static hu.webtown.liferay.portlet.reference.model.ReferenceResource fetchReferenceResource(
		String uuid, long groupId) {
		return getService().fetchReferenceResource(uuid, groupId);
	}

	/**
	* Returns the reference resource matching the UUID and group.
	*
	* @param uuid the reference resource's UUID
	* @param groupId the primary key of the group
	* @return the matching reference resource, or <code>null</code> if a matching reference resource could not be found
	*/
	public static hu.webtown.liferay.portlet.reference.model.ReferenceResource fetchReferenceResourceByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchReferenceResourceByUuidAndGroupId(uuid, groupId);
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
	* Returns the reference resource with the primary key.
	*
	* @param resourcePrimKey the primary key of the reference resource
	* @return the reference resource
	* @throws PortalException if a reference resource with the primary key could not be found
	*/
	public static hu.webtown.liferay.portlet.reference.model.ReferenceResource getReferenceResource(
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getReferenceResource(resourcePrimKey);
	}

	/**
	* Returns the reference resource matching the UUID and group.
	*
	* @param uuid the reference resource's UUID
	* @param groupId the primary key of the group
	* @return the matching reference resource
	* @throws PortalException if a matching reference resource could not be found
	*/
	public static hu.webtown.liferay.portlet.reference.model.ReferenceResource getReferenceResourceByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getReferenceResourceByUuidAndGroupId(uuid, groupId);
	}

	public static long getReferenceResourcePrimKey(long groupId,
		String referenceId) {
		return getService().getReferenceResourcePrimKey(groupId, referenceId);
	}

	public static long getReferenceResourcePrimKey(String uuid, long groupId,
		String referenceId) {
		return getService()
				   .getReferenceResourcePrimKey(uuid, groupId, referenceId);
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
	public static java.util.List<hu.webtown.liferay.portlet.reference.model.ReferenceResource> getReferenceResources(
		int start, int end) {
		return getService().getReferenceResources(start, end);
	}

	public static java.util.List<hu.webtown.liferay.portlet.reference.model.ReferenceResource> getReferenceResources(
		long groupId) {
		return getService().getReferenceResources(groupId);
	}

	/**
	* Returns all the reference resources matching the UUID and company.
	*
	* @param uuid the UUID of the reference resources
	* @param companyId the primary key of the company
	* @return the matching reference resources, or an empty list if no matches were found
	*/
	public static java.util.List<hu.webtown.liferay.portlet.reference.model.ReferenceResource> getReferenceResourcesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService()
				   .getReferenceResourcesByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<hu.webtown.liferay.portlet.reference.model.ReferenceResource> getReferenceResourcesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.ReferenceResource> orderByComparator) {
		return getService()
				   .getReferenceResourcesByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of reference resources.
	*
	* @return the number of reference resources
	*/
	public static int getReferenceResourcesCount() {
		return getService().getReferenceResourcesCount();
	}

	/**
	* Updates the reference resource in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param referenceResource the reference resource
	* @return the reference resource that was updated
	*/
	public static hu.webtown.liferay.portlet.reference.model.ReferenceResource updateReferenceResource(
		hu.webtown.liferay.portlet.reference.model.ReferenceResource referenceResource) {
		return getService().updateReferenceResource(referenceResource);
	}

	public static ReferenceResourceLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ReferenceResourceLocalService, ReferenceResourceLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ReferenceResourceLocalService.class);

		ServiceTracker<ReferenceResourceLocalService, ReferenceResourceLocalService> serviceTracker =
			new ServiceTracker<ReferenceResourceLocalService, ReferenceResourceLocalService>(bundle.getBundleContext(),
				ReferenceResourceLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}