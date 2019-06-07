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

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import hu.webtown.liferay.portlet.reference.model.ReferenceResource;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for ReferenceResource. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see ReferenceResourceLocalServiceUtil
 * @see hu.webtown.liferay.portlet.reference.service.base.ReferenceResourceLocalServiceBaseImpl
 * @see hu.webtown.liferay.portlet.reference.service.impl.ReferenceResourceLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ReferenceResourceLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ReferenceResourceLocalServiceUtil} to access the reference resource local service. Add custom service methods to {@link hu.webtown.liferay.portlet.reference.service.impl.ReferenceResourceLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the reference resource to the database. Also notifies the appropriate model listeners.
	*
	* @param referenceResource the reference resource
	* @return the reference resource that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ReferenceResource addReferenceResource(
		ReferenceResource referenceResource);

	/**
	* Creates a new reference resource with the primary key. Does not add the reference resource to the database.
	*
	* @param resourcePrimKey the primary key for the new reference resource
	* @return the new reference resource
	*/
	@Transactional(enabled = false)
	public ReferenceResource createReferenceResource(long resourcePrimKey);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the reference resource with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param resourcePrimKey the primary key of the reference resource
	* @return the reference resource that was removed
	* @throws PortalException if a reference resource with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ReferenceResource deleteReferenceResource(long resourcePrimKey)
		throws PortalException;

	public void deleteReferenceResource(long groupId, String referenceId)
		throws PortalException;

	/**
	* Deletes the reference resource from the database. Also notifies the appropriate model listeners.
	*
	* @param referenceResource the reference resource
	* @return the reference resource that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ReferenceResource deleteReferenceResource(
		ReferenceResource referenceResource);

	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ReferenceResource fetchReferenceResource(long resourcePrimKey);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ReferenceResource fetchReferenceResource(long groupId,
		String referenceId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ReferenceResource fetchReferenceResource(String uuid, long groupId);

	/**
	* Returns the reference resource matching the UUID and group.
	*
	* @param uuid the reference resource's UUID
	* @param groupId the primary key of the group
	* @return the matching reference resource, or <code>null</code> if a matching reference resource could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ReferenceResource fetchReferenceResourceByUuidAndGroupId(
		String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the reference resource with the primary key.
	*
	* @param resourcePrimKey the primary key of the reference resource
	* @return the reference resource
	* @throws PortalException if a reference resource with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ReferenceResource getReferenceResource(long resourcePrimKey)
		throws PortalException;

	/**
	* Returns the reference resource matching the UUID and group.
	*
	* @param uuid the reference resource's UUID
	* @param groupId the primary key of the group
	* @return the matching reference resource
	* @throws PortalException if a matching reference resource could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ReferenceResource getReferenceResourceByUuidAndGroupId(String uuid,
		long groupId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getReferenceResourcePrimKey(long groupId, String referenceId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getReferenceResourcePrimKey(String uuid, long groupId,
		String referenceId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ReferenceResource> getReferenceResources(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ReferenceResource> getReferenceResources(long groupId);

	/**
	* Returns all the reference resources matching the UUID and company.
	*
	* @param uuid the UUID of the reference resources
	* @param companyId the primary key of the company
	* @return the matching reference resources, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ReferenceResource> getReferenceResourcesByUuidAndCompanyId(
		String uuid, long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ReferenceResource> getReferenceResourcesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ReferenceResource> orderByComparator);

	/**
	* Returns the number of reference resources.
	*
	* @return the number of reference resources
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getReferenceResourcesCount();

	/**
	* Updates the reference resource in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param referenceResource the reference resource
	* @return the reference resource that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ReferenceResource updateReferenceResource(
		ReferenceResource referenceResource);
}