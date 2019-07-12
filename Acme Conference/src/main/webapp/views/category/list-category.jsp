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

<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>


<security:authorize access="hasRole('ADMIN')">

<display:table pagesize="5" name="categories" id="row"
requestURI="category/administrator/list.do" >

<display:column>
	<jstl:if test="${(row.name ne 'CATEGORY')}">
		<a href="category/administrator/edit.do?categoryId=${row.id}"><spring:message code="category.edit" /></a>
	</jstl:if>
</display:column>


<display:column property="name" titleKey="category.name"  />

<display:column>
		<a href="category/administrator/show.do?categoryId=${row.id}"><spring:message code="category.show" /></a>
</display:column>

</display:table>

<br />

<a href="category/administrator/create.do"><spring:message code="category.create" /></a>

</security:authorize>
