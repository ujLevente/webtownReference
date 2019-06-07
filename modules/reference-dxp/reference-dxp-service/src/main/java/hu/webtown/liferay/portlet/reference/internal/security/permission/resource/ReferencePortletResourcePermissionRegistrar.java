
package hu.webtown.liferay.portlet.reference.internal.security.permission.resource;

import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.StagedPortletPermissionLogic;
import com.liferay.portal.kernel.util.HashMapDictionary;

import hu.webtown.liferay.portlet.reference.constants.ReferenceConstants;
import hu.webtown.liferay.portlet.reference.constants.ReferencePortletKeys;

@Component(immediate = true)
public class ReferencePortletResourcePermissionRegistrar {

    private ServiceRegistration<PortletResourcePermission> serviceRegistration;

    @Reference
    private StagingPermission stagingPermission;

    @Activate
    public void activate(BundleContext bundleContext) {

        Dictionary<String, Object> properties = new HashMapDictionary<>();

        properties.put("resource.name", ReferenceConstants.RESOURCE_NAME);

        serviceRegistration = bundleContext.registerService(
            PortletResourcePermission.class,
            PortletResourcePermissionFactory.create(
                ReferenceConstants.RESOURCE_NAME,
                new StagedPortletPermissionLogic(
                    stagingPermission,
                    ReferencePortletKeys.REFERENCE_DXP_PORTLET)),
            properties);
    }

    @Deactivate
    public void deactivate() {

        serviceRegistration.unregister();
    }

}
