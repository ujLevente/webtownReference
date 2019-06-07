create index IX_AF5EDF3D on Reference (companyId, status);
create index IX_1524FE77 on Reference (companyId, version, status);
create index IX_B9414363 on Reference (groupId, referenceId[$COLUMN_LENGTH:75$], status);
create unique index IX_966E012B on Reference (groupId, referenceId[$COLUMN_LENGTH:75$], version);
create index IX_3D4BED7F on Reference (groupId, status);
create index IX_ED0BC41C on Reference (groupId, urlTitle[$COLUMN_LENGTH:255$], status);
create index IX_DECB06D3 on Reference (groupId, userId);
create index IX_2D21DD30 on Reference (resourcePrimKey, status);
create index IX_A007BE5 on Reference (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_34D0B7A7 on Reference (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_B38D45DF on ReferenceImage (groupId, referenceId[$COLUMN_LENGTH:75$], version, imageType);
create index IX_FA75A52A on ReferenceImage (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F4996E2C on ReferenceImage (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_F48674AB on ReferenceResource (groupId, referenceId[$COLUMN_LENGTH:75$]);
create index IX_BCC9E613 on ReferenceResource (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_B5AAD555 on ReferenceResource (uuid_[$COLUMN_LENGTH:75$], groupId);