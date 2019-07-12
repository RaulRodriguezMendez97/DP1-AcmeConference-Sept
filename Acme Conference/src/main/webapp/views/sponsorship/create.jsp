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

<security:authorize access="hasRole('SPONSOR')">

<p><spring:message code="sponsorship.new" /></p>
<form:form action="sponsorship/sponsor/edit.do" modelAttribute="sponsorship">
	
	<form:hidden path="sponsor"/>
	
	<form:label path="urlBanner">
	<spring:message code="sponsorship.urlBanner"/>:
	</form:label>
	<form:input path="urlBanner"/>
	<form:errors cssClass="error" path="urlBanner"/>
	<br />
	
	<form:label path="linkTargetPage">
	<spring:message code="sponsorship.linkTargetPage"/>:
	</form:label>
	<form:input path="linkTargetPage"/>
	<form:errors cssClass="error" path="linkTargetPage"/>
	<br />
	
	<form:label path="creditCard">
	<spring:message code="sponsorship.creditCard"/>:
	</form:label>
	<form:select path="creditCard">
		<form:options items="${creditCards}" itemLabel="holderName" itemValue="id"/>
	</form:select>
	
	<br /><br />
	<input type="submit" name="save" 
	value="<spring:message code="sponsorship.save" />" />
	
	<input type="button" name="cancel" 
	value="<spring:message code="sponsorship.cancel" />" 
	onclick="javascript:relativeRedir('sponsorship/sponsor/list.do');"/>	
	
</form:form>

</security:authorize>


