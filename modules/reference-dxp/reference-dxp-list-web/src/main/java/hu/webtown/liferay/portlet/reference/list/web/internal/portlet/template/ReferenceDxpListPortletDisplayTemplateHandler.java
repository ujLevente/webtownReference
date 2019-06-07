package hu.webtown.liferay.portlet.reference.list.web.internal.portlet.template;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.display.template.PortletDisplayTemplateConstants;

import hu.webtown.liferay.portlet.reference.list.web.constants.ReferenceDxpListPortletKeys;
import hu.webtown.liferay.portlet.reference.model.Reference;
import hu.webtown.liferay.portlet.reference.service.ReferenceImageLocalService;
import hu.webtown.liferay.portlet.reference.service.ReferenceLocalService;
import hu.webtown.liferay.portlet.reference.service.ReferenceService;

@Component(
    immediate = true,
    property = {
        "javax.portlet.name=" + ReferenceDxpListPortletKeys.REFERENCE_DXP_LIST_PORTLET
    },
    service = TemplateHandler.class
)
public class ReferenceDxpListPortletDisplayTemplateHandler
    extends BasePortletDisplayTemplateHandler {

    @Override
    public String getClassName() {

        return Reference.class.getName();
    }

    @Override
    public String getName(Locale locale) {

        ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
            "content.Language", locale, getClass());

        String portletTitle = PortalUtil.getPortletTitle(
            ReferenceDxpListPortletKeys.REFERENCE_DXP_LIST_PORTLET,
            resourceBundle);

        return portletTitle.concat(StringPool.SPACE).concat(
            LanguageUtil.get(locale, "template"));
    }

    @Override
    public String getResourceName() {

        return ReferenceDxpListPortletKeys.REFERENCE_DXP_LIST_PORTLET;
    }
    
    @Override
    public Map<String, TemplateVariableGroup> getTemplateVariableGroups(
        long classPK, String language, Locale locale)
        throws Exception {

        Map<String, TemplateVariableGroup> templateVariableGroups =
            super.getTemplateVariableGroups(classPK, language, locale);

        String[] restrictedVariables = getRestrictedVariables(language);

        // Fields

        TemplateVariableGroup fieldsTemplateVariableGroup =
            templateVariableGroups.get("fields");

        fieldsTemplateVariableGroup.empty();

        fieldsTemplateVariableGroup.addCollectionVariable(
            "references", List.class, PortletDisplayTemplateConstants.ENTRIES,
            "reference", Reference.class, "curReference", "getName()");
        fieldsTemplateVariableGroup.addVariable(
            "reference", Reference.class, PortletDisplayTemplateConstants.ENTRY,
            "getName()");

        // Services

        TemplateVariableGroup referencesServicesTemplateVariableGroup =
            new TemplateVariableGroup(
                "reference-services", restrictedVariables);

        referencesServicesTemplateVariableGroup.setAutocompleteEnabled(false);

        referencesServicesTemplateVariableGroup.addServiceLocatorVariables(
            ReferenceLocalService.class, ReferenceService.class,
            ReferenceImageLocalService.class);

        templateVariableGroups.put(
            referencesServicesTemplateVariableGroup.getLabel(),
            referencesServicesTemplateVariableGroup);

        return templateVariableGroups;
    }

}
