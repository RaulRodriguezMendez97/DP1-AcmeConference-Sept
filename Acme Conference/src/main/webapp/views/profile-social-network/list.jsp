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

<security:authorize access="isAuthenticated()">

<display:table pagesize="5" name="profiles" id="row"
requestURI="profileSocial/actor/list.do" >

<display:column property="nickName" titleKey="profile.nickname"  />
<display:column property="nameSocialNetwork" titleKey="profile.nameSocial"  />
<display:column titleKey="profile.link"  >
<a href="${row.linkProfile}">${row.nameSocialNetwork}</a>
</display:column>
<display:column>
		<a href="profileSocial/actor/edit.do?idProfile=${row.id}"><spring:message code="profile.show" /></a>
</display:column>

</display:table>

<a href="profileSocial/actor/create.do"><spring:message code="profile.create" /></a>
<a href="profile/personal-datas.do"><spring:message code="profile.cancel" /></a>

</security:authorize>