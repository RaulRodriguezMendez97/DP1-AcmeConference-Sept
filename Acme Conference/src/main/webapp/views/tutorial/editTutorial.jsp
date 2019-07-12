<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


</head>
<body>

<security:authorize access="hasRole('HANDYWORKER')">
<form:form action="tutorial/handyWorker/editTutorial.do" modelAttribute="tutorial">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="sponsorship" />
	<form:hidden path="handyWorker" />
	<form:hidden path="moment" />
	<form:hidden path="section" />
	

 
 	<form:label path="title">
		<spring:message code="tutorial.title" />:
	</form:label>
	<form:input path="title" />
	<form:errors cssClass="error" path="title" />
	<br />
	
	<form:label path="summary">
		<spring:message code="tutorial.summary" />:
	</form:label>
	<form:input path="summary" />
	<form:errors cssClass="error" path="summary" />
	<br />
		
	<input type="submit" name="save" value="<spring:message code="tutorial.save" />" />
	<jstl:if test="${tutorial.id ne 0 }">
		<input type="submit" name="delete" value="<spring:message code="tutorial.delete" />"/>
	</jstl:if>
	
	<input type="button" name="cancel" value="<spring:message code="tutorial.cancel" />"
		onclick="javascript: relativeRedir('tutorial/handyWorker/tutorials.do');" />
	<br />
</form:form>
</security:authorize>
</body>
</html>