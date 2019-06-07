<%@ include file="/init.jsp" %>

<%
Reference reference = referenceDisplayContext.getReference();

long assetEntryId = 0;
long classPK = 0;

if (reference != null) {
    
	classPK = reference.getResourcePrimKey();
    
	if (!reference.isApproved() && (!ReferenceUtil.isDoublesEquals(reference.getVersion(), ReferenceConstants.VERSION_DEFAULT))) {
        
		AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(Reference.class.getName(), reference.getPrimaryKey());
        
		if (assetEntry != null) {
            
            assetEntryId = assetEntry.getEntryId();
            classPK = reference.getPrimaryKey();
        }
    }
}
%>

<liferay-ui:error-marker key="<%= WebKeys.ERROR_SECTION %>" value="related-assets" />

<aui:model-context bean="<%= reference %>" model="<%= Reference.class %>" />

<liferay-ui:input-asset-links
	assetEntryId="<%= assetEntryId %>"
	className="<%= Reference.class.getName() %>"
	classPK="<%= classPK %>"
/>
