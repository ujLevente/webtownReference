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

package hu.webtown.liferay.portlet.reference.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import hu.webtown.liferay.portlet.reference.exception.NoSuchImageException;
import hu.webtown.liferay.portlet.reference.model.ReferenceImage;
import hu.webtown.liferay.portlet.reference.model.impl.ReferenceImageImpl;
import hu.webtown.liferay.portlet.reference.model.impl.ReferenceImageModelImpl;
import hu.webtown.liferay.portlet.reference.service.persistence.ReferenceImagePersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the reference image service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReferenceImagePersistence
 * @see hu.webtown.liferay.portlet.reference.service.persistence.ReferenceImageUtil
 * @generated
 */
@ProviderType
public class ReferenceImagePersistenceImpl extends BasePersistenceImpl<ReferenceImage>
	implements ReferenceImagePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ReferenceImageUtil} to access the reference image persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ReferenceImageImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceImageModelImpl.FINDER_CACHE_ENABLED,
			ReferenceImageImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceImageModelImpl.FINDER_CACHE_ENABLED,
			ReferenceImageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceImageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceImageModelImpl.FINDER_CACHE_ENABLED,
			ReferenceImageImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceImageModelImpl.FINDER_CACHE_ENABLED,
			ReferenceImageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ReferenceImageModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceImageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the reference images where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching reference images
	 */
	@Override
	public List<ReferenceImage> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<ReferenceImage> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
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
	@Override
	public List<ReferenceImage> findByUuid(String uuid, int start, int end,
		OrderByComparator<ReferenceImage> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
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
	@Override
	public List<ReferenceImage> findByUuid(String uuid, int start, int end,
		OrderByComparator<ReferenceImage> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<ReferenceImage> list = null;

		if (retrieveFromCache) {
			list = (List<ReferenceImage>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ReferenceImage referenceImage : list) {
					if (!Objects.equals(uuid, referenceImage.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_REFERENCEIMAGE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReferenceImageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<ReferenceImage>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ReferenceImage>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first reference image in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference image
	 * @throws NoSuchImageException if a matching reference image could not be found
	 */
	@Override
	public ReferenceImage findByUuid_First(String uuid,
		OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException {
		ReferenceImage referenceImage = fetchByUuid_First(uuid,
				orderByComparator);

		if (referenceImage != null) {
			return referenceImage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchImageException(msg.toString());
	}

	/**
	 * Returns the first reference image in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference image, or <code>null</code> if a matching reference image could not be found
	 */
	@Override
	public ReferenceImage fetchByUuid_First(String uuid,
		OrderByComparator<ReferenceImage> orderByComparator) {
		List<ReferenceImage> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last reference image in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference image
	 * @throws NoSuchImageException if a matching reference image could not be found
	 */
	@Override
	public ReferenceImage findByUuid_Last(String uuid,
		OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException {
		ReferenceImage referenceImage = fetchByUuid_Last(uuid, orderByComparator);

		if (referenceImage != null) {
			return referenceImage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchImageException(msg.toString());
	}

	/**
	 * Returns the last reference image in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference image, or <code>null</code> if a matching reference image could not be found
	 */
	@Override
	public ReferenceImage fetchByUuid_Last(String uuid,
		OrderByComparator<ReferenceImage> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ReferenceImage> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public ReferenceImage[] findByUuid_PrevAndNext(long referenceImageId,
		String uuid, OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException {
		ReferenceImage referenceImage = findByPrimaryKey(referenceImageId);

		Session session = null;

		try {
			session = openSession();

			ReferenceImage[] array = new ReferenceImageImpl[3];

			array[0] = getByUuid_PrevAndNext(session, referenceImage, uuid,
					orderByComparator, true);

			array[1] = referenceImage;

			array[2] = getByUuid_PrevAndNext(session, referenceImage, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ReferenceImage getByUuid_PrevAndNext(Session session,
		ReferenceImage referenceImage, String uuid,
		OrderByComparator<ReferenceImage> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REFERENCEIMAGE_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals("")) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(ReferenceImageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(referenceImage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ReferenceImage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the reference images where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ReferenceImage referenceImage : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(referenceImage);
		}
	}

	/**
	 * Returns the number of reference images where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching reference images
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_REFERENCEIMAGE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "referenceImage.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "referenceImage.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(referenceImage.uuid IS NULL OR referenceImage.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceImageModelImpl.FINDER_CACHE_ENABLED,
			ReferenceImageImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ReferenceImageModelImpl.UUID_COLUMN_BITMASK |
			ReferenceImageModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceImageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the reference image where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchImageException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching reference image
	 * @throws NoSuchImageException if a matching reference image could not be found
	 */
	@Override
	public ReferenceImage findByUUID_G(String uuid, long groupId)
		throws NoSuchImageException {
		ReferenceImage referenceImage = fetchByUUID_G(uuid, groupId);

		if (referenceImage == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchImageException(msg.toString());
		}

		return referenceImage;
	}

	/**
	 * Returns the reference image where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching reference image, or <code>null</code> if a matching reference image could not be found
	 */
	@Override
	public ReferenceImage fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the reference image where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching reference image, or <code>null</code> if a matching reference image could not be found
	 */
	@Override
	public ReferenceImage fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ReferenceImage) {
			ReferenceImage referenceImage = (ReferenceImage)result;

			if (!Objects.equals(uuid, referenceImage.getUuid()) ||
					(groupId != referenceImage.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_REFERENCEIMAGE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<ReferenceImage> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ReferenceImage referenceImage = list.get(0);

					result = referenceImage;

					cacheResult(referenceImage);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (ReferenceImage)result;
		}
	}

	/**
	 * Removes the reference image where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the reference image that was removed
	 */
	@Override
	public ReferenceImage removeByUUID_G(String uuid, long groupId)
		throws NoSuchImageException {
		ReferenceImage referenceImage = findByUUID_G(uuid, groupId);

		return remove(referenceImage);
	}

	/**
	 * Returns the number of reference images where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching reference images
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REFERENCEIMAGE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "referenceImage.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "referenceImage.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(referenceImage.uuid IS NULL OR referenceImage.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "referenceImage.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceImageModelImpl.FINDER_CACHE_ENABLED,
			ReferenceImageImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceImageModelImpl.FINDER_CACHE_ENABLED,
			ReferenceImageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ReferenceImageModelImpl.UUID_COLUMN_BITMASK |
			ReferenceImageModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceImageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the reference images where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching reference images
	 */
	@Override
	public List<ReferenceImage> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<ReferenceImage> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
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
	@Override
	public List<ReferenceImage> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ReferenceImage> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
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
	@Override
	public List<ReferenceImage> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<ReferenceImage> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<ReferenceImage> list = null;

		if (retrieveFromCache) {
			list = (List<ReferenceImage>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ReferenceImage referenceImage : list) {
					if (!Objects.equals(uuid, referenceImage.getUuid()) ||
							(companyId != referenceImage.getCompanyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_REFERENCEIMAGE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReferenceImageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<ReferenceImage>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ReferenceImage>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public ReferenceImage findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException {
		ReferenceImage referenceImage = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (referenceImage != null) {
			return referenceImage;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchImageException(msg.toString());
	}

	/**
	 * Returns the first reference image in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference image, or <code>null</code> if a matching reference image could not be found
	 */
	@Override
	public ReferenceImage fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ReferenceImage> orderByComparator) {
		List<ReferenceImage> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public ReferenceImage findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException {
		ReferenceImage referenceImage = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (referenceImage != null) {
			return referenceImage;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchImageException(msg.toString());
	}

	/**
	 * Returns the last reference image in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference image, or <code>null</code> if a matching reference image could not be found
	 */
	@Override
	public ReferenceImage fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ReferenceImage> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ReferenceImage> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public ReferenceImage[] findByUuid_C_PrevAndNext(long referenceImageId,
		String uuid, long companyId,
		OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException {
		ReferenceImage referenceImage = findByPrimaryKey(referenceImageId);

		Session session = null;

		try {
			session = openSession();

			ReferenceImage[] array = new ReferenceImageImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, referenceImage, uuid,
					companyId, orderByComparator, true);

			array[1] = referenceImage;

			array[2] = getByUuid_C_PrevAndNext(session, referenceImage, uuid,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ReferenceImage getByUuid_C_PrevAndNext(Session session,
		ReferenceImage referenceImage, String uuid, long companyId,
		OrderByComparator<ReferenceImage> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_REFERENCEIMAGE_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals("")) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(ReferenceImageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(referenceImage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ReferenceImage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the reference images where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ReferenceImage referenceImage : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(referenceImage);
		}
	}

	/**
	 * Returns the number of reference images where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching reference images
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REFERENCEIMAGE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "referenceImage.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "referenceImage.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(referenceImage.uuid IS NULL OR referenceImage.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "referenceImage.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceImageModelImpl.FINDER_CACHE_ENABLED,
			ReferenceImageImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceImageModelImpl.FINDER_CACHE_ENABLED,
			ReferenceImageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			ReferenceImageModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceImageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the reference images where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching reference images
	 */
	@Override
	public List<ReferenceImage> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<ReferenceImage> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
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
	@Override
	public List<ReferenceImage> findByGroupId(long groupId, int start, int end,
		OrderByComparator<ReferenceImage> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
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
	@Override
	public List<ReferenceImage> findByGroupId(long groupId, int start, int end,
		OrderByComparator<ReferenceImage> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<ReferenceImage> list = null;

		if (retrieveFromCache) {
			list = (List<ReferenceImage>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ReferenceImage referenceImage : list) {
					if ((groupId != referenceImage.getGroupId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_REFERENCEIMAGE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReferenceImageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ReferenceImage>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ReferenceImage>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first reference image in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference image
	 * @throws NoSuchImageException if a matching reference image could not be found
	 */
	@Override
	public ReferenceImage findByGroupId_First(long groupId,
		OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException {
		ReferenceImage referenceImage = fetchByGroupId_First(groupId,
				orderByComparator);

		if (referenceImage != null) {
			return referenceImage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchImageException(msg.toString());
	}

	/**
	 * Returns the first reference image in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference image, or <code>null</code> if a matching reference image could not be found
	 */
	@Override
	public ReferenceImage fetchByGroupId_First(long groupId,
		OrderByComparator<ReferenceImage> orderByComparator) {
		List<ReferenceImage> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last reference image in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference image
	 * @throws NoSuchImageException if a matching reference image could not be found
	 */
	@Override
	public ReferenceImage findByGroupId_Last(long groupId,
		OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException {
		ReferenceImage referenceImage = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (referenceImage != null) {
			return referenceImage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchImageException(msg.toString());
	}

	/**
	 * Returns the last reference image in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference image, or <code>null</code> if a matching reference image could not be found
	 */
	@Override
	public ReferenceImage fetchByGroupId_Last(long groupId,
		OrderByComparator<ReferenceImage> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<ReferenceImage> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public ReferenceImage[] findByGroupId_PrevAndNext(long referenceImageId,
		long groupId, OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException {
		ReferenceImage referenceImage = findByPrimaryKey(referenceImageId);

		Session session = null;

		try {
			session = openSession();

			ReferenceImage[] array = new ReferenceImageImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, referenceImage,
					groupId, orderByComparator, true);

			array[1] = referenceImage;

			array[2] = getByGroupId_PrevAndNext(session, referenceImage,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ReferenceImage getByGroupId_PrevAndNext(Session session,
		ReferenceImage referenceImage, long groupId,
		OrderByComparator<ReferenceImage> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REFERENCEIMAGE_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(ReferenceImageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(referenceImage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ReferenceImage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the reference images where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (ReferenceImage referenceImage : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(referenceImage);
		}
	}

	/**
	 * Returns the number of reference images where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching reference images
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_REFERENCEIMAGE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "referenceImage.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_R_V = new FinderPath(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceImageModelImpl.FINDER_CACHE_ENABLED,
			ReferenceImageImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_R_V",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Double.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R_V = new FinderPath(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceImageModelImpl.FINDER_CACHE_ENABLED,
			ReferenceImageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_R_V",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Double.class.getName()
			},
			ReferenceImageModelImpl.GROUPID_COLUMN_BITMASK |
			ReferenceImageModelImpl.REFERENCEID_COLUMN_BITMASK |
			ReferenceImageModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_R_V = new FinderPath(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceImageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_R_V",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Double.class.getName()
			});

	/**
	 * Returns all the reference images where groupId = &#63; and referenceId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param version the version
	 * @return the matching reference images
	 */
	@Override
	public List<ReferenceImage> findByG_R_V(long groupId, String referenceId,
		double version) {
		return findByG_R_V(groupId, referenceId, version, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<ReferenceImage> findByG_R_V(long groupId, String referenceId,
		double version, int start, int end) {
		return findByG_R_V(groupId, referenceId, version, start, end, null);
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
	@Override
	public List<ReferenceImage> findByG_R_V(long groupId, String referenceId,
		double version, int start, int end,
		OrderByComparator<ReferenceImage> orderByComparator) {
		return findByG_R_V(groupId, referenceId, version, start, end,
			orderByComparator, true);
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
	@Override
	public List<ReferenceImage> findByG_R_V(long groupId, String referenceId,
		double version, int start, int end,
		OrderByComparator<ReferenceImage> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R_V;
			finderArgs = new Object[] { groupId, referenceId, version };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_R_V;
			finderArgs = new Object[] {
					groupId, referenceId, version,
					
					start, end, orderByComparator
				};
		}

		List<ReferenceImage> list = null;

		if (retrieveFromCache) {
			list = (List<ReferenceImage>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ReferenceImage referenceImage : list) {
					if ((groupId != referenceImage.getGroupId()) ||
							!Objects.equals(referenceId,
								referenceImage.getReferenceId()) ||
							(version != referenceImage.getVersion())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_REFERENCEIMAGE_WHERE);

			query.append(_FINDER_COLUMN_G_R_V_GROUPID_2);

			boolean bindReferenceId = false;

			if (referenceId == null) {
				query.append(_FINDER_COLUMN_G_R_V_REFERENCEID_1);
			}
			else if (referenceId.equals("")) {
				query.append(_FINDER_COLUMN_G_R_V_REFERENCEID_3);
			}
			else {
				bindReferenceId = true;

				query.append(_FINDER_COLUMN_G_R_V_REFERENCEID_2);
			}

			query.append(_FINDER_COLUMN_G_R_V_VERSION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReferenceImageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindReferenceId) {
					qPos.add(referenceId);
				}

				qPos.add(version);

				if (!pagination) {
					list = (List<ReferenceImage>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ReferenceImage>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public ReferenceImage findByG_R_V_First(long groupId, String referenceId,
		double version, OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException {
		ReferenceImage referenceImage = fetchByG_R_V_First(groupId,
				referenceId, version, orderByComparator);

		if (referenceImage != null) {
			return referenceImage;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", referenceId=");
		msg.append(referenceId);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchImageException(msg.toString());
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
	@Override
	public ReferenceImage fetchByG_R_V_First(long groupId, String referenceId,
		double version, OrderByComparator<ReferenceImage> orderByComparator) {
		List<ReferenceImage> list = findByG_R_V(groupId, referenceId, version,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public ReferenceImage findByG_R_V_Last(long groupId, String referenceId,
		double version, OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException {
		ReferenceImage referenceImage = fetchByG_R_V_Last(groupId, referenceId,
				version, orderByComparator);

		if (referenceImage != null) {
			return referenceImage;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", referenceId=");
		msg.append(referenceId);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchImageException(msg.toString());
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
	@Override
	public ReferenceImage fetchByG_R_V_Last(long groupId, String referenceId,
		double version, OrderByComparator<ReferenceImage> orderByComparator) {
		int count = countByG_R_V(groupId, referenceId, version);

		if (count == 0) {
			return null;
		}

		List<ReferenceImage> list = findByG_R_V(groupId, referenceId, version,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public ReferenceImage[] findByG_R_V_PrevAndNext(long referenceImageId,
		long groupId, String referenceId, double version,
		OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException {
		ReferenceImage referenceImage = findByPrimaryKey(referenceImageId);

		Session session = null;

		try {
			session = openSession();

			ReferenceImage[] array = new ReferenceImageImpl[3];

			array[0] = getByG_R_V_PrevAndNext(session, referenceImage, groupId,
					referenceId, version, orderByComparator, true);

			array[1] = referenceImage;

			array[2] = getByG_R_V_PrevAndNext(session, referenceImage, groupId,
					referenceId, version, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ReferenceImage getByG_R_V_PrevAndNext(Session session,
		ReferenceImage referenceImage, long groupId, String referenceId,
		double version, OrderByComparator<ReferenceImage> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_REFERENCEIMAGE_WHERE);

		query.append(_FINDER_COLUMN_G_R_V_GROUPID_2);

		boolean bindReferenceId = false;

		if (referenceId == null) {
			query.append(_FINDER_COLUMN_G_R_V_REFERENCEID_1);
		}
		else if (referenceId.equals("")) {
			query.append(_FINDER_COLUMN_G_R_V_REFERENCEID_3);
		}
		else {
			bindReferenceId = true;

			query.append(_FINDER_COLUMN_G_R_V_REFERENCEID_2);
		}

		query.append(_FINDER_COLUMN_G_R_V_VERSION_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(ReferenceImageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindReferenceId) {
			qPos.add(referenceId);
		}

		qPos.add(version);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(referenceImage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ReferenceImage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the reference images where groupId = &#63; and referenceId = &#63; and version = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param version the version
	 */
	@Override
	public void removeByG_R_V(long groupId, String referenceId, double version) {
		for (ReferenceImage referenceImage : findByG_R_V(groupId, referenceId,
				version, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(referenceImage);
		}
	}

	/**
	 * Returns the number of reference images where groupId = &#63; and referenceId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param version the version
	 * @return the number of matching reference images
	 */
	@Override
	public int countByG_R_V(long groupId, String referenceId, double version) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_R_V;

		Object[] finderArgs = new Object[] { groupId, referenceId, version };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_REFERENCEIMAGE_WHERE);

			query.append(_FINDER_COLUMN_G_R_V_GROUPID_2);

			boolean bindReferenceId = false;

			if (referenceId == null) {
				query.append(_FINDER_COLUMN_G_R_V_REFERENCEID_1);
			}
			else if (referenceId.equals("")) {
				query.append(_FINDER_COLUMN_G_R_V_REFERENCEID_3);
			}
			else {
				bindReferenceId = true;

				query.append(_FINDER_COLUMN_G_R_V_REFERENCEID_2);
			}

			query.append(_FINDER_COLUMN_G_R_V_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindReferenceId) {
					qPos.add(referenceId);
				}

				qPos.add(version);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_R_V_GROUPID_2 = "referenceImage.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_R_V_REFERENCEID_1 = "referenceImage.referenceId IS NULL AND ";
	private static final String _FINDER_COLUMN_G_R_V_REFERENCEID_2 = "referenceImage.referenceId = ? AND ";
	private static final String _FINDER_COLUMN_G_R_V_REFERENCEID_3 = "(referenceImage.referenceId IS NULL OR referenceImage.referenceId = '') AND ";
	private static final String _FINDER_COLUMN_G_R_V_VERSION_2 = "referenceImage.version = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_R_V_I = new FinderPath(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceImageModelImpl.FINDER_CACHE_ENABLED,
			ReferenceImageImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_R_V_I",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Double.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R_V_I =
		new FinderPath(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceImageModelImpl.FINDER_CACHE_ENABLED,
			ReferenceImageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_R_V_I",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Double.class.getName(), Integer.class.getName()
			},
			ReferenceImageModelImpl.GROUPID_COLUMN_BITMASK |
			ReferenceImageModelImpl.REFERENCEID_COLUMN_BITMASK |
			ReferenceImageModelImpl.VERSION_COLUMN_BITMASK |
			ReferenceImageModelImpl.IMAGETYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_R_V_I = new FinderPath(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceImageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_R_V_I",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Double.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns all the reference images where groupId = &#63; and referenceId = &#63; and version = &#63; and imageType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param version the version
	 * @param imageType the image type
	 * @return the matching reference images
	 */
	@Override
	public List<ReferenceImage> findByG_R_V_I(long groupId, String referenceId,
		double version, int imageType) {
		return findByG_R_V_I(groupId, referenceId, version, imageType,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<ReferenceImage> findByG_R_V_I(long groupId, String referenceId,
		double version, int imageType, int start, int end) {
		return findByG_R_V_I(groupId, referenceId, version, imageType, start,
			end, null);
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
	@Override
	public List<ReferenceImage> findByG_R_V_I(long groupId, String referenceId,
		double version, int imageType, int start, int end,
		OrderByComparator<ReferenceImage> orderByComparator) {
		return findByG_R_V_I(groupId, referenceId, version, imageType, start,
			end, orderByComparator, true);
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
	@Override
	public List<ReferenceImage> findByG_R_V_I(long groupId, String referenceId,
		double version, int imageType, int start, int end,
		OrderByComparator<ReferenceImage> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R_V_I;
			finderArgs = new Object[] { groupId, referenceId, version, imageType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_R_V_I;
			finderArgs = new Object[] {
					groupId, referenceId, version, imageType,
					
					start, end, orderByComparator
				};
		}

		List<ReferenceImage> list = null;

		if (retrieveFromCache) {
			list = (List<ReferenceImage>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ReferenceImage referenceImage : list) {
					if ((groupId != referenceImage.getGroupId()) ||
							!Objects.equals(referenceId,
								referenceImage.getReferenceId()) ||
							(version != referenceImage.getVersion()) ||
							(imageType != referenceImage.getImageType())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_REFERENCEIMAGE_WHERE);

			query.append(_FINDER_COLUMN_G_R_V_I_GROUPID_2);

			boolean bindReferenceId = false;

			if (referenceId == null) {
				query.append(_FINDER_COLUMN_G_R_V_I_REFERENCEID_1);
			}
			else if (referenceId.equals("")) {
				query.append(_FINDER_COLUMN_G_R_V_I_REFERENCEID_3);
			}
			else {
				bindReferenceId = true;

				query.append(_FINDER_COLUMN_G_R_V_I_REFERENCEID_2);
			}

			query.append(_FINDER_COLUMN_G_R_V_I_VERSION_2);

			query.append(_FINDER_COLUMN_G_R_V_I_IMAGETYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReferenceImageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindReferenceId) {
					qPos.add(referenceId);
				}

				qPos.add(version);

				qPos.add(imageType);

				if (!pagination) {
					list = (List<ReferenceImage>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ReferenceImage>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public ReferenceImage findByG_R_V_I_First(long groupId, String referenceId,
		double version, int imageType,
		OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException {
		ReferenceImage referenceImage = fetchByG_R_V_I_First(groupId,
				referenceId, version, imageType, orderByComparator);

		if (referenceImage != null) {
			return referenceImage;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", referenceId=");
		msg.append(referenceId);

		msg.append(", version=");
		msg.append(version);

		msg.append(", imageType=");
		msg.append(imageType);

		msg.append("}");

		throw new NoSuchImageException(msg.toString());
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
	@Override
	public ReferenceImage fetchByG_R_V_I_First(long groupId,
		String referenceId, double version, int imageType,
		OrderByComparator<ReferenceImage> orderByComparator) {
		List<ReferenceImage> list = findByG_R_V_I(groupId, referenceId,
				version, imageType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public ReferenceImage findByG_R_V_I_Last(long groupId, String referenceId,
		double version, int imageType,
		OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException {
		ReferenceImage referenceImage = fetchByG_R_V_I_Last(groupId,
				referenceId, version, imageType, orderByComparator);

		if (referenceImage != null) {
			return referenceImage;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", referenceId=");
		msg.append(referenceId);

		msg.append(", version=");
		msg.append(version);

		msg.append(", imageType=");
		msg.append(imageType);

		msg.append("}");

		throw new NoSuchImageException(msg.toString());
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
	@Override
	public ReferenceImage fetchByG_R_V_I_Last(long groupId, String referenceId,
		double version, int imageType,
		OrderByComparator<ReferenceImage> orderByComparator) {
		int count = countByG_R_V_I(groupId, referenceId, version, imageType);

		if (count == 0) {
			return null;
		}

		List<ReferenceImage> list = findByG_R_V_I(groupId, referenceId,
				version, imageType, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public ReferenceImage[] findByG_R_V_I_PrevAndNext(long referenceImageId,
		long groupId, String referenceId, double version, int imageType,
		OrderByComparator<ReferenceImage> orderByComparator)
		throws NoSuchImageException {
		ReferenceImage referenceImage = findByPrimaryKey(referenceImageId);

		Session session = null;

		try {
			session = openSession();

			ReferenceImage[] array = new ReferenceImageImpl[3];

			array[0] = getByG_R_V_I_PrevAndNext(session, referenceImage,
					groupId, referenceId, version, imageType,
					orderByComparator, true);

			array[1] = referenceImage;

			array[2] = getByG_R_V_I_PrevAndNext(session, referenceImage,
					groupId, referenceId, version, imageType,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ReferenceImage getByG_R_V_I_PrevAndNext(Session session,
		ReferenceImage referenceImage, long groupId, String referenceId,
		double version, int imageType,
		OrderByComparator<ReferenceImage> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_REFERENCEIMAGE_WHERE);

		query.append(_FINDER_COLUMN_G_R_V_I_GROUPID_2);

		boolean bindReferenceId = false;

		if (referenceId == null) {
			query.append(_FINDER_COLUMN_G_R_V_I_REFERENCEID_1);
		}
		else if (referenceId.equals("")) {
			query.append(_FINDER_COLUMN_G_R_V_I_REFERENCEID_3);
		}
		else {
			bindReferenceId = true;

			query.append(_FINDER_COLUMN_G_R_V_I_REFERENCEID_2);
		}

		query.append(_FINDER_COLUMN_G_R_V_I_VERSION_2);

		query.append(_FINDER_COLUMN_G_R_V_I_IMAGETYPE_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(ReferenceImageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindReferenceId) {
			qPos.add(referenceId);
		}

		qPos.add(version);

		qPos.add(imageType);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(referenceImage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ReferenceImage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the reference images where groupId = &#63; and referenceId = &#63; and version = &#63; and imageType = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param version the version
	 * @param imageType the image type
	 */
	@Override
	public void removeByG_R_V_I(long groupId, String referenceId,
		double version, int imageType) {
		for (ReferenceImage referenceImage : findByG_R_V_I(groupId,
				referenceId, version, imageType, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(referenceImage);
		}
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
	@Override
	public int countByG_R_V_I(long groupId, String referenceId, double version,
		int imageType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_R_V_I;

		Object[] finderArgs = new Object[] {
				groupId, referenceId, version, imageType
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_REFERENCEIMAGE_WHERE);

			query.append(_FINDER_COLUMN_G_R_V_I_GROUPID_2);

			boolean bindReferenceId = false;

			if (referenceId == null) {
				query.append(_FINDER_COLUMN_G_R_V_I_REFERENCEID_1);
			}
			else if (referenceId.equals("")) {
				query.append(_FINDER_COLUMN_G_R_V_I_REFERENCEID_3);
			}
			else {
				bindReferenceId = true;

				query.append(_FINDER_COLUMN_G_R_V_I_REFERENCEID_2);
			}

			query.append(_FINDER_COLUMN_G_R_V_I_VERSION_2);

			query.append(_FINDER_COLUMN_G_R_V_I_IMAGETYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindReferenceId) {
					qPos.add(referenceId);
				}

				qPos.add(version);

				qPos.add(imageType);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_R_V_I_GROUPID_2 = "referenceImage.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_R_V_I_REFERENCEID_1 = "referenceImage.referenceId IS NULL AND ";
	private static final String _FINDER_COLUMN_G_R_V_I_REFERENCEID_2 = "referenceImage.referenceId = ? AND ";
	private static final String _FINDER_COLUMN_G_R_V_I_REFERENCEID_3 = "(referenceImage.referenceId IS NULL OR referenceImage.referenceId = '') AND ";
	private static final String _FINDER_COLUMN_G_R_V_I_VERSION_2 = "referenceImage.version = ? AND ";
	private static final String _FINDER_COLUMN_G_R_V_I_IMAGETYPE_2 = "referenceImage.imageType = ?";

	public ReferenceImagePersistenceImpl() {
		setModelClass(ReferenceImage.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the reference image in the entity cache if it is enabled.
	 *
	 * @param referenceImage the reference image
	 */
	@Override
	public void cacheResult(ReferenceImage referenceImage) {
		entityCache.putResult(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceImageImpl.class, referenceImage.getPrimaryKey(),
			referenceImage);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { referenceImage.getUuid(), referenceImage.getGroupId() },
			referenceImage);

		referenceImage.resetOriginalValues();
	}

	/**
	 * Caches the reference images in the entity cache if it is enabled.
	 *
	 * @param referenceImages the reference images
	 */
	@Override
	public void cacheResult(List<ReferenceImage> referenceImages) {
		for (ReferenceImage referenceImage : referenceImages) {
			if (entityCache.getResult(
						ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
						ReferenceImageImpl.class, referenceImage.getPrimaryKey()) == null) {
				cacheResult(referenceImage);
			}
			else {
				referenceImage.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all reference images.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ReferenceImageImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the reference image.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ReferenceImage referenceImage) {
		entityCache.removeResult(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceImageImpl.class, referenceImage.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ReferenceImageModelImpl)referenceImage, true);
	}

	@Override
	public void clearCache(List<ReferenceImage> referenceImages) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ReferenceImage referenceImage : referenceImages) {
			entityCache.removeResult(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
				ReferenceImageImpl.class, referenceImage.getPrimaryKey());

			clearUniqueFindersCache((ReferenceImageModelImpl)referenceImage,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		ReferenceImageModelImpl referenceImageModelImpl) {
		Object[] args = new Object[] {
				referenceImageModelImpl.getUuid(),
				referenceImageModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			referenceImageModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ReferenceImageModelImpl referenceImageModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					referenceImageModelImpl.getUuid(),
					referenceImageModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((referenceImageModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					referenceImageModelImpl.getOriginalUuid(),
					referenceImageModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new reference image with the primary key. Does not add the reference image to the database.
	 *
	 * @param referenceImageId the primary key for the new reference image
	 * @return the new reference image
	 */
	@Override
	public ReferenceImage create(long referenceImageId) {
		ReferenceImage referenceImage = new ReferenceImageImpl();

		referenceImage.setNew(true);
		referenceImage.setPrimaryKey(referenceImageId);

		String uuid = PortalUUIDUtil.generate();

		referenceImage.setUuid(uuid);

		referenceImage.setCompanyId(companyProvider.getCompanyId());

		return referenceImage;
	}

	/**
	 * Removes the reference image with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param referenceImageId the primary key of the reference image
	 * @return the reference image that was removed
	 * @throws NoSuchImageException if a reference image with the primary key could not be found
	 */
	@Override
	public ReferenceImage remove(long referenceImageId)
		throws NoSuchImageException {
		return remove((Serializable)referenceImageId);
	}

	/**
	 * Removes the reference image with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the reference image
	 * @return the reference image that was removed
	 * @throws NoSuchImageException if a reference image with the primary key could not be found
	 */
	@Override
	public ReferenceImage remove(Serializable primaryKey)
		throws NoSuchImageException {
		Session session = null;

		try {
			session = openSession();

			ReferenceImage referenceImage = (ReferenceImage)session.get(ReferenceImageImpl.class,
					primaryKey);

			if (referenceImage == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchImageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(referenceImage);
		}
		catch (NoSuchImageException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected ReferenceImage removeImpl(ReferenceImage referenceImage) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(referenceImage)) {
				referenceImage = (ReferenceImage)session.get(ReferenceImageImpl.class,
						referenceImage.getPrimaryKeyObj());
			}

			if (referenceImage != null) {
				session.delete(referenceImage);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (referenceImage != null) {
			clearCache(referenceImage);
		}

		return referenceImage;
	}

	@Override
	public ReferenceImage updateImpl(ReferenceImage referenceImage) {
		boolean isNew = referenceImage.isNew();

		if (!(referenceImage instanceof ReferenceImageModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(referenceImage.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(referenceImage);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in referenceImage proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ReferenceImage implementation " +
				referenceImage.getClass());
		}

		ReferenceImageModelImpl referenceImageModelImpl = (ReferenceImageModelImpl)referenceImage;

		if (Validator.isNull(referenceImage.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			referenceImage.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (referenceImage.isNew()) {
				session.save(referenceImage);

				referenceImage.setNew(false);
			}
			else {
				referenceImage = (ReferenceImage)session.merge(referenceImage);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ReferenceImageModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { referenceImageModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					referenceImageModelImpl.getUuid(),
					referenceImageModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { referenceImageModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			args = new Object[] {
					referenceImageModelImpl.getGroupId(),
					referenceImageModelImpl.getReferenceId(),
					referenceImageModelImpl.getVersion()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_R_V, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R_V,
				args);

			args = new Object[] {
					referenceImageModelImpl.getGroupId(),
					referenceImageModelImpl.getReferenceId(),
					referenceImageModelImpl.getVersion(),
					referenceImageModelImpl.getImageType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_R_V_I, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R_V_I,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((referenceImageModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						referenceImageModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { referenceImageModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((referenceImageModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						referenceImageModelImpl.getOriginalUuid(),
						referenceImageModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						referenceImageModelImpl.getUuid(),
						referenceImageModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((referenceImageModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						referenceImageModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { referenceImageModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((referenceImageModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R_V.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						referenceImageModelImpl.getOriginalGroupId(),
						referenceImageModelImpl.getOriginalReferenceId(),
						referenceImageModelImpl.getOriginalVersion()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_R_V, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R_V,
					args);

				args = new Object[] {
						referenceImageModelImpl.getGroupId(),
						referenceImageModelImpl.getReferenceId(),
						referenceImageModelImpl.getVersion()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_R_V, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R_V,
					args);
			}

			if ((referenceImageModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R_V_I.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						referenceImageModelImpl.getOriginalGroupId(),
						referenceImageModelImpl.getOriginalReferenceId(),
						referenceImageModelImpl.getOriginalVersion(),
						referenceImageModelImpl.getOriginalImageType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_R_V_I, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R_V_I,
					args);

				args = new Object[] {
						referenceImageModelImpl.getGroupId(),
						referenceImageModelImpl.getReferenceId(),
						referenceImageModelImpl.getVersion(),
						referenceImageModelImpl.getImageType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_R_V_I, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R_V_I,
					args);
			}
		}

		entityCache.putResult(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceImageImpl.class, referenceImage.getPrimaryKey(),
			referenceImage, false);

		clearUniqueFindersCache(referenceImageModelImpl, false);
		cacheUniqueFindersCache(referenceImageModelImpl);

		referenceImage.resetOriginalValues();

		return referenceImage;
	}

	/**
	 * Returns the reference image with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the reference image
	 * @return the reference image
	 * @throws NoSuchImageException if a reference image with the primary key could not be found
	 */
	@Override
	public ReferenceImage findByPrimaryKey(Serializable primaryKey)
		throws NoSuchImageException {
		ReferenceImage referenceImage = fetchByPrimaryKey(primaryKey);

		if (referenceImage == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchImageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return referenceImage;
	}

	/**
	 * Returns the reference image with the primary key or throws a {@link NoSuchImageException} if it could not be found.
	 *
	 * @param referenceImageId the primary key of the reference image
	 * @return the reference image
	 * @throws NoSuchImageException if a reference image with the primary key could not be found
	 */
	@Override
	public ReferenceImage findByPrimaryKey(long referenceImageId)
		throws NoSuchImageException {
		return findByPrimaryKey((Serializable)referenceImageId);
	}

	/**
	 * Returns the reference image with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the reference image
	 * @return the reference image, or <code>null</code> if a reference image with the primary key could not be found
	 */
	@Override
	public ReferenceImage fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
				ReferenceImageImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ReferenceImage referenceImage = (ReferenceImage)serializable;

		if (referenceImage == null) {
			Session session = null;

			try {
				session = openSession();

				referenceImage = (ReferenceImage)session.get(ReferenceImageImpl.class,
						primaryKey);

				if (referenceImage != null) {
					cacheResult(referenceImage);
				}
				else {
					entityCache.putResult(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
						ReferenceImageImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
					ReferenceImageImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return referenceImage;
	}

	/**
	 * Returns the reference image with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param referenceImageId the primary key of the reference image
	 * @return the reference image, or <code>null</code> if a reference image with the primary key could not be found
	 */
	@Override
	public ReferenceImage fetchByPrimaryKey(long referenceImageId) {
		return fetchByPrimaryKey((Serializable)referenceImageId);
	}

	@Override
	public Map<Serializable, ReferenceImage> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ReferenceImage> map = new HashMap<Serializable, ReferenceImage>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ReferenceImage referenceImage = fetchByPrimaryKey(primaryKey);

			if (referenceImage != null) {
				map.put(primaryKey, referenceImage);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
					ReferenceImageImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ReferenceImage)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_REFERENCEIMAGE_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (ReferenceImage referenceImage : (List<ReferenceImage>)q.list()) {
				map.put(referenceImage.getPrimaryKeyObj(), referenceImage);

				cacheResult(referenceImage);

				uncachedPrimaryKeys.remove(referenceImage.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ReferenceImageModelImpl.ENTITY_CACHE_ENABLED,
					ReferenceImageImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the reference images.
	 *
	 * @return the reference images
	 */
	@Override
	public List<ReferenceImage> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<ReferenceImage> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<ReferenceImage> findAll(int start, int end,
		OrderByComparator<ReferenceImage> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<ReferenceImage> findAll(int start, int end,
		OrderByComparator<ReferenceImage> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<ReferenceImage> list = null;

		if (retrieveFromCache) {
			list = (List<ReferenceImage>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_REFERENCEIMAGE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_REFERENCEIMAGE;

				if (pagination) {
					sql = sql.concat(ReferenceImageModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ReferenceImage>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ReferenceImage>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the reference images from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ReferenceImage referenceImage : findAll()) {
			remove(referenceImage);
		}
	}

	/**
	 * Returns the number of reference images.
	 *
	 * @return the number of reference images
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_REFERENCEIMAGE);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ReferenceImageModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the reference image persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ReferenceImageImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_REFERENCEIMAGE = "SELECT referenceImage FROM ReferenceImage referenceImage";
	private static final String _SQL_SELECT_REFERENCEIMAGE_WHERE_PKS_IN = "SELECT referenceImage FROM ReferenceImage referenceImage WHERE referenceImageId IN (";
	private static final String _SQL_SELECT_REFERENCEIMAGE_WHERE = "SELECT referenceImage FROM ReferenceImage referenceImage WHERE ";
	private static final String _SQL_COUNT_REFERENCEIMAGE = "SELECT COUNT(referenceImage) FROM ReferenceImage referenceImage";
	private static final String _SQL_COUNT_REFERENCEIMAGE_WHERE = "SELECT COUNT(referenceImage) FROM ReferenceImage referenceImage WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "referenceImage.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ReferenceImage exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ReferenceImage exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ReferenceImagePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}