<%--
 * action-1.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('AUTHOR')">

<form:form action="submission/author/edit.do" modelAttribute="submissionReviwedForm">
<form:hidden path="id"/>
<form:hidden path="version" />

<acme:textbox code="submission.reviwed.title" path="title"/>
<acme:textbox code="submission.reviwed.summary" path="summary"/>
<acme:textbox code="submission.reviwed.urlDocument" path="urlDocument"/>
<acme:select items="${conferences}" itemLabel="title" code="submission.conference.table" path="conf"/>
<acme:multipleSelect items="${coAuthors}" itemLabel="email" code="submission.reviwed.coAuthors" path="coAuthors"/>

<br/>
<input type="submit" name="save" value="<spring:message code="submission.save" />" />
<jstl:if test="${submissionReviwedForm.id ne 0 }">	
	<input type="submit" name="delete" value="<spring:message code="submission.delete" />" />
</jstl:if>
<acme:cancel url="submission/author/list.do" code="submission.cancel"/>
</form:form>
</security:authorize>





<security:authorize access="hasRole('ADMIN')">
<form:form action="submission/administrator/edit.do" modelAttribute="submission">

<jstl:if test="${not empty exception}">
		<p style="color:red"> <spring:message code="submission.error" /> </p>
</jstl:if>

<form:hidden path="id"/>
<form:hidden path="version" />

<jstl:if test="${(submission.status eq 0) and (empty submission.reviwers)}">	
	<acme:multipleSelect items="${reviwers}" itemLabel="email" code="submission.reviwers" path="reviwers"/>
</jstl:if>

<jstl:if test="${(submission.status eq 0) and ((submission.reviwers ne null) and (!empty submission.reviwers)) and (fechaActual > submission.conference.submissionDeadline)}">	
	<form:label path="status"><spring:message code="submission.status" />:</form:label>
		<form:select path="status">
			<jstl:if test="${res < 0}">
				<form:option value="1" label="Rejected" />	
			</jstl:if>
			<jstl:if test="${res >= 0}">
				<form:option value="2" label="Accepted" />
			</jstl:if>	
		</form:select>
</jstl:if>


<input type="submit" name="save" value="<spring:message code="submission.save" />" />
<acme:cancel url="submission/administrator/submissionsUnderReviwed.do" code="submission.cancel"/>

<jstl:if test="${(submission.status eq 0) and (empty submission.reviwers)}">	
		<input type="submit" name="asignar" value="<spring:message code="submission.asignarAutomaticamente" />" />
</jstl:if>
</form:form>
</security:authorize>