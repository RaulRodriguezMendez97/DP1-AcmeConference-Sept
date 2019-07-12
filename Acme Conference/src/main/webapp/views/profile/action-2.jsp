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

<p><spring:message code="profile.action.2"/></p>

<security:authorize access="isAuthenticated()">

<img src="${actor.photo}">  <br/>
<br/>
<b><spring:message code="profile.action.2.name" /> </b> ${actor.name} <br/>
<b><spring:message code="profile.action.2.middleName" /></b> ${actor.middleName} <br/>
<b><spring:message code="profile.action.2.surname" /></b> ${actor.surname} <br/>
<b><spring:message code="profile.action.2.email" /></b> ${actor.email} <br/>
<b><spring:message code="profile.action.2.phone" /></b> ${actor.phone} <br/>
<b><spring:message code="profile.action.2.address" /></b> ${actor.address} <br/>
<b><spring:message code="profile.action.2.numberSocial" /></b> ${actor.numberSocialProfiles} <a href="profileSocial/actor/list.do"><spring:message code="profile.list" /></a>

<br/>
<input type="button" name="cancel" value="<spring:message code="administrator.cancel" />"
			onclick="javascript: relativeRedir('welcome/index.do');" />


</security:authorize>

<security:authorize access="hasRole('ADMIN')">
<form action="profile/edit-administrator.do">
    <input type="submit" value="<spring:message code="profile.edit.profile" />" />
</form>
</security:authorize>

<security:authorize access="hasRole('SPONSOR')">
<form action="profile/edit-sponsor.do">
    <input type="submit" value="<spring:message code="profile.edit.profile" />" />
</form>
</security:authorize>

<security:authorize access="hasRole('REFEREE')">
<form action="profile/edit-referee.do">
    <input type="submit" value="<spring:message code="profile.edit.profile" />" />
</form>
</security:authorize>

<security:authorize access="hasRole('CUSTOMER')">
<form action="fixUpTask/customer/list.do">
    <input type="submit" value="<spring:message code="fixUpTask.list" />" />
</form>
</security:authorize>

