<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ddm" prefix="liferay-ddm" %>
<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/trash" prefix="liferay-trash" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Objects"%>

<%@page import="com.liferay.asset.kernel.exception.DuplicateQueryRuleException"%>
<%@page import="com.liferay.asset.kernel.model.AssetVocabulary"%>
<%@page import="com.liferay.asset.kernel.service.AssetVocabularyServiceUtil"%>

<%@page import="com.liferay.document.library.kernel.util.DLUtil"%>

<%@page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil"%>
<%@page import="com.liferay.portal.kernel.model.ModelHintsConstants"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.kernel.repository.model.FileEntry"%>
<%@page import="com.liferay.portal.kernel.util.ArrayUtil"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.PrefsParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.PropsKeys"%>
<%@page import="com.liferay.portal.kernel.util.PropsUtil"%>
<%@page import="com.liferay.portal.kernel.util.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>

<%@page import="com.liferay.taglib.util.AssetCategoryUtil"%>

<%@page import="hu.webtown.liferay.portlet.reference.constants.ReferenceImageType"%>
<%@page import="hu.webtown.liferay.portlet.reference.list.web.constants.ReferenceDxpListPortletKeys"%>
<%@page import="hu.webtown.liferay.portlet.reference.list.web.display.context.ReferenceDxpListDisplayContext"%>
<%@page import="hu.webtown.liferay.portlet.reference.list.web.util.ReferenceDxpListUtil"%>
<%@page import="hu.webtown.liferay.portlet.reference.model.Reference"%>
<%@page import="hu.webtown.liferay.portlet.reference.model.ReferenceImage"%>
<%@page import="hu.webtown.liferay.portlet.reference.service.ReferenceImageLocalServiceUtil"%>
<%@page import="hu.webtown.liferay.portlet.reference.util.ReferenceUtil"%>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
ReferenceDxpListDisplayContext referenceDxpListDisplayContext = new ReferenceDxpListDisplayContext(liferayPortletRequest, liferayPortletResponse, portletPreferences);
%>