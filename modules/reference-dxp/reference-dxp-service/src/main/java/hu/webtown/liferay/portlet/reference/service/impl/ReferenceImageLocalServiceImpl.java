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

import aQute.bnd.annotation.ProviderType;
import hu.webtown.liferay.portlet.reference.model.ReferenceImage;
import hu.webtown.liferay.portlet.reference.service.base.ReferenceImageLocalServiceBaseImpl;

/**
 * The implementation of the reference image local service. <p> All custom
 * service methods should be put in this class. Whenever methods are added,
 * rerun ServiceBuilder to copy their definitions into the
 * {@link hu.webtown.liferay.portlet.reference.service.ReferenceImageLocalService}
 * interface. <p> This is a local service. Methods of this service will not have
 * security checks based on the propagated JAAS credentials because this service
 * can only be accessed from within the same VM. </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReferenceImageLocalServiceBaseImpl
 * @see hu.webtown.liferay.portlet.reference.service.ReferenceImageLocalServiceUtil
 */
@ProviderType
public class ReferenceImageLocalServiceImpl
    extends ReferenceImageLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS: Never reference this class directly. Always use
     * {@link hu.webtown.liferay.portlet.reference.service.
     * ReferenceImageLocalServiceUtil} to access the reference image local
     * service.
     */

    @Override
    public ReferenceImage addReferenceImage(
        long groupId, String referenceId, double version,
        int referenceImageType, String image) {

        long referenceImageId =
            counterLocalService.increment();

        ReferenceImage model =
            referenceImagePersistence.create(referenceImageId);

        model.setGroupId(groupId);
        model.setReferenceId(referenceId);
        model.setVersion(version);
        model.setImageType(referenceImageType);
        model.setImage(image);

        model = referenceImagePersistence.update(model);

        return model;
    }

    @Override
    public void deleteReferenceImages(
        long groupId, String referenceId, double version) {

        referenceImagePersistence.removeByG_R_V(groupId, referenceId, version);
    }

    @Override
    public void deleteReferenceImages(
        long groupId, String referenceId, double version,
        int referenceImageType) {

        referenceImagePersistence.removeByG_R_V_I(
            groupId, referenceId, version, referenceImageType);
    }

    @Override
    public List<ReferenceImage> getReferenceImages(
        long groupId, String referenceId, double version) {

        return referenceImagePersistence.findByG_R_V(
            groupId, referenceId, version);
    }

    @Override
    public List<ReferenceImage> getReferenceImages(
        long groupId, String referenceId, double version,
        int referenceImageType) {

        return referenceImagePersistence.findByG_R_V_I(
            groupId, referenceId, version, referenceImageType);
    }
}
