

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('REFEREE')">



<display:table pagesize="5" name="complaints" id="row"
requestURI="${requestURI}" >


<display:column property="ticker" titleKey="complaint.ticker" />
<display:column property="moment" titleKey="complaint.moment" />
<display:column property="description" titleKey="complaint.description" />
<display:column property="numberAttachments" titleKey="complaint.numberAttachments" />
<display:column property="fixUpTask.id" titleKey="complaint.fixUpTask" />

		<display:column>
					<a href="complaint/referee/self-assign.do?complaintId=${row.id}"
						   onclick="javascript: return confirm('<spring:message code="complaint.confirm.self-assign" />')">
						<spring:message code="complaint.self-assign" />
					</a>					
				
		</display:column>




</display:table>

	<a href="complaint/referee/list2.do">
				<spring:message code="complaints.self-assign" /></a>


</security:authorize>