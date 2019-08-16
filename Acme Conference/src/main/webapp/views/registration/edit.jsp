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

<form:form action="registration/edit.do" modelAttribute="registration">
	<jstl:if test="${not empty exception}">
		<p style="color: red">
			
		</p>
	</jstl:if>
	<form:hidden path="id" />
	<form:hidden path="version" />



	<fieldset>
		<legend>
			<spring:message code="registration.personalDatas" />
		</legend>

		<form:label path="name">
			<spring:message code="registration.name" />
		</form:label>
		<form:input path="name" />
		<form:errors cssClass="error" path="name" />
		<br />

		<form:label path="middleName">
			<spring:message code="registration.middleName" />
		</form:label>
		<form:input path="middleName" />
		<form:errors cssClass="error" path="middleName" />
		<br />

		<form:label path="surname">
			<spring:message code="registration.surname" />
		</form:label>
		<form:input path="surname" />
		<form:errors cssClass="error" path="surname" />
		<br />

		<form:label path="photo">
			<spring:message code="registration.photo" />
		</form:label>
		<form:input path="photo" />
		<form:errors cssClass="error" path="photo" />
		<br />

		<form:label path="email">
			<spring:message code="registration.email" />
		</form:label>
		<form:input path="email" />
		<form:errors cssClass="error" path="email" />
		<br />

		<form:label path="phone">
			<spring:message code="registration.phone" />
		</form:label>
		<form:input path="phone" />
		<form:errors cssClass="error" path="phone" />
		<br />

		<form:label path="address">
			<spring:message code="registration.adress" />
		</form:label>
		<form:input path="address" />
		<form:errors cssClass="error" path="address" />
		<br />
		<p>
			<spring:message code="registration.information" />
		</p>
	</fieldset>
	<br />


	<fieldset>
		<legend>
			<spring:message code="administrator.userAccount" />
		</legend>

		<acme:textbox code="registration.username" path="userAccount.username" />

		<acme:password code="registration.password" path="userAccount.password" />


		<acme:password code="registration.confirmation.password" path="password" />

	</fieldset>
	<br />

	<input type="submit" name="save" onclick=" return validar(); "	value="<spring:message code="registration.save"/> ">

	<input type="button" name="cancel"
		value="<spring:message code="registration.cancel" />"
		onclick="javascript: relativeRedir('welcome/index.do');" />

</form:form>

<script>
	function validar() {
		return validar_phone();
	}

	function validar_phone() {
		var numeroTelefono = document.getElementById('phone');
		var expresionRegular1 = /^\+[0-9]{0,3}\ \([0-9]{0,3}\)\ [0-9]{4,}$|^\+[1-9][0-9]{0,2}\ [0-9]{4,}$|^[0-9]{4,}|^\+[0-9]\ $|^$|^\+$/gm;//<-- hay que cambiar el pattern

		if (!expresionRegular1.test(numeroTelefono.value)) {
			var confirmarTelefono = confirm('Are you sure you want to register that phone number?');

			if (confirmarTelefono == true) {

				document.getElementById('patternPhone').value = true;

			}
		}
		return confirmarTelefono;

	}
</script>