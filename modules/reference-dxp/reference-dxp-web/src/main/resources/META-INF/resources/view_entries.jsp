<%@page import="hu.webtown.liferay.portlet.reference.model.Reference"%>
<%@page import="com.liferay.portal.kernel.dao.search.SearchContainer"%>
<%@ include file="/init.jsp" %>

<%
String referringPortletResource = ParamUtil.getString(request, "referringPortletResource");

SearchContainer<Reference> referenceSearchContainer = referenceDisplayContext.getSearchContainer();

request.setAttribute("view.jsp-total", String.valueOf(referenceSearchContainer.getTotal()));
request.setAttribute("view_entries.jsp-entryEnd", String.valueOf(referenceSearchContainer.getEnd()));
request.setAttribute("view_entries.jsp-entryStart", String.valueOf(referenceSearchContainer.getStart()));

String displayStyle = referenceDisplayContext.getDisplayStyle();

String searchContainerId = ParamUtil.getString(request, "searchContainerId");
%>

<liferay-ui:search-container
	emptyResultsMessage="no-references-were-found"
	id="<%= searchContainerId %>"
	searchContainer="<%= referenceSearchContainer %>"
>
	<liferay-ui:search-container-row
		className="hu.webtown.liferay.portlet.reference.model.Reference"
		keyProperty="referenceId"
		modelVar="reference"
	>

		<%
		PortletURL rowURL = null;

		if (referenceDisplayContext.isShowEditActions() && ReferencePermission.contains(permissionChecker, reference, ActionKeys.UPDATE)) {
            rowURL = liferayPortletResponse.createRenderURL();

            rowURL.setParameter("mvcPath", "/edit_reference.jsp");
            rowURL.setParameter("redirect", currentURL);
            rowURL.setParameter("referringPortletResource", referringPortletResource);
            rowURL.setParameter("groupId", String.valueOf(reference.getGroupId()));
            rowURL.setParameter("referenceId", reference.getReferenceId());
            rowURL.setParameter("version", String.valueOf(reference.getVersion()));
    	}
		%>

		<c:choose>
			<c:when test='<%= "descriptive".equals(displayStyle) %>'>
				<liferay-ui:search-container-column-text>
					<liferay-ui:user-portrait
						cssClass="user-icon-lg"
						userId="<%= reference.getUserId() %>"
					/>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					colspan="<%= 2 %>"
				>

					<%
					Date createDate = reference.getModifiedDate();
					
					String modifiedDateDescription = LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - createDate.getTime(), true);
					%>

					<h6 class="text-default">
						<liferay-ui:message arguments="<%= new String[] { HtmlUtil.escape(reference.getUserName()), modifiedDateDescription } %>" key="x-modified-x-ago" />
					</h6>

					<h5>
						<aui:a href="<%= rowURL != null ? rowURL.toString() : null %>">
							<%= HtmlUtil.escape(reference.getName()) %>
						</aui:a>
					</h5>
					
					<h6 class="text-default">
						<aui:workflow-status markupView="lexicon" showIcon="<%= false %>" showLabel="<%= false %>" status="<%= reference.getStatus() %>" />
					</h6>
				</liferay-ui:search-container-column-text>
				
				<c:if test="<%= referenceDisplayContext.isShowEditActions() %>">
					<liferay-ui:search-container-column-jsp
						path="/action.jsp"
					/>
				</c:if>
			</c:when>
			<c:when test='<%= "icon".equals(displayStyle) %>'>

				<%
				String imageUrl = null;
				
				List<ReferenceImage> smallReferenceImages = ReferenceImageLocalServiceUtil.getReferenceImages(reference.getGroupId(), reference.getReferenceId(), reference.getVersion(), ReferenceImageType.SMALL_IMAGE.getId());
				
				if (smallReferenceImages != null && !smallReferenceImages.isEmpty()) {
				    
				    ReferenceImage smallReferenceImage = smallReferenceImages.get(0);
				    
				    try {
				        
				        FileEntry fileEntry = ReferenceUtil.getFileEntryFromJSON(scopeGroupId, smallReferenceImage.getImage());
				        
						imageUrl = DLUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), themeDisplay, StringPool.BLANK, false, true);
				    } catch (PortalException pe) {
				        // ignore
				    }
				}
				
				row.setCssClass("entry-card lfr-asset-item " + row.getCssClass());
				%>

				<liferay-ui:search-container-column-text>
					<c:choose>
						<c:when test="<%= imageUrl != null %>">
							<liferay-frontend:vertical-card
								actionJsp='<%= referenceDisplayContext.isShowEditActions() ? "/action.jsp" : null %>'
								actionJspServletContext="<%= application %>"
								imageUrl="<%= imageUrl %>"
								resultRow="<%= row %>"
								rowChecker="<%= referenceSearchContainer.getRowChecker() %>"
								title="<%= reference.getName() %>"
								url="<%= rowURL != null ? rowURL.toString() : null %>"
							>
								<%@ include file="/vertical_card.jspf" %>
							</liferay-frontend:vertical-card>
						</c:when>
						<c:otherwise>
							<liferay-frontend:icon-vertical-card
								actionJsp='<%= referenceDisplayContext.isShowEditActions() ? "/action.jsp" : null %>'
								actionJspServletContext="<%= application %>"
								icon="reference"
								resultRow="<%= row %>"
								rowChecker="<%= referenceSearchContainer.getRowChecker() %>"
								title="<%= reference.getName() %>"
								url="<%= rowURL != null ? rowURL.toString() : null %>"
							>
								<%@ include file="/vertical_card.jspf" %>
							</liferay-frontend:icon-vertical-card>
						</c:otherwise>
					</c:choose>
				</liferay-ui:search-container-column-text>
			</c:when>
			<c:otherwise>
				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="id"
					property="referenceId"
				/>
				
				<liferay-ui:search-container-column-jsp
					href="<%= rowURL %>"
					name="name"
					path="/reference_name.jsp"
					truncate="<%= true %>"
				/>
					
				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="version"
					property="version"
				/>

<%--
				<c:if test="<%= portletId.equals(ReferencePortletKeys.REFERENCE_DXP_PORTLET) %>">
--%>					
					<liferay-ui:search-container-column-status
					      name="status"
					/>
<%--
				</c:if>
--%>

				<liferay-ui:search-container-column-date
					href="<%= rowURL %>"
					name="modified-date"
					value="<%= reference.getModifiedDate() %>"
				/>
					
				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="realization-date"
					value="<%= reference.getRealizationDate() != null ? dateFormatDate.format(reference.getRealizationDate()) : StringPool.BLANK %>"
				/>
					
				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="author"
					value="<%= HtmlUtil.escape(PortalUtil.getUserName(reference.getUserId(), reference.getUserName())) %>"
				/>
				
				<c:if test="<%= referenceDisplayContext.isShowEditActions() %>">
					<liferay-ui:search-container-column-jsp
						path="/action.jsp"
					/>
				</c:if>
			</c:otherwise>
		</c:choose>
	</liferay-ui:search-container-row>
	
	<liferay-ui:search-iterator
		displayStyle="<%= displayStyle %>"
		markupView="lexicon"
		searchContainer="<%= referenceSearchContainer %>"
	/>
</liferay-ui:search-container>