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

<p><spring:message code="finder.results" /></p>
<jstl:if test="${not empty exception}">
		<p style="color:red"> <spring:message code="finder.error" /> </p>
</jstl:if>
<display:table pagesize="5" name="fixUpTasks" id="row" requestURI="${requestURI}" >

<display:column property="ticker" titleKey="fixUpTask.ticker" />
<display:column property="moment" titleKey="fixUpTask.moment"/>
<display:column property="description" titleKey="fixUpTask.description" />
<display:column property="address" titleKey="fixUpTask.address" />
<display:column property="maximunPrice" titleKey="fixUpTask.maximunPrice" />
<display:column property="periodTime" titleKey="fixUpTask.periodTime" />

</display:table>

<div style="text-align:center;">
	<a href="finder/handy-worker/show.do" ><spring:message code="filter.volver" /></a>
</div>
</security:authorize>


