<%@page import="com.liferay.trash.kernel.util.TrashUtil"%>
<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String referringPortletResource = ParamUtil.getString(request, "referringPortletResource");

ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Reference reference = (Reference)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= ReferencePermission.contains(permissionChecker, reference, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcPath" value="/edit_reference.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="referringPortletResource" value="<%= referringPortletResource %>" />
			<portlet:param name="groupId" value="<%= String.valueOf(reference.getGroupId()) %>" />
			<portlet:param name="referenceId" value="<%= reference.getReferenceId() %>" />
			<portlet:param name="version" value="<%= String.valueOf(reference.getVersion()) %>" />
		</portlet:renderURL>

			<liferay-ui:icon image="edit" url="<%= editURL %>" />
	</c:if>

	<c:if test="<%= ReferencePermission.contains(permissionChecker, reference, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= Reference.class.getName() %>"
			modelResourceDescription="<%= HtmlUtil.escape(reference.getName()) %>"
			resourcePrimKey="<%= String.valueOf(reference.getResourcePrimKey()) %>"
			var="permissionsURL"
			windowState="<%= LiferayWindowState.POP_UP.toString() %>"
		/>

		<liferay-ui:icon
			image="permissions"
			method="get"
			url="<%= permissionsURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>

	<c:if test="<%= ReferencePermission.contains(permissionChecker, reference, ActionKeys.DELETE) %>">
		<portlet:actionURL name='<%= TrashUtil.isTrashEnabled(scopeGroupId) ? "moveToTrash" : "deleteReferences" %>' var="deleteURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="groupId" value="<%= String.valueOf(reference.getGroupId()) %>" />
			<portlet:param name="referenceId" value="<%= reference.getReferenceId() %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			trash="<%= TrashUtil.isTrashEnabled(scopeGroupId) %>"
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>