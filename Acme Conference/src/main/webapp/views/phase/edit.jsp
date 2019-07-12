<%--
 * edit.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="phase/handyWorker/edit.do" modelAttribute="phase">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="startMoment" />
	<form:hidden path="application"/>


<form:label path="title">
		<spring:message code="phase.title" />:
	</form:label>
	<form:input path="title" />
	<form:errors cssClass="error" path="title" />
	<br /><br />
	
	<form:label path="description">
		<spring:message code="report.description" />:
	</form:label>
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br /><br />
	
		<form:label path="endMoment">
		<spring:message code="phase.endMoment" />:
	</form:label>
	<form:input path="endMoment" format="{0,date,dd/MM/yyyy HH:mm}"/>
	<form:errors cssClass="error" path="endMoment" />
	<br /><br />

	
	<input type="submit" name="save"
		value="<spring:message code="phase.save" />" />&nbsp; 
	
	<jstl:if test="${report.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="phase.delete" />"
			onclick="return confirm('<spring:message code="phase.confirm.delete" />')" />&nbsp;
	</jstl:if>
	
	
	<input type="button" name="cancel"
		value="<spring:message code="report.cancel" />"
		onclick="javascript: relativeRedir('phase/handyWorker/list.do?applicationId=${phase.application.id}');" />
	<br />

	
	


</form:form>
