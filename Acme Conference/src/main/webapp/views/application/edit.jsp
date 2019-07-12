<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form action="application/handyWorker,customer/edit.do" modelAttribute="application">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment" format="{0,date,dd/MM/yyyy HH:mm}" />
	<form:hidden path="handyWorker" />
	
	<security:authorize access="hasRole('CUSTOMER')">
	<form:hidden path="fixUpTask" />
	<form:hidden path="price" />
		</security:authorize>
	<security:authorize access="hasRole('HANDYWORKER')">
	<form:hidden path="status" />
	</security:authorize>
	

	
<!-- solo lo puede modificar el customer -->
<security:authorize access="hasRole('CUSTOMER')">
<form:label path="status">
	<spring:message code="handyWorker.application.status" />:
</form:label>
<form:select id="status"	path="status" onchange="javascript: reloadCreditCards()">
		<form:option value="0" label="----" />		
		<option value="0"><spring:message code="status.aceptado" /></option>
		<option value="1"><spring:message code="status.pendiente" /></option>
		<option value="2"><spring:message code="status.rechazado" /></option>
	</form:select>
<form:errors cssClass="error" paht="status"/>

<br />
</security:authorize>
<security:authorize access="hasRole('HANDYWORKER')">
<form:label path="price">
	<spring:message code="handyWorker.application.price" />
</form:label>
<form:input path="price" />
<form:errors	cssClass="error"  path="price"/>

<br />
</security:authorize>

<form:label path="comments">
	<spring:message code="handyWorker.application.comments" />
</form:label>
<form:input path="comments" />
<form:errors cssClass="error"  path="comments"/>

<br />

<!-- Faltan creditCard y fixUpTask -->
<security:authorize access="hasRole('CUSTOMER')">
<form:label path="creditCard">
	<spring:message code="handyWorker.application.creditCard" />
</form:label>
<form:select id="creditCards" path="creditCard">
		<form:option value="0" label="----" />		
		<form:options items="${creditCards}" itemValue="id"
			itemLabel="number" />
	</form:select>
<form:errors cssClass="error" path="creditCard"/>
<br />
</security:authorize>

<security:authorize access="hasRole('HANDYWORKER')">
<form:label path="fixUpTask">
	<spring:message code="handyWorker.application.fixUpTask" />
</form:label>
<form:select id="fixUpTasks" path="fixUpTask">
		<form:option value="0" label="----" />		
		<form:options items="${fixUpTasks}" itemValue="id"
			itemLabel="ticker" />
	</form:select>
<form:errors cssClass="error" path="fixUpTask"/>
<br />
</security:authorize>
	
<input type="submit" name="save" value="<spring:message code="handyWorker.application.save" />" />

<input type="button" name="cancel" value="<spring:message code="handyWorker.application.cancel" />"
			onclick="javascript: relativeRedir('application/handyWorker,customer/applications.do');" />
	
<script type="text/javascript">
		function reloadCreditCards() {
			var valorStatus = $('select#status').val();
			var placeholder = $('select#creditCards');

			placeholder.load("application/handyWorker,customer/loadCreditCard.do?valorStatus=" + valorStatus);			
		}
	</script>

</form:form>


