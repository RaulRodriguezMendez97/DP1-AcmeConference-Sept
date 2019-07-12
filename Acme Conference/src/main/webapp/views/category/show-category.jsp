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

<spring:message code="category.name" /> : ${category.name} <br/>
<spring:message code="category.parent" /> : ${category.parent.name} <br/>
<spring:message code="category.son" /> :  <br/>
<display:table name="${category.soon}" id="row">
<display:column property="name" titleKey="category.name" />
<display:column>
<jstl:if test="${row.name != 'CATEGORY'}">
		<a href="category/administrator/editByName.do?nameCategory=${row.name}"><spring:message code="category.edit" /></a>
	</jstl:if>
		
</display:column>

</display:table>
<br />
<br />
	<input type="button" name="cancel" value="<spring:message code="category.cancel" />"
			onclick="javascript: relativeRedir('category/administrator/list.do');" />
			
</security:authorize>
