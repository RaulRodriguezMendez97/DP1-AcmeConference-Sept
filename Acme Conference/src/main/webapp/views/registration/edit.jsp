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

<form:form action="registration/author/edit.do" modelAttribute="registration">
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
	<form:label path="creditCard">
		<spring:message code="creditCard.holderName" />
	</form:label>
	<form:input path="creditCard" />
	<form:errors cssClass="error" path="creditCard" />
	<br /><br />
	<form:label path="creditCard.brandName">
		<spring:message code="creditCard.brandName" />
	</form:label>
	
	<form:select id="creditCard.brandName" path="creditCard.brandName">
		<form:options items="${marcas}"/>
	</form:select>
	<form:errors cssClass="error" path="creditCard.brandName" />
	<br /><br />

	<form:label path="creditCard.number">
		<spring:message code="creditCard.number" />
	</form:label>
	
	<form:input path="creditCard.number" />
	<form:errors cssClass="error" path="creditCard.number" />
	<br /><br />
	
	<form:label path="creditCard.expirationMonth">
		<spring:message code="creditCard.expirationMonth" />
	</form:label>
	
	<form:input path="creditCard.expirationMonth" />
	<form:errors cssClass="error" path="creditCard.expirationMonth" />
	<br /><br />
	<form:label path="creditCard.expirationYear">
		<spring:message code="creditCard.expirationYear" />
	</form:label>
	
	<form:input path="creditCard.expirationYear" />
	<form:errors cssClass="error" path="creditCard.expirationYear" />
	<br /><br />
	
	<form:label path="creditCard.CW">
		<spring:message code="creditCard.CW" />
	</form:label>
	
	<form:input path="creditCard.CW" />
	<form:errors cssClass="error" path="creditCard.CW" />
	<br /><br />
		<p>
			<spring:message code="registration.information" />
		</p>
				<legend>
			<spring:message code="select.creditCard" />
		</legend>
	<acme:select items="${myCreditCards}" itemLabel="number" code="registration.creditCard" path="creditCard"/>
	
	<br />
	</fieldset>
	<br />
	
	
	<fieldset>
		<legend>
			<spring:message code="select.conference" />
		</legend>
	<acme:select items="${conferences}" itemLabel="title" code="registration.conference" path="conference"/>
	</fieldset>
	<br />
	


	<input type="submit" name="save" value="<spring:message code="registration.save"/> ">

	<input type="button" name="cancel"
		value="<spring:message code="registration.cancel" />"
		onclick="javascript: relativeRedir('welcome/index.do');" />

</form:form>

