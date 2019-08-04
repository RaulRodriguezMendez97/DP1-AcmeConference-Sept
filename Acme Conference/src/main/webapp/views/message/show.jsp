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

<security:authorize access="isAuthenticated()">

	<jstl:if test="${lang eq 'es' }">
	<b><spring:message code="mensaje.moment" />:</b>
		<fmt:formatDate value="${mensaje.moment}" pattern="dd-MM-yy HH:mm" />
		<br />
	</jstl:if>
	
	<jstl:if test="${lang eq 'en' }">
	<b><spring:message code="mensaje.moment" />:</b>
		<fmt:formatDate value="${mensaje.moment}" pattern="yy/MM/dd HH:mm" />
		<br />
	</jstl:if>

<b><spring:message code="mensaje.subject" /> : </b> <jstl:out value="${mensaje.subject}"></jstl:out> <br/>
<b><spring:message code="mensaje.body" /> : </b> <jstl:out value="${mensaje.body}"></jstl:out> <br/>
<b><spring:message code="mensaje.sender" /> : </b> <jstl:out value="${mensaje.sender.email}"></jstl:out> <br/>
<b><spring:message code="mensaje.receiver" /> : </b> <jstl:out value="${mensaje.receiver.email}"></jstl:out> <br/>
<b><spring:message code="mensaje.topic" /> : </b> 
<jstl:if test="${lang eq 'en' }">
<jstl:out value="${mensaje.topic.name}"></jstl:out> <br/>
</jstl:if>

<jstl:if test="${lang eq 'es' }">
<jstl:out value="${mensaje.topic.spanishName}"></jstl:out> <br/>
</jstl:if>

<br/>
<input type="button" name="cancel" value="<spring:message code="mensaje.cancel" />"
			onclick="javascript: relativeRedir('message/actor/list.do');" />

</security:authorize>