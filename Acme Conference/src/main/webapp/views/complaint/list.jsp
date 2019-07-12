<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('CUSTOMER')">
<p><spring:message code="customer.complaint.title" /></p>

<display:table pagesize="5" name="complaints" id="row"
requestURI="${requestURI}" >


<display:column property="ticker" titleKey="complaint.ticker" />
<display:column property="moment" titleKey="complaint.moment" />
<display:column property="description" titleKey="complaint.description" />
<display:column property="numberAttachments" titleKey="complaint.numberAttachments" />
<display:column property="referee.id" titleKey="complaint.referee" />
<display:column property="fixUpTask.id" titleKey="complaint.fixUpTask" />



</display:table>
<form action="complaint/customer/create.do">
  	 	<input type="submit" value="<spring:message code="customer.complaint.create.link" />" />
	</form>

</security:authorize>
