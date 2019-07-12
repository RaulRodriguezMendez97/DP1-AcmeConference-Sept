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

<img src="${customer.photo}">  <br/>
<spring:message code="profile.action.2.name" /> ${customer.name} <br/>
<spring:message code="profile.action.2.middleName" /> ${customer.middleName} <br/>
<spring:message code="profile.action.2.surname" /> ${customer.surname} <br/>
<spring:message code="profile.action.2.email" /> ${customer.email} <br/>
<spring:message code="profile.action.2.phone" /> ${customer.phone} <br/>
<spring:message code="profile.action.2.address" /> ${customer.address} <br/>
<spring:message code="profile.action.2.numberSocial" /> ${customer.numberSocialProfiles} <br/>
<%-- que hemos con la cuenta  --%>
<spring:message code="profile.socialProfile" />

<a href="fix-up-task/handy-worker/customer-data-profile.do?customerId=${customer.id}">Info</a><br />


<p><spring:message code="fixUpTask.list" /></p>
	<display:table  name="fixUpTasks" id="row">

		<display:column property="moment" titleKey="fixUpTask.moment"
			sortable="true" format="{0,date,dd/MM/yyyy HH:mm}" />
		<display:column property="description"
			titleKey="fixUpTask.description" />
		<display:column property="address" titleKey="fixUpTask.address" />
		<display:column property="maximunPrice" titleKey="fixUpTask.maximunPrice" />
		<display:column property="periodTime" titleKey="fixUpTask.periodTime" />
		<display:column property="category.name" titleKey="fixUpTask.category" />
		<display:column property="warranty.title" titleKey="fixUpTask.warranty" />
		<display:column property="application" titleKey="fixUpTask.application" />

	</display:table>
<br>
	<div style="text-align:center;">
		<a href="fix-up-task/handy-worker/list.do" ><spring:message code="fixUpTask.volver" /></a>
	</div>
</security:authorize>


