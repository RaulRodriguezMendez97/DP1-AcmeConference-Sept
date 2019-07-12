<%--
 * action-2.jsp
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

<form:form action="word/administrator/list.do" modelAttribute="newWord">

<jstl:if test="${not empty exception}">
		<p style="color:red"> <spring:message code="word.error" /> </p>
</jstl:if>

	<form:hidden path="id"/>
	<form:hidden path="version" />

	<form:label path="name">
	<spring:message code="word.name" />
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name"/>
	<br/>
	
	<form:label path="value"><spring:message code="word.value" />:</form:label>
	<form:select path="value">
		<form:option value="0" label="Positive" />	
		<form:option value="1" label="Negative" />	
	</form:select>
	<form:errors path="value"/>
	
	<input type="submit" name="save" value="<spring:message code="word.save" />" />

</form:form>

<p><spring:message code="word.list" /></p>
<display:table pagesize="5" name="words" id="row" requestURI="${requestURI}" >

<jstl:choose>

<jstl:when test="${row.value eq 0 }">
<display:column property="name" style="color:green" titleKey="word.name" />
</jstl:when>

<jstl:when test="${row.value eq 1 }">
<display:column property="name" style="color:red" titleKey="word.name" />
</jstl:when>
</jstl:choose>

</display:table>

</security:authorize>


