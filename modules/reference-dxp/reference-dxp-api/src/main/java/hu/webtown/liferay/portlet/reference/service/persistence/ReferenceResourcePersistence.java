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

import hu.webtown.liferay.portlet.reference.exception.NoSuchResourceException;
import hu.webtown.liferay.portlet.reference.model.ReferenceResource;

/**
 * The persistence interface for the reference resource service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see hu.webtown.liferay.portlet.reference.service.persistence.impl.ReferenceResourcePersistenceImpl
 * @see ReferenceResourceUtil
 * @generated
 */
@ProviderType
public interface ReferenceResourcePersistence extends BasePersistence<ReferenceResource> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ReferenceResourceUtil} to access the reference resource persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the reference resources where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching reference resources
	*/
	public java.util.List<ReferenceResource> findByUuid(String uuid);

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
	public java.util.List<ReferenceResource> findByUuid(String uuid, int start,
		int end);

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
	public java.util.List<ReferenceResource> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceResource> orderByComparator);

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
	public java.util.List<ReferenceResource> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceResource> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first reference resource in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference resource
	* @throws NoSuchResourceException if a matching reference resource could not be found
	*/
	public ReferenceResource findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceResource> orderByComparator)
		throws NoSuchResourceException;

	/**
	* Returns the first reference resource in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference resource, or <code>null</code> if a matching reference resource could not be found
	*/
	public ReferenceResource fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceResource> orderByComparator);

	/**
	* Returns the last reference resource in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference resource
	* @throws NoSuchResourceException if a matching reference resource could not be found
	*/
	public ReferenceResource findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceResource> orderByComparator)
		throws NoSuchResourceException;

	/**
	* Returns the last reference resource in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference resource, or <code>null</code> if a matching reference resource could not be found
	*/
	public ReferenceResource fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceResource> orderByComparator);

	/**
	* Returns the reference resources before and after the current reference resource in the ordered set where uuid = &#63;.
	*
	* @param resourcePrimKey the primary key of the current reference resource
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference resource
	* @throws NoSuchResourceException if a reference resource with the primary key could not be found
	*/
	public ReferenceResource[] findByUuid_PrevAndNext(long resourcePrimKey,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceResource> orderByComparator)
		throws NoSuchResourceException;

	/**
	* Removes all the reference resources where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of reference resources where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching reference resources
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the reference resource where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchResourceException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching reference resource
	* @throws NoSuchResourceException if a matching reference resource could not be found
	*/
	public ReferenceResource findByUUID_G(String uuid, long groupId)
		throws NoSuchResourceException;

	/**
	* Returns the reference resource where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching reference resource, or <code>null</code> if a matching reference resource could not be found
	*/
	public ReferenceResource fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the reference resource where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching reference resource, or <code>null</code> if a matching reference resource could not be found
	*/
	public ReferenceResource fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the reference resource where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the reference resource that was removed
	*/
	public ReferenceResource removeByUUID_G(String uuid, long groupId)
		throws NoSuchResourceException;

	/**
	* Returns the number of reference resources where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching reference resources
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the reference resources where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching reference resources
	*/
	public java.util.List<ReferenceResource> findByUuid_C(String uuid,
		long companyId);

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
	public java.util.List<ReferenceResource> findByUuid_C(String uuid,
		long companyId, int start, int end);

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
	public java.util.List<ReferenceResource> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceResource> orderByComparator);

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
	public java.util.List<ReferenceResource> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceResource> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first reference resource in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference resource
	* @throws NoSuchResourceException if a matching reference resource could not be found
	*/
	public ReferenceResource findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceResource> orderByComparator)
		throws NoSuchResourceException;

	/**
	* Returns the first reference resource in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference resource, or <code>null</code> if a matching reference resource could not be found
	*/
	public ReferenceResource fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceResource> orderByComparator);

	/**
	* Returns the last reference resource in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference resource
	* @throws NoSuchResourceException if a matching reference resource could not be found
	*/
	public ReferenceResource findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceResource> orderByComparator)
		throws NoSuchResourceException;

	/**
	* Returns the last reference resource in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference resource, or <code>null</code> if a matching reference resource could not be found
	*/
	public ReferenceResource fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceResource> orderByComparator);

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
	public ReferenceResource[] findByUuid_C_PrevAndNext(long resourcePrimKey,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceResource> orderByComparator)
		throws NoSuchResourceException;

	/**
	* Removes all the reference resources where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of reference resources where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching reference resources
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the reference resources where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching reference resources
	*/
	public java.util.List<ReferenceResource> findByGroupId(long groupId);

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
	public java.util.List<ReferenceResource> findByGroupId(long groupId,
		int start, int end);

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
	public java.util.List<ReferenceResource> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceResource> orderByComparator);

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
	public java.util.List<ReferenceResource> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceResource> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first reference resource in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference resource
	* @throws NoSuchResourceException if a matching reference resource could not be found
	*/
	public ReferenceResource findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceResource> orderByComparator)
		throws NoSuchResourceException;

	/**
	* Returns the first reference resource in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference resource, or <code>null</code> if a matching reference resource could not be found
	*/
	public ReferenceResource fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceResource> orderByComparator);

	/**
	* Returns the last reference resource in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference resource
	* @throws NoSuchResourceException if a matching reference resource could not be found
	*/
	public ReferenceResource findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceResource> orderByComparator)
		throws NoSuchResourceException;

	/**
	* Returns the last reference resource in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference resource, or <code>null</code> if a matching reference resource could not be found
	*/
	public ReferenceResource fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceResource> orderByComparator);

	/**
	* Returns the reference resources before and after the current reference resource in the ordered set where groupId = &#63;.
	*
	* @param resourcePrimKey the primary key of the current reference resource
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference resource
	* @throws NoSuchResourceException if a reference resource with the primary key could not be found
	*/
	public ReferenceResource[] findByGroupId_PrevAndNext(long resourcePrimKey,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceResource> orderByComparator)
		throws NoSuchResourceException;

	/**
	* Removes all the reference resources where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of reference resources where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching reference resources
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns the reference resource where groupId = &#63; and referenceId = &#63; or throws a {@link NoSuchResourceException} if it could not be found.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @return the matching reference resource
	* @throws NoSuchResourceException if a matching reference resource could not be found
	*/
	public ReferenceResource findByG_R(long groupId, String referenceId)
		throws NoSuchResourceException;

	/**
	* Returns the reference resource where groupId = &#63; and referenceId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @return the matching reference resource, or <code>null</code> if a matching reference resource could not be found
	*/
	public ReferenceResource fetchByG_R(long groupId, String referenceId);

	/**
	* Returns the reference resource where groupId = &#63; and referenceId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching reference resource, or <code>null</code> if a matching reference resource could not be found
	*/
	public ReferenceResource fetchByG_R(long groupId, String referenceId,
		boolean retrieveFromCache);

	/**
	* Removes the reference resource where groupId = &#63; and referenceId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @return the reference resource that was removed
	*/
	public ReferenceResource removeByG_R(long groupId, String referenceId)
		throws NoSuchResourceException;

	/**
	* Returns the number of reference resources where groupId = &#63; and referenceId = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @return the number of matching reference resources
	*/
	public int countByG_R(long groupId, String referenceId);

	/**
	* Caches the reference resource in the entity cache if it is enabled.
	*
	* @param referenceResource the reference resource
	*/
	public void cacheResult(ReferenceResource referenceResource);

	/**
	* Caches the reference resources in the entity cache if it is enabled.
	*
	* @param referenceResources the reference resources
	*/
	public void cacheResult(
		java.util.List<ReferenceResource> referenceResources);

	/**
	* Creates a new reference resource with the primary key. Does not add the reference resource to the database.
	*
	* @param resourcePrimKey the primary key for the new reference resource
	* @return the new reference resource
	*/
	public ReferenceResource create(long resourcePrimKey);

	/**
	* Removes the reference resource with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param resourcePrimKey the primary key of the reference resource
	* @return the reference resource that was removed
	* @throws NoSuchResourceException if a reference resource with the primary key could not be found
	*/
	public ReferenceResource remove(long resourcePrimKey)
		throws NoSuchResourceException;

	public ReferenceResource updateImpl(ReferenceResource referenceResource);

	/**
	* Returns the reference resource with the primary key or throws a {@link NoSuchResourceException} if it could not be found.
	*
	* @param resourcePrimKey the primary key of the reference resource
	* @return the reference resource
	* @throws NoSuchResourceException if a reference resource with the primary key could not be found
	*/
	public ReferenceResource findByPrimaryKey(long resourcePrimKey)
		throws NoSuchResourceException;

	/**
	* Returns the reference resource with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param resourcePrimKey the primary key of the reference resource
	* @return the reference resource, or <code>null</code> if a reference resource with the primary key could not be found
	*/
	public ReferenceResource fetchByPrimaryKey(long resourcePrimKey);

	@Override
	public java.util.Map<java.io.Serializable, ReferenceResource> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the reference resources.
	*
	* @return the reference resources
	*/
	public java.util.List<ReferenceResource> findAll();

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
	public java.util.List<ReferenceResource> findAll(int start, int end);

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
	public java.util.List<ReferenceResource> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceResource> orderByComparator);

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
	public java.util.List<ReferenceResource> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceResource> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the reference resources from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of reference resources.
	*
	* @return the number of reference resources
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}