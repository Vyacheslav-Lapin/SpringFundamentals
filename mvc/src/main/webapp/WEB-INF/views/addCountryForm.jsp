<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Country :: AddForm</title>
</head>
<body>

<h3><spring:message code="form.addCountry.name"/></h3>
<form:form commandName="countryFormBean">
    <table>
        <tr><td>Country Name:</td><td><form:input path="name"/></td></tr>
        <tr><td colspan="2" style="color: red; font-size: small;"><form:errors path="name"/></td></tr>
        <tr><td><spring:message code="code"/></td><td><form:input path="code"/></td></tr>
        <tr><td colspan="2"><input type="submit" value="Save Changes"/></td></tr>
    </table>
</form:form>

</body>
</html>
