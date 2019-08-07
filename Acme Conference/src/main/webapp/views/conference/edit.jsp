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
	<form:form action="conference/administrator/edit.do" modelAttribute="conference">
	
		<jstl:if test="${not empty exception}">
			<p style="color:red"> <spring:message code="conference.error" /> </p>
		</jstl:if>
	
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		
		<acme:textbox code="conference.title" path="title"/>
		<acme:textbox code="conference.acronym" path="acronym"/>
		<acme:textbox code="conference.venue" path="venue"/>
		<acme:textbox code="conference.submissionDeadline" path="submissionDeadline"/>
		<acme:textbox code="conference.notificacionDeadline" path="notificacionDeadline"/>
		<acme:textbox code="conference.cameraDeadline" path="cameraDeadline"/>
		<acme:textbox code="conference.startDate" path="startDate"/>
		<acme:textbox code="conference.endDate" path="endDate"/>
		<acme:textbox code="conference.summary" path="summary"/>
		<acme:textbox code="conference.fee" path="fee"/>
		
		<form:label path="finalMode"><spring:message code="conference.finalMode" />:</form:label>
		<form:select path="finalMode">
			<form:option value="0" label="Draft mode" />	
			<form:option value="1" label="Save mode" />		
		</form:select>
	<form:errors path="finalMode"/>
		
		<br/>
		<input type="submit" name="save" 
		value="<spring:message code="conference.save" />" />

		<input type="button" name="cancel" value="<spring:message code="conference.cancel" />"
				onclick="javascript: relativeRedir('conference/administrator/list.do');" />
	</form:form>	
	
</security:authorize>