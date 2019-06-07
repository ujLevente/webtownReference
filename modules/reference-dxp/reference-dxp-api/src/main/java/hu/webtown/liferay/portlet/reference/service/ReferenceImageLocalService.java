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

import hu.webtown.liferay.portlet.reference.model.ReferenceImage;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for ReferenceImage. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see ReferenceImageLocalServiceUtil
 * @see hu.webtown.liferay.portlet.reference.service.base.ReferenceImageLocalServiceBaseImpl
 * @see hu.webtown.liferay.portlet.reference.service.impl.ReferenceImageLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ReferenceImageLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ReferenceImageLocalServiceUtil} to access the reference image local service. Add custom service methods to {@link hu.webtown.liferay.portlet.reference.service.impl.ReferenceImageLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public ReferenceImage addReferenceImage(long groupId, String referenceId,
		double version, int referenceImageType, String image);

	/**
	* Adds the reference image to the database. Also notifies the appropriate model listeners.
	*
	* @param referenceImage the reference image
	* @return the reference image that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ReferenceImage addReferenceImage(ReferenceImage referenceImage);

	/**
	* Creates a new reference image with the primary key. Does not add the reference image to the database.
	*
	* @param referenceImageId the primary key for the new reference image
	* @return the new reference image
	*/
	@Transactional(enabled = false)
	public ReferenceImage createReferenceImage(long referenceImageId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the reference image with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param referenceImageId the primary key of the reference image
	* @return the reference image that was removed
	* @throws PortalException if a reference image with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ReferenceImage deleteReferenceImage(long referenceImageId)
		throws PortalException;

	/**
	* Deletes the reference image from the database. Also notifies the appropriate model listeners.
	*
	* @param referenceImage the reference image
	* @return the reference image that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ReferenceImage deleteReferenceImage(ReferenceImage referenceImage);

	public void deleteReferenceImages(long groupId, String referenceId,
		double version);

	public void deleteReferenceImages(long groupId, String referenceId,
		double version, int referenceImageType);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link hu.webtown.liferay.portlet.reference.model.impl.ReferenceImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link hu.webtown.liferay.portlet.reference.model.impl.ReferenceImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public ReferenceImage fetchReferenceImage(long referenceImageId);

	/**
	* Returns the reference image matching the UUID and group.
	*
	* @param uuid the reference image's UUID
	* @param groupId the primary key of the group
	* @return the matching reference image, or <code>null</code> if a matching reference image could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ReferenceImage fetchReferenceImageByUuidAndGroupId(String uuid,
		long groupId);

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
	* Returns the reference image with the primary key.
	*
	* @param referenceImageId the primary key of the reference image
	* @return the reference image
	* @throws PortalException if a reference image with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ReferenceImage getReferenceImage(long referenceImageId)
		throws PortalException;

	/**
	* Returns the reference image matching the UUID and group.
	*
	* @param uuid the reference image's UUID
	* @param groupId the primary key of the group
	* @return the matching reference image
	* @throws PortalException if a matching reference image could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ReferenceImage getReferenceImageByUuidAndGroupId(String uuid,
		long groupId) throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ReferenceImage> getReferenceImages(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ReferenceImage> getReferenceImages(long groupId,
		String referenceId, double version);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ReferenceImage> getReferenceImages(long groupId,
		String referenceId, double version, int referenceImageType);

	/**
	* Returns all the reference images matching the UUID and company.
	*
	* @param uuid the UUID of the reference images
	* @param companyId the primary key of the company
	* @return the matching reference images, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ReferenceImage> getReferenceImagesByUuidAndCompanyId(
		String uuid, long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ReferenceImage> getReferenceImagesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ReferenceImage> orderByComparator);

	/**
	* Returns the number of reference images.
	*
	* @return the number of reference images
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getReferenceImagesCount();

	/**
	* Updates the reference image in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param referenceImage the reference image
	* @return the reference image that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ReferenceImage updateReferenceImage(ReferenceImage referenceImage);
}