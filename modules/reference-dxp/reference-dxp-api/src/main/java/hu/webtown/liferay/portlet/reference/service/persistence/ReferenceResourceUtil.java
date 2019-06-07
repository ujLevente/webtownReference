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

import hu.webtown.liferay.portlet.reference.model.ReferenceResource;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the reference resource service. This utility wraps {@link hu.webtown.liferay.portlet.reference.service.persistence.impl.ReferenceResourcePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReferenceResourcePersistence
 * @see hu.webtown.liferay.portlet.reference.service.persistence.impl.ReferenceResourcePersistenceImpl
 * @generated
 */
@ProviderType
public class ReferenceResourceUtil {
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
	public static void clearCache(ReferenceResource referenceResource) {
		getPersistence().clearCache(referenceResource);
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
	public static List<ReferenceResource> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ReferenceResource> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ReferenceResource> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ReferenceResource> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ReferenceResource update(ReferenceResource referenceResource) {
		return getPersistence().update(referenceResource);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ReferenceResource update(
		ReferenceResource referenceResource, ServiceContext serviceContext) {
		return getPersistence().update(referenceResource, serviceContext);
	}

	/**
	* Returns all the reference resources where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching reference resources
	*/
	public static List<ReferenceResource> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the reference resources where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of reference resources
	* @param end the upper bound of the range of reference resources (not inclusive)
	* @return the range of matching reference resources
	*/
	public static List<ReferenceResource> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the reference resources where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of reference resources
	* @param end the upper bound of the range of reference resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching reference resources
	*/
	public static List<ReferenceResource> findByUuid(String uuid, int start,
		int end, OrderByComparator<ReferenceResource> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the reference resources where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of reference resources
	* @param end the upper bound of the range of reference resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching reference resources
	*/
	public static List<ReferenceResource> findByUuid(String uuid, int start,
		int end, OrderByComparator<ReferenceResource> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first reference resource in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference resource
	* @throws NoSuchResourceException if a matching reference resource could not be found
	*/
	public static ReferenceResource findByUuid_First(String uuid,
		OrderByComparator<ReferenceResource> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchResourceException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first reference resource in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference resource, or <code>null</code> if a matching reference resource could not be found
	*/
	public static ReferenceResource fetchByUuid_First(String uuid,
		OrderByComparator<ReferenceResource> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last reference resource in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference resource
	* @throws NoSuchResourceException if a matching reference resource could not be found
	*/
	public static ReferenceResource findByUuid_Last(String uuid,
		OrderByComparator<ReferenceResource> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchResourceException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last reference resource in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference resource, or <code>null</code> if a matching reference resource could not be found
	*/
	public static ReferenceResource fetchByUuid_Last(String uuid,
		OrderByComparator<ReferenceResource> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the reference resources before and after the current reference resource in the ordered set where uuid = &#63;.
	*
	* @param resourcePrimKey the primary key of the current reference resource
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference resource
	* @throws NoSuchResourceException if a reference resource with the primary key could not be found
	*/
	public static ReferenceResource[] findByUuid_PrevAndNext(
		long resourcePrimKey, String uuid,
		OrderByComparator<ReferenceResource> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchResourceException {
		return getPersistence()
				   .findByUuid_PrevAndNext(resourcePrimKey, uuid,
			orderByComparator);
	}

	/**
	* Removes all the reference resources where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of reference resources where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching reference resources
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the reference resource where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchResourceException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching reference resource
	* @throws NoSuchResourceException if a matching reference resource could not be found
	*/
	public static ReferenceResource findByUUID_G(String uuid, long groupId)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchResourceException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the reference resource where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching reference resource, or <code>null</code> if a matching reference resource could not be found
	*/
	public static ReferenceResource fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the reference resource where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching reference resource, or <code>null</code> if a matching reference resource could not be found
	*/
	public static ReferenceResource fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the reference resource where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the reference resource that was removed
	*/
	public static ReferenceResource removeByUUID_G(String uuid, long groupId)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchResourceException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of reference resources where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching reference resources
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the reference resources where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching reference resources
	*/
	public static List<ReferenceResource> findByUuid_C(String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the reference resources where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of reference resources
	* @param end the upper bound of the range of reference resources (not inclusive)
	* @return the range of matching reference resources
	*/
	public static List<ReferenceResource> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the reference resources where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of reference resources
	* @param end the upper bound of the range of reference resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching reference resources
	*/
	public static List<ReferenceResource> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<ReferenceResource> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the reference resources where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of reference resources
	* @param end the upper bound of the range of reference resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching reference resources
	*/
	public static List<ReferenceResource> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<ReferenceResource> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first reference resource in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference resource
	* @throws NoSuchResourceException if a matching reference resource could not be found
	*/
	public static ReferenceResource findByUuid_C_First(String uuid,
		long companyId, OrderByComparator<ReferenceResource> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchResourceException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first reference resource in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference resource, or <code>null</code> if a matching reference resource could not be found
	*/
	public static ReferenceResource fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<ReferenceResource> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last reference resource in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference resource
	* @throws NoSuchResourceException if a matching reference resource could not be found
	*/
	public static ReferenceResource findByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<ReferenceResource> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchResourceException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last reference resource in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference resource, or <code>null</code> if a matching reference resource could not be found
	*/
	public static ReferenceResource fetchByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<ReferenceResource> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the reference resources before and after the current reference resource in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param resourcePrimKey the primary key of the current reference resource
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference resource
	* @throws NoSuchResourceException if a reference resource with the primary key could not be found
	*/
	public static ReferenceResource[] findByUuid_C_PrevAndNext(
		long resourcePrimKey, String uuid, long companyId,
		OrderByComparator<ReferenceResource> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchResourceException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(resourcePrimKey, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the reference resources where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of reference resources where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching reference resources
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the reference resources where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching reference resources
	*/
	public static List<ReferenceResource> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the reference resources where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of reference resources
	* @param end the upper bound of the range of reference resources (not inclusive)
	* @return the range of matching reference resources
	*/
	public static List<ReferenceResource> findByGroupId(long groupId,
		int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the reference resources where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of reference resources
	* @param end the upper bound of the range of reference resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching reference resources
	*/
	public static List<ReferenceResource> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<ReferenceResource> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the reference resources where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of reference resources
	* @param end the upper bound of the range of reference resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching reference resources
	*/
	public static List<ReferenceResource> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<ReferenceResource> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first reference resource in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference resource
	* @throws NoSuchResourceException if a matching reference resource could not be found
	*/
	public static ReferenceResource findByGroupId_First(long groupId,
		OrderByComparator<ReferenceResource> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchResourceException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first reference resource in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference resource, or <code>null</code> if a matching reference resource could not be found
	*/
	public static ReferenceResource fetchByGroupId_First(long groupId,
		OrderByComparator<ReferenceResource> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last reference resource in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference resource
	* @throws NoSuchResourceException if a matching reference resource could not be found
	*/
	public static ReferenceResource findByGroupId_Last(long groupId,
		OrderByComparator<ReferenceResource> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchResourceException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last reference resource in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference resource, or <code>null</code> if a matching reference resource could not be found
	*/
	public static ReferenceResource fetchByGroupId_Last(long groupId,
		OrderByComparator<ReferenceResource> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the reference resources before and after the current reference resource in the ordered set where groupId = &#63;.
	*
	* @param resourcePrimKey the primary key of the current reference resource
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference resource
	* @throws NoSuchResourceException if a reference resource with the primary key could not be found
	*/
	public static ReferenceResource[] findByGroupId_PrevAndNext(
		long resourcePrimKey, long groupId,
		OrderByComparator<ReferenceResource> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchResourceException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(resourcePrimKey, groupId,
			orderByComparator);
	}

	/**
	* Removes all the reference resources where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of reference resources where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching reference resources
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns the reference resource where groupId = &#63; and referenceId = &#63; or throws a {@link NoSuchResourceException} if it could not be found.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @return the matching reference resource
	* @throws NoSuchResourceException if a matching reference resource could not be found
	*/
	public static ReferenceResource findByG_R(long groupId, String referenceId)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchResourceException {
		return getPersistence().findByG_R(groupId, referenceId);
	}

	/**
	* Returns the reference resource where groupId = &#63; and referenceId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @return the matching reference resource, or <code>null</code> if a matching reference resource could not be found
	*/
	public static ReferenceResource fetchByG_R(long groupId, String referenceId) {
		return getPersistence().fetchByG_R(groupId, referenceId);
	}

	/**
	* Returns the reference resource where groupId = &#63; and referenceId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching reference resource, or <code>null</code> if a matching reference resource could not be found
	*/
	public static ReferenceResource fetchByG_R(long groupId,
		String referenceId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_R(groupId, referenceId, retrieveFromCache);
	}

	/**
	* Removes the reference resource where groupId = &#63; and referenceId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @return the reference resource that was removed
	*/
	public static ReferenceResource removeByG_R(long groupId, String referenceId)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchResourceException {
		return getPersistence().removeByG_R(groupId, referenceId);
	}

	/**
	* Returns the number of reference resources where groupId = &#63; and referenceId = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @return the number of matching reference resources
	*/
	public static int countByG_R(long groupId, String referenceId) {
		return getPersistence().countByG_R(groupId, referenceId);
	}

	/**
	* Caches the reference resource in the entity cache if it is enabled.
	*
	* @param referenceResource the reference resource
	*/
	public static void cacheResult(ReferenceResource referenceResource) {
		getPersistence().cacheResult(referenceResource);
	}

	/**
	* Caches the reference resources in the entity cache if it is enabled.
	*
	* @param referenceResources the reference resources
	*/
	public static void cacheResult(List<ReferenceResource> referenceResources) {
		getPersistence().cacheResult(referenceResources);
	}

	/**
	* Creates a new reference resource with the primary key. Does not add the reference resource to the database.
	*
	* @param resourcePrimKey the primary key for the new reference resource
	* @return the new reference resource
	*/
	public static ReferenceResource create(long resourcePrimKey) {
		return getPersistence().create(resourcePrimKey);
	}

	/**
	* Removes the reference resource with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param resourcePrimKey the primary key of the reference resource
	* @return the reference resource that was removed
	* @throws NoSuchResourceException if a reference resource with the primary key could not be found
	*/
	public static ReferenceResource remove(long resourcePrimKey)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchResourceException {
		return getPersistence().remove(resourcePrimKey);
	}

	public static ReferenceResource updateImpl(
		ReferenceResource referenceResource) {
		return getPersistence().updateImpl(referenceResource);
	}

	/**
	* Returns the reference resource with the primary key or throws a {@link NoSuchResourceException} if it could not be found.
	*
	* @param resourcePrimKey the primary key of the reference resource
	* @return the reference resource
	* @throws NoSuchResourceException if a reference resource with the primary key could not be found
	*/
	public static ReferenceResource findByPrimaryKey(long resourcePrimKey)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchResourceException {
		return getPersistence().findByPrimaryKey(resourcePrimKey);
	}

	/**
	* Returns the reference resource with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param resourcePrimKey the primary key of the reference resource
	* @return the reference resource, or <code>null</code> if a reference resource with the primary key could not be found
	*/
	public static ReferenceResource fetchByPrimaryKey(long resourcePrimKey) {
		return getPersistence().fetchByPrimaryKey(resourcePrimKey);
	}

	public static java.util.Map<java.io.Serializable, ReferenceResource> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the reference resources.
	*
	* @return the reference resources
	*/
	public static List<ReferenceResource> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the reference resources.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of reference resources
	* @param end the upper bound of the range of reference resources (not inclusive)
	* @return the range of reference resources
	*/
	public static List<ReferenceResource> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the reference resources.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of reference resources
	* @param end the upper bound of the range of reference resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of reference resources
	*/
	public static List<ReferenceResource> findAll(int start, int end,
		OrderByComparator<ReferenceResource> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the reference resources.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of reference resources
	* @param end the upper bound of the range of reference resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of reference resources
	*/
	public static List<ReferenceResource> findAll(int start, int end,
		OrderByComparator<ReferenceResource> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the reference resources from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of reference resources.
	*
	* @return the number of reference resources
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ReferenceResourcePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ReferenceResourcePersistence, ReferenceResourcePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ReferenceResourcePersistence.class);

		ServiceTracker<ReferenceResourcePersistence, ReferenceResourcePersistence> serviceTracker =
			new ServiceTracker<ReferenceResourcePersistence, ReferenceResourcePersistence>(bundle.getBundleContext(),
				ReferenceResourcePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}