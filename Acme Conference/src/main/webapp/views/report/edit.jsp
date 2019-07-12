<%--
 * edit.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="report/referee/edit.do" modelAttribute="report">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment" format="{0,date,dd/MM/yyyy HH:mm}" />
	<form:hidden path="complaint" />



	<form:label path="description">
		<spring:message code="report.description" />:
	</form:label>
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br /><br />

	<form:label path="attachment">
		<spring:message code="report.attachment" />:
	</form:label>
	<form:select id="attachments" path="attachment" >
		<form:option value="0" label="----" />		
		<form:options items="${attachments}" itemValue="id" itemLabel="link"/>
	
	</form:select>
	<form:errors cssClass="error" path="attachment" />
	<br /> <br />
		<form:label path="published">
		<spring:message code="report.published" />:
	</form:label>
	<form:input path="published" />
	<form:errors cssClass="error" path="published" />
	<br /><br />

	<jstl:if test="${report.published==0}">
	<input type="submit" name="save"
		value="<spring:message code="report.save" />" />&nbsp; 
	
	<jstl:if test="${report.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="report.delete" />"
			onclick="return confirm('<spring:message code="report.confirm.delete" />')" />&nbsp;
	</jstl:if>
	</jstl:if>
	
	
	<input type="button" name="cancel"
		value="<spring:message code="report.cancel" />"
		onclick="javascript: relativeRedir('report/referee/list.do?complaintId=${report.complaint.id}');" />
	<br />

	
	


</form:form>
