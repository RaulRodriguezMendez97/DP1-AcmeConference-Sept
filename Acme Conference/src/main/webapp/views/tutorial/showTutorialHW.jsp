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

<p><spring:message code="tutorial.HandyWorker"/></p>



<img src="${h.photo}"><br/>
<fieldset>
<legend><spring:message code="tutorial.handyWorker.personalDatas" /></legend>
<spring:message code="tutorial.HW.name" /> ${h.name} <br/>
<spring:message code="tutorial.HW.middleName" /> ${h.middleName} <br/>
<spring:message code="tutorial.HW.surname" /> ${h.surname} <br/>
<spring:message code="tutorial.HW.email" /> ${h.email} <br/>
<spring:message code="tutorial.HW.phone" /> ${h.phone} <br/>
<spring:message code="tutorial.HW.address" /> ${h.address} <br/>
<spring:message code="tutorial.HW.photo" /> ${h.photo} <br/>
<spring:message code="tutorial.HW.numberSocial" /> ${h.numberSocialProfiles} <br/>
</fieldset>
	<br />

