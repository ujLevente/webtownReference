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

import hu.webtown.liferay.portlet.reference.model.Reference;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Reference in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Reference
 * @generated
 */
@ProviderType
public class ReferenceCacheModel implements CacheModel<Reference>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ReferenceCacheModel)) {
			return false;
		}

		ReferenceCacheModel referenceCacheModel = (ReferenceCacheModel)obj;

		if (id == referenceCacheModel.id) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, id);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(49);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", id=");
		sb.append(id);
		sb.append(", resourcePrimKey=");
		sb.append(resourcePrimKey);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", referenceId=");
		sb.append(referenceId);
		sb.append(", version=");
		sb.append(version);
		sb.append(", name=");
		sb.append(name);
		sb.append(", urlTitle=");
		sb.append(urlTitle);
		sb.append(", shortDescription=");
		sb.append(shortDescription);
		sb.append(", description=");
		sb.append(description);
		sb.append(", realizationDate=");
		sb.append(realizationDate);
		sb.append(", emphasized=");
		sb.append(emphasized);
		sb.append(", overlayText=");
		sb.append(overlayText);
		sb.append(", overlayUrl=");
		sb.append(overlayUrl);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Reference toEntityModel() {
		ReferenceImpl referenceImpl = new ReferenceImpl();

		if (uuid == null) {
			referenceImpl.setUuid("");
		}
		else {
			referenceImpl.setUuid(uuid);
		}

		referenceImpl.setId(id);
		referenceImpl.setResourcePrimKey(resourcePrimKey);
		referenceImpl.setGroupId(groupId);
		referenceImpl.setCompanyId(companyId);
		referenceImpl.setUserId(userId);

		if (userName == null) {
			referenceImpl.setUserName("");
		}
		else {
			referenceImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			referenceImpl.setCreateDate(null);
		}
		else {
			referenceImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			referenceImpl.setModifiedDate(null);
		}
		else {
			referenceImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (referenceId == null) {
			referenceImpl.setReferenceId("");
		}
		else {
			referenceImpl.setReferenceId(referenceId);
		}

		referenceImpl.setVersion(version);

		if (name == null) {
			referenceImpl.setName("");
		}
		else {
			referenceImpl.setName(name);
		}

		if (urlTitle == null) {
			referenceImpl.setUrlTitle("");
		}
		else {
			referenceImpl.setUrlTitle(urlTitle);
		}

		if (shortDescription == null) {
			referenceImpl.setShortDescription("");
		}
		else {
			referenceImpl.setShortDescription(shortDescription);
		}

		if (description == null) {
			referenceImpl.setDescription("");
		}
		else {
			referenceImpl.setDescription(description);
		}

		if (realizationDate == Long.MIN_VALUE) {
			referenceImpl.setRealizationDate(null);
		}
		else {
			referenceImpl.setRealizationDate(new Date(realizationDate));
		}

		referenceImpl.setEmphasized(emphasized);

		if (overlayText == null) {
			referenceImpl.setOverlayText("");
		}
		else {
			referenceImpl.setOverlayText(overlayText);
		}

		if (overlayUrl == null) {
			referenceImpl.setOverlayUrl("");
		}
		else {
			referenceImpl.setOverlayUrl(overlayUrl);
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			referenceImpl.setLastPublishDate(null);
		}
		else {
			referenceImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		referenceImpl.setStatus(status);
		referenceImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			referenceImpl.setStatusByUserName("");
		}
		else {
			referenceImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			referenceImpl.setStatusDate(null);
		}
		else {
			referenceImpl.setStatusDate(new Date(statusDate));
		}

		referenceImpl.resetOriginalValues();

		return referenceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		id = objectInput.readLong();

		resourcePrimKey = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		referenceId = objectInput.readUTF();

		version = objectInput.readDouble();
		name = objectInput.readUTF();
		urlTitle = objectInput.readUTF();
		shortDescription = objectInput.readUTF();
		description = objectInput.readUTF();
		realizationDate = objectInput.readLong();

		emphasized = objectInput.readBoolean();
		overlayText = objectInput.readUTF();
		overlayUrl = objectInput.readUTF();
		lastPublishDate = objectInput.readLong();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
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

		objectOutput.writeLong(id);

		objectOutput.writeLong(resourcePrimKey);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (referenceId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(referenceId);
		}

		objectOutput.writeDouble(version);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (urlTitle == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(urlTitle);
		}

		if (shortDescription == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(shortDescription);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(realizationDate);

		objectOutput.writeBoolean(emphasized);

		if (overlayText == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(overlayText);
		}

		if (overlayUrl == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(overlayUrl);
		}

		objectOutput.writeLong(lastPublishDate);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);
	}

	public String uuid;
	public long id;
	public long resourcePrimKey;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String referenceId;
	public double version;
	public String name;
	public String urlTitle;
	public String shortDescription;
	public String description;
	public long realizationDate;
	public boolean emphasized;
	public String overlayText;
	public String overlayUrl;
	public long lastPublishDate;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
}