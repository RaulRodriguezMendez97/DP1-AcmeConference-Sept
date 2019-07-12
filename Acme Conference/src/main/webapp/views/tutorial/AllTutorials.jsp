<%--
 * action-2.jsp
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


<p><spring:message code="all.tutorial" /></p>
<display:table pagesize="5" name="tutorials" id="row"
requestURI="tutorial/AllTutorials.do" >

<display:column property="title" titleKey="tutorial.title" />
<display:column property="moment" titleKey="tutorial.moment" />
<display:column property="summary" titleKey="tutorial.summary" />
<display:column property="handyWorker.id" titleKey="tutorial.handyWorker" />
<display:column>
	<a href="tutorial/showTutorialSectionHW.do?tutorialId=${row.id}"><spring:message code="tutorial.sections" /></a>
</display:column>
<display:column>
	<a href="tutorial/showTutorialSponsorshipHW.do?tutorialId=${row.id}"><spring:message code="tutorial.sponsorship" /></a>
</display:column>
<display:column>
	<a href="tutorial/showTutorialHW.do?handyWorkerId=${row.handyWorker.id}"><spring:message code="tutorial.seeHandyWorker" /></a>
</display:column>

</display:table>




