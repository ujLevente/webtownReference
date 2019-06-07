
package hu.webtown.liferay.portlet.reference.util;

import java.util.ArrayList;
import java.util.List;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import hu.webtown.liferay.portlet.reference.model.Reference;
import hu.webtown.liferay.portlet.reference.service.ReferenceLocalServiceUtil;

public class ReferenceUtil {

    public static final String[] SELECTED_FIELD_NAMES = {
        "referenceId", Field.COMPANY_ID, Field.GROUP_ID, Field.UID
    };

    public static FileEntry getFileEntryFromJSON(
        long defaultGroupId, String fileEntryJSON)
        throws PortalException {

        if (Validator.isNull(fileEntryJSON)) {

            return null;
        }

        JSONObject fileJSONObject =
            JSONFactoryUtil.createJSONObject(fileEntryJSON);

        String fileEntryUUID = fileJSONObject.getString("uuid");
        long fileEntryGroupId = defaultGroupId;

        if (fileJSONObject.getLong("groupId") > 0) {

            fileEntryGroupId = fileJSONObject.getLong("groupId");
        }

        return DLAppServiceUtil.getFileEntryByUuidAndGroupId(
            fileEntryUUID, fileEntryGroupId);
    }

    public static List<Reference> getReferences(Hits hits)
        throws PortalException {

        List<com.liferay.portal.kernel.search.Document> documents =
            hits.toList();

        List<Reference> references = new ArrayList<>(documents.size());

        for (com.liferay.portal.kernel.search.Document document : documents) {

            String referenceId = document.get("referenceId");
            long groupId = GetterUtil.getLong(document.get(Field.GROUP_ID));

            Reference reference =
                ReferenceLocalServiceUtil.fetchLatestReference(
                    groupId, referenceId, WorkflowConstants.STATUS_APPROVED);

            if (reference == null) {

                references = null;

                Indexer<Reference> indexer =
                    IndexerRegistryUtil.getIndexer(Reference.class);

                long companyId =
                    GetterUtil.getLong(document.get(Field.COMPANY_ID));

                indexer.delete(companyId, document.getUID());
            } else if (references != null) {

                references.add(reference);
            }
        }

        return references;
    }

    public static String getUrlTitle(long id, String name) {

        if (name == null) {

            return String.valueOf(id);
        }

        String urlTitle = StringUtil.toLowerCase(name.trim());

        if (Validator.isNull(urlTitle) || Validator.isNumber(urlTitle)) {

            urlTitle = String.valueOf(id);
        } else {

            urlTitle = FriendlyURLNormalizerUtil.normalizeWithPeriodsAndSlashes(
                urlTitle);
        }

        return ModelHintsUtil.trimString(
            Reference.class.getName(), "urlTitle", urlTitle);
    }

    public static boolean isDoublesEquals(double d1, double d2) {

        return Math.abs(d1 - d2) < 0.0000001;
    }

    /**
     * Private constructor to prevent instantiation
     */
    private ReferenceUtil() {

    }

}
