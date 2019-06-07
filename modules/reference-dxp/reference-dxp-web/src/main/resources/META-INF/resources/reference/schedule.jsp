<%@ include file="/init.jsp" %>

<%
Reference reference = referenceDisplayContext.getReference();

Calendar today = CalendarFactoryUtil.getCalendar(timeZone, locale);
today.add(Calendar.MONTH, -1);
Calendar realizationDate = CalendarFactoryUtil.getCalendar(timeZone, locale);

if (reference != null) {

	realizationDate.setTime(reference.getRealizationDate());
}

int realizationDateDay = ParamUtil.getInteger(request, "realizationDateDay", realizationDate.get(Calendar.DATE));
int realizationDateMonth = ParamUtil.getInteger(request, "realizationDateMonth", realizationDate.get(Calendar.MONTH));
int realizationDateYear = ParamUtil.getInteger(request, "realizationDateYear", realizationDate.get(Calendar.YEAR));
%>

<liferay-ui:error-marker key="<%= WebKeys.ERROR_SECTION %>" value="schedule" />

<aui:model-context bean="<%= reference %>" model="<%= Reference.class %>" />

<aui:field-wrapper label="realization-date">
	<div class="clearfix">
		<liferay-ui:input-date
			cssClass="form-group form-group-inline"
			dayParam="realizationDateDay"
			dayValue="<%= realizationDateDay %>"
			firstDayOfWeek="<%= today.getFirstDayOfWeek() - 1 %>"
			monthParam="realizationDateMonth"
			monthValue="<%= realizationDateMonth %>"
			name="relaizationDate"
			yearParam="realizationDateYear"
			yearValue="<%= realizationDateYear %>"
		/>
	</div>
</aui:field-wrapper>
