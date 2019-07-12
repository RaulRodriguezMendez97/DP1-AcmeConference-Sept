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

<security:authorize access="isAuthenticated()">

<form:form action="profileSocial/actor/edit.do" modelAttribute="newProfile">

<jstl:if test="${not empty exception}">
		<p style="color:red"> <spring:message code="profile.error" /> </p>
</jstl:if>

<form:hidden path="id"/>
<form:hidden path="version"/>
<form:hidden path="actor"/>

<form:label path="nickName">
	<spring:message code="profile.nickname" />:
	</form:label>
	<form:input path="nickName" />
	<form:errors cssClass="error" path="nickName" />
	<br />
	
	<form:label path="nameSocialNetwork">
	<spring:message code="profile.nameSocial" />:
	</form:label>
	<form:input path="nameSocialNetwork" />
	<form:errors cssClass="error" path="nameSocialNetwork" />
	<br />
	
	<form:label path="linkProfile">
	<spring:message code="profile.link" />:
	</form:label>
	<form:input path="linkProfile" />
	<form:errors cssClass="error" path="linkProfile" />
	<br />

<input type="submit" name="save" 
	value="<spring:message code="profile.save" />" />
	
<input type="submit" name="delete" 
	value="<spring:message code="profile.delete" />" />

<input type="button" name="cancel" value="<spring:message code="category.cancel" />"
			onclick="javascript: relativeRedir('profileSocial/actor/list.do');" />
</form:form>

</security:authorize>
