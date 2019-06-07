<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Reference reference = (Reference)row.getObject();

String href = (String)request.getAttribute(WebKeys.SEARCH_ENTRY_HREF);

AssetRendererFactory<Reference> assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClass(Reference.class);

AssetRenderer<Reference> assetRenderer = assetRendererFactory.getAssetRenderer(ReferenceAssetRenderer.getClassPK(reference));

Map<String, Object> data = new HashMap<>();

data.put("placement", "top");
data.put("toggle", "tooltip");
%>

<aui:a data="<%= data %>" href="<%= href %>" title="<%= HtmlUtil.escape(reference.getName()) %>">
	<%= HtmlUtil.escape(reference.getName()) %>
</aui:a>

<c:if test="<%= reference.getGroupId() != scopeGroupId %>">
	<small class="group-info">
		<dl>

			<%
			Group group = GroupLocalServiceUtil.getGroup(reference.getGroupId());
			%>

			<c:if test="<%= !group.isLayout() || (group.getParentGroupId() != scopeGroupId) %>">
				<dt>
					<liferay-ui:message key="site" />:
				</dt>
				<dd>

					<%
					String groupDescriptiveName = null;

					if (group.isLayout()) {

						Group parentGroup = group.getParentGroup();

						groupDescriptiveName = parentGroup.getDescriptiveName(locale);
					} else {

						groupDescriptiveName = group.getDescriptiveName(locale);
					}
					%>

					<%= HtmlUtil.escape(groupDescriptiveName) %>

				</dd>
			</c:if>

			<c:if test="<%= group.isLayout() %>">
				<dt>
					<liferay-ui:message key="scope" />:
				</dt>
				<dd>
					<%= HtmlUtil.escape(group.getDescriptiveName(locale)) %>
				</dd>
			</c:if>
		</dl>
	</small>
</c:if>