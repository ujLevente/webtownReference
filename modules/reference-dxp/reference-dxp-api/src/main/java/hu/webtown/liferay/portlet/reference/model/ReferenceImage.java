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
 * The extended model interface for the ReferenceImage service. Represents a row in the &quot;ReferenceImage&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ReferenceImageModel
 * @see hu.webtown.liferay.portlet.reference.model.impl.ReferenceImageImpl
 * @see hu.webtown.liferay.portlet.reference.model.impl.ReferenceImageModelImpl
 * @generated
 */
@ImplementationClassName("hu.webtown.liferay.portlet.reference.model.impl.ReferenceImageImpl")
@ProviderType
public interface ReferenceImage extends ReferenceImageModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link hu.webtown.liferay.portlet.reference.model.impl.ReferenceImageImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ReferenceImage, Long> REFERENCE_IMAGE_ID_ACCESSOR =
		new Accessor<ReferenceImage, Long>() {
			@Override
			public Long get(ReferenceImage referenceImage) {
				return referenceImage.getReferenceImageId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ReferenceImage> getTypeClass() {
				return ReferenceImage.class;
			}
		};
}