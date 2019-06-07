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

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Reference}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Reference
 * @generated
 */
@ProviderType
public class ReferenceWrapper implements Reference, ModelWrapper<Reference> {
	public ReferenceWrapper(Reference reference) {
		_reference = reference;
	}

	@Override
	public Class<?> getModelClass() {
		return Reference.class;
	}

	@Override
	public String getModelClassName() {
		return Reference.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("id", getId());
		attributes.put("resourcePrimKey", getResourcePrimKey());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("referenceId", getReferenceId());
		attributes.put("version", getVersion());
		attributes.put("name", getName());
		attributes.put("urlTitle", getUrlTitle());
		attributes.put("shortDescription", getShortDescription());
		attributes.put("description", getDescription());
		attributes.put("realizationDate", getRealizationDate());
		attributes.put("emphasized", isEmphasized());
		attributes.put("overlayText", getOverlayText());
		attributes.put("overlayUrl", getOverlayUrl());
		attributes.put("lastPublishDate", getLastPublishDate());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
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

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String referenceId = (String)attributes.get("referenceId");

		if (referenceId != null) {
			setReferenceId(referenceId);
		}

		Double version = (Double)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String urlTitle = (String)attributes.get("urlTitle");

		if (urlTitle != null) {
			setUrlTitle(urlTitle);
		}

		String shortDescription = (String)attributes.get("shortDescription");

		if (shortDescription != null) {
			setShortDescription(shortDescription);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Date realizationDate = (Date)attributes.get("realizationDate");

		if (realizationDate != null) {
			setRealizationDate(realizationDate);
		}

		Boolean emphasized = (Boolean)attributes.get("emphasized");

		if (emphasized != null) {
			setEmphasized(emphasized);
		}

		String overlayText = (String)attributes.get("overlayText");

		if (overlayText != null) {
			setOverlayText(overlayText);
		}

		String overlayUrl = (String)attributes.get("overlayUrl");

		if (overlayUrl != null) {
			setOverlayUrl(overlayUrl);
		}

		Date lastPublishDate = (Date)attributes.get("lastPublishDate");

		if (lastPublishDate != null) {
			setLastPublishDate(lastPublishDate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}
	}

	@Override
	public Object clone() {
		return new ReferenceWrapper((Reference)_reference.clone());
	}

	@Override
	public int compareTo(Reference reference) {
		return _reference.compareTo(reference);
	}

	/**
	* Returns the company ID of this reference.
	*
	* @return the company ID of this reference
	*/
	@Override
	public long getCompanyId() {
		return _reference.getCompanyId();
	}

	/**
	* Returns the create date of this reference.
	*
	* @return the create date of this reference
	*/
	@Override
	public Date getCreateDate() {
		return _reference.getCreateDate();
	}

	/**
	* Returns the description of this reference.
	*
	* @return the description of this reference
	*/
	@Override
	public String getDescription() {
		return _reference.getDescription();
	}

	/**
	* Returns the emphasized of this reference.
	*
	* @return the emphasized of this reference
	*/
	@Override
	public boolean getEmphasized() {
		return _reference.getEmphasized();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _reference.getExpandoBridge();
	}

	/**
	* Returns the group ID of this reference.
	*
	* @return the group ID of this reference
	*/
	@Override
	public long getGroupId() {
		return _reference.getGroupId();
	}

	/**
	* Returns the ID of this reference.
	*
	* @return the ID of this reference
	*/
	@Override
	public long getId() {
		return _reference.getId();
	}

	/**
	* Returns the last publish date of this reference.
	*
	* @return the last publish date of this reference
	*/
	@Override
	public Date getLastPublishDate() {
		return _reference.getLastPublishDate();
	}

	/**
	* Returns the modified date of this reference.
	*
	* @return the modified date of this reference
	*/
	@Override
	public Date getModifiedDate() {
		return _reference.getModifiedDate();
	}

	/**
	* Returns the name of this reference.
	*
	* @return the name of this reference
	*/
	@Override
	public String getName() {
		return _reference.getName();
	}

	/**
	* Returns the overlay text of this reference.
	*
	* @return the overlay text of this reference
	*/
	@Override
	public String getOverlayText() {
		return _reference.getOverlayText();
	}

	/**
	* Returns the overlay url of this reference.
	*
	* @return the overlay url of this reference
	*/
	@Override
	public String getOverlayUrl() {
		return _reference.getOverlayUrl();
	}

	/**
	* Returns the primary key of this reference.
	*
	* @return the primary key of this reference
	*/
	@Override
	public long getPrimaryKey() {
		return _reference.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _reference.getPrimaryKeyObj();
	}

	/**
	* Returns the realization date of this reference.
	*
	* @return the realization date of this reference
	*/
	@Override
	public Date getRealizationDate() {
		return _reference.getRealizationDate();
	}

	/**
	* Returns the reference ID of this reference.
	*
	* @return the reference ID of this reference
	*/
	@Override
	public String getReferenceId() {
		return _reference.getReferenceId();
	}

	@Override
	public hu.webtown.liferay.portlet.reference.model.ReferenceResource getReferenceResource()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _reference.getReferenceResource();
	}

	@Override
	public String getReferenceResourceUuid()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _reference.getReferenceResourceUuid();
	}

