create table Reference (
	uuid_ VARCHAR(75) null,
	id_ LONG not null primary key,
	resourcePrimKey LONG,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	referenceId VARCHAR(75) null,
	version DOUBLE,
	name VARCHAR(255) null,
	urlTitle VARCHAR(255) null,
	shortDescription TEXT null,
	description TEXT null,
	realizationDate DATE null,
	emphasized BOOLEAN,
	overlayText VARCHAR(255) null,
	overlayUrl VARCHAR(255) null,
	lastPublishDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table ReferenceImage (
	uuid_ VARCHAR(75) null,
	referenceImageId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	referenceId VARCHAR(75) null,
	version DOUBLE,
	imageType INTEGER,
	image VARCHAR(2000) null
);

create table ReferenceResource (
	uuid_ VARCHAR(75) null,
	resourcePrimKey LONG not null primary key,
	groupId LONG,
	companyId LONG,
	referenceId VARCHAR(75) null
);