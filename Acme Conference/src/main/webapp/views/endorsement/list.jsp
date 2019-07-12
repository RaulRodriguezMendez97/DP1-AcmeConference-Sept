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

<security:authorize access="hasRole('CUSTOMER')">

<a href="endorsement/customer/create.do"><spring:message code="endorsement.create" /></a>

<p><spring:message code="endorsement.list" /></p>
<jstl:if test="${not empty exception}">
		<p style="color:red"> <spring:message code="endorsement.error" /> </p>
</jstl:if>
<display:table pagesize="5" name="endorsements" id="row" requestURI="endorsement/customer/list.do" >
	<display:column property="moment" titleKey="endorsement.moment"/>
	
	<jstl:choose>
    	<jstl:when test="${row.customerReceiver.email==myEmail}">
			<display:column titleKey="endorsement.sender">${row.handyWorkerSender.email}</display:column>
			<display:column titleKey="endorsement.receiver">${row.customerReceiver.email}</display:column>
    	</jstl:when>    
    	<jstl:otherwise>
     	   	<display:column titleKey="endorsement.sender">${row.customerSender.email}</display:column>
			<display:column titleKey="endorsement.receiver">${row.handyWorkerReceiver.email}</display:column>
		 </jstl:otherwise>
	</jstl:choose>
	
	<display:column titleKey="endorsement.show">
		<a href="endorsement/customer/show.do?endorsementId=${row.id}"><spring:message code="endorsement.show" /></a>
	</display:column>
	
	<display:column titleKey="endorsement.edit">
	<jstl:choose>
    	<jstl:when test="${row.customerSender.email==myEmail}">
			<a href="endorsement/customer/edit.do?endorsementId=${row.id}"><spring:message code="endorsement.edit" /></a> 
    	</jstl:when>    
    	<jstl:otherwise>
     	   <a>-</a>
    	</jstl:otherwise>
	</jstl:choose>
	</display:column>
	
	<display:column titleKey="endorsement.delete">
	<jstl:choose>
    	<jstl:when test="${row.customerSender.email==myEmail}">
			<a href="endorsement/customer/delete.do?endorsementId=${row.id}"><spring:message code="endorsement.delete" /></a> 
    	</jstl:when>    
    	<jstl:otherwise>
     	   <a>-</a>
    	</jstl:otherwise>
	</jstl:choose>
	</display:column>
</display:table>

</security:authorize>


<security:authorize access="hasRole('HANDYWORKER')">

<a href="endorsement/handy-worker/create.do"><spring:message code="endorsement.create" /></a>

<p><spring:message code="endorsement.list" /></p>
<jstl:if test="${not empty exception}">
		<p style="color:red"> <spring:message code="endorsement.error" /> </p>
</jstl:if>
<display:table pagesize="5" name="endorsements" id="row" requestURI="endorsement/handy-worker/list.do" >
	<display:column property="moment" titleKey="endorsement.moment"/>
	
	<jstl:choose>
    	<jstl:when test="${row.handyWorkerReceiver.email==myEmail}">
			<display:column titleKey="endorsement.sender">${row.customerSender.email}</display:column>
			<display:column titleKey="endorsement.receiver">${row.handyWorkerReceiver.email}</display:column>
    	</jstl:when>    
    	<jstl:otherwise>
			<display:column titleKey="endorsement.sender">${row.handyWorkerSender.email}</display:column>
			<display:column titleKey="endorsement.receiver">${row.customerReceiver.email}</display:column>
    	</jstl:otherwise>
	</jstl:choose>
	
	<display:column titleKey="endorsement.show">
		<a href="endorsement/handy-worker/show.do?endorsementId=${row.id}"><spring:message code="endorsement.show" /></a>
	</display:column>
	
	<display:column titleKey="endorsement.edit">
	<jstl:choose>
    	<jstl:when test="${row.handyWorkerSender.email==myEmail}">
			<a href="endorsement/handy-worker/edit.do?endorsementId=${row.id}"><spring:message code="endorsement.edit" /></a> 
    	</jstl:when>    
    	<jstl:otherwise>
     	   <a>-</a>
    	</jstl:otherwise>
	</jstl:choose>
	</display:column>
	
	<display:column titleKey="endorsement.delete">
	<jstl:choose>
    	<jstl:when test="${row.handyWorkerSender.email==myEmail}">
			<a href="endorsement/handy-worker/delete.do?endorsementId=${row.id}"><spring:message code="endorsement.delete" /></a> 
    	</jstl:when>    
    	<jstl:otherwise>
     	   <a>-</a>
    	</jstl:otherwise>
	</jstl:choose>
	</display:column>
</display:table>

</security:authorize>

