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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="registration/author/edit.do" modelAttribute="registrationForm">
	<jstl:if test="${not empty exception}">
		<p style="color: red">
			
		</p>
	</jstl:if>
	<form:hidden path="id" />
	<form:hidden path="version" />


	<fieldset>
		<legend>
			<spring:message code="CreditCard.Datas" />
		</legend>
	<form:label path="holdName">
		<spring:message code="creditCard.holderName" />
	</form:label>
	<form:input path="holdName" />
	<form:errors cssClass="error" path="holdName" />
	<br /><br />
	<form:label path="brandName">
		<spring:message code="creditCard.brandName" />
	</form:label>
	
	<form:select id="brandName" path="brandName">
		<form:options items="${marcas}"/>
	</form:select>
	<form:errors cssClass="error" path="brandName" />
	<br /><br />

	<form:label path="number">
		<spring:message code="creditCard.number" />
	</form:label>
	
	<form:input path="number" />
	<form:errors cssClass="error" path="number" />
	<br /><br />
	
	<form:label path="expirationMonth">
		<spring:message code="creditCard.expirationMonth" />
	</form:label>
	
	<form:input path="expirationMonth" />
	<form:errors cssClass="error" path="expirationMonth" />
	<br /><br />
	<form:label path="expirationYear">
		<spring:message code="creditCard.expirationYear" />
	</form:label>
	
	<form:input path="expirationYear" />
	<form:errors cssClass="error" path="expirationYear" />
	<br /><br />
	
	<form:label path="CW">
		<spring:message code="creditCard.CW" />
	</form:label>
	
	<form:input path="CW" />
	<form:errors cssClass="error" path="creditCard.CW" />
	<br /><br />
		<p>
			<spring:message code="registration.information" />
		</p>
		<h3>
			<spring:message code="select.creditCard" />
		</h3>
	<acme:select items="${myCreditCards}" itemLabel="number" code="registration.creditCard" path="creditCard"/>
	
	<br />
	</fieldset>
	<br />
	
	
	<fieldset>
		<legend>
			<spring:message code="select.conference" />
		</legend>
	
	<jstl:choose>
	<jstl:when test = "${ not empty conferences}">
	<acme:select items="${conferences}" itemLabel="title" code="registration.conference" path="conference"/>
	</jstl:when>
	<jstl:when test = "${empty conferences}">
	<h4 style="color: red"> <spring:message code="conference.noDisponible" /> </h4>
	</jstl:when>
	
	</jstl:choose>
	</fieldset>
	<br />
	
<jstl:if test="${not empty conferences}">

	<input type="submit" name="save" value="<spring:message code="registration.save"/> ">
</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="registration.cancel" />"
		onclick="javascript: relativeRedir('welcome/index.do');" />

</form:form>

