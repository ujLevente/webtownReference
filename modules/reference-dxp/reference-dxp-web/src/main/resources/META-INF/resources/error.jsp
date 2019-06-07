<%@ include file="/init.jsp" %>

<liferay-ui:error-header />

<liferay-ui:error exception="<%= NoSuchReferenceException.class %>" message="the-reference-could-not-be-found" />

<liferay-ui:error-principal />