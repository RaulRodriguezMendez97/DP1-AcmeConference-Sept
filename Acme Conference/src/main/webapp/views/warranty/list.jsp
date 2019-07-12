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

<security:authorize access="hasRole('ADMIN')">

<a href="warranty/administrator/create.do"><spring:message code="warranty.create" /></a>

<p><spring:message code="warranty.list" /></p>
<jstl:if test="${not empty exception}">
		<p style="color:red;font-weight: bold;"> <spring:message code="warranty.error.foreignKey" /> </p>
</jstl:if>
<display:table pagesize="5" name="warranties" id="row" requestURI="warranty/administrator/list.do" >
	
	<display:column property="title" titleKey="warranty.title"/>
	
	<display:column titleKey="warranty.show">
		<a href="warranty/administrator/show.do?warrantyId=${row.id}"><spring:message code="warranty.show" /></a>
	</display:column>
	
	<display:column titleKey="warranty.edit">
	<jstl:if test="${row.draftMode == 1}">
		<a href="warranty/administrator/edit.do?warrantyId=${row.id}"><spring:message code="warranty.edit" /></a>
	</jstl:if>
	<jstl:if test="${row.draftMode == 0}">
		<a>-</a>
	</jstl:if>
	</display:column>
	
	<display:column titleKey="warranty.delete">
	<jstl:if test="${row.draftMode == 1}">
		<a href="warranty/administrator/delete.do?warrantyId=${row.id}"><spring:message code="warranty.delete" /></a>
	</jstl:if>
	<jstl:if test="${row.draftMode == 0}">
		<a>-</a>
	</jstl:if>
	</display:column>
</display:table>


</security:authorize>


