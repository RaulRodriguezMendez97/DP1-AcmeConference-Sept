<%--
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

<security:authorize access="hasRole('ADMIN')">

<b><spring:message	code="tutorial.title" />:</b> <jstl:out value="${tutorial.title}"></jstl:out> <br/>
<b><spring:message	code="tutorial.speaker" />:</b> <jstl:out value="${tutorial.speaker}"></jstl:out> <br/>
<b><spring:message	code="tutorial.room" />:</b> <jstl:out value="${tutorial.room}"></jstl:out> <br/>
<b><spring:message	code="tutorial.schedule" />:</b> <jstl:out value="${tutorial.schedule}"></jstl:out> <br/>
<b><spring:message	code="tutorial.duration" />:</b> <jstl:out value="${tutorial.duration}"></jstl:out> <br/>
<b><spring:message	code="tutorial.summary" />:</b> <jstl:out value="${tutorial.summary}"></jstl:out> <br/>
<b><spring:message	code="tutorial.attachments" />:</b> <jstl:out value="${tutorial.attachments}"></jstl:out> <br/>
<b><spring:message	code="tutorial.sections" />:</b>
	<display:table name="sections" id="row" >
		<display:column titleKey="tutorial.section.title">
			<jstl:out value="${row.title}"></jstl:out>
		</display:column>

		<display:column titleKey="tutorial.section.summary">
			<jstl:out value="${row.summary}"></jstl:out>
		</display:column>

		<display:column titleKey="tutorial.section.pictures">
			<jstl:forEach items="${row.pictures}" var="item">
    			<img width="80" height="80" src="${item.urlPicture}">
			</jstl:forEach>
		</display:column>
	</display:table>

</security:authorize>


