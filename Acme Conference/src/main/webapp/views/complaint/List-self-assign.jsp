

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('REFEREE')">



<display:table pagesize="5" name="complaints" id="row"
requestURI="${requestURI}" >


<display:column property="ticker" titleKey="complaint.ticker" />
<display:column property="moment" titleKey="complaint.moment" />
<display:column property="description" titleKey="complaint.description" />
<display:column property="numberAttachments" titleKey="complaint.numberAttachments" />
<display:column property="fixUpTask.id" titleKey="complaint.fixUpTask" />
	<display:column >
			<a href="report/referee/list.do?complaintId=${row.id}">
				<spring:message	code="complaint.report.list" />
			</a>
		</display:column>

</display:table>

	<input type="button" name="back"
		value="<spring:message code="complaints.back" />"
		onclick="javascript: relativeRedir('complaint/referee/list.do');" />


</security:authorize>