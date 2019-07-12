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


<p><spring:message code="personalRecord.title"/></p>
<img src="${personalRecord.photo}"><br/>
<fieldset>
<legend><spring:message code="personalRecord.datas" /></legend>
<spring:message code="personalRecord.nameHW" /> ${personalRecord.nameHandyWorker} <br/>
<spring:message code="personalRecord.email" /> ${personalRecord.email} <br/>
<spring:message code="personalRecord.phone" /> ${personalRecord.phone} <br/>
<spring:message code="personalRecord.linkedInProfile" /> ${personalRecord.linkedInProfile} <br/>
</fieldset>
<br />
</security:authorize>