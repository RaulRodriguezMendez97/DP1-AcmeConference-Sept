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



<security:authorize access="hasAnyRole('CUSTOMER,HANDYWORKER')">

	<spring:message code="fixUpTask.moment" />: ${fixUpTask.moment} <br />
	<spring:message code="fixUpTask.description" />: ${fixUpTask.description} <br />
	<spring:message code="fixUpTask.address" />: ${fixUpTask.address} <br />
	<spring:message code="fixUpTask.maximunPrice" />: ${fixUpTask.maximunPrice} <br />
	<spring:message code="fixUpTask.periodTime" />: ${fixUpTask.periodTime} <br />
	<spring:message code="fixUpTask.category" />: ${fixUpTask.category.name} <br />
	<spring:message code="fixUpTask.warranty" />: ${fixUpTask.warranty.title} <br />
	<security:authorize access="hasRole('HANDYWORKER')">
		<spring:message code="fixUpTask.customer" /> <a href="fix-up-task/handy-worker/customer-data.do?customerId=${fixUpTask.customer.id}">Info</a><br />
	</security:authorize>
	<spring:message code="fixUpTask.application" />
	<display:table name="${fixUpTask.application}" id="row">
		<display:column property="moment"
			titleKey="fixUpTask.application.moment" />
		<display:column property="status"
			titleKey="fixUpTask.application.status" />
		<display:column property="price"
			titleKey="fixUpTask.application.price" />
	</display:table>
	<br><br>
</security:authorize>

<security:authorize access="hasRole('CUSTOMER')">
	<div style="text-align:center;">
	<a href="fix-up-task/customer/list.do" ><spring:message code="fixUpTask.volver" /></a>
	</div>
</security:authorize>


<security:authorize access="hasRole('HANDYWORKER')">
	<div style="text-align:center;">
	<a href="fix-up-task/handy-worker/list.do" ><spring:message code="fixUpTask.volver" /></a>
	</div>
</security:authorize>