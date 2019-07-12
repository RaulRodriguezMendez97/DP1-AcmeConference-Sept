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

<p><spring:message code="professionalRecord.title" /></p>

<display:table pagesize="5" name="professionalsRecords" id="row"
requestURI="professionalRecord/handyWorker/showProfessionalsRecords.do" >

<display:column property="nameCompany" titleKey="professionalRecord.nameCompany" />
<display:column property="startDate" titleKey="professionalRecord.startDate" />
<display:column property="endDate" titleKey="professionalRecord.endDate" />
<display:column property="role" titleKey="professionalRecord.role" />
<display:column property="link" titleKey="professionalRecord.link" />
<display:column property="comments" titleKey="professionalRecord.comments" />
</display:table>

</security:authorize>