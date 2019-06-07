
package hu.webtown.liferay.portlet.reference.internal.workflow;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.BaseWorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandler;

import hu.webtown.liferay.portlet.reference.model.Reference;
import hu.webtown.liferay.portlet.reference.service.ReferenceLocalService;

@Component(
    property = "model.class.name=hu.webtown.liferay.portlet.reference.model.Reference",
    service = WorkflowHandler.class
)
public class ReferenceWorkflowHandler extends BaseWorkflowHandler<Reference> {

    private ReferenceLocalService referenceLocalService;

    @Override
    public String getClassName() {

        return Reference.class.getName();
    }

    @Override
    public String getType(Locale locale) {

        return ResourceActionsUtil.getModelResource(locale, getClassName());
    }

    @Override
    public boolean isVisible() {

        return false;
    }

    @Override
    public Reference updateStatus(
        int status, Map<String, Serializable> workflowContext)
        throws PortalException {

        long userId = GetterUtil.getLong(
            (String) workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));
        long classPK = GetterUtil.getLong(
            (String) workflowContext.get(
                WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

        Reference reference = referenceLocalService.getReference(classPK);

        ServiceContext serviceContext =
            (ServiceContext) workflowContext.get("serviceContext");

        return referenceLocalService.updateStatus(
            userId, reference, status, workflowContext, serviceContext);
    }

    @org.osgi.service.component.annotations.Reference(unbind = "-")
    protected void setReferenceLocalService(
        ReferenceLocalService referenceLocalService) {

        this.referenceLocalService = referenceLocalService;
    }

}
