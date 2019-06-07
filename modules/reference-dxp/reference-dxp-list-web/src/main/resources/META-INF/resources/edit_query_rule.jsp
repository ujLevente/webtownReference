<%@ include file="/init.jsp" %>

<%
String portletResource = ParamUtil.getString(request, "portletResource");

String portletResourceNamespace = StringPool.BLANK;

boolean useNamespace = true;

if (Validator.isNotNull(portletResource)) {
    
    useNamespace = false;
    portletResourceNamespace = PortalUtil.getPortletNamespace(portletResource);
}

String randomNamespace = PortalUtil.generateRandomKey(request, "portlet_reference_dxp_list_edit_query_rule") + StringPool.UNDERLINE;

long[] categorizableGroupIds = (long[])request.getAttribute("configuration.jsp-categorizableGroupIds");

if (categorizableGroupIds == null) {
    
	categorizableGroupIds = StringUtil.split(ParamUtil.getString(request, "categorizableGroupIds"), 0L);
}

int index = ParamUtil.getInteger(request, "index", GetterUtil.getInteger((String)request.getAttribute("configuration.jsp-index")));
int queryLogicIndex = GetterUtil.getInteger((String)request.getAttribute("configuration.jsp-queryLogicIndex"));

boolean queryContains = true;
boolean queryAndOperator = false;
String queryName = "assetTags";
String queryValues = null;

if (queryLogicIndex >= 0) {

    queryContains = PrefsParamUtil.getBoolean(portletPreferences, request, "queryContains" + queryLogicIndex, true);
	queryAndOperator = PrefsParamUtil.getBoolean(portletPreferences, request, "queryAndOperator" + queryLogicIndex);
	queryName = PrefsParamUtil.getString(portletPreferences, request, "queryName" + queryLogicIndex, "assetTags");
	queryValues = StringUtil.merge(portletPreferences.getValues("queryValues" + queryLogicIndex, new String[0]));
	
	if (Objects.equals(queryName, "assetTags")) {

		queryValues = ParamUtil.getString(request, "queryTagNames" + queryLogicIndex, queryValues);

		queryValues = referenceDxpListDisplayContext.filterAssetTagNames(scopeGroupId, queryValues);
	} else {

	    queryValues = ParamUtil.getString(request, "queryCategoryIds" + queryLogicIndex, queryValues);
	}
}
%>

