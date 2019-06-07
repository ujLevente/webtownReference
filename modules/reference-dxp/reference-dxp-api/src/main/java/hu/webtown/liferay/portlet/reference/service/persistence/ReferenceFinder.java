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

package hu.webtown.liferay.portlet.reference.service.persistence;

import aQute.bnd.annotation.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface ReferenceFinder {
	public int countByC_G_R_V_N_S_D_R_S(long companyId, long groupId,
		String referenceId, Double version, String name,
		String shortDescription, String description,
		java.util.Date realizationDateGT, java.util.Date realizationDateLT,
		int status, boolean andOperator);

	public int countByC_G_R_V_N_S_D_R_S(long companyId, long groupId,
		String[] referenceIds, Double version, String[] names,
		String[] shortDescriptions, String[] descriptions,
		java.util.Date realizationDateGT, java.util.Date realizationDateLT,
		int status, boolean andOperator);

	public int countByGroupId(long groupId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition<hu.webtown.liferay.portlet.reference.model.Reference> queryDefinition);

	public int countByKeywords(long companyId, long groupId, String keywords,
		Double version, java.util.Date realizationDateGT,
		java.util.Date realizationDateLT, int status);

	public int filterCountByC_G_R_V_N_S_D_R_S(long companyId, long groupId,
		String referenceId, Double version, String name,
		String shortDescription, String description,
		java.util.Date realizationDateGT, java.util.Date realizationDateLT,
		int status, boolean andOperator);

	public int filterCountByC_G_R_V_N_S_D_R_S(long companyId, long groupId,
		String[] referenceIds, Double version, String[] names,
		String[] shortDescriptions, String[] descriptions,
		java.util.Date realizationDateGT, java.util.Date realizationDateLT,
		int status, boolean andOperator);

	public int filterCountByGroupId(long groupId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition<hu.webtown.liferay.portlet.reference.model.Reference> queryDefinition);

	public int filterCountByKeywords(long companyId, long groupId,
		String keywords, Double version, java.util.Date realizationDateGT,
		java.util.Date realizationDateLT, int status);

	public java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> filterFindByC_G_R_V_N_S_D_R_S(
		long companyId, long groupId, String referenceId, Double version,
		String name, String shortDescription, String description,
		java.util.Date realizationDateGT, java.util.Date realizationDateLT,
		int status, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.Reference> orderByComparator);

	public java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> filterFindByC_G_R_V_N_S_D_R_S(
		long companyId, long groupId, String[] referenceIds, Double version,
		String[] names, String[] shortDescriptions, String[] descriptions,
		java.util.Date realizationDateGT, java.util.Date realizationDateLT,
		int status, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.Reference> orderByComparator);

	public java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> filterFindByGroupId(
		long groupId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition<hu.webtown.liferay.portlet.reference.model.Reference> queryDefinition);

	public java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> filterFindByKeywords(
		long companyId, long groupId, String keywords, Double version,
		java.util.Date realizationDateGT, java.util.Date realizationDateLT,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.Reference> orderByComparator);

	public java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> findByC_G_R_V_N_S_D_R_S(
		long companyId, long groupId, String referenceId, Double version,
		String name, String shortDescription, String description,
		java.util.Date realizationDateGT, java.util.Date realizationDateLT,
		int status, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.Reference> orderByComparator);

	public java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> findByC_G_R_V_N_S_D_R_S(
		long companyId, long groupId, String[] referenceIds, Double version,
		String[] names, String[] shortDescriptions, String[] descriptions,
		java.util.Date realizationDateGT, java.util.Date realizationDateLT,
		int status, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.Reference> orderByComparator);

	public java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> findByGroupId(
		long groupId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition<hu.webtown.liferay.portlet.reference.model.Reference> queryDefinition);

	public java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> findByKeywords(
		long companyId, long groupId, String keywords, Double version,
		java.util.Date realizationDateGT, java.util.Date realizationDateLT,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.Reference> orderByComparator);

	public java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> findByRealizationDate(
		java.util.Date realizationDateLT, java.util.Date realizationDateGT);
}