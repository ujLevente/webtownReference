
package hu.webtown.liferay.portlet.reference.exportimport.data.handler;

import java.util.List;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import com.liferay.exportimport.kernel.lar.BasePortletDataHandler;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataHandler;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerControl;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.xml.Element;

import hu.webtown.liferay.portlet.reference.constants.ReferencePortletKeys;
import hu.webtown.liferay.portlet.reference.model.Reference;
import hu.webtown.liferay.portlet.reference.service.ReferenceLocalService;
import hu.webtown.liferay.portlet.reference.service.permission.ReferenceAdminPermission;

@Component(property = {
    "javax.portlet.name=" + ReferencePortletKeys.REFERENCE_DXP_PORTLET
}, service = PortletDataHandler.class)
public class ReferenceDxpPortletDataHandler extends BasePortletDataHandler {

    public static final String NAMESPACE = "reference";

    public static final String SCHEMA_VERSION = "1.0.0";

    private static final String REFERENCES = "references";

    private static final String VERSION_HISTORY = "version-history";

    private ReferenceLocalService referenceLocalService;
    private ReferenceStagedModelDataHandler referenceStagedModelDataHandler;

    @Override
    public String getSchemaVersion() {

        return SCHEMA_VERSION;
    }

    @Activate
    protected void activate() {

        setDataLocalized(false);
        setDeletionSystemEventStagedModelTypes(
            new StagedModelType(Reference.class));

        setExportControls(
            new PortletDataHandlerBoolean(
                NAMESPACE, REFERENCES, true, false,
                new PortletDataHandlerControl[] {
                    new PortletDataHandlerBoolean(
                        NAMESPACE, VERSION_HISTORY, true)
                }, Reference.class.getName()));

        setImportControls(getExportControls());

        setPublishToLiveByDefault(true);
    }

    @Override
    protected PortletPreferences doDeleteData(
        PortletDataContext portletDataContext, String portletId,
        PortletPreferences portletPreferences)
        throws Exception {

        if (portletDataContext.addPrimaryKey(
            ReferenceDxpPortletDataHandler.class, "deleteData")) {

            return portletPreferences;
        }

        referenceLocalService.deleteReferences(
            portletDataContext.getScopeGroupId());

        return portletPreferences;
    }

    @Override
    protected String doExportData(
        final PortletDataContext portletDataContext, String portletId,
        PortletPreferences portletPreferences)
        throws Exception {

        portletDataContext.addPortletPermissions(
            ReferenceAdminPermission.RESOURCE_NAME);

        Element rootElement = addExportDataRootElement(portletDataContext);

        rootElement.addAttribute(
            "group-id", String.valueOf(portletDataContext.getScopeGroupId()));

        if (portletDataContext.getBooleanParameter(NAMESPACE, REFERENCES)) {

            ActionableDynamicQuery referenceActionableDynamicQuery =
                getReferenceActionableDynamicQuery(portletDataContext);

            referenceActionableDynamicQuery.performActions();
        }

        return getExportDataRootElementString(rootElement);
    }

    @Override
    protected PortletPreferences doImportData(
        PortletDataContext portletDataContext, String portletId,
        PortletPreferences portletPreferences, String data)
        throws Exception {

        portletDataContext.importPortletPermissions(
            ReferenceAdminPermission.RESOURCE_NAME);

        if (portletDataContext.getBooleanParameter(NAMESPACE, REFERENCES)) {

            Element referencesElement =
                portletDataContext.getImportDataGroupElement(Reference.class);

            List<Element> referenceElements = referencesElement.elements();

            for (Element curReferenceElement : referenceElements) {

                StagedModelDataHandlerUtil.importStagedModel(
                    portletDataContext, curReferenceElement);
            }
        }

        return portletPreferences;
    }

    @Override
    protected void doPrepareManifestSummary(
        PortletDataContext portletDataContext,
        PortletPreferences portletPreferences)
        throws Exception {

        ActionableDynamicQuery referenceActionableDynamicQuery =
            getReferenceActionableDynamicQuery(portletDataContext);

        referenceActionableDynamicQuery.performCount();
    }

    protected ActionableDynamicQuery getReferenceActionableDynamicQuery(
        final PortletDataContext portletDataContext) {

        ExportActionableDynamicQuery exportActionableDynamicQuery =
            referenceLocalService.getExportActionableDynamicQuery(
                portletDataContext);

        final ActionableDynamicQuery.AddCriteriaMethod addCriteriaMethod =
            exportActionableDynamicQuery.getAddCriteriaMethod();

        exportActionableDynamicQuery.setAddCriteriaMethod(
            new ActionableDynamicQuery.AddCriteriaMethod() {

                @Override
                public void addCriteria(DynamicQuery dynamicQuery) {

                    addCriteriaMethod.addCriteria(dynamicQuery);

                    if (portletDataContext.getBooleanParameter(
                        NAMESPACE, VERSION_HISTORY)) {

                        return;
                    }

                    Class<?> clazz = getClass();

                    DynamicQuery versionReferenceDynamicQuery =
                        DynamicQueryFactoryUtil.forClass(
                            Reference.class, "versionReference",
                            clazz.getClassLoader());

                    versionReferenceDynamicQuery.setProjection(
                        ProjectionFactoryUtil.alias(
                            ProjectionFactoryUtil.max(
                                "versionReference.version"),
                            "versionReference.version"));

                    // We need to use the "this" default alias to make sure the
                    // database engine handles this subquery as a correlated
                    // subquery

                    versionReferenceDynamicQuery.add(
                        RestrictionsFactoryUtil.eqProperty(
                            "this.resourcePrimKey",
                            "versionReference.resourcePrimKey"));

                    Property workflowStatusProperty =
                        PropertyFactoryUtil.forName("status");

                    versionReferenceDynamicQuery.add(
                        workflowStatusProperty.in(
                            referenceStagedModelDataHandler.getExportableStatuses()));

                    Property versionProperty =
                        PropertyFactoryUtil.forName("version");

                    dynamicQuery.add(
                        versionProperty.eq(versionReferenceDynamicQuery));
                }
            });

        exportActionableDynamicQuery.setStagedModelType(
            new StagedModelType(Reference.class.getName()));

        return exportActionableDynamicQuery;

    }

    @org.osgi.service.component.annotations.Reference(unbind = "-")
    protected void setReferenceLocalService(
        ReferenceLocalService referenceLocalService) {

        this.referenceLocalService = referenceLocalService;
    }

    @org.osgi.service.component.annotations.Reference(unbind = "-")
    protected void setReferenceStagedModelDataHandler(
        ReferenceStagedModelDataHandler referenceStagedModelDataHandler) {

        this.referenceStagedModelDataHandler = referenceStagedModelDataHandler;
    }
}
