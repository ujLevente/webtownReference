<%@ include file="/init.jsp" %>

<%--
modules/apps/forms-and-workflow/dynamic-data-mapping/dynamic-data-mapping-service/src/main/resources/com/liferay/dynamic/data/mapping/dependencies/ddm/documentlibrary.ftl
--%>

<%
String originalFieldName = ParamUtil.getString(request, "fieldName");
String documentLibrarySelectorURL = ParamUtil.getString(request, "documentLibrarySelectorURL");
String fieldRawValue = ParamUtil.getString(request, "fieldRawValue");
String index = ParamUtil.getString(request, "index");

String fieldName = originalFieldName + index;

String fileEntryTitle = StringPool.BLANK;

try {
    
    FileEntry fileEntry = ReferenceUtil.getFileEntryFromJSON(scopeGroupId, fieldRawValue);
    
    if (fileEntry != null) {
        
        fileEntryTitle = fileEntry.getTitle();
    }
} catch (PortalException pe) {
    // ignore
}
%>

<aui:field-wrapper>
	<div class="form-group">
		<aui:input name="<%= fieldName %>" type="hidden" value="<%= fieldRawValue %>" />
		
		<aui:input inlineField="<%= true %>" label="" name='<%= originalFieldName + "Title" + index %>' readonly="readonly" type="text" value="<%= fileEntryTitle %>" />
		
		<aui:button-row>
			<aui:button cssClass="select-button" id='<%= fieldName + "SelectButton"  %>' value="select" />
			<aui:button cssClass="clear-button" onClick='<%= liferayPortletResponse.getNamespace() + fieldName + "clearFileEntry();" %>' value="clear" />
		</aui:button-row>
	</div>
</aui:field-wrapper>

<aui:script>

	Liferay.provide(
		window,
		'<portlet:namespace /><%= fieldName %>clearFileEntry',
		function() {
		    <portlet:namespace /><%= fieldName %>setFileEntry();
		},
		[]
	);
        
	Liferay.provide(
		window,
		'<portlet:namespace /><%= fieldName %>setFileEntry',
		function(uuid, groupId, title, type) {
			var A = AUI();

			var inputNode = A.one('#<portlet:namespace /><%= fieldName %>');

			if (inputNode) {

				if (uuid || title) {

					inputNode.val(
						A.JSON.stringify(
							{
								groupId: groupId,
								uuid: uuid,
								type: type
							}
						)
					);
				} else {

					inputNode.val('');
				}
			}

			var titleNode = A.one('#<portlet:namespace /><%= originalFieldName %>Title<%= index %>');

			if (titleNode) {

				titleNode.val(title);
			}
		},
		['json']
	);

</aui:script>

<aui:script use="aui-base,json,liferay-item-selector-dialog">

/*
	function <portlet:namespace />getDocumentLibrarySelectorURL() {
		var instance = this;

		var scopeGroupId = Liferay.ThemeDisplay.getScopeGroupId();

		var portletNamespace = instance.get('portletNamespace');

		var portletURL = Liferay.PortletURL.createURL(themeDisplay.getURLControlPanel());

		portletURL.setDoAsGroupId(scopeGroupId);
		portletURL.setParameter('criteria', 'com.liferay.item.selector.criteria.file.criterion.FileItemSelectorCriterion');
		portletURL.setParameter('itemSelectedEventName', portletNamespace + 'selectDocumentLibrary');

		var criterionJSON = {
			desiredItemSelectorReturnTypes: 'com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType,com.liferay.item.sel
ector.criteria.UploadableFileReturnType'
		};

		portletURL.setParameter('0_json', JSON.stringify(criterionJSON));
		portletURL.setParameter('1_json', JSON.stringify(criterionJSON));

		var uploadCriterionJSON = {
			desiredItemSelectorReturnTypes: 'com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType,com.liferay.item.sel
ector.criteria.UploadableFileReturnType',
			URL: <portlet:namespace />getUploadURL()
		};

		portletURL.setParameter('2_json', JSON.stringify(uploadCriterionJSON));
		portletURL.setPortletId(Liferay.PortletKeys.ITEM_SELECTOR);
		portletURL.setPortletMode('view');
		portletURL.setWindowState('pop_up');

		return portletURL.toString();
    }
	
	function getUploadURL() {
		var instance = this;

		var scopeGroupId = Liferay.ThemeDisplay.getScopeGroupId();

		var portletURL = Liferay.PortletURL.createURL(themeDisplay.getURLControlPanel());

		portletURL.setDoAsGroupId(scopeGroupId);
		portletURL.setLifecycle(Liferay.PortletURL.ACTION_PHASE);
		portletURL.setParameter('cmd', 'add_temp');
		portletURL.setParameter('javax.portlet.action', '/document_library/upload_file_entry');
		portletURL.setParameter('p_auth', Liferay.authToken);

		portletURL.setPortletId(Liferay.PortletKeys.DOCUMENT_LIBRARY);

		return portletURL.toString();
    }
*/

	var field = A.one('#<portlet:namespace /><%= fieldName %>SelectButton');

	if (field) {

		field.on(
			'click',
			function(event) {
			    
			    var itemSelectorDialog = new A.LiferayItemSelectorDialog(
				    {
						eventName: '<portlet:namespace />selectDocumentLibrary',
						on: {
						    selectedItemChange: function(event) {
								var selectedItem = event.newVal;
								
								if (selectedItem) {
								    var itemValue = JSON.parse(selectedItem.value);
								    
								    <portlet:namespace /><%= fieldName %>setFileEntry(itemValue.uuid, itemValue.groupId, itemValue.title, itemValue.type);
								}
						    }
						},
						url: '<%= documentLibrarySelectorURL %>'
				    }
			    );
			    
			    itemSelectorDialog.open();
			}
		);
	}

</aui:script>