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
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<security:authorize access="hasRole('HANDYWORKER')">

<p><spring:message code="tutorial.show.title"/></p>

<jstl:if test="${fn:length(tutorial.sponsorship) > 0}">
	<img src="${tutorial.sponsorship[0].urlBanner}"><br/>
</jstl:if>

<p>${tutorial.title }</p>
<br/>
<p>${tutorial.moment }</p>
<br/>
<p>${tutorial.summary }</p>
<br/>
<p><strong><spring:message code="tutorial.show.section.title"/></strong></p>
<br/>
<jstl:forEach var="section" items="${tutorial.section}">
	<p>${section.number }, ${section.title }, ${section.pieceOfText }</p>
	<br/>
</jstl:forEach>
<p><strong><spring:message code="tutorial.show.sponsorship.title"/></strong></p>
<jstl:forEach var="sponsorship" items="${tutorial.sponsorship}">
	<img src="${sponsorship.urlBanner}">
	<br/>
	<p>${sponsorship.linkTargetPage }, ${sponsorship.creditCard.number }, ${sponsorship.sponsor.name }</p>
</jstl:forEach>

</security:authorize>