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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<security:authorize access="hasRole('REVIWER')">
<form:form action="report/reviwer/edit.do?submissionId=${submissionId}" modelAttribute="report">
		<form:hidden path="id"/>
		<form:hidden path="version"/>

<acme:textbox code="report.originalityScore" path="originalityScore"/>
<acme:textbox code="report.qualityScore" path="qualityScore"/>
<acme:textbox code="report.eadabilityScore" path="eadabilityScore"/>
<jstl:if test="${report.id ne 0}">
	<form:label path="decision"><spring:message code="report.decision" />:</form:label>
		<form:select path="decision">
			<form:option value="0" label="Rejected" />	
			<form:option value="1" label="Border line" />	
			<form:option value="2" label="Accept" />		
		</form:select>
</jstl:if>
<acme:textbox code="report.comments" path="comment"/>

<br/>
<input type="submit" name="save" value="<spring:message code="report.save" />" />
<jstl:if test="${report.id ne 0 }">	
	<input type="submit" name="delete" value="<spring:message code="report.delete" />" />
</jstl:if>
<acme:cancel url="report/reviwer/list.do?submissionId=${submissionId}" code="report.cancel"/>


</form:form>
</security:authorize>