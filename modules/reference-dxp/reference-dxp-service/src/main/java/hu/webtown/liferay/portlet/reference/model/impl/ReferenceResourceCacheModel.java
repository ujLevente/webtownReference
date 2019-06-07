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

import hu.webtown.liferay.portlet.reference.model.ReferenceResource;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ReferenceResource in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ReferenceResource
 * @generated
 */
@ProviderType
public class ReferenceResourceCacheModel implements CacheModel<ReferenceResource>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ReferenceResourceCacheModel)) {
			return false;
		}

		ReferenceResourceCacheModel referenceResourceCacheModel = (ReferenceResourceCacheModel)obj;

		if (resourcePrimKey == referenceResourceCacheModel.resourcePrimKey) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, resourcePrimKey);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", resourcePrimKey=");
		sb.append(resourcePrimKey);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", referenceId=");
		sb.append(referenceId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ReferenceResource toEntityModel() {
		ReferenceResourceImpl referenceResourceImpl = new ReferenceResourceImpl();

		if (uuid == null) {
			referenceResourceImpl.setUuid("");
		}
		else {
			referenceResourceImpl.setUuid(uuid);
		}

		referenceResourceImpl.setResourcePrimKey(resourcePrimKey);
		referenceResourceImpl.setGroupId(groupId);
		referenceResourceImpl.setCompanyId(companyId);

		if (referenceId == null) {
			referenceResourceImpl.setReferenceId("");
		}
		else {
			referenceResourceImpl.setReferenceId(referenceId);
		}

		referenceResourceImpl.resetOriginalValues();

		return referenceResourceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		resourcePrimKey = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();
		referenceId = objectInput.readUTF();
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

		objectOutput.writeLong(resourcePrimKey);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		if (referenceId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(referenceId);
		}
	}

	public String uuid;
	public long resourcePrimKey;
	public long groupId;
	public long companyId;
	public String referenceId;
}