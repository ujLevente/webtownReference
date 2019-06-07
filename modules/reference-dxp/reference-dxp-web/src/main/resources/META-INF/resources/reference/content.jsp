<%@ include file="/init.jsp" %>

<%
Reference reference = referenceDisplayContext.getReference();

long groupId = BeanParamUtil.getLong(reference, request, "groupId", scopeGroupId);

String description = BeanParamUtil.getString(reference, request, "description");
%>

<liferay-ui:error-marker key="<%= WebKeys.ERROR_SECTION %>" value="content" />

<aui:model-context bean="<%= reference %>" model="<%= Reference.class %>" />

<liferay-ui:error exception="<%= DuplicateUrlTitleException.class %>" message="please-enter-a-unique-url-title" />
<liferay-ui:error exception="<%= ReferenceNameException.class %>" message="please-enter-a-valid-name" />
<liferay-ui:error exception="<%= ReferenceDescriptionException.class %>" message="please-enter-a-valid-description" />
<liferay-ui:error exception="<%= ReferenceShortDescriptionException.class %>" message="please-enter-a-valid-short-description" />
<liferay-ui:error exception="<%= ReferenceUrlTitleException.class %>" message="please-enter-a-valid-url-title" />

<aui:fieldset>
	<aui:input autoFocus="<%= true %>" name="name" wrapperCssClass="reference-content-name">
		<aui:validator name="required" />
	</aui:input>
	
	<aui:input label="emphasized-reference" name="emphasized" type="toggle-switch" value="<%= (reference != null) ? reference.isEmphasized() : false %>" />
        
    <aui:input name="overlayText" />
        
    <aui:input name="overlayUrl" />
    
    <aui:input name="urlTitle">
        <aui:validator name="required" />
    </aui:input>
	
	<aui:input label="short-description" name="shortDescription" type="textarea" />
	
	<aui:field-wrapper cssClass="field-wrapper-html" label="description" name="descriptionEditor" required="<%= true %>">
		<div class="form-group">
			<liferay-ui:input-editor
				configKey="referenceEditorConfigKey"
				contents="<%= description %>"
				editorName="ckeditor"
				name="descriptionEditor"
				onChangeMethod="descriptionOnChangeEditor"
				toolbarSet="reference"
			/>
			
			<aui:input name="description" type="hidden" value="<%= description %>">
				<aui:validator name="required" />
			</aui:input>
			
			<aui:script>
				Liferay.provide(
					window,
					'<portlet:namespace />descriptionOnChangeEditor',
					function() {
					    var A = AUI();
					    
					    var field = A.one('#<portlet:namespace />description');
					    
					    field.val(window['<portlet:namespace />descriptionEditor'].getHTML());
					    
					    var form = field.get('form');
					    
					    if (form) {
						
							var formName = form.get('name');
							
							var formValidator = Liferay.Form.get(formName).formValidator;
							
							if (formValidator) {
							    
							    formValidator.validateField(field);
							}
					    }
					},
					['liferay-form']
				);
			</aui:script>
		</div>
	</aui:field-wrapper>
</aui:fieldset>