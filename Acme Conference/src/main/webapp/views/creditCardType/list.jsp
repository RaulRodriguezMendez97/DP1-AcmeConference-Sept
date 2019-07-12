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

<form:form action="credit-card-type/administrator/list.do" modelAttribute="creditCardType">

	<form:label path="brandName">
	<spring:message code="creditCardType.brandName" />
	</form:label>
	<form:input path="brandName" />
	<form:errors cssClass="error" path="brandName"/>
	
	<input type="submit" name="save" value="<spring:message code="creditCardType.save" />" />

</form:form>

<p><spring:message code="creditCardType.list" /></p>
<display:table pagesize="5" name="creditCardTypes" id="row" requestURI="${requestURI}" >

<display:column property="brandName" titleKey="creditCardType.brandName" />

</display:table>

</security:authorize>


