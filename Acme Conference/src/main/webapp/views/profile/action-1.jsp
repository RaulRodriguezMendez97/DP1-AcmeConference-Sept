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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>



<security:authorize access="isAuthenticated()">

<img src="<jstl:out value='${actor.photo}'/>" width="400px" height="200px" />  <br/>
<br/>
<fieldset>
	<legend><spring:message code="profile.personalDatas" /></legend>	
<b><spring:message code="profile.action.2.name" /> </b> <jstl:out value="${actor.name }"/> <br/>
<b><spring:message code="profile.action.2.surname" /></b> <jstl:out value="${actor.surname}"/> <br/>
<b><spring:message code="profile.action.2.email" /></b> <jstl:out value="${actor.email }"/> <br/>
<b><spring:message code="profile.action.2.phone" /></b> <jstl:out value="${actor.phone }"/> <br/>
<b><spring:message code="profile.action.2.address" /></b> <jstl:out value="${actor.address }"/> <br/>
<b><spring:message code="profile.action.2.middleName" /></b> <jstl:out value="${actor.middleName }"/> <br/>

<security:authorize access="hasRole('REVIWER')">
<b><spring:message code="profile.reviwer.keyWords" /></b> <jstl:out value="${actor.keyWords}"/> <br/>

</security:authorize>






</fieldset>

	<fieldset>
	 <legend><spring:message code="actor.userAccount" /></legend>
	 <b><spring:message code="see.username" /></b> <jstl:out value="${actor.userAccount.username }"/> <br/>
	 </fieldset>
	<br />
	




<acme:cancel url="welcome/index.do" code="action1.volver"/>


</security:authorize>

<security:authorize access="hasRole('ADMIN')">
<form action="profile/edit-administrator.do">
    <input type="submit" value="<spring:message code="profile.edit.profile" />" />
</form>
</security:authorize>


<security:authorize access="hasRole('REVIWER')">
<form action="profile/edit-reviwer.do">
    <input type="submit" value="<spring:message code="profile.edit.profile" />" />
</form>
</security:authorize>

<security:authorize access="hasRole('AUTHOR')">
<form action="profile/edit-author.do">
    <input type="submit" value="<spring:message code="profile.edit.profile" />" />
</form>
</security:authorize>

