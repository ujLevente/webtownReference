
package hu.webtown.liferay.portlet.reference.service.persistence.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.spring.extender.service.ServiceReference;

import aQute.bnd.annotation.ProviderType;
import hu.webtown.liferay.portlet.reference.model.Reference;
import hu.webtown.liferay.portlet.reference.model.impl.ReferenceImpl;
import hu.webtown.liferay.portlet.reference.service.persistence.ReferenceFinder;

@ProviderType
public class ReferenceFinderImpl extends ReferenceFinderBaseImpl
    implements ReferenceFinder {

    public static final String COUNT_BY_C_G_R_V_N_S_D_R_S =
        ReferenceFinder.class.getName() + ".countByC_G_R_V_N_S_D_R_S";

    public static final String COUNT_BY_GROUP_ID =
        ReferenceFinder.class.getName() + ".countByGroupId";

    public static final String FIND_BY_C_G_R_V_N_S_D_R_S =
        ReferenceFinder.class.getName() + ".findByC_G_R_V_N_S_D_R_S";

    public static final String FIND_BY_GROUP_ID =
        ReferenceFinder.class.getName() + ".findByGroupId";

    public static final String FIND_BY_REALIZATION_DATE =
        ReferenceFinder.class.getName() + ".findByRealizationDate";

    @ServiceReference(type = CustomSQL.class)
    private CustomSQL customSQL;

    private static String replaceStatusJoin(
        String sql, QueryDefinition<Reference> queryDefinition) {

        if (queryDefinition.getStatus() == WorkflowConstants.STATUS_ANY) {

            return StringUtil.replace(
                sql, "[$STATUS_JOIN$] AND", StringPool.BLANK);
        }

        if (queryDefinition.isExcludeStatus()) {
            StringBundler sb = new StringBundler(5);

            sb.append("(Reference.status != ");
            sb.append(queryDefinition.getStatus());
            sb.append(") AND (tempReference.status != ");
            sb.append(queryDefinition.getStatus());
            sb.append(")");

            sql = StringUtil.replace(sql, "[$STATUS_JOIN$]", sb.toString());
        } else {
            StringBundler sb = new StringBundler(5);

            sb.append("(Reference.status = ");
            sb.append(queryDefinition.getStatus());
            sb.append(") AND (tempReference.status = ");
            sb.append(queryDefinition.getStatus());
            sb.append(")");

            sql = StringUtil.replace(sql, "[$STATUS_JOIN$]", sb.toString());
        }

        return sql;
    }

    private static void setQueryParameters(
        SQLQuery q, long companyId, long groupId, String[] referenceIdsKeywords,
        Double version, String[] namesKeywords,
        String[] shortDescriptionsKeywords, String[] descriptionsKeywords,
        Timestamp realizationDateGTTimestamp,
        Timestamp realizationDateLTTimestamp, int status,
        QueryDefinition<Reference> queryDefinition) {

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(companyId);

        if (groupId > 0) {

            qPos.add(groupId);
        }

        qPos.add(queryDefinition.getStatus());

        qPos.add(referenceIdsKeywords, 2);

        if ((version != null) && (version > 0)) {

            qPos.add(version);
        }

        qPos.add(namesKeywords, 2);
        qPos.add(shortDescriptionsKeywords, 2);
        qPos.add(descriptionsKeywords, 2);
        qPos.add(realizationDateGTTimestamp);
        qPos.add(realizationDateGTTimestamp);
        qPos.add(realizationDateLTTimestamp);
        qPos.add(realizationDateLTTimestamp);
    }

    @Override
    public int countByC_G_R_V_N_S_D_R_S(
        long companyId, long groupId, String referenceId, Double version,
        String name, String shortDescription, String description,
        Date realizationDateGT, Date realizationDateLT, int status,
        boolean andOperator) {

        String[] referenceIds = customSQL.keywords(referenceId, false);
        String[] names = customSQL.keywords(name);
        String[] shortDescriptions =
            customSQL.keywords(shortDescription, false);
        String[] descriptions = customSQL.keywords(description, false);

        return countByC_G_R_V_N_S_D_R_S(
            companyId, groupId, referenceIds, version, names, shortDescriptions,
            descriptions, realizationDateGT, realizationDateLT, status,
            andOperator);
    }

    @Override
    public int countByC_G_R_V_N_S_D_R_S(
        long companyId, long groupId, String[] referenceIds, Double version,
        String[] names, String[] shortDescriptions, String[] descriptions,
        Date realizationDateGT, Date realizationDateLT, int status,
        boolean andOperator) {

        QueryDefinition<Reference> queryDefinition =
            new QueryDefinition<>(status);

        return doCountByC_G_R_V_N_S_D_R_S(
            companyId, groupId, referenceIds, version, names, shortDescriptions,
            descriptions, realizationDateGT, realizationDateLT, status,
            andOperator, queryDefinition, false);
    }

    @Override
    public int countByGroupId(
        long groupId, QueryDefinition<Reference> queryDefinition) {

        return doCountByGroupId(groupId, queryDefinition, false);
    }

    @Override
    public int countByKeywords(
        long companyId, long groupId, String keywords, Double version,
        Date realizationDateGT, Date realizationDateLT, int status) {

        return doCountByKeywords(
            companyId, groupId, keywords, version, realizationDateGT,
            realizationDateLT, status, false);
    }

    @Override
    public int filterCountByC_G_R_V_N_S_D_R_S(
        long companyId, long groupId, String referenceId, Double version,
        String name, String shortDescription, String description,
        Date realizationDateGT, Date realizationDateLT, int status,
        boolean andOperator) {

        String[] referenceIds = customSQL.keywords(referenceId, false);
        String[] names = customSQL.keywords(name);
        String[] shortDescriptions =
            customSQL.keywords(shortDescription, false);
        String[] descriptions = customSQL.keywords(description, false);

        return filterCountByC_G_R_V_N_S_D_R_S(
            companyId, groupId, referenceIds, version, names, shortDescriptions,
            descriptions, realizationDateGT, realizationDateLT, status,
            andOperator);
    }

    @Override
    public int filterCountByC_G_R_V_N_S_D_R_S(
        long companyId, long groupId, String[] referenceIds, Double version,
        String[] names, String[] shortDescriptions, String[] descriptions,
        Date realizationDateGT, Date realizationDateLT, int status,
        boolean andOperator) {

        QueryDefinition<Reference> queryDefinition =
            new QueryDefinition<>(status);

        return doCountByC_G_R_V_N_S_D_R_S(
            companyId, groupId, referenceIds, version, names, shortDescriptions,
            descriptions, realizationDateGT, realizationDateLT, status,
            andOperator, queryDefinition, true);
    }

    @Override
    public int filterCountByGroupId(
        long groupId, QueryDefinition<Reference> queryDefinition) {

        return doCountByGroupId(groupId, queryDefinition, true);
    }

    @Override
    public int filterCountByKeywords(
        long companyId, long groupId, String keywords, Double version,
        Date realizationDateGT, Date realizationDateLT, int status) {

        return doCountByKeywords(
            companyId, groupId, keywords, version, realizationDateGT,
            realizationDateLT, status, true);
    }

    @Override
    public List<Reference> filterFindByC_G_R_V_N_S_D_R_S(
        long companyId, long groupId, String referenceId, Double version,
        String name, String shortDescription, String description,
        Date realizationDateGT, Date realizationDateLT, int status,
        boolean andOperator, int start, int end,
        OrderByComparator<Reference> orderByComparator) {

        String[] referenceIds = customSQL.keywords(referenceId, false);
        String[] names = customSQL.keywords(name);
        String[] shortDescriptions =
            customSQL.keywords(shortDescription, false);
        String[] descriptions = customSQL.keywords(description, false);

        return filterFindByC_G_R_V_N_S_D_R_S(
            companyId, groupId, referenceIds, version, names, shortDescriptions,
            descriptions, realizationDateGT, realizationDateLT, status,
            andOperator, start, end, orderByComparator);
    }

    @Override
    public List<Reference> filterFindByC_G_R_V_N_S_D_R_S(
        long companyId, long groupId, String[] referenceIds, Double version,
        String[] names, String[] shortDescriptions, String[] descriptions,
        Date realizationDateGT, Date realizationDateLT, int status,
        boolean andOperator, int start, int end,
        OrderByComparator<Reference> orderByComparator) {

        QueryDefinition<Reference> queryDefinition =
            new QueryDefinition<>(status, start, end, orderByComparator);

        return doFindByC_G_R_V_N_S_D_R_S(
            companyId, groupId, referenceIds, version, names, shortDescriptions,
            descriptions, realizationDateGT, realizationDateLT, status,
            andOperator, queryDefinition, true);
    }

    @Override
    public List<Reference> filterFindByGroupId(
        long groupId, QueryDefinition<Reference> queryDefinition) {

        return doFindByGroupId(groupId, queryDefinition, true);
    }

    @Override
    public List<Reference> filterFindByKeywords(
        long companyId, long groupId, String keywords, Double version,
        Date realizationDateGT, Date realizationDateLT, int status, int start,
        int end, OrderByComparator<Reference> orderByComparator) {

        return doFindByKeywords(
            companyId, groupId, keywords, version, realizationDateGT,
            realizationDateLT, status, start, end, orderByComparator, true);
    }

    @Override
    public List<Reference> findByC_G_R_V_N_S_D_R_S(
        long companyId, long groupId, String referenceId, Double version,
        String name, String shortDescription, String description,
        Date realizationDateGT, Date realizationDateLT, int status,
        boolean andOperator, int start, int end,
        OrderByComparator<Reference> orderByComparator) {

        String[] referenceIds = customSQL.keywords(referenceId, false);
        String[] names = customSQL.keywords(name);
        String[] shortDescriptions =
            customSQL.keywords(shortDescription, false);
        String[] descriptions = customSQL.keywords(description, false);

        return findByC_G_R_V_N_S_D_R_S(
            companyId, groupId, referenceIds, version, names, shortDescriptions,
            descriptions, realizationDateGT, realizationDateLT, status,
            andOperator, start, end, orderByComparator);
    }

    @Override
    public List<Reference> findByC_G_R_V_N_S_D_R_S(
        long companyId, long groupId, String[] referenceIds, Double version,
        String[] names, String[] shortDescriptions, String[] descriptions,
        Date realizationDateGT, Date realizationDateLT, int status,
        boolean andOperator, int start, int end,
        OrderByComparator<Reference> orderByComparator) {

        QueryDefinition<Reference> queryDefinition =
            new QueryDefinition<>(status, start, end, orderByComparator);

        return doFindByC_G_R_V_N_S_D_R_S(
            companyId, groupId, referenceIds, version, names, shortDescriptions,
            descriptions, realizationDateGT, realizationDateLT, status,
            andOperator, queryDefinition, false);
    }

    @Override
    public List<Reference> findByGroupId(
        long groupId, QueryDefinition<Reference> queryDefinition) {

        return doFindByGroupId(groupId, queryDefinition, false);
    }

    @Override
    public List<Reference> findByKeywords(
        long companyId, long groupId, String keywords, Double version,
        Date realizationDateGT, Date realizationDateLT, int status, int start,
        int end, OrderByComparator<Reference> orderByComparator) {

        return doFindByKeywords(
            companyId, groupId, keywords, version, realizationDateGT,
            realizationDateLT, status, start, end, orderByComparator, false);
    }

    @Override
    public List<Reference> findByRealizationDate(
        Date realizationDateLT, Date realizationDateGT) {

        Timestamp realizationDateTimestampLT =
            CalendarUtil.getTimestamp(realizationDateLT);
        Timestamp realizationDateTimestampGT =
            CalendarUtil.getTimestamp(realizationDateGT);

        Session session = null;

        try {

            session = openSession();

            String sql = customSQL.get(getClass(), FIND_BY_REALIZATION_DATE);

            SQLQuery q = session.createSynchronizedSQLQuery(sql);

            q.addEntity(ReferenceImpl.TABLE_NAME, ReferenceImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);

            qPos.add(realizationDateTimestampGT);
            qPos.add(realizationDateTimestampLT);

            return q.list(true);
        } catch (Exception e) {

            throw new SystemException(e);
        } finally {

            closeSession(session);
        }
    }

    protected String createQueryString(
        String sqlId, long groupId, String[] referenceIds, Double version,
        String[] names, String[] shortDescriptions, String[] descriptions,
        Date realizationDateGT, Date realizationDateLT, int status,
        boolean andOperator, QueryDefinition<Reference> queryDefinition) {

        String sql =
            customSQL.get(getClass(), sqlId, queryDefinition, "Reference");

        sql = replaceStatusJoin(sql, queryDefinition);

        if (groupId <= 0) {

            sql = StringUtil.replace(
                sql, "(Reference.groupId = ?) AND", StringPool.BLANK);
        }

        sql = customSQL.replaceKeywords(
            sql, "Reference.referenceId", StringPool.LIKE, false, referenceIds);

        if ((version == null) || (version <= 0)) {

            sql = StringUtil.replace(
                sql, "(Reference.version = ?) [$AND_OR_CONNECTOR$]",
                StringPool.BLANK);
        }

        sql = customSQL.replaceKeywords(
            sql, "lower(Reference.name)", StringPool.LIKE, false, names);
        sql = customSQL.replaceKeywords(
            sql, "Reference.shortDescription", StringPool.LIKE, false,
            shortDescriptions);
        sql = customSQL.replaceKeywords(
            sql, "Reference.description", StringPool.LIKE, false, descriptions);

        if (status == WorkflowConstants.STATUS_ANY) {

            sql = StringUtil.replace(
                sql, "[$AND_OR_CONNECTOR$] ( (status = ?) )", "");
        }

        sql = customSQL.replaceAndOperator(sql, andOperator);

        // boolean referenceIdsValid =
        // referenceIds != null && (referenceIds.length > 1 ||
        // (referenceIds.length == 1 && referenceIds[0] != null));
        //
        // if (referenceIdsValid && version == null) {
        //
        // sql = StringUtil.replace(
        // sql, "MAX(version) AS tempVersion", "version AS tempVersion");
        // sql = StringUtil.replace(sql, GROUP_BY_CLAUSE, "");
        // } else {
        //
        // sql = StringUtil.replace(
        // sql, GROUP_BY_CLAUSE, "GROUP BY groupId, referenceId");
        // }

        return sql;
    }

    protected int doCountByC_G_R_V_N_S_D_R_S(
        long companyId, long groupId, String[] referenceIds, Double version,
        String[] names, String[] shortDescriptions, String[] descriptions,
        Date realizationDateGT, Date realizationDateLT, int status,
        boolean andOperator, QueryDefinition<Reference> queryDefinition,
        boolean inlineSQLHelper) {

        String[] referenceIdsKeywords = customSQL.keywords(referenceIds, false);
        String[] namesKeywords = customSQL.keywords(names);
        String[] shortDescriptionsKeywords =
            customSQL.keywords(shortDescriptions, false);
        String[] descriptionsKeywords = customSQL.keywords(descriptions, false);
        Timestamp realizationDateGTTimestamp =
            CalendarUtil.getTimestamp(realizationDateGT);
        Timestamp realizationDateLTTimestamp =
            CalendarUtil.getTimestamp(realizationDateLT);

        Session session = null;

        try {

            session = openSession();

            String queryString = createQueryString(
                COUNT_BY_C_G_R_V_N_S_D_R_S, groupId, referenceIdsKeywords,
                version, namesKeywords, shortDescriptionsKeywords,
                descriptionsKeywords, realizationDateGT, realizationDateLT,
                status, andOperator, queryDefinition);

            if (inlineSQLHelper) {

                queryString = InlineSQLHelperUtil.replacePermissionCheck(
                    queryString, Reference.class.getName(),
                    "Reference.resourcePrimKey", groupId);

                queryString = StringUtil.replace(
                    queryString, "(companyId", "(Reference.companyId");
            }

            SQLQuery q = session.createSynchronizedSQLQuery(queryString);

            q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

            setQueryParameters(
                q, companyId, groupId, referenceIdsKeywords, version,
                namesKeywords, shortDescriptionsKeywords, descriptionsKeywords,
                realizationDateGTTimestamp, realizationDateLTTimestamp, status,
                queryDefinition);

            Iterator<Long> itr = q.iterate();

            if (itr.hasNext()) {
                Long count = itr.next();

                if (count != null) {
                    return count.intValue();
                }
            }

            return 0;
        } catch (Exception e) {
            throw new SystemException(e);
        } finally {
            closeSession(session);
        }
    }

    protected int doCountByGroupId(
        long groupId, QueryDefinition<Reference> queryDefinition,
        boolean inlineSQLHelper) {

        Session session = null;

        try {

            session = openSession();

            String queryString = customSQL.get(
                getClass(), COUNT_BY_GROUP_ID, queryDefinition, "Reference");

            queryString = replaceStatusJoin(queryString, queryDefinition);

            if (inlineSQLHelper) {

                queryString = InlineSQLHelperUtil.replacePermissionCheck(
                    queryString, Reference.class.getName(),
                    "Reference.resourcePrimKey", groupId);
            }

            SQLQuery q = session.createSynchronizedSQLQuery(queryString);

            q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

            QueryPos qPos = QueryPos.getInstance(q);

            qPos.add(groupId);
            qPos.add(queryDefinition.getStatus());

            Iterator<Long> itr = q.iterate();

            if (itr.hasNext()) {
                Long count = itr.next();

                if (count != null) {
                    return count.intValue();
                }
            }

            return 0;
        } catch (Exception e) {
            throw new SystemException(e);
        } finally {
            closeSession(session);
        }
    }

    protected int doCountByKeywords(
        long companyId, long groupId, String keywords, Double version,
        Date realizationDateGT, Date realizationDateLT, int status,
        boolean inlineSQLHelper)
        throws SystemException {

        String[] referenceIds = null;
        String[] names = null;
        String[] shortDescriptions = null;
        String[] descriptions = null;
        boolean andOperator = false;

        if (Validator.isNotNull(keywords)) {

            referenceIds = customSQL.keywords(keywords, false);
            names = customSQL.keywords(keywords);
            shortDescriptions = customSQL.keywords(keywords, false);
            descriptions = customSQL.keywords(keywords, false);
        } else {

            andOperator = true;
        }

        QueryDefinition<Reference> queryDefinition =
            new QueryDefinition<>(status);

        return doCountByC_G_R_V_N_S_D_R_S(
            companyId, groupId, referenceIds, version, names, shortDescriptions,
            descriptions, realizationDateGT, realizationDateLT, status,
            andOperator, queryDefinition, inlineSQLHelper);
    }

    protected List<Reference> doFindByC_G_R_V_N_S_D_R_S(
        long companyId, long groupId, String[] referenceIds, Double version,
        String[] names, String[] shortDescriptions, String[] descriptions,
        Date realizationDateGT, Date realizationDateLT, int status,
        boolean andOperator, QueryDefinition<Reference> queryDefinition,
        boolean inlineSQLHelper) {

        String[] referenceIdsKeywords = customSQL.keywords(referenceIds, false);
        String[] namesKeywords = customSQL.keywords(names);
        String[] shortDescriptionsKeywords =
            customSQL.keywords(shortDescriptions, false);
        String[] descriptionsKeywords = customSQL.keywords(descriptions, false);
        Timestamp realizationDateGTTimestamp =
            CalendarUtil.getTimestamp(realizationDateGT);
        Timestamp realizationDateLTTimestamp =
            CalendarUtil.getTimestamp(realizationDateLT);

        Session session = null;

        try {

            session = openSession();

            String queryString = createQueryString(
                FIND_BY_C_G_R_V_N_S_D_R_S, groupId, referenceIdsKeywords,
                version, namesKeywords, shortDescriptionsKeywords,
                descriptionsKeywords, realizationDateGT, realizationDateLT,
                status, andOperator, queryDefinition);

            queryString = customSQL.replaceOrderBy(
                queryString, queryDefinition.getOrderByComparator("Reference"));

            if (inlineSQLHelper) {

                queryString = InlineSQLHelperUtil.replacePermissionCheck(
                    queryString, Reference.class.getName(),
                    "Reference.resourcePrimKey", groupId);

                queryString = StringUtil.replace(
                    queryString, "(companyId", "(Reference.companyId");
            }

            SQLQuery q = session.createSynchronizedSQLQuery(queryString);

            q.addEntity(ReferenceImpl.TABLE_NAME, ReferenceImpl.class);

            setQueryParameters(
                q, companyId, groupId, referenceIdsKeywords, version,
                namesKeywords, shortDescriptionsKeywords, descriptionsKeywords,
                realizationDateGTTimestamp, realizationDateLTTimestamp, status,
                queryDefinition);

            return (List<Reference>) QueryUtil.list(
                q, getDialect(), queryDefinition.getStart(),
                queryDefinition.getEnd());
        } catch (Exception e) {

            throw new SystemException(e);
        } finally {

            closeSession(session);
        }
    }

    protected List<Reference> doFindByGroupId(
        long groupId, QueryDefinition<Reference> queryDefinition,
        boolean inlineSQLHelper) {

        Session session = null;

        try {

            session = openSession();

            String queryString = customSQL.get(
                getClass(), FIND_BY_GROUP_ID, queryDefinition, "Reference");

            queryString = replaceStatusJoin(queryString, queryDefinition);

            queryString = customSQL.replaceOrderBy(
                queryString, queryDefinition.getOrderByComparator("Reference"));

            if (inlineSQLHelper) {

                queryString = InlineSQLHelperUtil.replacePermissionCheck(
                    queryString, Reference.class.getName(),
                    "Reference.resourcePrimKey", groupId);
            }

            SQLQuery q = session.createSynchronizedSQLQuery(queryString);

            q.addEntity(ReferenceImpl.TABLE_NAME, ReferenceImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);

            qPos.add(groupId);
            qPos.add(queryDefinition.getStatus());

            return (List<Reference>) QueryUtil.list(
                q, getDialect(), queryDefinition.getStart(),
                queryDefinition.getEnd());
        } catch (Exception e) {
            throw new SystemException(e);
        } finally {
            closeSession(session);
        }
    }

    protected List<Reference> doFindByKeywords(
        long companyId, long groupId, String keywords, Double version,
        Date realizationDateGT, Date realizationDateLT, int status, int start,
        int end, OrderByComparator<Reference> orderByComparator,
        boolean inlineSQLHelper)
        throws SystemException {

        String[] referenceIds = null;
        String[] names = null;
        String[] shortDescriptions = null;
        String[] descriptions = null;
        boolean andOperator = false;

        if (Validator.isNotNull(keywords)) {

            referenceIds = customSQL.keywords(keywords, false);
            names = customSQL.keywords(keywords);
            shortDescriptions = customSQL.keywords(keywords, false);
            descriptions = customSQL.keywords(keywords, false);
        } else {

            andOperator = true;
        }

        QueryDefinition<Reference> queryDefinition =
            new QueryDefinition<>(status, start, end, orderByComparator);

        return doFindByC_G_R_V_N_S_D_R_S(
            companyId, groupId, referenceIds, version, names, shortDescriptions,
            descriptions, realizationDateGT, realizationDateLT, status,
            andOperator, queryDefinition, inlineSQLHelper);
    }
}
