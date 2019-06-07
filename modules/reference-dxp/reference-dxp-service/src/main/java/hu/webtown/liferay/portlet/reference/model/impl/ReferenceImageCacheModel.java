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

package hu.webtown.liferay.portlet.reference.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import hu.webtown.liferay.portlet.reference.model.ReferenceImage;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ReferenceImage in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ReferenceImage
 * @generated
 */
@ProviderType
public class ReferenceImageCacheModel implements CacheModel<ReferenceImage>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ReferenceImageCacheModel)) {
			return false;
		}

		ReferenceImageCacheModel referenceImageCacheModel = (ReferenceImageCacheModel)obj;

		if (referenceImageId == referenceImageCacheModel.referenceImageId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, referenceImageId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", referenceImageId=");
		sb.append(referenceImageId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", referenceId=");
		sb.append(referenceId);
		sb.append(", version=");
		sb.append(version);
		sb.append(", imageType=");
		sb.append(imageType);
		sb.append(", image=");
		sb.append(image);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ReferenceImage toEntityModel() {
		ReferenceImageImpl referenceImageImpl = new ReferenceImageImpl();

		if (uuid == null) {
			referenceImageImpl.setUuid("");
		}
		else {
			referenceImageImpl.setUuid(uuid);
		}

		referenceImageImpl.setReferenceImageId(referenceImageId);
		referenceImageImpl.setGroupId(groupId);
		referenceImageImpl.setCompanyId(companyId);

		if (referenceId == null) {
			referenceImageImpl.setReferenceId("");
		}
		else {
			referenceImageImpl.setReferenceId(referenceId);
		}

		referenceImageImpl.setVersion(version);
		referenceImageImpl.setImageType(imageType);

		if (image == null) {
			referenceImageImpl.setImage("");
		}
		else {
			referenceImageImpl.setImage(image);
		}

		referenceImageImpl.resetOriginalValues();

		return referenceImageImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		referenceImageId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();
		referenceId = objectInput.readUTF();

		version = objectInput.readDouble();

		imageType = objectInput.readInt();
		image = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(referenceImageId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		if (referenceId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(referenceId);
		}

		objectOutput.writeDouble(version);

		objectOutput.writeInt(imageType);

		if (image == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(image);
		}
	}

	public String uuid;
	public long referenceImageId;
	public long groupId;
	public long companyId;
	public String referenceId;
	public double version;
	public int imageType;
	public String image;
}