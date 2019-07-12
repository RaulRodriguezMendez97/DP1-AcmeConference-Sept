<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('CUSTOMER')">

<p><spring:message code="customer.complaint.form.edit.title" /></p>

<form:form action="complaint/customer/edit.do" modelAttribute="complaint">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="ticker" />
	<form:hidden path="moment" />
	


<form:label path="description">
	<spring:message code="customer.complaint.description" />
</form:label>
<form:input path="description" />
<form:errors path="description"/>

<br />

<form:label path="numberAttachments">
	<spring:message code="customer.complaint.numberAttachments" />
</form:label>
<form:input path="numberAttachments" />
<form:errors path="numberAttachments"/>

<br />

<!-- Faltan referee y fixUpTask -->
<form:label path="referee">
	<spring:message code="customer.complaint.referee" />
</form:label>
<form:select id="referees" path="referee">
		<form:option value="0" label="----" />		
		<form:options items="${referees}" itemValue="id"
			itemLabel="name" />
	</form:select>
<form:errors path="referee"/>

<br />

<form:label path="fixUpTask">
	<spring:message code="customer.complaint.fixUpTask" />
</form:label>
<form:select id="fixUpTasks" path="fixUpTask">
		<form:option value="0" label="----" />		
		<form:options items="${fixUpTasks}" itemValue="id"
			itemLabel="ticker" />
	</form:select>
<form:errors path="fixUpTask"/>

<br />
	
<input type="submit" name="save" value="<spring:message code="customer.complaint.save" />" />
<input type="button" name="cancel" value="<spring:message code="customer.complaint.cancel" />"
			onclick="javascript: relativeRedir('complaint/customer/list.do');" />
	

</form:form>


</security:authorize>