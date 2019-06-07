package hu.webtown.liferay.portlet.reference.list.web.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import hu.webtown.liferay.portlet.reference.list.web.constants.ReferenceDxpListPortletKeys;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author imiszucs
 */
@Component(
	immediate = true,
	property = {
        "com.liferay.portlet.add-default-resource=true",
        "com.liferay.portlet.css-class-wrapper=portlet-asset-publisher",
        "com.liferay.portlet.display-category=category.cms",
        "com.liferay.portlet.display-category=category.highlighted",
        "com.liferay.portlet.instanceable=true",
        "com.liferay.portlet.preferences-owned-by-group=true",
        "com.liferay.portlet.private-request-attributes=false",
        "com.liferay.portlet.private-session-attributes=false",
        "com.liferay.portlet.render-weight=50",
        "com.liferay.portlet.scopeable=false",
        "com.liferay.portlet.use-default-template=true",        
        "javax.portlet.display-name=Reference List Portlet",
        "javax.portlet.expiration-cache=0",
        "javax.portlet.init-param.template-path=/",
        "javax.portlet.init-param.view-template=/view.jsp",
        "javax.portlet.name=" + ReferenceDxpListPortletKeys.REFERENCE_DXP_LIST_PORTLET,
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=guest,power-user,user",
        "javax.portlet.supports.mime-type=text/html"
	},
	service = { ReferenceDxpListPortlet.class, Portlet.class }
)
public class ReferenceDxpListPortlet extends MVCPortlet {
}