<%@ include file="/init.jsp" %>

<%
Reference reference = referenceDisplayContext.getReference();
%>

<liferay-ui:error-marker key="<%= WebKeys.ERROR_SECTION %>" value="categorization" />

<aui:model-context bean="<%= reference %>" model="<%= Reference.class %>" />

<liferay-ui:asset-categories-error />

<liferay-ui:asset-tags-error />

<%
long classPK = 0;
double priority = 0;

if (reference != null) {
	classPK = reference.getResourcePrimKey();

	if (!reference.isApproved() && (reference.getVersion() != ReferenceConstants.VERSION_DEFAULT)) {
	    
		AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(Reference.class.getName(), reference.getPrimaryKey());

		if (assetEntry != null) {
		    
			classPK = reference.getPrimaryKey();
			priority = assetEntry.getPriority();
		}
	} else {
		AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(Reference.class.getName(), reference.getResourcePrimKey());

		if (assetEntry != null) {
		    
			priority = assetEntry.getPriority();
		}
	}
}
%>

<aui:input classPK="<%= classPK %>" name="categories" type="assetCategories" />

<aui:input classPK="<%= classPK %>" name="tags" type="assetTags" />

<aui:input label="priority" name="assetPriority" type="text" value="<%= priority %>">
	<aui:validator name="number" />

	<aui:validator name="min">[0]</aui:validator>
</aui:input>

<c:if test="<%= CustomAttributesUtil.hasCustomAttributes(company.getCompanyId(), Reference.class.getName(), classPK, null) %>">
	<liferay-ui:custom-attribute-list
		className="<%= Reference.class.getName() %>"
		classPK="<%= (reference != null) ? reference.getPrimaryKey() : 0 %>"
		editable="<%= true %>"
		label="<%= true %>"
	/>
</c:if>