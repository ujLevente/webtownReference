
package hu.webtown.liferay.portlet.reference.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelper;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.search.batch.BatchIndexingHelper;
import com.liferay.portal.search.filter.FilterBuilders;
import com.liferay.portal.search.index.IndexStatusManager;
import com.liferay.trash.TrashHelper;

import hu.webtown.liferay.portlet.reference.constants.ReferenceActionKeys;
import hu.webtown.liferay.portlet.reference.constants.ReferenceConstants;
import hu.webtown.liferay.portlet.reference.model.Reference;
import hu.webtown.liferay.portlet.reference.model.ReferenceResource;
import hu.webtown.liferay.portlet.reference.service.ReferenceLocalService;
import hu.webtown.liferay.portlet.reference.service.ReferenceResourceLocalService;
import hu.webtown.liferay.portlet.reference.util.ReferenceUtil;

@Component(immediate = true, service = Indexer.class)
public class ReferenceIndexer extends BaseIndexer<Reference> {

    public static final String CLASS_NAME = Reference.class.getName();

    protected static final String REFERENCE_ID = "referenceId";
    protected static final String SHORT_DESCRIPTION = "shortDescription";

    private static final Log LOG =
        LogFactoryUtil.getLog(ReferenceIndexer.class);
    
    @org.osgi.service.component.annotations.Reference
    private BatchIndexingHelper batchIndexingHelper;
    
    @org.osgi.service.component.annotations.Reference
    private FilterBuilders filterBuilders;

    @org.osgi.service.component.annotations.Reference
    private IndexerRegistry indexerRegistry;

    @org.osgi.service.component.annotations.Reference
    private IndexStatusManager indexStatusManager;
    
    @org.osgi.service.component.annotations.Reference
    private IndexWriterHelper indexWriterHelper;

    private ReferenceLocalService referenceLocalService;
    
    private ReferenceResourceLocalService referenceResourceLocalService;
    
    @org.osgi.service.component.annotations.Reference(
        target = "(model.class.name=hu.webtown.liferay.portlet.reference.model.Reference)"
    )
    private ModelResourcePermission<Reference> referenceModelResourcePermission;
    
    @org.osgi.service.component.annotations.Reference
    private TrashHelper trashHelper;

    public ReferenceIndexer() {
        setDefaultSelectedFieldNames(
            Field.ASSET_TAG_NAMES, Field.COMPANY_ID, Field.CONTENT,
            Field.DESCRIPTION, Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
            Field.GROUP_ID, Field.MODIFIED_DATE, REFERENCE_ID,
            Field.SCOPE_GROUP_ID, Field.TITLE, Field.VERSION, Field.UID);
        setFilterSearch(true);
        setPermissionAware(true);
    }

    @Override
    protected void doDelete(Reference reference)
        throws Exception {

        if (referenceLocalService.getReferencesCount(
            reference.getGroupId(), reference.getReferenceId()) > 0) {

            doReindex(reference);

            return;
        }

        deleteDocument(
            reference.getCompanyId(), reference.getResourcePrimKey());

        if (!reference.isApproved()) {

            return;
        }

        Reference latestIndexableReference =
            referenceLocalService.fetchLatestIndexableReference(
                reference.getResourcePrimKey());

        if ((latestIndexableReference == null) ||
            (latestIndexableReference.getVersion() > reference.getVersion())) {

            return;
        }

        indexWriterHelper.updateDocument(
            getSearchEngineId(), reference.getCompanyId(),
            getDocument(latestIndexableReference), isCommitImmediately());
    }

    @Override
    protected Document doGetDocument(Reference reference)
        throws Exception {

        Document document = getBaseModelDocument(CLASS_NAME, reference);

        long classPK = reference.getResourcePrimKey();

        document.addUID(CLASS_NAME, classPK);

        document.addText(Field.CONTENT, reference.getDescription());
        document.addText(Field.DESCRIPTION, reference.getShortDescription());
        document.addText(Field.TITLE, reference.getName());

        String referenceId = reference.getReferenceId();

        if (reference.isInTrash()) {
            referenceId = trashHelper.getOriginalTitle(referenceId);
        }

        document.addKeywordSortable(REFERENCE_ID, referenceId);
        document.addKeyword(Field.VERSION, reference.getVersion());
        document.addDate("realizationDate", reference.getRealizationDate());

        return document;
    }

    @Override
    protected String doGetSortField(String orderByCol) {

        if ("realization-date".equals(orderByCol)) {

            return "realizationDate";
        } else if ("id".equals(orderByCol)) {

            return Field.ENTRY_CLASS_PK;
        } else if ("modified-date".equals(orderByCol)) {

            return Field.MODIFIED_DATE;
        } else if ("name".equals(orderByCol)) {

            return Field.TITLE;
        } else {

            return orderByCol;
        }
    }

    @Override
    protected Summary doGetSummary(
        Document document, Locale locale, String snippet,
        PortletRequest portletRequest, PortletResponse portletResponse) {

        String title = document.get(
            Field.SNIPPET + StringPool.UNDERLINE + Field.TITLE, Field.TITLE);

        String content = snippet;

        if (Validator.isNull(snippet)) {

            content = document.get(
                Field.SNIPPET + StringPool.UNDERLINE + Field.CONTENT,
                Field.CONTENT);

            if (Validator.isNull(content)) {

                content = document.get(Field.DESCRIPTION);
            }
        }

        Summary summary = new Summary(title, content);

        summary.setMaxContentLength(200);

        return summary;
    }

    @Override
    protected void doReindex(Reference reference)
        throws Exception {

        if (!reference.isApproved() && !ReferenceUtil.isDoublesEquals(
            reference.getVersion(), ReferenceConstants.VERSION_DEFAULT)) {

            Document document = getDocument(reference);

            indexWriterHelper.deleteDocument(
                getSearchEngineId(), reference.getCompanyId(),
                document.get(Field.UID), isCommitImmediately());

            return;
        }

        reindexReferenceVersions(reference);
    }

