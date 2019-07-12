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

<p><spring:message code="warranty.create" /></p>

<form:form action="warranty/administrator/create.do" modelAttribute="warranty">
	
	<form:label path="title">
	<spring:message code="warranty.title"/>:
	</form:label>
	<form:input path="title"/>
	<form:errors cssClass="error" path="title"/>
	<br />
	
	<form:label path="terms">
	<spring:message code="warranty.terms"/>:
	</form:label>
	<form:input path="terms"/>
	<form:errors cssClass="error" path="terms"/>
	<br />
	
	<form:label path="laws">
	<spring:message code="warranty.laws"/>:
	</form:label>
	<form:input path="laws"/>
	<form:errors cssClass="error" path="laws"/>
	<br />
	
	<form:label path="draftMode">
	<spring:message code="warranty.draftmode"/>:
	</form:label>
	<form:input path="draftMode"/>
	<form:errors cssClass="error" path="draftMode"/>*
	<p>*<spring:message code="warranty.draftmode.text"/></p>
		
	<br />
	<input type="submit" name="save" 
	value="<spring:message code="warranty.save" />" />
	
	<input type="button" name="cancel" 
	value="<spring:message code="warranty.cancel" />" 
	onclick="javascript:relativeRedir('warranty/administrator/list.do');"/>
</form:form>

</security:authorize>


