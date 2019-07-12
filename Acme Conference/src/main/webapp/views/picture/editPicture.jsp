<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('HANDYWORKER')">

<p><spring:message code="picture.edit.title" /></p>

<form:form action="picture/handyWorker/editPicture.do" modelAttribute="picture">
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	
<form:label path="urlPicture">
	<spring:message code="picture.picture.form" />
</form:label>
<form:input path="urlPicture" />
<form:errors path="urlPicture"/>

<br />

<form:label path="tutorial">
	<spring:message code="picture.tutorial.form" />:
	</form:label>
	<form:select id="tutorial" path="tutorial">
		<form:options items="${tutorials}" itemValue="id" itemLabel="title" />
	</form:select>
	<form:errors cssClass="error" path="tutorial" />
	<br />
	
<input type="submit" name="save" value="<spring:message code="picture.save" />" />
<jstl:if test="${picture.id ne 0 }">
	<input type="submit" name="delete" value="<spring:message code="picture.delete" />"/>
</jstl:if>
<jstl:if test="${picture.id eq 0 }">
<input type="button" name="cancel" value="<spring:message code="picture.cancel" />"
			onclick="javascript: relativeRedir('tutorial/handyWorker/tutorials.do');" />
</jstl:if>
<jstl:if test="${picture.id ne 0 }">
<input type="button" name="cancel" value="<spring:message code="picture.cancel" />"
			onclick="javascript: relativeRedir('picture/handyWorker/showPicture.do');" />
</jstl:if>

	

</form:form>


</security:authorize>