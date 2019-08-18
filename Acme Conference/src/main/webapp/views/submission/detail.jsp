<%--
 * action-1.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('AUTHOR')">
<b><spring:message code="submission.ticker" /> : </b> <jstl:out value="${submission.ticker}"></jstl:out> <br/>
<b><spring:message code="submission.moment" /> : </b> <jstl:out value="${submission.moment}"></jstl:out> <br/>

<jstl:if test="${submission.status eq 0 }">
	<b><spring:message code="submission.status" /> : </b><spring:message code="submission.status.0" /><br/>
</jstl:if>
<jstl:if test="${submission.status eq 1 }">
	<b><spring:message code="submission.status" /> : </b><spring:message code="submission.status.1" /><br/>
</jstl:if>
<jstl:if test="${submission.status eq 2 }">
	<b><spring:message code="submission.status" /> : </b><spring:message code="submission.status.2" /><br/>
</jstl:if>

<h3><spring:message code="submission.conference" /></h3>
<b><spring:message code="submission.conference.title" /> : </b> <jstl:out value="${conference.title}"></jstl:out> <br/>
<b><spring:message code="submission.conference.acronym" /> : </b> <jstl:out value="${conference.acronym}"></jstl:out> <br/>
<b><spring:message code="submission.conference.venue" /> : </b> <jstl:out value="${conference.venue}"></jstl:out> <br/>
<b><spring:message code="submission.conference.submissionDeadline" /> : </b> <jstl:out value="${conference.submissionDeadline}"></jstl:out> <br/>
<b><spring:message code="submission.conference.notificacionDeadline" /> : </b> <jstl:out value="${conference.notificacionDeadline}"></jstl:out> <br/>
<b><spring:message code="submission.conference.cameraDeadline" /> : </b> <jstl:out value="${conference.cameraDeadline}"></jstl:out> <br/>
<b><spring:message code="submission.conference.startDate" /> : </b> <jstl:out value="${conference.startDate}"></jstl:out> <br/>
<b><spring:message code="submission.conference.endDate" /> : </b> <jstl:out value="${conference.endDate}"></jstl:out> <br/>
<b><spring:message code="submission.conference.summary" /> : </b> <jstl:out value="${conference.summary}"></jstl:out> <br/>
<b><spring:message code="submission.conference.fee" /> : </b> <jstl:out value="${conference.fee}"></jstl:out> <br/>

<jstl:if test="${conference.finalMode eq 0 }">
	<b><spring:message code="submission.conference.finalMode" /> : </b><spring:message code="submission.conference.finalMode.0" /><br/>
</jstl:if>
<jstl:if test="${conference.finalMode eq 1 }">
	<b><spring:message code="submission.conference.finalMode" /> : </b><spring:message code="submission.conference.finalMode.1" /><br/>
</jstl:if>


<h3><spring:message code="submission.reviwed" /></h3>
<b><spring:message code="submission.reviwed.title" /> : </b> <jstl:out value="${reviwed.title}"></jstl:out> <br/>
<b><spring:message code="submission.reviwed.summary" /> : </b> <jstl:out value="${reviwed.summary}"></jstl:out> <br/>
<b><spring:message code="submission.reviwed.urlDocument" /> : </b> <jstl:out value="${reviwed.urlDocument}"></jstl:out> <br/>
<b><spring:message code="submission.reviwed.author" /> : </b> <jstl:out value="${reviwed.author.email}"></jstl:out> <br/>
<b><spring:message code="submission.reviwed.coAuthors" /></b>
	
	<jstl:if test="${empty reviwed.coAuthors}">
		<b>: </b><spring:message code="submission.coAuthors.null" /><br/>
	</jstl:if>
	<jstl:if test="${reviwed.coAuthors ne null }">
		<ul>
			<jstl:forEach var="coAutor" items="${reviwed.coAuthors}">
				<li><b><spring:message code="submission.reviwed.author" /> :</b> <jstl:out value="${coAutor.name}, ${coAutor.email}"></jstl:out> </li>
			</jstl:forEach>
		</ul>
	</jstl:if>
	 

<h3><spring:message code="submission.camaraReady" /></h3>
<jstl:if test="${submission.camaraReady eq null}">
	<spring:message code="submission.camaraReady.null" /><br/>
</jstl:if>
<jstl:if test="${submission.camaraReady ne null }">
	<b><spring:message code="submission.camaraReady.title" /> : </b> <jstl:out value="${submission.camaraReady.title}"></jstl:out> <br/>
	<b><spring:message code="submission.camaraReady.summary" /> : </b> <jstl:out value="${submission.camaraReady.summary}"></jstl:out> <br/>
	<b><spring:message code="submission.camaraReady.urlDocument" /> : </b> <jstl:out value="${submission.camaraReady.urlDocument}"></jstl:out> <br/>
</jstl:if>

<h3><spring:message code="submission.reviwers" /></h3>
<jstl:if test="${empty submission.reviwers}">
	<spring:message code="submission.reviwers.null" /><br/>
</jstl:if>

<jstl:if test="${submission.reviwers ne null }">
	<ul>
		<jstl:forEach var="reviwer" items="${submission.reviwers}">
			<li><b><spring:message code="submission.reviwers.reviwer" /> : </b> <jstl:out value="${reviwer.name}, ${reviwer.email}"></jstl:out></li>
		</jstl:forEach>
	</ul>
</jstl:if>



