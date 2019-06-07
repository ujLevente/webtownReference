
package hu.webtown.liferay.portlet.reference.web.internal.display.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.SafeConsumer;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItemList;
import com.liferay.frontend.taglib.servlet.taglib.ManagementBarFilterItem;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.trash.TrashHelper;

import hu.webtown.liferay.portlet.reference.constants.ReferenceActionKeys;
import hu.webtown.liferay.portlet.reference.constants.ReferenceConstants;
import hu.webtown.liferay.portlet.reference.constants.ReferencePortletKeys;
import hu.webtown.liferay.portlet.reference.model.Reference;
import hu.webtown.liferay.portlet.reference.service.ReferenceServiceUtil;
import hu.webtown.liferay.portlet.reference.web.configuration.ReferenceWebConfiguration;
import hu.webtown.liferay.portlet.reference.web.internal.portlet.action.ActionUtil;
import hu.webtown.liferay.portlet.reference.web.internal.search.ReferencesChecker;
import hu.webtown.liferay.portlet.reference.web.internal.search.ReferencesMover;
import hu.webtown.liferay.portlet.reference.web.internal.security.permission.resource.ReferenceAdminPermission;
import hu.webtown.liferay.portlet.reference.web.util.ReferenceDxpPortletUtil;

public class ReferenceDisplayContext {

    private String displayStyle;
    private String[] displayViews;
    private String keywords;
    private final LiferayPortletRequest liferayPortletRequest;
    private final LiferayPortletResponse liferayPortletResponse;
    private String navigation;
    private String orderByCol;
    private String orderByType;
    private final PortalPreferences portalPreferences;
    private final PortletPreferences portletPreferences;
    private Reference reference;
    private final HttpServletRequest request;
    private Boolean showEditActions;
    private Integer status;
    private final ReferenceWebConfiguration referenceWebConfiguration;
    private final TrashHelper trashHelper;

    public ReferenceDisplayContext(
        HttpServletRequest request, LiferayPortletRequest liferayPortletRequest,
        LiferayPortletResponse liferayPortletResponse,
        PortletPreferences portletPreferences, TrashHelper trashHelper) {

        this.request = request;
        this.liferayPortletRequest = liferayPortletRequest;
        this.liferayPortletResponse = liferayPortletResponse;
        this.portletPreferences = portletPreferences;
        this.trashHelper = trashHelper;

        referenceWebConfiguration =
            (ReferenceWebConfiguration) request.getAttribute(
                ReferenceWebConfiguration.class.getName());

        portalPreferences =
            PortletPreferencesFactoryUtil.getPortalPreferences(request);
    }

