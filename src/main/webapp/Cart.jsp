<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS NK
  Date: 11/19/2026
  Time: 4:47 PM
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
    <h1>Your Cart</h1>
    <table>
        <tr>
            <th>Quantity</th>
            <th>Description</th>
            <th>Price</th>
            <th>Amount</th>
            <th></th>
        </tr>
        <c:forEach var = "LineProduct" items="${sessionScope.cart.cart}" >
            <tr>
                <td>
                <form action = "update" method = "post">
                    <input type = "hidden" name = "action" value = "update" >
                    <input type = "hidden" name = "productCode" value = "${LineProduct.product.productCode}" >
                    <input type = "text" name = "quantity" value = "${LineProduct.quantity}" required style="width: 25px">
                    <input type = "submit" name = update value = "update" >
                </form>
                </td>

                <td>${LineProduct.product.description}</td>
                <td>${LineProduct.product.price}</td>
                <td>${LineProduct.amount}</td>
                <td>
                    <form action = "remove" method = "post">
                        <input type = "hidden" name = "action" value = "remove" >
                        <input type = "hidden" name = "productCode" value = "${LineProduct.product.productCode}">
                        <input type = "submit" name = "remove" value="remove" >
                    </form>
                </td>
            </tr>
        </c:forEach>

    </table>

    <p><b>To change the quantity </b>, enter the new quantity and click on the Update button</p>
    <form action = "" method="post">
        <input type="hidden" name="action" value="return">
        <input type="submit" name="continueShopping" value="Continue Shopping">
    </form>
    <form action ="checkOut" method="post">
        <input type="hidden" name="action" value="checkOut">
        <input type="submit" name="checkOut" value="checkOut">
    </form>

</body>
</html>
