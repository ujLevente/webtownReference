
package hu.webtown.liferay.portlet.reference.internal.security.permission.resource;

import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.StagedModelPermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.WorkflowedModelPermissionLogic;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.workflow.permission.WorkflowPermission;

import hu.webtown.liferay.portlet.reference.constants.ReferenceConstants;
import hu.webtown.liferay.portlet.reference.constants.ReferencePortletKeys;
import hu.webtown.liferay.portlet.reference.model.Reference;
import hu.webtown.liferay.portlet.reference.service.ReferenceLocalService;

@Component(immediate = true)
public class ReferenceModelResourcePermissionRegistrar {

    private ServiceRegistration<ModelResourcePermission> serviceRegistration;

    @org.osgi.service.component.annotations.Reference
    private GroupLocalService groupLocalService;

    @org.osgi.service.component.annotations.Reference
    private ReferenceLocalService referenceLocalService;

    @org.osgi.service.component.annotations.Reference(
        target = "(resource.name=" + ReferenceConstants.RESOURCE_NAME + ")"
    )
    private PortletResourcePermission portletResourcePermission;

    @org.osgi.service.component.annotations.Reference
    private StagingPermission stagingPermission;

    @org.osgi.service.component.annotations.Reference
    private WorkflowPermission workflowPermission;

    @Activate
    public void activate(BundleContext bundleContext) {

        Dictionary<String, Object> properties = new HashMapDictionary<>();

        properties.put("model.class.name", Reference.class.getName());

        serviceRegistration = bundleContext.registerService(
            ModelResourcePermission.class,
            ModelResourcePermissionFactory.create(
                Reference.class, Reference::getResourcePrimKey, classPK -> {
                    Reference reference =
                        referenceLocalService.fetchLatestReference(classPK);

                    if (reference != null) {
                        return reference;
                    }

                    return referenceLocalService.getReference(classPK);
                }, portletResourcePermission,
                (modelResourcePermission, consumer) -> {
                    consumer.accept(
                        new StagedModelPermissionLogic<>(
                            stagingPermission,
                            ReferencePortletKeys.REFERENCE_DXP_PORTLET,
                            Reference::getResourcePrimKey));
                    consumer.accept(
                        new WorkflowedModelPermissionLogic<>(
                            workflowPermission, modelResourcePermission,
                            groupLocalService, Reference::getId));
                }),
            properties);
    }

    @Deactivate
    public void deactivate() {

        serviceRegistration.unregister();
    }
}
