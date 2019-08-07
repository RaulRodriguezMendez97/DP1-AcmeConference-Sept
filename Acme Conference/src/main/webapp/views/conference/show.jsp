<%--
 * action-1.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
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
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<security:authorize access="hasRole('ADMIN')">

	<b><spring:message code="conference.title" /> : </b> <jstl:out value="${conference.title}"></jstl:out> <br/>
	<b><spring:message code="conference.acronym" /> : </b> <jstl:out value="${conference.acronym}"></jstl:out> <br/>
	<b><spring:message code="conference.venue" /> : </b> <jstl:out value="${conference.venue}"></jstl:out> <br/>
	<b><spring:message code="conference.fee" /> : </b> <jstl:out value="${conference.fee}"></jstl:out> <br/>
	
	<jstl:if test="${lang eq 'es' }">
	<b><spring:message code="conference.submissionDeadline" />:</b>
		<fmt:formatDate value="${conference.submissionDeadline}" pattern="dd-MM-yy HH:mm" />
		<br />
	</jstl:if>
	
	<jstl:if test="${lang eq 'en' }">
	<b><spring:message code="conference.submissionDeadline" />:</b>
		<fmt:formatDate value="${conference.submissionDeadline}" pattern="yy/MM/dd HH:mm" />
		<br />
	</jstl:if>
	
	<jstl:if test="${lang eq 'es' }">
	<b><spring:message code="conference.notificacionDeadline" />:</b>
		<fmt:formatDate value="${conference.notificacionDeadline}" pattern="dd-MM-yy HH:mm" />
		<br />
	</jstl:if>
	
	<jstl:if test="${lang eq 'en' }">
	<b><spring:message code="conference.notificacionDeadline" />:</b>
		<fmt:formatDate value="${conference.notificacionDeadline}" pattern="yy/MM/dd HH:mm" />
		<br />
	</jstl:if>
	
	<jstl:if test="${lang eq 'es' }">
	<b><spring:message code="conference.cameraDeadline" />:</b>
		<fmt:formatDate value="${conference.cameraDeadline}" pattern="dd-MM-yy HH:mm" />
		<br />
	</jstl:if>
	
	<jstl:if test="${lang eq 'en' }">
	<b><spring:message code="conference.cameraDeadline" />:</b>
		<fmt:formatDate value="${conference.cameraDeadline}" pattern="yy/MM/dd HH:mm" />
		<br />
	</jstl:if>
	
	<jstl:if test="${lang eq 'es' }">
	<b><spring:message code="conference.startDate" />:</b>
		<fmt:formatDate value="${conference.startDate}" pattern="dd-MM-yy HH:mm" />
		<br />
	</jstl:if>
	
	<jstl:if test="${lang eq 'en' }">
	<b><spring:message code="conference.startDate" />:</b>
		<fmt:formatDate value="${conference.startDate}" pattern="yy/MM/dd HH:mm" />
		<br />
	</jstl:if>
	
	<jstl:if test="${lang eq 'es' }">
	<b><spring:message code="conference.endDate" />:</b>
		<fmt:formatDate value="${conference.endDate}" pattern="dd-MM-yy HH:mm" />
		<br />
	</jstl:if>
	
	<jstl:if test="${lang eq 'en' }">
	<b><spring:message code="conference.endDate" />:</b>
		<fmt:formatDate value="${conference.endDate}" pattern="yy/MM/dd HH:mm" />
		<br />
	</jstl:if>
	
	<b><spring:message code="conference.finalMode" />:</b>
		<jstl:choose>
			<jstl:when test="${conference.finalMode eq 0}">
				<spring:message code="conference.inDraftMode" /> <br/>
			</jstl:when>
		
			<jstl:otherwise>
				<spring:message code="conference.inSaveMode" /><br/>
			</jstl:otherwise>
	</jstl:choose>

<br/>
<input type="button" name="cancel" value="<spring:message code="conference.cancel" />"
			onclick="javascript: relativeRedir('conference/administrator/list.do');" />

</security:authorize>