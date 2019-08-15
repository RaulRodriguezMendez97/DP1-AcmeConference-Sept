 <%--
 * header.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<a href="#"><img src="${urlBanner }" alt="Acme Conference Co., Inc." width="500px" height="200px" /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a href="customizableSystem/administrator/edit.do"><spring:message code="master.page.customizable" /></a></li>
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/create.do"><spring:message code="master.page.administrator.action.1" /></a></li>
					<li><a href="administrator/dashboard.do"><spring:message code="master.page.administrator.dashboard" /></a></li>
					<li><a href="topic/administrator/list.do"><spring:message code="master.page.administrator.topics" /></a></li>
					<li><a href="message-broadcast/administrator/send.do"><spring:message code="master.page.administrator.broadcast" /></a></li>
	
				</ul>
			</li>
			<li><a href="conference/administrator/list.do"><spring:message code="master.page.administrator.conferences" /></a></li>
			<li><a class="fNiv"><spring:message	code="master.page.administrator.conference.list" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="conference/administrator/submission-last-5.do"><spring:message code="master.page.administrator.conference.submission.less.5" /></a></li>
					<li><a href="conference/administrator/notification-less-5.do"><spring:message code="master.page.administrator.conference.notification.less.5" /></a></li>
					<li><a href="conference/administrator/notification-less-5.do"><spring:message code="master.page.administrator.conference.camera.less.5" /></a></li>
					<li><a href="conference/administrator/notification-less-5.do"><spring:message code="master.page.administrator.conference.start.less.5" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.submission" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="submission/administrator/submissionsUnderReviwed.do"><spring:message code="master.page.administrator.submission.underReviwed" /></a></li>
					<li><a href="submission/administrator/submissionsRejected.do"><spring:message code="master.page.administrator.submission.rejected" /></a></li>
					<li><a href="submission/administrator/submissionsAccepted.do"><spring:message code="master.page.administrator.submission.accepted" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.administrador.activity" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="tutorial/administrator/list.do"><spring:message code="master.page.administrator.tutorial" /></a></li>
					<li><a href="panel/administrator/list.do"><spring:message code="master.page.administrator.panel" /></a></li>
					<li><a href="presentation/administrator/list.do"><spring:message code="master.page.administrator.presentation" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('AUTHOR')">
			<li><a href="submission/author/list.do"><spring:message code="master.page.author.submissions" /></a></li>
			<li><a href=""><spring:message	code="master.page.author.registrations" /></a>
		</security:authorize>
		
		<security:authorize access="hasRole('REVIWER')">
			<li><a href="submission/reviwer/list.do"><spring:message code="master.page.reviwer.submission" /></a></li>				
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv"><spring:message	code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="author/create.do"><spring:message code="master.page.author.register" /></a></li>
					<li><a href="reviwer/create.do"><spring:message code="master.page.reviwer.register" /></a></li>
				</ul>
			</li>			
			<li><a class="fNiv"><spring:message	code="master.page.conference" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="conference/list.do"><spring:message code="master.page.conference.all" /></a></li>
					<li><a href="conference/incoming.do"><spring:message code="master.page.conference.incoming" /></a></li>
					<li><a href="conference/running.do"><spring:message code="master.page.conference.running" /></a></li>
					<li><a href="conference/past.do"><spring:message code="master.page.conference.past" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="message/actor/list.do"><spring:message code="master.page.profile.message" /></a></li>
					<li><a href="profile/personal-datas.do"><spring:message code="master.page.profile.personalData" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

