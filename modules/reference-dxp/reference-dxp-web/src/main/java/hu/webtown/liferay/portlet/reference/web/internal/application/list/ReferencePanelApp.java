package hu.webtown.liferay.portlet.reference.web.internal.application.list;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.model.Portlet;

import hu.webtown.liferay.portlet.reference.constants.ReferencePortletKeys;

@Component(
    immediate = true,
    property = {
        "panel.app.order:Integer=101",
        "panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT
    },
    service = PanelApp.class
)
public class ReferencePanelApp extends BasePanelApp {

    @Override
    public String getPortletId() {
    
        return ReferencePortletKeys.REFERENCE_DXP_PORTLET;
    }
    
    @Override
    @Reference(
        target = "(javax.portlet.name=" + ReferencePortletKeys.REFERENCE_DXP_PORTLET + ")",
        unbind = "-"
    )
    public void setPortlet(Portlet portlet) {
    
        super.setPortlet(portlet);
    }
}
