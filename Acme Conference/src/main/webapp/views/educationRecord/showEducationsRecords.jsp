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

<p><spring:message code="educationRecord.title" /></p>

<display:table pagesize="5" name="educationsRecords" id="row"
requestURI="educationRecord/handyWorker/showEducationsRecords.do" >

<display:column property="titleDiploma" titleKey="educationRecord.titleDiploma" />
<display:column property="startDate" titleKey="educationRecord.startDate" />
<display:column property="endDate" titleKey="educationRecord.endDate" />
<display:column property="institution" titleKey="educationRecord.institution" />
<display:column property="link" titleKey="educationRecord.link" />
<display:column property="comment" titleKey="educationRecord.comment" />
</display:table>


</security:authorize>