	/**
	* Returns the resource prim key of this reference.
	*
	* @return the resource prim key of this reference
	*/
	@Override
	public long getResourcePrimKey() {
		return _reference.getResourcePrimKey();
	}

	/**
	* Returns the short description of this reference.
	*
	* @return the short description of this reference
	*/
	@Override
	public String getShortDescription() {
		return _reference.getShortDescription();
	}

	/**
	* Returns the status of this reference.
	*
	* @return the status of this reference
	*/
	@Override
	public int getStatus() {
		return _reference.getStatus();
	}

	/**
	* Returns the status by user ID of this reference.
	*
	* @return the status by user ID of this reference
	*/
	@Override
	public long getStatusByUserId() {
		return _reference.getStatusByUserId();
	}

	/**
	* Returns the status by user name of this reference.
	*
	* @return the status by user name of this reference
	*/
	@Override
	public String getStatusByUserName() {
		return _reference.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this reference.
	*
	* @return the status by user uuid of this reference
	*/
	@Override
	public String getStatusByUserUuid() {
		return _reference.getStatusByUserUuid();
	}

	/**
	* Returns the status date of this reference.
	*
	* @return the status date of this reference
	*/
	@Override
	public Date getStatusDate() {
		return _reference.getStatusDate();
	}

	/**
	* Returns the trash entry created when this reference was moved to the Recycle Bin. The trash entry may belong to one of the ancestors of this reference.
	*
	* @return the trash entry created when this reference was moved to the Recycle Bin
	*/
	@Override
	public com.liferay.trash.kernel.model.TrashEntry getTrashEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _reference.getTrashEntry();
	}

	/**
	* Returns the class primary key of the trash entry for this reference.
	*
	* @return the class primary key of the trash entry for this reference
	*/
	@Override
	public long getTrashEntryClassPK() {
		return _reference.getTrashEntryClassPK();
	}

	/**
	* Returns the trash handler for this reference.
	*
	* @return the trash handler for this reference
	* @deprecated As of 7.0.0, with no direct replacement
	*/
	@Deprecated
	@Override
	public com.liferay.portal.kernel.trash.TrashHandler getTrashHandler() {
		return _reference.getTrashHandler();
	}

	/**
	* Returns the url title of this reference.
	*
	* @return the url title of this reference
	*/
	@Override
	public String getUrlTitle() {
		return _reference.getUrlTitle();
	}

	/**
	* Returns the user ID of this reference.
	*
	* @return the user ID of this reference
	*/
	@Override
	public long getUserId() {
		return _reference.getUserId();
	}

	/**
	* Returns the user name of this reference.
	*
	* @return the user name of this reference
	*/
	@Override
	public String getUserName() {
		return _reference.getUserName();
	}

	/**
	* Returns the user uuid of this reference.
	*
	* @return the user uuid of this reference
	*/
	@Override
	public String getUserUuid() {
		return _reference.getUserUuid();
	}

	/**
	* Returns the uuid of this reference.
	*
	* @return the uuid of this reference
	*/
	@Override
	public String getUuid() {
		return _reference.getUuid();
	}

