
package hu.webtown.liferay.portlet.reference.list.web.util;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryService;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BaseModelSearchResult;

import aQute.bnd.annotation.ProviderType;
import hu.webtown.liferay.portlet.reference.list.web.display.context.ReferenceDxpListDisplayContext;
import hu.webtown.liferay.portlet.reference.model.Reference;
import hu.webtown.liferay.portlet.reference.web.asset.ReferenceAssetRenderer;

@Component(
    immediate = true,
    service = ReferenceDxpListUtil.class
)
@ProviderType
public class ReferenceDxpListUtil {

    private static AssetEntryService assetEntryService;
    
    public static BaseModelSearchResult<AssetEntry> getAssetEntries(
        AssetEntryQuery assetEntryQuery, int start, int end)
        throws PortalException {

        int total = assetEntryService.getEntriesCount(assetEntryQuery);

        assetEntryQuery.setEnd(end);
        assetEntryQuery.setStart(start);

        List<AssetEntry> results =
            assetEntryService.getEntries(assetEntryQuery);

        return new BaseModelSearchResult<>(results, total);
    }

    public static List<Reference> getReferenceResults(
        ReferenceDxpListDisplayContext referenceDxpListDisplayContext,
        PortletPreferences portletPreferences)
        throws PortalException {

        AssetEntryQuery assetEntryQuery =
            referenceDxpListDisplayContext.getAssetEntryQuery();

        BaseModelSearchResult<AssetEntry> baseModelSearchResult =
            getAssetEntries(
                assetEntryQuery, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

        List<AssetEntry> assetEntries = baseModelSearchResult.getBaseModels();

        List<Reference> references = new ArrayList<>();

        for (AssetEntry curAssetEntry : assetEntries) {
            
            references.add(
                ((ReferenceAssetRenderer) curAssetEntry.getAssetRenderer()).getAssetObject());
        }

        return references;
    }

    @org.osgi.service.component.annotations.Reference(unbind = "-")
    protected void setAssetEntryService(
        AssetEntryService newAssetEntryService) {

        assetEntryService = newAssetEntryService;
    }

}
