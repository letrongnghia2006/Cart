<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="style.css" type="text/css"/>
</head>

<body>
<h1>Cookies</h1>
<p>Here's a table with all of the cookies that this browser is sending to the current server.</p>

<table>
    <tr>
        <th>Name</th>
        <th>Value</th>
    </tr>
    <c:forEach var="c" items="${cookie}">
        <tr>
            <td>${c.value.name}</td>
            <td>${c.value.value}</td>
        </tr>
    </c:forEach>
</table>

<p><a href="download?action=join">View list of albums</a></p>
<p><a href="download?action=deleteCookies">Delete all persistent cookies</a></p>
</body>
</html>

