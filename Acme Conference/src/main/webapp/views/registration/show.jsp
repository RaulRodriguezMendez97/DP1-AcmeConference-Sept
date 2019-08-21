<%--
 * action-1.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
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
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<security:authorize access="hasRole('AUTHOR')">
	
<fieldset>
	 <legend><spring:message code="Conference.Data" /></legend>
	<b><spring:message code="conference.title" /> : </b> <jstl:out value="${registration.conference.title}"></jstl:out> <br/>
	<b><spring:message code="conference.acronym" /> : </b> <jstl:out value="${registration.conference.acronym}"></jstl:out> <br/>
	<b><spring:message code="conference.venue" /> : </b> <jstl:out value="${registration.conference.venue}"></jstl:out> <br/>
	<b><spring:message code="conference.fee" /> : </b> <jstl:out value="${registration.conference.fee}"></jstl:out> <br/>
	
	<jstl:if test="${lang eq 'es' }">
	<b><spring:message code="conference.submissionDeadline" />:</b>
		<fmt:formatDate value="${registration.conference.submissionDeadline}" pattern="dd-MM-yy HH:mm" />
		<br />
	</jstl:if>
	
	<jstl:if test="${lang eq 'en' }">
	<b><spring:message code="conference.submissionDeadline" />:</b>
		<fmt:formatDate value="${registration.conference.submissionDeadline}" pattern="yy/MM/dd HH:mm" />
		<br />
	</jstl:if>
	
	<jstl:if test="${lang eq 'es' }">
	<b><spring:message code="conference.notificacionDeadline" />:</b>
		<fmt:formatDate value="${registration.conference.notificacionDeadline}" pattern="dd-MM-yy HH:mm" />
		<br />
	</jstl:if>
	
	<jstl:if test="${lang eq 'en' }">
	<b><spring:message code="conference.notificacionDeadline" />:</b>
		<fmt:formatDate value="${registration.conference.notificacionDeadline}" pattern="yy/MM/dd HH:mm" />
		<br />
	</jstl:if>
	
	<jstl:if test="${lang eq 'es' }">
	<b><spring:message code="conference.cameraDeadline" />:</b>
		<fmt:formatDate value="${registration.conference.cameraDeadline}" pattern="dd-MM-yy HH:mm" />
		<br />
	</jstl:if>
	
	<jstl:if test="${lang eq 'en' }">
	<b><spring:message code="conference.cameraDeadline" />:</b>
		<fmt:formatDate value="${registration.conference.cameraDeadline}" pattern="yy/MM/dd HH:mm" />
		<br />
	</jstl:if>
	
	<jstl:if test="${lang eq 'es' }">
	<b><spring:message code="conference.startDate" />:</b>
		<fmt:formatDate value="${registration.conference.startDate}" pattern="dd-MM-yy HH:mm" />
		<br />
	</jstl:if>
	
	<jstl:if test="${lang eq 'en' }">
	<b><spring:message code="conference.startDate" />:</b>
		<fmt:formatDate value="${registration.conference.startDate}" pattern="yy/MM/dd HH:mm" />
		<br />
	</jstl:if>
	
	<jstl:if test="${lang eq 'es' }">
	<b><spring:message code="conference.endDate" />:</b>
		<fmt:formatDate value="${registration.conference.endDate}" pattern="dd-MM-yy HH:mm" />
		<br />
	</jstl:if>
	
	<jstl:if test="${lang eq 'en' }">
	<b><spring:message code="conference.endDate" />:</b>
		<fmt:formatDate value="${registration.conference.endDate}" pattern="yy/MM/dd HH:mm" />
		<br />
	</jstl:if>
		</fieldset>
	
<fieldset>
	 <legend><spring:message code="CreditCard.Datas" /></legend>
	 <b><spring:message code="creditCard.brandName" /> </b> <jstl:out value="${registration.creditCard.brandName}"/> <br/>
	 <b><spring:message code="creditCard.holderName" /> </b> <jstl:out value="${registration.creditCard.holdName}"/> <br/>
	 <b><spring:message code="creditCard.number" /> </b> <jstl:out value="${registration.creditCard.number}"/> <br/>
	 <b><spring:message code="creditCard.expirationMonth" /> </b> <jstl:out value="${registration.creditCard.expirationMonth}"/> <br/>
	 <b><spring:message code="creditCard.expirationYear" /> </b> <jstl:out value="${registration.creditCard.expirationYear}"/> <br/>
	 <b><spring:message code="creditCard.CW" /> </b> <jstl:out value="${registration.creditCard.CW}"/> <br/>
	 
	 <br />
	</fieldset>

<br/>
<input type="button" name="cancel" value="<spring:message code="registration.volver" />"
			onclick="javascript: relativeRedir('registration/author/list.do');" />

</security:authorize>