	/**
	* Returns the version of this reference.
	*
	* @return the version of this reference
	*/
	@Override
	public double getVersion() {
		return _reference.getVersion();
	}

	@Override
	public boolean hasApprovedVersion() {
		return _reference.hasApprovedVersion();
	}

	@Override
	public int hashCode() {
		return _reference.hashCode();
	}

	/**
	* Returns <code>true</code> if this reference is approved.
	*
	* @return <code>true</code> if this reference is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _reference.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _reference.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this reference is denied.
	*
	* @return <code>true</code> if this reference is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _reference.isDenied();
	}

	/**
	* Returns <code>true</code> if this reference is a draft.
	*
	* @return <code>true</code> if this reference is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _reference.isDraft();
	}

	/**
	* Returns <code>true</code> if this reference is emphasized.
	*
	* @return <code>true</code> if this reference is emphasized; <code>false</code> otherwise
	*/
	@Override
	public boolean isEmphasized() {
		return _reference.isEmphasized();
	}

	@Override
	public boolean isEscapedModel() {
		return _reference.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this reference is expired.
	*
	* @return <code>true</code> if this reference is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _reference.isExpired();
	}

	/**
	* Returns <code>true</code> if this reference is inactive.
	*
	* @return <code>true</code> if this reference is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _reference.isInactive();
	}

	/**
	* Returns <code>true</code> if this reference is incomplete.
	*
	* @return <code>true</code> if this reference is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _reference.isIncomplete();
	}

	/**
	* Returns <code>true</code> if this reference is in the Recycle Bin.
	*
	* @return <code>true</code> if this reference is in the Recycle Bin; <code>false</code> otherwise
	*/
	@Override
	public boolean isInTrash() {
		return _reference.isInTrash();
	}

	/**
	* Returns <code>true</code> if the parent of this reference is in the Recycle Bin.
	*
	* @return <code>true</code> if the parent of this reference is in the Recycle Bin; <code>false</code> otherwise
	*/
	@Override
	public boolean isInTrashContainer() {
		return _reference.isInTrashContainer();
	}

	@Override
	public boolean isInTrashExplicitly() {
		return _reference.isInTrashExplicitly();
	}

	@Override
	public boolean isInTrashImplicitly() {
		return _reference.isInTrashImplicitly();
	}

	@Override
	public boolean isNew() {
		return _reference.isNew();
	}

	/**
	* Returns <code>true</code> if this reference is pending.
	*
	* @return <code>true</code> if this reference is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _reference.isPending();
	}

	@Override
	public boolean isResourceMain() {
		return _reference.isResourceMain();
	}

	/**
	* Returns <code>true</code> if this reference is scheduled.
	*
	* @return <code>true</code> if this reference is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _reference.isScheduled();
	}

	@Override
	public void persist() {
		_reference.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_reference.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this reference.
	*
	* @param companyId the company ID of this reference
	*/
	@Override
	public void setCompanyId(long companyId) {
		_reference.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this reference.
	*
	* @param createDate the create date of this reference
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_reference.setCreateDate(createDate);
	}

	/**
	* Sets the description of this reference.
	*
	* @param description the description of this reference
	*/
	@Override
	public void setDescription(String description) {
		_reference.setDescription(description);
	}

	/**
	* Sets whether this reference is emphasized.
	*
	* @param emphasized the emphasized of this reference
	*/
	@Override
	public void setEmphasized(boolean emphasized) {
		_reference.setEmphasized(emphasized);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_reference.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_reference.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_reference.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this reference.
	*
	* @param groupId the group ID of this reference
	*/
	@Override
	public void setGroupId(long groupId) {
		_reference.setGroupId(groupId);
	}

	/**
	* Sets the ID of this reference.
	*
	* @param id the ID of this reference
	*/
	@Override
	public void setId(long id) {
		_reference.setId(id);
	}

	/**
	* Sets the last publish date of this reference.
	*
	* @param lastPublishDate the last publish date of this reference
	*/
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_reference.setLastPublishDate(lastPublishDate);
	}

	/**
	* Sets the modified date of this reference.
	*
	* @param modifiedDate the modified date of this reference
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_reference.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this reference.
	*
	* @param name the name of this reference
	*/
	@Override
	public void setName(String name) {
		_reference.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_reference.setNew(n);
	}

