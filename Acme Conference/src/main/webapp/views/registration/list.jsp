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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<security:authorize access="hasRole('AUTHOR')">

<display:table pagesize="5" name="registrations" id="row"
requestURI="registration/author/list.do" >

	        
      

	    <display:column  titleKey="registration.creditCard.number" >
	      <jstl:out value="${row.creditCard.number}"></jstl:out>
        </display:column>
        
       	<display:column  titleKey="registration.conference.title" >
	      <jstl:out value="${row.conference.title}"></jstl:out>
        </display:column>
        
             
       	<display:column>
	  		 <a href="registration/author/show.do?idRegistration=${row.id}"><spring:message code="registration.show" /></a> 
    	</display:column>
        
        

</display:table>

<input type="button" name="create" value="<spring:message code="registration.create" />"
			onclick="javascript: relativeRedir('registration/author/create.do');" />
</security:authorize>

