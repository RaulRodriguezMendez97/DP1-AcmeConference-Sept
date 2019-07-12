<%--
 * action-2.jsp
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
<security:authorize access="hasRole('HANDYWORKER')">

<p><spring:message code="tutotial" /></p>
<display:table pagesize="5" name="tutorials" id="row"
requestURI="tutorial/handyWorker/tutorials.do" >


<display:column>
	<a href="tutorial/handyWorker/editTutorial.do?tutorialId=${row.id}"><spring:message code="tutorial.edit" /></a>
</display:column>
<display:column>
	<a href="tutorial/handyWorker/showTutorial.do?tutorialId=${row.id}"><spring:message code="tutorial.show" /></a>
</display:column>
<display:column property="title" titleKey="tutorial.title" />
<display:column property="moment" titleKey="tutorial.moment" />
<display:column property="summary" titleKey="tutorial.summary" />

<display:column>
	<a href="picture/handyWorker/show.do?idTutorial=${row.id}"><spring:message code="tutorial.pictures" /></a>
</display:column>
<display:column titleKey="tutorial.sections">
	<a href="section/handyWorker/showSections.do?tutorialId=${row.id}"><spring:message code="tutorial.sections" /></a>
</display:column>
</display:table>

<form action="tutorial/handyWorker/createTutorial.do">
    <input type="submit" value="<spring:message code="tutorial.create" />" />
</form>


<form action="picture/handyWorker/createPicture.do">
    <input type="submit" value="<spring:message code="tutorial.create.pictures" />" />
</form>




</security:authorize>
