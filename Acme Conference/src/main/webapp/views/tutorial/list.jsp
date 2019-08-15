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


<security:authorize access="hasRole('ADMIN')">

<display:table pagesize="5" name="tutorials" id="row"
requestURI="tutorial/administrator/list.do" >
	
	<!--<display:column  titleKey="topic.name" >
		<jstl:out value="${row.spanishName}"></jstl:out>
    </display:column>-->

	<display:column>
	   <a href="tutorial/administrator/show.do?tutorialId=${row.id}"><spring:message code="tutorial.show" /></a>   
    </display:column>

</display:table>

<input type="button" name="create" value="<spring:message code="tutorial.create" />"
			onclick="javascript: relativeRedir('tutorial/administrator/edit.do');" />
</security:authorize>