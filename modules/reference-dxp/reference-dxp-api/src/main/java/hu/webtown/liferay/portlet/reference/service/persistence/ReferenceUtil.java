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

import hu.webtown.liferay.portlet.reference.model.Reference;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the reference service. This utility wraps {@link hu.webtown.liferay.portlet.reference.service.persistence.impl.ReferencePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReferencePersistence
 * @see hu.webtown.liferay.portlet.reference.service.persistence.impl.ReferencePersistenceImpl
 * @generated
 */
@ProviderType
public class ReferenceUtil {
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
	public static void clearCache(Reference reference) {
		getPersistence().clearCache(reference);
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
	public static List<Reference> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Reference> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Reference> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Reference update(Reference reference) {
		return getPersistence().update(reference);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Reference update(Reference reference,
		ServiceContext serviceContext) {
		return getPersistence().update(reference, serviceContext);
	}

	/**
	* Returns all the references where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @return the matching references
	*/
	public static List<Reference> findByResourcePrimKey(long resourcePrimKey) {
		return getPersistence().findByResourcePrimKey(resourcePrimKey);
	}

	/**
	* Returns a range of all the references where resourcePrimKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references
	*/
	public static List<Reference> findByResourcePrimKey(long resourcePrimKey,
		int start, int end) {
		return getPersistence()
				   .findByResourcePrimKey(resourcePrimKey, start, end);
	}

	/**
	* Returns an ordered range of all the references where resourcePrimKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByResourcePrimKey(long resourcePrimKey,
		int start, int end, OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .findByResourcePrimKey(resourcePrimKey, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the references where resourcePrimKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByResourcePrimKey(long resourcePrimKey,
		int start, int end, OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByResourcePrimKey(resourcePrimKey, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first reference in the ordered set where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByResourcePrimKey_First(long resourcePrimKey,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByResourcePrimKey_First(resourcePrimKey,
			orderByComparator);
	}

	/**
	* Returns the first reference in the ordered set where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByResourcePrimKey_First(long resourcePrimKey,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByResourcePrimKey_First(resourcePrimKey,
			orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByResourcePrimKey_Last(long resourcePrimKey,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByResourcePrimKey_Last(resourcePrimKey,
			orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByResourcePrimKey_Last(long resourcePrimKey,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByResourcePrimKey_Last(resourcePrimKey,
			orderByComparator);
	}

	/**
	* Returns the references before and after the current reference in the ordered set where resourcePrimKey = &#63;.
	*
	* @param id the primary key of the current reference
	* @param resourcePrimKey the resource prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference[] findByResourcePrimKey_PrevAndNext(long id,
		long resourcePrimKey, OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByResourcePrimKey_PrevAndNext(id, resourcePrimKey,
			orderByComparator);
	}

	/**
	* Removes all the references where resourcePrimKey = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key
	*/
	public static void removeByResourcePrimKey(long resourcePrimKey) {
		getPersistence().removeByResourcePrimKey(resourcePrimKey);
	}

	/**
	* Returns the number of references where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @return the number of matching references
	*/
	public static int countByResourcePrimKey(long resourcePrimKey) {
		return getPersistence().countByResourcePrimKey(resourcePrimKey);
	}

	/**
	* Returns all the references where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching references
	*/
	public static List<Reference> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the references where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references
	*/
	public static List<Reference> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the references where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByUuid(String uuid, int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the references where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByUuid(String uuid, int start, int end,
		OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first reference in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByUuid_First(String uuid,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first reference in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByUuid_First(String uuid,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByUuid_Last(String uuid,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByUuid_Last(String uuid,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the references before and after the current reference in the ordered set where uuid = &#63;.
	*
	* @param id the primary key of the current reference
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference[] findByUuid_PrevAndNext(long id, String uuid,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByUuid_PrevAndNext(id, uuid, orderByComparator);
	}

	/**
	* Removes all the references where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of references where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching references
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the reference where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchReferenceException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByUUID_G(String uuid, long groupId)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the reference where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the reference where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the reference where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the reference that was removed
	*/
	public static Reference removeByUUID_G(String uuid, long groupId)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of references where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching references
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the references where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching references
	*/
	public static List<Reference> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the references where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references
	*/
	public static List<Reference> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the references where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the references where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first reference in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first reference in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the references before and after the current reference in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param id the primary key of the current reference
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference[] findByUuid_C_PrevAndNext(long id, String uuid,
		long companyId, OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(id, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the references where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of references where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching references
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the references where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching references
	*/
	public static List<Reference> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the references where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references
	*/
	public static List<Reference> findByGroupId(long groupId, int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the references where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByGroupId(long groupId, int start,
		int end, OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the references where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByGroupId(long groupId, int start,
		int end, OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first reference in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByGroupId_First(long groupId,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first reference in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByGroupId_First(long groupId,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByGroupId_Last(long groupId,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByGroupId_Last(long groupId,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the references before and after the current reference in the ordered set where groupId = &#63;.
	*
	* @param id the primary key of the current reference
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference[] findByGroupId_PrevAndNext(long id, long groupId,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(id, groupId, orderByComparator);
	}

	/**
	* Returns all the references that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByGroupId(long groupId) {
		return getPersistence().filterFindByGroupId(groupId);
	}

	/**
	* Returns a range of all the references that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByGroupId(long groupId, int start,
		int end) {
		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the references that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .filterFindByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the references before and after the current reference in the ordered set of references that the user has permission to view where groupId = &#63;.
	*
	* @param id the primary key of the current reference
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference[] filterFindByGroupId_PrevAndNext(long id,
		long groupId, OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .filterFindByGroupId_PrevAndNext(id, groupId,
			orderByComparator);
	}

	/**
	* Removes all the references where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of references where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching references
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns the number of references that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching references that the user has permission to view
	*/
	public static int filterCountByGroupId(long groupId) {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	* Returns all the references where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching references
	*/
	public static List<Reference> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the references where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references
	*/
	public static List<Reference> findByCompanyId(long companyId, int start,
		int end) {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the references where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the references where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first reference in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByCompanyId_First(long companyId,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first reference in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByCompanyId_First(long companyId,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByCompanyId_Last(long companyId,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByCompanyId_Last(long companyId,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the references before and after the current reference in the ordered set where companyId = &#63;.
	*
	* @param id the primary key of the current reference
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference[] findByCompanyId_PrevAndNext(long id,
		long companyId, OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(id, companyId, orderByComparator);
	}

	/**
	* Removes all the references where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of references where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching references
	*/
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns all the references where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @return the matching references
	*/
	public static List<Reference> findByR_S(long resourcePrimKey, int status) {
		return getPersistence().findByR_S(resourcePrimKey, status);
	}

	/**
	* Returns a range of all the references where resourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references
	*/
	public static List<Reference> findByR_S(long resourcePrimKey, int status,
		int start, int end) {
		return getPersistence().findByR_S(resourcePrimKey, status, start, end);
	}

	/**
	* Returns an ordered range of all the references where resourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByR_S(long resourcePrimKey, int status,
		int start, int end, OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .findByR_S(resourcePrimKey, status, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the references where resourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByR_S(long resourcePrimKey, int status,
		int start, int end, OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByR_S(resourcePrimKey, status, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first reference in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByR_S_First(long resourcePrimKey, int status,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByR_S_First(resourcePrimKey, status, orderByComparator);
	}

	/**
	* Returns the first reference in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByR_S_First(long resourcePrimKey, int status,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByR_S_First(resourcePrimKey, status, orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByR_S_Last(long resourcePrimKey, int status,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByR_S_Last(resourcePrimKey, status, orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByR_S_Last(long resourcePrimKey, int status,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByR_S_Last(resourcePrimKey, status, orderByComparator);
	}

	/**
	* Returns the references before and after the current reference in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param id the primary key of the current reference
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference[] findByR_S_PrevAndNext(long id,
		long resourcePrimKey, int status,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByR_S_PrevAndNext(id, resourcePrimKey, status,
			orderByComparator);
	}

	/**
	* Returns all the references where resourcePrimKey = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param statuses the statuses
	* @return the matching references
	*/
	public static List<Reference> findByR_S(long resourcePrimKey, int[] statuses) {
		return getPersistence().findByR_S(resourcePrimKey, statuses);
	}

	/**
	* Returns a range of all the references where resourcePrimKey = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param statuses the statuses
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references
	*/
	public static List<Reference> findByR_S(long resourcePrimKey,
		int[] statuses, int start, int end) {
		return getPersistence().findByR_S(resourcePrimKey, statuses, start, end);
	}

	/**
	* Returns an ordered range of all the references where resourcePrimKey = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param statuses the statuses
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByR_S(long resourcePrimKey,
		int[] statuses, int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .findByR_S(resourcePrimKey, statuses, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the references where resourcePrimKey = &#63; and status = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByR_S(long resourcePrimKey,
		int[] statuses, int start, int end,
		OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByR_S(resourcePrimKey, statuses, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the references where resourcePrimKey = &#63; and status = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	*/
	public static void removeByR_S(long resourcePrimKey, int status) {
		getPersistence().removeByR_S(resourcePrimKey, status);
	}

	/**
	* Returns the number of references where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @return the number of matching references
	*/
	public static int countByR_S(long resourcePrimKey, int status) {
		return getPersistence().countByR_S(resourcePrimKey, status);
	}

	/**
	* Returns the number of references where resourcePrimKey = &#63; and status = any &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param statuses the statuses
	* @return the number of matching references
	*/
	public static int countByR_S(long resourcePrimKey, int[] statuses) {
		return getPersistence().countByR_S(resourcePrimKey, statuses);
	}

	/**
	* Returns all the references where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching references
	*/
	public static List<Reference> findByG_U(long groupId, long userId) {
		return getPersistence().findByG_U(groupId, userId);
	}

	/**
	* Returns a range of all the references where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references
	*/
	public static List<Reference> findByG_U(long groupId, long userId,
		int start, int end) {
		return getPersistence().findByG_U(groupId, userId, start, end);
	}

	/**
	* Returns an ordered range of all the references where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByG_U(long groupId, long userId,
		int start, int end, OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .findByG_U(groupId, userId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the references where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByG_U(long groupId, long userId,
		int start, int end, OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_U(groupId, userId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first reference in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByG_U_First(long groupId, long userId,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByG_U_First(groupId, userId, orderByComparator);
	}

	/**
	* Returns the first reference in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByG_U_First(long groupId, long userId,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByG_U_First(groupId, userId, orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByG_U_Last(long groupId, long userId,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByG_U_Last(groupId, userId, orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByG_U_Last(long groupId, long userId,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByG_U_Last(groupId, userId, orderByComparator);
	}

	/**
	* Returns the references before and after the current reference in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param id the primary key of the current reference
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference[] findByG_U_PrevAndNext(long id, long groupId,
		long userId, OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByG_U_PrevAndNext(id, groupId, userId, orderByComparator);
	}

	/**
	* Returns all the references that the user has permission to view where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByG_U(long groupId, long userId) {
		return getPersistence().filterFindByG_U(groupId, userId);
	}

	/**
	* Returns a range of all the references that the user has permission to view where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByG_U(long groupId, long userId,
		int start, int end) {
		return getPersistence().filterFindByG_U(groupId, userId, start, end);
	}

	/**
	* Returns an ordered range of all the references that the user has permissions to view where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByG_U(long groupId, long userId,
		int start, int end, OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .filterFindByG_U(groupId, userId, start, end,
			orderByComparator);
	}

	/**
	* Returns the references before and after the current reference in the ordered set of references that the user has permission to view where groupId = &#63; and userId = &#63;.
	*
	* @param id the primary key of the current reference
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference[] filterFindByG_U_PrevAndNext(long id,
		long groupId, long userId,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .filterFindByG_U_PrevAndNext(id, groupId, userId,
			orderByComparator);
	}

	/**
	* Removes all the references where groupId = &#63; and userId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	*/
	public static void removeByG_U(long groupId, long userId) {
		getPersistence().removeByG_U(groupId, userId);
	}

	/**
	* Returns the number of references where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching references
	*/
	public static int countByG_U(long groupId, long userId) {
		return getPersistence().countByG_U(groupId, userId);
	}

	/**
	* Returns the number of references that the user has permission to view where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching references that the user has permission to view
	*/
	public static int filterCountByG_U(long groupId, long userId) {
		return getPersistence().filterCountByG_U(groupId, userId);
	}

	/**
	* Returns all the references where groupId = &#63; and referenceId = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @return the matching references
	*/
	public static List<Reference> findByG_R(long groupId, String referenceId) {
		return getPersistence().findByG_R(groupId, referenceId);
	}

	/**
	* Returns a range of all the references where groupId = &#63; and referenceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references
	*/
	public static List<Reference> findByG_R(long groupId, String referenceId,
		int start, int end) {
		return getPersistence().findByG_R(groupId, referenceId, start, end);
	}

	/**
	* Returns an ordered range of all the references where groupId = &#63; and referenceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByG_R(long groupId, String referenceId,
		int start, int end, OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .findByG_R(groupId, referenceId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the references where groupId = &#63; and referenceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByG_R(long groupId, String referenceId,
		int start, int end, OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_R(groupId, referenceId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first reference in the ordered set where groupId = &#63; and referenceId = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByG_R_First(long groupId, String referenceId,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByG_R_First(groupId, referenceId, orderByComparator);
	}

	/**
	* Returns the first reference in the ordered set where groupId = &#63; and referenceId = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByG_R_First(long groupId, String referenceId,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByG_R_First(groupId, referenceId, orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where groupId = &#63; and referenceId = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByG_R_Last(long groupId, String referenceId,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByG_R_Last(groupId, referenceId, orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where groupId = &#63; and referenceId = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByG_R_Last(long groupId, String referenceId,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByG_R_Last(groupId, referenceId, orderByComparator);
	}

	/**
	* Returns the references before and after the current reference in the ordered set where groupId = &#63; and referenceId = &#63;.
	*
	* @param id the primary key of the current reference
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference[] findByG_R_PrevAndNext(long id, long groupId,
		String referenceId, OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByG_R_PrevAndNext(id, groupId, referenceId,
			orderByComparator);
	}

	/**
	* Returns all the references that the user has permission to view where groupId = &#63; and referenceId = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @return the matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByG_R(long groupId,
		String referenceId) {
		return getPersistence().filterFindByG_R(groupId, referenceId);
	}

	/**
	* Returns a range of all the references that the user has permission to view where groupId = &#63; and referenceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByG_R(long groupId,
		String referenceId, int start, int end) {
		return getPersistence().filterFindByG_R(groupId, referenceId, start, end);
	}

	/**
	* Returns an ordered range of all the references that the user has permissions to view where groupId = &#63; and referenceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByG_R(long groupId,
		String referenceId, int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .filterFindByG_R(groupId, referenceId, start, end,
			orderByComparator);
	}

	/**
	* Returns the references before and after the current reference in the ordered set of references that the user has permission to view where groupId = &#63; and referenceId = &#63;.
	*
	* @param id the primary key of the current reference
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference[] filterFindByG_R_PrevAndNext(long id,
		long groupId, String referenceId,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .filterFindByG_R_PrevAndNext(id, groupId, referenceId,
			orderByComparator);
	}

	/**
	* Removes all the references where groupId = &#63; and referenceId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	*/
	public static void removeByG_R(long groupId, String referenceId) {
		getPersistence().removeByG_R(groupId, referenceId);
	}

	/**
	* Returns the number of references where groupId = &#63; and referenceId = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @return the number of matching references
	*/
	public static int countByG_R(long groupId, String referenceId) {
		return getPersistence().countByG_R(groupId, referenceId);
	}

	/**
	* Returns the number of references that the user has permission to view where groupId = &#63; and referenceId = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @return the number of matching references that the user has permission to view
	*/
	public static int filterCountByG_R(long groupId, String referenceId) {
		return getPersistence().filterCountByG_R(groupId, referenceId);
	}

	/**
	* Returns all the references where groupId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @return the matching references
	*/
	public static List<Reference> findByG_UT(long groupId, String urlTitle) {
		return getPersistence().findByG_UT(groupId, urlTitle);
	}

	/**
	* Returns a range of all the references where groupId = &#63; and urlTitle = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references
	*/
	public static List<Reference> findByG_UT(long groupId, String urlTitle,
		int start, int end) {
		return getPersistence().findByG_UT(groupId, urlTitle, start, end);
	}

	/**
	* Returns an ordered range of all the references where groupId = &#63; and urlTitle = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByG_UT(long groupId, String urlTitle,
		int start, int end, OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .findByG_UT(groupId, urlTitle, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the references where groupId = &#63; and urlTitle = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByG_UT(long groupId, String urlTitle,
		int start, int end, OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_UT(groupId, urlTitle, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first reference in the ordered set where groupId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByG_UT_First(long groupId, String urlTitle,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByG_UT_First(groupId, urlTitle, orderByComparator);
	}

	/**
	* Returns the first reference in the ordered set where groupId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByG_UT_First(long groupId, String urlTitle,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByG_UT_First(groupId, urlTitle, orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where groupId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByG_UT_Last(long groupId, String urlTitle,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByG_UT_Last(groupId, urlTitle, orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where groupId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByG_UT_Last(long groupId, String urlTitle,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByG_UT_Last(groupId, urlTitle, orderByComparator);
	}

	/**
	* Returns the references before and after the current reference in the ordered set where groupId = &#63; and urlTitle = &#63;.
	*
	* @param id the primary key of the current reference
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference[] findByG_UT_PrevAndNext(long id, long groupId,
		String urlTitle, OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByG_UT_PrevAndNext(id, groupId, urlTitle,
			orderByComparator);
	}

	/**
	* Returns all the references that the user has permission to view where groupId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @return the matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByG_UT(long groupId, String urlTitle) {
		return getPersistence().filterFindByG_UT(groupId, urlTitle);
	}

	/**
	* Returns a range of all the references that the user has permission to view where groupId = &#63; and urlTitle = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByG_UT(long groupId,
		String urlTitle, int start, int end) {
		return getPersistence().filterFindByG_UT(groupId, urlTitle, start, end);
	}

	/**
	* Returns an ordered range of all the references that the user has permissions to view where groupId = &#63; and urlTitle = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByG_UT(long groupId,
		String urlTitle, int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .filterFindByG_UT(groupId, urlTitle, start, end,
			orderByComparator);
	}

	/**
	* Returns the references before and after the current reference in the ordered set of references that the user has permission to view where groupId = &#63; and urlTitle = &#63;.
	*
	* @param id the primary key of the current reference
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference[] filterFindByG_UT_PrevAndNext(long id,
		long groupId, String urlTitle,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .filterFindByG_UT_PrevAndNext(id, groupId, urlTitle,
			orderByComparator);
	}

	/**
	* Removes all the references where groupId = &#63; and urlTitle = &#63; from the database.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	*/
	public static void removeByG_UT(long groupId, String urlTitle) {
		getPersistence().removeByG_UT(groupId, urlTitle);
	}

	/**
	* Returns the number of references where groupId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @return the number of matching references
	*/
	public static int countByG_UT(long groupId, String urlTitle) {
		return getPersistence().countByG_UT(groupId, urlTitle);
	}

	/**
	* Returns the number of references that the user has permission to view where groupId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @return the number of matching references that the user has permission to view
	*/
	public static int filterCountByG_UT(long groupId, String urlTitle) {
		return getPersistence().filterCountByG_UT(groupId, urlTitle);
	}

	/**
	* Returns all the references where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching references
	*/
	public static List<Reference> findByG_ST(long groupId, int status) {
		return getPersistence().findByG_ST(groupId, status);
	}

	/**
	* Returns a range of all the references where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references
	*/
	public static List<Reference> findByG_ST(long groupId, int status,
		int start, int end) {
		return getPersistence().findByG_ST(groupId, status, start, end);
	}

	/**
	* Returns an ordered range of all the references where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByG_ST(long groupId, int status,
		int start, int end, OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .findByG_ST(groupId, status, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the references where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByG_ST(long groupId, int status,
		int start, int end, OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_ST(groupId, status, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first reference in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByG_ST_First(long groupId, int status,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByG_ST_First(groupId, status, orderByComparator);
	}

	/**
	* Returns the first reference in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByG_ST_First(long groupId, int status,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByG_ST_First(groupId, status, orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByG_ST_Last(long groupId, int status,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByG_ST_Last(groupId, status, orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByG_ST_Last(long groupId, int status,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByG_ST_Last(groupId, status, orderByComparator);
	}

	/**
	* Returns the references before and after the current reference in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param id the primary key of the current reference
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference[] findByG_ST_PrevAndNext(long id, long groupId,
		int status, OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByG_ST_PrevAndNext(id, groupId, status,
			orderByComparator);
	}

	/**
	* Returns all the references that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByG_ST(long groupId, int status) {
		return getPersistence().filterFindByG_ST(groupId, status);
	}

	/**
	* Returns a range of all the references that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByG_ST(long groupId, int status,
		int start, int end) {
		return getPersistence().filterFindByG_ST(groupId, status, start, end);
	}

	/**
	* Returns an ordered range of all the references that the user has permissions to view where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByG_ST(long groupId, int status,
		int start, int end, OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .filterFindByG_ST(groupId, status, start, end,
			orderByComparator);
	}

	/**
	* Returns the references before and after the current reference in the ordered set of references that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param id the primary key of the current reference
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference[] filterFindByG_ST_PrevAndNext(long id,
		long groupId, int status, OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .filterFindByG_ST_PrevAndNext(id, groupId, status,
			orderByComparator);
	}

	/**
	* Removes all the references where groupId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param status the status
	*/
	public static void removeByG_ST(long groupId, int status) {
		getPersistence().removeByG_ST(groupId, status);
	}

	/**
	* Returns the number of references where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching references
	*/
	public static int countByG_ST(long groupId, int status) {
		return getPersistence().countByG_ST(groupId, status);
	}

	/**
	* Returns the number of references that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching references that the user has permission to view
	*/
	public static int filterCountByG_ST(long groupId, int status) {
		return getPersistence().filterCountByG_ST(groupId, status);
	}

	/**
	* Returns all the references where companyId = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param version the version
	* @return the matching references
	*/
	public static List<Reference> findByC_V(long companyId, double version) {
		return getPersistence().findByC_V(companyId, version);
	}

	/**
	* Returns a range of all the references where companyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param version the version
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references
	*/
	public static List<Reference> findByC_V(long companyId, double version,
		int start, int end) {
		return getPersistence().findByC_V(companyId, version, start, end);
	}

	/**
	* Returns an ordered range of all the references where companyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param version the version
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByC_V(long companyId, double version,
		int start, int end, OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .findByC_V(companyId, version, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the references where companyId = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param version the version
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByC_V(long companyId, double version,
		int start, int end, OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_V(companyId, version, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first reference in the ordered set where companyId = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByC_V_First(long companyId, double version,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByC_V_First(companyId, version, orderByComparator);
	}

	/**
	* Returns the first reference in the ordered set where companyId = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByC_V_First(long companyId, double version,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByC_V_First(companyId, version, orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where companyId = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByC_V_Last(long companyId, double version,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByC_V_Last(companyId, version, orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where companyId = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByC_V_Last(long companyId, double version,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByC_V_Last(companyId, version, orderByComparator);
	}

	/**
	* Returns the references before and after the current reference in the ordered set where companyId = &#63; and version = &#63;.
	*
	* @param id the primary key of the current reference
	* @param companyId the company ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference[] findByC_V_PrevAndNext(long id, long companyId,
		double version, OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByC_V_PrevAndNext(id, companyId, version,
			orderByComparator);
	}

	/**
	* Removes all the references where companyId = &#63; and version = &#63; from the database.
	*
	* @param companyId the company ID
	* @param version the version
	*/
	public static void removeByC_V(long companyId, double version) {
		getPersistence().removeByC_V(companyId, version);
	}

	/**
	* Returns the number of references where companyId = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param version the version
	* @return the number of matching references
	*/
	public static int countByC_V(long companyId, double version) {
		return getPersistence().countByC_V(companyId, version);
	}

	/**
	* Returns all the references where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @return the matching references
	*/
	public static List<Reference> findByC_ST(long companyId, int status) {
		return getPersistence().findByC_ST(companyId, status);
	}

	/**
	* Returns a range of all the references where companyId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references
	*/
	public static List<Reference> findByC_ST(long companyId, int status,
		int start, int end) {
		return getPersistence().findByC_ST(companyId, status, start, end);
	}

	/**
	* Returns an ordered range of all the references where companyId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByC_ST(long companyId, int status,
		int start, int end, OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .findByC_ST(companyId, status, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the references where companyId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByC_ST(long companyId, int status,
		int start, int end, OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_ST(companyId, status, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first reference in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByC_ST_First(long companyId, int status,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByC_ST_First(companyId, status, orderByComparator);
	}

	/**
	* Returns the first reference in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByC_ST_First(long companyId, int status,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByC_ST_First(companyId, status, orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByC_ST_Last(long companyId, int status,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByC_ST_Last(companyId, status, orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByC_ST_Last(long companyId, int status,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByC_ST_Last(companyId, status, orderByComparator);
	}

	/**
	* Returns the references before and after the current reference in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param id the primary key of the current reference
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference[] findByC_ST_PrevAndNext(long id, long companyId,
		int status, OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByC_ST_PrevAndNext(id, companyId, status,
			orderByComparator);
	}

	/**
	* Removes all the references where companyId = &#63; and status = &#63; from the database.
	*
	* @param companyId the company ID
	* @param status the status
	*/
	public static void removeByC_ST(long companyId, int status) {
		getPersistence().removeByC_ST(companyId, status);
	}

	/**
	* Returns the number of references where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @return the number of matching references
	*/
	public static int countByC_ST(long companyId, int status) {
		return getPersistence().countByC_ST(companyId, status);
	}

	/**
	* Returns all the references where companyId = &#63; and status &ne; &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @return the matching references
	*/
	public static List<Reference> findByC_NotST(long companyId, int status) {
		return getPersistence().findByC_NotST(companyId, status);
	}

	/**
	* Returns a range of all the references where companyId = &#63; and status &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references
	*/
	public static List<Reference> findByC_NotST(long companyId, int status,
		int start, int end) {
		return getPersistence().findByC_NotST(companyId, status, start, end);
	}

	/**
	* Returns an ordered range of all the references where companyId = &#63; and status &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByC_NotST(long companyId, int status,
		int start, int end, OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .findByC_NotST(companyId, status, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the references where companyId = &#63; and status &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByC_NotST(long companyId, int status,
		int start, int end, OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_NotST(companyId, status, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first reference in the ordered set where companyId = &#63; and status &ne; &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByC_NotST_First(long companyId, int status,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByC_NotST_First(companyId, status, orderByComparator);
	}

	/**
	* Returns the first reference in the ordered set where companyId = &#63; and status &ne; &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByC_NotST_First(long companyId, int status,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByC_NotST_First(companyId, status, orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where companyId = &#63; and status &ne; &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByC_NotST_Last(long companyId, int status,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByC_NotST_Last(companyId, status, orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where companyId = &#63; and status &ne; &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByC_NotST_Last(long companyId, int status,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByC_NotST_Last(companyId, status, orderByComparator);
	}

	/**
	* Returns the references before and after the current reference in the ordered set where companyId = &#63; and status &ne; &#63;.
	*
	* @param id the primary key of the current reference
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference[] findByC_NotST_PrevAndNext(long id,
		long companyId, int status,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByC_NotST_PrevAndNext(id, companyId, status,
			orderByComparator);
	}

	/**
	* Removes all the references where companyId = &#63; and status &ne; &#63; from the database.
	*
	* @param companyId the company ID
	* @param status the status
	*/
	public static void removeByC_NotST(long companyId, int status) {
		getPersistence().removeByC_NotST(companyId, status);
	}

	/**
	* Returns the number of references where companyId = &#63; and status &ne; &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @return the number of matching references
	*/
	public static int countByC_NotST(long companyId, int status) {
		return getPersistence().countByC_NotST(companyId, status);
	}

	/**
	* Returns the reference where groupId = &#63; and referenceId = &#63; and version = &#63; or throws a {@link NoSuchReferenceException} if it could not be found.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @return the matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByG_R_V(long groupId, String referenceId,
		double version)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence().findByG_R_V(groupId, referenceId, version);
	}

	/**
	* Returns the reference where groupId = &#63; and referenceId = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @return the matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByG_R_V(long groupId, String referenceId,
		double version) {
		return getPersistence().fetchByG_R_V(groupId, referenceId, version);
	}

	/**
	* Returns the reference where groupId = &#63; and referenceId = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByG_R_V(long groupId, String referenceId,
		double version, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_R_V(groupId, referenceId, version,
			retrieveFromCache);
	}

	/**
	* Removes the reference where groupId = &#63; and referenceId = &#63; and version = &#63; from the database.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @return the reference that was removed
	*/
	public static Reference removeByG_R_V(long groupId, String referenceId,
		double version)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence().removeByG_R_V(groupId, referenceId, version);
	}

	/**
	* Returns the number of references where groupId = &#63; and referenceId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @return the number of matching references
	*/
	public static int countByG_R_V(long groupId, String referenceId,
		double version) {
		return getPersistence().countByG_R_V(groupId, referenceId, version);
	}

	/**
	* Returns all the references where groupId = &#63; and referenceId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @return the matching references
	*/
	public static List<Reference> findByG_R_ST(long groupId,
		String referenceId, int status) {
		return getPersistence().findByG_R_ST(groupId, referenceId, status);
	}

	/**
	* Returns a range of all the references where groupId = &#63; and referenceId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references
	*/
	public static List<Reference> findByG_R_ST(long groupId,
		String referenceId, int status, int start, int end) {
		return getPersistence()
				   .findByG_R_ST(groupId, referenceId, status, start, end);
	}

	/**
	* Returns an ordered range of all the references where groupId = &#63; and referenceId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByG_R_ST(long groupId,
		String referenceId, int status, int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .findByG_R_ST(groupId, referenceId, status, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the references where groupId = &#63; and referenceId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByG_R_ST(long groupId,
		String referenceId, int status, int start, int end,
		OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_R_ST(groupId, referenceId, status, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first reference in the ordered set where groupId = &#63; and referenceId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByG_R_ST_First(long groupId,
		String referenceId, int status,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByG_R_ST_First(groupId, referenceId, status,
			orderByComparator);
	}

	/**
	* Returns the first reference in the ordered set where groupId = &#63; and referenceId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByG_R_ST_First(long groupId,
		String referenceId, int status,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByG_R_ST_First(groupId, referenceId, status,
			orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where groupId = &#63; and referenceId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByG_R_ST_Last(long groupId, String referenceId,
		int status, OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByG_R_ST_Last(groupId, referenceId, status,
			orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where groupId = &#63; and referenceId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByG_R_ST_Last(long groupId,
		String referenceId, int status,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByG_R_ST_Last(groupId, referenceId, status,
			orderByComparator);
	}

	/**
	* Returns the references before and after the current reference in the ordered set where groupId = &#63; and referenceId = &#63; and status = &#63;.
	*
	* @param id the primary key of the current reference
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference[] findByG_R_ST_PrevAndNext(long id, long groupId,
		String referenceId, int status,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByG_R_ST_PrevAndNext(id, groupId, referenceId, status,
			orderByComparator);
	}

	/**
	* Returns all the references that the user has permission to view where groupId = &#63; and referenceId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @return the matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByG_R_ST(long groupId,
		String referenceId, int status) {
		return getPersistence().filterFindByG_R_ST(groupId, referenceId, status);
	}

	/**
	* Returns a range of all the references that the user has permission to view where groupId = &#63; and referenceId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByG_R_ST(long groupId,
		String referenceId, int status, int start, int end) {
		return getPersistence()
				   .filterFindByG_R_ST(groupId, referenceId, status, start, end);
	}

	/**
	* Returns an ordered range of all the references that the user has permissions to view where groupId = &#63; and referenceId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByG_R_ST(long groupId,
		String referenceId, int status, int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .filterFindByG_R_ST(groupId, referenceId, status, start,
			end, orderByComparator);
	}

	/**
	* Returns the references before and after the current reference in the ordered set of references that the user has permission to view where groupId = &#63; and referenceId = &#63; and status = &#63;.
	*
	* @param id the primary key of the current reference
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference[] filterFindByG_R_ST_PrevAndNext(long id,
		long groupId, String referenceId, int status,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .filterFindByG_R_ST_PrevAndNext(id, groupId, referenceId,
			status, orderByComparator);
	}

	/**
	* Returns all the references that the user has permission to view where groupId = &#63; and referenceId = &#63; and status = any &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param statuses the statuses
	* @return the matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByG_R_ST(long groupId,
		String referenceId, int[] statuses) {
		return getPersistence()
				   .filterFindByG_R_ST(groupId, referenceId, statuses);
	}

	/**
	* Returns a range of all the references that the user has permission to view where groupId = &#63; and referenceId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param statuses the statuses
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByG_R_ST(long groupId,
		String referenceId, int[] statuses, int start, int end) {
		return getPersistence()
				   .filterFindByG_R_ST(groupId, referenceId, statuses, start,
			end);
	}

	/**
	* Returns an ordered range of all the references that the user has permission to view where groupId = &#63; and referenceId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param statuses the statuses
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByG_R_ST(long groupId,
		String referenceId, int[] statuses, int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .filterFindByG_R_ST(groupId, referenceId, statuses, start,
			end, orderByComparator);
	}

	/**
	* Returns all the references where groupId = &#63; and referenceId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param statuses the statuses
	* @return the matching references
	*/
	public static List<Reference> findByG_R_ST(long groupId,
		String referenceId, int[] statuses) {
		return getPersistence().findByG_R_ST(groupId, referenceId, statuses);
	}

	/**
	* Returns a range of all the references where groupId = &#63; and referenceId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param statuses the statuses
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references
	*/
	public static List<Reference> findByG_R_ST(long groupId,
		String referenceId, int[] statuses, int start, int end) {
		return getPersistence()
				   .findByG_R_ST(groupId, referenceId, statuses, start, end);
	}

	/**
	* Returns an ordered range of all the references where groupId = &#63; and referenceId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param statuses the statuses
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByG_R_ST(long groupId,
		String referenceId, int[] statuses, int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .findByG_R_ST(groupId, referenceId, statuses, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the references where groupId = &#63; and referenceId = &#63; and status = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByG_R_ST(long groupId,
		String referenceId, int[] statuses, int start, int end,
		OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_R_ST(groupId, referenceId, statuses, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the references where groupId = &#63; and referenceId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	*/
	public static void removeByG_R_ST(long groupId, String referenceId,
		int status) {
		getPersistence().removeByG_R_ST(groupId, referenceId, status);
	}

	/**
	* Returns the number of references where groupId = &#63; and referenceId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @return the number of matching references
	*/
	public static int countByG_R_ST(long groupId, String referenceId, int status) {
		return getPersistence().countByG_R_ST(groupId, referenceId, status);
	}

	/**
	* Returns the number of references where groupId = &#63; and referenceId = &#63; and status = any &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param statuses the statuses
	* @return the number of matching references
	*/
	public static int countByG_R_ST(long groupId, String referenceId,
		int[] statuses) {
		return getPersistence().countByG_R_ST(groupId, referenceId, statuses);
	}

	/**
	* Returns the number of references that the user has permission to view where groupId = &#63; and referenceId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @return the number of matching references that the user has permission to view
	*/
	public static int filterCountByG_R_ST(long groupId, String referenceId,
		int status) {
		return getPersistence().filterCountByG_R_ST(groupId, referenceId, status);
	}

	/**
	* Returns the number of references that the user has permission to view where groupId = &#63; and referenceId = &#63; and status = any &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param statuses the statuses
	* @return the number of matching references that the user has permission to view
	*/
	public static int filterCountByG_R_ST(long groupId, String referenceId,
		int[] statuses) {
		return getPersistence()
				   .filterCountByG_R_ST(groupId, referenceId, statuses);
	}

	/**
	* Returns all the references where groupId = &#63; and referenceId = &#63; and status &ne; &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @return the matching references
	*/
	public static List<Reference> findByG_R_NotST(long groupId,
		String referenceId, int status) {
		return getPersistence().findByG_R_NotST(groupId, referenceId, status);
	}

	/**
	* Returns a range of all the references where groupId = &#63; and referenceId = &#63; and status &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references
	*/
	public static List<Reference> findByG_R_NotST(long groupId,
		String referenceId, int status, int start, int end) {
		return getPersistence()
				   .findByG_R_NotST(groupId, referenceId, status, start, end);
	}

	/**
	* Returns an ordered range of all the references where groupId = &#63; and referenceId = &#63; and status &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByG_R_NotST(long groupId,
		String referenceId, int status, int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .findByG_R_NotST(groupId, referenceId, status, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the references where groupId = &#63; and referenceId = &#63; and status &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByG_R_NotST(long groupId,
		String referenceId, int status, int start, int end,
		OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_R_NotST(groupId, referenceId, status, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first reference in the ordered set where groupId = &#63; and referenceId = &#63; and status &ne; &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByG_R_NotST_First(long groupId,
		String referenceId, int status,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByG_R_NotST_First(groupId, referenceId, status,
			orderByComparator);
	}

	/**
	* Returns the first reference in the ordered set where groupId = &#63; and referenceId = &#63; and status &ne; &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByG_R_NotST_First(long groupId,
		String referenceId, int status,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByG_R_NotST_First(groupId, referenceId, status,
			orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where groupId = &#63; and referenceId = &#63; and status &ne; &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByG_R_NotST_Last(long groupId,
		String referenceId, int status,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByG_R_NotST_Last(groupId, referenceId, status,
			orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where groupId = &#63; and referenceId = &#63; and status &ne; &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByG_R_NotST_Last(long groupId,
		String referenceId, int status,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByG_R_NotST_Last(groupId, referenceId, status,
			orderByComparator);
	}

	/**
	* Returns the references before and after the current reference in the ordered set where groupId = &#63; and referenceId = &#63; and status &ne; &#63;.
	*
	* @param id the primary key of the current reference
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference[] findByG_R_NotST_PrevAndNext(long id,
		long groupId, String referenceId, int status,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByG_R_NotST_PrevAndNext(id, groupId, referenceId,
			status, orderByComparator);
	}

	/**
	* Returns all the references that the user has permission to view where groupId = &#63; and referenceId = &#63; and status &ne; &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @return the matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByG_R_NotST(long groupId,
		String referenceId, int status) {
		return getPersistence()
				   .filterFindByG_R_NotST(groupId, referenceId, status);
	}

	/**
	* Returns a range of all the references that the user has permission to view where groupId = &#63; and referenceId = &#63; and status &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByG_R_NotST(long groupId,
		String referenceId, int status, int start, int end) {
		return getPersistence()
				   .filterFindByG_R_NotST(groupId, referenceId, status, start,
			end);
	}

	/**
	* Returns an ordered range of all the references that the user has permissions to view where groupId = &#63; and referenceId = &#63; and status &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByG_R_NotST(long groupId,
		String referenceId, int status, int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .filterFindByG_R_NotST(groupId, referenceId, status, start,
			end, orderByComparator);
	}

	/**
	* Returns the references before and after the current reference in the ordered set of references that the user has permission to view where groupId = &#63; and referenceId = &#63; and status &ne; &#63;.
	*
	* @param id the primary key of the current reference
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference[] filterFindByG_R_NotST_PrevAndNext(long id,
		long groupId, String referenceId, int status,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .filterFindByG_R_NotST_PrevAndNext(id, groupId, referenceId,
			status, orderByComparator);
	}

	/**
	* Removes all the references where groupId = &#63; and referenceId = &#63; and status &ne; &#63; from the database.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	*/
	public static void removeByG_R_NotST(long groupId, String referenceId,
		int status) {
		getPersistence().removeByG_R_NotST(groupId, referenceId, status);
	}

	/**
	* Returns the number of references where groupId = &#63; and referenceId = &#63; and status &ne; &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @return the number of matching references
	*/
	public static int countByG_R_NotST(long groupId, String referenceId,
		int status) {
		return getPersistence().countByG_R_NotST(groupId, referenceId, status);
	}

	/**
	* Returns the number of references that the user has permission to view where groupId = &#63; and referenceId = &#63; and status &ne; &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @return the number of matching references that the user has permission to view
	*/
	public static int filterCountByG_R_NotST(long groupId, String referenceId,
		int status) {
		return getPersistence()
				   .filterCountByG_R_NotST(groupId, referenceId, status);
	}

	/**
	* Returns all the references where groupId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param status the status
	* @return the matching references
	*/
	public static List<Reference> findByG_UT_ST(long groupId, String urlTitle,
		int status) {
		return getPersistence().findByG_UT_ST(groupId, urlTitle, status);
	}

	/**
	* Returns a range of all the references where groupId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references
	*/
	public static List<Reference> findByG_UT_ST(long groupId, String urlTitle,
		int status, int start, int end) {
		return getPersistence()
				   .findByG_UT_ST(groupId, urlTitle, status, start, end);
	}

	/**
	* Returns an ordered range of all the references where groupId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByG_UT_ST(long groupId, String urlTitle,
		int status, int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .findByG_UT_ST(groupId, urlTitle, status, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the references where groupId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByG_UT_ST(long groupId, String urlTitle,
		int status, int start, int end,
		OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_UT_ST(groupId, urlTitle, status, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first reference in the ordered set where groupId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByG_UT_ST_First(long groupId, String urlTitle,
		int status, OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByG_UT_ST_First(groupId, urlTitle, status,
			orderByComparator);
	}

	/**
	* Returns the first reference in the ordered set where groupId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByG_UT_ST_First(long groupId, String urlTitle,
		int status, OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByG_UT_ST_First(groupId, urlTitle, status,
			orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where groupId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByG_UT_ST_Last(long groupId, String urlTitle,
		int status, OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByG_UT_ST_Last(groupId, urlTitle, status,
			orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where groupId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByG_UT_ST_Last(long groupId, String urlTitle,
		int status, OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByG_UT_ST_Last(groupId, urlTitle, status,
			orderByComparator);
	}

	/**
	* Returns the references before and after the current reference in the ordered set where groupId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param id the primary key of the current reference
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference[] findByG_UT_ST_PrevAndNext(long id, long groupId,
		String urlTitle, int status,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByG_UT_ST_PrevAndNext(id, groupId, urlTitle, status,
			orderByComparator);
	}

	/**
	* Returns all the references that the user has permission to view where groupId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param status the status
	* @return the matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByG_UT_ST(long groupId,
		String urlTitle, int status) {
		return getPersistence().filterFindByG_UT_ST(groupId, urlTitle, status);
	}

	/**
	* Returns a range of all the references that the user has permission to view where groupId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByG_UT_ST(long groupId,
		String urlTitle, int status, int start, int end) {
		return getPersistence()
				   .filterFindByG_UT_ST(groupId, urlTitle, status, start, end);
	}

	/**
	* Returns an ordered range of all the references that the user has permissions to view where groupId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references that the user has permission to view
	*/
	public static List<Reference> filterFindByG_UT_ST(long groupId,
		String urlTitle, int status, int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .filterFindByG_UT_ST(groupId, urlTitle, status, start, end,
			orderByComparator);
	}

	/**
	* Returns the references before and after the current reference in the ordered set of references that the user has permission to view where groupId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param id the primary key of the current reference
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference[] filterFindByG_UT_ST_PrevAndNext(long id,
		long groupId, String urlTitle, int status,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .filterFindByG_UT_ST_PrevAndNext(id, groupId, urlTitle,
			status, orderByComparator);
	}

	/**
	* Removes all the references where groupId = &#63; and urlTitle = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param status the status
	*/
	public static void removeByG_UT_ST(long groupId, String urlTitle, int status) {
		getPersistence().removeByG_UT_ST(groupId, urlTitle, status);
	}

	/**
	* Returns the number of references where groupId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param status the status
	* @return the number of matching references
	*/
	public static int countByG_UT_ST(long groupId, String urlTitle, int status) {
		return getPersistence().countByG_UT_ST(groupId, urlTitle, status);
	}

	/**
	* Returns the number of references that the user has permission to view where groupId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param status the status
	* @return the number of matching references that the user has permission to view
	*/
	public static int filterCountByG_UT_ST(long groupId, String urlTitle,
		int status) {
		return getPersistence().filterCountByG_UT_ST(groupId, urlTitle, status);
	}

	/**
	* Returns all the references where companyId = &#63; and version = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param version the version
	* @param status the status
	* @return the matching references
	*/
	public static List<Reference> findByC_V_ST(long companyId, double version,
		int status) {
		return getPersistence().findByC_V_ST(companyId, version, status);
	}

	/**
	* Returns a range of all the references where companyId = &#63; and version = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param version the version
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of matching references
	*/
	public static List<Reference> findByC_V_ST(long companyId, double version,
		int status, int start, int end) {
		return getPersistence()
				   .findByC_V_ST(companyId, version, status, start, end);
	}

	/**
	* Returns an ordered range of all the references where companyId = &#63; and version = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param version the version
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByC_V_ST(long companyId, double version,
		int status, int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .findByC_V_ST(companyId, version, status, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the references where companyId = &#63; and version = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param version the version
	* @param status the status
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching references
	*/
	public static List<Reference> findByC_V_ST(long companyId, double version,
		int status, int start, int end,
		OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_V_ST(companyId, version, status, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first reference in the ordered set where companyId = &#63; and version = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param version the version
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByC_V_ST_First(long companyId, double version,
		int status, OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByC_V_ST_First(companyId, version, status,
			orderByComparator);
	}

	/**
	* Returns the first reference in the ordered set where companyId = &#63; and version = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param version the version
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByC_V_ST_First(long companyId, double version,
		int status, OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByC_V_ST_First(companyId, version, status,
			orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where companyId = &#63; and version = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param version the version
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public static Reference findByC_V_ST_Last(long companyId, double version,
		int status, OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByC_V_ST_Last(companyId, version, status,
			orderByComparator);
	}

	/**
	* Returns the last reference in the ordered set where companyId = &#63; and version = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param version the version
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public static Reference fetchByC_V_ST_Last(long companyId, double version,
		int status, OrderByComparator<Reference> orderByComparator) {
		return getPersistence()
				   .fetchByC_V_ST_Last(companyId, version, status,
			orderByComparator);
	}

	/**
	* Returns the references before and after the current reference in the ordered set where companyId = &#63; and version = &#63; and status = &#63;.
	*
	* @param id the primary key of the current reference
	* @param companyId the company ID
	* @param version the version
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference[] findByC_V_ST_PrevAndNext(long id, long companyId,
		double version, int status,
		OrderByComparator<Reference> orderByComparator)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence()
				   .findByC_V_ST_PrevAndNext(id, companyId, version, status,
			orderByComparator);
	}

	/**
	* Removes all the references where companyId = &#63; and version = &#63; and status = &#63; from the database.
	*
	* @param companyId the company ID
	* @param version the version
	* @param status the status
	*/
	public static void removeByC_V_ST(long companyId, double version, int status) {
		getPersistence().removeByC_V_ST(companyId, version, status);
	}

	/**
	* Returns the number of references where companyId = &#63; and version = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param version the version
	* @param status the status
	* @return the number of matching references
	*/
	public static int countByC_V_ST(long companyId, double version, int status) {
		return getPersistence().countByC_V_ST(companyId, version, status);
	}

	/**
	* Caches the reference in the entity cache if it is enabled.
	*
	* @param reference the reference
	*/
	public static void cacheResult(Reference reference) {
		getPersistence().cacheResult(reference);
	}

	/**
	* Caches the references in the entity cache if it is enabled.
	*
	* @param references the references
	*/
	public static void cacheResult(List<Reference> references) {
		getPersistence().cacheResult(references);
	}

	/**
	* Creates a new reference with the primary key. Does not add the reference to the database.
	*
	* @param id the primary key for the new reference
	* @return the new reference
	*/
	public static Reference create(long id) {
		return getPersistence().create(id);
	}

	/**
	* Removes the reference with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the reference
	* @return the reference that was removed
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference remove(long id)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence().remove(id);
	}

	public static Reference updateImpl(Reference reference) {
		return getPersistence().updateImpl(reference);
	}

	/**
	* Returns the reference with the primary key or throws a {@link NoSuchReferenceException} if it could not be found.
	*
	* @param id the primary key of the reference
	* @return the reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public static Reference findByPrimaryKey(long id)
		throws hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException {
		return getPersistence().findByPrimaryKey(id);
	}

	/**
	* Returns the reference with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the reference
	* @return the reference, or <code>null</code> if a reference with the primary key could not be found
	*/
	public static Reference fetchByPrimaryKey(long id) {
		return getPersistence().fetchByPrimaryKey(id);
	}

	public static java.util.Map<java.io.Serializable, Reference> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the references.
	*
	* @return the references
	*/
	public static List<Reference> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the references.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @return the range of references
	*/
	public static List<Reference> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the references.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of references
	*/
	public static List<Reference> findAll(int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the references.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of references
	* @param end the upper bound of the range of references (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of references
	*/
	public static List<Reference> findAll(int start, int end,
		OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the references from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of references.
	*
	* @return the number of references
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ReferencePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ReferencePersistence, ReferencePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ReferencePersistence.class);

		ServiceTracker<ReferencePersistence, ReferencePersistence> serviceTracker =
			new ServiceTracker<ReferencePersistence, ReferencePersistence>(bundle.getBundleContext(),
				ReferencePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}