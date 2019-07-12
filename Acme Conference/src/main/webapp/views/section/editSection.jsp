<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="isAuthenticated()">

<p><spring:message code="section.edit.title" /></p>

<form:form action="section/handyWorker/editSection.do" modelAttribute="section">
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	
<form:label path="number">
	<spring:message code="section.number.form" />
</form:label>
<form:input path="number" />
<form:errors path="number"/>

<br />

<form:label path="title">
	<spring:message code="section.title.form" />
</form:label>
<form:input path="title" />
<form:errors path="title"/>

<br />

<form:label path="pieceOfText">
	<spring:message code="section.pieceOfText.form" />
</form:label>
<form:input path="pieceOfText" />
<form:errors path="pieceOfText"/>

<br />
	
<input type="submit" name="save" value="<spring:message code="section.save" />" />
<input type="button" name="cancel" value="<spring:message code="section.cancel" />"
			onclick="javascript: relativeRedir('section/handyWorker/showSections.do');" />
	

</form:form>


</security:authorize>