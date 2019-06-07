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

package hu.webtown.liferay.portlet.reference.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import hu.webtown.liferay.portlet.reference.model.ReferenceImage;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the reference image service. This utility wraps {@link hu.webtown.liferay.portlet.reference.service.persistence.impl.ReferenceImagePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReferenceImagePersistence
 * @see hu.webtown.liferay.portlet.reference.service.persistence.impl.ReferenceImagePersistenceImpl
 * @generated
 */
@ProviderType
public class ReferenceImageUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(ReferenceImage referenceImage) {
		getPersistence().clearCache(referenceImage);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ReferenceImage> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ReferenceImage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ReferenceImage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ReferenceImage> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ReferenceImage update(ReferenceImage referenceImage) {
		return getPersistence().update(referenceImage);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ReferenceImage update(ReferenceImage referenceImage,
		ServiceContext serviceContext) {
		return getPersistence().update(referenceImage, serviceContext);
	}

	/**
	* Returns all the reference images where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching reference images
	*/
	public static List<ReferenceImage> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the reference images where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of reference images
	* @param end the upper bound of the range of reference images (not inclusive)
	* @return the range of matching reference images
	*/
	public static List<ReferenceImage> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the reference images where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of reference images
	* @param end the upper bound of the range of reference images (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching reference images
	*/
	public static List<ReferenceImage> findByUuid(String uuid, int start,
		int end, OrderByComparator<ReferenceImage> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the reference images where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of reference images
	* @param end the upper bound of the range of reference images (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching reference images
	*/
	public static List<ReferenceImage> findByUuid(String uuid, int start,
		int end, OrderByComparator<ReferenceImage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first reference image in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference image
	* @throws NoSuchImageException if a matching reference image could not be found
	*/
	public static ReferenceImage findByUuid_First(String uuid,
		OrderByComparator<ReferenceImage> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchImageException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first reference image in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference image, or <code>null</code> if a matching reference image could not be found
	*/
	public static ReferenceImage fetchByUuid_First(String uuid,
		OrderByComparator<ReferenceImage> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last reference image in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference image
	* @throws NoSuchImageException if a matching reference image could not be found
	*/
	public static ReferenceImage findByUuid_Last(String uuid,
		OrderByComparator<ReferenceImage> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchImageException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last reference image in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference image, or <code>null</code> if a matching reference image could not be found
	*/
	public static ReferenceImage fetchByUuid_Last(String uuid,
		OrderByComparator<ReferenceImage> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the reference images before and after the current reference image in the ordered set where uuid = &#63;.
	*
	* @param referenceImageId the primary key of the current reference image
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference image
	* @throws NoSuchImageException if a reference image with the primary key could not be found
	*/
	public static ReferenceImage[] findByUuid_PrevAndNext(
		long referenceImageId, String uuid,
		OrderByComparator<ReferenceImage> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchImageException {
		return getPersistence()
				   .findByUuid_PrevAndNext(referenceImageId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the reference images where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of reference images where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching reference images
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the reference image where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchImageException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching reference image
	* @throws NoSuchImageException if a matching reference image could not be found
	*/
	public static ReferenceImage findByUUID_G(String uuid, long groupId)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchImageException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the reference image where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching reference image, or <code>null</code> if a matching reference image could not be found
	*/
	public static ReferenceImage fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the reference image where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching reference image, or <code>null</code> if a matching reference image could not be found
	*/
	public static ReferenceImage fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the reference image where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the reference image that was removed
	*/
	public static ReferenceImage removeByUUID_G(String uuid, long groupId)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchImageException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of reference images where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching reference images
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the reference images where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching reference images
	*/
	public static List<ReferenceImage> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the reference images where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of reference images
	* @param end the upper bound of the range of reference images (not inclusive)
	* @return the range of matching reference images
	*/
	public static List<ReferenceImage> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the reference images where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of reference images
	* @param end the upper bound of the range of reference images (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching reference images
	*/
	public static List<ReferenceImage> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<ReferenceImage> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the reference images where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of reference images
	* @param end the upper bound of the range of reference images (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching reference images
	*/
	public static List<ReferenceImage> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<ReferenceImage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first reference image in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference image
	* @throws NoSuchImageException if a matching reference image could not be found
	*/
	public static ReferenceImage findByUuid_C_First(String uuid,
		long companyId, OrderByComparator<ReferenceImage> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchImageException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first reference image in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference image, or <code>null</code> if a matching reference image could not be found
	*/
	public static ReferenceImage fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<ReferenceImage> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last reference image in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference image
	* @throws NoSuchImageException if a matching reference image could not be found
	*/
	public static ReferenceImage findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ReferenceImage> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchImageException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last reference image in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference image, or <code>null</code> if a matching reference image could not be found
	*/
	public static ReferenceImage fetchByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<ReferenceImage> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the reference images before and after the current reference image in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param referenceImageId the primary key of the current reference image
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference image
	* @throws NoSuchImageException if a reference image with the primary key could not be found
	*/
	public static ReferenceImage[] findByUuid_C_PrevAndNext(
		long referenceImageId, String uuid, long companyId,
		OrderByComparator<ReferenceImage> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchImageException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(referenceImageId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the reference images where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of reference images where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching reference images
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the reference images where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching reference images
	*/
	public static List<ReferenceImage> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the reference images where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of reference images
	* @param end the upper bound of the range of reference images (not inclusive)
	* @return the range of matching reference images
	*/
	public static List<ReferenceImage> findByGroupId(long groupId, int start,
		int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the reference images where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of reference images
	* @param end the upper bound of the range of reference images (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching reference images
	*/
	public static List<ReferenceImage> findByGroupId(long groupId, int start,
		int end, OrderByComparator<ReferenceImage> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the reference images where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of reference images
	* @param end the upper bound of the range of reference images (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching reference images
	*/
	public static List<ReferenceImage> findByGroupId(long groupId, int start,
		int end, OrderByComparator<ReferenceImage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first reference image in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference image
	* @throws NoSuchImageException if a matching reference image could not be found
	*/
	public static ReferenceImage findByGroupId_First(long groupId,
		OrderByComparator<ReferenceImage> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchImageException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first reference image in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference image, or <code>null</code> if a matching reference image could not be found
	*/
	public static ReferenceImage fetchByGroupId_First(long groupId,
		OrderByComparator<ReferenceImage> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last reference image in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference image
	* @throws NoSuchImageException if a matching reference image could not be found
	*/
	public static ReferenceImage findByGroupId_Last(long groupId,
		OrderByComparator<ReferenceImage> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchImageException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last reference image in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference image, or <code>null</code> if a matching reference image could not be found
	*/
	public static ReferenceImage fetchByGroupId_Last(long groupId,
		OrderByComparator<ReferenceImage> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the reference images before and after the current reference image in the ordered set where groupId = &#63;.
	*
	* @param referenceImageId the primary key of the current reference image
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference image
	* @throws NoSuchImageException if a reference image with the primary key could not be found
	*/
	public static ReferenceImage[] findByGroupId_PrevAndNext(
		long referenceImageId, long groupId,
		OrderByComparator<ReferenceImage> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchImageException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(referenceImageId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the reference images where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of reference images where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching reference images
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the reference images where groupId = &#63; and referenceId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @return the matching reference images
	*/
	public static List<ReferenceImage> findByG_R_V(long groupId,
		String referenceId, double version) {
		return getPersistence().findByG_R_V(groupId, referenceId, version);
	}

	/**
	* Returns a range of all the reference images where groupId = &#63; and referenceId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @param start the lower bound of the range of reference images
	* @param end the upper bound of the range of reference images (not inclusive)
	* @return the range of matching reference images
	*/
	public static List<ReferenceImage> findByG_R_V(long groupId,
		String referenceId, double version, int start, int end) {
		return getPersistence()
				   .findByG_R_V(groupId, referenceId, version, start, end);
	}

	/**
	* Returns an ordered range of all the reference images where groupId = &#63; and referenceId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @param start the lower bound of the range of reference images
	* @param end the upper bound of the range of reference images (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching reference images
	*/
	public static List<ReferenceImage> findByG_R_V(long groupId,
		String referenceId, double version, int start, int end,
		OrderByComparator<ReferenceImage> orderByComparator) {
		return getPersistence()
				   .findByG_R_V(groupId, referenceId, version, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the reference images where groupId = &#63; and referenceId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @param start the lower bound of the range of reference images
	* @param end the upper bound of the range of reference images (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching reference images
	*/
	public static List<ReferenceImage> findByG_R_V(long groupId,
		String referenceId, double version, int start, int end,
		OrderByComparator<ReferenceImage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_R_V(groupId, referenceId, version, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first reference image in the ordered set where groupId = &#63; and referenceId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference image
	* @throws NoSuchImageException if a matching reference image could not be found
	*/
	public static ReferenceImage findByG_R_V_First(long groupId,
		String referenceId, double version,
		OrderByComparator<ReferenceImage> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchImageException {
		return getPersistence()
				   .findByG_R_V_First(groupId, referenceId, version,
			orderByComparator);
	}

	/**
	* Returns the first reference image in the ordered set where groupId = &#63; and referenceId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference image, or <code>null</code> if a matching reference image could not be found
	*/
	public static ReferenceImage fetchByG_R_V_First(long groupId,
		String referenceId, double version,
		OrderByComparator<ReferenceImage> orderByComparator) {
		return getPersistence()
				   .fetchByG_R_V_First(groupId, referenceId, version,
			orderByComparator);
	}

	/**
	* Returns the last reference image in the ordered set where groupId = &#63; and referenceId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference image
	* @throws NoSuchImageException if a matching reference image could not be found
	*/
	public static ReferenceImage findByG_R_V_Last(long groupId,
		String referenceId, double version,
		OrderByComparator<ReferenceImage> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchImageException {
		return getPersistence()
				   .findByG_R_V_Last(groupId, referenceId, version,
			orderByComparator);
	}

	/**
	* Returns the last reference image in the ordered set where groupId = &#63; and referenceId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference image, or <code>null</code> if a matching reference image could not be found
	*/
	public static ReferenceImage fetchByG_R_V_Last(long groupId,
		String referenceId, double version,
		OrderByComparator<ReferenceImage> orderByComparator) {
		return getPersistence()
				   .fetchByG_R_V_Last(groupId, referenceId, version,
			orderByComparator);
	}

	/**
	* Returns the reference images before and after the current reference image in the ordered set where groupId = &#63; and referenceId = &#63; and version = &#63;.
	*
	* @param referenceImageId the primary key of the current reference image
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference image
	* @throws NoSuchImageException if a reference image with the primary key could not be found
	*/
	public static ReferenceImage[] findByG_R_V_PrevAndNext(
		long referenceImageId, long groupId, String referenceId,
		double version, OrderByComparator<ReferenceImage> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchImageException {
		return getPersistence()
				   .findByG_R_V_PrevAndNext(referenceImageId, groupId,
			referenceId, version, orderByComparator);
	}

	/**
	* Removes all the reference images where groupId = &#63; and referenceId = &#63; and version = &#63; from the database.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	*/
	public static void removeByG_R_V(long groupId, String referenceId,
		double version) {
		getPersistence().removeByG_R_V(groupId, referenceId, version);
	}

	/**
	* Returns the number of reference images where groupId = &#63; and referenceId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @return the number of matching reference images
	*/
	public static int countByG_R_V(long groupId, String referenceId,
		double version) {
		return getPersistence().countByG_R_V(groupId, referenceId, version);
	}

	/**
	* Returns all the reference images where groupId = &#63; and referenceId = &#63; and version = &#63; and imageType = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @param imageType the image type
	* @return the matching reference images
	*/
	public static List<ReferenceImage> findByG_R_V_I(long groupId,
		String referenceId, double version, int imageType) {
		return getPersistence()
				   .findByG_R_V_I(groupId, referenceId, version, imageType);
	}

	/**
	* Returns a range of all the reference images where groupId = &#63; and referenceId = &#63; and version = &#63; and imageType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @param imageType the image type
	* @param start the lower bound of the range of reference images
	* @param end the upper bound of the range of reference images (not inclusive)
	* @return the range of matching reference images
	*/
	public static List<ReferenceImage> findByG_R_V_I(long groupId,
		String referenceId, double version, int imageType, int start, int end) {
		return getPersistence()
				   .findByG_R_V_I(groupId, referenceId, version, imageType,
			start, end);
	}

	/**
	* Returns an ordered range of all the reference images where groupId = &#63; and referenceId = &#63; and version = &#63; and imageType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @param imageType the image type
	* @param start the lower bound of the range of reference images
	* @param end the upper bound of the range of reference images (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching reference images
	*/
	public static List<ReferenceImage> findByG_R_V_I(long groupId,
		String referenceId, double version, int imageType, int start, int end,
		OrderByComparator<ReferenceImage> orderByComparator) {
		return getPersistence()
				   .findByG_R_V_I(groupId, referenceId, version, imageType,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the reference images where groupId = &#63; and referenceId = &#63; and version = &#63; and imageType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @param imageType the image type
	* @param start the lower bound of the range of reference images
	* @param end the upper bound of the range of reference images (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching reference images
	*/
	public static List<ReferenceImage> findByG_R_V_I(long groupId,
		String referenceId, double version, int imageType, int start, int end,
		OrderByComparator<ReferenceImage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_R_V_I(groupId, referenceId, version, imageType,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first reference image in the ordered set where groupId = &#63; and referenceId = &#63; and version = &#63; and imageType = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @param imageType the image type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference image
	* @throws NoSuchImageException if a matching reference image could not be found
	*/
	public static ReferenceImage findByG_R_V_I_First(long groupId,
		String referenceId, double version, int imageType,
		OrderByComparator<ReferenceImage> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchImageException {
		return getPersistence()
				   .findByG_R_V_I_First(groupId, referenceId, version,
			imageType, orderByComparator);
	}

	/**
	* Returns the first reference image in the ordered set where groupId = &#63; and referenceId = &#63; and version = &#63; and imageType = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @param imageType the image type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference image, or <code>null</code> if a matching reference image could not be found
	*/
	public static ReferenceImage fetchByG_R_V_I_First(long groupId,
		String referenceId, double version, int imageType,
		OrderByComparator<ReferenceImage> orderByComparator) {
		return getPersistence()
				   .fetchByG_R_V_I_First(groupId, referenceId, version,
			imageType, orderByComparator);
	}

	/**
	* Returns the last reference image in the ordered set where groupId = &#63; and referenceId = &#63; and version = &#63; and imageType = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @param imageType the image type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference image
	* @throws NoSuchImageException if a matching reference image could not be found
	*/
	public static ReferenceImage findByG_R_V_I_Last(long groupId,
		String referenceId, double version, int imageType,
		OrderByComparator<ReferenceImage> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchImageException {
		return getPersistence()
				   .findByG_R_V_I_Last(groupId, referenceId, version,
			imageType, orderByComparator);
	}

	/**
	* Returns the last reference image in the ordered set where groupId = &#63; and referenceId = &#63; and version = &#63; and imageType = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @param imageType the image type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference image, or <code>null</code> if a matching reference image could not be found
	*/
	public static ReferenceImage fetchByG_R_V_I_Last(long groupId,
		String referenceId, double version, int imageType,
		OrderByComparator<ReferenceImage> orderByComparator) {
		return getPersistence()
				   .fetchByG_R_V_I_Last(groupId, referenceId, version,
			imageType, orderByComparator);
	}

	/**
	* Returns the reference images before and after the current reference image in the ordered set where groupId = &#63; and referenceId = &#63; and version = &#63; and imageType = &#63;.
	*
	* @param referenceImageId the primary key of the current reference image
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @param imageType the image type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference image
	* @throws NoSuchImageException if a reference image with the primary key could not be found
	*/
	public static ReferenceImage[] findByG_R_V_I_PrevAndNext(
		long referenceImageId, long groupId, String referenceId,
		double version, int imageType,
		OrderByComparator<ReferenceImage> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchImageException {
		return getPersistence()
				   .findByG_R_V_I_PrevAndNext(referenceImageId, groupId,
			referenceId, version, imageType, orderByComparator);
	}

	/**
	* Removes all the reference images where groupId = &#63; and referenceId = &#63; and version = &#63; and imageType = &#63; from the database.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @param imageType the image type
	*/
	public static void removeByG_R_V_I(long groupId, String referenceId,
		double version, int imageType) {
		getPersistence()
			.removeByG_R_V_I(groupId, referenceId, version, imageType);
	}

	/**
	* Returns the number of reference images where groupId = &#63; and referenceId = &#63; and version = &#63; and imageType = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @param imageType the image type
	* @return the number of matching reference images
	*/
	public static int countByG_R_V_I(long groupId, String referenceId,
		double version, int imageType) {
		return getPersistence()
				   .countByG_R_V_I(groupId, referenceId, version, imageType);
	}

	/**
	* Caches the reference image in the entity cache if it is enabled.
	*
	* @param referenceImage the reference image
	*/
	public static void cacheResult(ReferenceImage referenceImage) {
		getPersistence().cacheResult(referenceImage);
	}

	/**
	* Caches the reference images in the entity cache if it is enabled.
	*
	* @param referenceImages the reference images
	*/
	public static void cacheResult(List<ReferenceImage> referenceImages) {
		getPersistence().cacheResult(referenceImages);
	}

	/**
	* Creates a new reference image with the primary key. Does not add the reference image to the database.
	*
	* @param referenceImageId the primary key for the new reference image
	* @return the new reference image
	*/
	public static ReferenceImage create(long referenceImageId) {
		return getPersistence().create(referenceImageId);
	}

	/**
	* Removes the reference image with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param referenceImageId the primary key of the reference image
	* @return the reference image that was removed
	* @throws NoSuchImageException if a reference image with the primary key could not be found
	*/
	public static ReferenceImage remove(long referenceImageId)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchImageException {
		return getPersistence().remove(referenceImageId);
	}

	public static ReferenceImage updateImpl(ReferenceImage referenceImage) {
		return getPersistence().updateImpl(referenceImage);
	}

	/**
	* Returns the reference image with the primary key or throws a {@link NoSuchImageException} if it could not be found.
	*
	* @param referenceImageId the primary key of the reference image
	* @return the reference image
	* @throws NoSuchImageException if a reference image with the primary key could not be found
	*/
	public static ReferenceImage findByPrimaryKey(long referenceImageId)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchImageException {
		return getPersistence().findByPrimaryKey(referenceImageId);
	}

	/**
	* Returns the reference image with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param referenceImageId the primary key of the reference image
	* @return the reference image, or <code>null</code> if a reference image with the primary key could not be found
	*/
	public static ReferenceImage fetchByPrimaryKey(long referenceImageId) {
		return getPersistence().fetchByPrimaryKey(referenceImageId);
	}

	public static java.util.Map<java.io.Serializable, ReferenceImage> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the reference images.
	*
	* @return the reference images
	*/
	public static List<ReferenceImage> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the reference images.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of reference images
	* @param end the upper bound of the range of reference images (not inclusive)
	* @return the range of reference images
	*/
	public static List<ReferenceImage> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the reference images.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of reference images
	* @param end the upper bound of the range of reference images (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of reference images
	*/
	public static List<ReferenceImage> findAll(int start, int end,
		OrderByComparator<ReferenceImage> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the reference images.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of reference images
	* @param end the upper bound of the range of reference images (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of reference images
	*/
	public static List<ReferenceImage> findAll(int start, int end,
		OrderByComparator<ReferenceImage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the reference images from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of reference images.
	*
	* @return the number of reference images
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ReferenceImagePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ReferenceImagePersistence, ReferenceImagePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ReferenceImagePersistence.class);

		ServiceTracker<ReferenceImagePersistence, ReferenceImagePersistence> serviceTracker =
			new ServiceTracker<ReferenceImagePersistence, ReferenceImagePersistence>(bundle.getBundleContext(),
				ReferenceImagePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}