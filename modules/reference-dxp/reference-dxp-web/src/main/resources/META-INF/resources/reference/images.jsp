<%@ include file="/init.jsp" %>

<%
Reference reference = referenceDisplayContext.getReference();

long groupId = BeanParamUtil.getLong(reference, request, "groupId", scopeGroupId);

String referenceId = BeanParamUtil.getString(reference, request, "referenceId");

double version = BeanParamUtil.getDouble(reference, request, "version", ReferenceConstants.VERSION_DEFAULT);

String smallImage = ParamUtil.getString(request, "smallImage");
String largeImage = ParamUtil.getString(request, "largeImage");
String[] otherImages = new String[0];

if (reference != null) {

	List<ReferenceImage> referenceImages = ReferenceImageLocalServiceUtil.getReferenceImages(reference.getGroupId(), reference.getReferenceId(), reference.getVersion());

	if (referenceImages != null) {

		for (ReferenceImage referenceImage : referenceImages) {

			if (referenceImage.getImageType() == ReferenceImageType.SMALL_IMAGE.getId()) {

				smallImage = referenceImage.getImage();
			} else if (referenceImage.getImageType() == ReferenceImageType.LARGE_IMAGE.getId()) {

				largeImage = referenceImage.getImage();
			} else {

				otherImages = ArrayUtil.append(otherImages, referenceImage.getImage());
			}
		}
	}
}
%>

<liferay-ui:error-marker key="<%= WebKeys.ERROR_SECTION %>" value="images" />

<aui:model-context bean="<%= reference %>" model="<%= Reference.class %>" />

<%
LiferayPortletURL uploadURL = (LiferayPortletURL)PortalUtil.getControlPanelPortletURL(liferayPortletRequest, PortletKeys.DOCUMENT_LIBRARY, PortletRequest.ACTION_PHASE);
uploadURL.setDoAsGroupId(scopeGroupId);
uploadURL.setParameter(Constants.CMD, Constants.ADD_TEMP);
uploadURL.setParameter(ActionRequest.ACTION_NAME, "/document_library/upload_file_entry");
uploadURL.setParameter("p_auth", AuthTokenUtil.getToken(request));

LiferayPortletURL documentLibrarySelectorURL = (LiferayPortletURL)PortalUtil.getControlPanelPortletURL(liferayPortletRequest, PortletKeys.ITEM_SELECTOR, PortletRequest.RENDER_PHASE);

documentLibrarySelectorURL.setDoAsGroupId(scopeGroupId);
documentLibrarySelectorURL.setParameter("criteria", "com.liferay.item.selector.criteria.file.criterion.FileItemSelectorCriterion");
documentLibrarySelectorURL.setParameter("itemSelectedEventName", liferayPortletResponse.getNamespace() + "selectDocumentLibrary");

String criterionJSON = "{ \"desiredItemSelectorReturnTypes\": \"com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType,com.liferay.item.selector.criteria.UploadableFileReturnType\" }";

documentLibrarySelectorURL.setParameter("0_json", criterionJSON);
documentLibrarySelectorURL.setParameter("1_json", criterionJSON);

String uploadCriterionJSON = "{ \"desiredItemSelectorReturnTypes\": \"com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType,com.liferay.item.selector.criteria.UploadableFileReturnType\", \"URL\": \"" + uploadURL.toString() + "\" }";

documentLibrarySelectorURL.setParameter("2_json", uploadCriterionJSON);
documentLibrarySelectorURL.setPortletMode(PortletMode.VIEW);
documentLibrarySelectorURL.setWindowState(LiferayWindowState.POP_UP);
%>

<aui:fieldset>
	<aui:field-wrapper label="small-image" cssClass="reference-small-image-container">
		<liferay-util:include page="/image.jsp" servletContext="<%= application %>">
			<liferay-util:param name="fieldName" value="smallImage" />
			<liferay-util:param name="documentLibrarySelectorURL" value="<%= documentLibrarySelectorURL.toString() %>" />
			<liferay-util:param name="fieldRawValue" value="<%= smallImage %>" />
		</liferay-util:include>
	</aui:field-wrapper>
	<aui:field-wrapper label="large-image" cssClass="reference-large-image-container">
		<liferay-util:include page="/image.jsp" servletContext="<%= application %>">
			<liferay-util:param name="fieldName" value="largeImage" />
			<liferay-util:param name="documentLibrarySelectorURL" value="<%= documentLibrarySelectorURL.toString() %>" />
			<liferay-util:param name="fieldRawValue" value="<%= largeImage %>" />
		</liferay-util:include>
	</aui:field-wrapper>
	<div id="<portlet:namespace />otherImages" class="reference-other-images-container">
		<aui:fieldset label="other-images">

			<%
			String otherImageIndexesParam = ParamUtil.getString(request, "otherImageIndexes");

			int[] otherImageIndexes = null;

			if (Validator.isNotNull(otherImageIndexesParam)) {

				otherImageIndexes = StringUtil.split(otherImageIndexesParam, 0);
			} else {

				otherImageIndexes = new int[0];

				for (int i = 0; true; i++) {

					String otherImageFromDB = GetterUtil.getString(ArrayUtil.getValue(otherImages, i));
					String otherImage = ParamUtil.getString(request, "otherImage" + i, otherImageFromDB);

					if (Validator.isNull(otherImage)) {
					    
						break;
					}

					otherImageIndexes = ArrayUtil.append(otherImageIndexes, i);
				}

				if (otherImageIndexes.length == 0) {

					otherImageIndexes = ArrayUtil.append(otherImageIndexes, -1);
				}
			}

			int index = 0;

			for (int otherImageIndex : otherImageIndexes) {

				String otherImageFromDB = otherImageIndex >= 0 ? GetterUtil.getString(ArrayUtil.getValue(otherImages, otherImageIndex)) : StringPool.BLANK;
				String otherImage = ParamUtil.getString(request, "otherImage" + otherImageIndex, otherImageFromDB);

				if (Validator.isNotNull(otherImage) || (otherImageIndexes.length == 1)) {
				%>

				<div class="lfr-form-row">
					<div class="row-fields">
						<liferay-util:include page="/image.jsp" servletContext="<%= application %>">
							<liferay-util:param name="fieldName" value="otherImage" />
							<liferay-util:param name="index" value="<%= String.valueOf(index) %>" />
							<liferay-util:param name="documentLibrarySelectorURL" value="<%= documentLibrarySelectorURL.toString() %>" />
							<liferay-util:param name="fieldRawValue" value="<%= otherImage %>" />
						</liferay-util:include>
					</div>
				</div>

				<%
			}

			index++;
		}
		%>

		</aui:fieldset>
	</div>

	<aui:script use="liferay-auto-fields">
	
	    var autoFields = new Liferay.AutoFields(
	        {
	            contentBox: '#<portlet:namespace />otherImages > fieldset',
	            fieldIndexes: '<portlet:namespace />otherImageIndexes',
	            namespace: '<portlet:namespace />',
	            url: '<liferay-portlet:renderURL portletName="<%= ReferencePortletKeys.REFERENCE_DXP_PORTLET %>" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/image.jsp" /><portlet:param name="fieldName" value="otherImage" /><portlet:param name="documentLibrarySelectorURL" value="<%= documentLibrarySelectorURL.toString() %>" /></liferay-portlet:renderURL>'
	        }
	    ).render();
	    
	</aui:script>
</aui:fieldset>
