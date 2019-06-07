package hu.webtown.liferay.portlet.reference.web.internal.servlet.taglib.ui;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorEntry;

@Component(
    property = "form.navigator.entry.order:Integer=50",
    service = FormNavigatorEntry.class
)
public class ReferenceImagesFormNavigatorEntry
    extends BaseReferenceFormNavigatorEntry {

    @Override
    public String getKey() {

        return "images";
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

        return "/reference/images.jsp";
    }

}
