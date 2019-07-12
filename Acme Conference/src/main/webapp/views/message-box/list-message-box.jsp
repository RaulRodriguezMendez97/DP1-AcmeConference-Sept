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


<security:authorize access="isAuthenticated()">

<p><spring:message code="profile.action.1" /></p>
<display:table pagesize="5" name="boxes" id="row"
requestURI="messageBox/actor/list.do" >
<jstl:if test="${fn:length(boxes) ne 4}">
<display:column>
	<jstl:if test="${(row.name ne 'Spam box') and (row.name ne 'Out box') and (row.name ne 'In box') and (row.name ne 'Trash box')}">
		<a href="messageBox/actor/edit.do?messageBoxId=${row.id}"><spring:message code="messageBox.list.edit" /></a>
	</jstl:if>
</display:column>
</jstl:if>
<display:column property="name" titleKey="messageBox.name"  />
<display:column> <a href="message/actor/list.do?messageBoxId=${row.id}"><spring:message code="messageBox.show.message" /></a> </display:column>


</display:table>

<form action="messageBox/actor/create.do">
    <input type="submit" value="<spring:message code="messageBox.create" />" />
</form>

<input type="button" name="cancel" value="<spring:message code="messageBox.create.cancel" />"
			onclick="javascript: relativeRedir('welcome/index.do');" />

</security:authorize>
