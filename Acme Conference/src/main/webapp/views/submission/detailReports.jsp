<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('ADMIN')">

<b><spring:message code="report.accepted" /> : </b> <jstl:out value="${reportAccepted}"></jstl:out> <br/>
<b><spring:message code="report.rejected" /> : </b> <jstl:out value="${reportRejected}"></jstl:out> <br/>
<b><spring:message code="report.borderLine" /> : </b> <jstl:out value="${reportBorderLine}"></jstl:out> <br/>

<acme:cancel url="submission/administrator/submissionsUnderReviwed.do" code="submission.cancel"/>
</security:authorize>