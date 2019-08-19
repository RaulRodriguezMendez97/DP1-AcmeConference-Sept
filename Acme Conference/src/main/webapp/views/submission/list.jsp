<%--
 * action-1.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('AUTHOR')">
<display:table pagesize="5" name="submissions" id="row"
requestURI="submission/author/list.do" >
<display:column>
	  		 <a href="submission/author/detail.do?submissionId=${row.id}"><spring:message code="submission.details" /></a> 
</display:column>
<display:column property="moment" titleKey="submission.moment"/>
<display:column titleKey="submission.status">

	<jstl:if test="${row.status eq 0 }">
		<spring:message code="submission.status.0" />
	</jstl:if>
	<jstl:if test="${row.status eq 1 }">
		<spring:message code="submission.status.1" />
	</jstl:if>
	<jstl:if test="${row.status eq 2 }">
		<spring:message code="submission.status.2" />
	</jstl:if>

</display:column>
<display:column property="conference.title" titleKey="submission.conference.table"/>
<display:column property="reviwed.title" titleKey="submission.reviwed.table.title"/>
<display:column property="reviwed.summary" titleKey="submission.reviwed.table.summary"/>
<display:column property="reviwed.urlDocument" titleKey="submission.reviwed.table.urlDocument"/>
<display:column>
	<jstl:if test="${row.status eq 2  }">
		<a href="camera-ready/author/show.do?idSubmission=${row.id}"><spring:message code="submission.camaraReady" /></a> 
	</jstl:if>
</display:column>
</display:table>

<input type="button" name="create" value="<spring:message code="submission.create" />"
			onclick="javascript: relativeRedir('submission/author/create.do');" /><br>
</security:authorize>


<security:authorize access="hasRole('REVIWER')">
<display:table pagesize="5" name="submissions" id="row"
requestURI="submission/reviwer/list.do" >
<display:column property="ticker" titleKey="submission.ticker" />
<display:column property="moment" titleKey="submission.moment"/>
<display:column property="status" titleKey="submission.status"/>
<display:column property="conference.title" titleKey="submission.conference.table"/>

<display:column>
	<a href="report/reviwer/list.do?submissionId=${row.id}"><spring:message code="submission.reviwer.report" /></a>
</display:column>
</display:table>
</security:authorize>


<security:authorize access="hasRole('ADMIN')">
<display:table pagesize="5" name="submissions" id="row"
requestURI="${uriL}" >
<display:column property="ticker" titleKey="submission.ticker" />
<display:column property="moment" titleKey="submission.moment"/>
<display:column property="status" titleKey="submission.status"/>
<display:column property="conference.title" titleKey="submission.conference.table"/>

<display:column>
	<jstl:if test="${row.status eq 0}">	
		<a href="submission/administrator/edit.do?submissionId=${row.id}"><spring:message code="submission.asignar" /></a>
	</jstl:if>
</display:column>


<display:column>
	<jstl:if test="${(row.status eq 0) and (fechaActual > row.conference.submissionDeadline)}">	
		<a href="submission/administrator/edit.do?submissionId=${row.id}"><spring:message code="submission.editar" /></a>
	</jstl:if>
</display:column>


<display:column>
		<a href="${uriD}?submissionId=${row.id}"><spring:message code="submission.details" /></a> 
</display:column>
<display:column>
		<a href="${uriDR}?submissionId=${row.id}"><spring:message code="submission.detailsReports" /></a>  
</display:column>
</display:table>
</security:authorize>
