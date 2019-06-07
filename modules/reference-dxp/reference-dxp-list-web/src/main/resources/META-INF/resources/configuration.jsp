<%@ include file="/init.jsp" %>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />
<liferay-portlet:renderURL portletConfiguration="<%= true %>" varImpl="configurationRenderURL" />

<%
int queryLogicIndexesCount = 0;
%>

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL.toString() %>" />
	
	<div class="portlet-configuration-body-content">
		<div class="container-fluid-1280">
			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" label="filter[action]">
					<div id="<portlet:namespace />queryRules">
						<aui:fieldset label="displayed-references-must-match-these-rules" markupView="lexicon">
							<liferay-ui:asset-tags-error />

							<%
							DuplicateQueryRuleException dqre = null;
							%>

							<liferay-ui:error exception="<%= DuplicateQueryRuleException.class %>">

								<%
								dqre = (DuplicateQueryRuleException)errorException;

								String name = dqre.getName();
								%>

								<liferay-util:buffer var="messageArgument">
									<em>(<liferay-ui:message key='<%= dqre.isContains() ? "contains" : "does-not-contain" %>' /> - <liferay-ui:message key='<%= dqre.isAndOperator() ? "all" : "any" %>' /> - <liferay-ui:message key='<%= name.equals(("assetTags")) ? "tags" : "categories" %>' />)</em>
								</liferay-util:buffer>
	
								<liferay-ui:message arguments="<%= messageArgument %>" key="only-one-rule-with-the-combination-x-is-supported" translateArguments="<%= false %>" />
							</liferay-ui:error>

							<%
							String queryLogicIndexesParam = ParamUtil.getString(request, "queryLogicIndexes");

			                int[] queryLogicIndexes = null;

			                if (Validator.isNotNull(queryLogicIndexesParam)) {

								queryLogicIndexes = StringUtil.split(queryLogicIndexesParam, 0);
			                } else {

								queryLogicIndexes = new int[0];

								for (int i = 0; true; i++) {

									String queryValues = PrefsParamUtil.getString(portletPreferences, request, "queryValues" + i);

									if (Validator.isNull(queryValues)) {

										break;
									}

									queryLogicIndexes = ArrayUtil.append(queryLogicIndexes, i);
								}

								if (queryLogicIndexes.length == 0) {

									queryLogicIndexes = ArrayUtil.append(queryLogicIndexes, -1);
								}
								
								queryLogicIndexesCount = queryLogicIndexes.length;

								int index = 0;

								for (int queryLogicIndex : queryLogicIndexes) {

								    String queryValues = StringUtil.merge(portletPreferences.getValues("queryValues" + queryLogicIndex, new String[0]));
			                        String tagNames = ParamUtil.getString(request, "queryTagNames" + queryLogicIndex, queryValues);
			                        String categoryIds = ParamUtil.getString(request, "queryCategoryIds" + queryLogicIndex, queryValues);

			                        if (Validator.isNotNull(tagNames) || Validator.isNotNull(categoryIds) || (queryLogicIndexes.length == 1)) {

			                            request.setAttribute("configuration.jsp-categorizableGroupIds", PortalUtil.getCurrentAndAncestorSiteGroupIds(new long[] { scopeGroupId }, true));
			                            request.setAttribute("configuration.jsp-index", String.valueOf(index));
			                            request.setAttribute("configuration.jsp-queryLogicIndex", String.valueOf(queryLogicIndex));

			                            String cssClass = StringPool.BLANK;

			                            if (dqre != null) {

			                                boolean queryContains = PrefsParamUtil.getBoolean(portletPreferences, request, "queryContains" + queryLogicIndex, true);
			                                boolean queryAndOperator = PrefsParamUtil.getBoolean(portletPreferences, request, "queryAndOperator" + queryLogicIndex);
			                                String queryName = PrefsParamUtil.getString(portletPreferences, request, "queryName" + queryLogicIndex, "assetTags");

			                                String dqreQueryName = dqre.getName();

			                                if ((dqre.isContains() == queryContains) && (dqre.isAndOperator() == queryAndOperator) && dqreQueryName.equals(queryName)) {

			                                    cssClass = "asset-query-rule-error";
			                                }
			                        	}
			                            %>

			                            <div class="lfr-form-row <%= cssClass %>">
											<div class="row-fields">
												<liferay-util:include page="/edit_query_rule.jsp" servletContext="<%= application %>">
													<liferay-util:param name="portletResource" value="<%= portletName %>" />
												</liferay-util:include>
											</div>
										</div>

			                            <%
			                        }

			                        index++;
								}
			                }
							%>

						</aui:fieldset>
					</div>

					<aui:script use="liferay-auto-fields">
						var autoFields = new Liferay.AutoFields(
							{
								contentBox: '#<portlet:namespace />queryRules',
								fieldIndexes: '<portlet:namespace />queryLogicIndexes',
								namespace: '<portlet:namespace />',
								url: '<liferay-portlet:renderURL portletName="<%= ReferenceDxpListPortletKeys.REFERENCE_DXP_LIST_PORTLET %>" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><liferay-portlet:param name="mvcPath" value="/edit_query_rule.jsp" /><liferay-portlet:param name="categorizableGroupIds" value="<%= StringUtil.merge(new long[] { scopeGroupId }) %>" /><liferay-portlet:param name="index" value="<%= String.valueOf(queryLogicIndexesCount) %>" /><liferay-portlet:param name="portletResource" value="<%= portletName %>" /></liferay-portlet:renderURL>'
							}
						).render();
					</aui:script>
				</aui:fieldset>
				<aui:fieldset>
					<div class="display-template">
						<liferay-ddm:template-selector
							className="<%= Reference.class.getName() %>"
							displayStyle="<%= referenceDxpListDisplayContext.getDisplayStyle() %>"
							displayStyleGroupId="<%= referenceDxpListDisplayContext.getDisplayStyleGroupId() %>"
							label="display-template"
							refreshURL="<%= configurationRenderURL.toString() %>"
							showEmptyOption="<%= true %>"
                        />
					</div>
				</aui:fieldset>
			</aui:fieldset-group>
		</div>
	</div>

	<aui:button-row>
        <aui:button cssClass="btn-lg" type="submit" />
	</aui:button-row>
</aui:form>
