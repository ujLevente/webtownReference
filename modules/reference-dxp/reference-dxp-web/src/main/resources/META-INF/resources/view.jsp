<%@ include file="/init.jsp" %>

<portlet:actionURL name="restoreTrashEntries" var="restoreTrashEntriesURL" />

<liferay-trash:undo
	portletURL="<%= restoreTrashEntriesURL %>"
/>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= referenceDisplayContext.getNavigationBarItems() %>"
/>

<liferay-util:include page="/toolbar.jsp" servletContext="<%= application %>">
	<liferay-util:param name="searchContainerId" value="references" />
</liferay-util:include>

<div id="<portlet:namespace />referenceContainer">
	<div class="closed container-fluid-1280 sidenav-container sidenav-right" id="<portlet:namespace />infoPanelId">
		<c:if test="<%=  referenceDisplayContext.isShowInfoButton() %>">
			<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/reference/info_panel" var="sidebarPanelURL" />

			<liferay-frontend:sidebar-panel
				resourceURL="<%= sidebarPanelURL %>"
				searchContainerId="articles"
			>
				<liferay-util:include page="/info_panel.jsp" servletContext="<%= application %>" />
			</liferay-frontend:sidebar-panel>
		</c:if>
	</div>
</div>


















<%
Map<String, Object> data = new HashMap<>();

data.put("qa-id", "navigation");
%>

<aui:nav-bar cssClass="collapse-basic-search" data="<%= data %>" markupView="lexicon">
	<portlet:renderURL var="mainURL" />

	<aui:nav cssClass="navbar-nav">
		<aui:nav-item href="<%= mainURL.toString() %>" label="reference" selected="<%= true %>" />
	</aui:nav>

	<c:if test="<%= referenceDisplayContext.isShowSearch() %>">
		<aui:nav-bar-search>

			<%
			PortletURL portletURL = liferayPortletResponse.createRenderURL();

			portletURL.setParameter("showEditActions", String.valueOf(referenceDisplayContext.isShowEditActions()));
			%>

			<aui:form action="<%= portletURL.toString() %>" method="post" name="fm1">
				<liferay-ui:input-search markupView="lexicon" />
			</aui:form>
		</aui:nav-bar-search>
	</c:if>
</aui:nav-bar>

<liferay-util:include page="/toolbar.jsp" servletContext="<%= application %>">
	<liferay-util:param name="searchContainerId" value="references" />
</liferay-util:include>

<div id="<portlet:namespace />referenceContainer">
	<div class="container-fluid-1280">

		<%
		PortletURL portletURL = referenceDisplayContext.getPortletURL();
		%>
		
		<aui:form action="<%= portletURL.toString() %>" method="get" name="fm">
			<aui:input name="<%= ActionRequest.ACTION_NAME %>" type="hidden" />
			<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
			<aui:input name="groupId" type="hidden" value="<%= scopeGroupId %>" />
			
			<div class="reference-container" id="<portlet:namespace />entriesContainer">
				<liferay-util:include page="/view_entries.jsp" servletContext="<%= application %>">
					<liferay-util:param name="searchContainerId" value="references" />
				</liferay-util:include>
			</div>
		</aui:form>
	</div>
</div>

<c:if test="<%= !referenceDisplayContext.isSearch() %>">
	<liferay-util:include page="/add_button.jsp" servletContext="<%= application %>" />
</c:if>

<aui:script use="reference-dxp-navigation">
	var referenceNavigation = new Liferay.Portlet.ReferenceNavigation(
		{
		    editReferenceUrl: '<portlet:actionURL />',
		    form: {
				method: 'POST',
				node: A.one(document.<portlet:namespace />fm)
		    },
		    namespace: '<portlet:namespace />',
		    searchContainerId: 'references'
		}
	);
	
	var clearReferenceNavigationHandles = function(event) {
	    if (event.portletId === '<%= portletDisplay.getRootPortletId() %>') {
			referenceNavigation.destroy();
			
			Liferay.detach('destroyPortlet', clearReferenceNavigationHandles);
	    }
	};
	
	Liferay.on('destroyPortlet', clearReferenceNavigationHandles);
</aui:script>