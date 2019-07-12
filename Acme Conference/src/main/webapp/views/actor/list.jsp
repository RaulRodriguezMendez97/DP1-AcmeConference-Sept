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

<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<security:authorize access="hasRole('ADMIN')">

<display:table pagesize="5" name="actors" id="row"
requestURI="suspiciousActor/administrator/list.do" >

<display:column property="name" titleKey="actor.name"  />
<display:column property="middleName" titleKey="actor.middleName"  />
<display:column property="surname" titleKey="actor.surname"  />
<display:column>
	<jstl:out value="${row.userAccount.authorities }"></jstl:out>
</display:column>

<display:column>
	<jstl:choose>
		<jstl:when test = "${fn:contains(row.userAccount.authorities, 'ADMIN') or fn:contains(row.userAccount.authorities, 'ADMIN_BAN')}">
			<a href="suspiciousActor/administrator/editAdministrator.do?idAdmin=${row.id}"><spring:message code="actor.edit" /></a>
		</jstl:when>
		
		<jstl:when test = "${fn:contains(row.userAccount.authorities, 'CUSTOMER') or fn:contains(row.userAccount.authorities, 'CUSTOMER_BAN')}">
			<a href="suspiciousActor/administrator/editCustomer.do?idCustomer=${row.id}"><spring:message code="actor.edit" /></a>
		</jstl:when>
		
		<jstl:when test = "${fn:contains(row.userAccount.authorities, 'HANDYWORKER') or fn:contains(row.userAccount.authorities, 'HANDY_WORKER_BAN')}">
			<a href="suspiciousActor/administrator/editHandyWorker.do?idHandyWorker=${row.id}"><spring:message code="actor.edit" /></a>
		</jstl:when>
		
		<jstl:when test = "${fn:contains(row.userAccount.authorities, 'SPONSOR') or fn:contains(row.userAccount.authorities, 'SPONSOR_BAN')}">
			<a href="suspiciousActor/administrator/editSponsor.do?idSponsor=${row.id}"><spring:message code="actor.edit" /></a>
		</jstl:when>
		
		<jstl:when test = "${fn:contains(row.userAccount.authorities, 'REFEREE') or fn:contains(row.userAccount.authorities, 'REFEREE_BAN')}">
			<a href="suspiciousActor/administrator/editReferee.do?idReferee=${row.id}"><spring:message code="actor.edit" /></a>
		</jstl:when>
	</jstl:choose>
</display:column>
</display:table>
<input type="button" name="cancel" value="<spring:message code="category.cancel" />"
			onclick="javascript: relativeRedir('welcome/index.do');" />

</security:authorize>
