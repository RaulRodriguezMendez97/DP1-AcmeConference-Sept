<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('HANDYWORKER')">

<p><spring:message code="curricula.create.title" /></p>


<form:form action="curricula/handyWorker/createCurricula.do" modelAttribute="curricula">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="educationsRecords" />
	<form:hidden path="professionalsRecords" />
	<form:hidden path="endosersRecords" />
	<form:hidden path="personalRecord" />
	<form:hidden path="miscellaneousRecords" />
	
	

<form:label path="educationsRecords">
	<spring:message code="curricula.educationsRecords" />
</form:label>
<form:select id="educationsRecords" path="educationsRecords">
	<form:options items="${educationsRecords}" itemValue="id" itemLabel="titleDiploma" />
</form:select>
<form:errors path="educationsRecords"/>

<br />

<form:label path="professionalsRecords">
	<spring:message code="curricula.professionalsRecords" />
</form:label>
<form:select id="professionalsRecords" path="professionalsRecords">
		<form:options items="${professionalsRecords}" itemValue="id" itemLabel="id" />
	</form:select>
<form:errors path="professionalsRecords"/>

<br />

<form:label path="miscellaneousRecords">
	<spring:message code="curricula.miscellaneousRecords" />
</form:label>
<form:select id="miscellaneousRecords" path="miscellaneousRecords">
		<form:options items="${miscellaneousRecords}" itemValue="id" itemLabel="title" />
</form:select>
<form:errors path="miscellaneousRecords"/>

<br />

<form:label path="endosersRecords">
	<spring:message code="curricula.endosersRecords" />
</form:label>
<form:select id="endosersRecords" path="endosersRecords">
		<form:options items="${endosersRecords}" itemValue="id" itemLabel="name" />
</form:select>
<form:errors path="endosersRecords"/>

<br />

<form:label path="personalRecord">
	<spring:message code="curricula.personalRecord" />
</form:label>
<form:select id="personalRecord" path="personalRecord">
		<form:option value="0" label="----" />	
		<form:options items="${personalRecord}" itemValue="id" itemLabel="id" />
</form:select>
<form:errors path="personalRecord"/>

<br />
	
<input type="submit" name="save" value="<spring:message code="curricula.save" />" />
<input type="button" name="cancel" value="<spring:message code="curricula.cancel" />"
			onclick="javascript: relativeRedir('curricula/handyWorker/curriculas.do');" />
	

</form:form>


</security:authorize>