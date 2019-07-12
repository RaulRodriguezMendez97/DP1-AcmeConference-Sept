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

<security:authorize access="hasRole('SPONSOR')">

<p><spring:message code="sponsor.list" /></p>
<a href = "sponsorship/sponsor/create.do"><spring:message code="sponsorship.create" /></a>
<display:table pagesize="5" name="sponsorships" id="row" requestURI="sponorship/list.do" >

<display:column property="urlBanner" titleKey="sponsorship.urlBanner" />
<display:column property="linkTargetPage" titleKey="sponsorship.linkTargetPage"/>
<display:column property="sponsor.name" titleKey="sponsorship.sponsor" />
<display:column property="creditCard.holderName" titleKey="sponsorship.creditCard" />

</display:table>

</security:authorize>


