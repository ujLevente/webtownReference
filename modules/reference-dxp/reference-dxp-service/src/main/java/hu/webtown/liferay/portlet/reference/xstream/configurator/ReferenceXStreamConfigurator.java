
package hu.webtown.liferay.portlet.reference.xstream.configurator;

import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import com.liferay.exportimport.kernel.xstream.XStreamAlias;
import com.liferay.exportimport.kernel.xstream.XStreamConverter;
import com.liferay.exportimport.kernel.xstream.XStreamType;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.xstream.configurator.XStreamConfigurator;

import hu.webtown.liferay.portlet.reference.model.impl.ReferenceImpl;

@Component(immediate = true, service = XStreamConfigurator.class)
public class ReferenceXStreamConfigurator implements XStreamConfigurator {

    private XStreamAlias[] xStreamAliases;

    @Override
    public List<XStreamType> getAllowedXStreamTypes() {

        return null;
    }

    @Override
    public List<XStreamAlias> getXStreamAliases() {

        return ListUtil.toList(xStreamAliases);
    }

    @Override
    public List<XStreamConverter> getXStreamConverters() {

        return null;
    }

    @Activate
    protected void activate() {

        xStreamAliases = new XStreamAlias[] {
            new XStreamAlias(ReferenceImpl.class, "Reference")
        };
    }
}
