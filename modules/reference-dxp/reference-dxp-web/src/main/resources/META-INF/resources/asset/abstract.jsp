<%@page import="com.liferay.portlet.asset.util.AssetUtil"%>
<%@ include file="/init.jsp" %>

<%
int abstractLength = GetterUtil.getInteger(request.getAttribute(WebKeys.ASSET_ENTRY_ABSTRACT_LENGTH), AssetUtil.ASSET_ENTRY_ABSTRACT_LENGTH);
String viewURL = (String) request.getAttribute(WebKeys.ASSET_ENTRY_VIEW_URL);

Reference reference = (Reference)request.getAttribute("REFERENCE");

String summary = HtmlUtil.escape(reference.getShortDescription());

summary = HtmlUtil.replaceNewLine(summary);

if (Validator.isNull(summary)) {
    
	summary = HtmlUtil.stripHtml(reference.getDescription());
}

List<ReferenceImage> smallImages = ReferenceImageLocalServiceUtil.getReferenceImages(reference.getGroupId(), reference.getReferenceId(), reference.getVersion(), ReferenceImageType.SMALL_IMAGE.getId());
ReferenceImage smallImage = smallImages != null && !smallImages.isEmpty() ? smallImages.get(0) : null;
FileEntry smallImageFileEntry = smallImage != null ? ReferenceUtil.getFileEntryFromJSON(scopeGroupId, smallImage.getImage()) : null;
%>

<c:if test="<%= smallImageFileEntry != null %>">
	<div class="asset-small-image">

		<%
		String smallImagePreviewURL = DLUtil.getPreviewURL(smallImageFileEntry, smallImageFileEntry.getFileVersion(), themeDisplay, StringPool.BLANK, false, true);
		
		if (Validator.isNotNull(viewURL)) {
		    %>

		    <a href="<%= HtmlUtil.escapeAttribute(viewURL) %>">

		    <%
		}
		%>

		<img
			alt="<%= HtmlUtil.escapeAttribute(reference.getName()) %>"
			class="asset-small-image img-thumbnail"
			src='<%= HtmlUtil.escapeAttribute(smallImagePreviewURL) %>'
			width="150"
		/>

		<%
		if (Validator.isNotNull(viewURL)) {
		    %>

		    </a>

		    <%
		}
		%>

	</div>
</c:if>

<%= StringUtil.shorten(summary, abstractLength) %>