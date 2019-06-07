package hu.webtown.liferay.portlet.reference.web.internal.servlet.taglib.ui;

import org.osgi.service.component.annotations.Component;

import com.liferay.frontend.taglib.form.navigator.constants.FormNavigatorContextConstants;
import com.liferay.frontend.taglib.form.navigator.context.FormNavigatorContextProvider;

import hu.webtown.liferay.portlet.reference.model.Reference;

@Component(
    property = FormNavigatorContextConstants.FORM_NAVIGATOR_ID + "=" + "reference.form",
    service = FormNavigatorContextProvider.class
)
public class ReferenceFormNavigatorContextProvider
    implements FormNavigatorContextProvider<Reference> {

    @Override
    public String getContext(Reference formModelBean) {

        if (formModelBean != null && formModelBean.getId() > 0) {

            return "update";
        }

        return "add";
    }
}
