

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('HANDYWORKER')">



<display:table pagesize="5" name="phases" id="row"
requestURI="${requestURI}" >



<display:column property="title" titleKey="phase.title" sortable="true"  />
<display:column property="description" titleKey="phase.description" />
<display:column property="startMoment" titleKey="phase.starMoment" />
<display:column property="endMoment" titleKey="phase.endMoment" />

	<display:column >
	

			<a href="phase/handyWorker/edit.do?phaseId=${row.id}">
				<spring:message	code="phase.edit" />
			</a>
	
			
		</display:column>	
		

</display:table>
	<div>
		<a href="phase/handyWorker/create.do?applicationId=${application.id}"> <spring:message code="phase.create" />
		</a>
	</div>

	<input type="button" name="back"
		value="<spring:message code="phase.back" />"
		onclick="javascript: relativeRedir('application/handyWorker,customer/applications.do');" />


</security:authorize>