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


<security:authorize access="isAuthenticated()">

	<display:table pagesize="5" name="mensajes" id="row"
	requestURI="message/actor/list.do" >

		<jstl:choose>
			<jstl:when test="${lang eq 'en'}">
				<display:column property="moment" titleKey="message.moment" format="{0,date,yy/MM/dd hh:mm}"  />
			</jstl:when>
		
			<jstl:otherwise>
				<display:column property="moment" titleKey="message.moment" format="{0,date,dd-MM-yy hh:mm}"  />
			</jstl:otherwise>
		</jstl:choose>

		<display:column  titleKey="message.sender" >
	      <jstl:out value="${row.sender.email}"></jstl:out>
        </display:column>
        
        <display:column  titleKey="message.receiver" >
	      <jstl:out value="${row.receiver.email}"></jstl:out>
        </display:column>

		<display:column  titleKey="message.subject" >
	      <jstl:out value="${row.subject}"></jstl:out>
        </display:column>
        
		<jstl:if test="${lang eq 'en' }">
			<display:column  titleKey="message.topic" >
	     	 <jstl:out value="${row.topic.name}"></jstl:out>
      	  </display:column>
		</jstl:if>
	
		<jstl:if test="${lang eq 'es' }">
			<display:column  titleKey="message.topic" >
	    	  <jstl:out value="${row.topic.spanishName}"></jstl:out>
       	 </display:column>
		</jstl:if>

	</display:table>
</security:authorize>