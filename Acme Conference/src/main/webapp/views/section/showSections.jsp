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

<p><spring:message code="sections.title" /></p>
<display:table pagesize="5" name="sections" id="row"
requestURI="section/handyWorker/showSections.do" >

<display:column>
	<a href="section/handyWorker/editSection.do?sectionId=${row.id}"><spring:message code="section.edit" /></a>
</display:column>

<display:column property="number" titleKey="section.number" />
<display:column property="title" titleKey="section.title" />
<display:column property="pieceOfText" titleKey="section.pieceOfText" />

</display:table>

<input type="button" name="cancel" value="<spring:message code="section.cancel" />"
			onclick="javascript: relativeRedir('tutorial/handyWorker/tutorials.do');" />

</security:authorize>