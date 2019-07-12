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

<p><spring:message code="pictures.title" /></p>
<input type="hidden" value="tutorial" />
<display:table pagesize="5" name="pictures" id="row"
requestURI="picture/handyWorker/show.do" >

<display:column>
	<a href="picture/handyWorker/editPicture.do?pictureId=${row.id}" ><spring:message code="picture.edit" /></a>
</display:column>

<display:column titleKey="pictures"> <img src="${row.urlPicture}" width="130px" height="80px">  </display:column>

</display:table>

<input type="button" name="cancel" value="<spring:message code="picture.cancel" />"
			onclick="javascript: relativeRedir('tutorial/handyWorker/tutorials.do');" />

</security:authorize>