    public List<DropdownItem> getActionDropdownItems() {

        return new DropdownItemList() {

            {
                ThemeDisplay themeDisplay =
                    (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

                add(SafeConsumer.ignore(dropdownItem -> {
                    dropdownItem.putData("action", "deleteEntries");

                    boolean trashEnabled = trashHelper.isTrashEnabled(
                        themeDisplay.getScopeGroupId());

                    dropdownItem.setIcon(trashEnabled ? "trash" : "times");

                    String label = "delete";

                    if (trashEnabled) {
                        label = "recycle-bin";
                    }

                    dropdownItem.setLabel(LanguageUtil.get(request, label));

                    dropdownItem.setQuickAction(true);
                }));
            }
        };
    }

    public String getClearResultsURL() {

        PortletURL clearResultsURL = getPortletURL();

        clearResultsURL.setParameter("keywords", StringPool.BLANK);

        return clearResultsURL.toString();
    }

    public CreationMenu getCreationMenu() {

        ThemeDisplay themeDisplay =
            (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        if (!ReferenceAdminPermission.contains(
            themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(),
            ReferenceActionKeys.ADD_REFERENCE)) {

            return null;
        }

        return new CreationMenu() {

            {
                addDropdownItem(dropdownItem -> {
                    dropdownItem.setHref(
                        liferayPortletResponse.createRenderURL(), "mvcPath",
                        "/edit_reference.jsp", "redirect",
                        PortalUtil.getCurrentURL(request), "groupId",
                        String.valueOf(themeDisplay.getScopeGroupId()));

                    dropdownItem.setLabel(
                        LanguageUtil.get(request, "add-reference"));
                });
            }
        };
    }

    public String getDisplayStyle() {

        if (displayStyle != null) {
            return displayStyle;
        }

        String[] displayViews = getDisplayViews();

        PortalPreferences portalPreferences =
            PortletPreferencesFactoryUtil.getPortalPreferences(request);

        displayStyle = ParamUtil.getString(request, "displayStyle");

        if (Validator.isNull(displayStyle)) {

            displayStyle = portalPreferences.getValue(
                ReferencePortletKeys.REFERENCE_DXP_PORTLET, "display-style",
                referenceWebConfiguration.defaultDisplayView());
        } else if (ArrayUtil.contains(displayViews, displayStyle)) {

            portalPreferences.setValue(
                ReferencePortletKeys.REFERENCE_DXP_PORTLET, "display-style",
                displayStyle);

            request.setAttribute(
                WebKeys.SINGLE_PAGE_APPLICATION_CLEAR_CACHE, Boolean.TRUE);
        }

        if (!ArrayUtil.contains(displayViews, displayStyle)) {

            displayStyle = displayViews[0];
        }

        return displayStyle;
    }

    public String[] getDisplayViews() {

        if (displayViews == null) {

            displayViews = StringUtil.split(
                PrefsParamUtil.getString(
                    portletPreferences, request, "displayViews",
                    StringUtil.merge(
                        referenceWebConfiguration.displayViews())));
        }

        return displayViews;
    }

    public List<DropdownItem> getFilterDropdownItems() {

        return new DropdownItemList() {

            {
                addGroup(dropdownGroupItem -> {
                    dropdownGroupItem.setDropdownItems(
                        getFilterStatusDropdownItems());;
                    dropdownGroupItem.setLabel(
                        LanguageUtil.get(request, "filter-by-status"));
                });

                addGroup(dropdownGroupItem -> {
                    dropdownGroupItem.setDropdownItems(
                        getOrderByDropdownItems());
                    dropdownGroupItem.setLabel(
                        LanguageUtil.get(request, "order-by"));
                });
            }
        };
    }

    public String getFriendlyURLBase() {

        StringBundler sb = new StringBundler(4);

        ThemeDisplay themeDisplay =
            (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        sb.append(themeDisplay.getPortalURL());

        Group group = themeDisplay.getScopeGroup();

        sb.append(group.getPathFriendlyURL(false, themeDisplay));
        sb.append(group.getFriendlyURL());

        sb.append(ReferenceConstants.CANONICAL_URL_SEPARATOR);

        return sb.toString();
    }

    public String getKeywords() {

        if (keywords != null) {

            return keywords;
        }

        keywords = ParamUtil.getString(request, "keywords");

        return keywords;
    }

    public String getNavigation() {

        if (navigation != null) {

            return navigation;
        }

        navigation = ParamUtil.getString(request, "navigation", "all");

        return navigation;
    }

    public List<NavigationItem> getNavigationBarItems() {

        return new NavigationItemList() {

            {
                add(navigationItem -> {
                    navigationItem.setActive(true);
                    // navigationItem.setHref(StringPool.BLANK);
                    navigationItem.setHref(
                        liferayPortletResponse.createRenderURL());
                    navigationItem.setLabel(
                        LanguageUtil.get(request, "reference"));
                });
            }
        };
    }

    public String getOrderByCol() {

        if (orderByCol != null) {

            return orderByCol;
        }

        orderByCol = ParamUtil.getString(request, "orderByCol");

        if (Validator.isNull(orderByCol)) {

            orderByCol = portalPreferences.getValue(
                ReferencePortletKeys.REFERENCE_DXP_PORTLET, "order-by-col",
                "modified-date");
        } else {

            boolean saveOrderBy = ParamUtil.getBoolean(request, "saveOrderBy");

            if (saveOrderBy) {

                portalPreferences.setValue(
                    ReferencePortletKeys.REFERENCE_DXP_PORTLET, "order-by-col",
                    orderByCol);
            }
        }

        return orderByCol;
    }

    public String getOrderByType() {

        if (orderByType != null) {

            return orderByType;
        }

        orderByType = ParamUtil.getString(request, "orderByType");

        if (Validator.isNull(orderByType)) {

            orderByType = portalPreferences.getValue(
                ReferencePortletKeys.REFERENCE_DXP_PORTLET, "order-by-type",
                "asc");
        } else {

            boolean saveOrderBy = ParamUtil.getBoolean(request, "saveOrderBy");

            if (saveOrderBy) {

                portalPreferences.setValue(
                    ReferencePortletKeys.REFERENCE_DXP_PORTLET, "order-by-type",
                    orderByType);
            }
        }

        return orderByType;
    }

    public String[] getOrderColumns() {

        return new String[] {
            "id", "name", "version", "status", "modified-date",
            "realization-date", "author"
        };
    }

    public PortletURL getPortletURL() {

        PortletURL portletURL = liferayPortletResponse.createRenderURL();

        String navigation = ParamUtil.getString(request, "navigation");

        if (Validator.isNotNull(navigation)) {
            portletURL.setParameter(
                "navigation", HtmlUtil.escapeJS(getNavigation()));
        }

        String status = ParamUtil.getString(request, "status");

        if (Validator.isNotNull(status)) {
            portletURL.setParameter("status", String.valueOf(getStatus()));
        }

        String delta = ParamUtil.getString(request, "delta");

        if (Validator.isNotNull(delta)) {
            portletURL.setParameter("delta", delta);
        }

        String deltaEntry = ParamUtil.getString(request, "deltaEntry");

        if (Validator.isNotNull(deltaEntry)) {
            portletURL.setParameter("deltaEntry", deltaEntry);
        }

        String displayStyle = ParamUtil.getString(request, "displayStyle");

        if (Validator.isNotNull(displayStyle)) {
            portletURL.setParameter("displayStyle", getDisplayStyle());
        }

        String keywords = ParamUtil.getString(request, "keywords");

        if (Validator.isNotNull(keywords)) {
            portletURL.setParameter("keywords", keywords);
        }

        String orderByCol = getOrderByCol();

        if (Validator.isNotNull(orderByCol)) {
            portletURL.setParameter("orderByCol", orderByCol);
        }

        String orderByType = getOrderByType();

        if (Validator.isNotNull(orderByType)) {
            portletURL.setParameter("orderByType", orderByType);
        }

        if (!isShowEditActions()) {
            portletURL.setParameter(
                "showEditActions", String.valueOf(isShowEditActions()));
        }

        return portletURL;
    }

    @Deprecated
    public List<ManagementBarFilterItem> getManagementBarStatusFilterItems()
        throws PortalException, PortletException {

        List<ManagementBarFilterItem> managementBarFilterItems =
            new ArrayList<>();

        managementBarFilterItems.add(
            getManagementBarFilterItem(WorkflowConstants.STATUS_ANY));
        managementBarFilterItems.add(
            getManagementBarFilterItem(WorkflowConstants.STATUS_DRAFT));
        managementBarFilterItems.add(
            getManagementBarFilterItem(WorkflowConstants.STATUS_PENDING));
        managementBarFilterItems.add(
            getManagementBarFilterItem(WorkflowConstants.STATUS_DENIED));
        managementBarFilterItems.add(
            getManagementBarFilterItem(WorkflowConstants.STATUS_APPROVED));

        return managementBarFilterItems;
    }

    @Deprecated
    public String getManagementBarStatusFilterValue() {

        return WorkflowConstants.getStatusLabel(getStatus());
    }

    public Reference getReference()
        throws PortalException {

        if (reference != null) {

            return reference;
        }

        reference = ActionUtil.getReference(request);

        return reference;
    }

    public String getSearchActionURL() {

        PortletURL portletURL = liferayPortletResponse.createRenderURL();

        portletURL.setParameter(
            "showEditActions", String.valueOf(isShowEditActions()));

        return portletURL.toString();
    }

    public SearchContainer<Reference> getSearchContainer()
        throws PortalException {

        ThemeDisplay themeDisplay =
            (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        SearchContainer<Reference> referenceSearchContainer = new SearchContainer<>(
            liferayPortletRequest, getPortletURL(), null, null);

        OrderByComparator<Reference> orderByComparator =
            ReferenceDxpPortletUtil.getReferenceOrderByComparator(
                getOrderByCol(), getOrderByType());

        referenceSearchContainer.setOrderByCol(getOrderByCol());
        referenceSearchContainer.setOrderByComparator(orderByComparator);
        referenceSearchContainer.setOrderByType(getOrderByType());

        ReferencesChecker referencesChecker = new ReferencesChecker(
            liferayPortletRequest, liferayPortletResponse, trashHelper);

        referencesChecker.setCssClass("entry-selector");
        referencesChecker.setRememberCheckBoxStateURLRegex(
            "^(?!.*" + liferayPortletResponse.getNamespace() + "redirect).*");

        referenceSearchContainer.setRowChecker(referencesChecker);

        ReferencesMover referencesMover = new ReferencesMover(
            trashHelper.isTrashEnabled(themeDisplay.getScopeGroupId()));

        referenceSearchContainer.setRowMover(referencesMover);

        if (isSearch()) {

            int total = ReferenceServiceUtil.searchCount(
                themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
                getKeywords(), -1.0, null, null, getStatus());

            referenceSearchContainer.setTotal(total);

            List<Reference> results = ReferenceServiceUtil.search(
                themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
                getKeywords(), -1.0, null, null, getStatus(),
                referenceSearchContainer.getStart(),
                referenceSearchContainer.getEnd(),
                referenceSearchContainer.getOrderByComparator());

            referenceSearchContainer.setResults(results);

            referenceSearchContainer.setSearch(true);
        } else {

            int total = ReferenceServiceUtil.getGroupReferencesCount(
                themeDisplay.getScopeGroupId(), getStatus());

            referenceSearchContainer.setTotal(total);

            List<Reference> results = ReferenceServiceUtil.getGroupReferences(
                themeDisplay.getScopeGroupId(), getStatus(),
                referenceSearchContainer.getStart(),
                referenceSearchContainer.getEnd(),
                referenceSearchContainer.getOrderByComparator());

            referenceSearchContainer.setResults(results);
        }

        return referenceSearchContainer;
    }

    public String getSortingURL() {

        PortletURL sortingURL = getPortletURL();

        sortingURL.setParameter(
            "orderByType",
            Objects.equals(getOrderByType(), "asc") ? "desc" : "asc");

        return sortingURL.toString();
    }

    public int getStatus() {

        if (status != null) {

            return status;
        }

        ThemeDisplay themeDisplay =
            (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        int defaultStatus = WorkflowConstants.STATUS_APPROVED;

        PermissionChecker permissionChecker =
            themeDisplay.getPermissionChecker();

        if (permissionChecker.isContentReviewer(
            themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId())) {

            defaultStatus = WorkflowConstants.STATUS_ANY;
        }

        status = ParamUtil.getInteger(request, "status", defaultStatus);

        return status;
    }

    public int getTotalItems()
        throws PortalException {

        SearchContainer referenceSearch = getSearchContainer();

        return referenceSearch.getTotal();
    }

    public List<ViewTypeItem> getViewTypeItems() {

        return new ViewTypeItemList(getPortletURL(), getDisplayStyle()) {

            {
                if (ArrayUtil.contains(getDisplayViews(), "icon")) {
                    addCardViewTypeItem();
                }

                if (ArrayUtil.contains(getDisplayViews(), "descriptive")) {
                    addListViewTypeItem();
                }

                if (ArrayUtil.contains(getDisplayViews(), "list")) {
                    addTableViewTypeItem();
                }
            }
        };
    }

    public boolean hasResults()
        throws PortalException {

        return getTotalItems() > 0;
    }

    public boolean isDisabled()
        throws PortalException {

        if (hasResults()) {
            return false;
        }

        if (isSearch()) {
            return false;
        }

        if (getStatus() != WorkflowConstants.STATUS_ANY) {

            return false;
        }

        return true;
    }

    public boolean isSearch() {

        return Validator.isNotNull(getKeywords());
    }

    public boolean isShowAddButton()
        throws PortalException {

        ThemeDisplay themeDisplay =
            (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        Group group = themeDisplay.getScopeGroup();

        if (group.isLayout()) {
            group = group.getParentGroup();
        }

        if (group.isStaged() && !group.isStagingGroup() &&
            !group.isStagedRemotely() &&
            group.isStagedPortlet(ReferencePortletKeys.REFERENCE_DXP_PORTLET)) {

            return false;
        }

        if (ReferenceAdminPermission.contains(
            themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(),
            ReferenceActionKeys.ADD_REFERENCE)) {
            return true;
        }

        return false;
    }

    public boolean isShowEditActions() {

        if (showEditActions != null) {

            return showEditActions;
        }

        showEditActions =
            ParamUtil.getBoolean(request, "showEditActions", true);

        return showEditActions;
    }

    public boolean isShowInfoButton() {

        return !isSearch();
    }

    public boolean isShowSearch()
        throws PortalException {

        return hasResults() || isSearch();
    }

    @Deprecated
    protected ManagementBarFilterItem getManagementBarFilterItem(int status)
        throws PortalException, PortletException {

        boolean active = false;

        if (status == getStatus()) {
            active = true;
        }

        PortletURL portletURL =
            PortletURLUtil.clone(getPortletURL(), liferayPortletResponse);

        portletURL.setParameter("status", String.valueOf(status));

        return new ManagementBarFilterItem(
            active, WorkflowConstants.getStatusLabel(status),
            portletURL.toString());
    }

    private List<DropdownItem> getFilterStatusDropdownItems() {

        return new DropdownItemList() {

            {
                for (int status : getStatuses()) {
                    add(dropdownItem -> {
                        dropdownItem.setActive(getStatus() == status);
                        dropdownItem.setHref(
                            getPortletURL(), "status", String.valueOf(status));
                        dropdownItem.setLabel(
                            LanguageUtil.get(
                                request,
                                WorkflowConstants.getStatusLabel(status)));
                    });
                }
            }
        };
    }

    private Consumer<DropdownItem> getOrderByDropdownItem(
        final String orderByCol) {

        return dropdownItem -> {
            dropdownItem.setActive(orderByCol.equals(getOrderByCol()));
            dropdownItem.setHref(getPortletURL(), "orderByCol", orderByCol);
            dropdownItem.setLabel(LanguageUtil.get(request, orderByCol));
        };
    }

    private List<DropdownItem> getOrderByDropdownItems() {

        return new DropdownItemList() {

            {
                for (String orderColumn : getOrderColumns()) {
                    add(getOrderByDropdownItem(orderColumn));
                }
            }
        };
    }

    private List<Integer> getStatuses() {

        List<Integer> statuses = new ArrayList<>();

        statuses.add(WorkflowConstants.STATUS_ANY);
        statuses.add(WorkflowConstants.STATUS_DRAFT);

        ThemeDisplay themeDisplay =
            (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        int workflowDefinitionLinksCount =
            WorkflowDefinitionLinkLocalServiceUtil.getWorkflowDefinitionLinksCount(
                themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
                Reference.class.getName());

        if (workflowDefinitionLinksCount > 0) {
            statuses.add(WorkflowConstants.STATUS_PENDING);
            statuses.add(WorkflowConstants.STATUS_DENIED);
        }

        statuses.add(WorkflowConstants.STATUS_SCHEDULED);
        statuses.add(WorkflowConstants.STATUS_APPROVED);
        statuses.add(WorkflowConstants.STATUS_EXPIRED);

        return statuses;
    }

}
