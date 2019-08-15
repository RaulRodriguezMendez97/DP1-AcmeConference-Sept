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

<security:authorize access="hasRole('AUTHOR')">

	<b><spring:message code="camaraReady.title" /> : </b> <jstl:out value="${camaraReady.title}"></jstl:out> <br/>
	<b><spring:message code="camaraReady.summary" /> : </b> <jstl:out value="${camaraReady.summary}"></jstl:out> <br/>
	<b><spring:message code="camaraReady.urlDocument" /> : </b> <jstl:out value="${camaraReady.urlDocument}"></jstl:out> <br/>

	<h3><spring:message code="submission.conference" /></h3>
	<jstl:if test="${lang eq 'es' }">
	<b><spring:message code="conference.cameraDeadline" />:</b>
		<fmt:formatDate value="${submission.conference.cameraDeadline}" pattern="dd-MM-yy HH:mm" />
		<br />
	</jstl:if>
	
	<jstl:if test="${lang eq 'en' }">
	<b><spring:message code="conference.cameraDeadline" />:</b>
		<fmt:formatDate value="${submission.conference.cameraDeadline}" pattern="yy/MM/dd HH:mm" />
		<br />
	</jstl:if>
	
<br/>
<input type="button" name="cancel" value="<spring:message code="camaraReady.cancel" />"
			onclick="javascript: relativeRedir('submission/author/list.do');" />

</security:authorize>