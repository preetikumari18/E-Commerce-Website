<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
</style>
</head>
<body>
<center>
<a href="Index.jsp"><img src="images/newlogo.png" alt="" width="400" height="90" /></a>
<form method="POST" action="CartController">
<h2>Shopping Cart</h2>
<table>
<tr>
<th>Name</th> 
<th>Title</th>
<th>Quantity</th>
<th>Modify</th>
<th>Unit Price</th>
<th>Total Price</th>
</tr>
 <c:forEach items="${shoppingList}" var="item">
 <tr>
<td><input type="hidden" name="modelNo" value='<c:out value="${item.getPartNumber()}"/>'>${item.getPartNumber()}</td>
<td>${item.getModelDescription()}</td>
<td>${item.getQuantity()}</td>
<td><input type="button" onclick="CartController" name="${item.getPartNumber()}" value="+" />
<%-- <input type="submit" name="${item.getPartNumber()}" value="+"> --%> <input type="submit" name="${item.getPartNumber()}" value="-"></td>
<td>$ ${item.getUnitCost()}</td>
<td>$ ${item.getTotalCost()}</td>
</tr>
</c:forEach>


<tr>
<th colspan="5">Total Amount: </th>
<td colspan="1">$ ${paymentList[0]}</td>
</tr>
<tr>
<th colspan="5">13% Tax: </th>
<td colspan="1">$ ${paymentList[1]}</td>
</tr>
<tr>
<th colspan="5">Total Payment: </th>
<td colspan="1">$ ${paymentList[2]}</td>
</tr>


<%-- <tr>
<td colspan="5">Total : </td>
<td colspan="1">${item.getTotalCost()}</td>
</tr>
<tr>
<td colspan="5">Tax : </td>
<td colspan="1">${item.getTotalCost()}</td>
</tr>
<tr>
<td colspan="5">Total Payment : </td>
<td colspan="1">${item.getTotalCost()}</td>
</tr> --%>
 </table>
 </form><br>
 <form method="POST" action="CheckoutController"><input type="submit" name="checkout" value="Proceed to Checkout"></form>
 <br>
 <input type="button" onclick="location.href='Index.jsp';" value="Continue Shopping" />
 </center>
</body>
</html>