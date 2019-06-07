
package hu.webtown.liferay.portlet.reference.web.internal.custom.attributes;

import org.osgi.service.component.annotations.Component;

import com.liferay.expando.kernel.model.BaseCustomAttributesDisplay;
import com.liferay.expando.kernel.model.CustomAttributesDisplay;

import hu.webtown.liferay.portlet.reference.constants.ReferencePortletKeys;
import hu.webtown.liferay.portlet.reference.model.Reference;

@Component(
    immediate = true,
    property = "javax.portlet.name=" + ReferencePortletKeys.REFERENCE_DXP_PORTLET,
    service = CustomAttributesDisplay.class
)
public class ReferenceCustomAttributesDisplay
    extends BaseCustomAttributesDisplay {

    @Override
    public String getClassName() {

        return Reference.class.getName();
    }

}
