<%@ include file="/init.jsp" %>

<c:if test="<%= ReferenceAdminPermission.contains(permissionChecker, scopeGroupId, ReferenceActionKeys.ADD_REFERENCE) %>">
	<liferay-frontend:add-menu>
		<portlet:renderURL var="addReferenceURL">
			<portlet:param name="mvcPath" value="/edit_reference.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="groupId" value="<%= String.valueOf(scopeGroupId) %>" />
		</portlet:renderURL>
		
		<liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request, "add-reference") %>' url="<%= addReferenceURL.toString() %>" />
	</liferay-frontend:add-menu>
</c:if>