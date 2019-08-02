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

<display:table pagesize="5" name="topics" id="row"
requestURI="topic/administrator/list.do" >

	<jstl:if test="${lang eq 'en' }">
		<display:column  titleKey="topic.name" >
	      <jstl:out value="${row.name}"></jstl:out>
        </display:column>
	</jstl:if>
	
	<jstl:if test="${lang eq 'es' }">
		<display:column  titleKey="topic.name" >
	      <jstl:out value="${row.spanishName}"></jstl:out>
        </display:column>
	</jstl:if>

	<display:column>
	   <a href="topic/administrator/delete.do?idTopic=${row.id}"><spring:message code="topic.delete" /></a>   
    </display:column>

</display:table>

<input type="button" name="create" value="<spring:message code="topic.create" />"
			onclick="javascript: relativeRedir('topic/administrator/create.do');" />
</security:authorize>