<%--
 * action-2.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
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
<security:authorize access="hasRole('HANDYWORKER')">

<p><spring:message code="curricula.title" /></p>

<display:table pagesize="5" name="curriculas" id="row"
requestURI="curricula/handyWorker/curriculas.do" >

<display:column property="ticker" titleKey="curricula.ticker" />
<display:column titleKey="curricula.educationsRecords">
	<a href="educationRecord/handyWorker/showEducationsRecords.do?curriculaId=${row.id}"><spring:message code="curricula.seeEducationRecords" /></a>
</display:column>
<display:column titleKey="curricula.professionalsRecords" >
	<a href="professionalRecord/handyWorker/showProfessionalsRecords.do?curriculaId=${row.id}"><spring:message code="curricula.seeProfessionalRecords" /></a>
</display:column>
<display:column titleKey="curricula.personalRecords" >
	<a href="personalRecord/handyWorker/showPersonalRecord.do?curriculaId=${row.id}"><spring:message code="curricula.seePersonalRecords" /></a>
</display:column>
<display:column titleKey="curricula.miscellaneousRecords" >
	<a href="miscellaneousRecord/handyWorker/showMiscellaneousRecords.do?curriculaId=${row.id}"><spring:message code="curricula.seeMiscellaneousRecords" /></a>
</display:column>
<display:column titleKey="curricula.endosersRecords" >
	<a href="endorserRecord/handyWorker/showEndorsersRecords.do?curriculaId=${row.id}"><spring:message code="curricula.seeEndoserRecords" /></a>
</display:column>
</display:table>

<form action="curricula/handyWorker/createCurricula.do">
    <input type="submit" value="<spring:message code="curricula.create" />" />
</form>


</security:authorize>