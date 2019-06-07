<%@ include file="/init.jsp" %>

<%
String searchContainerId = ParamUtil.getString(request, "searchContainerId");
%>

<clay:management-toolbar
	actionDropdownItems="<%= referenceDisplayContext.getActionDropdownItems() %>"
	clearResultsURL="<%= referenceDisplayContext.getClearResultsURL() %>"
	componentId="referenceWebManagementToolbar"
	creationMenu="<%= referenceDisplayContext.getCreationMenu() %>"
	disabled="<%= referenceDisplayContext.isDisabled() %>"
	filterDropdownItems="<%= referenceDisplayContext.getFilterDropdownItems() %>"
	infoPanelId="infoPanelId"
	itemsTotal="<%= referenceDisplayContext.getTotalItems() %>"
	searchActionURL="<%= referenceDisplayContext.getSearchActionURL() %>"
	searchContainerId="<%= searchContainerId %>"
	searchFormName="fm1"
	showCreationMenu="<%= referenceDisplayContext.isShowAddButton() %>"
	showInfoButton="<%= referenceDisplayContext.isShowInfoButton() %>"
	showSearch="<%= referenceDisplayContext.isShowSearch() %>"
	sortingOrder="<%= referenceDisplayContext.getOrderByType() %>"
	sortingURL="<%= referenceDisplayContext.getSortingURL() %>"
	viewTypeItems="<%= referenceDisplayContext.getViewTypeItems() %>"
/>

<aui:script sandbox="<%= true %>">
	var deleteReferences = function() {
		if (<%= trashHelper.isTrashEnabled(scopeGroupId) %> || confirm(' <%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-delete-the-selected-entries") %>')) {
			Liferay.fire(
				'<%= renderResponse.getNamespace() %>editReference',
				{
					action: '<%= trashHelper.isTrashEnabled(scopeGroupId) ? "moveReferencesToTrash" : "deleteReferences" %>'
				}
			);
		}
	}
	
	var ACTIONS = {
		'deleteReferences': deleteReferences
	};
	
	Liferay.componentReady('referenceWebManagementToolbar').then(
		function(managementToolbar) {
		    managementToolbar.on(
			    ['actionItemClicked', 'filterItemClicked'],
			    function(event) {
					var itemData = event.data.item.data;
					
					if (itemData && itemData.action && ACTIONS[itemData.action]) {
					    ACTIONS[itemData.action]();
					}
			    }
		    );
		}
	);
</aui:script>
