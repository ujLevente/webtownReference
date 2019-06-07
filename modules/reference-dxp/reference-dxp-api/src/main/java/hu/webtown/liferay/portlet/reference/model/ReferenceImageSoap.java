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
public class ReferenceImageSoap implements Serializable {
	public static ReferenceImageSoap toSoapModel(ReferenceImage model) {
		ReferenceImageSoap soapModel = new ReferenceImageSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setReferenceImageId(model.getReferenceImageId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setReferenceId(model.getReferenceId());
		soapModel.setVersion(model.getVersion());
		soapModel.setImageType(model.getImageType());
		soapModel.setImage(model.getImage());

		return soapModel;
	}

	public static ReferenceImageSoap[] toSoapModels(ReferenceImage[] models) {
		ReferenceImageSoap[] soapModels = new ReferenceImageSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ReferenceImageSoap[][] toSoapModels(ReferenceImage[][] models) {
		ReferenceImageSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ReferenceImageSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ReferenceImageSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ReferenceImageSoap[] toSoapModels(List<ReferenceImage> models) {
		List<ReferenceImageSoap> soapModels = new ArrayList<ReferenceImageSoap>(models.size());

		for (ReferenceImage model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ReferenceImageSoap[soapModels.size()]);
	}

	public ReferenceImageSoap() {
	}

	public long getPrimaryKey() {
		return _referenceImageId;
	}

	public void setPrimaryKey(long pk) {
		setReferenceImageId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getReferenceImageId() {
		return _referenceImageId;
	}

	public void setReferenceImageId(long referenceImageId) {
		_referenceImageId = referenceImageId;
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

	public double getVersion() {
		return _version;
	}

	public void setVersion(double version) {
		_version = version;
	}

	public int getImageType() {
		return _imageType;
	}

	public void setImageType(int imageType) {
		_imageType = imageType;
	}

	public String getImage() {
		return _image;
	}

	public void setImage(String image) {
		_image = image;
	}

	private String _uuid;
	private long _referenceImageId;
	private long _groupId;
	private long _companyId;
	private String _referenceId;
	private double _version;
	private int _imageType;
	private String _image;
}