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

<form:form action="note/referee/edit.do" modelAttribute="note">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment" format="{0,date,dd/MM/yyyy HH:mm}" />
	<form:hidden path="report" />



	<form:label path="comment">
		<spring:message code="note.comment" />:
	</form:label>
	<form:input path="comment" />
	<form:errors cssClass="error" path="comment" />
	<br /><br />

	<form:label path="optionalComments">
		<spring:message code="note.comment" />:
	</form:label>
	
	<form:input path="optionalComments" />
	<form:errors cssClass="error" path="optionalComments" />
	<br /><br />

	
	<input type="submit" name="save"
		value="<spring:message code="note.save" />" 
		onclick="return confirm('<spring:message code="note.confirm1.create" />')"
		onclick="return confirm('<spring:message code="note.confirm2.create" />')" />&nbsp; 

	
	
	<input type="button" name="cancel"
		value="<spring:message code="note.cancel" />"
		onclick="javascript: relativeRedir('note/referee/list.do?reportId=${note.report.id}');" />
	<br />



</form:form>
