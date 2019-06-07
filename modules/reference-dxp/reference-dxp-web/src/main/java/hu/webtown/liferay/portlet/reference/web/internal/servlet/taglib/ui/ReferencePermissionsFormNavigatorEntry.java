
package hu.webtown.liferay.portlet.reference.web.internal.servlet.taglib.ui;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorEntry;

@Component(
    property = "form.navigator.entry.order:Integer=10",
    service = FormNavigatorEntry.class
)
public class ReferencePermissionsFormNavigatorEntry
    extends BaseReferenceFormNavigatorEntry {

    @Override
    public String getKey() {

        return "permissions";
    }

    @Override
    public boolean isVisible(
        User user,
        hu.webtown.liferay.portlet.reference.model.Reference formModelBean) {

        return formModelBean == null || formModelBean.getId() <= 0;
    }

    @Override
    @Reference(
        target = "(osgi.web.symbolicname=reference.dxp.web)",
        unbind = "-"
    )
    public void setServletContext(ServletContext servletContext) {

        super.setServletContext(servletContext);
    }

    @Override
    protected String getJspPath() {

        return "/reference/permissions.jsp";
    }

}
