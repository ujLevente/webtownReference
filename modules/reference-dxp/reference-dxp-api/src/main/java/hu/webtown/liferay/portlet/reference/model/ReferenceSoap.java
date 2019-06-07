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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link hu.webtown.liferay.portlet.reference.service.http.ReferenceServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see hu.webtown.liferay.portlet.reference.service.http.ReferenceServiceSoap
 * @generated
 */
@ProviderType
public class ReferenceSoap implements Serializable {
	public static ReferenceSoap toSoapModel(Reference model) {
		ReferenceSoap soapModel = new ReferenceSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setId(model.getId());
		soapModel.setResourcePrimKey(model.getResourcePrimKey());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setReferenceId(model.getReferenceId());
		soapModel.setVersion(model.getVersion());
		soapModel.setName(model.getName());
		soapModel.setUrlTitle(model.getUrlTitle());
		soapModel.setShortDescription(model.getShortDescription());
		soapModel.setDescription(model.getDescription());
		soapModel.setRealizationDate(model.getRealizationDate());
		soapModel.setEmphasized(model.isEmphasized());
		soapModel.setOverlayText(model.getOverlayText());
		soapModel.setOverlayUrl(model.getOverlayUrl());
		soapModel.setLastPublishDate(model.getLastPublishDate());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());

		return soapModel;
	}

	public static ReferenceSoap[] toSoapModels(Reference[] models) {
		ReferenceSoap[] soapModels = new ReferenceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ReferenceSoap[][] toSoapModels(Reference[][] models) {
		ReferenceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ReferenceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ReferenceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ReferenceSoap[] toSoapModels(List<Reference> models) {
		List<ReferenceSoap> soapModels = new ArrayList<ReferenceSoap>(models.size());

		for (Reference model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ReferenceSoap[soapModels.size()]);
	}

	public ReferenceSoap() {
	}

	public long getPrimaryKey() {
		return _id;
	}

	public void setPrimaryKey(long pk) {
		setId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;
	}

	public long getResourcePrimKey() {
		return _resourcePrimKey;
	}

	public void setResourcePrimKey(long resourcePrimKey) {
		_resourcePrimKey = resourcePrimKey;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getReferenceId() {
		return _referenceId;
	}

	public void setReferenceId(String referenceId) {
		_referenceId = referenceId;
	}

	public double getVersion() {
		return _version;
	}

	public void setVersion(double version) {
		_version = version;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getUrlTitle() {
		return _urlTitle;
	}

	public void setUrlTitle(String urlTitle) {
		_urlTitle = urlTitle;
	}

	public String getShortDescription() {
		return _shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		_shortDescription = shortDescription;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public Date getRealizationDate() {
		return _realizationDate;
	}

	public void setRealizationDate(Date realizationDate) {
		_realizationDate = realizationDate;
	}

	public boolean getEmphasized() {
		return _emphasized;
	}

	public boolean isEmphasized() {
		return _emphasized;
	}

	public void setEmphasized(boolean emphasized) {
		_emphasized = emphasized;
	}

	public String getOverlayText() {
		return _overlayText;
	}

	public void setOverlayText(String overlayText) {
		_overlayText = overlayText;
	}

	public String getOverlayUrl() {
		return _overlayUrl;
	}

	public void setOverlayUrl(String overlayUrl) {
		_overlayUrl = overlayUrl;
	}

	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	private String _uuid;
	private long _id;
	private long _resourcePrimKey;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _referenceId;
	private double _version;
	private String _name;
	private String _urlTitle;
	private String _shortDescription;
	private String _description;
	private Date _realizationDate;
	private boolean _emphasized;
	private String _overlayText;
	private String _overlayUrl;
	private Date _lastPublishDate;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
}