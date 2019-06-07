
package hu.webtown.liferay.portlet.reference.web.internal.portlet;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.BasePortletProvider;
import com.liferay.portal.kernel.portlet.EditPortletProvider;

import hu.webtown.liferay.portlet.reference.constants.ReferencePortletKeys;

@Component(
    immediate = true,
    property = { "model.class.name=hu.webtown.liferay.portlet.reference.model.Reference" },
    service = EditPortletProvider.class
)
public class ReferenceEditPortletProvider extends BasePortletProvider
    implements EditPortletProvider {

    @Override
    public String getPortletName() {

        return ReferencePortletKeys.REFERENCE_DXP_PORTLET;
    }

}
