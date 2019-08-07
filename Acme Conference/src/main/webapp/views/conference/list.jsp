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

<display:table pagesize="5" name="conferences" id="row"
requestURI="conference/administrator/list.do" >

	        
       	<display:column>
	  		 <a href="conference/administrator/show.do?idConference=${row.id}"><spring:message code="conference.show" /></a> 
    	</display:column>

	    <display:column  titleKey="conference.title" >
	      <jstl:out value="${row.title}"></jstl:out>
        </display:column>
        
       	<display:column  titleKey="conference.venue" >
	      <jstl:out value="${row.venue}"></jstl:out>
        </display:column>
        
        <jstl:choose>
			<jstl:when test="${lang eq 'en'}">
				<display:column property="startDate" titleKey="conference.startDate" format="{0,date,yy/MM/dd hh:mm}"  />
			</jstl:when>
		
			<jstl:otherwise>
				<display:column property="startDate" titleKey="conference.startDate" format="{0,date,dd-MM-yy hh:mm}"  />
			</jstl:otherwise>
		</jstl:choose>
		
		<jstl:choose>
			<jstl:when test="${lang eq 'en'}">
				<display:column property="endDate" titleKey="conference.endDate" format="{0,date,yy/MM/dd hh:mm}"  />
			</jstl:when>
		
			<jstl:otherwise>
				<display:column property="endDate" titleKey="conference.endDate" format="{0,date,dd-MM-yy hh:mm}"  />
			</jstl:otherwise>
		</jstl:choose>
		
		<display:column  titleKey="conference.fee" >
	      <jstl:out value="${row.fee}"></jstl:out>
        </display:column>
        
       	<display:column>
       		<jstl:if test="${row.finalMode eq 0 }">
	  		 	<a href="conference/administrator/edit.do?idConference=${row.id}"><spring:message code="conference.edit" /></a> 
	  		 </jstl:if>  
    	</display:column>

</display:table>

<input type="button" name="create" value="<spring:message code="conference.create" />"
			onclick="javascript: relativeRedir('conference/administrator/create.do');" />
</security:authorize>