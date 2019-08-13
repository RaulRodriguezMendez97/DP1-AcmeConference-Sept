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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<security:authorize access="hasRole('ADMIN')">
	<form:form action="message-broadcast/administrator/send.do" modelAttribute="mensaje">
	
		<jstl:if test="${not empty exception}">
			<p style="color:red"> <spring:message code="mensaje.error" /> </p>
		</jstl:if>
	
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		
		<acme:select items="${conferences }" itemLabel="title" code="mensaje.conference" path="conference"/>
		<acme:textbox code="mensaje.subject" path="subject"/>
		<acme:textarea code="mensaje.body" path="body"/>
		<acme:select items="${topics }" itemLabel="name" code="mensaje.topic" path="topic"/>
		
		<br/>
		<input type="submit" name="save-submission" 
		value="<spring:message code="mensaje.send.submission" />" />
		
		<input type="submit" name="save-registration" 
		value="<spring:message code="mensaje.send.registration" />" />
		
		<input type="submit" name="save-authors" 
		value="<spring:message code="mensaje.send.authors" />" />
		
		<input type="submit" name="save-alls" 
		value="<spring:message code="mensaje.send.alls" />" />

	</form:form>	
	
</security:authorize>