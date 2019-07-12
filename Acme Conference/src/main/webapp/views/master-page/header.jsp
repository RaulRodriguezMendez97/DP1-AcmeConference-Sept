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
	<a href="#"><img src="images/logo.png" alt="Acme Conference Co., Inc." width="500px" height="300px" /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/create.do"><spring:message code="master.page.administrator.action.1" /></a></li>
					<li><a href="referee/administrator/create.do"><spring:message code="master.page.administrator.referee" /></a></li>
					<li><a href="administrator/dashboard.do"><spring:message code="master.page.administrator.dashboard" /></a></li>
					<li><a href="category/administrator/list.do"><spring:message code="master.page.administrator.category" /></a></li>
					<li><a href="warranty/administrator/list.do"><spring:message code="master.page.administrator.warranty" /></a></li>						
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.administrator.creditCard" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="credit-card-type/administrator/list.do"><spring:message code="master.page.administrator.creditCardType" /></a></li>						
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.administrator.spam" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="spam-word/administrator/list.do"><spring:message code="master.page.administrator.spamWord" /></a></li>
					<li><a href="word/administrator/list.do"><spring:message code="master.page.administrator.word" /></a></li>						
											
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.administrator.suspicious" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="suspiciousActor/administrator/list.do"><spring:message code="master.page.administrator.suspicious" /></a></li>	
				</ul>
			</li>	
		</security:authorize>
		
		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv"><spring:message	code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="customer/action-1.do"><spring:message code="master.page.customer.action.1" /></a></li>
					<li><a href="customer/action-2.do"><spring:message code="master.page.customer.action.2" /></a></li>					
				</ul>
			</li>
			<li><a href="complaint/customer/list.do"><spring:message code="master.page.customer.complaints" /></a></li>
			<li><a href="fix-up-task/customer/list.do"><spring:message	code="master.page.customer.fixUpTask" /></a>
			<li><a href="endorsement/customer/list.do"><spring:message	code="master.page.customer.endorsement" /></a>
			<li><a href="application/handyWorker,customer/applications.do"><spring:message code="master.page.handyworker.applications"/></a></li>
			<li><a href="creditCard/customer,sponsor/list.do"><spring:message code="master.page.customer.creditCard"/></a></li>
		</security:authorize>
		
			<security:authorize access="hasRole('REFEREE')">
			<li><a class="fNiv"><spring:message	code="master.page.referee" /></a>
				
					<li class="arrow"></li>
					<li><a href="complaint/referee/list.do"><spring:message code="master.page.referee.complaint" /></a></li>
				
		</security:authorize>
		
		
		<security:authorize access="hasRole('HANDYWORKER')">
			<li><a class="fNiv"><spring:message	code="master.page.handyWorker" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="finder/handy-worker/show.do"><spring:message code="master.page.handyworker.show" /></a></li>
					<li><a href="tutorial/handyWorker/tutorials.do"><spring:message code="master.page.handyworker.tutorial" /></a></li>
					<li><a href="fixUptask/handyWorker/list.do"><spring:message code="master.page.handyworker.fixUptask" /></a></li>
				</ul>
			</li>
			<li><a href="endorsement/handy-worker/list.do"><spring:message	code="master.page.customer.endorsement" /></a>
			<li><a href="curricula/handyWorker/curriculas.do"><spring:message	code="master.page.handyWorker.curricula" /></a>
			<li><a href="fix-up-task/handy-worker/list.do"><spring:message	code="master.page.handyworker.fixUpTask" /></a>
			<li><a href="application/handyWorker,customer/applications.do"><spring:message code="master.page.handyworker.applications"/></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('SPONSOR')">
			<li><a class="fNiv"><spring:message	code="master.page.sponsor" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="sponsorship/sponsor/list.do"><spring:message code="master.page.sponsor.sponsorships" /></a></li>
				</ul>
			</li>
			<li><a href="creditCard/customer,sponsor/list.do"><spring:message code="master.page.customer.creditCard"/></a></li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv"><spring:message	code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="sponsor/create.do"><spring:message code="master.page.sponsor.register" /></a></li>
					<li><a href="handy-worker/create.do"><spring:message code="master.page.handy.register" /></a></li>
					<li><a href="customer/create.do"><spring:message code="master.page.customer.register" /></a></li>
				</ul>
			</li>			<li><a href="tutorial/AllTutorials.do"><spring:message code="master.page.tutorials" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="messageBox/actor/list.do"><spring:message code="master.page.profile.messageBox" /></a></li>
					<li><a href="profile/personal-datas.do"><spring:message code="master.page.profile.personal.datas" /></a></li>
					<li><a href="message/actor/send.do"><spring:message code="master.page.profile.action.3" /></a></li>					
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

