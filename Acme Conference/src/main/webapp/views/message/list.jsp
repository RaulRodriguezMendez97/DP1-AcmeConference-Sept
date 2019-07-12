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


<security:authorize access="isAuthenticated()">

<display:table pagesize="5" name="messages" id="row"
requestURI="${Uri }" >
<display:column property="moment" titleKey ="message.moment"  format="{0,date,dd/MM/yyyy}"  />
<display:column property="subject" titleKey="message.subject"  />
<display:column property="tag" titleKey="message.tag"  />
<display:column property="emailReceiver" titleKey="message.emailReceiver"  />
<display:column> <a href="message/actor/show.do?messageId=${row.id}"><spring:message code="message.show" /></a> </display:column>
<display:column> <a href="message/actor/delete.do?idMessage=${row.id}"><spring:message code="message.delete" /></a> </display:column>

</display:table>
 <a href="messageBox/actor/list.do"><spring:message code="message.send.cancel" /></a>
</security:authorize>