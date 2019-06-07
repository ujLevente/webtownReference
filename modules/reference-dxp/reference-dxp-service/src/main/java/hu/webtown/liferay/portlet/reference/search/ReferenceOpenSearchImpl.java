
package hu.webtown.liferay.portlet.reference.search;

import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.HitsOpenSearchImpl;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.OpenSearch;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import hu.webtown.liferay.portlet.reference.model.Reference;
import hu.webtown.liferay.portlet.reference.service.ReferenceService;

@Component(immediate = true, service = OpenSearch.class)
public class ReferenceOpenSearchImpl extends HitsOpenSearchImpl {

    public static final String TITLE = "Webtown Reference Search: ";

    private AssetEntryLocalService assetEntryLocalService;
    private ReferenceService referenceService;

    @Override
    public String getClassName() {

        return Reference.class.getName();
    }

    @Override
    public Indexer<Reference> getIndexer() {

        return IndexerRegistryUtil.getIndexer(Reference.class);
    }

    @Override
    public String getSearchPath() {

        return StringPool.BLANK;
    }

    @Override
    public String getTitle(String keywords) {

        return TITLE + keywords;
    }

    @Override
    protected String getURL(
        ThemeDisplay themeDisplay, long groupId, Document result,
        PortletURL portletURL)
        throws Exception {

        String referenceId = result.get("referenceId");

        Reference reference =
            referenceService.getReference(groupId, referenceId);

        AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
            Reference.class.getName(), reference.getResourcePrimKey());

        if (assetEntry == null) {
            return null;
        }

        portletURL.setParameter(
            "assetEntryId", String.valueOf(assetEntry.getEntryId()));
        portletURL.setParameter("groupId", String.valueOf(groupId));
        portletURL.setParameter("referenceId", referenceId);

        return portletURL.toString();
    }

    @org.osgi.service.component.annotations.Reference(unbind = "-")
    protected void setAssetEntryLocalService(
        AssetEntryLocalService assetEntryLocalService) {

        this.assetEntryLocalService = assetEntryLocalService;
    }

    @org.osgi.service.component.annotations.Reference(unbind = "-")
    protected void setReferenceService(ReferenceService referenceService) {

        this.referenceService = referenceService;
    }

}
