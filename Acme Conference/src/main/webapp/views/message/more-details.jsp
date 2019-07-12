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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<security:authorize access="isAuthenticated()">

<b><spring:message code="message.moment" />:</b> <fmt:formatDate value="${mensaje.moment }" pattern="yyyy-MM-dd HH:mm" />
<br/> 
<b><spring:message code="message.sender" />:</b> ${mensaje.sender.email }
<br/> 
<b><spring:message code="message.emailReceiver" />:</b> ${mensaje.emailReceiver }
<br/> 
<b><spring:message code="message.subject" />:</b> ${mensaje.subject } 
<br/> 
<b><spring:message code="message.body" />: </b>${mensaje.body }
<br/> 
<b><spring:message code="message.priority" />: </b>
<jstl:choose>
<jstl:when test="${mensaje.priority eq 0}">
HIGH
</jstl:when>
<jstl:when test="${mensaje.priority eq 1}">
NEUTRAL
</jstl:when>
<jstl:otherwise>
LOW
</jstl:otherwise>
</jstl:choose>
<br/>
<b><spring:message code="message.tag" />: </b>${mensaje.tag }

</security:authorize>
