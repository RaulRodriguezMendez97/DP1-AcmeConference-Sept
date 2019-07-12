

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('REFEREE')">



<display:table pagesize="5" name="reports" id="row"
requestURI="${requestURI}" >



<display:column property="moment" titleKey="report.moment" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}" />
<display:column property="description" titleKey="report.description" />
<display:column property="published" titleKey="report.published" />

<display:column titleKey="report.attachment" >
	<jstl:forEach var="attachment"  items="${row.attachment}">
		<jstl:out value="${attachment.link}"></jstl:out>
	</jstl:forEach>
</display:column>

<jstl:if test="${row.published==1}">
	<display:column >
			<a href="note/referee/list.do?reportId=${row.id}">
				<spring:message	code="report.note.list" />
			</a>
	</display:column>
</jstl:if>

<jstl:if test="${row.published==0}">
	<display:column >
			<jstl:out value="-"></jstl:out>
			
	</display:column>
</jstl:if>
	
	<display:column >
	
	<jstl:if test="${row.published==0}">
			<a href="report/referee/edit.do?reportId=${row.id}">
				<spring:message	code="report.edit" />
			</a>
			</jstl:if>
			
		</display:column>	
<jstl:if test="${row.published==1}">
	<display:column >
			<jstl:out value="-"></jstl:out>
			
	</display:column>
</jstl:if>
		

</display:table>
	<div>
		<a href="report/referee/create.do?complaintId=${complaint.id}"> <spring:message
				code="report.create" />
		</a>
	</div>

	<input type="button" name="back"
		value="<spring:message code="report.back" />"
		onclick="javascript: relativeRedir('complaint/referee/list2.do');" />


</security:authorize>