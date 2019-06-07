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
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class ReferenceResourceSoap implements Serializable {
	public static ReferenceResourceSoap toSoapModel(ReferenceResource model) {
		ReferenceResourceSoap soapModel = new ReferenceResourceSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setResourcePrimKey(model.getResourcePrimKey());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setReferenceId(model.getReferenceId());

		return soapModel;
	}

	public static ReferenceResourceSoap[] toSoapModels(
		ReferenceResource[] models) {
		ReferenceResourceSoap[] soapModels = new ReferenceResourceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ReferenceResourceSoap[][] toSoapModels(
		ReferenceResource[][] models) {
		ReferenceResourceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ReferenceResourceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ReferenceResourceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ReferenceResourceSoap[] toSoapModels(
		List<ReferenceResource> models) {
		List<ReferenceResourceSoap> soapModels = new ArrayList<ReferenceResourceSoap>(models.size());

		for (ReferenceResource model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ReferenceResourceSoap[soapModels.size()]);
	}

	public ReferenceResourceSoap() {
	}

	public long getPrimaryKey() {
		return _resourcePrimKey;
	}

	public void setPrimaryKey(long pk) {
		setResourcePrimKey(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
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

	public String getReferenceId() {
		return _referenceId;
	}

	public void setReferenceId(String referenceId) {
		_referenceId = referenceId;
	}

	private String _uuid;
	private long _resourcePrimKey;
	private long _groupId;
	private long _companyId;
	private String _referenceId;
}