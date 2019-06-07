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

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import aQute.bnd.annotation.ProviderType;
import hu.webtown.liferay.portlet.reference.model.Reference;
import hu.webtown.liferay.portlet.reference.model.ReferenceResource;
import hu.webtown.liferay.portlet.reference.service.ReferenceLocalServiceUtil;
import hu.webtown.liferay.portlet.reference.service.ReferenceResourceLocalServiceUtil;

/**
 * The extended model implementation for the Reference service. Represents a row
 * in the &quot;Reference&quot; database table, with each column mapped to a
 * property of this class. <p> Helper methods and all application logic should
 * be put in this class. Whenever methods are added, rerun ServiceBuilder to
 * copy their definitions into the
 * {@link hu.webtown.liferay.portlet.reference.model.Reference} interface. </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class ReferenceImpl extends ReferenceBaseImpl {

    /*
     * NOTE FOR DEVELOPERS: Never reference this class directly. All methods
     * that expect a reference model instance should use the {@link
     * hu.webtown.liferay.portlet.reference.model.Reference} interface instead.
     */
    public ReferenceImpl() {
    }

    @Override
    public ReferenceResource getReferenceResource()
        throws PortalException {

        return ReferenceResourceLocalServiceUtil.getReferenceResource(
            getResourcePrimKey());
    }

    @Override
    public String getReferenceResourceUuid()
        throws PortalException {

        ReferenceResource referenceResource = getReferenceResource();

        return referenceResource.getUuid();
    }

    @Override
    public StagedModelType getStagedModelType() {

        return new StagedModelType(Reference.class);
    }

    @Override
    public long getTrashEntryClassPK() {

        // getTrashEntryClassPK returns with primary key by default, so change
        // it to resource prim key
        
        return getResourcePrimKey();
    }

    @Override
    public boolean hasApprovedVersion() {

        Reference reference = ReferenceLocalServiceUtil.fetchLatestReference(
            getGroupId(), getReferenceId(), WorkflowConstants.STATUS_APPROVED);

        if (reference == null) {
            return false;
        }

        return true;
    }
}
