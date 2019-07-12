

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table pagesize="5" name="creditCards" id="row"
requestURI="creditCard/customer,sponsor/list.do" >



<display:column property="holderName" titleKey="creditCard.holderName"/>
<display:column property="brandName" titleKey="creditCard.brandName" />
<display:column property="number" titleKey="creditCard.number" />
<display:column property="expirationMonth" titleKey="creditCard.expirationMonth" />
<display:column property="expirationYear" titleKey="creditCard.expirationYear" />
<display:column property="CW" titleKey="creditCard.CW" />

</display:table>
<form action="creditCard/customer,sponsor/edit.do">
    <input type="submit" value="<spring:message code="creditCard.create" />" />
</form>






