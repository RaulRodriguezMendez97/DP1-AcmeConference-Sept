<%--
 * action-1.jsp
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

	<spring:message code="application.moment" />: ${application.moment} <br />
	<spring:message code="application.status" />: ${application.status} <br />
	<spring:message code="application.price" />: ${application.price} <br />
	<spring:message code="application.comments" />: ${application.comments} <br />
	<spring:message code="application.fixUpTask" />: ${application.fixUpTask.ticker} <br />
	<spring:message code="application.customer" />: ${application.fixUpTask.customer.name} <br />



	<div style="text-align:center;">
	<a href="application/handyWorker,customer/applications.do" ><spring:message code="application.volver" /></a>
	</div>


</security:authorize>