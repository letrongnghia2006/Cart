<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS NK
  Date: 11/19/2026
  Time: 7:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel = "stylesheet" href = "style.css" type = "text/css">
</head>
<body>
    <h1>Your Invoice</h1>
    <table>
        <tr>
            <th>Description</th>
            <th>Quantity</th>
            <th>Amount</th>

        <c:forEach var = "LineProduct" items="${sessionScope.cart.cart}" >
            <tr>
                <td>${LineProduct.product.description}</td>
                <td>${LineProduct.product.price}</td>
                <td>${LineProduct.amount}</td>
            </tr>
        </c:forEach>
        <tr>
            <th>Total</th>
            <th></th>
            <th>${sessionScope.cart.getTotal()}</th>
        </tr>
    </table>

</body>
</html>