	/**
	* Sets the overlay text of this reference.
	*
	* @param overlayText the overlay text of this reference
	*/
	@Override
	public void setOverlayText(String overlayText) {
		_reference.setOverlayText(overlayText);
	}

	/**
	* Sets the overlay url of this reference.
	*
	* @param overlayUrl the overlay url of this reference
	*/
	@Override
	public void setOverlayUrl(String overlayUrl) {
		_reference.setOverlayUrl(overlayUrl);
	}

	/**
	* Sets the primary key of this reference.
	*
	* @param primaryKey the primary key of this reference
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_reference.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_reference.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the realization date of this reference.
	*
	* @param realizationDate the realization date of this reference
	*/
	@Override
	public void setRealizationDate(Date realizationDate) {
		_reference.setRealizationDate(realizationDate);
	}

	/**
	* Sets the reference ID of this reference.
	*
	* @param referenceId the reference ID of this reference
	*/
	@Override
	public void setReferenceId(String referenceId) {
		_reference.setReferenceId(referenceId);
	}

	/**
	* Sets the resource prim key of this reference.
	*
	* @param resourcePrimKey the resource prim key of this reference
	*/
	@Override
	public void setResourcePrimKey(long resourcePrimKey) {
		_reference.setResourcePrimKey(resourcePrimKey);
	}

	/**
	* Sets the short description of this reference.
	*
	* @param shortDescription the short description of this reference
	*/
	@Override
	public void setShortDescription(String shortDescription) {
		_reference.setShortDescription(shortDescription);
	}

	/**
	* Sets the status of this reference.
	*
	* @param status the status of this reference
	*/
	@Override
	public void setStatus(int status) {
		_reference.setStatus(status);
	}

	/**
	* Sets the status by user ID of this reference.
	*
	* @param statusByUserId the status by user ID of this reference
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_reference.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this reference.
	*
	* @param statusByUserName the status by user name of this reference
	*/
	@Override
	public void setStatusByUserName(String statusByUserName) {
		_reference.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this reference.
	*
	* @param statusByUserUuid the status by user uuid of this reference
	*/
	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		_reference.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this reference.
	*
	* @param statusDate the status date of this reference
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_reference.setStatusDate(statusDate);
	}

	/**
	* Sets the url title of this reference.
	*
	* @param urlTitle the url title of this reference
	*/
	@Override
	public void setUrlTitle(String urlTitle) {
		_reference.setUrlTitle(urlTitle);
	}

	/**
	* Sets the user ID of this reference.
	*
	* @param userId the user ID of this reference
	*/
	@Override
	public void setUserId(long userId) {
		_reference.setUserId(userId);
	}

	/**
	* Sets the user name of this reference.
	*
	* @param userName the user name of this reference
	*/
	@Override
	public void setUserName(String userName) {
		_reference.setUserName(userName);
	}

	/**
	* Sets the user uuid of this reference.
	*
	* @param userUuid the user uuid of this reference
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_reference.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this reference.
	*
	* @param uuid the uuid of this reference
	*/
	@Override
	public void setUuid(String uuid) {
		_reference.setUuid(uuid);
	}

	/**
	* Sets the version of this reference.
	*
	* @param version the version of this reference
	*/
	@Override
	public void setVersion(double version) {
		_reference.setVersion(version);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Reference> toCacheModel() {
		return _reference.toCacheModel();
	}

	@Override
	public Reference toEscapedModel() {
		return new ReferenceWrapper(_reference.toEscapedModel());
	}

	@Override
	public String toString() {
		return _reference.toString();
	}

	@Override
	public Reference toUnescapedModel() {
		return new ReferenceWrapper(_reference.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _reference.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ReferenceWrapper)) {
			return false;
		}

		ReferenceWrapper referenceWrapper = (ReferenceWrapper)obj;

		if (Objects.equals(_reference, referenceWrapper._reference)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _reference.getStagedModelType();
	}

	@Override
	public Reference getWrappedModel() {
		return _reference;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _reference.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _reference.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_reference.resetOriginalValues();
	}

	private final Reference _reference;
}