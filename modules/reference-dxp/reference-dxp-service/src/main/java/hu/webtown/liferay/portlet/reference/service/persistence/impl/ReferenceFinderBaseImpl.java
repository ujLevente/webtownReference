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

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import hu.webtown.liferay.portlet.reference.model.Reference;
import hu.webtown.liferay.portlet.reference.service.persistence.ReferencePersistence;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ReferenceFinderBaseImpl extends BasePersistenceImpl<Reference> {
	public ReferenceFinderBaseImpl() {
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

	@Override
	public Set<String> getBadColumnNames() {
		return getReferencePersistence().getBadColumnNames();
	}

	/**
	 * Returns the reference persistence.
	 *
	 * @return the reference persistence
	 */
	public ReferencePersistence getReferencePersistence() {
		return referencePersistence;
	}

	/**
	 * Sets the reference persistence.
	 *
	 * @param referencePersistence the reference persistence
	 */
	public void setReferencePersistence(
		ReferencePersistence referencePersistence) {
		this.referencePersistence = referencePersistence;
	}

	@BeanReference(type = ReferencePersistence.class)
	protected ReferencePersistence referencePersistence;
	private static final Log _log = LogFactoryUtil.getLog(ReferenceFinderBaseImpl.class);
}