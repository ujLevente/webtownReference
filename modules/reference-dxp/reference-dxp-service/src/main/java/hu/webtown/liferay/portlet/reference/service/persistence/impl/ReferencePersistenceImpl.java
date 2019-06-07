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
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException;
import hu.webtown.liferay.portlet.reference.model.Reference;
import hu.webtown.liferay.portlet.reference.model.impl.ReferenceImpl;
import hu.webtown.liferay.portlet.reference.model.impl.ReferenceModelImpl;
import hu.webtown.liferay.portlet.reference.service.persistence.ReferencePersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the reference service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReferencePersistence
 * @see hu.webtown.liferay.portlet.reference.service.persistence.ReferenceUtil
 * @generated
 */
@ProviderType
public class ReferencePersistenceImpl extends BasePersistenceImpl<Reference>
	implements ReferencePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ReferenceUtil} to access the reference persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ReferenceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RESOURCEPRIMKEY =
		new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByResourcePrimKey",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEPRIMKEY =
		new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByResourcePrimKey",
			new String[] { Long.class.getName() },
			ReferenceModelImpl.RESOURCEPRIMKEY_COLUMN_BITMASK |
			ReferenceModelImpl.REFERENCEID_COLUMN_BITMASK |
			ReferenceModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RESOURCEPRIMKEY = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByResourcePrimKey", new String[] { Long.class.getName() });

	/**
	 * Returns all the references where resourcePrimKey = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @return the matching references
	 */
	@Override
	public List<Reference> findByResourcePrimKey(long resourcePrimKey) {
		return findByResourcePrimKey(resourcePrimKey, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> findByResourcePrimKey(long resourcePrimKey,
		int start, int end) {
		return findByResourcePrimKey(resourcePrimKey, start, end, null);
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
	@Override
	public List<Reference> findByResourcePrimKey(long resourcePrimKey,
		int start, int end, OrderByComparator<Reference> orderByComparator) {
		return findByResourcePrimKey(resourcePrimKey, start, end,
			orderByComparator, true);
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
	@Override
	public List<Reference> findByResourcePrimKey(long resourcePrimKey,
		int start, int end, OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEPRIMKEY;
			finderArgs = new Object[] { resourcePrimKey };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RESOURCEPRIMKEY;
			finderArgs = new Object[] {
					resourcePrimKey,
					
					start, end, orderByComparator
				};
		}

		List<Reference> list = null;

		if (retrieveFromCache) {
			list = (List<Reference>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Reference reference : list) {
					if ((resourcePrimKey != reference.getResourcePrimKey())) {
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

			query.append(_SQL_SELECT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_RESOURCEPRIMKEY_RESOURCEPRIMKEY_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				if (!pagination) {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first reference in the ordered set where resourcePrimKey = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference
	 * @throws NoSuchReferenceException if a matching reference could not be found
	 */
	@Override
	public Reference findByResourcePrimKey_First(long resourcePrimKey,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByResourcePrimKey_First(resourcePrimKey,
				orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
	}

	/**
	 * Returns the first reference in the ordered set where resourcePrimKey = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByResourcePrimKey_First(long resourcePrimKey,
		OrderByComparator<Reference> orderByComparator) {
		List<Reference> list = findByResourcePrimKey(resourcePrimKey, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last reference in the ordered set where resourcePrimKey = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference
	 * @throws NoSuchReferenceException if a matching reference could not be found
	 */
	@Override
	public Reference findByResourcePrimKey_Last(long resourcePrimKey,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByResourcePrimKey_Last(resourcePrimKey,
				orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
	}

	/**
	 * Returns the last reference in the ordered set where resourcePrimKey = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByResourcePrimKey_Last(long resourcePrimKey,
		OrderByComparator<Reference> orderByComparator) {
		int count = countByResourcePrimKey(resourcePrimKey);

		if (count == 0) {
			return null;
		}

		List<Reference> list = findByResourcePrimKey(resourcePrimKey,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference[] findByResourcePrimKey_PrevAndNext(long id,
		long resourcePrimKey, OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Reference[] array = new ReferenceImpl[3];

			array[0] = getByResourcePrimKey_PrevAndNext(session, reference,
					resourcePrimKey, orderByComparator, true);

			array[1] = reference;

			array[2] = getByResourcePrimKey_PrevAndNext(session, reference,
					resourcePrimKey, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Reference getByResourcePrimKey_PrevAndNext(Session session,
		Reference reference, long resourcePrimKey,
		OrderByComparator<Reference> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REFERENCE_WHERE);

		query.append(_FINDER_COLUMN_RESOURCEPRIMKEY_RESOURCEPRIMKEY_2);

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
			query.append(ReferenceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(reference);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Reference> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the references where resourcePrimKey = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key
	 */
	@Override
	public void removeByResourcePrimKey(long resourcePrimKey) {
		for (Reference reference : findByResourcePrimKey(resourcePrimKey,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(reference);
		}
	}

	/**
	 * Returns the number of references where resourcePrimKey = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @return the number of matching references
	 */
	@Override
	public int countByResourcePrimKey(long resourcePrimKey) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_RESOURCEPRIMKEY;

		Object[] finderArgs = new Object[] { resourcePrimKey };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_RESOURCEPRIMKEY_RESOURCEPRIMKEY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

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

	private static final String _FINDER_COLUMN_RESOURCEPRIMKEY_RESOURCEPRIMKEY_2 =
		"reference.resourcePrimKey = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ReferenceModelImpl.UUID_COLUMN_BITMASK |
			ReferenceModelImpl.REFERENCEID_COLUMN_BITMASK |
			ReferenceModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the references where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching references
	 */
	@Override
	public List<Reference> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
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
	@Override
	public List<Reference> findByUuid(String uuid, int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
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
	@Override
	public List<Reference> findByUuid(String uuid, int start, int end,
		OrderByComparator<Reference> orderByComparator,
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

		List<Reference> list = null;

		if (retrieveFromCache) {
			list = (List<Reference>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Reference reference : list) {
					if (!Objects.equals(uuid, reference.getUuid())) {
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

			query.append(_SQL_SELECT_REFERENCE_WHERE);

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
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
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
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first reference in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference
	 * @throws NoSuchReferenceException if a matching reference could not be found
	 */
	@Override
	public Reference findByUuid_First(String uuid,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByUuid_First(uuid, orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
	}

	/**
	 * Returns the first reference in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByUuid_First(String uuid,
		OrderByComparator<Reference> orderByComparator) {
		List<Reference> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last reference in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference
	 * @throws NoSuchReferenceException if a matching reference could not be found
	 */
	@Override
	public Reference findByUuid_Last(String uuid,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByUuid_Last(uuid, orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
	}

	/**
	 * Returns the last reference in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByUuid_Last(String uuid,
		OrderByComparator<Reference> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Reference> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference[] findByUuid_PrevAndNext(long id, String uuid,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Reference[] array = new ReferenceImpl[3];

			array[0] = getByUuid_PrevAndNext(session, reference, uuid,
					orderByComparator, true);

			array[1] = reference;

			array[2] = getByUuid_PrevAndNext(session, reference, uuid,
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

	protected Reference getByUuid_PrevAndNext(Session session,
		Reference reference, String uuid,
		OrderByComparator<Reference> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REFERENCE_WHERE);

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
			query.append(ReferenceModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(reference);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Reference> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the references where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Reference reference : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(reference);
		}
	}

	/**
	 * Returns the number of references where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching references
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_REFERENCE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "reference.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "reference.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(reference.uuid IS NULL OR reference.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ReferenceModelImpl.UUID_COLUMN_BITMASK |
			ReferenceModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the reference where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchReferenceException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching reference
	 * @throws NoSuchReferenceException if a matching reference could not be found
	 */
	@Override
	public Reference findByUUID_G(String uuid, long groupId)
		throws NoSuchReferenceException {
		Reference reference = fetchByUUID_G(uuid, groupId);

		if (reference == null) {
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

			throw new NoSuchReferenceException(msg.toString());
		}

		return reference;
	}

	/**
	 * Returns the reference where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the reference where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Reference) {
			Reference reference = (Reference)result;

			if (!Objects.equals(uuid, reference.getUuid()) ||
					(groupId != reference.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_REFERENCE_WHERE);

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

				List<Reference> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Reference reference = list.get(0);

					result = reference;

					cacheResult(reference);
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
			return (Reference)result;
		}
	}

	/**
	 * Removes the reference where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the reference that was removed
	 */
	@Override
	public Reference removeByUUID_G(String uuid, long groupId)
		throws NoSuchReferenceException {
		Reference reference = findByUUID_G(uuid, groupId);

		return remove(reference);
	}

	/**
	 * Returns the number of references where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching references
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REFERENCE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "reference.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "reference.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(reference.uuid IS NULL OR reference.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "reference.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ReferenceModelImpl.UUID_COLUMN_BITMASK |
			ReferenceModelImpl.COMPANYID_COLUMN_BITMASK |
			ReferenceModelImpl.REFERENCEID_COLUMN_BITMASK |
			ReferenceModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the references where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching references
	 */
	@Override
	public List<Reference> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
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
	@Override
	public List<Reference> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Reference> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
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
	@Override
	public List<Reference> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Reference> orderByComparator,
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

		List<Reference> list = null;

		if (retrieveFromCache) {
			list = (List<Reference>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Reference reference : list) {
					if (!Objects.equals(uuid, reference.getUuid()) ||
							(companyId != reference.getCompanyId())) {
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

			query.append(_SQL_SELECT_REFERENCE_WHERE);

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
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
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
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first reference in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference
	 * @throws NoSuchReferenceException if a matching reference could not be found
	 */
	@Override
	public Reference findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
	}

	/**
	 * Returns the first reference in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Reference> orderByComparator) {
		List<Reference> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
	}

	/**
	 * Returns the last reference in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Reference> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Reference> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference[] findByUuid_C_PrevAndNext(long id, String uuid,
		long companyId, OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Reference[] array = new ReferenceImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, reference, uuid,
					companyId, orderByComparator, true);

			array[1] = reference;

			array[2] = getByUuid_C_PrevAndNext(session, reference, uuid,
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

	protected Reference getByUuid_C_PrevAndNext(Session session,
		Reference reference, String uuid, long companyId,
		OrderByComparator<Reference> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_REFERENCE_WHERE);

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
			query.append(ReferenceModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(reference);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Reference> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the references where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Reference reference : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(reference);
		}
	}

	/**
	 * Returns the number of references where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching references
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REFERENCE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "reference.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "reference.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(reference.uuid IS NULL OR reference.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "reference.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			ReferenceModelImpl.GROUPID_COLUMN_BITMASK |
			ReferenceModelImpl.REFERENCEID_COLUMN_BITMASK |
			ReferenceModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the references where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching references
	 */
	@Override
	public List<Reference> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
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
	@Override
	public List<Reference> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
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
	@Override
	public List<Reference> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Reference> orderByComparator,
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

		List<Reference> list = null;

		if (retrieveFromCache) {
			list = (List<Reference>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Reference reference : list) {
					if ((groupId != reference.getGroupId())) {
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

			query.append(_SQL_SELECT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first reference in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference
	 * @throws NoSuchReferenceException if a matching reference could not be found
	 */
	@Override
	public Reference findByGroupId_First(long groupId,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByGroupId_First(groupId, orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
	}

	/**
	 * Returns the first reference in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByGroupId_First(long groupId,
		OrderByComparator<Reference> orderByComparator) {
		List<Reference> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last reference in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference
	 * @throws NoSuchReferenceException if a matching reference could not be found
	 */
	@Override
	public Reference findByGroupId_Last(long groupId,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByGroupId_Last(groupId, orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
	}

	/**
	 * Returns the last reference in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByGroupId_Last(long groupId,
		OrderByComparator<Reference> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<Reference> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference[] findByGroupId_PrevAndNext(long id, long groupId,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Reference[] array = new ReferenceImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, reference, groupId,
					orderByComparator, true);

			array[1] = reference;

			array[2] = getByGroupId_PrevAndNext(session, reference, groupId,
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

	protected Reference getByGroupId_PrevAndNext(Session session,
		Reference reference, long groupId,
		OrderByComparator<Reference> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REFERENCE_WHERE);

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
			query.append(ReferenceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(reference);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Reference> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the references that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching references that the user has permission to view
	 */
	@Override
	public List<Reference> filterFindByGroupId(long groupId) {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> filterFindByGroupId(long groupId, int start, int end) {
		return filterFindByGroupId(groupId, start, end, null);
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
	@Override
	public List<Reference> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator<Reference> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId(groupId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ReferenceModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Reference.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ReferenceImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ReferenceImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<Reference>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
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
	@Override
	public Reference[] filterFindByGroupId_PrevAndNext(long id, long groupId,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(id, groupId, orderByComparator);
		}

		Reference reference = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Reference[] array = new ReferenceImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session, reference,
					groupId, orderByComparator, true);

			array[1] = reference;

			array[2] = filterGetByGroupId_PrevAndNext(session, reference,
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

	protected Reference filterGetByGroupId_PrevAndNext(Session session,
		Reference reference, long groupId,
		OrderByComparator<Reference> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ReferenceModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Reference.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ReferenceImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ReferenceImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(reference);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Reference> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the references where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (Reference reference : findByGroupId(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(reference);
		}
	}

	/**
	 * Returns the number of references where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching references
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_REFERENCE_WHERE);

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

	/**
	 * Returns the number of references that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching references that the user has permission to view
	 */
	@Override
	public int filterCountByGroupId(long groupId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_REFERENCE_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Reference.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "reference.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			ReferenceModelImpl.COMPANYID_COLUMN_BITMASK |
			ReferenceModelImpl.REFERENCEID_COLUMN_BITMASK |
			ReferenceModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the references where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching references
	 */
	@Override
	public List<Reference> findByCompanyId(long companyId) {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	@Override
	public List<Reference> findByCompanyId(long companyId, int start, int end) {
		return findByCompanyId(companyId, start, end, null);
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
	@Override
	public List<Reference> findByCompanyId(long companyId, int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		return findByCompanyId(companyId, start, end, orderByComparator, true);
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
	@Override
	public List<Reference> findByCompanyId(long companyId, int start, int end,
		OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<Reference> list = null;

		if (retrieveFromCache) {
			list = (List<Reference>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Reference reference : list) {
					if ((companyId != reference.getCompanyId())) {
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

			query.append(_SQL_SELECT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first reference in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference
	 * @throws NoSuchReferenceException if a matching reference could not be found
	 */
	@Override
	public Reference findByCompanyId_First(long companyId,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
	}

	/**
	 * Returns the first reference in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByCompanyId_First(long companyId,
		OrderByComparator<Reference> orderByComparator) {
		List<Reference> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last reference in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference
	 * @throws NoSuchReferenceException if a matching reference could not be found
	 */
	@Override
	public Reference findByCompanyId_Last(long companyId,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByCompanyId_Last(companyId, orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
	}

	/**
	 * Returns the last reference in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByCompanyId_Last(long companyId,
		OrderByComparator<Reference> orderByComparator) {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<Reference> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference[] findByCompanyId_PrevAndNext(long id, long companyId,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Reference[] array = new ReferenceImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, reference,
					companyId, orderByComparator, true);

			array[1] = reference;

			array[2] = getByCompanyId_PrevAndNext(session, reference,
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

	protected Reference getByCompanyId_PrevAndNext(Session session,
		Reference reference, long companyId,
		OrderByComparator<Reference> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REFERENCE_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

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
			query.append(ReferenceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(reference);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Reference> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the references where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (Reference reference : findByCompanyId(companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(reference);
		}
	}

	/**
	 * Returns the number of references where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching references
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "reference.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_R_S = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByR_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByR_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			ReferenceModelImpl.RESOURCEPRIMKEY_COLUMN_BITMASK |
			ReferenceModelImpl.STATUS_COLUMN_BITMASK |
			ReferenceModelImpl.REFERENCEID_COLUMN_BITMASK |
			ReferenceModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_R_S = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByR_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_S = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByR_S",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the references where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param status the status
	 * @return the matching references
	 */
	@Override
	public List<Reference> findByR_S(long resourcePrimKey, int status) {
		return findByR_S(resourcePrimKey, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> findByR_S(long resourcePrimKey, int status,
		int start, int end) {
		return findByR_S(resourcePrimKey, status, start, end, null);
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
	@Override
	public List<Reference> findByR_S(long resourcePrimKey, int status,
		int start, int end, OrderByComparator<Reference> orderByComparator) {
		return findByR_S(resourcePrimKey, status, start, end,
			orderByComparator, true);
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
	@Override
	public List<Reference> findByR_S(long resourcePrimKey, int status,
		int start, int end, OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S;
			finderArgs = new Object[] { resourcePrimKey, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_S;
			finderArgs = new Object[] {
					resourcePrimKey, status,
					
					start, end, orderByComparator
				};
		}

		List<Reference> list = null;

		if (retrieveFromCache) {
			list = (List<Reference>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Reference reference : list) {
					if ((resourcePrimKey != reference.getResourcePrimKey()) ||
							(status != reference.getStatus())) {
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

			query.append(_SQL_SELECT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_R_S_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(status);

				if (!pagination) {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first reference in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference
	 * @throws NoSuchReferenceException if a matching reference could not be found
	 */
	@Override
	public Reference findByR_S_First(long resourcePrimKey, int status,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByR_S_First(resourcePrimKey, status,
				orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
	}

	/**
	 * Returns the first reference in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByR_S_First(long resourcePrimKey, int status,
		OrderByComparator<Reference> orderByComparator) {
		List<Reference> list = findByR_S(resourcePrimKey, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference findByR_S_Last(long resourcePrimKey, int status,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByR_S_Last(resourcePrimKey, status,
				orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
	}

	/**
	 * Returns the last reference in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByR_S_Last(long resourcePrimKey, int status,
		OrderByComparator<Reference> orderByComparator) {
		int count = countByR_S(resourcePrimKey, status);

		if (count == 0) {
			return null;
		}

		List<Reference> list = findByR_S(resourcePrimKey, status, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference[] findByR_S_PrevAndNext(long id, long resourcePrimKey,
		int status, OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Reference[] array = new ReferenceImpl[3];

			array[0] = getByR_S_PrevAndNext(session, reference,
					resourcePrimKey, status, orderByComparator, true);

			array[1] = reference;

			array[2] = getByR_S_PrevAndNext(session, reference,
					resourcePrimKey, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Reference getByR_S_PrevAndNext(Session session,
		Reference reference, long resourcePrimKey, int status,
		OrderByComparator<Reference> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_REFERENCE_WHERE);

		query.append(_FINDER_COLUMN_R_S_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_S_STATUS_2);

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
			query.append(ReferenceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(reference);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Reference> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
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
	@Override
	public List<Reference> findByR_S(long resourcePrimKey, int[] statuses) {
		return findByR_S(resourcePrimKey, statuses, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> findByR_S(long resourcePrimKey, int[] statuses,
		int start, int end) {
		return findByR_S(resourcePrimKey, statuses, start, end, null);
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
	@Override
	public List<Reference> findByR_S(long resourcePrimKey, int[] statuses,
		int start, int end, OrderByComparator<Reference> orderByComparator) {
		return findByR_S(resourcePrimKey, statuses, start, end,
			orderByComparator, true);
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
	@Override
	public List<Reference> findByR_S(long resourcePrimKey, int[] statuses,
		int start, int end, OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.unique(statuses);

			Arrays.sort(statuses);
		}

		if (statuses.length == 1) {
			return findByR_S(resourcePrimKey, statuses[0], start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					resourcePrimKey, StringUtil.merge(statuses)
				};
		}
		else {
			finderArgs = new Object[] {
					resourcePrimKey, StringUtil.merge(statuses),
					
					start, end, orderByComparator
				};
		}

		List<Reference> list = null;

		if (retrieveFromCache) {
			list = (List<Reference>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_R_S,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Reference reference : list) {
					if ((resourcePrimKey != reference.getResourcePrimKey()) ||
							!ArrayUtil.contains(statuses, reference.getStatus())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_R_S_RESOURCEPRIMKEY_2);

			if (statuses.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_R_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				if (!pagination) {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_R_S,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_R_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the references where resourcePrimKey = &#63; and status = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param status the status
	 */
	@Override
	public void removeByR_S(long resourcePrimKey, int status) {
		for (Reference reference : findByR_S(resourcePrimKey, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(reference);
		}
	}

	/**
	 * Returns the number of references where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param status the status
	 * @return the number of matching references
	 */
	@Override
	public int countByR_S(long resourcePrimKey, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_R_S;

		Object[] finderArgs = new Object[] { resourcePrimKey, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_R_S_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(status);

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

	/**
	 * Returns the number of references where resourcePrimKey = &#63; and status = any &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param statuses the statuses
	 * @return the number of matching references
	 */
	@Override
	public int countByR_S(long resourcePrimKey, int[] statuses) {
		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.unique(statuses);

			Arrays.sort(statuses);
		}

		Object[] finderArgs = new Object[] {
				resourcePrimKey, StringUtil.merge(statuses)
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_R_S_RESOURCEPRIMKEY_2);

			if (statuses.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_R_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_S,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_R_S_RESOURCEPRIMKEY_2 = "reference.resourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_R_S_STATUS_2 = "reference.status = ?";
	private static final String _FINDER_COLUMN_R_S_STATUS_7 = "reference.status IN (";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_U",
			new String[] { Long.class.getName(), Long.class.getName() },
			ReferenceModelImpl.GROUPID_COLUMN_BITMASK |
			ReferenceModelImpl.USERID_COLUMN_BITMASK |
			ReferenceModelImpl.REFERENCEID_COLUMN_BITMASK |
			ReferenceModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_U = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_U",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the references where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching references
	 */
	@Override
	public List<Reference> findByG_U(long groupId, long userId) {
		return findByG_U(groupId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	@Override
	public List<Reference> findByG_U(long groupId, long userId, int start,
		int end) {
		return findByG_U(groupId, userId, start, end, null);
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
	@Override
	public List<Reference> findByG_U(long groupId, long userId, int start,
		int end, OrderByComparator<Reference> orderByComparator) {
		return findByG_U(groupId, userId, start, end, orderByComparator, true);
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
	@Override
	public List<Reference> findByG_U(long groupId, long userId, int start,
		int end, OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U;
			finderArgs = new Object[] { groupId, userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U;
			finderArgs = new Object[] {
					groupId, userId,
					
					start, end, orderByComparator
				};
		}

		List<Reference> list = null;

		if (retrieveFromCache) {
			list = (List<Reference>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Reference reference : list) {
					if ((groupId != reference.getGroupId()) ||
							(userId != reference.getUserId())) {
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

			query.append(_SQL_SELECT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				if (!pagination) {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first reference in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference
	 * @throws NoSuchReferenceException if a matching reference could not be found
	 */
	@Override
	public Reference findByG_U_First(long groupId, long userId,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByG_U_First(groupId, userId,
				orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
	}

	/**
	 * Returns the first reference in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByG_U_First(long groupId, long userId,
		OrderByComparator<Reference> orderByComparator) {
		List<Reference> list = findByG_U(groupId, userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference findByG_U_Last(long groupId, long userId,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByG_U_Last(groupId, userId, orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
	}

	/**
	 * Returns the last reference in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByG_U_Last(long groupId, long userId,
		OrderByComparator<Reference> orderByComparator) {
		int count = countByG_U(groupId, userId);

		if (count == 0) {
			return null;
		}

		List<Reference> list = findByG_U(groupId, userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference[] findByG_U_PrevAndNext(long id, long groupId,
		long userId, OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Reference[] array = new ReferenceImpl[3];

			array[0] = getByG_U_PrevAndNext(session, reference, groupId,
					userId, orderByComparator, true);

			array[1] = reference;

			array[2] = getByG_U_PrevAndNext(session, reference, groupId,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Reference getByG_U_PrevAndNext(Session session,
		Reference reference, long groupId, long userId,
		OrderByComparator<Reference> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_REFERENCE_WHERE);

		query.append(_FINDER_COLUMN_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_USERID_2);

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
			query.append(ReferenceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(reference);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Reference> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the references that the user has permission to view where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching references that the user has permission to view
	 */
	@Override
	public List<Reference> filterFindByG_U(long groupId, long userId) {
		return filterFindByG_U(groupId, userId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> filterFindByG_U(long groupId, long userId,
		int start, int end) {
		return filterFindByG_U(groupId, userId, start, end, null);
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
	@Override
	public List<Reference> filterFindByG_U(long groupId, long userId,
		int start, int end, OrderByComparator<Reference> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_U(groupId, userId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_USERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ReferenceModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Reference.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ReferenceImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ReferenceImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(userId);

			return (List<Reference>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
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
	@Override
	public Reference[] filterFindByG_U_PrevAndNext(long id, long groupId,
		long userId, OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_U_PrevAndNext(id, groupId, userId, orderByComparator);
		}

		Reference reference = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Reference[] array = new ReferenceImpl[3];

			array[0] = filterGetByG_U_PrevAndNext(session, reference, groupId,
					userId, orderByComparator, true);

			array[1] = reference;

			array[2] = filterGetByG_U_PrevAndNext(session, reference, groupId,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Reference filterGetByG_U_PrevAndNext(Session session,
		Reference reference, long groupId, long userId,
		OrderByComparator<Reference> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_USERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ReferenceModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Reference.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ReferenceImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ReferenceImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(reference);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Reference> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the references where groupId = &#63; and userId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 */
	@Override
	public void removeByG_U(long groupId, long userId) {
		for (Reference reference : findByG_U(groupId, userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(reference);
		}
	}

	/**
	 * Returns the number of references where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching references
	 */
	@Override
	public int countByG_U(long groupId, long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_U;

		Object[] finderArgs = new Object[] { groupId, userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

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

	/**
	 * Returns the number of references that the user has permission to view where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching references that the user has permission to view
	 */
	@Override
	public int filterCountByG_U(long groupId, long userId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_U(groupId, userId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_REFERENCE_WHERE);

		query.append(_FINDER_COLUMN_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_USERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Reference.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(userId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_U_GROUPID_2 = "reference.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_USERID_2 = "reference.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_R = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_R",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_R",
			new String[] { Long.class.getName(), String.class.getName() },
			ReferenceModelImpl.GROUPID_COLUMN_BITMASK |
			ReferenceModelImpl.REFERENCEID_COLUMN_BITMASK |
			ReferenceModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_R = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_R",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the references where groupId = &#63; and referenceId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @return the matching references
	 */
	@Override
	public List<Reference> findByG_R(long groupId, String referenceId) {
		return findByG_R(groupId, referenceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> findByG_R(long groupId, String referenceId,
		int start, int end) {
		return findByG_R(groupId, referenceId, start, end, null);
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
	@Override
	public List<Reference> findByG_R(long groupId, String referenceId,
		int start, int end, OrderByComparator<Reference> orderByComparator) {
		return findByG_R(groupId, referenceId, start, end, orderByComparator,
			true);
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
	@Override
	public List<Reference> findByG_R(long groupId, String referenceId,
		int start, int end, OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R;
			finderArgs = new Object[] { groupId, referenceId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_R;
			finderArgs = new Object[] {
					groupId, referenceId,
					
					start, end, orderByComparator
				};
		}

		List<Reference> list = null;

		if (retrieveFromCache) {
			list = (List<Reference>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Reference reference : list) {
					if ((groupId != reference.getGroupId()) ||
							!Objects.equals(referenceId,
								reference.getReferenceId())) {
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

			query.append(_SQL_SELECT_REFERENCE_WHERE);

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

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
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

				if (!pagination) {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first reference in the ordered set where groupId = &#63; and referenceId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference
	 * @throws NoSuchReferenceException if a matching reference could not be found
	 */
	@Override
	public Reference findByG_R_First(long groupId, String referenceId,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByG_R_First(groupId, referenceId,
				orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", referenceId=");
		msg.append(referenceId);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
	}

	/**
	 * Returns the first reference in the ordered set where groupId = &#63; and referenceId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByG_R_First(long groupId, String referenceId,
		OrderByComparator<Reference> orderByComparator) {
		List<Reference> list = findByG_R(groupId, referenceId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference findByG_R_Last(long groupId, String referenceId,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByG_R_Last(groupId, referenceId,
				orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", referenceId=");
		msg.append(referenceId);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
	}

	/**
	 * Returns the last reference in the ordered set where groupId = &#63; and referenceId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByG_R_Last(long groupId, String referenceId,
		OrderByComparator<Reference> orderByComparator) {
		int count = countByG_R(groupId, referenceId);

		if (count == 0) {
			return null;
		}

		List<Reference> list = findByG_R(groupId, referenceId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference[] findByG_R_PrevAndNext(long id, long groupId,
		String referenceId, OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Reference[] array = new ReferenceImpl[3];

			array[0] = getByG_R_PrevAndNext(session, reference, groupId,
					referenceId, orderByComparator, true);

			array[1] = reference;

			array[2] = getByG_R_PrevAndNext(session, reference, groupId,
					referenceId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Reference getByG_R_PrevAndNext(Session session,
		Reference reference, long groupId, String referenceId,
		OrderByComparator<Reference> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_REFERENCE_WHERE);

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
			query.append(ReferenceModelImpl.ORDER_BY_JPQL);
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

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(reference);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Reference> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the references that the user has permission to view where groupId = &#63; and referenceId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @return the matching references that the user has permission to view
	 */
	@Override
	public List<Reference> filterFindByG_R(long groupId, String referenceId) {
		return filterFindByG_R(groupId, referenceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> filterFindByG_R(long groupId, String referenceId,
		int start, int end) {
		return filterFindByG_R(groupId, referenceId, start, end, null);
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
	@Override
	public List<Reference> filterFindByG_R(long groupId, String referenceId,
		int start, int end, OrderByComparator<Reference> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_R(groupId, referenceId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_1);
		}

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

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ReferenceModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Reference.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ReferenceImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ReferenceImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindReferenceId) {
				qPos.add(referenceId);
			}

			return (List<Reference>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
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
	@Override
	public Reference[] filterFindByG_R_PrevAndNext(long id, long groupId,
		String referenceId, OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_R_PrevAndNext(id, groupId, referenceId,
				orderByComparator);
		}

		Reference reference = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Reference[] array = new ReferenceImpl[3];

			array[0] = filterGetByG_R_PrevAndNext(session, reference, groupId,
					referenceId, orderByComparator, true);

			array[1] = reference;

			array[2] = filterGetByG_R_PrevAndNext(session, reference, groupId,
					referenceId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Reference filterGetByG_R_PrevAndNext(Session session,
		Reference reference, long groupId, String referenceId,
		OrderByComparator<Reference> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_1);
		}

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

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ReferenceModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Reference.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ReferenceImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ReferenceImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindReferenceId) {
			qPos.add(referenceId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(reference);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Reference> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the references where groupId = &#63; and referenceId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 */
	@Override
	public void removeByG_R(long groupId, String referenceId) {
		for (Reference reference : findByG_R(groupId, referenceId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(reference);
		}
	}

	/**
	 * Returns the number of references where groupId = &#63; and referenceId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @return the number of matching references
	 */
	@Override
	public int countByG_R(long groupId, String referenceId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_R;

		Object[] finderArgs = new Object[] { groupId, referenceId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REFERENCE_WHERE);

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

	/**
	 * Returns the number of references that the user has permission to view where groupId = &#63; and referenceId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @return the number of matching references that the user has permission to view
	 */
	@Override
	public int filterCountByG_R(long groupId, String referenceId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_R(groupId, referenceId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_REFERENCE_WHERE);

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

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Reference.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindReferenceId) {
				qPos.add(referenceId);
			}

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_R_GROUPID_2 = "reference.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_R_REFERENCEID_1 = "reference.referenceId IS NULL";
	private static final String _FINDER_COLUMN_G_R_REFERENCEID_2 = "reference.referenceId = ?";
	private static final String _FINDER_COLUMN_G_R_REFERENCEID_3 = "(reference.referenceId IS NULL OR reference.referenceId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_UT = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_UT",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UT = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_UT",
			new String[] { Long.class.getName(), String.class.getName() },
			ReferenceModelImpl.GROUPID_COLUMN_BITMASK |
			ReferenceModelImpl.URLTITLE_COLUMN_BITMASK |
			ReferenceModelImpl.REFERENCEID_COLUMN_BITMASK |
			ReferenceModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_UT = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_UT",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the references where groupId = &#63; and urlTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param urlTitle the url title
	 * @return the matching references
	 */
	@Override
	public List<Reference> findByG_UT(long groupId, String urlTitle) {
		return findByG_UT(groupId, urlTitle, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> findByG_UT(long groupId, String urlTitle, int start,
		int end) {
		return findByG_UT(groupId, urlTitle, start, end, null);
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
	@Override
	public List<Reference> findByG_UT(long groupId, String urlTitle, int start,
		int end, OrderByComparator<Reference> orderByComparator) {
		return findByG_UT(groupId, urlTitle, start, end, orderByComparator, true);
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
	@Override
	public List<Reference> findByG_UT(long groupId, String urlTitle, int start,
		int end, OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UT;
			finderArgs = new Object[] { groupId, urlTitle };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_UT;
			finderArgs = new Object[] {
					groupId, urlTitle,
					
					start, end, orderByComparator
				};
		}

		List<Reference> list = null;

		if (retrieveFromCache) {
			list = (List<Reference>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Reference reference : list) {
					if ((groupId != reference.getGroupId()) ||
							!Objects.equals(urlTitle, reference.getUrlTitle())) {
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

			query.append(_SQL_SELECT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_G_UT_GROUPID_2);

			boolean bindUrlTitle = false;

			if (urlTitle == null) {
				query.append(_FINDER_COLUMN_G_UT_URLTITLE_1);
			}
			else if (urlTitle.equals("")) {
				query.append(_FINDER_COLUMN_G_UT_URLTITLE_3);
			}
			else {
				bindUrlTitle = true;

				query.append(_FINDER_COLUMN_G_UT_URLTITLE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindUrlTitle) {
					qPos.add(urlTitle);
				}

				if (!pagination) {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first reference in the ordered set where groupId = &#63; and urlTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param urlTitle the url title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference
	 * @throws NoSuchReferenceException if a matching reference could not be found
	 */
	@Override
	public Reference findByG_UT_First(long groupId, String urlTitle,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByG_UT_First(groupId, urlTitle,
				orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", urlTitle=");
		msg.append(urlTitle);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
	}

	/**
	 * Returns the first reference in the ordered set where groupId = &#63; and urlTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param urlTitle the url title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByG_UT_First(long groupId, String urlTitle,
		OrderByComparator<Reference> orderByComparator) {
		List<Reference> list = findByG_UT(groupId, urlTitle, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference findByG_UT_Last(long groupId, String urlTitle,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByG_UT_Last(groupId, urlTitle,
				orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", urlTitle=");
		msg.append(urlTitle);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
	}

	/**
	 * Returns the last reference in the ordered set where groupId = &#63; and urlTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param urlTitle the url title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByG_UT_Last(long groupId, String urlTitle,
		OrderByComparator<Reference> orderByComparator) {
		int count = countByG_UT(groupId, urlTitle);

		if (count == 0) {
			return null;
		}

		List<Reference> list = findByG_UT(groupId, urlTitle, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference[] findByG_UT_PrevAndNext(long id, long groupId,
		String urlTitle, OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Reference[] array = new ReferenceImpl[3];

			array[0] = getByG_UT_PrevAndNext(session, reference, groupId,
					urlTitle, orderByComparator, true);

			array[1] = reference;

			array[2] = getByG_UT_PrevAndNext(session, reference, groupId,
					urlTitle, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Reference getByG_UT_PrevAndNext(Session session,
		Reference reference, long groupId, String urlTitle,
		OrderByComparator<Reference> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_REFERENCE_WHERE);

		query.append(_FINDER_COLUMN_G_UT_GROUPID_2);

		boolean bindUrlTitle = false;

		if (urlTitle == null) {
			query.append(_FINDER_COLUMN_G_UT_URLTITLE_1);
		}
		else if (urlTitle.equals("")) {
			query.append(_FINDER_COLUMN_G_UT_URLTITLE_3);
		}
		else {
			bindUrlTitle = true;

			query.append(_FINDER_COLUMN_G_UT_URLTITLE_2);
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
			query.append(ReferenceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindUrlTitle) {
			qPos.add(urlTitle);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(reference);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Reference> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the references that the user has permission to view where groupId = &#63; and urlTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param urlTitle the url title
	 * @return the matching references that the user has permission to view
	 */
	@Override
	public List<Reference> filterFindByG_UT(long groupId, String urlTitle) {
		return filterFindByG_UT(groupId, urlTitle, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> filterFindByG_UT(long groupId, String urlTitle,
		int start, int end) {
		return filterFindByG_UT(groupId, urlTitle, start, end, null);
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
	@Override
	public List<Reference> filterFindByG_UT(long groupId, String urlTitle,
		int start, int end, OrderByComparator<Reference> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_UT(groupId, urlTitle, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_UT_GROUPID_2);

		boolean bindUrlTitle = false;

		if (urlTitle == null) {
			query.append(_FINDER_COLUMN_G_UT_URLTITLE_1);
		}
		else if (urlTitle.equals("")) {
			query.append(_FINDER_COLUMN_G_UT_URLTITLE_3);
		}
		else {
			bindUrlTitle = true;

			query.append(_FINDER_COLUMN_G_UT_URLTITLE_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ReferenceModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Reference.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ReferenceImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ReferenceImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindUrlTitle) {
				qPos.add(urlTitle);
			}

			return (List<Reference>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
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
	@Override
	public Reference[] filterFindByG_UT_PrevAndNext(long id, long groupId,
		String urlTitle, OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_UT_PrevAndNext(id, groupId, urlTitle,
				orderByComparator);
		}

		Reference reference = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Reference[] array = new ReferenceImpl[3];

			array[0] = filterGetByG_UT_PrevAndNext(session, reference, groupId,
					urlTitle, orderByComparator, true);

			array[1] = reference;

			array[2] = filterGetByG_UT_PrevAndNext(session, reference, groupId,
					urlTitle, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Reference filterGetByG_UT_PrevAndNext(Session session,
		Reference reference, long groupId, String urlTitle,
		OrderByComparator<Reference> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_UT_GROUPID_2);

		boolean bindUrlTitle = false;

		if (urlTitle == null) {
			query.append(_FINDER_COLUMN_G_UT_URLTITLE_1);
		}
		else if (urlTitle.equals("")) {
			query.append(_FINDER_COLUMN_G_UT_URLTITLE_3);
		}
		else {
			bindUrlTitle = true;

			query.append(_FINDER_COLUMN_G_UT_URLTITLE_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ReferenceModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Reference.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ReferenceImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ReferenceImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindUrlTitle) {
			qPos.add(urlTitle);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(reference);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Reference> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the references where groupId = &#63; and urlTitle = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param urlTitle the url title
	 */
	@Override
	public void removeByG_UT(long groupId, String urlTitle) {
		for (Reference reference : findByG_UT(groupId, urlTitle,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(reference);
		}
	}

	/**
	 * Returns the number of references where groupId = &#63; and urlTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param urlTitle the url title
	 * @return the number of matching references
	 */
	@Override
	public int countByG_UT(long groupId, String urlTitle) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_UT;

		Object[] finderArgs = new Object[] { groupId, urlTitle };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_G_UT_GROUPID_2);

			boolean bindUrlTitle = false;

			if (urlTitle == null) {
				query.append(_FINDER_COLUMN_G_UT_URLTITLE_1);
			}
			else if (urlTitle.equals("")) {
				query.append(_FINDER_COLUMN_G_UT_URLTITLE_3);
			}
			else {
				bindUrlTitle = true;

				query.append(_FINDER_COLUMN_G_UT_URLTITLE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindUrlTitle) {
					qPos.add(urlTitle);
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

	/**
	 * Returns the number of references that the user has permission to view where groupId = &#63; and urlTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param urlTitle the url title
	 * @return the number of matching references that the user has permission to view
	 */
	@Override
	public int filterCountByG_UT(long groupId, String urlTitle) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_UT(groupId, urlTitle);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_REFERENCE_WHERE);

		query.append(_FINDER_COLUMN_G_UT_GROUPID_2);

		boolean bindUrlTitle = false;

		if (urlTitle == null) {
			query.append(_FINDER_COLUMN_G_UT_URLTITLE_1);
		}
		else if (urlTitle.equals("")) {
			query.append(_FINDER_COLUMN_G_UT_URLTITLE_3);
		}
		else {
			bindUrlTitle = true;

			query.append(_FINDER_COLUMN_G_UT_URLTITLE_2);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Reference.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindUrlTitle) {
				qPos.add(urlTitle);
			}

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_UT_GROUPID_2 = "reference.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_UT_URLTITLE_1 = "reference.urlTitle IS NULL";
	private static final String _FINDER_COLUMN_G_UT_URLTITLE_2 = "reference.urlTitle = ?";
	private static final String _FINDER_COLUMN_G_UT_URLTITLE_3 = "(reference.urlTitle IS NULL OR reference.urlTitle = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_ST = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_ST",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_ST = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_ST",
			new String[] { Long.class.getName(), Integer.class.getName() },
			ReferenceModelImpl.GROUPID_COLUMN_BITMASK |
			ReferenceModelImpl.STATUS_COLUMN_BITMASK |
			ReferenceModelImpl.REFERENCEID_COLUMN_BITMASK |
			ReferenceModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_ST = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_ST",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the references where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching references
	 */
	@Override
	public List<Reference> findByG_ST(long groupId, int status) {
		return findByG_ST(groupId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> findByG_ST(long groupId, int status, int start,
		int end) {
		return findByG_ST(groupId, status, start, end, null);
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
	@Override
	public List<Reference> findByG_ST(long groupId, int status, int start,
		int end, OrderByComparator<Reference> orderByComparator) {
		return findByG_ST(groupId, status, start, end, orderByComparator, true);
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
	@Override
	public List<Reference> findByG_ST(long groupId, int status, int start,
		int end, OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_ST;
			finderArgs = new Object[] { groupId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_ST;
			finderArgs = new Object[] {
					groupId, status,
					
					start, end, orderByComparator
				};
		}

		List<Reference> list = null;

		if (retrieveFromCache) {
			list = (List<Reference>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Reference reference : list) {
					if ((groupId != reference.getGroupId()) ||
							(status != reference.getStatus())) {
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

			query.append(_SQL_SELECT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_G_ST_GROUPID_2);

			query.append(_FINDER_COLUMN_G_ST_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(status);

				if (!pagination) {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first reference in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference
	 * @throws NoSuchReferenceException if a matching reference could not be found
	 */
	@Override
	public Reference findByG_ST_First(long groupId, int status,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByG_ST_First(groupId, status,
				orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
	}

	/**
	 * Returns the first reference in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByG_ST_First(long groupId, int status,
		OrderByComparator<Reference> orderByComparator) {
		List<Reference> list = findByG_ST(groupId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference findByG_ST_Last(long groupId, int status,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByG_ST_Last(groupId, status,
				orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
	}

	/**
	 * Returns the last reference in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByG_ST_Last(long groupId, int status,
		OrderByComparator<Reference> orderByComparator) {
		int count = countByG_ST(groupId, status);

		if (count == 0) {
			return null;
		}

		List<Reference> list = findByG_ST(groupId, status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference[] findByG_ST_PrevAndNext(long id, long groupId,
		int status, OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Reference[] array = new ReferenceImpl[3];

			array[0] = getByG_ST_PrevAndNext(session, reference, groupId,
					status, orderByComparator, true);

			array[1] = reference;

			array[2] = getByG_ST_PrevAndNext(session, reference, groupId,
					status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Reference getByG_ST_PrevAndNext(Session session,
		Reference reference, long groupId, int status,
		OrderByComparator<Reference> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_REFERENCE_WHERE);

		query.append(_FINDER_COLUMN_G_ST_GROUPID_2);

		query.append(_FINDER_COLUMN_G_ST_STATUS_2);

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
			query.append(ReferenceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(reference);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Reference> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the references that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching references that the user has permission to view
	 */
	@Override
	public List<Reference> filterFindByG_ST(long groupId, int status) {
		return filterFindByG_ST(groupId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> filterFindByG_ST(long groupId, int status,
		int start, int end) {
		return filterFindByG_ST(groupId, status, start, end, null);
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
	@Override
	public List<Reference> filterFindByG_ST(long groupId, int status,
		int start, int end, OrderByComparator<Reference> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_ST(groupId, status, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_ST_GROUPID_2);

		query.append(_FINDER_COLUMN_G_ST_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ReferenceModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Reference.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ReferenceImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ReferenceImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(status);

			return (List<Reference>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
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
	@Override
	public Reference[] filterFindByG_ST_PrevAndNext(long id, long groupId,
		int status, OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_ST_PrevAndNext(id, groupId, status, orderByComparator);
		}

		Reference reference = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Reference[] array = new ReferenceImpl[3];

			array[0] = filterGetByG_ST_PrevAndNext(session, reference, groupId,
					status, orderByComparator, true);

			array[1] = reference;

			array[2] = filterGetByG_ST_PrevAndNext(session, reference, groupId,
					status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Reference filterGetByG_ST_PrevAndNext(Session session,
		Reference reference, long groupId, int status,
		OrderByComparator<Reference> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_ST_GROUPID_2);

		query.append(_FINDER_COLUMN_G_ST_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ReferenceModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Reference.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ReferenceImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ReferenceImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(reference);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Reference> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the references where groupId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 */
	@Override
	public void removeByG_ST(long groupId, int status) {
		for (Reference reference : findByG_ST(groupId, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(reference);
		}
	}

	/**
	 * Returns the number of references where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching references
	 */
	@Override
	public int countByG_ST(long groupId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_ST;

		Object[] finderArgs = new Object[] { groupId, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_G_ST_GROUPID_2);

			query.append(_FINDER_COLUMN_G_ST_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(status);

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

	/**
	 * Returns the number of references that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching references that the user has permission to view
	 */
	@Override
	public int filterCountByG_ST(long groupId, int status) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_ST(groupId, status);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_REFERENCE_WHERE);

		query.append(_FINDER_COLUMN_G_ST_GROUPID_2);

		query.append(_FINDER_COLUMN_G_ST_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Reference.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(status);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_ST_GROUPID_2 = "reference.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_ST_STATUS_2 = "reference.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_V = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_V",
			new String[] {
				Long.class.getName(), Double.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_V = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_V",
			new String[] { Long.class.getName(), Double.class.getName() },
			ReferenceModelImpl.COMPANYID_COLUMN_BITMASK |
			ReferenceModelImpl.VERSION_COLUMN_BITMASK |
			ReferenceModelImpl.REFERENCEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_V = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_V",
			new String[] { Long.class.getName(), Double.class.getName() });

	/**
	 * Returns all the references where companyId = &#63; and version = &#63;.
	 *
	 * @param companyId the company ID
	 * @param version the version
	 * @return the matching references
	 */
	@Override
	public List<Reference> findByC_V(long companyId, double version) {
		return findByC_V(companyId, version, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> findByC_V(long companyId, double version, int start,
		int end) {
		return findByC_V(companyId, version, start, end, null);
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
	@Override
	public List<Reference> findByC_V(long companyId, double version, int start,
		int end, OrderByComparator<Reference> orderByComparator) {
		return findByC_V(companyId, version, start, end, orderByComparator, true);
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
	@Override
	public List<Reference> findByC_V(long companyId, double version, int start,
		int end, OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_V;
			finderArgs = new Object[] { companyId, version };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_V;
			finderArgs = new Object[] {
					companyId, version,
					
					start, end, orderByComparator
				};
		}

		List<Reference> list = null;

		if (retrieveFromCache) {
			list = (List<Reference>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Reference reference : list) {
					if ((companyId != reference.getCompanyId()) ||
							(version != reference.getVersion())) {
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

			query.append(_SQL_SELECT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_C_V_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_V_VERSION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(version);

				if (!pagination) {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first reference in the ordered set where companyId = &#63; and version = &#63;.
	 *
	 * @param companyId the company ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference
	 * @throws NoSuchReferenceException if a matching reference could not be found
	 */
	@Override
	public Reference findByC_V_First(long companyId, double version,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByC_V_First(companyId, version,
				orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
	}

	/**
	 * Returns the first reference in the ordered set where companyId = &#63; and version = &#63;.
	 *
	 * @param companyId the company ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByC_V_First(long companyId, double version,
		OrderByComparator<Reference> orderByComparator) {
		List<Reference> list = findByC_V(companyId, version, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference findByC_V_Last(long companyId, double version,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByC_V_Last(companyId, version,
				orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", version=");
		msg.append(version);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
	}

	/**
	 * Returns the last reference in the ordered set where companyId = &#63; and version = &#63;.
	 *
	 * @param companyId the company ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByC_V_Last(long companyId, double version,
		OrderByComparator<Reference> orderByComparator) {
		int count = countByC_V(companyId, version);

		if (count == 0) {
			return null;
		}

		List<Reference> list = findByC_V(companyId, version, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference[] findByC_V_PrevAndNext(long id, long companyId,
		double version, OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Reference[] array = new ReferenceImpl[3];

			array[0] = getByC_V_PrevAndNext(session, reference, companyId,
					version, orderByComparator, true);

			array[1] = reference;

			array[2] = getByC_V_PrevAndNext(session, reference, companyId,
					version, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Reference getByC_V_PrevAndNext(Session session,
		Reference reference, long companyId, double version,
		OrderByComparator<Reference> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_REFERENCE_WHERE);

		query.append(_FINDER_COLUMN_C_V_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_V_VERSION_2);

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
			query.append(ReferenceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(version);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(reference);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Reference> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the references where companyId = &#63; and version = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param version the version
	 */
	@Override
	public void removeByC_V(long companyId, double version) {
		for (Reference reference : findByC_V(companyId, version,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(reference);
		}
	}

	/**
	 * Returns the number of references where companyId = &#63; and version = &#63;.
	 *
	 * @param companyId the company ID
	 * @param version the version
	 * @return the number of matching references
	 */
	@Override
	public int countByC_V(long companyId, double version) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_V;

		Object[] finderArgs = new Object[] { companyId, version };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_C_V_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_V_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_C_V_COMPANYID_2 = "reference.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_V_VERSION_2 = "reference.version = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_ST = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_ST",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_ST = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_ST",
			new String[] { Long.class.getName(), Integer.class.getName() },
			ReferenceModelImpl.COMPANYID_COLUMN_BITMASK |
			ReferenceModelImpl.STATUS_COLUMN_BITMASK |
			ReferenceModelImpl.REFERENCEID_COLUMN_BITMASK |
			ReferenceModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_ST = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_ST",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the references where companyId = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @return the matching references
	 */
	@Override
	public List<Reference> findByC_ST(long companyId, int status) {
		return findByC_ST(companyId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> findByC_ST(long companyId, int status, int start,
		int end) {
		return findByC_ST(companyId, status, start, end, null);
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
	@Override
	public List<Reference> findByC_ST(long companyId, int status, int start,
		int end, OrderByComparator<Reference> orderByComparator) {
		return findByC_ST(companyId, status, start, end, orderByComparator, true);
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
	@Override
	public List<Reference> findByC_ST(long companyId, int status, int start,
		int end, OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_ST;
			finderArgs = new Object[] { companyId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_ST;
			finderArgs = new Object[] {
					companyId, status,
					
					start, end, orderByComparator
				};
		}

		List<Reference> list = null;

		if (retrieveFromCache) {
			list = (List<Reference>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Reference reference : list) {
					if ((companyId != reference.getCompanyId()) ||
							(status != reference.getStatus())) {
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

			query.append(_SQL_SELECT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_C_ST_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_ST_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(status);

				if (!pagination) {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first reference in the ordered set where companyId = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference
	 * @throws NoSuchReferenceException if a matching reference could not be found
	 */
	@Override
	public Reference findByC_ST_First(long companyId, int status,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByC_ST_First(companyId, status,
				orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
	}

	/**
	 * Returns the first reference in the ordered set where companyId = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByC_ST_First(long companyId, int status,
		OrderByComparator<Reference> orderByComparator) {
		List<Reference> list = findByC_ST(companyId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference findByC_ST_Last(long companyId, int status,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByC_ST_Last(companyId, status,
				orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
	}

	/**
	 * Returns the last reference in the ordered set where companyId = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByC_ST_Last(long companyId, int status,
		OrderByComparator<Reference> orderByComparator) {
		int count = countByC_ST(companyId, status);

		if (count == 0) {
			return null;
		}

		List<Reference> list = findByC_ST(companyId, status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference[] findByC_ST_PrevAndNext(long id, long companyId,
		int status, OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Reference[] array = new ReferenceImpl[3];

			array[0] = getByC_ST_PrevAndNext(session, reference, companyId,
					status, orderByComparator, true);

			array[1] = reference;

			array[2] = getByC_ST_PrevAndNext(session, reference, companyId,
					status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Reference getByC_ST_PrevAndNext(Session session,
		Reference reference, long companyId, int status,
		OrderByComparator<Reference> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_REFERENCE_WHERE);

		query.append(_FINDER_COLUMN_C_ST_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_ST_STATUS_2);

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
			query.append(ReferenceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(reference);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Reference> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the references where companyId = &#63; and status = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 */
	@Override
	public void removeByC_ST(long companyId, int status) {
		for (Reference reference : findByC_ST(companyId, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(reference);
		}
	}

	/**
	 * Returns the number of references where companyId = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @return the number of matching references
	 */
	@Override
	public int countByC_ST(long companyId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_ST;

		Object[] finderArgs = new Object[] { companyId, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_C_ST_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_ST_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_C_ST_COMPANYID_2 = "reference.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_ST_STATUS_2 = "reference.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_NOTST = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_NotST",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_NOTST = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByC_NotST",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the references where companyId = &#63; and status &ne; &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @return the matching references
	 */
	@Override
	public List<Reference> findByC_NotST(long companyId, int status) {
		return findByC_NotST(companyId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> findByC_NotST(long companyId, int status, int start,
		int end) {
		return findByC_NotST(companyId, status, start, end, null);
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
	@Override
	public List<Reference> findByC_NotST(long companyId, int status, int start,
		int end, OrderByComparator<Reference> orderByComparator) {
		return findByC_NotST(companyId, status, start, end, orderByComparator,
			true);
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
	@Override
	public List<Reference> findByC_NotST(long companyId, int status, int start,
		int end, OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_NOTST;
		finderArgs = new Object[] {
				companyId, status,
				
				start, end, orderByComparator
			};

		List<Reference> list = null;

		if (retrieveFromCache) {
			list = (List<Reference>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Reference reference : list) {
					if ((companyId != reference.getCompanyId()) ||
							(status == reference.getStatus())) {
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

			query.append(_SQL_SELECT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_C_NOTST_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_NOTST_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(status);

				if (!pagination) {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first reference in the ordered set where companyId = &#63; and status &ne; &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference
	 * @throws NoSuchReferenceException if a matching reference could not be found
	 */
	@Override
	public Reference findByC_NotST_First(long companyId, int status,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByC_NotST_First(companyId, status,
				orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
	}

	/**
	 * Returns the first reference in the ordered set where companyId = &#63; and status &ne; &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByC_NotST_First(long companyId, int status,
		OrderByComparator<Reference> orderByComparator) {
		List<Reference> list = findByC_NotST(companyId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference findByC_NotST_Last(long companyId, int status,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByC_NotST_Last(companyId, status,
				orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
	}

	/**
	 * Returns the last reference in the ordered set where companyId = &#63; and status &ne; &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByC_NotST_Last(long companyId, int status,
		OrderByComparator<Reference> orderByComparator) {
		int count = countByC_NotST(companyId, status);

		if (count == 0) {
			return null;
		}

		List<Reference> list = findByC_NotST(companyId, status, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference[] findByC_NotST_PrevAndNext(long id, long companyId,
		int status, OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Reference[] array = new ReferenceImpl[3];

			array[0] = getByC_NotST_PrevAndNext(session, reference, companyId,
					status, orderByComparator, true);

			array[1] = reference;

			array[2] = getByC_NotST_PrevAndNext(session, reference, companyId,
					status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Reference getByC_NotST_PrevAndNext(Session session,
		Reference reference, long companyId, int status,
		OrderByComparator<Reference> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_REFERENCE_WHERE);

		query.append(_FINDER_COLUMN_C_NOTST_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_NOTST_STATUS_2);

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
			query.append(ReferenceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(reference);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Reference> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the references where companyId = &#63; and status &ne; &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 */
	@Override
	public void removeByC_NotST(long companyId, int status) {
		for (Reference reference : findByC_NotST(companyId, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(reference);
		}
	}

	/**
	 * Returns the number of references where companyId = &#63; and status &ne; &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @return the number of matching references
	 */
	@Override
	public int countByC_NotST(long companyId, int status) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_NOTST;

		Object[] finderArgs = new Object[] { companyId, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_C_NOTST_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_NOTST_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_C_NOTST_COMPANYID_2 = "reference.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_NOTST_STATUS_2 = "reference.status != ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_R_V = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByG_R_V",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Double.class.getName()
			},
			ReferenceModelImpl.GROUPID_COLUMN_BITMASK |
			ReferenceModelImpl.REFERENCEID_COLUMN_BITMASK |
			ReferenceModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_R_V = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_R_V",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Double.class.getName()
			});

	/**
	 * Returns the reference where groupId = &#63; and referenceId = &#63; and version = &#63; or throws a {@link NoSuchReferenceException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param version the version
	 * @return the matching reference
	 * @throws NoSuchReferenceException if a matching reference could not be found
	 */
	@Override
	public Reference findByG_R_V(long groupId, String referenceId,
		double version) throws NoSuchReferenceException {
		Reference reference = fetchByG_R_V(groupId, referenceId, version);

		if (reference == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", referenceId=");
			msg.append(referenceId);

			msg.append(", version=");
			msg.append(version);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchReferenceException(msg.toString());
		}

		return reference;
	}

	/**
	 * Returns the reference where groupId = &#63; and referenceId = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param version the version
	 * @return the matching reference, or <code>null</code> if a matching reference could not be found
	 */
	@Override
	public Reference fetchByG_R_V(long groupId, String referenceId,
		double version) {
		return fetchByG_R_V(groupId, referenceId, version, true);
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
	@Override
	public Reference fetchByG_R_V(long groupId, String referenceId,
		double version, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, referenceId, version };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_R_V,
					finderArgs, this);
		}

		if (result instanceof Reference) {
			Reference reference = (Reference)result;

			if ((groupId != reference.getGroupId()) ||
					!Objects.equals(referenceId, reference.getReferenceId()) ||
					(version != reference.getVersion())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_REFERENCE_WHERE);

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

				List<Reference> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_R_V,
						finderArgs, list);
				}
				else {
					Reference reference = list.get(0);

					result = reference;

					cacheResult(reference);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_R_V, finderArgs);

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
			return (Reference)result;
		}
	}

	/**
	 * Removes the reference where groupId = &#63; and referenceId = &#63; and version = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param version the version
	 * @return the reference that was removed
	 */
	@Override
	public Reference removeByG_R_V(long groupId, String referenceId,
		double version) throws NoSuchReferenceException {
		Reference reference = findByG_R_V(groupId, referenceId, version);

		return remove(reference);
	}

	/**
	 * Returns the number of references where groupId = &#63; and referenceId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param version the version
	 * @return the number of matching references
	 */
	@Override
	public int countByG_R_V(long groupId, String referenceId, double version) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_R_V;

		Object[] finderArgs = new Object[] { groupId, referenceId, version };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_REFERENCE_WHERE);

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

	private static final String _FINDER_COLUMN_G_R_V_GROUPID_2 = "reference.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_R_V_REFERENCEID_1 = "reference.referenceId IS NULL AND ";
	private static final String _FINDER_COLUMN_G_R_V_REFERENCEID_2 = "reference.referenceId = ? AND ";
	private static final String _FINDER_COLUMN_G_R_V_REFERENCEID_3 = "(reference.referenceId IS NULL OR reference.referenceId = '') AND ";
	private static final String _FINDER_COLUMN_G_R_V_VERSION_2 = "reference.version = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_R_ST = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_R_ST",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R_ST =
		new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_R_ST",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			},
			ReferenceModelImpl.GROUPID_COLUMN_BITMASK |
			ReferenceModelImpl.REFERENCEID_COLUMN_BITMASK |
			ReferenceModelImpl.STATUS_COLUMN_BITMASK |
			ReferenceModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_R_ST = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_R_ST",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_R_ST = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_R_ST",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the references where groupId = &#63; and referenceId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param status the status
	 * @return the matching references
	 */
	@Override
	public List<Reference> findByG_R_ST(long groupId, String referenceId,
		int status) {
		return findByG_R_ST(groupId, referenceId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> findByG_R_ST(long groupId, String referenceId,
		int status, int start, int end) {
		return findByG_R_ST(groupId, referenceId, status, start, end, null);
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
	@Override
	public List<Reference> findByG_R_ST(long groupId, String referenceId,
		int status, int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		return findByG_R_ST(groupId, referenceId, status, start, end,
			orderByComparator, true);
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
	@Override
	public List<Reference> findByG_R_ST(long groupId, String referenceId,
		int status, int start, int end,
		OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R_ST;
			finderArgs = new Object[] { groupId, referenceId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_R_ST;
			finderArgs = new Object[] {
					groupId, referenceId, status,
					
					start, end, orderByComparator
				};
		}

		List<Reference> list = null;

		if (retrieveFromCache) {
			list = (List<Reference>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Reference reference : list) {
					if ((groupId != reference.getGroupId()) ||
							!Objects.equals(referenceId,
								reference.getReferenceId()) ||
							(status != reference.getStatus())) {
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

			query.append(_SQL_SELECT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_G_R_ST_GROUPID_2);

			boolean bindReferenceId = false;

			if (referenceId == null) {
				query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_1);
			}
			else if (referenceId.equals("")) {
				query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_3);
			}
			else {
				bindReferenceId = true;

				query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_2);
			}

			query.append(_FINDER_COLUMN_G_R_ST_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
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

				qPos.add(status);

				if (!pagination) {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first reference in the ordered set where groupId = &#63; and referenceId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference
	 * @throws NoSuchReferenceException if a matching reference could not be found
	 */
	@Override
	public Reference findByG_R_ST_First(long groupId, String referenceId,
		int status, OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByG_R_ST_First(groupId, referenceId, status,
				orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", referenceId=");
		msg.append(referenceId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
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
	@Override
	public Reference fetchByG_R_ST_First(long groupId, String referenceId,
		int status, OrderByComparator<Reference> orderByComparator) {
		List<Reference> list = findByG_R_ST(groupId, referenceId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference findByG_R_ST_Last(long groupId, String referenceId,
		int status, OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByG_R_ST_Last(groupId, referenceId, status,
				orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", referenceId=");
		msg.append(referenceId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
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
	@Override
	public Reference fetchByG_R_ST_Last(long groupId, String referenceId,
		int status, OrderByComparator<Reference> orderByComparator) {
		int count = countByG_R_ST(groupId, referenceId, status);

		if (count == 0) {
			return null;
		}

		List<Reference> list = findByG_R_ST(groupId, referenceId, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference[] findByG_R_ST_PrevAndNext(long id, long groupId,
		String referenceId, int status,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Reference[] array = new ReferenceImpl[3];

			array[0] = getByG_R_ST_PrevAndNext(session, reference, groupId,
					referenceId, status, orderByComparator, true);

			array[1] = reference;

			array[2] = getByG_R_ST_PrevAndNext(session, reference, groupId,
					referenceId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Reference getByG_R_ST_PrevAndNext(Session session,
		Reference reference, long groupId, String referenceId, int status,
		OrderByComparator<Reference> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_REFERENCE_WHERE);

		query.append(_FINDER_COLUMN_G_R_ST_GROUPID_2);

		boolean bindReferenceId = false;

		if (referenceId == null) {
			query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_1);
		}
		else if (referenceId.equals("")) {
			query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_3);
		}
		else {
			bindReferenceId = true;

			query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_2);
		}

		query.append(_FINDER_COLUMN_G_R_ST_STATUS_2);

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
			query.append(ReferenceModelImpl.ORDER_BY_JPQL);
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

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(reference);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Reference> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the references that the user has permission to view where groupId = &#63; and referenceId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param status the status
	 * @return the matching references that the user has permission to view
	 */
	@Override
	public List<Reference> filterFindByG_R_ST(long groupId, String referenceId,
		int status) {
		return filterFindByG_R_ST(groupId, referenceId, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> filterFindByG_R_ST(long groupId, String referenceId,
		int status, int start, int end) {
		return filterFindByG_R_ST(groupId, referenceId, status, start, end, null);
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
	@Override
	public List<Reference> filterFindByG_R_ST(long groupId, String referenceId,
		int status, int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_R_ST(groupId, referenceId, status, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(6);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_R_ST_GROUPID_2);

		boolean bindReferenceId = false;

		if (referenceId == null) {
			query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_1);
		}
		else if (referenceId.equals("")) {
			query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_3);
		}
		else {
			bindReferenceId = true;

			query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_2);
		}

		query.append(_FINDER_COLUMN_G_R_ST_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ReferenceModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Reference.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ReferenceImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ReferenceImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindReferenceId) {
				qPos.add(referenceId);
			}

			qPos.add(status);

			return (List<Reference>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
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
	@Override
	public Reference[] filterFindByG_R_ST_PrevAndNext(long id, long groupId,
		String referenceId, int status,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_R_ST_PrevAndNext(id, groupId, referenceId, status,
				orderByComparator);
		}

		Reference reference = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Reference[] array = new ReferenceImpl[3];

			array[0] = filterGetByG_R_ST_PrevAndNext(session, reference,
					groupId, referenceId, status, orderByComparator, true);

			array[1] = reference;

			array[2] = filterGetByG_R_ST_PrevAndNext(session, reference,
					groupId, referenceId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Reference filterGetByG_R_ST_PrevAndNext(Session session,
		Reference reference, long groupId, String referenceId, int status,
		OrderByComparator<Reference> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_R_ST_GROUPID_2);

		boolean bindReferenceId = false;

		if (referenceId == null) {
			query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_1);
		}
		else if (referenceId.equals("")) {
			query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_3);
		}
		else {
			bindReferenceId = true;

			query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_2);
		}

		query.append(_FINDER_COLUMN_G_R_ST_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ReferenceModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Reference.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ReferenceImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ReferenceImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindReferenceId) {
			qPos.add(referenceId);
		}

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(reference);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Reference> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the references that the user has permission to view where groupId = &#63; and referenceId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param statuses the statuses
	 * @return the matching references that the user has permission to view
	 */
	@Override
	public List<Reference> filterFindByG_R_ST(long groupId, String referenceId,
		int[] statuses) {
		return filterFindByG_R_ST(groupId, referenceId, statuses,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> filterFindByG_R_ST(long groupId, String referenceId,
		int[] statuses, int start, int end) {
		return filterFindByG_R_ST(groupId, referenceId, statuses, start, end,
			null);
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
	@Override
	public List<Reference> filterFindByG_R_ST(long groupId, String referenceId,
		int[] statuses, int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_R_ST(groupId, referenceId, statuses, start, end,
				orderByComparator);
		}

		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.unique(statuses);

			Arrays.sort(statuses);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_R_ST_GROUPID_2);

		boolean bindReferenceId = false;

		if (referenceId == null) {
			query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_1);
		}
		else if (referenceId.equals("")) {
			query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_3);
		}
		else {
			bindReferenceId = true;

			query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_2);
		}

		if (statuses.length > 0) {
			query.append("(");

			query.append(_FINDER_COLUMN_G_R_ST_STATUS_7);

			query.append(StringUtil.merge(statuses));

			query.append(")");

			query.append(")");
		}

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ReferenceModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Reference.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ReferenceImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ReferenceImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindReferenceId) {
				qPos.add(referenceId);
			}

			return (List<Reference>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
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
	@Override
	public List<Reference> findByG_R_ST(long groupId, String referenceId,
		int[] statuses) {
		return findByG_R_ST(groupId, referenceId, statuses, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> findByG_R_ST(long groupId, String referenceId,
		int[] statuses, int start, int end) {
		return findByG_R_ST(groupId, referenceId, statuses, start, end, null);
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
	@Override
	public List<Reference> findByG_R_ST(long groupId, String referenceId,
		int[] statuses, int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		return findByG_R_ST(groupId, referenceId, statuses, start, end,
			orderByComparator, true);
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
	@Override
	public List<Reference> findByG_R_ST(long groupId, String referenceId,
		int[] statuses, int start, int end,
		OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.unique(statuses);

			Arrays.sort(statuses);
		}

		if (statuses.length == 1) {
			return findByG_R_ST(groupId, referenceId, statuses[0], start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					groupId, referenceId, StringUtil.merge(statuses)
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, referenceId, StringUtil.merge(statuses),
					
					start, end, orderByComparator
				};
		}

		List<Reference> list = null;

		if (retrieveFromCache) {
			list = (List<Reference>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_R_ST,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Reference reference : list) {
					if ((groupId != reference.getGroupId()) ||
							!Objects.equals(referenceId,
								reference.getReferenceId()) ||
							!ArrayUtil.contains(statuses, reference.getStatus())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_G_R_ST_GROUPID_2);

			boolean bindReferenceId = false;

			if (referenceId == null) {
				query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_1);
			}
			else if (referenceId.equals("")) {
				query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_3);
			}
			else {
				bindReferenceId = true;

				query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_2);
			}

			if (statuses.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_G_R_ST_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
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

				if (!pagination) {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_R_ST,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_R_ST,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the references where groupId = &#63; and referenceId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param status the status
	 */
	@Override
	public void removeByG_R_ST(long groupId, String referenceId, int status) {
		for (Reference reference : findByG_R_ST(groupId, referenceId, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(reference);
		}
	}

	/**
	 * Returns the number of references where groupId = &#63; and referenceId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param status the status
	 * @return the number of matching references
	 */
	@Override
	public int countByG_R_ST(long groupId, String referenceId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_R_ST;

		Object[] finderArgs = new Object[] { groupId, referenceId, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_G_R_ST_GROUPID_2);

			boolean bindReferenceId = false;

			if (referenceId == null) {
				query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_1);
			}
			else if (referenceId.equals("")) {
				query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_3);
			}
			else {
				bindReferenceId = true;

				query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_2);
			}

			query.append(_FINDER_COLUMN_G_R_ST_STATUS_2);

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

				qPos.add(status);

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

	/**
	 * Returns the number of references where groupId = &#63; and referenceId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param statuses the statuses
	 * @return the number of matching references
	 */
	@Override
	public int countByG_R_ST(long groupId, String referenceId, int[] statuses) {
		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.unique(statuses);

			Arrays.sort(statuses);
		}

		Object[] finderArgs = new Object[] {
				groupId, referenceId, StringUtil.merge(statuses)
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_R_ST,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_G_R_ST_GROUPID_2);

			boolean bindReferenceId = false;

			if (referenceId == null) {
				query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_1);
			}
			else if (referenceId.equals("")) {
				query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_3);
			}
			else {
				bindReferenceId = true;

				query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_2);
			}

			if (statuses.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_G_R_ST_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

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

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_R_ST,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_R_ST,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of references that the user has permission to view where groupId = &#63; and referenceId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param status the status
	 * @return the number of matching references that the user has permission to view
	 */
	@Override
	public int filterCountByG_R_ST(long groupId, String referenceId, int status) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_R_ST(groupId, referenceId, status);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_REFERENCE_WHERE);

		query.append(_FINDER_COLUMN_G_R_ST_GROUPID_2);

		boolean bindReferenceId = false;

		if (referenceId == null) {
			query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_1);
		}
		else if (referenceId.equals("")) {
			query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_3);
		}
		else {
			bindReferenceId = true;

			query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_2);
		}

		query.append(_FINDER_COLUMN_G_R_ST_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Reference.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindReferenceId) {
				qPos.add(referenceId);
			}

			qPos.add(status);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of references that the user has permission to view where groupId = &#63; and referenceId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param statuses the statuses
	 * @return the number of matching references that the user has permission to view
	 */
	@Override
	public int filterCountByG_R_ST(long groupId, String referenceId,
		int[] statuses) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_R_ST(groupId, referenceId, statuses);
		}

		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.unique(statuses);

			Arrays.sort(statuses);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_REFERENCE_WHERE);

		query.append(_FINDER_COLUMN_G_R_ST_GROUPID_2);

		boolean bindReferenceId = false;

		if (referenceId == null) {
			query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_1);
		}
		else if (referenceId.equals("")) {
			query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_3);
		}
		else {
			bindReferenceId = true;

			query.append(_FINDER_COLUMN_G_R_ST_REFERENCEID_2);
		}

		if (statuses.length > 0) {
			query.append("(");

			query.append(_FINDER_COLUMN_G_R_ST_STATUS_7);

			query.append(StringUtil.merge(statuses));

			query.append(")");

			query.append(")");
		}

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Reference.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindReferenceId) {
				qPos.add(referenceId);
			}

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_R_ST_GROUPID_2 = "reference.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_R_ST_REFERENCEID_1 = "reference.referenceId IS NULL AND ";
	private static final String _FINDER_COLUMN_G_R_ST_REFERENCEID_2 = "reference.referenceId = ? AND ";
	private static final String _FINDER_COLUMN_G_R_ST_REFERENCEID_3 = "(reference.referenceId IS NULL OR reference.referenceId = '') AND ";
	private static final String _FINDER_COLUMN_G_R_ST_STATUS_2 = "reference.status = ?";
	private static final String _FINDER_COLUMN_G_R_ST_STATUS_7 = "reference.status IN (";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_R_NOTST =
		new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_R_NotST",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_R_NOTST =
		new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_R_NotST",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the references where groupId = &#63; and referenceId = &#63; and status &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param status the status
	 * @return the matching references
	 */
	@Override
	public List<Reference> findByG_R_NotST(long groupId, String referenceId,
		int status) {
		return findByG_R_NotST(groupId, referenceId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> findByG_R_NotST(long groupId, String referenceId,
		int status, int start, int end) {
		return findByG_R_NotST(groupId, referenceId, status, start, end, null);
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
	@Override
	public List<Reference> findByG_R_NotST(long groupId, String referenceId,
		int status, int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		return findByG_R_NotST(groupId, referenceId, status, start, end,
			orderByComparator, true);
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
	@Override
	public List<Reference> findByG_R_NotST(long groupId, String referenceId,
		int status, int start, int end,
		OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_R_NOTST;
		finderArgs = new Object[] {
				groupId, referenceId, status,
				
				start, end, orderByComparator
			};

		List<Reference> list = null;

		if (retrieveFromCache) {
			list = (List<Reference>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Reference reference : list) {
					if ((groupId != reference.getGroupId()) ||
							!Objects.equals(referenceId,
								reference.getReferenceId()) ||
							(status == reference.getStatus())) {
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

			query.append(_SQL_SELECT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_G_R_NOTST_GROUPID_2);

			boolean bindReferenceId = false;

			if (referenceId == null) {
				query.append(_FINDER_COLUMN_G_R_NOTST_REFERENCEID_1);
			}
			else if (referenceId.equals("")) {
				query.append(_FINDER_COLUMN_G_R_NOTST_REFERENCEID_3);
			}
			else {
				bindReferenceId = true;

				query.append(_FINDER_COLUMN_G_R_NOTST_REFERENCEID_2);
			}

			query.append(_FINDER_COLUMN_G_R_NOTST_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
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

				qPos.add(status);

				if (!pagination) {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first reference in the ordered set where groupId = &#63; and referenceId = &#63; and status &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference
	 * @throws NoSuchReferenceException if a matching reference could not be found
	 */
	@Override
	public Reference findByG_R_NotST_First(long groupId, String referenceId,
		int status, OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByG_R_NotST_First(groupId, referenceId,
				status, orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", referenceId=");
		msg.append(referenceId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
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
	@Override
	public Reference fetchByG_R_NotST_First(long groupId, String referenceId,
		int status, OrderByComparator<Reference> orderByComparator) {
		List<Reference> list = findByG_R_NotST(groupId, referenceId, status, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference findByG_R_NotST_Last(long groupId, String referenceId,
		int status, OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByG_R_NotST_Last(groupId, referenceId,
				status, orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", referenceId=");
		msg.append(referenceId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
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
	@Override
	public Reference fetchByG_R_NotST_Last(long groupId, String referenceId,
		int status, OrderByComparator<Reference> orderByComparator) {
		int count = countByG_R_NotST(groupId, referenceId, status);

		if (count == 0) {
			return null;
		}

		List<Reference> list = findByG_R_NotST(groupId, referenceId, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference[] findByG_R_NotST_PrevAndNext(long id, long groupId,
		String referenceId, int status,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Reference[] array = new ReferenceImpl[3];

			array[0] = getByG_R_NotST_PrevAndNext(session, reference, groupId,
					referenceId, status, orderByComparator, true);

			array[1] = reference;

			array[2] = getByG_R_NotST_PrevAndNext(session, reference, groupId,
					referenceId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Reference getByG_R_NotST_PrevAndNext(Session session,
		Reference reference, long groupId, String referenceId, int status,
		OrderByComparator<Reference> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_REFERENCE_WHERE);

		query.append(_FINDER_COLUMN_G_R_NOTST_GROUPID_2);

		boolean bindReferenceId = false;

		if (referenceId == null) {
			query.append(_FINDER_COLUMN_G_R_NOTST_REFERENCEID_1);
		}
		else if (referenceId.equals("")) {
			query.append(_FINDER_COLUMN_G_R_NOTST_REFERENCEID_3);
		}
		else {
			bindReferenceId = true;

			query.append(_FINDER_COLUMN_G_R_NOTST_REFERENCEID_2);
		}

		query.append(_FINDER_COLUMN_G_R_NOTST_STATUS_2);

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
			query.append(ReferenceModelImpl.ORDER_BY_JPQL);
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

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(reference);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Reference> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the references that the user has permission to view where groupId = &#63; and referenceId = &#63; and status &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param status the status
	 * @return the matching references that the user has permission to view
	 */
	@Override
	public List<Reference> filterFindByG_R_NotST(long groupId,
		String referenceId, int status) {
		return filterFindByG_R_NotST(groupId, referenceId, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> filterFindByG_R_NotST(long groupId,
		String referenceId, int status, int start, int end) {
		return filterFindByG_R_NotST(groupId, referenceId, status, start, end,
			null);
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
	@Override
	public List<Reference> filterFindByG_R_NotST(long groupId,
		String referenceId, int status, int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_R_NotST(groupId, referenceId, status, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(6);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_R_NOTST_GROUPID_2);

		boolean bindReferenceId = false;

		if (referenceId == null) {
			query.append(_FINDER_COLUMN_G_R_NOTST_REFERENCEID_1);
		}
		else if (referenceId.equals("")) {
			query.append(_FINDER_COLUMN_G_R_NOTST_REFERENCEID_3);
		}
		else {
			bindReferenceId = true;

			query.append(_FINDER_COLUMN_G_R_NOTST_REFERENCEID_2);
		}

		query.append(_FINDER_COLUMN_G_R_NOTST_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ReferenceModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Reference.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ReferenceImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ReferenceImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindReferenceId) {
				qPos.add(referenceId);
			}

			qPos.add(status);

			return (List<Reference>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
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
	@Override
	public Reference[] filterFindByG_R_NotST_PrevAndNext(long id, long groupId,
		String referenceId, int status,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_R_NotST_PrevAndNext(id, groupId, referenceId,
				status, orderByComparator);
		}

		Reference reference = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Reference[] array = new ReferenceImpl[3];

			array[0] = filterGetByG_R_NotST_PrevAndNext(session, reference,
					groupId, referenceId, status, orderByComparator, true);

			array[1] = reference;

			array[2] = filterGetByG_R_NotST_PrevAndNext(session, reference,
					groupId, referenceId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Reference filterGetByG_R_NotST_PrevAndNext(Session session,
		Reference reference, long groupId, String referenceId, int status,
		OrderByComparator<Reference> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_R_NOTST_GROUPID_2);

		boolean bindReferenceId = false;

		if (referenceId == null) {
			query.append(_FINDER_COLUMN_G_R_NOTST_REFERENCEID_1);
		}
		else if (referenceId.equals("")) {
			query.append(_FINDER_COLUMN_G_R_NOTST_REFERENCEID_3);
		}
		else {
			bindReferenceId = true;

			query.append(_FINDER_COLUMN_G_R_NOTST_REFERENCEID_2);
		}

		query.append(_FINDER_COLUMN_G_R_NOTST_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ReferenceModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Reference.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ReferenceImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ReferenceImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindReferenceId) {
			qPos.add(referenceId);
		}

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(reference);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Reference> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the references where groupId = &#63; and referenceId = &#63; and status &ne; &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param status the status
	 */
	@Override
	public void removeByG_R_NotST(long groupId, String referenceId, int status) {
		for (Reference reference : findByG_R_NotST(groupId, referenceId,
				status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(reference);
		}
	}

	/**
	 * Returns the number of references where groupId = &#63; and referenceId = &#63; and status &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param status the status
	 * @return the number of matching references
	 */
	@Override
	public int countByG_R_NotST(long groupId, String referenceId, int status) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_R_NOTST;

		Object[] finderArgs = new Object[] { groupId, referenceId, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_G_R_NOTST_GROUPID_2);

			boolean bindReferenceId = false;

			if (referenceId == null) {
				query.append(_FINDER_COLUMN_G_R_NOTST_REFERENCEID_1);
			}
			else if (referenceId.equals("")) {
				query.append(_FINDER_COLUMN_G_R_NOTST_REFERENCEID_3);
			}
			else {
				bindReferenceId = true;

				query.append(_FINDER_COLUMN_G_R_NOTST_REFERENCEID_2);
			}

			query.append(_FINDER_COLUMN_G_R_NOTST_STATUS_2);

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

				qPos.add(status);

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

	/**
	 * Returns the number of references that the user has permission to view where groupId = &#63; and referenceId = &#63; and status &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceId the reference ID
	 * @param status the status
	 * @return the number of matching references that the user has permission to view
	 */
	@Override
	public int filterCountByG_R_NotST(long groupId, String referenceId,
		int status) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_R_NotST(groupId, referenceId, status);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_REFERENCE_WHERE);

		query.append(_FINDER_COLUMN_G_R_NOTST_GROUPID_2);

		boolean bindReferenceId = false;

		if (referenceId == null) {
			query.append(_FINDER_COLUMN_G_R_NOTST_REFERENCEID_1);
		}
		else if (referenceId.equals("")) {
			query.append(_FINDER_COLUMN_G_R_NOTST_REFERENCEID_3);
		}
		else {
			bindReferenceId = true;

			query.append(_FINDER_COLUMN_G_R_NOTST_REFERENCEID_2);
		}

		query.append(_FINDER_COLUMN_G_R_NOTST_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Reference.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindReferenceId) {
				qPos.add(referenceId);
			}

			qPos.add(status);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_R_NOTST_GROUPID_2 = "reference.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_R_NOTST_REFERENCEID_1 = "reference.referenceId IS NULL AND ";
	private static final String _FINDER_COLUMN_G_R_NOTST_REFERENCEID_2 = "reference.referenceId = ? AND ";
	private static final String _FINDER_COLUMN_G_R_NOTST_REFERENCEID_3 = "(reference.referenceId IS NULL OR reference.referenceId = '') AND ";
	private static final String _FINDER_COLUMN_G_R_NOTST_STATUS_2 = "reference.status != ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_UT_ST = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_UT_ST",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UT_ST =
		new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_UT_ST",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			},
			ReferenceModelImpl.GROUPID_COLUMN_BITMASK |
			ReferenceModelImpl.URLTITLE_COLUMN_BITMASK |
			ReferenceModelImpl.STATUS_COLUMN_BITMASK |
			ReferenceModelImpl.REFERENCEID_COLUMN_BITMASK |
			ReferenceModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_UT_ST = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_UT_ST",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the references where groupId = &#63; and urlTitle = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param urlTitle the url title
	 * @param status the status
	 * @return the matching references
	 */
	@Override
	public List<Reference> findByG_UT_ST(long groupId, String urlTitle,
		int status) {
		return findByG_UT_ST(groupId, urlTitle, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> findByG_UT_ST(long groupId, String urlTitle,
		int status, int start, int end) {
		return findByG_UT_ST(groupId, urlTitle, status, start, end, null);
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
	@Override
	public List<Reference> findByG_UT_ST(long groupId, String urlTitle,
		int status, int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		return findByG_UT_ST(groupId, urlTitle, status, start, end,
			orderByComparator, true);
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
	@Override
	public List<Reference> findByG_UT_ST(long groupId, String urlTitle,
		int status, int start, int end,
		OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UT_ST;
			finderArgs = new Object[] { groupId, urlTitle, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_UT_ST;
			finderArgs = new Object[] {
					groupId, urlTitle, status,
					
					start, end, orderByComparator
				};
		}

		List<Reference> list = null;

		if (retrieveFromCache) {
			list = (List<Reference>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Reference reference : list) {
					if ((groupId != reference.getGroupId()) ||
							!Objects.equals(urlTitle, reference.getUrlTitle()) ||
							(status != reference.getStatus())) {
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

			query.append(_SQL_SELECT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_G_UT_ST_GROUPID_2);

			boolean bindUrlTitle = false;

			if (urlTitle == null) {
				query.append(_FINDER_COLUMN_G_UT_ST_URLTITLE_1);
			}
			else if (urlTitle.equals("")) {
				query.append(_FINDER_COLUMN_G_UT_ST_URLTITLE_3);
			}
			else {
				bindUrlTitle = true;

				query.append(_FINDER_COLUMN_G_UT_ST_URLTITLE_2);
			}

			query.append(_FINDER_COLUMN_G_UT_ST_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindUrlTitle) {
					qPos.add(urlTitle);
				}

				qPos.add(status);

				if (!pagination) {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first reference in the ordered set where groupId = &#63; and urlTitle = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param urlTitle the url title
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference
	 * @throws NoSuchReferenceException if a matching reference could not be found
	 */
	@Override
	public Reference findByG_UT_ST_First(long groupId, String urlTitle,
		int status, OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByG_UT_ST_First(groupId, urlTitle, status,
				orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", urlTitle=");
		msg.append(urlTitle);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
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
	@Override
	public Reference fetchByG_UT_ST_First(long groupId, String urlTitle,
		int status, OrderByComparator<Reference> orderByComparator) {
		List<Reference> list = findByG_UT_ST(groupId, urlTitle, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference findByG_UT_ST_Last(long groupId, String urlTitle,
		int status, OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByG_UT_ST_Last(groupId, urlTitle, status,
				orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", urlTitle=");
		msg.append(urlTitle);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
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
	@Override
	public Reference fetchByG_UT_ST_Last(long groupId, String urlTitle,
		int status, OrderByComparator<Reference> orderByComparator) {
		int count = countByG_UT_ST(groupId, urlTitle, status);

		if (count == 0) {
			return null;
		}

		List<Reference> list = findByG_UT_ST(groupId, urlTitle, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference[] findByG_UT_ST_PrevAndNext(long id, long groupId,
		String urlTitle, int status,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Reference[] array = new ReferenceImpl[3];

			array[0] = getByG_UT_ST_PrevAndNext(session, reference, groupId,
					urlTitle, status, orderByComparator, true);

			array[1] = reference;

			array[2] = getByG_UT_ST_PrevAndNext(session, reference, groupId,
					urlTitle, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Reference getByG_UT_ST_PrevAndNext(Session session,
		Reference reference, long groupId, String urlTitle, int status,
		OrderByComparator<Reference> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_REFERENCE_WHERE);

		query.append(_FINDER_COLUMN_G_UT_ST_GROUPID_2);

		boolean bindUrlTitle = false;

		if (urlTitle == null) {
			query.append(_FINDER_COLUMN_G_UT_ST_URLTITLE_1);
		}
		else if (urlTitle.equals("")) {
			query.append(_FINDER_COLUMN_G_UT_ST_URLTITLE_3);
		}
		else {
			bindUrlTitle = true;

			query.append(_FINDER_COLUMN_G_UT_ST_URLTITLE_2);
		}

		query.append(_FINDER_COLUMN_G_UT_ST_STATUS_2);

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
			query.append(ReferenceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindUrlTitle) {
			qPos.add(urlTitle);
		}

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(reference);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Reference> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the references that the user has permission to view where groupId = &#63; and urlTitle = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param urlTitle the url title
	 * @param status the status
	 * @return the matching references that the user has permission to view
	 */
	@Override
	public List<Reference> filterFindByG_UT_ST(long groupId, String urlTitle,
		int status) {
		return filterFindByG_UT_ST(groupId, urlTitle, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> filterFindByG_UT_ST(long groupId, String urlTitle,
		int status, int start, int end) {
		return filterFindByG_UT_ST(groupId, urlTitle, status, start, end, null);
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
	@Override
	public List<Reference> filterFindByG_UT_ST(long groupId, String urlTitle,
		int status, int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_UT_ST(groupId, urlTitle, status, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(6);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_UT_ST_GROUPID_2);

		boolean bindUrlTitle = false;

		if (urlTitle == null) {
			query.append(_FINDER_COLUMN_G_UT_ST_URLTITLE_1);
		}
		else if (urlTitle.equals("")) {
			query.append(_FINDER_COLUMN_G_UT_ST_URLTITLE_3);
		}
		else {
			bindUrlTitle = true;

			query.append(_FINDER_COLUMN_G_UT_ST_URLTITLE_2);
		}

		query.append(_FINDER_COLUMN_G_UT_ST_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ReferenceModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Reference.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ReferenceImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ReferenceImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindUrlTitle) {
				qPos.add(urlTitle);
			}

			qPos.add(status);

			return (List<Reference>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
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
	@Override
	public Reference[] filterFindByG_UT_ST_PrevAndNext(long id, long groupId,
		String urlTitle, int status,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_UT_ST_PrevAndNext(id, groupId, urlTitle, status,
				orderByComparator);
		}

		Reference reference = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Reference[] array = new ReferenceImpl[3];

			array[0] = filterGetByG_UT_ST_PrevAndNext(session, reference,
					groupId, urlTitle, status, orderByComparator, true);

			array[1] = reference;

			array[2] = filterGetByG_UT_ST_PrevAndNext(session, reference,
					groupId, urlTitle, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Reference filterGetByG_UT_ST_PrevAndNext(Session session,
		Reference reference, long groupId, String urlTitle, int status,
		OrderByComparator<Reference> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_UT_ST_GROUPID_2);

		boolean bindUrlTitle = false;

		if (urlTitle == null) {
			query.append(_FINDER_COLUMN_G_UT_ST_URLTITLE_1);
		}
		else if (urlTitle.equals("")) {
			query.append(_FINDER_COLUMN_G_UT_ST_URLTITLE_3);
		}
		else {
			bindUrlTitle = true;

			query.append(_FINDER_COLUMN_G_UT_ST_URLTITLE_2);
		}

		query.append(_FINDER_COLUMN_G_UT_ST_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ReferenceModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Reference.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ReferenceImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ReferenceImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindUrlTitle) {
			qPos.add(urlTitle);
		}

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(reference);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Reference> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the references where groupId = &#63; and urlTitle = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param urlTitle the url title
	 * @param status the status
	 */
	@Override
	public void removeByG_UT_ST(long groupId, String urlTitle, int status) {
		for (Reference reference : findByG_UT_ST(groupId, urlTitle, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(reference);
		}
	}

	/**
	 * Returns the number of references where groupId = &#63; and urlTitle = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param urlTitle the url title
	 * @param status the status
	 * @return the number of matching references
	 */
	@Override
	public int countByG_UT_ST(long groupId, String urlTitle, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_UT_ST;

		Object[] finderArgs = new Object[] { groupId, urlTitle, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_G_UT_ST_GROUPID_2);

			boolean bindUrlTitle = false;

			if (urlTitle == null) {
				query.append(_FINDER_COLUMN_G_UT_ST_URLTITLE_1);
			}
			else if (urlTitle.equals("")) {
				query.append(_FINDER_COLUMN_G_UT_ST_URLTITLE_3);
			}
			else {
				bindUrlTitle = true;

				query.append(_FINDER_COLUMN_G_UT_ST_URLTITLE_2);
			}

			query.append(_FINDER_COLUMN_G_UT_ST_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindUrlTitle) {
					qPos.add(urlTitle);
				}

				qPos.add(status);

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

	/**
	 * Returns the number of references that the user has permission to view where groupId = &#63; and urlTitle = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param urlTitle the url title
	 * @param status the status
	 * @return the number of matching references that the user has permission to view
	 */
	@Override
	public int filterCountByG_UT_ST(long groupId, String urlTitle, int status) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_UT_ST(groupId, urlTitle, status);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_REFERENCE_WHERE);

		query.append(_FINDER_COLUMN_G_UT_ST_GROUPID_2);

		boolean bindUrlTitle = false;

		if (urlTitle == null) {
			query.append(_FINDER_COLUMN_G_UT_ST_URLTITLE_1);
		}
		else if (urlTitle.equals("")) {
			query.append(_FINDER_COLUMN_G_UT_ST_URLTITLE_3);
		}
		else {
			bindUrlTitle = true;

			query.append(_FINDER_COLUMN_G_UT_ST_URLTITLE_2);
		}

		query.append(_FINDER_COLUMN_G_UT_ST_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Reference.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindUrlTitle) {
				qPos.add(urlTitle);
			}

			qPos.add(status);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_UT_ST_GROUPID_2 = "reference.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_UT_ST_URLTITLE_1 = "reference.urlTitle IS NULL AND ";
	private static final String _FINDER_COLUMN_G_UT_ST_URLTITLE_2 = "reference.urlTitle = ? AND ";
	private static final String _FINDER_COLUMN_G_UT_ST_URLTITLE_3 = "(reference.urlTitle IS NULL OR reference.urlTitle = '') AND ";
	private static final String _FINDER_COLUMN_G_UT_ST_STATUS_2 = "reference.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_V_ST = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_V_ST",
			new String[] {
				Long.class.getName(), Double.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_V_ST =
		new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, ReferenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_V_ST",
			new String[] {
				Long.class.getName(), Double.class.getName(),
				Integer.class.getName()
			},
			ReferenceModelImpl.COMPANYID_COLUMN_BITMASK |
			ReferenceModelImpl.VERSION_COLUMN_BITMASK |
			ReferenceModelImpl.STATUS_COLUMN_BITMASK |
			ReferenceModelImpl.REFERENCEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_V_ST = new FinderPath(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_V_ST",
			new String[] {
				Long.class.getName(), Double.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the references where companyId = &#63; and version = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param version the version
	 * @param status the status
	 * @return the matching references
	 */
	@Override
	public List<Reference> findByC_V_ST(long companyId, double version,
		int status) {
		return findByC_V_ST(companyId, version, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> findByC_V_ST(long companyId, double version,
		int status, int start, int end) {
		return findByC_V_ST(companyId, version, status, start, end, null);
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
	@Override
	public List<Reference> findByC_V_ST(long companyId, double version,
		int status, int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		return findByC_V_ST(companyId, version, status, start, end,
			orderByComparator, true);
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
	@Override
	public List<Reference> findByC_V_ST(long companyId, double version,
		int status, int start, int end,
		OrderByComparator<Reference> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_V_ST;
			finderArgs = new Object[] { companyId, version, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_V_ST;
			finderArgs = new Object[] {
					companyId, version, status,
					
					start, end, orderByComparator
				};
		}

		List<Reference> list = null;

		if (retrieveFromCache) {
			list = (List<Reference>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Reference reference : list) {
					if ((companyId != reference.getCompanyId()) ||
							(version != reference.getVersion()) ||
							(status != reference.getStatus())) {
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

			query.append(_SQL_SELECT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_C_V_ST_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_V_ST_VERSION_2);

			query.append(_FINDER_COLUMN_C_V_ST_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReferenceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(version);

				qPos.add(status);

				if (!pagination) {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first reference in the ordered set where companyId = &#63; and version = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param version the version
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching reference
	 * @throws NoSuchReferenceException if a matching reference could not be found
	 */
	@Override
	public Reference findByC_V_ST_First(long companyId, double version,
		int status, OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByC_V_ST_First(companyId, version, status,
				orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", version=");
		msg.append(version);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
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
	@Override
	public Reference fetchByC_V_ST_First(long companyId, double version,
		int status, OrderByComparator<Reference> orderByComparator) {
		List<Reference> list = findByC_V_ST(companyId, version, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference findByC_V_ST_Last(long companyId, double version,
		int status, OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = fetchByC_V_ST_Last(companyId, version, status,
				orderByComparator);

		if (reference != null) {
			return reference;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", version=");
		msg.append(version);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchReferenceException(msg.toString());
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
	@Override
	public Reference fetchByC_V_ST_Last(long companyId, double version,
		int status, OrderByComparator<Reference> orderByComparator) {
		int count = countByC_V_ST(companyId, version, status);

		if (count == 0) {
			return null;
		}

		List<Reference> list = findByC_V_ST(companyId, version, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Reference[] findByC_V_ST_PrevAndNext(long id, long companyId,
		double version, int status,
		OrderByComparator<Reference> orderByComparator)
		throws NoSuchReferenceException {
		Reference reference = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Reference[] array = new ReferenceImpl[3];

			array[0] = getByC_V_ST_PrevAndNext(session, reference, companyId,
					version, status, orderByComparator, true);

			array[1] = reference;

			array[2] = getByC_V_ST_PrevAndNext(session, reference, companyId,
					version, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Reference getByC_V_ST_PrevAndNext(Session session,
		Reference reference, long companyId, double version, int status,
		OrderByComparator<Reference> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_REFERENCE_WHERE);

		query.append(_FINDER_COLUMN_C_V_ST_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_V_ST_VERSION_2);

		query.append(_FINDER_COLUMN_C_V_ST_STATUS_2);

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
			query.append(ReferenceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(version);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(reference);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Reference> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the references where companyId = &#63; and version = &#63; and status = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param version the version
	 * @param status the status
	 */
	@Override
	public void removeByC_V_ST(long companyId, double version, int status) {
		for (Reference reference : findByC_V_ST(companyId, version, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(reference);
		}
	}

	/**
	 * Returns the number of references where companyId = &#63; and version = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param version the version
	 * @param status the status
	 * @return the number of matching references
	 */
	@Override
	public int countByC_V_ST(long companyId, double version, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_V_ST;

		Object[] finderArgs = new Object[] { companyId, version, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_REFERENCE_WHERE);

			query.append(_FINDER_COLUMN_C_V_ST_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_V_ST_VERSION_2);

			query.append(_FINDER_COLUMN_C_V_ST_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(version);

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_C_V_ST_COMPANYID_2 = "reference.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_V_ST_VERSION_2 = "reference.version = ? AND ";
	private static final String _FINDER_COLUMN_C_V_ST_STATUS_2 = "reference.status = ?";

	public ReferencePersistenceImpl() {
		setModelClass(Reference.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");
			dbColumnNames.put("id", "id_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the reference in the entity cache if it is enabled.
	 *
	 * @param reference the reference
	 */
	@Override
	public void cacheResult(Reference reference) {
		entityCache.putResult(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceImpl.class, reference.getPrimaryKey(), reference);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { reference.getUuid(), reference.getGroupId() },
			reference);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_R_V,
			new Object[] {
				reference.getGroupId(), reference.getReferenceId(),
				reference.getVersion()
			}, reference);

		reference.resetOriginalValues();
	}

	/**
	 * Caches the references in the entity cache if it is enabled.
	 *
	 * @param references the references
	 */
	@Override
	public void cacheResult(List<Reference> references) {
		for (Reference reference : references) {
			if (entityCache.getResult(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
						ReferenceImpl.class, reference.getPrimaryKey()) == null) {
				cacheResult(reference);
			}
			else {
				reference.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all references.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ReferenceImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the reference.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Reference reference) {
		entityCache.removeResult(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceImpl.class, reference.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ReferenceModelImpl)reference, true);
	}

	@Override
	public void clearCache(List<Reference> references) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Reference reference : references) {
			entityCache.removeResult(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
				ReferenceImpl.class, reference.getPrimaryKey());

			clearUniqueFindersCache((ReferenceModelImpl)reference, true);
		}
	}

	protected void cacheUniqueFindersCache(
		ReferenceModelImpl referenceModelImpl) {
		Object[] args = new Object[] {
				referenceModelImpl.getUuid(), referenceModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			referenceModelImpl, false);

		args = new Object[] {
				referenceModelImpl.getGroupId(),
				referenceModelImpl.getReferenceId(),
				referenceModelImpl.getVersion()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_R_V, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_R_V, args,
			referenceModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ReferenceModelImpl referenceModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					referenceModelImpl.getUuid(),
					referenceModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((referenceModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					referenceModelImpl.getOriginalUuid(),
					referenceModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					referenceModelImpl.getGroupId(),
					referenceModelImpl.getReferenceId(),
					referenceModelImpl.getVersion()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_R_V, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_R_V, args);
		}

		if ((referenceModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_R_V.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					referenceModelImpl.getOriginalGroupId(),
					referenceModelImpl.getOriginalReferenceId(),
					referenceModelImpl.getOriginalVersion()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_R_V, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_R_V, args);
		}
	}

	/**
	 * Creates a new reference with the primary key. Does not add the reference to the database.
	 *
	 * @param id the primary key for the new reference
	 * @return the new reference
	 */
	@Override
	public Reference create(long id) {
		Reference reference = new ReferenceImpl();

		reference.setNew(true);
		reference.setPrimaryKey(id);

		String uuid = PortalUUIDUtil.generate();

		reference.setUuid(uuid);

		reference.setCompanyId(companyProvider.getCompanyId());

		return reference;
	}

	/**
	 * Removes the reference with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the reference
	 * @return the reference that was removed
	 * @throws NoSuchReferenceException if a reference with the primary key could not be found
	 */
	@Override
	public Reference remove(long id) throws NoSuchReferenceException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the reference with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the reference
	 * @return the reference that was removed
	 * @throws NoSuchReferenceException if a reference with the primary key could not be found
	 */
	@Override
	public Reference remove(Serializable primaryKey)
		throws NoSuchReferenceException {
		Session session = null;

		try {
			session = openSession();

			Reference reference = (Reference)session.get(ReferenceImpl.class,
					primaryKey);

			if (reference == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchReferenceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(reference);
		}
		catch (NoSuchReferenceException nsee) {
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
	protected Reference removeImpl(Reference reference) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(reference)) {
				reference = (Reference)session.get(ReferenceImpl.class,
						reference.getPrimaryKeyObj());
			}

			if (reference != null) {
				session.delete(reference);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (reference != null) {
			clearCache(reference);
		}

		return reference;
	}

	@Override
	public Reference updateImpl(Reference reference) {
		boolean isNew = reference.isNew();

		if (!(reference instanceof ReferenceModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(reference.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(reference);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in reference proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Reference implementation " +
				reference.getClass());
		}

		ReferenceModelImpl referenceModelImpl = (ReferenceModelImpl)reference;

		if (Validator.isNull(reference.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			reference.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (reference.getCreateDate() == null)) {
			if (serviceContext == null) {
				reference.setCreateDate(now);
			}
			else {
				reference.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!referenceModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				reference.setModifiedDate(now);
			}
			else {
				reference.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (reference.isNew()) {
				session.save(reference);

				reference.setNew(false);
			}
			else {
				reference = (Reference)session.merge(reference);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ReferenceModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { referenceModelImpl.getResourcePrimKey() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_RESOURCEPRIMKEY, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEPRIMKEY,
				args);

			args = new Object[] { referenceModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					referenceModelImpl.getUuid(),
					referenceModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { referenceModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			args = new Object[] { referenceModelImpl.getCompanyId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
				args);

			args = new Object[] {
					referenceModelImpl.getResourcePrimKey(),
					referenceModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_R_S, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S,
				args);

			args = new Object[] {
					referenceModelImpl.getGroupId(),
					referenceModelImpl.getUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_U, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U,
				args);

			args = new Object[] {
					referenceModelImpl.getGroupId(),
					referenceModelImpl.getReferenceId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_R, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R,
				args);

			args = new Object[] {
					referenceModelImpl.getGroupId(),
					referenceModelImpl.getUrlTitle()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UT, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UT,
				args);

			args = new Object[] {
					referenceModelImpl.getGroupId(),
					referenceModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_ST, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_ST,
				args);

			args = new Object[] {
					referenceModelImpl.getCompanyId(),
					referenceModelImpl.getVersion()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_V, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_V,
				args);

			args = new Object[] {
					referenceModelImpl.getCompanyId(),
					referenceModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_ST, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_ST,
				args);

			args = new Object[] {
					referenceModelImpl.getGroupId(),
					referenceModelImpl.getReferenceId(),
					referenceModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_R_ST, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R_ST,
				args);

			args = new Object[] {
					referenceModelImpl.getGroupId(),
					referenceModelImpl.getUrlTitle(),
					referenceModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UT_ST, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UT_ST,
				args);

			args = new Object[] {
					referenceModelImpl.getCompanyId(),
					referenceModelImpl.getVersion(),
					referenceModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_V_ST, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_V_ST,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((referenceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEPRIMKEY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						referenceModelImpl.getOriginalResourcePrimKey()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RESOURCEPRIMKEY,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEPRIMKEY,
					args);

				args = new Object[] { referenceModelImpl.getResourcePrimKey() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RESOURCEPRIMKEY,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEPRIMKEY,
					args);
			}

			if ((referenceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						referenceModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { referenceModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((referenceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						referenceModelImpl.getOriginalUuid(),
						referenceModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						referenceModelImpl.getUuid(),
						referenceModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((referenceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						referenceModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { referenceModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((referenceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						referenceModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { referenceModelImpl.getCompanyId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((referenceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						referenceModelImpl.getOriginalResourcePrimKey(),
						referenceModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_R_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S,
					args);

				args = new Object[] {
						referenceModelImpl.getResourcePrimKey(),
						referenceModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_R_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S,
					args);
			}

			if ((referenceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						referenceModelImpl.getOriginalGroupId(),
						referenceModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_U, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U,
					args);

				args = new Object[] {
						referenceModelImpl.getGroupId(),
						referenceModelImpl.getUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_U, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U,
					args);
			}

			if ((referenceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						referenceModelImpl.getOriginalGroupId(),
						referenceModelImpl.getOriginalReferenceId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_R, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R,
					args);

				args = new Object[] {
						referenceModelImpl.getGroupId(),
						referenceModelImpl.getReferenceId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_R, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R,
					args);
			}

			if ((referenceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						referenceModelImpl.getOriginalGroupId(),
						referenceModelImpl.getOriginalUrlTitle()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UT, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UT,
					args);

				args = new Object[] {
						referenceModelImpl.getGroupId(),
						referenceModelImpl.getUrlTitle()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UT, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UT,
					args);
			}

			if ((referenceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_ST.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						referenceModelImpl.getOriginalGroupId(),
						referenceModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_ST, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_ST,
					args);

				args = new Object[] {
						referenceModelImpl.getGroupId(),
						referenceModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_ST, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_ST,
					args);
			}

			if ((referenceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_V.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						referenceModelImpl.getOriginalCompanyId(),
						referenceModelImpl.getOriginalVersion()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_V, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_V,
					args);

				args = new Object[] {
						referenceModelImpl.getCompanyId(),
						referenceModelImpl.getVersion()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_V, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_V,
					args);
			}

			if ((referenceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_ST.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						referenceModelImpl.getOriginalCompanyId(),
						referenceModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_ST, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_ST,
					args);

				args = new Object[] {
						referenceModelImpl.getCompanyId(),
						referenceModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_ST, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_ST,
					args);
			}

			if ((referenceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R_ST.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						referenceModelImpl.getOriginalGroupId(),
						referenceModelImpl.getOriginalReferenceId(),
						referenceModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_R_ST, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R_ST,
					args);

				args = new Object[] {
						referenceModelImpl.getGroupId(),
						referenceModelImpl.getReferenceId(),
						referenceModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_R_ST, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R_ST,
					args);
			}

			if ((referenceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UT_ST.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						referenceModelImpl.getOriginalGroupId(),
						referenceModelImpl.getOriginalUrlTitle(),
						referenceModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UT_ST, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UT_ST,
					args);

				args = new Object[] {
						referenceModelImpl.getGroupId(),
						referenceModelImpl.getUrlTitle(),
						referenceModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UT_ST, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UT_ST,
					args);
			}

			if ((referenceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_V_ST.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						referenceModelImpl.getOriginalCompanyId(),
						referenceModelImpl.getOriginalVersion(),
						referenceModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_V_ST, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_V_ST,
					args);

				args = new Object[] {
						referenceModelImpl.getCompanyId(),
						referenceModelImpl.getVersion(),
						referenceModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_V_ST, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_V_ST,
					args);
			}
		}

		entityCache.putResult(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
			ReferenceImpl.class, reference.getPrimaryKey(), reference, false);

		clearUniqueFindersCache(referenceModelImpl, false);
		cacheUniqueFindersCache(referenceModelImpl);

		reference.resetOriginalValues();

		return reference;
	}

	/**
	 * Returns the reference with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the reference
	 * @return the reference
	 * @throws NoSuchReferenceException if a reference with the primary key could not be found
	 */
	@Override
	public Reference findByPrimaryKey(Serializable primaryKey)
		throws NoSuchReferenceException {
		Reference reference = fetchByPrimaryKey(primaryKey);

		if (reference == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchReferenceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return reference;
	}

	/**
	 * Returns the reference with the primary key or throws a {@link NoSuchReferenceException} if it could not be found.
	 *
	 * @param id the primary key of the reference
	 * @return the reference
	 * @throws NoSuchReferenceException if a reference with the primary key could not be found
	 */
	@Override
	public Reference findByPrimaryKey(long id) throws NoSuchReferenceException {
		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the reference with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the reference
	 * @return the reference, or <code>null</code> if a reference with the primary key could not be found
	 */
	@Override
	public Reference fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
				ReferenceImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Reference reference = (Reference)serializable;

		if (reference == null) {
			Session session = null;

			try {
				session = openSession();

				reference = (Reference)session.get(ReferenceImpl.class,
						primaryKey);

				if (reference != null) {
					cacheResult(reference);
				}
				else {
					entityCache.putResult(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
						ReferenceImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
					ReferenceImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return reference;
	}

	/**
	 * Returns the reference with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the reference
	 * @return the reference, or <code>null</code> if a reference with the primary key could not be found
	 */
	@Override
	public Reference fetchByPrimaryKey(long id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	@Override
	public Map<Serializable, Reference> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Reference> map = new HashMap<Serializable, Reference>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Reference reference = fetchByPrimaryKey(primaryKey);

			if (reference != null) {
				map.put(primaryKey, reference);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
					ReferenceImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Reference)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_REFERENCE_WHERE_PKS_IN);

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

			for (Reference reference : (List<Reference>)q.list()) {
				map.put(reference.getPrimaryKeyObj(), reference);

				cacheResult(reference);

				uncachedPrimaryKeys.remove(reference.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ReferenceModelImpl.ENTITY_CACHE_ENABLED,
					ReferenceImpl.class, primaryKey, nullModel);
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
	 * Returns all the references.
	 *
	 * @return the references
	 */
	@Override
	public List<Reference> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Reference> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<Reference> findAll(int start, int end,
		OrderByComparator<Reference> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<Reference> findAll(int start, int end,
		OrderByComparator<Reference> orderByComparator,
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

		List<Reference> list = null;

		if (retrieveFromCache) {
			list = (List<Reference>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_REFERENCE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_REFERENCE;

				if (pagination) {
					sql = sql.concat(ReferenceModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Reference>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the references from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Reference reference : findAll()) {
			remove(reference);
		}
	}

	/**
	 * Returns the number of references.
	 *
	 * @return the number of references
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_REFERENCE);

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
		return ReferenceModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the reference persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ReferenceImpl.class.getName());
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
	private static final String _SQL_SELECT_REFERENCE = "SELECT reference FROM Reference reference";
	private static final String _SQL_SELECT_REFERENCE_WHERE_PKS_IN = "SELECT reference FROM Reference reference WHERE id_ IN (";
	private static final String _SQL_SELECT_REFERENCE_WHERE = "SELECT reference FROM Reference reference WHERE ";
	private static final String _SQL_COUNT_REFERENCE = "SELECT COUNT(reference) FROM Reference reference";
	private static final String _SQL_COUNT_REFERENCE_WHERE = "SELECT COUNT(reference) FROM Reference reference WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "reference.resourcePrimKey";
	private static final String _FILTER_SQL_SELECT_REFERENCE_WHERE = "SELECT DISTINCT {reference.*} FROM Reference reference WHERE ";
	private static final String _FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {Reference.*} FROM (SELECT DISTINCT reference.id_ FROM Reference reference WHERE ";
	private static final String _FILTER_SQL_SELECT_REFERENCE_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN Reference ON TEMP_TABLE.id_ = Reference.id_";
	private static final String _FILTER_SQL_COUNT_REFERENCE_WHERE = "SELECT COUNT(DISTINCT reference.id_) AS COUNT_VALUE FROM Reference reference WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "reference";
	private static final String _FILTER_ENTITY_TABLE = "Reference";
	private static final String _ORDER_BY_ENTITY_ALIAS = "reference.";
	private static final String _ORDER_BY_ENTITY_TABLE = "Reference.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Reference exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Reference exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ReferencePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "id"
			});
}