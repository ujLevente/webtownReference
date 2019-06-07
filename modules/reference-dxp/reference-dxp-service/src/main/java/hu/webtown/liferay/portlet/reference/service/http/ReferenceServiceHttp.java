/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package hu.webtown.liferay.portlet.reference.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import hu.webtown.liferay.portlet.reference.service.ReferenceServiceUtil;

/**
 * Provides the HTTP utility for the
 * {@link ReferenceServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReferenceServiceSoap
 * @see HttpPrincipal
 * @see ReferenceServiceUtil
 * @generated
 */
@ProviderType
public class ReferenceServiceHttp {
	public static hu.webtown.liferay.portlet.reference.model.Reference addReference(
		HttpPrincipal httpPrincipal, long groupId, String referenceId,
		boolean autoReferenceId, String name, String shortDescription,
		String description, int realizationDateMonth, int realizationDateDay,
		int realizationDateYear, boolean emphasized, String overlayText,
		String overlayUrl, String urlTitle, String smallImage,
		String largeImage, String[] otherImages,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ReferenceServiceUtil.class,
					"addReference", _addReferenceParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					referenceId, autoReferenceId, name, shortDescription,
					description, realizationDateMonth, realizationDateDay,
					realizationDateYear, emphasized, overlayText, overlayUrl,
					urlTitle, smallImage, largeImage, otherImages,
					serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (hu.webtown.liferay.portlet.reference.model.Reference)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static hu.webtown.liferay.portlet.reference.model.Reference deleteReference(
		HttpPrincipal httpPrincipal, long groupId, String referenceId,
		double version,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ReferenceServiceUtil.class,
					"deleteReference", _deleteReferenceParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					referenceId, version, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (hu.webtown.liferay.portlet.reference.model.Reference)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteReference(HttpPrincipal httpPrincipal,
		long groupId, String referenceId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ReferenceServiceUtil.class,
					"deleteReference", _deleteReferenceParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					referenceId, serviceContext);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static hu.webtown.liferay.portlet.reference.model.Reference fetchReference(
		HttpPrincipal httpPrincipal, long groupId, String referenceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ReferenceServiceUtil.class,
					"fetchReference", _fetchReferenceParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					referenceId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (hu.webtown.liferay.portlet.reference.model.Reference)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> getGroupReferences(
		HttpPrincipal httpPrincipal, long groupId, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.Reference> orderByComparator) {
		try {
			MethodKey methodKey = new MethodKey(ReferenceServiceUtil.class,
					"getGroupReferences", _getGroupReferencesParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					status, start, end, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<hu.webtown.liferay.portlet.reference.model.Reference>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getGroupReferencesCount(HttpPrincipal httpPrincipal,
		long groupId, int status) {
		try {
			MethodKey methodKey = new MethodKey(ReferenceServiceUtil.class,
					"getGroupReferencesCount",
					_getGroupReferencesCountParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static hu.webtown.liferay.portlet.reference.model.Reference getLatestReference(
		HttpPrincipal httpPrincipal, long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ReferenceServiceUtil.class,
					"getLatestReference", _getLatestReferenceParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					resourcePrimKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (hu.webtown.liferay.portlet.reference.model.Reference)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static hu.webtown.liferay.portlet.reference.model.Reference getLatestReference(
		HttpPrincipal httpPrincipal, long groupId, String referenceId,
		int status) throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ReferenceServiceUtil.class,
					"getLatestReference", _getLatestReferenceParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					referenceId, status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (hu.webtown.liferay.portlet.reference.model.Reference)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static hu.webtown.liferay.portlet.reference.model.Reference getReference(
		HttpPrincipal httpPrincipal, long id)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ReferenceServiceUtil.class,
					"getReference", _getReferenceParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey, id);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (hu.webtown.liferay.portlet.reference.model.Reference)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static hu.webtown.liferay.portlet.reference.model.Reference getReference(
		HttpPrincipal httpPrincipal, long groupId, String referenceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ReferenceServiceUtil.class,
					"getReference", _getReferenceParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					referenceId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (hu.webtown.liferay.portlet.reference.model.Reference)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static hu.webtown.liferay.portlet.reference.model.Reference getReference(
		HttpPrincipal httpPrincipal, long groupId, String referenceId,
		double version)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ReferenceServiceUtil.class,
					"getReference", _getReferenceParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					referenceId, version);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (hu.webtown.liferay.portlet.reference.model.Reference)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static hu.webtown.liferay.portlet.reference.model.Reference getReferenceByUrlTitle(
		HttpPrincipal httpPrincipal, long groupId, String urlTitle)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ReferenceServiceUtil.class,
					"getReferenceByUrlTitle",
					_getReferenceByUrlTitleParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					urlTitle);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (hu.webtown.liferay.portlet.reference.model.Reference)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> getReferences(
		HttpPrincipal httpPrincipal, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.Reference> obc) {
		try {
			MethodKey methodKey = new MethodKey(ReferenceServiceUtil.class,
					"getReferences", _getReferencesParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<hu.webtown.liferay.portlet.reference.model.Reference>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getReferencesCount(HttpPrincipal httpPrincipal,
		long groupId) {
		try {
			MethodKey methodKey = new MethodKey(ReferenceServiceUtil.class,
					"getReferencesCount", _getReferencesCountParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static String getUniqueUrlTitle(HttpPrincipal httpPrincipal,
		long groupId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ReferenceServiceUtil.class,
					"getUniqueUrlTitle", _getUniqueUrlTitleParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					name);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static hu.webtown.liferay.portlet.reference.model.Reference moveReferenceFromTrash(
		HttpPrincipal httpPrincipal, long groupId, long resourcePrimKey,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ReferenceServiceUtil.class,
					"moveReferenceFromTrash",
					_moveReferenceFromTrashParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					resourcePrimKey, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (hu.webtown.liferay.portlet.reference.model.Reference)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static hu.webtown.liferay.portlet.reference.model.Reference moveReferenceFromTrash(
		HttpPrincipal httpPrincipal, long groupId, String referenceId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ReferenceServiceUtil.class,
					"moveReferenceFromTrash",
					_moveReferenceFromTrashParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					referenceId, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (hu.webtown.liferay.portlet.reference.model.Reference)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static hu.webtown.liferay.portlet.reference.model.Reference moveReferenceToTrash(
		HttpPrincipal httpPrincipal, long groupId, String referenceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ReferenceServiceUtil.class,
					"moveReferenceToTrash",
					_moveReferenceToTrashParameterTypes17);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					referenceId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (hu.webtown.liferay.portlet.reference.model.Reference)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void restoreReferenceFromTrash(HttpPrincipal httpPrincipal,
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ReferenceServiceUtil.class,
					"restoreReferenceFromTrash",
					_restoreReferenceFromTrashParameterTypes18);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					resourcePrimKey);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void restoreReferenceFromTrash(HttpPrincipal httpPrincipal,
		long groupId, String referenceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ReferenceServiceUtil.class,
					"restoreReferenceFromTrash",
					_restoreReferenceFromTrashParameterTypes19);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					referenceId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> search(
		HttpPrincipal httpPrincipal, long companyId, long groupId,
		String keywords, Double version, java.util.Date realizationDateGT,
		java.util.Date realizationDateLT, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.Reference> obc) {
		try {
			MethodKey methodKey = new MethodKey(ReferenceServiceUtil.class,
					"search", _searchParameterTypes20);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, groupId, keywords, version, realizationDateGT,
					realizationDateLT, status, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<hu.webtown.liferay.portlet.reference.model.Reference>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<hu.webtown.liferay.portlet.reference.model.Reference> search(
		HttpPrincipal httpPrincipal, long companyId, long groupId,
		String referenceId, Double version, String name,
		String shortDescription, String description,
		java.util.Date realizationDateGT, java.util.Date realizationDateLT,
		int status, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<hu.webtown.liferay.portlet.reference.model.Reference> obc) {
		try {
			MethodKey methodKey = new MethodKey(ReferenceServiceUtil.class,
					"search", _searchParameterTypes21);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, groupId, referenceId, version, name,
					shortDescription, description, realizationDateGT,
					realizationDateLT, status, andOperator, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<hu.webtown.liferay.portlet.reference.model.Reference>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int searchCount(HttpPrincipal httpPrincipal, long companyId,
		long groupId, String keywords, Double version,
		java.util.Date realizationDateGT, java.util.Date realizationDateLT,
		int status) {
		try {
			MethodKey methodKey = new MethodKey(ReferenceServiceUtil.class,
					"searchCount", _searchCountParameterTypes22);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, groupId, keywords, version, realizationDateGT,
					realizationDateLT, status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int searchCount(HttpPrincipal httpPrincipal, long companyId,
		long groupId, String referenceId, Double version, String name,
		String shortDescription, String description,
		java.util.Date relizationDateGT, java.util.Date realizationDateLT,
		int status, boolean andOperator) {
		try {
			MethodKey methodKey = new MethodKey(ReferenceServiceUtil.class,
					"searchCount", _searchCountParameterTypes23);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, groupId, referenceId, version, name,
					shortDescription, description, relizationDateGT,
					realizationDateLT, status, andOperator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static hu.webtown.liferay.portlet.reference.model.Reference updateReference(
		HttpPrincipal httpPrincipal, long groupId, String currentReferenceId,
		double version, String name, String shortDescription,
		String description, int realizationDateMonth, int realizationDateDay,
		int realizationDateYear, boolean emphasized, String overlayText,
		String overlayUrl, String urlTitle, String smallImage,
		String largeImage, String[] otherImages,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ReferenceServiceUtil.class,
					"updateReference", _updateReferenceParameterTypes24);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					currentReferenceId, version, name, shortDescription,
					description, realizationDateMonth, realizationDateDay,
					realizationDateYear, emphasized, overlayText, overlayUrl,
					urlTitle, smallImage, largeImage, otherImages,
					serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (hu.webtown.liferay.portlet.reference.model.Reference)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static hu.webtown.liferay.portlet.reference.model.Reference updateStatus(
		HttpPrincipal httpPrincipal, long groupId, String referenceId,
		double version, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ReferenceServiceUtil.class,
					"updateStatus", _updateStatusParameterTypes25);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					referenceId, version, status, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (hu.webtown.liferay.portlet.reference.model.Reference)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ReferenceServiceHttp.class);
	private static final Class<?>[] _addReferenceParameterTypes0 = new Class[] {
			long.class, String.class, boolean.class, String.class, String.class,
			String.class, int.class, int.class, int.class, boolean.class,
			String.class, String.class, String.class, String.class, String.class,
			String[].class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteReferenceParameterTypes1 = new Class[] {
			long.class, String.class, double.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteReferenceParameterTypes2 = new Class[] {
			long.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _fetchReferenceParameterTypes3 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[] _getGroupReferencesParameterTypes4 = new Class[] {
			long.class, int.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getGroupReferencesCountParameterTypes5 = new Class[] {
			long.class, int.class
		};
	private static final Class<?>[] _getLatestReferenceParameterTypes6 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getLatestReferenceParameterTypes7 = new Class[] {
			long.class, String.class, int.class
		};
	private static final Class<?>[] _getReferenceParameterTypes8 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getReferenceParameterTypes9 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[] _getReferenceParameterTypes10 = new Class[] {
			long.class, String.class, double.class
		};
	private static final Class<?>[] _getReferenceByUrlTitleParameterTypes11 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[] _getReferencesParameterTypes12 = new Class[] {
			long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getReferencesCountParameterTypes13 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getUniqueUrlTitleParameterTypes14 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[] _moveReferenceFromTrashParameterTypes15 = new Class[] {
			long.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _moveReferenceFromTrashParameterTypes16 = new Class[] {
			long.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _moveReferenceToTrashParameterTypes17 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[] _restoreReferenceFromTrashParameterTypes18 = new Class[] {
			long.class
		};
	private static final Class<?>[] _restoreReferenceFromTrashParameterTypes19 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[] _searchParameterTypes20 = new Class[] {
			long.class, long.class, String.class, Double.class,
			java.util.Date.class, java.util.Date.class, int.class, int.class,
			int.class, com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _searchParameterTypes21 = new Class[] {
			long.class, long.class, String.class, Double.class, String.class,
			String.class, String.class, java.util.Date.class,
			java.util.Date.class, int.class, boolean.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _searchCountParameterTypes22 = new Class[] {
			long.class, long.class, String.class, Double.class,
			java.util.Date.class, java.util.Date.class, int.class
		};
	private static final Class<?>[] _searchCountParameterTypes23 = new Class[] {
			long.class, long.class, String.class, Double.class, String.class,
			String.class, String.class, java.util.Date.class,
			java.util.Date.class, int.class, boolean.class
		};
	private static final Class<?>[] _updateReferenceParameterTypes24 = new Class[] {
			long.class, String.class, double.class, String.class, String.class,
			String.class, int.class, int.class, int.class, boolean.class,
			String.class, String.class, String.class, String.class, String.class,
			String[].class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateStatusParameterTypes25 = new Class[] {
			long.class, String.class, double.class, int.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
}