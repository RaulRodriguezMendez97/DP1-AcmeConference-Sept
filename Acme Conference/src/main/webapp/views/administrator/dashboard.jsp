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
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<security:authorize access="hasRole('ADMIN')">

<fieldset>
<legend><spring:message code="administrator.submission.conference" /></legend>
<b><spring:message code="administrator.avg" /></b>: <jstl:out value="${getAvgSubmissionsByConference}"></jstl:out><br/>
<b><spring:message code="administrator.min" /></b>: <jstl:out value="${getMinSubmissionsByConference}"></jstl:out><br/>
<b><spring:message code="administrator.max" /></b>: <jstl:out value="${getMaxSubmissionsByConference}"></jstl:out><br/>
<b><spring:message code="administrator.desv" /></b>: <fmt:formatNumber type="number" maxIntegerDigits = "3" value ="${getDesvSubmissionsByConference}"></fmt:formatNumber><br/>
</fieldset>

<fieldset>
<legend><spring:message code="administrator.registration.conference" /></legend>
<b><spring:message code="administrator.avg" /></b>: <jstl:out value="${getAvgRegistrationByConference}"></jstl:out><br/>
<b><spring:message code="administrator.min" /></b>: <jstl:out value="${getMinRegistrationByConference}"></jstl:out><br/>
<b><spring:message code="administrator.max" /></b>: <jstl:out value="${getMaxRegistrationByConference}"></jstl:out><br/>
<b><spring:message code="administrator.desv" /></b>: <fmt:formatNumber type="number" maxIntegerDigits = "3" value ="${getDesvRegistrationByConference}"></fmt:formatNumber><br/>
</fieldset>

<fieldset>
<legend><spring:message code="administrator.fee.conference" /></legend>
<b><spring:message code="administrator.avg" /></b>: <jstl:out value="${getAvgFeesByConference}"></jstl:out><br/>
<b><spring:message code="administrator.min" /></b>: <jstl:out value="${getMinFeesByConference}"></jstl:out><br/>
<b><spring:message code="administrator.max" /></b>: <jstl:out value="${getMaxFeesByConference}"></jstl:out><br/>
<b><spring:message code="administrator.desv" /></b>: <fmt:formatNumber type="number" maxIntegerDigits = "3" value ="${getDesvFeesByConference}"></fmt:formatNumber><br/>
</fieldset>

<fieldset>
<legend><spring:message code="administrator.days.conference" /></legend>
<b><spring:message code="administrator.avg" /></b>: <fmt:formatNumber type="number" maxIntegerDigits = "3" value ="${getAvgDaysByConference}"></fmt:formatNumber><br/>
<b><spring:message code="administrator.min" /></b>: <jstl:out value="${getMinDaysByConference}"></jstl:out><br/>
<b><spring:message code="administrator.max" /></b>: <jstl:out value="${getMaxDaysByConference}"></jstl:out><br/>
<b><spring:message code="administrator.desv" /></b>: <fmt:formatNumber type="number" maxIntegerDigits = "3" value ="${getDesvDaysByConference}"></fmt:formatNumber><br/>
</fieldset>


</security:authorize>

