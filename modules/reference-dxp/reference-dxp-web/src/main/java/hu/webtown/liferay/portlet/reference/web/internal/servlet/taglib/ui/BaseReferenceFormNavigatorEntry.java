
package hu.webtown.liferay.portlet.reference.web.internal.servlet.taglib.ui;

import java.util.Locale;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.taglib.ui.BaseJSPFormNavigatorEntry;

import hu.webtown.liferay.portlet.reference.model.Reference;

public abstract class BaseReferenceFormNavigatorEntry
    extends BaseJSPFormNavigatorEntry<Reference> {

    @Override
    public String getCategoryKey() {

        return StringPool.BLANK;
    }

    @Override
    public String getFormNavigatorId() {

        return "reference.form";
    }

    @Override
    public String getLabel(Locale locale) {

        return LanguageUtil.get(locale, getKey());
    }

}
