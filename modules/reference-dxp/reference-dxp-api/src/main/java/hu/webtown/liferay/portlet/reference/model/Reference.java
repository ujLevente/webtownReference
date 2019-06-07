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

package hu.webtown.liferay.portlet.reference.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Reference service. Represents a row in the &quot;Reference&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ReferenceModel
 * @see hu.webtown.liferay.portlet.reference.model.impl.ReferenceImpl
 * @see hu.webtown.liferay.portlet.reference.model.impl.ReferenceModelImpl
 * @generated
 */
@ImplementationClassName("hu.webtown.liferay.portlet.reference.model.impl.ReferenceImpl")
@ProviderType
public interface Reference extends ReferenceModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link hu.webtown.liferay.portlet.reference.model.impl.ReferenceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Reference, Long> ID_ACCESSOR = new Accessor<Reference, Long>() {
			@Override
			public Long get(Reference reference) {
				return reference.getId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Reference> getTypeClass() {
				return Reference.class;
			}
		};

	public static final Accessor<Reference, String> REFERENCE_ID_ACCESSOR = new Accessor<Reference, String>() {
			@Override
			public String get(Reference reference) {
				return reference.getReferenceId();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<Reference> getTypeClass() {
				return Reference.class;
			}
		};

	public hu.webtown.liferay.portlet.reference.model.ReferenceResource getReferenceResource()
		throws com.liferay.portal.kernel.exception.PortalException;

	public String getReferenceResourceUuid()
		throws com.liferay.portal.kernel.exception.PortalException;

	public boolean hasApprovedVersion();
}