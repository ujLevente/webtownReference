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

package hu.webtown.liferay.portlet.reference.service.impl;

import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;
import hu.webtown.liferay.portlet.reference.model.ReferenceResource;
import hu.webtown.liferay.portlet.reference.service.base.ReferenceResourceLocalServiceBaseImpl;

/**
 * The implementation of the reference resource local service. <p> All custom
 * service methods should be put in this class. Whenever methods are added,
 * rerun ServiceBuilder to copy their definitions into the
 * {@link hu.webtown.liferay.portlet.reference.service.ReferenceResourceLocalService}
 * interface. <p> This is a local service. Methods of this service will not have
 * security checks based on the propagated JAAS credentials because this service
 * can only be accessed from within the same VM. </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReferenceResourceLocalServiceBaseImpl
 * @see hu.webtown.liferay.portlet.reference.service.ReferenceResourceLocalServiceUtil
 */
@ProviderType
public class ReferenceResourceLocalServiceImpl
    extends ReferenceResourceLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS: Never reference this class directly. Always use
     * {@link hu.webtown.liferay.portlet.reference.service.
     * ReferenceResourceLocalServiceUtil} to access the reference resource local
     * service.
     */

    @Override
    public void deleteReferenceResource(long groupId, String referenceId)
        throws PortalException {

        referenceResourcePersistence.removeByG_R(groupId, referenceId);
    }

    @Override
    public ReferenceResource fetchReferenceResource(
        long groupId, String referenceId) {

        return referenceResourcePersistence.fetchByG_R(groupId, referenceId);
    }

    @Override
    public ReferenceResource fetchReferenceResource(String uuid, long groupId) {

        return referenceResourcePersistence.fetchByUUID_G(uuid, groupId);
    }

    @Override
    public long getReferenceResourcePrimKey(long groupId, String referenceId) {

        return getReferenceResourcePrimKey(null, groupId, referenceId);
    }

    @Override
    public long getReferenceResourcePrimKey(
        String uuid, long groupId, String referenceId) {

        ReferenceResource referenceResource = null;

        if (Validator.isNotNull(uuid)) {

            referenceResource =
                referenceResourcePersistence.fetchByUUID_G(uuid, groupId);
        }

        if (referenceResource == null) {

            referenceResource =
                referenceResourcePersistence.fetchByG_R(groupId, referenceId);
        }

        if (referenceResource == null) {

            long referenceResourcePrimKey = counterLocalService.increment();

            referenceResource =
                referenceResourcePersistence.create(referenceResourcePrimKey);

            if (Validator.isNotNull(uuid)) {

                referenceResource.setUuid(uuid);
            }

            referenceResource.setGroupId(groupId);
            referenceResource.setReferenceId(referenceId);

            referenceResourcePersistence.update(referenceResource);
        }

        return referenceResource.getResourcePrimKey();
    }

    @Override
    public List<ReferenceResource> getReferenceResources(long groupId) {

        return referenceResourcePersistence.findByGroupId(groupId);
    }
}
