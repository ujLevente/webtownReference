package hu.webtown.liferay.portlet.reference.web.internal.servlet.taglib.ui;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorEntry;

@Component(
    property = "form.navigator.entry.order:Integer=30",
    service = FormNavigatorEntry.class
)
public class ReferenceScheduleFormNavigatorEntry
    extends BaseReferenceFormNavigatorEntry {

    @Override
    public String getKey() {

        return "schedule";
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

        return "/reference/schedule.jsp";
    }

}