<div class="field-row form-inline query-row">
	<aui:select inlineField="<%= true %>" label="" name='<%= portletResourceNamespace + "queryContains" + index %>' title="query-contains" useNamespace="<%= useNamespace %>">
		<aui:option label="contains" selected="<%= queryContains %>" value="<%= true %>" />
		<aui:option label="does-not-contain" selected="<%= !queryContains %>" value="<%= false %>" />
	</aui:select>

	<aui:select inlineField="<%= true %>" label="" name='<%= portletResourceNamespace + "queryAndOperator" + index %>' title="and-operator" useNamespace="<%= useNamespace %>">
		<aui:option label="all" selected="<%= queryAndOperator %>" value="<%= true %>" />
		<aui:option label="any" selected="<%= !queryAndOperator %>" value="<%= false %>" />
	</aui:select>

	<aui:select cssClass="asset-query-name" id='<%= portletResourceNamespace + randomNamespace + "selector" %>' inlineField="<%= true %>" label="of-the-following" name='<%= portletResourceNamespace + "queryName" + index %>' useNamespace="<%= useNamespace %>">
		<aui:option label="tags" selected='<%= Objects.equals(queryName, "assetTags") %>' value="assetTags" />
		<aui:option label="categories" selected='<%= Objects.equals(queryName, "assetCategories") %>' value="assetCategories" />
	</aui:select>

	<div class="field tags-selector <%= Objects.equals(queryName, "assetTags") ? StringPool.BLANK : "hide" %>">
		<c:choose>
			<c:when test="<%= useNamespace %>">
				<liferay-ui:asset-tags-selector
					curTags='<%= Objects.equals(queryName, "assetTags") ? queryValues : null %>'
					groupIds="<%= categorizableGroupIds %>"
					hiddenInput='<%= "queryTagNames" + index %>'
				/>
			</c:when>
			<c:otherwise>
				<div class="lfr-tags-selector-content" id="<%= portletResourceNamespace + randomNamespace %>assetTagsSelector">
					<aui:input name='<%= portletResourceNamespace + "queryTagNames" + index %>' type="hidden" useNamespace="<%= useNamespace %>" />
					<input class="form-control lfr-tag-selector-input" id="<%= randomNamespace %>assetTagNames" maxlength="<%= ModelHintsConstants.TEXT_MAX_LENGTH %>" size="15" title="<liferay-ui:message key="add-tags" />" type="text" />
				</div>
				<aui:script use="liferay-asset-tags-selector">
					var assetTagsSelector = new Liferay.AssetTagsSelector(
						{
							allowAddEntry: <%= true %>,
							contentBox: '#<%= portletResourceNamespace + randomNamespace %>assetTagsSelector',
							curEntries: '<%= Objects.equals(queryName, "assetTags") && Validator.isNotNull(queryValues) ? HtmlUtil.escapeJS(queryValues) : StringPool.BLANK %>',

							<c:if test="<%= categorizableGroupIds != null %>">
								groupIds: '<%= StringUtil.merge(categorizableGroupIds) %>',
							</c:if>

							hiddenInput: '#<%= portletResourceNamespace + "queryTagNames" + index %>',
							input: '#<%= randomNamespace %>assetTagNames',
							instanceVar: '<%= portletResourceNamespace + randomNamespace %>',
							portalModelResource: <%= false %>
						}
			        ).render();
				</aui:script>
			</c:otherwise>
		</c:choose>
	</div>

	<div class="categories-selector field <%= Objects.equals(queryName, "assetCategories") ? StringPool.BLANK : "hide" %>">
		<c:choose>
			<c:when test="<%= useNamespace %>">
				<liferay-ui:asset-categories-selector
					curCategoryIds='<%= Objects.equals(queryName, "assetCategories") ? queryValues : null %>'
					groupIds="<%= categorizableGroupIds %>"
					hiddenInput='<%= "queryCategoryIds" + index %>'
				/>
			</c:when>
			<c:otherwise>

				<%
				if (ArrayUtil.isEmpty(categorizableGroupIds)) {

				    categorizableGroupIds = PortalUtil.getCurrentAndAncestorSiteGroupIds(scopeGroupId);
				} else {

				    categorizableGroupIds = PortalUtil.getCurrentAndAncestorSiteGroupIds(categorizableGroupIds);
				}

				List<AssetVocabulary> vocabularies = AssetVocabularyServiceUtil.getGroupVocabularies(categorizableGroupIds);

				String categoryNames = StringPool.BLANK;

				String[] categoryIdsTitles = AssetCategoryUtil.getCategoryIdsTitles(queryValues, categoryNames, 0, themeDisplay);

				int maxEntries = GetterUtil.getInteger(PropsUtil.get(PropsKeys.ASSET_CATEGORIES_SELECTOR_MAX_ENTRIES));
				%>

				<div class="lfr-tags-selector-content" id="<%= portletResourceNamespace + randomNamespace %>assetCategoriesSelector">
					<aui:input name='<%= portletResourceNamespace + "queryCategoryIds" + index %>' type="hidden" useNamespace="<%= useNamespace %>" />
        		</div>
        		<aui:script use="liferay-asset-categories-selector">
					var assetCategoriesSelector = new Liferay.AssetCategoriesSelector(
						{
							className: '<%= StringPool.BLANK %>',
							contentBox: '#<%= portletResourceNamespace + randomNamespace %>assetCategoriesSelector',
							curEntries: '<%= HtmlUtil.escapeJS(categoryIdsTitles[1]) %>',
							curEntryIds: '<%= categoryIdsTitles[0] %>',
							hiddenInput: '#<%= portletResourceNamespace + "queryCategoryIds" + index %>',
							instanceVar: '<%= portletResourceNamespace + randomNamespace %>',
							maxEntries: <%= maxEntries %>,
							moreResultsLabel: '<%= UnicodeLanguageUtil.get(resourceBundle, "load-more-results") %>',
							portalModelResource: <%= false %>,
							vocabularyGroupIds: '<%= StringUtil.merge(categorizableGroupIds) %>',
							vocabularyIds: '<%= ListUtil.toString(vocabularies, "vocabularyId") %>'
						}
					).render();
				</aui:script>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<aui:script sandbox="<%= true %>">
	var select = $('#<%= portletResourceNamespace + randomNamespace %>selector');
	
	<c:if test="<%= useNamespace %>">
		select = $('#<portlet:namespace /><%= randomNamespace %>selector');
	</c:if>

	var row = select.closest('.query-row');

	select.on(
		'change',
		function(event) {
			var tagsSelector = row.find('.tags-selector');
			var categoriesSelector = row.find('.categories-selector');

			var assetTags = (select.val() == 'assetTags');

			tagsSelector.toggleClass('hide', !assetTags);
			categoriesSelector.toggleClass('hide', assetTags);
		}
	);
</aui:script>