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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException;
import hu.webtown.liferay.portlet.reference.model.Reference;

/**
 * The persistence interface for the reference service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see hu.webtown.liferay.portlet.reference.service.persistence.impl.ReferencePersistenceImpl
 * @see ReferenceUtil
 * @generated
 */
@ProviderType
public interface ReferencePersistence extends BasePersistence<Reference> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ReferenceUtil} to access the reference persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the references where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @return the matching references
	*/
	public java.util.List<Reference> findByResourcePrimKey(long resourcePrimKey);

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
	public java.util.List<Reference> findByResourcePrimKey(
		long resourcePrimKey, int start, int end);

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
	public java.util.List<Reference> findByResourcePrimKey(
		long resourcePrimKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public java.util.List<Reference> findByResourcePrimKey(
		long resourcePrimKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first reference in the ordered set where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByResourcePrimKey_First(long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the first reference in the ordered set where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByResourcePrimKey_First(long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

	/**
	* Returns the last reference in the ordered set where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByResourcePrimKey_Last(long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the last reference in the ordered set where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByResourcePrimKey_Last(long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

	/**
	* Returns the references before and after the current reference in the ordered set where resourcePrimKey = &#63;.
	*
	* @param id the primary key of the current reference
	* @param resourcePrimKey the resource prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public Reference[] findByResourcePrimKey_PrevAndNext(long id,
		long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Removes all the references where resourcePrimKey = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key
	*/
	public void removeByResourcePrimKey(long resourcePrimKey);

	/**
	* Returns the number of references where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @return the number of matching references
	*/
	public int countByResourcePrimKey(long resourcePrimKey);

	/**
	* Returns all the references where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching references
	*/
	public java.util.List<Reference> findByUuid(String uuid);

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
	public java.util.List<Reference> findByUuid(String uuid, int start, int end);

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
	public java.util.List<Reference> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public java.util.List<Reference> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first reference in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the first reference in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

	/**
	* Returns the last reference in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the last reference in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

	/**
	* Returns the references before and after the current reference in the ordered set where uuid = &#63;.
	*
	* @param id the primary key of the current reference
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public Reference[] findByUuid_PrevAndNext(long id, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Removes all the references where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of references where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching references
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the reference where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchReferenceException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByUUID_G(String uuid, long groupId)
		throws NoSuchReferenceException;

	/**
	* Returns the reference where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the reference where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the reference where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the reference that was removed
	*/
	public Reference removeByUUID_G(String uuid, long groupId)
		throws NoSuchReferenceException;

	/**
	* Returns the number of references where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching references
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the references where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching references
	*/
	public java.util.List<Reference> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<Reference> findByUuid_C(String uuid, long companyId,
		int start, int end);

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
	public java.util.List<Reference> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public java.util.List<Reference> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first reference in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the first reference in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

	/**
	* Returns the last reference in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the last reference in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public Reference[] findByUuid_C_PrevAndNext(long id, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Removes all the references where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of references where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching references
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the references where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching references
	*/
	public java.util.List<Reference> findByGroupId(long groupId);

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
	public java.util.List<Reference> findByGroupId(long groupId, int start,
		int end);

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
	public java.util.List<Reference> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public java.util.List<Reference> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first reference in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the first reference in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

	/**
	* Returns the last reference in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the last reference in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

	/**
	* Returns the references before and after the current reference in the ordered set where groupId = &#63;.
	*
	* @param id the primary key of the current reference
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public Reference[] findByGroupId_PrevAndNext(long id, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns all the references that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching references that the user has permission to view
	*/
	public java.util.List<Reference> filterFindByGroupId(long groupId);

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
	public java.util.List<Reference> filterFindByGroupId(long groupId,
		int start, int end);

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
	public java.util.List<Reference> filterFindByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

	/**
	* Returns the references before and after the current reference in the ordered set of references that the user has permission to view where groupId = &#63;.
	*
	* @param id the primary key of the current reference
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public Reference[] filterFindByGroupId_PrevAndNext(long id, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Removes all the references where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of references where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching references
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns the number of references that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching references that the user has permission to view
	*/
	public int filterCountByGroupId(long groupId);

	/**
	* Returns all the references where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching references
	*/
	public java.util.List<Reference> findByCompanyId(long companyId);

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
	public java.util.List<Reference> findByCompanyId(long companyId, int start,
		int end);

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
	public java.util.List<Reference> findByCompanyId(long companyId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public java.util.List<Reference> findByCompanyId(long companyId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first reference in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the first reference in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

	/**
	* Returns the last reference in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the last reference in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

	/**
	* Returns the references before and after the current reference in the ordered set where companyId = &#63;.
	*
	* @param id the primary key of the current reference
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public Reference[] findByCompanyId_PrevAndNext(long id, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Removes all the references where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of references where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching references
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns all the references where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @return the matching references
	*/
	public java.util.List<Reference> findByR_S(long resourcePrimKey, int status);

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
	public java.util.List<Reference> findByR_S(long resourcePrimKey,
		int status, int start, int end);

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
	public java.util.List<Reference> findByR_S(long resourcePrimKey,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public java.util.List<Reference> findByR_S(long resourcePrimKey,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first reference in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByR_S_First(long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the first reference in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByR_S_First(long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

	/**
	* Returns the last reference in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByR_S_Last(long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the last reference in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByR_S_Last(long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public Reference[] findByR_S_PrevAndNext(long id, long resourcePrimKey,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

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
	public java.util.List<Reference> findByR_S(long resourcePrimKey,
		int[] statuses);

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
	public java.util.List<Reference> findByR_S(long resourcePrimKey,
		int[] statuses, int start, int end);

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
	public java.util.List<Reference> findByR_S(long resourcePrimKey,
		int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public java.util.List<Reference> findByR_S(long resourcePrimKey,
		int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the references where resourcePrimKey = &#63; and status = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	*/
	public void removeByR_S(long resourcePrimKey, int status);

	/**
	* Returns the number of references where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @return the number of matching references
	*/
	public int countByR_S(long resourcePrimKey, int status);

	/**
	* Returns the number of references where resourcePrimKey = &#63; and status = any &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param statuses the statuses
	* @return the number of matching references
	*/
	public int countByR_S(long resourcePrimKey, int[] statuses);

	/**
	* Returns all the references where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching references
	*/
	public java.util.List<Reference> findByG_U(long groupId, long userId);

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
	public java.util.List<Reference> findByG_U(long groupId, long userId,
		int start, int end);

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
	public java.util.List<Reference> findByG_U(long groupId, long userId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public java.util.List<Reference> findByG_U(long groupId, long userId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first reference in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByG_U_First(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the first reference in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByG_U_First(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

	/**
	* Returns the last reference in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByG_U_Last(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the last reference in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByG_U_Last(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public Reference[] findByG_U_PrevAndNext(long id, long groupId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns all the references that the user has permission to view where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching references that the user has permission to view
	*/
	public java.util.List<Reference> filterFindByG_U(long groupId, long userId);

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
	public java.util.List<Reference> filterFindByG_U(long groupId, long userId,
		int start, int end);

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
	public java.util.List<Reference> filterFindByG_U(long groupId, long userId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public Reference[] filterFindByG_U_PrevAndNext(long id, long groupId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Removes all the references where groupId = &#63; and userId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	*/
	public void removeByG_U(long groupId, long userId);

	/**
	* Returns the number of references where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching references
	*/
	public int countByG_U(long groupId, long userId);

	/**
	* Returns the number of references that the user has permission to view where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching references that the user has permission to view
	*/
	public int filterCountByG_U(long groupId, long userId);

	/**
	* Returns all the references where groupId = &#63; and referenceId = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @return the matching references
	*/
	public java.util.List<Reference> findByG_R(long groupId, String referenceId);

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
	public java.util.List<Reference> findByG_R(long groupId,
		String referenceId, int start, int end);

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
	public java.util.List<Reference> findByG_R(long groupId,
		String referenceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public java.util.List<Reference> findByG_R(long groupId,
		String referenceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first reference in the ordered set where groupId = &#63; and referenceId = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByG_R_First(long groupId, String referenceId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the first reference in the ordered set where groupId = &#63; and referenceId = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByG_R_First(long groupId, String referenceId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

	/**
	* Returns the last reference in the ordered set where groupId = &#63; and referenceId = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByG_R_Last(long groupId, String referenceId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the last reference in the ordered set where groupId = &#63; and referenceId = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByG_R_Last(long groupId, String referenceId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public Reference[] findByG_R_PrevAndNext(long id, long groupId,
		String referenceId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns all the references that the user has permission to view where groupId = &#63; and referenceId = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @return the matching references that the user has permission to view
	*/
	public java.util.List<Reference> filterFindByG_R(long groupId,
		String referenceId);

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
	public java.util.List<Reference> filterFindByG_R(long groupId,
		String referenceId, int start, int end);

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
	public java.util.List<Reference> filterFindByG_R(long groupId,
		String referenceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public Reference[] filterFindByG_R_PrevAndNext(long id, long groupId,
		String referenceId,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Removes all the references where groupId = &#63; and referenceId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	*/
	public void removeByG_R(long groupId, String referenceId);

	/**
	* Returns the number of references where groupId = &#63; and referenceId = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @return the number of matching references
	*/
	public int countByG_R(long groupId, String referenceId);

	/**
	* Returns the number of references that the user has permission to view where groupId = &#63; and referenceId = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @return the number of matching references that the user has permission to view
	*/
	public int filterCountByG_R(long groupId, String referenceId);

	/**
	* Returns all the references where groupId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @return the matching references
	*/
	public java.util.List<Reference> findByG_UT(long groupId, String urlTitle);

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
	public java.util.List<Reference> findByG_UT(long groupId, String urlTitle,
		int start, int end);

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
	public java.util.List<Reference> findByG_UT(long groupId, String urlTitle,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public java.util.List<Reference> findByG_UT(long groupId, String urlTitle,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first reference in the ordered set where groupId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByG_UT_First(long groupId, String urlTitle,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the first reference in the ordered set where groupId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByG_UT_First(long groupId, String urlTitle,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

	/**
	* Returns the last reference in the ordered set where groupId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByG_UT_Last(long groupId, String urlTitle,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the last reference in the ordered set where groupId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByG_UT_Last(long groupId, String urlTitle,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public Reference[] findByG_UT_PrevAndNext(long id, long groupId,
		String urlTitle,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns all the references that the user has permission to view where groupId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @return the matching references that the user has permission to view
	*/
	public java.util.List<Reference> filterFindByG_UT(long groupId,
		String urlTitle);

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
	public java.util.List<Reference> filterFindByG_UT(long groupId,
		String urlTitle, int start, int end);

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
	public java.util.List<Reference> filterFindByG_UT(long groupId,
		String urlTitle, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public Reference[] filterFindByG_UT_PrevAndNext(long id, long groupId,
		String urlTitle,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Removes all the references where groupId = &#63; and urlTitle = &#63; from the database.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	*/
	public void removeByG_UT(long groupId, String urlTitle);

	/**
	* Returns the number of references where groupId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @return the number of matching references
	*/
	public int countByG_UT(long groupId, String urlTitle);

	/**
	* Returns the number of references that the user has permission to view where groupId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @return the number of matching references that the user has permission to view
	*/
	public int filterCountByG_UT(long groupId, String urlTitle);

	/**
	* Returns all the references where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching references
	*/
	public java.util.List<Reference> findByG_ST(long groupId, int status);

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
	public java.util.List<Reference> findByG_ST(long groupId, int status,
		int start, int end);

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
	public java.util.List<Reference> findByG_ST(long groupId, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public java.util.List<Reference> findByG_ST(long groupId, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first reference in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByG_ST_First(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the first reference in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByG_ST_First(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

	/**
	* Returns the last reference in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByG_ST_Last(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the last reference in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByG_ST_Last(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public Reference[] findByG_ST_PrevAndNext(long id, long groupId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns all the references that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching references that the user has permission to view
	*/
	public java.util.List<Reference> filterFindByG_ST(long groupId, int status);

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
	public java.util.List<Reference> filterFindByG_ST(long groupId, int status,
		int start, int end);

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
	public java.util.List<Reference> filterFindByG_ST(long groupId, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public Reference[] filterFindByG_ST_PrevAndNext(long id, long groupId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Removes all the references where groupId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param status the status
	*/
	public void removeByG_ST(long groupId, int status);

	/**
	* Returns the number of references where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching references
	*/
	public int countByG_ST(long groupId, int status);

	/**
	* Returns the number of references that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching references that the user has permission to view
	*/
	public int filterCountByG_ST(long groupId, int status);

	/**
	* Returns all the references where companyId = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param version the version
	* @return the matching references
	*/
	public java.util.List<Reference> findByC_V(long companyId, double version);

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
	public java.util.List<Reference> findByC_V(long companyId, double version,
		int start, int end);

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
	public java.util.List<Reference> findByC_V(long companyId, double version,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public java.util.List<Reference> findByC_V(long companyId, double version,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first reference in the ordered set where companyId = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByC_V_First(long companyId, double version,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the first reference in the ordered set where companyId = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByC_V_First(long companyId, double version,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

	/**
	* Returns the last reference in the ordered set where companyId = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByC_V_Last(long companyId, double version,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the last reference in the ordered set where companyId = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByC_V_Last(long companyId, double version,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public Reference[] findByC_V_PrevAndNext(long id, long companyId,
		double version,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Removes all the references where companyId = &#63; and version = &#63; from the database.
	*
	* @param companyId the company ID
	* @param version the version
	*/
	public void removeByC_V(long companyId, double version);

	/**
	* Returns the number of references where companyId = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param version the version
	* @return the number of matching references
	*/
	public int countByC_V(long companyId, double version);

	/**
	* Returns all the references where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @return the matching references
	*/
	public java.util.List<Reference> findByC_ST(long companyId, int status);

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
	public java.util.List<Reference> findByC_ST(long companyId, int status,
		int start, int end);

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
	public java.util.List<Reference> findByC_ST(long companyId, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public java.util.List<Reference> findByC_ST(long companyId, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first reference in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByC_ST_First(long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the first reference in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByC_ST_First(long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

	/**
	* Returns the last reference in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByC_ST_Last(long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the last reference in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByC_ST_Last(long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public Reference[] findByC_ST_PrevAndNext(long id, long companyId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Removes all the references where companyId = &#63; and status = &#63; from the database.
	*
	* @param companyId the company ID
	* @param status the status
	*/
	public void removeByC_ST(long companyId, int status);

	/**
	* Returns the number of references where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @return the number of matching references
	*/
	public int countByC_ST(long companyId, int status);

	/**
	* Returns all the references where companyId = &#63; and status &ne; &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @return the matching references
	*/
	public java.util.List<Reference> findByC_NotST(long companyId, int status);

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
	public java.util.List<Reference> findByC_NotST(long companyId, int status,
		int start, int end);

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
	public java.util.List<Reference> findByC_NotST(long companyId, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public java.util.List<Reference> findByC_NotST(long companyId, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first reference in the ordered set where companyId = &#63; and status &ne; &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByC_NotST_First(long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the first reference in the ordered set where companyId = &#63; and status &ne; &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByC_NotST_First(long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

	/**
	* Returns the last reference in the ordered set where companyId = &#63; and status &ne; &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByC_NotST_Last(long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the last reference in the ordered set where companyId = &#63; and status &ne; &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByC_NotST_Last(long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public Reference[] findByC_NotST_PrevAndNext(long id, long companyId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Removes all the references where companyId = &#63; and status &ne; &#63; from the database.
	*
	* @param companyId the company ID
	* @param status the status
	*/
	public void removeByC_NotST(long companyId, int status);

	/**
	* Returns the number of references where companyId = &#63; and status &ne; &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @return the number of matching references
	*/
	public int countByC_NotST(long companyId, int status);

	/**
	* Returns the reference where groupId = &#63; and referenceId = &#63; and version = &#63; or throws a {@link NoSuchReferenceException} if it could not be found.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @return the matching reference
	* @throws NoSuchReferenceException if a matching reference could not be found
	*/
	public Reference findByG_R_V(long groupId, String referenceId,
		double version) throws NoSuchReferenceException;

	/**
	* Returns the reference where groupId = &#63; and referenceId = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @return the matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByG_R_V(long groupId, String referenceId,
		double version);

	/**
	* Returns the reference where groupId = &#63; and referenceId = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByG_R_V(long groupId, String referenceId,
		double version, boolean retrieveFromCache);

	/**
	* Removes the reference where groupId = &#63; and referenceId = &#63; and version = &#63; from the database.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @return the reference that was removed
	*/
	public Reference removeByG_R_V(long groupId, String referenceId,
		double version) throws NoSuchReferenceException;

	/**
	* Returns the number of references where groupId = &#63; and referenceId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @return the number of matching references
	*/
	public int countByG_R_V(long groupId, String referenceId, double version);

	/**
	* Returns all the references where groupId = &#63; and referenceId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @return the matching references
	*/
	public java.util.List<Reference> findByG_R_ST(long groupId,
		String referenceId, int status);

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
	public java.util.List<Reference> findByG_R_ST(long groupId,
		String referenceId, int status, int start, int end);

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
	public java.util.List<Reference> findByG_R_ST(long groupId,
		String referenceId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public java.util.List<Reference> findByG_R_ST(long groupId,
		String referenceId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache);

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
	public Reference findByG_R_ST_First(long groupId, String referenceId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the first reference in the ordered set where groupId = &#63; and referenceId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByG_R_ST_First(long groupId, String referenceId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public Reference findByG_R_ST_Last(long groupId, String referenceId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the last reference in the ordered set where groupId = &#63; and referenceId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByG_R_ST_Last(long groupId, String referenceId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public Reference[] findByG_R_ST_PrevAndNext(long id, long groupId,
		String referenceId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns all the references that the user has permission to view where groupId = &#63; and referenceId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @return the matching references that the user has permission to view
	*/
	public java.util.List<Reference> filterFindByG_R_ST(long groupId,
		String referenceId, int status);

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
	public java.util.List<Reference> filterFindByG_R_ST(long groupId,
		String referenceId, int status, int start, int end);

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
	public java.util.List<Reference> filterFindByG_R_ST(long groupId,
		String referenceId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public Reference[] filterFindByG_R_ST_PrevAndNext(long id, long groupId,
		String referenceId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns all the references that the user has permission to view where groupId = &#63; and referenceId = &#63; and status = any &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param statuses the statuses
	* @return the matching references that the user has permission to view
	*/
	public java.util.List<Reference> filterFindByG_R_ST(long groupId,
		String referenceId, int[] statuses);

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
	public java.util.List<Reference> filterFindByG_R_ST(long groupId,
		String referenceId, int[] statuses, int start, int end);

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
	public java.util.List<Reference> filterFindByG_R_ST(long groupId,
		String referenceId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public java.util.List<Reference> findByG_R_ST(long groupId,
		String referenceId, int[] statuses);

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
	public java.util.List<Reference> findByG_R_ST(long groupId,
		String referenceId, int[] statuses, int start, int end);

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
	public java.util.List<Reference> findByG_R_ST(long groupId,
		String referenceId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public java.util.List<Reference> findByG_R_ST(long groupId,
		String referenceId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the references where groupId = &#63; and referenceId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	*/
	public void removeByG_R_ST(long groupId, String referenceId, int status);

	/**
	* Returns the number of references where groupId = &#63; and referenceId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @return the number of matching references
	*/
	public int countByG_R_ST(long groupId, String referenceId, int status);

	/**
	* Returns the number of references where groupId = &#63; and referenceId = &#63; and status = any &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param statuses the statuses
	* @return the number of matching references
	*/
	public int countByG_R_ST(long groupId, String referenceId, int[] statuses);

	/**
	* Returns the number of references that the user has permission to view where groupId = &#63; and referenceId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @return the number of matching references that the user has permission to view
	*/
	public int filterCountByG_R_ST(long groupId, String referenceId, int status);

	/**
	* Returns the number of references that the user has permission to view where groupId = &#63; and referenceId = &#63; and status = any &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param statuses the statuses
	* @return the number of matching references that the user has permission to view
	*/
	public int filterCountByG_R_ST(long groupId, String referenceId,
		int[] statuses);

	/**
	* Returns all the references where groupId = &#63; and referenceId = &#63; and status &ne; &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @return the matching references
	*/
	public java.util.List<Reference> findByG_R_NotST(long groupId,
		String referenceId, int status);

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
	public java.util.List<Reference> findByG_R_NotST(long groupId,
		String referenceId, int status, int start, int end);

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
	public java.util.List<Reference> findByG_R_NotST(long groupId,
		String referenceId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public java.util.List<Reference> findByG_R_NotST(long groupId,
		String referenceId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache);

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
	public Reference findByG_R_NotST_First(long groupId, String referenceId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the first reference in the ordered set where groupId = &#63; and referenceId = &#63; and status &ne; &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByG_R_NotST_First(long groupId, String referenceId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public Reference findByG_R_NotST_Last(long groupId, String referenceId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the last reference in the ordered set where groupId = &#63; and referenceId = &#63; and status &ne; &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByG_R_NotST_Last(long groupId, String referenceId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public Reference[] findByG_R_NotST_PrevAndNext(long id, long groupId,
		String referenceId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns all the references that the user has permission to view where groupId = &#63; and referenceId = &#63; and status &ne; &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @return the matching references that the user has permission to view
	*/
	public java.util.List<Reference> filterFindByG_R_NotST(long groupId,
		String referenceId, int status);

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
	public java.util.List<Reference> filterFindByG_R_NotST(long groupId,
		String referenceId, int status, int start, int end);

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
	public java.util.List<Reference> filterFindByG_R_NotST(long groupId,
		String referenceId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public Reference[] filterFindByG_R_NotST_PrevAndNext(long id, long groupId,
		String referenceId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Removes all the references where groupId = &#63; and referenceId = &#63; and status &ne; &#63; from the database.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	*/
	public void removeByG_R_NotST(long groupId, String referenceId, int status);

	/**
	* Returns the number of references where groupId = &#63; and referenceId = &#63; and status &ne; &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @return the number of matching references
	*/
	public int countByG_R_NotST(long groupId, String referenceId, int status);

	/**
	* Returns the number of references that the user has permission to view where groupId = &#63; and referenceId = &#63; and status &ne; &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param status the status
	* @return the number of matching references that the user has permission to view
	*/
	public int filterCountByG_R_NotST(long groupId, String referenceId,
		int status);

	/**
	* Returns all the references where groupId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param status the status
	* @return the matching references
	*/
	public java.util.List<Reference> findByG_UT_ST(long groupId,
		String urlTitle, int status);

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
	public java.util.List<Reference> findByG_UT_ST(long groupId,
		String urlTitle, int status, int start, int end);

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
	public java.util.List<Reference> findByG_UT_ST(long groupId,
		String urlTitle, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public java.util.List<Reference> findByG_UT_ST(long groupId,
		String urlTitle, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache);

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
	public Reference findByG_UT_ST_First(long groupId, String urlTitle,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the first reference in the ordered set where groupId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByG_UT_ST_First(long groupId, String urlTitle,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public Reference findByG_UT_ST_Last(long groupId, String urlTitle,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the last reference in the ordered set where groupId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByG_UT_ST_Last(long groupId, String urlTitle,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public Reference[] findByG_UT_ST_PrevAndNext(long id, long groupId,
		String urlTitle, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns all the references that the user has permission to view where groupId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param status the status
	* @return the matching references that the user has permission to view
	*/
	public java.util.List<Reference> filterFindByG_UT_ST(long groupId,
		String urlTitle, int status);

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
	public java.util.List<Reference> filterFindByG_UT_ST(long groupId,
		String urlTitle, int status, int start, int end);

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
	public java.util.List<Reference> filterFindByG_UT_ST(long groupId,
		String urlTitle, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public Reference[] filterFindByG_UT_ST_PrevAndNext(long id, long groupId,
		String urlTitle, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Removes all the references where groupId = &#63; and urlTitle = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param status the status
	*/
	public void removeByG_UT_ST(long groupId, String urlTitle, int status);

	/**
	* Returns the number of references where groupId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param status the status
	* @return the number of matching references
	*/
	public int countByG_UT_ST(long groupId, String urlTitle, int status);

	/**
	* Returns the number of references that the user has permission to view where groupId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param urlTitle the url title
	* @param status the status
	* @return the number of matching references that the user has permission to view
	*/
	public int filterCountByG_UT_ST(long groupId, String urlTitle, int status);

	/**
	* Returns all the references where companyId = &#63; and version = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param version the version
	* @param status the status
	* @return the matching references
	*/
	public java.util.List<Reference> findByC_V_ST(long companyId,
		double version, int status);

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
	public java.util.List<Reference> findByC_V_ST(long companyId,
		double version, int status, int start, int end);

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
	public java.util.List<Reference> findByC_V_ST(long companyId,
		double version, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public java.util.List<Reference> findByC_V_ST(long companyId,
		double version, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache);

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
	public Reference findByC_V_ST_First(long companyId, double version,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the first reference in the ordered set where companyId = &#63; and version = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param version the version
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByC_V_ST_First(long companyId, double version,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public Reference findByC_V_ST_Last(long companyId, double version,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Returns the last reference in the ordered set where companyId = &#63; and version = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param version the version
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference, or <code>null</code> if a matching reference could not be found
	*/
	public Reference fetchByC_V_ST_Last(long companyId, double version,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public Reference[] findByC_V_ST_PrevAndNext(long id, long companyId,
		double version, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException;

	/**
	* Removes all the references where companyId = &#63; and version = &#63; and status = &#63; from the database.
	*
	* @param companyId the company ID
	* @param version the version
	* @param status the status
	*/
	public void removeByC_V_ST(long companyId, double version, int status);

	/**
	* Returns the number of references where companyId = &#63; and version = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param version the version
	* @param status the status
	* @return the number of matching references
	*/
	public int countByC_V_ST(long companyId, double version, int status);

	/**
	* Caches the reference in the entity cache if it is enabled.
	*
	* @param reference the reference
	*/
	public void cacheResult(Reference reference);

	/**
	* Caches the references in the entity cache if it is enabled.
	*
	* @param references the references
	*/
	public void cacheResult(java.util.List<Reference> references);

	/**
	* Creates a new reference with the primary key. Does not add the reference to the database.
	*
	* @param id the primary key for the new reference
	* @return the new reference
	*/
	public Reference create(long id);

	/**
	* Removes the reference with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the reference
	* @return the reference that was removed
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public Reference remove(long id) throws NoSuchReferenceException;

	public Reference updateImpl(Reference reference);

	/**
	* Returns the reference with the primary key or throws a {@link NoSuchReferenceException} if it could not be found.
	*
	* @param id the primary key of the reference
	* @return the reference
	* @throws NoSuchReferenceException if a reference with the primary key could not be found
	*/
	public Reference findByPrimaryKey(long id) throws NoSuchReferenceException;

	/**
	* Returns the reference with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the reference
	* @return the reference, or <code>null</code> if a reference with the primary key could not be found
	*/
	public Reference fetchByPrimaryKey(long id);

	@Override
	public java.util.Map<java.io.Serializable, Reference> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the references.
	*
	* @return the references
	*/
	public java.util.List<Reference> findAll();

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
	public java.util.List<Reference> findAll(int start, int end);

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
	public java.util.List<Reference> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator);

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
	public java.util.List<Reference> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the references from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of references.
	*
	* @return the number of references
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}