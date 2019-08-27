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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<security:authorize access="hasRole('ADMIN')">

<form:form action="presentation/administrator/edit.do" modelAttribute="presentation">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<acme:textbox code="tutorial.title" path="title"/>
	<acme:textbox code="tutorial.speaker" path="speaker"/>
	<acme:textbox code="tutorial.duration" path="duration"/>
	<acme:textbox code="tutorial.schedule" path="schedule"/>
	<acme:textbox code="tutorial.room" path="room"/>
	<acme:textbox code="tutorial.summary" path="summary"/>
	<spring:message code="tutorial.attachmets.comment" />
	<acme:textbox code="tutorial.attachments" path="attachments"/>
	<acme:select id="select-prueba" items="${conferences}" itemLabel="title" code="tutorial.conference" path="conference"/>
	<form:label path="camaraReady">
		<spring:message code="presentation.cameraReady" />
	</form:label>
	<form:select id="rellenarme" path="camaraReady">
	<form:select id="rellenarme" path="camaraReady" value="-1">
	</form:select>
	</form:select>
	<form:errors path="camaraReady" cssClass="error" />

	<br/>
	<input type="submit" name="save" 
	value="<spring:message code="tutorial.save" />" />
	
	<input type="submit" name="delete" 
	value="<spring:message code="tutorial.delete" />" />

	<input type="button" name="cancel" value="<spring:message code="tutorial.cancel" />"
			onclick="javascript: relativeRedir('presentation/administrator/list.do');" />
</form:form>

<script type="text/javascript">
	$(document).ready(function(){
		$('#select-prueba').change(function(){
			var conferenceId = $('#select-prueba option:selected').attr('value');
			if(conferenceId === ""){
				conferenceId='-1';
			}
			$.ajax({
				type:'GET',
				url:'presentation/administrator/camaraReadyList.do?conferenceId='+conferenceId,
				success: function(res) {
					var cameras = res.split(';');
					var i;
					var injectar = "";
					injectar += '<option value="-1"> --- </option>';
					for (i = 0; i < cameras.length; i++) { 
						p = cameras[i].split(':');
						injectar += '<option value="'+p[1]+'">'+p[0]+'</option>';
					}
					document.getElementById("rellenarme").innerHTML =injectar;
			    }
			});
		});
		document.getElementById("rellenarme").innerHTML ='<option value="-1"> --- </option>';
	});
</script>
<jstl:if test="${presentation.id ne 0 || error eq 1 }">
	<script type="text/javascript">
		$(document).ready(function(){
			var conferenceId = $('#select-prueba option:selected').attr('value');
			$.ajax({
				type:'GET',
				url:'presentation/administrator/camaraReadyList.do?conferenceId='+conferenceId,
				success: function(res) {
					var cameras = res.split(';');
					var i;
					var injectar = "";
					injectar += '<option value="-1"> --- </option>';
					for (i = 0; i < cameras.length; i++) { 
						p = cameras[i].split(':');
						injectar += '<option value="'+p[1]+'">'+p[0]+'</option>';
					}
					document.getElementById("rellenarme").innerHTML =injectar;
			    }
			});
		});
	</script>
</jstl:if>
</security:authorize>