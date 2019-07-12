

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('REFEREE')">



<display:table pagesize="5" name="notes" id="row"
requestURI="${requestURI}" >



<display:column property="moment" titleKey="note.moment" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}" />
<display:column property="comment" titleKey="note.comment" />
<display:column property="optionalComments" titleKey="note.optionalComments" />
	

</display:table>
	<div>
		<a href="note/referee/create.do?reportId=${report.id}"> <spring:message
				code="note.create" />
		</a>
	</div>

	<input type="button" name="back"
		value="<spring:message code="report.back" />"
		onclick="javascript: relativeRedir('report/referee/list.do?complaintId=${report.complaint.id}');" />


</security:authorize>