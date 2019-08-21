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

<form:form action="section/administrator/edit.do" modelAttribute="section">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="tutorial" />
	<acme:textbox code="tutorial.section.title" path="title"/>
	<acme:textbox code="tutorial.section.summary" path="summary"/>
	<acme:textbox code="section.form.picture" path="picture"/>
	<jstl:forEach items="${section.pictures}" var="item">
	<div>
		<img width="80" height="80" src="${item.urlPicture}"><br/>
		<a href="picture/administrator/delete.do?tutorialId=${tutorialId}&sectionId=${section.id}&pictureId=${item.id}"><spring:message
						code="tutorial.delete" /></a>
	</div>
	</jstl:forEach>
		<br/>
	<input type="submit" name="save" 
	value="<spring:message code="tutorial.save" />" />
	
	<input type="button" name="cancel" value="<spring:message code="tutorial.cancel" />"
			onclick="javascript: relativeRedir('section/administrator/list.do?tutorialId=${tutorialId}');" />
</form:form>


</security:authorize>