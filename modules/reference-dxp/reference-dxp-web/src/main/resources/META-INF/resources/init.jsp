<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/asset" prefix="liferay-asset" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/clay" prefix="clay" %>
<%@ taglib uri="http://liferay.com/tld/ddm" prefix="liferay-ddm" %>
<%@ taglib uri="http://liferay.com/tld/expando" prefix="liferay-expando" %>
<%@ taglib uri="http://liferay.com/tld/export-import-changeset" prefix="liferay-export-import-changeset" %>
<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/soy" prefix="soy" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/trash" prefix="liferay-trash" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.Format"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="javax.portlet.ActionRequest"%>
<%@page import="javax.portlet.PortletMode"%>
<%@page import="javax.portlet.PortletRequest"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="javax.portlet.WindowState"%>

<%@page import="com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil"%>
<%@page import="com.liferay.asset.kernel.model.AssetEntry"%>
<%@page import="com.liferay.asset.kernel.model.AssetRenderer"%>
<%@page import="com.liferay.asset.kernel.model.AssetRendererFactory"%>
<%@page import="com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil"%>
<%@page import="com.liferay.document.library.kernel.util.DLUtil"%>
<%@page import="com.liferay.petra.string.StringPool" %>
<%@page import="com.liferay.portal.kernel.bean.BeanParamUtil"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@page import="com.liferay.portal.kernel.dao.search.SearchContainer"%>
<%@page import="com.liferay.portal.kernel.exception.PortalException"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil"%>
<%@page import="com.liferay.portal.kernel.model.Group"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletURL"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.kernel.repository.model.FileEntry"%>
<%@page import="com.liferay.portal.kernel.security.auth.AuthTokenUtil"%>
<%@page import="com.liferay.portal.kernel.security.permission.ActionKeys"%>
<%@page import="com.liferay.portal.kernel.service.GroupLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.ArrayUtil"%>
<%@page import="com.liferay.portal.kernel.util.CalendarFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.PortletKeys"%>
<%@page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowConstants"%>
<%@page import="com.liferay.taglib.util.CustomAttributesUtil"%>

<%@page import="hu.webtown.liferay.portlet.reference.constants.ReferenceActionKeys"%>
<%@page import="hu.webtown.liferay.portlet.reference.constants.ReferenceConstants"%>
<%@page import="hu.webtown.liferay.portlet.reference.constants.ReferenceImageType"%>
<%@page import="hu.webtown.liferay.portlet.reference.constants.ReferencePortletKeys"%>
<%@page import="hu.webtown.liferay.portlet.reference.exception.DuplicateUrlTitleException"%>
<%@page import="hu.webtown.liferay.portlet.reference.exception.NoSuchReferenceException"%>
<%@page import="hu.webtown.liferay.portlet.reference.exception.ReferenceDescriptionException"%>
<%@page import="hu.webtown.liferay.portlet.reference.exception.ReferenceNameException"%>
<%@page import="hu.webtown.liferay.portlet.reference.exception.ReferenceUrlTitleException"%>
<%@page import="hu.webtown.liferay.portlet.reference.exception.ReferenceShortDescriptionException"%>
<%@page import="hu.webtown.liferay.portlet.reference.model.Reference"%>
<%@page import="hu.webtown.liferay.portlet.reference.model.ReferenceImage"%>
<%@page import="hu.webtown.liferay.portlet.reference.service.ReferenceImageLocalServiceUtil"%>
<%@page import="hu.webtown.liferay.portlet.reference.util.ReferenceUtil"%>
<%@page import="hu.webtown.liferay.portlet.reference.web.asset.ReferenceAssetRenderer"%>
<%@page import="hu.webtown.liferay.portlet.reference.web.configuration.ReferenceWebConfiguration"%>
<%@page import="hu.webtown.liferay.portlet.reference.web.internal.display.context.ReferenceDisplayContext"%>
<%@page import="hu.webtown.liferay.portlet.reference.web.internal.portlet.ReferenceDxpPortlet"%>
<%@page import="hu.webtown.liferay.portlet.reference.web.internal.security.permission.resource.ReferenceAdminPermission"%>
<%@page import="hu.webtown.liferay.portlet.reference.web.internal.security.permission.resource.ReferencePermission"%>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<liferay-trash:defineObjects />

<portlet:defineObjects />

<%
ReferenceWebConfiguration referenceWebConfiguration = (ReferenceWebConfiguration)request.getAttribute(ReferenceWebConfiguration.class.getName());

ReferenceDisplayContext referenceDisplayContext = new ReferenceDisplayContext(request, liferayPortletRequest, liferayPortletResponse, portletPreferences, trashHelper);

Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);
Format dateFormatDate = FastDateFormatFactoryUtil.getDate(locale, timeZone);
%>