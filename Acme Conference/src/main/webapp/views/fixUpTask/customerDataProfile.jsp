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



<security:authorize access="hasRole('HANDYWORKER')">

<spring:message code="profile.socialProfile" />

<display:table name="${profileSocialNetwork}" id="row">
	<display:column property="nickName" titleKey="profile.nickname" />
	<display:column property="nameSocialNetwork" titleKey="profile.socialNetwork" />
	<display:column property="linkProfile" titleKey="profile.link" />
</display:table>
<br/>
<a href="fix-up-task/handy-worker/customer-data.do?customerId=${customer.id}"><spring:message code="fixUpTask.volver" /></a><br />

<br>
<%-- 	<div style="text-align:center;">
		<a href="fix-up-task/handy-worker/list.do" ><spring:message code="fixUpTask.volver" /></a>
	</div> --%>
</security:authorize>


