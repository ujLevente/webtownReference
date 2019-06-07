<%@page import="java.util.Date"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil"%>
<%@page import="com.liferay.asset.kernel.model.AssetEntry"%>
<%@page import="hu.webtown.liferay.portlet.reference.web.asset.ReferenceAssetRenderer"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="hu.webtown.liferay.portlet.reference.constants.ReferenceWebKeys"%>
<%@page import="hu.webtown.liferay.portlet.reference.model.Reference"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp" %>

<%
List<Reference> references = (List<Reference>)request.getAttribute(ReferenceWebKeys.REFERENCES);
%>

<c:choose>
	<c:when test="<%= ListUtil.isNotEmpty(references) && references.size() == 1 %>">

		<%
		Reference reference = references.get(0);

		long classPK = ReferenceAssetRenderer.getClassPK(reference);

		AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(Reference.class.getName(), classPK);

		request.setAttribute("info_panel.jsp-entry", reference);
		%>

		<div class="sidebar-header">
			<h4><%= HtmlUtil.escape(assetEntry.getTitle()) %></h4>
		</div>

<%--
		<clay:navigation-bar
			navigationItems="<%= referenceDisplayContext.getInfoPanelNavigationItems() %>"
		/>
--%>

		<div class="sidebar-body">
			<h5><liferay-ui:message key="id" /></h5>

			<p>
				<%= HtmlUtil.escape(reference.getReferenceId()) %>
			</p>

			<h5><liferay-ui:message key="version" /></h5>

			<p>
				<%= reference.getVersion() %>
			</p>

			<h5><liferay-ui:message key="status" /></h5>

			<p>
				<aui:workflow-status markupView="lexicon" showIcon="<%= false %>" showLabel="<%= false %>" status="<%= reference.getStatus() %>" />
			</p>

			<h5><liferay-ui:message key="title" /></h5>

			<p>
				<%= HtmlUtil.escape(reference.getName()) %>
			</p>

			<div class="lfr-asset-tags">
				<liferay-asset:asset-tags-summary
					className="<%= Reference.class.getName() %>"
					classPK="<%= classPK %>"
					message="tags"
				/>
			</div>

			<h5><liferay-ui:message key="priority" /></h5>

			<p>
				<%= assetEntry.getPriority() %>
			</p>

			<c:if test="<%= reference.getRealizationDate() != null %>">
				<h5><liferay-ui:message key="realization-date" /></h5>

				<p>
					<%= dateFormatDateTime.format(reference.getRealizationDate()) %>
				</p>
			</c:if>
		</div>
	
	</c:when>
	<c:otherwise>
		<div class="sidebar-header">
			<h4><liferay-ui:message arguments="<%= references.size() %>" key="x-items-are-selected" /></h4>
		</div>

<%--
		<clay:navigation-bar
			navigationItems="<%= referenceDisplayContext.getInfoPanelNavigationItems() %>"
		/>
--%>

		<div class="sidebar-body">
			<h5><liferay-ui:message arguments="<%= references.size() %>" key="x-items-are-selected" /></h5>
		</div>
	</c:otherwise>
</c:choose>