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

import hu.webtown.liferay.portlet.reference.exception.NoSuchResourceException;
import hu.webtown.liferay.portlet.reference.model.ReferenceResource;
import hu.webtown.liferay.portlet.reference.model.impl.ReferenceResourceImpl;
import hu.webtown.liferay.portlet.reference.model.impl.ReferenceResourceModelImpl;
import hu.webtown.liferay.portlet.reference.service.persistence.ReferenceResourcePersistence;

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
 * The persistence implementation for the reference resource service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReferenceResourcePersistence
 * @see hu.webtown.liferay.portlet.reference.service.persistence.ReferenceResourceUtil
 * @generated
 */
@ProviderType
public class ReferenceResourcePersistenceImpl extends BasePersistenceImpl<ReferenceResource>
	implements ReferenceResourcePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ReferenceResourceUtil} to access the reference resource persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ReferenceResourceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ReferenceResourceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceResourceModelImpl.FINDER_CACHE_ENABLED,
			ReferenceResourceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ReferenceResourceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceResourceModelImpl.FINDER_CACHE_ENABLED,
			ReferenceResourceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ReferenceResourceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceResourceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ReferenceResourceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceResourceModelImpl.FINDER_CACHE_ENABLED,
			ReferenceResourceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ReferenceResourceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceResourceModelImpl.FINDER_CACHE_ENABLED,
			ReferenceResourceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ReferenceResourceModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ReferenceResourceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceResourceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the reference resources where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching reference resources
	 */
	@Override
	public List<ReferenceResource> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<ReferenceResource> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
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
	@Override
	public List<ReferenceResource> findByUuid(String uuid, int start, int end,
		OrderByComparator<ReferenceResource> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
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
	@Override
	public List<ReferenceResource> findByUuid(String uuid, int start, int end,
		OrderByComparator<ReferenceResource> orderByComparator,
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

		List<ReferenceResource> list = null;

		if (retrieveFromCache) {
			list = (List<ReferenceResource>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ReferenceResource referenceResource : list) {
					if (!Objects.equals(uuid, referenceResource.getUuid())) {
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

			query.append(_SQL_SELECT_REFERENCERESOURCE_WHERE);

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
				query.append(ReferenceResourceModelImpl.ORDER_BY_JPQL);
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
					list = (List<ReferenceResource>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ReferenceResource>)QueryUtil.list(q,
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
	 * Returns the first reference resource in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference resource
	 * @throws NoSuchResourceException if a matching reference resource could not be found
	 */
	@Override
	public ReferenceResource findByUuid_First(String uuid,
		OrderByComparator<ReferenceResource> orderByComparator)
		throws NoSuchResourceException {
		ReferenceResource referenceResource = fetchByUuid_First(uuid,
				orderByComparator);

		if (referenceResource != null) {
			return referenceResource;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchResourceException(msg.toString());
	}

	/**
	 * Returns the first reference resource in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference resource, or <code>null</code> if a matching reference resource could not be found
	 */
	@Override
	public ReferenceResource fetchByUuid_First(String uuid,
		OrderByComparator<ReferenceResource> orderByComparator) {
		List<ReferenceResource> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last reference resource in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference resource
	 * @throws NoSuchResourceException if a matching reference resource could not be found
	 */
	@Override
	public ReferenceResource findByUuid_Last(String uuid,
		OrderByComparator<ReferenceResource> orderByComparator)
		throws NoSuchResourceException {
		ReferenceResource referenceResource = fetchByUuid_Last(uuid,
				orderByComparator);

		if (referenceResource != null) {
			return referenceResource;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchResourceException(msg.toString());
	}

	/**
	 * Returns the last reference resource in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference resource, or <code>null</code> if a matching reference resource could not be found
	 */
	@Override
	public ReferenceResource fetchByUuid_Last(String uuid,
		OrderByComparator<ReferenceResource> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ReferenceResource> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public ReferenceResource[] findByUuid_PrevAndNext(long resourcePrimKey,
		String uuid, OrderByComparator<ReferenceResource> orderByComparator)
		throws NoSuchResourceException {
		ReferenceResource referenceResource = findByPrimaryKey(resourcePrimKey);

		Session session = null;

		try {
			session = openSession();

			ReferenceResource[] array = new ReferenceResourceImpl[3];

			array[0] = getByUuid_PrevAndNext(session, referenceResource, uuid,
					orderByComparator, true);

			array[1] = referenceResource;

			array[2] = getByUuid_PrevAndNext(session, referenceResource, uuid,
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

	protected ReferenceResource getByUuid_PrevAndNext(Session session,
		ReferenceResource referenceResource, String uuid,
		OrderByComparator<ReferenceResource> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REFERENCERESOURCE_WHERE);

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
			query.append(ReferenceResourceModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(referenceResource);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ReferenceResource> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the reference resources where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ReferenceResource referenceResource : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(referenceResource);
		}
	}

	/**
	 * Returns the number of reference resources where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching reference resources
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_REFERENCERESOURCE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "referenceResource.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "referenceResource.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(referenceResource.uuid IS NULL OR referenceResource.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ReferenceResourceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceResourceModelImpl.FINDER_CACHE_ENABLED,
			ReferenceResourceImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ReferenceResourceModelImpl.UUID_COLUMN_BITMASK |
			ReferenceResourceModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ReferenceResourceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceResourceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the reference resource where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchResourceException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching reference resource
	 * @throws NoSuchResourceException if a matching reference resource could not be found
	 */
	@Override
	public ReferenceResource findByUUID_G(String uuid, long groupId)
		throws NoSuchResourceException {
		ReferenceResource referenceResource = fetchByUUID_G(uuid, groupId);

		if (referenceResource == null) {
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

			throw new NoSuchResourceException(msg.toString());
		}

		return referenceResource;
	}

	/**
	 * Returns the reference resource where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching reference resource, or <code>null</code> if a matching reference resource could not be found
	 */
	@Override
	public ReferenceResource fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the reference resource where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching reference resource, or <code>null</code> if a matching reference resource could not be found
	 */
	@Override
	public ReferenceResource fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ReferenceResource) {
			ReferenceResource referenceResource = (ReferenceResource)result;

			if (!Objects.equals(uuid, referenceResource.getUuid()) ||
					(groupId != referenceResource.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_REFERENCERESOURCE_WHERE);

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

				List<ReferenceResource> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ReferenceResource referenceResource = list.get(0);

					result = referenceResource;

					cacheResult(referenceResource);
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
			return (ReferenceResource)result;
		}
	}

	/**
	 * Removes the reference resource where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the reference resource that was removed
	 */
	@Override
	public ReferenceResource removeByUUID_G(String uuid, long groupId)
		throws NoSuchResourceException {
		ReferenceResource referenceResource = findByUUID_G(uuid, groupId);

		return remove(referenceResource);
	}

	/**
	 * Returns the number of reference resources where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching reference resources
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REFERENCERESOURCE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "referenceResource.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "referenceResource.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(referenceResource.uuid IS NULL OR referenceResource.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "referenceResource.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ReferenceResourceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceResourceModelImpl.FINDER_CACHE_ENABLED,
			ReferenceResourceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ReferenceResourceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceResourceModelImpl.FINDER_CACHE_ENABLED,
			ReferenceResourceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ReferenceResourceModelImpl.UUID_COLUMN_BITMASK |
			ReferenceResourceModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ReferenceResourceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceResourceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the reference resources where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching reference resources
	 */
	@Override
	public List<ReferenceResource> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<ReferenceResource> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
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
	@Override
	public List<ReferenceResource> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<ReferenceResource> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
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
	@Override
	public List<ReferenceResource> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<ReferenceResource> orderByComparator,
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

		List<ReferenceResource> list = null;

		if (retrieveFromCache) {
			list = (List<ReferenceResource>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ReferenceResource referenceResource : list) {
					if (!Objects.equals(uuid, referenceResource.getUuid()) ||
							(companyId != referenceResource.getCompanyId())) {
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

			query.append(_SQL_SELECT_REFERENCERESOURCE_WHERE);

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
				query.append(ReferenceResourceModelImpl.ORDER_BY_JPQL);
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
					list = (List<ReferenceResource>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ReferenceResource>)QueryUtil.list(q,
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
	 * Returns the first reference resource in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference resource
	 * @throws NoSuchResourceException if a matching reference resource could not be found
	 */
	@Override
	public ReferenceResource findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ReferenceResource> orderByComparator)
		throws NoSuchResourceException {
		ReferenceResource referenceResource = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (referenceResource != null) {
			return referenceResource;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchResourceException(msg.toString());
	}

	/**
	 * Returns the first reference resource in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference resource, or <code>null</code> if a matching reference resource could not be found
	 */
	@Override
	public ReferenceResource fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ReferenceResource> orderByComparator) {
		List<ReferenceResource> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public ReferenceResource findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ReferenceResource> orderByComparator)
		throws NoSuchResourceException {
		ReferenceResource referenceResource = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (referenceResource != null) {
			return referenceResource;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchResourceException(msg.toString());
	}

	/**
	 * Returns the last reference resource in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference resource, or <code>null</code> if a matching reference resource could not be found
	 */
	@Override
	public ReferenceResource fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ReferenceResource> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ReferenceResource> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public ReferenceResource[] findByUuid_C_PrevAndNext(long resourcePrimKey,
		String uuid, long companyId,
		OrderByComparator<ReferenceResource> orderByComparator)
		throws NoSuchResourceException {
		ReferenceResource referenceResource = findByPrimaryKey(resourcePrimKey);

		Session session = null;

		try {
			session = openSession();

			ReferenceResource[] array = new ReferenceResourceImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, referenceResource,
					uuid, companyId, orderByComparator, true);

			array[1] = referenceResource;

			array[2] = getByUuid_C_PrevAndNext(session, referenceResource,
					uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ReferenceResource getByUuid_C_PrevAndNext(Session session,
		ReferenceResource referenceResource, String uuid, long companyId,
		OrderByComparator<ReferenceResource> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_REFERENCERESOURCE_WHERE);

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
			query.append(ReferenceResourceModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(referenceResource);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ReferenceResource> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the reference resources where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ReferenceResource referenceResource : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(referenceResource);
		}
	}

	/**
	 * Returns the number of reference resources where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching reference resources
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REFERENCERESOURCE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "referenceResource.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "referenceResource.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(referenceResource.uuid IS NULL OR referenceResource.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "referenceResource.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(ReferenceResourceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceResourceModelImpl.FINDER_CACHE_ENABLED,
			ReferenceResourceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(ReferenceResourceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceResourceModelImpl.FINDER_CACHE_ENABLED,
			ReferenceResourceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			ReferenceResourceModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(ReferenceResourceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceResourceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the reference resources where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching reference resources
	 */
	@Override
	public List<ReferenceResource> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<ReferenceResource> findByGroupId(long groupId, int start,
		int end) {
		return findByGroupId(groupId, start, end, null);
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
	@Override
	public List<ReferenceResource> findByGroupId(long groupId, int start,
		int end, OrderByComparator<ReferenceResource> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
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
	@Override
	public List<ReferenceResource> findByGroupId(long groupId, int start,
		int end, OrderByComparator<ReferenceResource> orderByComparator,
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

		List<ReferenceResource> list = null;

		if (retrieveFromCache) {
			list = (List<ReferenceResource>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ReferenceResource referenceResource : list) {
					if ((groupId != referenceResource.getGroupId())) {
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

			query.append(_SQL_SELECT_REFERENCERESOURCE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReferenceResourceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ReferenceResource>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ReferenceResource>)QueryUtil.list(q,
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
	 * Returns the first reference resource in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference resource
	 * @throws NoSuchResourceException if a matching reference resource could not be found
	 */
	@Override
	public ReferenceResource findByGroupId_First(long groupId,
		OrderByComparator<ReferenceResource> orderByComparator)
		throws NoSuchResourceException {
		ReferenceResource referenceResource = fetchByGroupId_First(groupId,
				orderByComparator);

		if (referenceResource != null) {
			return referenceResource;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchResourceException(msg.toString());
	}

	/**
	 * Returns the first reference resource in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference resource, or <code>null</code> if a matching reference resource could not be found
	 */
	@Override
	public ReferenceResource fetchByGroupId_First(long groupId,
		OrderByComparator<ReferenceResource> orderByComparator) {
		List<ReferenceResource> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last reference resource in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference resource
	 * @throws NoSuchResourceException if a matching reference resource could not be found
	 */
	@Override
	public ReferenceResource findByGroupId_Last(long groupId,
		OrderByComparator<ReferenceResource> orderByComparator)
		throws NoSuchResourceException {
		ReferenceResource referenceResource = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (referenceResource != null) {
			return referenceResource;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchResourceException(msg.toString());
	}

	/**
	 * Returns the last reference resource in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference resource, or <code>null</code> if a matching reference resource could not be found
	 */
	@Override
	public ReferenceResource fetchByGroupId_Last(long groupId,
		OrderByComparator<ReferenceResource> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<ReferenceResource> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public ReferenceResource[] findByGroupId_PrevAndNext(long resourcePrimKey,
		long groupId, OrderByComparator<ReferenceResource> orderByComparator)
		throws NoSuchResourceException {
		ReferenceResource referenceResource = findByPrimaryKey(resourcePrimKey);

		Session session = null;

		try {
			session = openSession();

			ReferenceResource[] array = new ReferenceResourceImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, referenceResource,
					groupId, orderByComparator, true);

			array[1] = referenceResource;

			array[2] = getByGroupId_PrevAndNext(session, referenceResource,
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

	protected ReferenceResource getByGroupId_PrevAndNext(Session session,
		ReferenceResource referenceResource, long groupId,
		OrderByComparator<ReferenceResource> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REFERENCERESOURCE_WHERE);

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
			query.append(ReferenceResourceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(referenceResource);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ReferenceResource> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the reference resources where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (ReferenceResource referenceResource : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(referenceResource);
		}
	}

	/**
	 * Returns the number of reference resources where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching reference resources
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_REFERENCERESOURCE_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "referenceResource.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_R = new FinderPath(ReferenceResourceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceResourceModelImpl.FINDER_CACHE_ENABLED,
			ReferenceResourceImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByG_R",
			new String[] { Long.class.getName(), String.class.getName() },
			ReferenceResourceModelImpl.GROUPID_COLUMN_BITMASK |
			ReferenceResourceModelImpl.REFERENCEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_R = new FinderPath(ReferenceResourceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceResourceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_R",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the reference resource where groupId = &#63; and referenceId = &#63; or throws a {@link NoSuchResourceException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @return the matching reference resource
	 * @throws NoSuchResourceException if a matching reference resource could not be found
	 */
	@Override
	public ReferenceResource findByG_R(long groupId, String referenceId)
		throws NoSuchResourceException {
		ReferenceResource referenceResource = fetchByG_R(groupId, referenceId);

		if (referenceResource == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", referenceId=");
			msg.append(referenceId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchResourceException(msg.toString());
		}

		return referenceResource;
	}

	/**
	 * Returns the reference resource where groupId = &#63; and referenceId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @return the matching reference resource, or <code>null</code> if a matching reference resource could not be found
	 */
	@Override
	public ReferenceResource fetchByG_R(long groupId, String referenceId) {
		return fetchByG_R(groupId, referenceId, true);
	}

	/**
	 * Returns the reference resource where groupId = &#63; and referenceId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching reference resource, or <code>null</code> if a matching reference resource could not be found
	 */
	@Override
	public ReferenceResource fetchByG_R(long groupId, String referenceId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, referenceId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_R,
					finderArgs, this);
		}

		if (result instanceof ReferenceResource) {
			ReferenceResource referenceResource = (ReferenceResource)result;

			if ((groupId != referenceResource.getGroupId()) ||
					!Objects.equals(referenceId,
						referenceResource.getReferenceId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_REFERENCERESOURCE_WHERE);

			query.append(_FINDER_COLUMN_G_R_GROUPID_2);

			boolean bindReferenceId = false;

			if (referenceId == null) {
				query.append(_FINDER_COLUMN_G_R_REFERENCEID_1);
			}
			else if (referenceId.equals("")) {
				query.append(_FINDER_COLUMN_G_R_REFERENCEID_3);
			}
			else {
				bindReferenceId = true;

				query.append(_FINDER_COLUMN_G_R_REFERENCEID_2);
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

				List<ReferenceResource> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_R, finderArgs,
						list);
				}
				else {
					ReferenceResource referenceResource = list.get(0);

					result = referenceResource;

					cacheResult(referenceResource);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_R, finderArgs);

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
			return (ReferenceResource)result;
		}
	}

	/**
	 * Removes the reference resource where groupId = &#63; and referenceId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @return the reference resource that was removed
	 */
	@Override
	public ReferenceResource removeByG_R(long groupId, String referenceId)
		throws NoSuchResourceException {
		ReferenceResource referenceResource = findByG_R(groupId, referenceId);

		return remove(referenceResource);
	}

	/**
	 * Returns the number of reference resources where groupId = &#63; and referenceId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @return the number of matching reference resources
	 */
	@Override
	public int countByG_R(long groupId, String referenceId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_R;

		Object[] finderArgs = new Object[] { groupId, referenceId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REFERENCERESOURCE_WHERE);

			query.append(_FINDER_COLUMN_G_R_GROUPID_2);

			boolean bindReferenceId = false;

			if (referenceId == null) {
				query.append(_FINDER_COLUMN_G_R_REFERENCEID_1);
			}
			else if (referenceId.equals("")) {
				query.append(_FINDER_COLUMN_G_R_REFERENCEID_3);
			}
			else {
				bindReferenceId = true;

				query.append(_FINDER_COLUMN_G_R_REFERENCEID_2);
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

	private static final String _FINDER_COLUMN_G_R_GROUPID_2 = "referenceResource.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_R_REFERENCEID_1 = "referenceResource.referenceId IS NULL";
	private static final String _FINDER_COLUMN_G_R_REFERENCEID_2 = "referenceResource.referenceId = ?";
	private static final String _FINDER_COLUMN_G_R_REFERENCEID_3 = "(referenceResource.referenceId IS NULL OR referenceResource.referenceId = '')";

	public ReferenceResourcePersistenceImpl() {
		setModelClass(ReferenceResource.class);

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
	 * Caches the reference resource in the entity cache if it is enabled.
	 *
	 * @param referenceResource the reference resource
	 */
	@Override
	public void cacheResult(ReferenceResource referenceResource) {
		entityCache.putResult(ReferenceResourceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceResourceImpl.class, referenceResource.getPrimaryKey(),
			referenceResource);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				referenceResource.getUuid(), referenceResource.getGroupId()
			}, referenceResource);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_R,
			new Object[] {
				referenceResource.getGroupId(),
				referenceResource.getReferenceId()
			}, referenceResource);

		referenceResource.resetOriginalValues();
	}

	/**
	 * Caches the reference resources in the entity cache if it is enabled.
	 *
	 * @param referenceResources the reference resources
	 */
	@Override
	public void cacheResult(List<ReferenceResource> referenceResources) {
		for (ReferenceResource referenceResource : referenceResources) {
			if (entityCache.getResult(
						ReferenceResourceModelImpl.ENTITY_CACHE_ENABLED,
						ReferenceResourceImpl.class,
						referenceResource.getPrimaryKey()) == null) {
				cacheResult(referenceResource);
			}
			else {
				referenceResource.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all reference resources.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ReferenceResourceImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the reference resource.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ReferenceResource referenceResource) {
		entityCache.removeResult(ReferenceResourceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceResourceImpl.class, referenceResource.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ReferenceResourceModelImpl)referenceResource,
			true);
	}

	@Override
	public void clearCache(List<ReferenceResource> referenceResources) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ReferenceResource referenceResource : referenceResources) {
			entityCache.removeResult(ReferenceResourceModelImpl.ENTITY_CACHE_ENABLED,
				ReferenceResourceImpl.class, referenceResource.getPrimaryKey());

			clearUniqueFindersCache((ReferenceResourceModelImpl)referenceResource,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		ReferenceResourceModelImpl referenceResourceModelImpl) {
		Object[] args = new Object[] {
				referenceResourceModelImpl.getUuid(),
				referenceResourceModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			referenceResourceModelImpl, false);

		args = new Object[] {
				referenceResourceModelImpl.getGroupId(),
				referenceResourceModelImpl.getReferenceId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_R, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_R, args,
			referenceResourceModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ReferenceResourceModelImpl referenceResourceModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					referenceResourceModelImpl.getUuid(),
					referenceResourceModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((referenceResourceModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					referenceResourceModelImpl.getOriginalUuid(),
					referenceResourceModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					referenceResourceModelImpl.getGroupId(),
					referenceResourceModelImpl.getReferenceId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_R, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_R, args);
		}

		if ((referenceResourceModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_R.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					referenceResourceModelImpl.getOriginalGroupId(),
					referenceResourceModelImpl.getOriginalReferenceId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_R, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_R, args);
		}
	}

	/**
	 * Creates a new reference resource with the primary key. Does not add the reference resource to the database.
	 *
	 * @param resourcePrimKey the primary key for the new reference resource
	 * @return the new reference resource
	 */
	@Override
	public ReferenceResource create(long resourcePrimKey) {
		ReferenceResource referenceResource = new ReferenceResourceImpl();

		referenceResource.setNew(true);
		referenceResource.setPrimaryKey(resourcePrimKey);

		String uuid = PortalUUIDUtil.generate();

		referenceResource.setUuid(uuid);

		referenceResource.setCompanyId(companyProvider.getCompanyId());

		return referenceResource;
	}

	/**
	 * Removes the reference resource with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param resourcePrimKey the primary key of the reference resource
	 * @return the reference resource that was removed
	 * @throws NoSuchResourceException if a reference resource with the primary key could not be found
	 */
	@Override
	public ReferenceResource remove(long resourcePrimKey)
		throws NoSuchResourceException {
		return remove((Serializable)resourcePrimKey);
	}

	/**
	 * Removes the reference resource with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the reference resource
	 * @return the reference resource that was removed
	 * @throws NoSuchResourceException if a reference resource with the primary key could not be found
	 */
	@Override
	public ReferenceResource remove(Serializable primaryKey)
		throws NoSuchResourceException {
		Session session = null;

		try {
			session = openSession();

			ReferenceResource referenceResource = (ReferenceResource)session.get(ReferenceResourceImpl.class,
					primaryKey);

			if (referenceResource == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchResourceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(referenceResource);
		}
		catch (NoSuchResourceException nsee) {
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
	protected ReferenceResource removeImpl(ReferenceResource referenceResource) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(referenceResource)) {
				referenceResource = (ReferenceResource)session.get(ReferenceResourceImpl.class,
						referenceResource.getPrimaryKeyObj());
			}

			if (referenceResource != null) {
				session.delete(referenceResource);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (referenceResource != null) {
			clearCache(referenceResource);
		}

		return referenceResource;
	}

	@Override
	public ReferenceResource updateImpl(ReferenceResource referenceResource) {
		boolean isNew = referenceResource.isNew();

		if (!(referenceResource instanceof ReferenceResourceModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(referenceResource.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(referenceResource);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in referenceResource proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ReferenceResource implementation " +
				referenceResource.getClass());
		}

		ReferenceResourceModelImpl referenceResourceModelImpl = (ReferenceResourceModelImpl)referenceResource;

		if (Validator.isNull(referenceResource.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			referenceResource.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (referenceResource.isNew()) {
				session.save(referenceResource);

				referenceResource.setNew(false);
			}
			else {
				referenceResource = (ReferenceResource)session.merge(referenceResource);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ReferenceResourceModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { referenceResourceModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					referenceResourceModelImpl.getUuid(),
					referenceResourceModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { referenceResourceModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((referenceResourceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						referenceResourceModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { referenceResourceModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((referenceResourceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						referenceResourceModelImpl.getOriginalUuid(),
						referenceResourceModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						referenceResourceModelImpl.getUuid(),
						referenceResourceModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((referenceResourceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						referenceResourceModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { referenceResourceModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}
		}

		entityCache.putResult(ReferenceResourceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceResourceImpl.class, referenceResource.getPrimaryKey(),
			referenceResource, false);

		clearUniqueFindersCache(referenceResourceModelImpl, false);
		cacheUniqueFindersCache(referenceResourceModelImpl);

		referenceResource.resetOriginalValues();

		return referenceResource;
	}

	/**
	 * Returns the reference resource with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the reference resource
	 * @return the reference resource
	 * @throws NoSuchResourceException if a reference resource with the primary key could not be found
	 */
	@Override
	public ReferenceResource findByPrimaryKey(Serializable primaryKey)
		throws NoSuchResourceException {
		ReferenceResource referenceResource = fetchByPrimaryKey(primaryKey);

		if (referenceResource == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchResourceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return referenceResource;
	}

	/**
	 * Returns the reference resource with the primary key or throws a {@link NoSuchResourceException} if it could not be found.
	 *
	 * @param resourcePrimKey the primary key of the reference resource
	 * @return the reference resource
	 * @throws NoSuchResourceException if a reference resource with the primary key could not be found
	 */
	@Override
	public ReferenceResource findByPrimaryKey(long resourcePrimKey)
		throws NoSuchResourceException {
		return findByPrimaryKey((Serializable)resourcePrimKey);
	}

	/**
	 * Returns the reference resource with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the reference resource
	 * @return the reference resource, or <code>null</code> if a reference resource with the primary key could not be found
	 */
	@Override
	public ReferenceResource fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ReferenceResourceModelImpl.ENTITY_CACHE_ENABLED,
				ReferenceResourceImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ReferenceResource referenceResource = (ReferenceResource)serializable;

		if (referenceResource == null) {
			Session session = null;

			try {
				session = openSession();

				referenceResource = (ReferenceResource)session.get(ReferenceResourceImpl.class,
						primaryKey);

				if (referenceResource != null) {
					cacheResult(referenceResource);
				}
				else {
					entityCache.putResult(ReferenceResourceModelImpl.ENTITY_CACHE_ENABLED,
						ReferenceResourceImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ReferenceResourceModelImpl.ENTITY_CACHE_ENABLED,
					ReferenceResourceImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return referenceResource;
	}

	/**
	 * Returns the reference resource with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param resourcePrimKey the primary key of the reference resource
	 * @return the reference resource, or <code>null</code> if a reference resource with the primary key could not be found
	 */
	@Override
	public ReferenceResource fetchByPrimaryKey(long resourcePrimKey) {
		return fetchByPrimaryKey((Serializable)resourcePrimKey);
	}

	@Override
	public Map<Serializable, ReferenceResource> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ReferenceResource> map = new HashMap<Serializable, ReferenceResource>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ReferenceResource referenceResource = fetchByPrimaryKey(primaryKey);

			if (referenceResource != null) {
				map.put(primaryKey, referenceResource);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ReferenceResourceModelImpl.ENTITY_CACHE_ENABLED,
					ReferenceResourceImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ReferenceResource)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_REFERENCERESOURCE_WHERE_PKS_IN);

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

			for (ReferenceResource referenceResource : (List<ReferenceResource>)q.list()) {
				map.put(referenceResource.getPrimaryKeyObj(), referenceResource);

				cacheResult(referenceResource);

				uncachedPrimaryKeys.remove(referenceResource.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ReferenceResourceModelImpl.ENTITY_CACHE_ENABLED,
					ReferenceResourceImpl.class, primaryKey, nullModel);
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
	 * Returns all the reference resources.
	 *
	 * @return the reference resources
	 */
	@Override
	public List<ReferenceResource> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<ReferenceResource> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<ReferenceResource> findAll(int start, int end,
		OrderByComparator<ReferenceResource> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<ReferenceResource> findAll(int start, int end,
		OrderByComparator<ReferenceResource> orderByComparator,
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

		List<ReferenceResource> list = null;

		if (retrieveFromCache) {
			list = (List<ReferenceResource>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_REFERENCERESOURCE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_REFERENCERESOURCE;

				if (pagination) {
					sql = sql.concat(ReferenceResourceModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ReferenceResource>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ReferenceResource>)QueryUtil.list(q,
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
	 * Removes all the reference resources from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ReferenceResource referenceResource : findAll()) {
			remove(referenceResource);
		}
	}

	/**
	 * Returns the number of reference resources.
	 *
	 * @return the number of reference resources
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_REFERENCERESOURCE);

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
		return ReferenceResourceModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the reference resource persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ReferenceResourceImpl.class.getName());
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
	private static final String _SQL_SELECT_REFERENCERESOURCE = "SELECT referenceResource FROM ReferenceResource referenceResource";
	private static final String _SQL_SELECT_REFERENCERESOURCE_WHERE_PKS_IN = "SELECT referenceResource FROM ReferenceResource referenceResource WHERE resourcePrimKey IN (";
	private static final String _SQL_SELECT_REFERENCERESOURCE_WHERE = "SELECT referenceResource FROM ReferenceResource referenceResource WHERE ";
	private static final String _SQL_COUNT_REFERENCERESOURCE = "SELECT COUNT(referenceResource) FROM ReferenceResource referenceResource";
	private static final String _SQL_COUNT_REFERENCERESOURCE_WHERE = "SELECT COUNT(referenceResource) FROM ReferenceResource referenceResource WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "referenceResource.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ReferenceResource exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ReferenceResource exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ReferenceResourcePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}