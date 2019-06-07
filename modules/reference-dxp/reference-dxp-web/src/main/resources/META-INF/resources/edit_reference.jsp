<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String portletResource = ParamUtil.getString(request, "portletResource");

long referringPlid = ParamUtil.getLong(request, "referringPlid");
String referringPortletResource = ParamUtil.getString(request, "referringPortletResource");

Reference reference = referenceDisplayContext.getReference();

long groupId = BeanParamUtil.getLong(reference, request, "groupId", scopeGroupId);

String referenceId = BeanParamUtil.getString(reference, request, "referenceId");

double version = BeanParamUtil.getDouble(reference, request, "version", ReferenceConstants.VERSION_DEFAULT);

request.setAttribute("edit_reference.jsp-redirect", redirect);

portletDisplay.setShowBackIcon(true);

if (Validator.isNotNull(redirect)) {
    
    portletDisplay.setURLBack(redirect);
} else if (reference != null) {
    
    PortletURL backURL = liferayPortletResponse.createRenderURL();
    
    backURL.setParameter("groupId", String.valueOf(reference.getGroupId()));
    
    portletDisplay.setURLBack(backURL.toString());
}

String title = StringPool.BLANK;

if (reference != null && !reference.isNew()) {
    
    title = reference.getName();
} else {
    
    title = LanguageUtil.get(request, "new-reference");
}

renderResponse.setTitle(title);
%>

<aui:model-context bean="<%= reference %>" model="<%= Reference.class %>" />

<c:if test="<%= (reference != null) && !reference.isNew() %>">
	<liferay-frontend:info-bar>
		<aui:workflow-status id="<%= String.valueOf(reference.getReferenceId()) %>" markupView="lexicon" showHelpMessage="<%= false %>" showIcon="<%= false %>" showLabel="<%= false %>" status="<%= reference.getStatus() %>" version="<%= String.valueOf(reference.getVersion()) %>" />
	</liferay-frontend:info-bar>
</c:if>

<portlet:actionURL var="editReferenceActionURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="mvcPath" value="/edit_reference.jsp" />
</portlet:actionURL>

<aui:form action="<%= editReferenceActionURL %>" cssClass="container-fluid-1280" enctype="multipart/form-data" method="post" name="fm" onSubmit="event.preventDefault();">
	<aui:input name="<%= ActionRequest.ACTION_NAME %>" type="hidden" />
	<aui:input name="hideDefaultSuccessMessage" type="hidden" value="<%= false %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="portletResource" type="hidden" value="<%= portletResource %>" />
	<aui:input name="referringPlid" type="hidden" value="<%= referringPlid %>" />
	<aui:input name="referringPortletResource" type="hidden" value="<%= referringPortletResource %>" />
	<aui:input name="groupId" type="hidden" value="<%= groupId %>" />
	<aui:input name="privateLayout" type="hidden" value="<%= layout.isPrivateLayout() %>" />
	<aui:input name="referenceId" type="hidden" value="<%= referenceId %>" />
	<aui:input name="referenceIds" type="hidden" value="<%= referenceId + ReferenceDxpPortlet.VERSION_SEPARATOR + version %>" />
	<aui:input name="version" type="hidden" value="<%= ((reference == null) || reference.isNew()) ? version : reference.getVersion() %>" />
	<aui:input name="workflowAction" type="hidden" value="<%= String.valueOf(WorkflowConstants.ACTION_SAVE_DRAFT) %>" />
	
	<%
	boolean approved = false;
	boolean pending = false;
	
	boolean workflowEnabled = WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(themeDisplay.getCompanyId(), groupId, Reference.class.getName());
	
	if (reference != null && version > 0) {
	    
	    approved = reference.isApproved();
	    
	    if (workflowEnabled) {
	        
	        pending = reference.isPending();
	    }
	}
	%>
	
	<c:if test="<%= approved %>">
		<div class="alert alert-info">
			<liferay-ui:message key="a-new-version-is-created-automatically-if-this-content-is-modified" />
		</div>
	</c:if>

	<c:if test="<%= pending %>">
		<div class="alert alert-info">
			<liferay-ui:message key="there-is-a-publication-workflow-in-process" />
		</div>
	</c:if>
	
	<%-- id used in hu.webtown.liferay.portlet.reference.web.servlet.taglib.ui.BaseReferenceFormNavigatorEntry class --%>
	<liferay-ui:form-navigator
		formModelBean="<%= reference %>"
		formName="fm"
		id="reference.form"
		markupView="lexicon"
		showButtons="<%= false %>"
	/>
	
	<aui:button-row cssClass="reference-button-row">

		<%
		boolean hasSavePermission = true;

		if (reference != null && !reference.isNew()) {

			hasSavePermission = ReferencePermission.contains(permissionChecker, reference, ActionKeys.UPDATE);
		} else {

			hasSavePermission = ReferenceAdminPermission.contains(permissionChecker, groupId, ReferenceActionKeys.ADD_REFERENCE);
		}

		String saveButtonLabel = "save";

		if ((reference == null) || reference.isApproved() || reference.isDraft()) {

			saveButtonLabel = "save-as-draft";
		}

		String publishButtonLabel = "publish";

		if (workflowEnabled) {

			publishButtonLabel = "submit-for-publication";
		}
		%>
		
		<c:if test="<%= hasSavePermission %>">
			<aui:button cssClass="btn-lg" data-actionname="<%= Constants.PUBLISH %>" disabled="<%= pending %>" name="publishButton" type="submit" value="<%= publishButtonLabel %>" />

			<aui:button cssClass="btn-lg" data-actionname='<%= ((reference == null) || Validator.isNull(reference.getReferenceId())) ? "addReference" : "updateReference" %>' name="saveButton" primary="<%= false %>" type="submit" value="<%= saveButtonLabel %>" />
		</c:if>

		<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script use="reference-dxp-portlet">
	new Liferay.Portlet.Reference(
		{
		    referenceId: '<%= reference != null ? HtmlUtil.escape(referenceId) : StringPool.BLANK %>',
		    namespace: '<portlet:namespace />'
		}
	);
</aui:script>