    @Override
    protected void doReindex(String className, long classPK)
        throws Exception {

        Reference reference = referenceLocalService.fetchReference(classPK);

        if (reference == null) {
            reference = referenceLocalService.fetchLatestReference(classPK);
        }

        if (reference != null) {
            doReindex(reference);
        }
    }

    @Override
    protected void doReindex(String[] ids)
        throws Exception {

        long companyId = GetterUtil.getLong(ids[0]);

        reindexReferences(companyId);
    }

    protected Reference fetchLatestIndexableReferenceVersion(
        long resourcePrimKey) {

        Reference latestIndexableReference =
            referenceLocalService.fetchLatestReference(
                resourcePrimKey, new int[] {
                    WorkflowConstants.STATUS_APPROVED,
                    WorkflowConstants.STATUS_IN_TRASH
                });

        if (latestIndexableReference == null) {

            latestIndexableReference =
                referenceLocalService.fetchLatestReference(resourcePrimKey);
        }

        return latestIndexableReference;
    }

    @Override
    public String getClassName() {

        return CLASS_NAME;
    }

    protected Collection<Document> getReferenceVersions(Reference reference)
        throws PortalException {

        Collection<Document> documents = new ArrayList<>();

        List<Reference> references = new ArrayList<>();

        Reference latestIndexableReference =
            fetchLatestIndexableReferenceVersion(
                reference.getResourcePrimKey());

        if (latestIndexableReference != null) {

            references.add(latestIndexableReference);
        }

        for (Reference curReference : references) {

            Document document = getDocument(curReference);

            documents.add(document);
        }

        return documents;
    }

    @Override
    public boolean hasPermission(
        PermissionChecker permissionChecker, String entryClassName,
        long entryClassPK, String actionId)
        throws Exception {

        return referenceModelResourcePermission.contains(
            permissionChecker, entryClassPK, ReferenceActionKeys.VIEW);
    }

    @Override
    public boolean isVisible(long classPK, int status)
        throws Exception {

        List<Reference> references =
            referenceLocalService.getReferencesByResourcePrimKey(classPK);

        for (Reference curReference : references) {

            if (isVisible(curReference.getStatus(), status)) {

                return true;
            }
        }

        return false;
    }

    @Override
    public void postProcessContextBooleanFilter(
        BooleanFilter contextBooleanFilter, SearchContext searchContext)
        throws Exception {

        addStatus(contextBooleanFilter, searchContext);
    }

    @Override
    public void postProcessSearchQuery(
        BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
        SearchContext searchContext)
        throws Exception {

        addSearchTerm(searchQuery, searchContext, REFERENCE_ID, false);
        addSearchTerm(searchQuery, searchContext, Field.CLASS_PK, false);
        addSearchTerm(searchQuery, searchContext, Field.CONTENT, false);
        addSearchTerm(searchQuery, searchContext, Field.DESCRIPTION, false);
        addSearchTerm(searchQuery, searchContext, Field.ENTRY_CLASS_PK, false);
        addSearchTerm(searchQuery, searchContext, Field.TITLE, false);
        addSearchTerm(searchQuery, searchContext, Field.USER_NAME, false);

        LinkedHashMap<String, Object> params =
            (LinkedHashMap<String, Object>) searchContext.getAttribute(
                "params");

        if (params != null) {

            String expandoAttributes = (String) params.get("expandoAttributes");

            if (Validator.isNotNull(expandoAttributes)) {

                addSearchExpando(searchQuery, searchContext, expandoAttributes);
            }
        }
    }

    protected void reindexReferences(long companyId)
        throws PortalException {

        final IndexableActionableDynamicQuery indexableActionableDynamicQuery =
            referenceResourceLocalService.getIndexableActionableDynamicQuery();

        indexableActionableDynamicQuery.setInterval(
            batchIndexingHelper.getBulkSize(ReferenceResource.class.getName()));

        indexableActionableDynamicQuery.setPerformActionMethod(
            new ActionableDynamicQuery.PerformActionMethod<ReferenceResource>() {

                @Override
                public void performAction(ReferenceResource referenceResource)
                    throws PortalException {

                    Reference latestIndexableReference =
                        fetchLatestIndexableReferenceVersion(
                            referenceResource.getResourcePrimKey());

                    if (latestIndexableReference == null) {

                        return;
                    }

                    try {

                        Document document =
                            getDocument(latestIndexableReference);

                        indexableActionableDynamicQuery.addDocuments(document);
                    } catch (PortalException pe) {
                        if (LOG.isWarnEnabled()) {

                            LOG.warn(
                                "Unable to index reference " +
                                    latestIndexableReference.getId(),
                                pe);
                        }
                    }
                }
            });

        indexableActionableDynamicQuery.setCompanyId(companyId);
        indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

        indexableActionableDynamicQuery.performActions();
    }

    protected void reindexReferenceVersions(Reference reference)
        throws PortalException {

        indexWriterHelper.updateDocuments(
            getSearchEngineId(), reference.getCompanyId(),
            getReferenceVersions(reference), isCommitImmediately());
    }

    @org.osgi.service.component.annotations.Reference(unbind = "-")
    protected void setReferenceLocalService(
        ReferenceLocalService referenceLocalService) {

        this.referenceLocalService = referenceLocalService;
    }

    @org.osgi.service.component.annotations.Reference(unbind = "-")
    protected void setReferenceResourceLocalService(
        ReferenceResourceLocalService referenceResourceLocalService) {

        this.referenceResourceLocalService = referenceResourceLocalService;
    }

}
