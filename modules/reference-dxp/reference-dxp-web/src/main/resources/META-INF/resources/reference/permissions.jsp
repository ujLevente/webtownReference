<%@ include file="/init.jsp" %>

<%
Reference reference = referenceDisplayContext.getReference();
%>

<liferay-ui:error-marker key="<%= WebKeys.ERROR_SECTION %>" value="permissions" />

<aui:model-context bean="<%= reference %>" model="<%= Reference.class %>" />

<liferay-ui:input-permissions
	modelName="<%= Reference.class.getName() %>"
/>