<acme:cancel url="submission/author/list.do" code="submission.cancel"/>
</security:authorize>








<security:authorize access="hasRole('ADMIN')">
<b><spring:message code="submission.ticker" /> : </b> <jstl:out value="${submission.ticker}"></jstl:out> <br/>
<b><spring:message code="submission.moment" /> : </b> <jstl:out value="${submission.moment}"></jstl:out> <br/>

<jstl:if test="${submission.status eq 0 }">
	<b><spring:message code="submission.status" /> : </b><spring:message code="submission.status.0" /><br/>
</jstl:if>
<jstl:if test="${submission.status eq 1 }">
	<b><spring:message code="submission.status" /> : </b><spring:message code="submission.status.1" /><br/>
</jstl:if>
<jstl:if test="${submission.status eq 2 }">
	<b><spring:message code="submission.status" /> : </b><spring:message code="submission.status.2" /><br/>
</jstl:if>

<h3><spring:message code="submission.conference" /></h3>
<b><spring:message code="submission.conference.title" /> : </b> <jstl:out value="${conference.title}"></jstl:out> <br/>
<b><spring:message code="submission.conference.acronym" /> : </b> <jstl:out value="${conference.acronym}"></jstl:out> <br/>
<b><spring:message code="submission.conference.venue" /> : </b> <jstl:out value="${conference.venue}"></jstl:out> <br/>
<b><spring:message code="submission.conference.submissionDeadline" /> : </b> <jstl:out value="${conference.submissionDeadline}"></jstl:out> <br/>
<b><spring:message code="submission.conference.notificacionDeadline" /> : </b> <jstl:out value="${conference.notificacionDeadline}"></jstl:out> <br/>
<b><spring:message code="submission.conference.cameraDeadline" /> : </b> <jstl:out value="${conference.cameraDeadline}"></jstl:out> <br/>
<b><spring:message code="submission.conference.startDate" /> : </b> <jstl:out value="${conference.startDate}"></jstl:out> <br/>
<b><spring:message code="submission.conference.endDate" /> : </b> <jstl:out value="${conference.endDate}"></jstl:out> <br/>
<b><spring:message code="submission.conference.summary" /> : </b> <jstl:out value="${conference.summary}"></jstl:out> <br/>
<b><spring:message code="submission.conference.fee" /> : </b> <jstl:out value="${conference.fee}"></jstl:out> <br/>

<jstl:if test="${conference.finalMode eq 0 }">
	<b><spring:message code="submission.conference.finalMode" /> : </b><spring:message code="submission.conference.finalMode.0" /><br/>
</jstl:if>
<jstl:if test="${conference.finalMode eq 1 }">
	<b><spring:message code="submission.conference.finalMode" /> : </b><spring:message code="submission.conference.finalMode.1" /><br/>
</jstl:if>


<h3><spring:message code="submission.reviwed" /></h3>
<b><spring:message code="submission.reviwed.title" /> : </b> <jstl:out value="${reviwed.title}"></jstl:out> <br/>
<b><spring:message code="submission.reviwed.summary" /> : </b> <jstl:out value="${reviwed.summary}"></jstl:out> <br/>
<b><spring:message code="submission.reviwed.urlDocument" /> : </b> <jstl:out value="${reviwed.urlDocument}"></jstl:out> <br/>
<b><spring:message code="submission.reviwed.author" /> : </b> <jstl:out value="${reviwed.author.email}"></jstl:out> <br/>
<b><spring:message code="submission.reviwed.coAuthors" /></b>
	
	<jstl:if test="${empty reviwed.coAuthors}">
		<b>: </b><spring:message code="submission.coAuthors.null" /><br/>
	</jstl:if>
	<jstl:if test="${reviwed.coAuthors ne null }">
		<ul>
			<jstl:forEach var="coAutor" items="${reviwed.coAuthors}">
				<li><b><spring:message code="submission.reviwed.author" /> :</b> <jstl:out value="${coAutor.name}, ${coAutor.email}"></jstl:out> </li>
			</jstl:forEach>
		</ul>
	</jstl:if>
	 

<h3><spring:message code="submission.camaraReady" /></h3>
<jstl:if test="${submission.camaraReady eq null}">
	<spring:message code="submission.camaraReady.null" /><br/>
</jstl:if>
<jstl:if test="${submission.camaraReady ne null }">
	<b><spring:message code="submission.camaraReady.title" /> : </b> <jstl:out value="${submission.camaraReady.title}"></jstl:out> <br/>
	<b><spring:message code="submission.camaraReady.summary" /> : </b> <jstl:out value="${submission.camaraReady.summary}"></jstl:out> <br/>
	<b><spring:message code="submission.camaraReady.urlDocument" /> : </b> <jstl:out value="${submission.camaraReady.urlDocument}"></jstl:out> <br/>
</jstl:if>

<h3><spring:message code="submission.reviwers" /></h3>
<jstl:if test="${empty submission.reviwers}">
	<spring:message code="submission.reviwers.null" /><br/>
</jstl:if>

<jstl:if test="${submission.reviwers ne null }">
	<ul>
		<jstl:forEach var="reviwer" items="${submission.reviwers}">
			<li><b><spring:message code="submission.reviwers.reviwer" /> : </b> <jstl:out value="${reviwer.name}, ${reviwer.email}"></jstl:out></li>
		</jstl:forEach>
	</ul>
</jstl:if>



<acme:cancel url="${uri}" code="submission.cancel"/>
</security:authorize>