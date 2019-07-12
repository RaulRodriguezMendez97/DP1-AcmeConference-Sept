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

<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>


<security:authorize access="hasRole('ADMIN')">

<form:form action="category/administrator/edit.do" modelAttribute="category">

<jstl:if test="${not empty exception}">
		<p style="color:red"> <spring:message code="category.error" /> </p>
</jstl:if>

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="soon" />
	
	
	<form:label path="name">
	<spring:message code="category.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />
	
	<form:label path="parent">
	<spring:message code="category.parent"/>:
	</form:label>
	<form:select path="parent">
		<form:options items="${categories}" itemLabel="name" itemValue="id"/>
	</form:select>
	<br />
	
	
	<input type="submit" name="save" 
	value="<spring:message code="category.save" />" />

<input type="button" name="cancel" value="<spring:message code="category.cancel" />"
			onclick="javascript: relativeRedir('category/administrator/list.do');" />
<jstl:if test="${category.id ne 0 }">
<input type="submit" name="delete" 
	value="<spring:message code="category.delete" />" />
</jstl:if>
</form:form>

</security:authorize>
