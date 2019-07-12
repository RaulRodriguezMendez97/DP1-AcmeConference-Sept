<%--
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

<script type="text/javascript" src="scripts/finder.js"></script>

<security:authorize access="hasRole('HANDYWORKER')">

<p><spring:message code="finder.show" /></p>
<jstl:if test="${not empty exception}">
		<p style="color:red"> <spring:message code="finder.error" /> </p>
</jstl:if>

<display:table pagesize="5" name="finder" id="row" requestURI="finder/show.do" >

<display:column property="ticker" titleKey="filter.ticker" />
<display:column property="description" titleKey="filter.description"/>
<display:column property="address" titleKey="filter.address" />
<display:column property="startDate" titleKey="filter.startDate" />
<display:column property="endDate" titleKey="filter.endDate" />
<display:column property="lowPrice" titleKey="filter.lowPrice" />
<display:column property="highPrice" titleKey="filter.highPrice" />
<display:column property="category.name" titleKey="filter.category" />
<display:column property="warranty.title" titleKey="filter.warranty" />

</display:table>

<div style="text-align:center;">
	<a href="finder/handy-worker/list.do" ><spring:message code="filter.busqueda-anterior" /></a>
</div>

<spring:message code="finder.keyword"/>:
<input id="keyword" type = "text" name = "keyword" oninput="getValue()" value="${finder.ticker}">
<br />

<form:form action="finder/handy-worker/save.do" modelAttribute="finder">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="moment"/>
	<form:hidden id="ticker" path="ticker" value=""/>
	<form:hidden id="description" path="description" value=""/>
	<form:hidden id="address" path="address" value=""/>
	
	<form:label path="startDate">
	<spring:message code="filter.startDate"/>:
	</form:label>
	<form:input path="startDate"/>
	<form:errors cssClass="error" path="startDate"/>
	<br />
	
	<form:label path="endDate">
	<spring:message code="filter.endDate"/>:
	</form:label>
	<form:input path="endDate"/>
	<form:errors cssClass="error" path="endDate"/>
	<br />
	
	<form:label path="lowPrice">
	<spring:message code="filter.lowPrice"/>:
	</form:label>
	<form:input path="lowPrice"/>
	<form:errors cssClass="error" path="lowPrice"/>
	<br />
	
	<form:label path="highPrice">
	<spring:message code="filter.highPrice"/>:
	</form:label>
	<form:input path="highPrice"/>
	<form:errors cssClass="error" path="highPrice"/>
	<br />
	
	<form:label path="category">
	<spring:message code="filter.category"/>:
	</form:label>
	<form:select path="category">
		<form:options items="${categories}" itemLabel="name" itemValue="id"/>
		<form:option label="todas" value="-1"></form:option>
	</form:select>
	<br />
	
	<form:label path="warranty">
	<spring:message code="filter.warranty"/>:
	</form:label>
	<form:select path="warranty">
		<form:options items="${warranties}" itemLabel="title" itemValue="id"/>
		<form:option label="todas" value="-1"></form:option>
	</form:select>
	
	<br /><br />
		
	<input type="submit" name="search" 
	value="<spring:message code="filter.search" />" onclick="cadenaVacia()" />
</form:form>
</security:authorize>


