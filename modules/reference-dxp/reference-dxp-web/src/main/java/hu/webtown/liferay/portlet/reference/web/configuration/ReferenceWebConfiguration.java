
package hu.webtown.liferay.portlet.reference.web.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
    category = "web-experience"
)
@Meta.OCD(
    id = "hu.webtown.liferay.portlet.reference.web.configuration.ReferenceWebConfiguration",
    localization = "content/Language",
    name = "reference.web.configuration.name"
)
public @interface ReferenceWebConfiguration {

    static final String DEFAULT_DISPLAY_VIEW = "descriptive";

    @Meta.AD(
        deflt = "descriptive",
        name = "default-display-view",
        required = false
    )
    public String defaultDisplayView();

    @Meta.AD(
        deflt = "icon|descriptive|list",
        name = "display-views",
        required = false
    )
    public String[] displayViews();
}
