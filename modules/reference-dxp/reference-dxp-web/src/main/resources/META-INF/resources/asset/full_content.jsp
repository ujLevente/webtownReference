<%@ include file="/init.jsp" %>

<%
Reference reference = (Reference)request.getAttribute("REFERENCE");
%>

<div class="reference-content">
	<%= reference.getDescription() %>
</div>