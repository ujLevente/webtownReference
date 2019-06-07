package hu.webtown.liferay.portlet.reference.web.internal.servlet.taglib.ui;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorEntry;

@Component(
    property = "form.navigator.entry.order:Integer=20",
    service = FormNavigatorEntry.class
)
public class ReferenceRelatedAssetsFormNavigatorEntry
    extends BaseReferenceFormNavigatorEntry {

    @Override
    public String getKey() {

        return "related-assets";
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

        return "/reference/related_assets.jsp";
    }

}
