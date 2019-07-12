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

<security:authorize access="hasRole('CUSTOMER')">
<form:form action="fix-up-task/customer/save.do" modelAttribute="fixUpTask">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="ticker" />	
	<form:hidden path="customer" />	
 	<form:hidden path="moment" />
 	
	<form:label path="description">
		<spring:message code="fixUpTask.description" />:
	</form:label>
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br />
	
	<form:label path="address">
		<spring:message code="fixUpTask.address" />:
	</form:label>
	<form:input path="address" />
	<form:errors cssClass="error" path="address" />
	<br />
	
	<form:label path="maximunPrice">
		<spring:message code="fixUpTask.maximunPrice" />:
	</form:label>
	<form:input path="maximunPrice" />
	<form:errors cssClass="error" path="maximunPrice" />
	<br />
	
	<form:label path="periodTime">
		<spring:message code="fixUpTask.periodTime" />:
	</form:label>
	<form:input path="periodTime" />
	<form:errors cssClass="error" path="periodTime" />
	<br />
	
	<form:label path="category">
		<spring:message code="fixUpTask.category" />:
	</form:label>
	<form:select path="category">
		<form:options items="${categories}" itemValue="id" itemLabel="name" />
	</form:select>
	<form:errors cssClass="error" path="category" />
	<br />
	
	<form:label path="warranty">
		<spring:message code="fixUpTask.warranty" />:
	</form:label>
	<form:select path="warranty">	
		<form:options items="${warranties}" itemValue="id" itemLabel="title" />
	</form:select>
	<form:errors cssClass="error" path="warranty" />
	<br> <br>
	
	<input type="submit" name="save" value="<spring:message code="fixUpTask.save" />" />
		
	<input type="button" name="cancel" 
	value="<spring:message code="fixUpTask.cancel" />" 
	onclick="javascript:relativeRedir('fix-up-task/customer/list.do');"/>	
		 	
</form:form>
</security:authorize>
</body>
</html>