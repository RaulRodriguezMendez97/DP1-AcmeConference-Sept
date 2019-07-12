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

<form:form action="messageBox/actor/edit.do" modelAttribute="messageBox">

<jstl:if test="${not empty exception}">
		<p style="color:red"> <spring:message code="messageBox.error" /> </p>
</jstl:if>

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="actor"/>
	
		
	<form:label path="name">
	<spring:message code="messageBox.create.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />
	
	<jstl:choose>
	<jstl:when test="${messageBox.id eq 0 }">
		<form:hidden path="messages"/>
	</jstl:when>
	
	<jstl:otherwise>
	<form:label path="messages">
	<spring:message code="messageBox.create.messages" />:
	</form:label>
	<form:select id="messages" path="messages">
		<form:options items="${messages}" itemValue="id" itemLabel="subject" />
	</form:select>
	<form:errors cssClass="error" path="messages" />
	<br />
	</jstl:otherwise>
	</jstl:choose>
			
	<br /><br />
	<input type="submit" name="save" 
	value="<spring:message code="messageBox.create.save" />" />

	<input type="button" name="cancel" value="<spring:message code="messageBox.create.cancel" />"
			onclick="javascript: relativeRedir('messageBox/actor/list.do');" />
	
	<jstl:if test="${messageBox.id ne 0 }">	
	<input type="submit" name="delete" 
	value="<spring:message code="messageBox.delete" />" />
	</jstl:if>	
</form:form>



</security:authorize>

