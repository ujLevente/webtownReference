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

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import hu.webtown.liferay.portlet.reference.model.ReferenceImage;
import hu.webtown.liferay.portlet.reference.model.ReferenceImageModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the ReferenceImage service. Represents a row in the &quot;ReferenceImage&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link ReferenceImageModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ReferenceImageImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReferenceImageImpl
 * @see ReferenceImage
 * @see ReferenceImageModel
 * @generated
 */
@ProviderType
public class ReferenceImageModelImpl extends BaseModelImpl<ReferenceImage>
	implements ReferenceImageModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a reference image model instance should use the {@link ReferenceImage} interface instead.
	 */
	public static final String TABLE_NAME = "ReferenceImage";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "referenceImageId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "referenceId", Types.VARCHAR },
			{ "version", Types.DOUBLE },
			{ "imageType", Types.INTEGER },
			{ "image", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("referenceImageId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("referenceId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("version", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("imageType", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("image", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table ReferenceImage (uuid_ VARCHAR(75) null,referenceImageId LONG not null primary key,groupId LONG,companyId LONG,referenceId VARCHAR(75) null,version DOUBLE,imageType INTEGER,image VARCHAR(2000) null)";
	public static final String TABLE_SQL_DROP = "drop table ReferenceImage";
	public static final String ORDER_BY_JPQL = " ORDER BY referenceImage.referenceImageId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY ReferenceImage.referenceImageId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(hu.webtown.liferay.portlet.reference.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.hu.webtown.liferay.portlet.reference.model.ReferenceImage"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(hu.webtown.liferay.portlet.reference.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.hu.webtown.liferay.portlet.reference.model.ReferenceImage"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(hu.webtown.liferay.portlet.reference.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.hu.webtown.liferay.portlet.reference.model.ReferenceImage"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long GROUPID_COLUMN_BITMASK = 2L;
	public static final long IMAGETYPE_COLUMN_BITMASK = 4L;
	public static final long REFERENCEID_COLUMN_BITMASK = 8L;
	public static final long UUID_COLUMN_BITMASK = 16L;
	public static final long VERSION_COLUMN_BITMASK = 32L;
	public static final long REFERENCEIMAGEID_COLUMN_BITMASK = 64L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(hu.webtown.liferay.portlet.reference.service.util.ServiceProps.get(
				"lock.expiration.time.hu.webtown.liferay.portlet.reference.model.ReferenceImage"));

	public ReferenceImageModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _referenceImageId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setReferenceImageId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _referenceImageId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

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
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@Override
	public long getReferenceImageId() {
		return _referenceImageId;
	}

	@Override
	public void setReferenceImageId(long referenceImageId) {
		_referenceImageId = referenceImageId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@Override
	public String getReferenceId() {
		if (_referenceId == null) {
			return "";
		}
		else {
			return _referenceId;
		}
	}

	@Override
	public void setReferenceId(String referenceId) {
		_columnBitmask |= REFERENCEID_COLUMN_BITMASK;

		if (_originalReferenceId == null) {
			_originalReferenceId = _referenceId;
		}

		_referenceId = referenceId;
	}

	public String getOriginalReferenceId() {
		return GetterUtil.getString(_originalReferenceId);
	}

	@Override
	public double getVersion() {
		return _version;
	}

	@Override
	public void setVersion(double version) {
		_columnBitmask |= VERSION_COLUMN_BITMASK;

		if (!_setOriginalVersion) {
			_setOriginalVersion = true;

			_originalVersion = _version;
		}

		_version = version;
	}

	public double getOriginalVersion() {
		return _originalVersion;
	}

	@Override
	public int getImageType() {
		return _imageType;
	}

	@Override
	public void setImageType(int imageType) {
		_columnBitmask |= IMAGETYPE_COLUMN_BITMASK;

		if (!_setOriginalImageType) {
			_setOriginalImageType = true;

			_originalImageType = _imageType;
		}

		_imageType = imageType;
	}

	public int getOriginalImageType() {
		return _originalImageType;
	}

	@Override
	public String getImage() {
		if (_image == null) {
			return "";
		}
		else {
			return _image;
		}
	}

	@Override
	public void setImage(String image) {
		_image = image;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			ReferenceImage.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ReferenceImage toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (ReferenceImage)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ReferenceImageImpl referenceImageImpl = new ReferenceImageImpl();

		referenceImageImpl.setUuid(getUuid());
		referenceImageImpl.setReferenceImageId(getReferenceImageId());
		referenceImageImpl.setGroupId(getGroupId());
		referenceImageImpl.setCompanyId(getCompanyId());
		referenceImageImpl.setReferenceId(getReferenceId());
		referenceImageImpl.setVersion(getVersion());
		referenceImageImpl.setImageType(getImageType());
		referenceImageImpl.setImage(getImage());

		referenceImageImpl.resetOriginalValues();

		return referenceImageImpl;
	}

	@Override
	public int compareTo(ReferenceImage referenceImage) {
		long primaryKey = referenceImage.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ReferenceImage)) {
			return false;
		}

		ReferenceImage referenceImage = (ReferenceImage)obj;

		long primaryKey = referenceImage.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		ReferenceImageModelImpl referenceImageModelImpl = this;

		referenceImageModelImpl._originalUuid = referenceImageModelImpl._uuid;

		referenceImageModelImpl._originalGroupId = referenceImageModelImpl._groupId;

		referenceImageModelImpl._setOriginalGroupId = false;

		referenceImageModelImpl._originalCompanyId = referenceImageModelImpl._companyId;

		referenceImageModelImpl._setOriginalCompanyId = false;

		referenceImageModelImpl._originalReferenceId = referenceImageModelImpl._referenceId;

		referenceImageModelImpl._originalVersion = referenceImageModelImpl._version;

		referenceImageModelImpl._setOriginalVersion = false;

		referenceImageModelImpl._originalImageType = referenceImageModelImpl._imageType;

		referenceImageModelImpl._setOriginalImageType = false;

		referenceImageModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ReferenceImage> toCacheModel() {
		ReferenceImageCacheModel referenceImageCacheModel = new ReferenceImageCacheModel();

		referenceImageCacheModel.uuid = getUuid();

		String uuid = referenceImageCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			referenceImageCacheModel.uuid = null;
		}

		referenceImageCacheModel.referenceImageId = getReferenceImageId();

		referenceImageCacheModel.groupId = getGroupId();

		referenceImageCacheModel.companyId = getCompanyId();

		referenceImageCacheModel.referenceId = getReferenceId();

		String referenceId = referenceImageCacheModel.referenceId;

		if ((referenceId != null) && (referenceId.length() == 0)) {
			referenceImageCacheModel.referenceId = null;
		}

		referenceImageCacheModel.version = getVersion();

		referenceImageCacheModel.imageType = getImageType();

		referenceImageCacheModel.image = getImage();

		String image = referenceImageCacheModel.image;

		if ((image != null) && (image.length() == 0)) {
			referenceImageCacheModel.image = null;
		}

		return referenceImageCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", referenceImageId=");
		sb.append(getReferenceImageId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", referenceId=");
		sb.append(getReferenceId());
		sb.append(", version=");
		sb.append(getVersion());
		sb.append(", imageType=");
		sb.append(getImageType());
		sb.append(", image=");
		sb.append(getImage());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(28);

		sb.append("<model><model-name>");
		sb.append("hu.webtown.liferay.portlet.reference.model.ReferenceImage");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>referenceImageId</column-name><column-value><![CDATA[");
		sb.append(getReferenceImageId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>referenceId</column-name><column-value><![CDATA[");
		sb.append(getReferenceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>version</column-name><column-value><![CDATA[");
		sb.append(getVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>imageType</column-name><column-value><![CDATA[");
		sb.append(getImageType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>image</column-name><column-value><![CDATA[");
		sb.append(getImage());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = ReferenceImage.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			ReferenceImage.class, ModelWrapper.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _referenceImageId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private String _referenceId;
	private String _originalReferenceId;
	private double _version;
	private double _originalVersion;
	private boolean _setOriginalVersion;
	private int _imageType;
	private int _originalImageType;
	private boolean _setOriginalImageType;
	private String _image;
	private long _columnBitmask;
	private ReferenceImage _escapedModel;
}