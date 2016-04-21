<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title><spring:message code="form.countrylist.name"/></title>
</head>
<body>

<h3><spring:message code="form.table.countrylist.title"/></h3>
<table border="1">
    <tr>
        <th><spring:message code="form.table.countrylist.col.id"/></th>
        <th><spring:message code="form.table.countrylist.col.firstname"/></th>
        <th><spring:message code="form.table.countrylist.col.lastname"/></th>
    </tr>
    <c:forEach var="x" items="${countryList}" >
        <tr>
            <td>${x.id}</td>
            <td>${x.name}</td>
            <td>${x.codeName}</td>
        </tr>
    </c:forEach>
</table>

<hr/>

<a href="${pageContext.request.contextPath}/addcountry.form"><spring:message code="form.addCountry.name"/></a>

</body>
</html>
