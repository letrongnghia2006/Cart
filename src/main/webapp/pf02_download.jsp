<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="style.css" type="text/css"/>
</head>

<body>
<h1>Downloads</h1>
<h2>${product.description}</h2>

<table>
    <tr>
        <th>Song title</th>
        <th>Audio Format</th>
    </tr>
    <tr>
        <td>Neon Lights</td>
        <td><a href="/musicStore/sound/${product.productCode}/neon.mp3">MP3</a></td>
    </tr>
    <tr>
        <td>Tank Hill</td>
        <td><a href="/musicStore/sound/${product.productCode}/tank.mp3">MP3</a></td>
    </tr>
</table>

<p><a href="download?action=viewCookies">View all cookies</a></p>
<p><a href="download?action=join">View list of albums</a></p>
</body>
</html>
