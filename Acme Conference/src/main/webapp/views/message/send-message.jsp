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

<security:authorize access="isAuthenticated()">

<p><spring:message code="message.send" /></p>

<jstl:if test="${not empty exception}">
		<p style="color:red"> <spring:message code="messageBox.error" /> </p>
</jstl:if>

<form:form modelAttribute="newMessage" action ="message/actor/send.do">

	<form:hidden path="id"/>
	<form:hidden path="version" />
	<form:hidden path="moment" />
	<form:hidden path="sender" />
	<form:hidden path="receiver" />
	
<form:label path="emailReceiver"><spring:message code="message.emailReceiver" />:</form:label>
	<form:input path="emailReceiver" />
	<form:errors path="emailReceiver"/>
	<br />

<form:label path="subject"><spring:message code="message.subject" />:</form:label>
	<form:input path="subject" />
	<form:errors path="subject"/>
	<br />

<form:label path="body"><spring:message code="message.body" />:</form:label>
	<form:input path="body" />
	<form:errors path="body"/>
	<br />
	
	<form:label path="priority"><spring:message code="message.priority" />:</form:label>
	<form:select path="priority">
		<option value="0">HIGH</option>
		<option value="1">NEUTRAL</option>
		<option value="2">LOW</option>
	</form:select>
	<form:errors path="priority"/>
	<br />
	
	<form:label path="tag"><spring:message code="message.tag" />:</form:label>
	<form:input path="tag" />
	<form:errors path="tag"/>
	<br />
	<br />

<input type="submit" name="save" value="<spring:message code="message.send.save" />" />
<security:authorize access="hasRole('ADMIN')">
<input type="submit" name="broadcast" value="<spring:message code="message.send.broadcast" />" />
</security:authorize>
<input type="button" name="cancel" value="<spring:message code="message.send.cancel" />"
			onclick="javascript: relativeRedir('welcome/index.do');" />


</form:form>
<security:authorize access="hasRole('ADMIN')">
<p><spring:message code="message.send.help" /></p>
</security:authorize>
</security:authorize>