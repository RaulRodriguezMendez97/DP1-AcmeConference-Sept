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

<form:form action="spam-word/administrator/list.do" modelAttribute="spamWord">

	<form:label path="name">
	<spring:message code="spamWord.name" />
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name"/>
	
	<input type="submit" name="save" value="<spring:message code="spamWord.save" />" />

</form:form>

<p><spring:message code="spamWord.list" /></p>
<display:table pagesize="5" name="spamWords" id="row" requestURI="${requestURI}" >

<display:column property="name" titleKey="spamWord.name" />

</display:table>

</security:authorize>


