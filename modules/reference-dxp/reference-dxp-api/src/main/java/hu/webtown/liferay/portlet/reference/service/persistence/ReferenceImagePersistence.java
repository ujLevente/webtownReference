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

import hu.webtown.liferay.portlet.reference.exception.NoSuchImageException;
import hu.webtown.liferay.portlet.reference.model.ReferenceImage;

/**
 * The persistence interface for the reference image service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see hu.webtown.liferay.portlet.reference.service.persistence.impl.ReferenceImagePersistenceImpl
 * @see ReferenceImageUtil
 * @generated
 */
@ProviderType
public interface ReferenceImagePersistence extends BasePersistence<ReferenceImage> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ReferenceImageUtil} to access the reference image persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the reference images where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching reference images
	*/
	public java.util.List<ReferenceImage> findByUuid(String uuid);

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
	public java.util.List<ReferenceImage> findByUuid(String uuid, int start,
		int end);

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
	public java.util.List<ReferenceImage> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator);

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
	public java.util.List<ReferenceImage> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first reference image in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference image
	* @throws NoSuchImageException if a matching reference image could not be found
	*/
	public ReferenceImage findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException;

	/**
	* Returns the first reference image in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference image, or <code>null</code> if a matching reference image could not be found
	*/
	public ReferenceImage fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator);

	/**
	* Returns the last reference image in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference image
	* @throws NoSuchImageException if a matching reference image could not be found
	*/
	public ReferenceImage findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException;

	/**
	* Returns the last reference image in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference image, or <code>null</code> if a matching reference image could not be found
	*/
	public ReferenceImage fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator);

	/**
	* Returns the reference images before and after the current reference image in the ordered set where uuid = &#63;.
	*
	* @param referenceImageId the primary key of the current reference image
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference image
	* @throws NoSuchImageException if a reference image with the primary key could not be found
	*/
	public ReferenceImage[] findByUuid_PrevAndNext(long referenceImageId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException;

	/**
	* Removes all the reference images where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of reference images where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching reference images
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the reference image where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchImageException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching reference image
	* @throws NoSuchImageException if a matching reference image could not be found
	*/
	public ReferenceImage findByUUID_G(String uuid, long groupId)
		throws NoSuchImageException;

	/**
	* Returns the reference image where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching reference image, or <code>null</code> if a matching reference image could not be found
	*/
	public ReferenceImage fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the reference image where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching reference image, or <code>null</code> if a matching reference image could not be found
	*/
	public ReferenceImage fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the reference image where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the reference image that was removed
	*/
	public ReferenceImage removeByUUID_G(String uuid, long groupId)
		throws NoSuchImageException;

	/**
	* Returns the number of reference images where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching reference images
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the reference images where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching reference images
	*/
	public java.util.List<ReferenceImage> findByUuid_C(String uuid,
		long companyId);

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
	public java.util.List<ReferenceImage> findByUuid_C(String uuid,
		long companyId, int start, int end);

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
	public java.util.List<ReferenceImage> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator);

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
	public java.util.List<ReferenceImage> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first reference image in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference image
	* @throws NoSuchImageException if a matching reference image could not be found
	*/
	public ReferenceImage findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException;

	/**
	* Returns the first reference image in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference image, or <code>null</code> if a matching reference image could not be found
	*/
	public ReferenceImage fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator);

	/**
	* Returns the last reference image in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference image
	* @throws NoSuchImageException if a matching reference image could not be found
	*/
	public ReferenceImage findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException;

	/**
	* Returns the last reference image in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference image, or <code>null</code> if a matching reference image could not be found
	*/
	public ReferenceImage fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator);

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
	public ReferenceImage[] findByUuid_C_PrevAndNext(long referenceImageId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException;

	/**
	* Removes all the reference images where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of reference images where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching reference images
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the reference images where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching reference images
	*/
	public java.util.List<ReferenceImage> findByGroupId(long groupId);

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
	public java.util.List<ReferenceImage> findByGroupId(long groupId,
		int start, int end);

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
	public java.util.List<ReferenceImage> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator);

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
	public java.util.List<ReferenceImage> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first reference image in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference image
	* @throws NoSuchImageException if a matching reference image could not be found
	*/
	public ReferenceImage findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException;

	/**
	* Returns the first reference image in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference image, or <code>null</code> if a matching reference image could not be found
	*/
	public ReferenceImage fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator);

	/**
	* Returns the last reference image in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference image
	* @throws NoSuchImageException if a matching reference image could not be found
	*/
	public ReferenceImage findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException;

	/**
	* Returns the last reference image in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference image, or <code>null</code> if a matching reference image could not be found
	*/
	public ReferenceImage fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator);

	/**
	* Returns the reference images before and after the current reference image in the ordered set where groupId = &#63;.
	*
	* @param referenceImageId the primary key of the current reference image
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next reference image
	* @throws NoSuchImageException if a reference image with the primary key could not be found
	*/
	public ReferenceImage[] findByGroupId_PrevAndNext(long referenceImageId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException;

	/**
	* Removes all the reference images where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of reference images where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching reference images
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the reference images where groupId = &#63; and referenceId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @return the matching reference images
	*/
	public java.util.List<ReferenceImage> findByG_R_V(long groupId,
		String referenceId, double version);

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
	public java.util.List<ReferenceImage> findByG_R_V(long groupId,
		String referenceId, double version, int start, int end);

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
	public java.util.List<ReferenceImage> findByG_R_V(long groupId,
		String referenceId, double version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator);

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
	public java.util.List<ReferenceImage> findByG_R_V(long groupId,
		String referenceId, double version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator,
		boolean retrieveFromCache);

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
	public ReferenceImage findByG_R_V_First(long groupId, String referenceId,
		double version,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException;

	/**
	* Returns the first reference image in the ordered set where groupId = &#63; and referenceId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching reference image, or <code>null</code> if a matching reference image could not be found
	*/
	public ReferenceImage fetchByG_R_V_First(long groupId, String referenceId,
		double version,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator);

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
	public ReferenceImage findByG_R_V_Last(long groupId, String referenceId,
		double version,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException;

	/**
	* Returns the last reference image in the ordered set where groupId = &#63; and referenceId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching reference image, or <code>null</code> if a matching reference image could not be found
	*/
	public ReferenceImage fetchByG_R_V_Last(long groupId, String referenceId,
		double version,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator);

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
	public ReferenceImage[] findByG_R_V_PrevAndNext(long referenceImageId,
		long groupId, String referenceId, double version,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException;

	/**
	* Removes all the reference images where groupId = &#63; and referenceId = &#63; and version = &#63; from the database.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	*/
	public void removeByG_R_V(long groupId, String referenceId, double version);

	/**
	* Returns the number of reference images where groupId = &#63; and referenceId = &#63; and version = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @return the number of matching reference images
	*/
	public int countByG_R_V(long groupId, String referenceId, double version);

	/**
	* Returns all the reference images where groupId = &#63; and referenceId = &#63; and version = &#63; and imageType = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @param imageType the image type
	* @return the matching reference images
	*/
	public java.util.List<ReferenceImage> findByG_R_V_I(long groupId,
		String referenceId, double version, int imageType);

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
	public java.util.List<ReferenceImage> findByG_R_V_I(long groupId,
		String referenceId, double version, int imageType, int start, int end);

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
	public java.util.List<ReferenceImage> findByG_R_V_I(long groupId,
		String referenceId, double version, int imageType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator);

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
	public java.util.List<ReferenceImage> findByG_R_V_I(long groupId,
		String referenceId, double version, int imageType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator,
		boolean retrieveFromCache);

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
	public ReferenceImage findByG_R_V_I_First(long groupId, String referenceId,
		double version, int imageType,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException;

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
	public ReferenceImage fetchByG_R_V_I_First(long groupId,
		String referenceId, double version, int imageType,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator);

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
	public ReferenceImage findByG_R_V_I_Last(long groupId, String referenceId,
		double version, int imageType,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException;

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
	public ReferenceImage fetchByG_R_V_I_Last(long groupId, String referenceId,
		double version, int imageType,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator);

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
	public ReferenceImage[] findByG_R_V_I_PrevAndNext(long referenceImageId,
		long groupId, String referenceId, double version, int imageType,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException;

	/**
	* Removes all the reference images where groupId = &#63; and referenceId = &#63; and version = &#63; and imageType = &#63; from the database.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @param imageType the image type
	*/
	public void removeByG_R_V_I(long groupId, String referenceId,
		double version, int imageType);

	/**
	* Returns the number of reference images where groupId = &#63; and referenceId = &#63; and version = &#63; and imageType = &#63;.
	*
	* @param groupId the group ID
	* @param referenceId the reference ID
	* @param version the version
	* @param imageType the image type
	* @return the number of matching reference images
	*/
	public int countByG_R_V_I(long groupId, String referenceId, double version,
		int imageType);

	/**
	* Caches the reference image in the entity cache if it is enabled.
	*
	* @param referenceImage the reference image
	*/
	public void cacheResult(ReferenceImage referenceImage);

	/**
	* Caches the reference images in the entity cache if it is enabled.
	*
	* @param referenceImages the reference images
	*/
	public void cacheResult(java.util.List<ReferenceImage> referenceImages);

	/**
	* Creates a new reference image with the primary key. Does not add the reference image to the database.
	*
	* @param referenceImageId the primary key for the new reference image
	* @return the new reference image
	*/
	public ReferenceImage create(long referenceImageId);

	/**
	* Removes the reference image with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param referenceImageId the primary key of the reference image
	* @return the reference image that was removed
	* @throws NoSuchImageException if a reference image with the primary key could not be found
	*/
	public ReferenceImage remove(long referenceImageId)
		throws NoSuchImageException;

	public ReferenceImage updateImpl(ReferenceImage referenceImage);

	/**
	* Returns the reference image with the primary key or throws a {@link NoSuchImageException} if it could not be found.
	*
	* @param referenceImageId the primary key of the reference image
	* @return the reference image
	* @throws NoSuchImageException if a reference image with the primary key could not be found
	*/
	public ReferenceImage findByPrimaryKey(long referenceImageId)
		throws NoSuchImageException;

	/**
	* Returns the reference image with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param referenceImageId the primary key of the reference image
	* @return the reference image, or <code>null</code> if a reference image with the primary key could not be found
	*/
	public ReferenceImage fetchByPrimaryKey(long referenceImageId);

	@Override
	public java.util.Map<java.io.Serializable, ReferenceImage> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the reference images.
	*
	* @return the reference images
	*/
	public java.util.List<ReferenceImage> findAll();

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
	public java.util.List<ReferenceImage> findAll(int start, int end);

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
	public java.util.List<ReferenceImage> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator);

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
	public java.util.List<ReferenceImage> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReferenceImage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the reference images from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of reference images.
	*
	* @return the number of reference images
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}