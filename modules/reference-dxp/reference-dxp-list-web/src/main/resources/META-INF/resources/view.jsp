<%@ include file="/init.jsp" %>

<%
Map<String, Object> contextObjects = new HashMap<>();

List<Reference> references = ReferenceDxpListUtil.getReferenceResults(referenceDxpListDisplayContext, portletPreferences);
%>

<liferay-ddm:template-renderer
	className="<%= Reference.class.getName() %>"
	contextObjects="<%= contextObjects %>"
	displayStyle="<%= referenceDxpListDisplayContext.getDisplayStyle() %>"
	displayStyleGroupId="<%= referenceDxpListDisplayContext.getDisplayStyleGroupId() %>"
	entries="<%= references %>"
>
	<c:choose>
		<c:when test="<%= references != null && !references.isEmpty() %>">
			<div class="row">

				<%
				String currentLetter = StringPool.BLANK;

				for (Reference curReference : references) {

				    String firstLetter = curReference.getName().substring(0, 1);

				    if (!firstLetter.equals(currentLetter)) {

				        currentLetter = firstLetter;
				        %>

				        <div class="col-xs-12 well"><h2><%= currentLetter %></h2></div>

				        <%
				    }
				    
				    List<ReferenceImage> smallImages = ReferenceImageLocalServiceUtil.getReferenceImages(curReference.getGroupId(), curReference.getReferenceId(), curReference.getVersion(), ReferenceImageType.SMALL_IMAGE.getId());
				    ReferenceImage smallImage = smallImages != null && !smallImages.isEmpty() ? smallImages.get(0) : null;
				    FileEntry smallImageFileEntry = smallImage != null ? ReferenceUtil.getFileEntryFromJSON(scopeGroupId, smallImage.getImage()) : null;
				    %>

				    <div class="col-sm-3 col-xs-12">
				    	<liferay-frontend:vertical-card
							imageUrl="<%= smallImageFileEntry != null ? DLUtil.getPreviewURL(smallImageFileEntry, smallImageFileEntry.getFileVersion(), themeDisplay, StringPool.BLANK, false, true) : StringPool.BLANK %>"
							title="<%= curReference.getName() %>"
						>
						</liferay-frontend:vertical-card>
				    </div>

				    <%
				}
				%>

			</div>
		</c:when>
		<c:otherwise>
			<liferay-ui:message key="there-are-no-references-were-found" />
		</c:otherwise>
	</c:choose>
</liferay-ddm:template-renderer>
