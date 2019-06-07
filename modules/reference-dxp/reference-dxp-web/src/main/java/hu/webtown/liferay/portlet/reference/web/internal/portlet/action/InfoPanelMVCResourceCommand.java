
package hu.webtown.liferay.portlet.reference.web.internal.portlet.action;

import java.util.List;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;

import hu.webtown.liferay.portlet.reference.constants.ReferencePortletKeys;
import hu.webtown.liferay.portlet.reference.constants.ReferenceWebKeys;
import hu.webtown.liferay.portlet.reference.model.Reference;

@Component(
    immediate = true,
    property = {
        "javax.portlet.name=" + ReferencePortletKeys.REFERENCE_DXP_PORTLET,
        "mvc.command.name=/reference/info_panel"
    },
    service = MVCResourceCommand.class
)
public class InfoPanelMVCResourceCommand extends BaseMVCResourceCommand {

    @Override
    protected void doServeResource(
        ResourceRequest resourceRequest, ResourceResponse resourceResponse)
        throws Exception {

        List<Reference> references = ActionUtil.getReferences(resourceRequest);

        resourceRequest.setAttribute(ReferenceWebKeys.REFERENCES, references);

        include(resourceRequest, resourceResponse, "/info_panel.jsp");
    }

}
