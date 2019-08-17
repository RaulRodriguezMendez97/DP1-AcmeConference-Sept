<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<security:authorize access="hasRole('ADMIN')">

	<b><spring:message code="tutorial.sections" />:</b>
	<display:table pagesize="5" name="sections" id="row"
		requestURI="section/administrator/list.do">
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

	<input type="button" name="create"
		value="<spring:message code="section.create" />"
		onclick="javascript: relativeRedir('section/administrator/create.do?tutorialId='${tutorialId});" />
	<input type="button" name="back"
		value="<spring:message code="tutorial.back" />"
		onclick="javascript: relativeRedir('tutorial/administrator/list.do');" />
</security:authorize>