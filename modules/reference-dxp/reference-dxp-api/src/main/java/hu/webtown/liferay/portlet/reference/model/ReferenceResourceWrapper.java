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
 * This class is a wrapper for {@link ReferenceResource}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReferenceResource
 * @generated
 */
@ProviderType
public class ReferenceResourceWrapper implements ReferenceResource,
	ModelWrapper<ReferenceResource> {
	public ReferenceResourceWrapper(ReferenceResource referenceResource) {
		_referenceResource = referenceResource;
	}

	@Override
	public Class<?> getModelClass() {
		return ReferenceResource.class;
	}

	@Override
	public String getModelClassName() {
		return ReferenceResource.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("resourcePrimKey", getResourcePrimKey());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("referenceId", getReferenceId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long resourcePrimKey = (Long)attributes.get("resourcePrimKey");

		if (resourcePrimKey != null) {
			setResourcePrimKey(resourcePrimKey);
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
	}

	@Override
	public Object clone() {
		return new ReferenceResourceWrapper((ReferenceResource)_referenceResource.clone());
	}

	@Override
	public int compareTo(ReferenceResource referenceResource) {
		return _referenceResource.compareTo(referenceResource);
	}

	/**
	* Returns the company ID of this reference resource.
	*
	* @return the company ID of this reference resource
	*/
	@Override
	public long getCompanyId() {
		return _referenceResource.getCompanyId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _referenceResource.getExpandoBridge();
	}

	/**
	* Returns the group ID of this reference resource.
	*
	* @return the group ID of this reference resource
	*/
	@Override
	public long getGroupId() {
		return _referenceResource.getGroupId();
	}

	/**
	* Returns the primary key of this reference resource.
	*
	* @return the primary key of this reference resource
	*/
	@Override
	public long getPrimaryKey() {
		return _referenceResource.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _referenceResource.getPrimaryKeyObj();
	}

	/**
	* Returns the reference ID of this reference resource.
	*
	* @return the reference ID of this reference resource
	*/
	@Override
	public String getReferenceId() {
		return _referenceResource.getReferenceId();
	}

	/**
	* Returns the resource prim key of this reference resource.
	*
	* @return the resource prim key of this reference resource
	*/
	@Override
	public long getResourcePrimKey() {
		return _referenceResource.getResourcePrimKey();
	}

	/**
	* Returns the uuid of this reference resource.
	*
	* @return the uuid of this reference resource
	*/
	@Override
	public String getUuid() {
		return _referenceResource.getUuid();
	}

	@Override
	public int hashCode() {
		return _referenceResource.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _referenceResource.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _referenceResource.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _referenceResource.isNew();
	}

	@Override
	public void persist() {
		_referenceResource.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_referenceResource.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this reference resource.
	*
	* @param companyId the company ID of this reference resource
	*/
	@Override
	public void setCompanyId(long companyId) {
		_referenceResource.setCompanyId(companyId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_referenceResource.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_referenceResource.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_referenceResource.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this reference resource.
	*
	* @param groupId the group ID of this reference resource
	*/
	@Override
	public void setGroupId(long groupId) {
		_referenceResource.setGroupId(groupId);
	}

	@Override
	public void setNew(boolean n) {
		_referenceResource.setNew(n);
	}

	/**
	* Sets the primary key of this reference resource.
	*
	* @param primaryKey the primary key of this reference resource
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_referenceResource.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_referenceResource.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the reference ID of this reference resource.
	*
	* @param referenceId the reference ID of this reference resource
	*/
	@Override
	public void setReferenceId(String referenceId) {
		_referenceResource.setReferenceId(referenceId);
	}

	/**
	* Sets the resource prim key of this reference resource.
	*
	* @param resourcePrimKey the resource prim key of this reference resource
	*/
	@Override
	public void setResourcePrimKey(long resourcePrimKey) {
		_referenceResource.setResourcePrimKey(resourcePrimKey);
	}

	/**
	* Sets the uuid of this reference resource.
	*
	* @param uuid the uuid of this reference resource
	*/
	@Override
	public void setUuid(String uuid) {
		_referenceResource.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ReferenceResource> toCacheModel() {
		return _referenceResource.toCacheModel();
	}

	@Override
	public ReferenceResource toEscapedModel() {
		return new ReferenceResourceWrapper(_referenceResource.toEscapedModel());
	}

	@Override
	public String toString() {
		return _referenceResource.toString();
	}

	@Override
	public ReferenceResource toUnescapedModel() {
		return new ReferenceResourceWrapper(_referenceResource.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _referenceResource.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ReferenceResourceWrapper)) {
			return false;
		}

		ReferenceResourceWrapper referenceResourceWrapper = (ReferenceResourceWrapper)obj;

		if (Objects.equals(_referenceResource,
					referenceResourceWrapper._referenceResource)) {
			return true;
		}

		return false;
	}

	@Override
	public ReferenceResource getWrappedModel() {
		return _referenceResource;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _referenceResource.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _referenceResource.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_referenceResource.resetOriginalValues();
	}

	private final ReferenceResource _referenceResource;
}