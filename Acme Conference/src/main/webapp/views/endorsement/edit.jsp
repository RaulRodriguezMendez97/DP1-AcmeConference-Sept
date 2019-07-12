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

<security:authorize access="hasRole('CUSTOMER')">

<p><spring:message code="warranty.edit" /></p>
<jstl:if test="${not empty exception}">
		<p style="color:red;font-weight: bold;" > <spring:message code="endorsement.error" /> </p>
</jstl:if>
<form:form action="endorsement/customer/edit.do" modelAttribute="endorsement">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="moment"/>
	<form:hidden path="handyWorkerSender"/>
	<form:hidden path="customerReceiver"/>
	<form:hidden path="handyWorkerReceiver"/>
	<form:hidden path="customerSender"/>
	<security:authorize access="hasRole('CUSTOMER')">
		<spring:message code="endorsement.sender" />: ${endorsement.customerSender.name} -> ${endorsement.customerSender.email} <br />
		<spring:message code="endorsement.receiver" />: ${endorsement.handyWorkerReceiver.name} -> ${endorsement.handyWorkerReceiver.email} <br />
	</security:authorize>
	
	<spring:message code="endorsement.moment" />: ${endorsement.moment} <br />
	
	<form:label path="comments">
	<spring:message code="endorsement.comments"/>:
	</form:label>
	<form:input path="comments"/>
	<form:errors cssClass="error" path="comments"/>
	
	<br /><br />
	<input type="submit" name="save" 
	value="<spring:message code="endorsement.save" />" />
	
	<input type="button" name="cancel" 
	value="<spring:message code="endorsement.cancel" />" 
	onclick="javascript:relativeRedir('endorsement/customer/list.do');"/>
	
</form:form>

</security:authorize>

<security:authorize access="hasRole('HANDYWORKER')">

<p><spring:message code="warranty.edit" /></p>
<jstl:if test="${not empty exception}">
		<p style="color:red;font-weight: bold;" > <spring:message code="endorsement.error" /> </p>
</jstl:if>
<form:form action="endorsement/handy-worker/edit.do" modelAttribute="endorsement">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="moment"/>
	<form:hidden path="handyWorkerSender"/>
	<form:hidden path="customerReceiver"/>
	<form:hidden path="handyWorkerReceiver"/>
	<form:hidden path="customerSender"/>
	<security:authorize access="hasRole('HANDYWORKER')">	
		<spring:message code="endorsement.sender" />: ${endorsement.handyWorkerSender.name} -> ${endorsement.handyWorkerSender.email} <br />
		<spring:message code="endorsement.receiver" />: ${endorsement.customerReceiver.name} -> ${endorsement.customerReceiver.email} <br />
	</security:authorize>
	
	<spring:message code="endorsement.moment" />: ${endorsement.moment} <br />
	
	<form:label path="comments">
	<spring:message code="endorsement.comments"/>:
	</form:label>
	<form:input path="comments"/>
	<form:errors cssClass="error" path="comments"/>
	
	<br /><br />
	<input type="submit" name="save" 
	value="<spring:message code="endorsement.save" />" />
	
	<input type="button" name="cancel" 
	value="<spring:message code="endorsement.cancel" />" 
	onclick="javascript:relativeRedir('endorsement/handy-worker/list.do');"/>
	
</form:form>

</security:authorize>

