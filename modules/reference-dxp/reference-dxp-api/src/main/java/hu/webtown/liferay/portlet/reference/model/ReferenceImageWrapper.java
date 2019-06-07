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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ReferenceImage}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReferenceImage
 * @generated
 */
@ProviderType
public class ReferenceImageWrapper implements ReferenceImage,
	ModelWrapper<ReferenceImage> {
	public ReferenceImageWrapper(ReferenceImage referenceImage) {
		_referenceImage = referenceImage;
	}

	@Override
	public Class<?> getModelClass() {
		return ReferenceImage.class;
	}

	@Override
	public String getModelClassName() {
		return ReferenceImage.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("referenceImageId", getReferenceImageId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("referenceId", getReferenceId());
		attributes.put("version", getVersion());
		attributes.put("imageType", getImageType());
		attributes.put("image", getImage());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long referenceImageId = (Long)attributes.get("referenceImageId");

		if (referenceImageId != null) {
			setReferenceImageId(referenceImageId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		String referenceId = (String)attributes.get("referenceId");

		if (referenceId != null) {
			setReferenceId(referenceId);
		}

		Double version = (Double)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		Integer imageType = (Integer)attributes.get("imageType");

		if (imageType != null) {
			setImageType(imageType);
		}

		String image = (String)attributes.get("image");

		if (image != null) {
			setImage(image);
		}
	}

	@Override
	public Object clone() {
		return new ReferenceImageWrapper((ReferenceImage)_referenceImage.clone());
	}

	@Override
	public int compareTo(ReferenceImage referenceImage) {
		return _referenceImage.compareTo(referenceImage);
	}

	/**
	* Returns the company ID of this reference image.
	*
	* @return the company ID of this reference image
	*/
	@Override
	public long getCompanyId() {
		return _referenceImage.getCompanyId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _referenceImage.getExpandoBridge();
	}

	/**
	* Returns the group ID of this reference image.
	*
	* @return the group ID of this reference image
	*/
	@Override
	public long getGroupId() {
		return _referenceImage.getGroupId();
	}

	/**
	* Returns the image of this reference image.
	*
	* @return the image of this reference image
	*/
	@Override
	public String getImage() {
		return _referenceImage.getImage();
	}

	/**
	* Returns the image type of this reference image.
	*
	* @return the image type of this reference image
	*/
	@Override
	public int getImageType() {
		return _referenceImage.getImageType();
	}

	/**
	* Returns the primary key of this reference image.
	*
	* @return the primary key of this reference image
	*/
	@Override
	public long getPrimaryKey() {
		return _referenceImage.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _referenceImage.getPrimaryKeyObj();
	}

	/**
	* Returns the reference ID of this reference image.
	*
	* @return the reference ID of this reference image
	*/
	@Override
	public String getReferenceId() {
		return _referenceImage.getReferenceId();
	}

	/**
	* Returns the reference image ID of this reference image.
	*
	* @return the reference image ID of this reference image
	*/
	@Override
	public long getReferenceImageId() {
		return _referenceImage.getReferenceImageId();
	}

	/**
	* Returns the uuid of this reference image.
	*
	* @return the uuid of this reference image
	*/
	@Override
	public String getUuid() {
		return _referenceImage.getUuid();
	}

	/**
	* Returns the version of this reference image.
	*
	* @return the version of this reference image
	*/
	@Override
	public double getVersion() {
		return _referenceImage.getVersion();
	}

	@Override
	public int hashCode() {
		return _referenceImage.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _referenceImage.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _referenceImage.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _referenceImage.isNew();
	}

	@Override
	public void persist() {
		_referenceImage.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_referenceImage.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this reference image.
	*
	* @param companyId the company ID of this reference image
	*/
	@Override
	public void setCompanyId(long companyId) {
		_referenceImage.setCompanyId(companyId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_referenceImage.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_referenceImage.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_referenceImage.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this reference image.
	*
	* @param groupId the group ID of this reference image
	*/
	@Override
	public void setGroupId(long groupId) {
		_referenceImage.setGroupId(groupId);
	}

	/**
	* Sets the image of this reference image.
	*
	* @param image the image of this reference image
	*/
	@Override
	public void setImage(String image) {
		_referenceImage.setImage(image);
	}

	/**
	* Sets the image type of this reference image.
	*
	* @param imageType the image type of this reference image
	*/
	@Override
	public void setImageType(int imageType) {
		_referenceImage.setImageType(imageType);
	}

	@Override
	public void setNew(boolean n) {
		_referenceImage.setNew(n);
	}

	/**
	* Sets the primary key of this reference image.
	*
	* @param primaryKey the primary key of this reference image
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_referenceImage.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_referenceImage.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the reference ID of this reference image.
	*
	* @param referenceId the reference ID of this reference image
	*/
	@Override
	public void setReferenceId(String referenceId) {
		_referenceImage.setReferenceId(referenceId);
	}

	/**
	* Sets the reference image ID of this reference image.
	*
	* @param referenceImageId the reference image ID of this reference image
	*/
	@Override
	public void setReferenceImageId(long referenceImageId) {
		_referenceImage.setReferenceImageId(referenceImageId);
	}

	/**
	* Sets the uuid of this reference image.
	*
	* @param uuid the uuid of this reference image
	*/
	@Override
	public void setUuid(String uuid) {
		_referenceImage.setUuid(uuid);
	}

	/**
	* Sets the version of this reference image.
	*
	* @param version the version of this reference image
	*/
	@Override
	public void setVersion(double version) {
		_referenceImage.setVersion(version);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ReferenceImage> toCacheModel() {
		return _referenceImage.toCacheModel();
	}

	@Override
	public ReferenceImage toEscapedModel() {
		return new ReferenceImageWrapper(_referenceImage.toEscapedModel());
	}

	@Override
	public String toString() {
		return _referenceImage.toString();
	}

	@Override
	public ReferenceImage toUnescapedModel() {
		return new ReferenceImageWrapper(_referenceImage.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _referenceImage.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ReferenceImageWrapper)) {
			return false;
		}

		ReferenceImageWrapper referenceImageWrapper = (ReferenceImageWrapper)obj;

		if (Objects.equals(_referenceImage,
					referenceImageWrapper._referenceImage)) {
			return true;
		}

		return false;
	}

	@Override
	public ReferenceImage getWrappedModel() {
		return _referenceImage;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _referenceImage.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _referenceImage.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_referenceImage.resetOriginalValues();
	}

	private final ReferenceImage _referenceImage;
}