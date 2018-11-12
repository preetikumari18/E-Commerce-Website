<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<form autocomplete="off" method="POST" action="PaymentController">
<h2>Please Enter Credit Card Details</h2>
<table>
<tr>
<th>Enter Card Number : </th>
<td><input type="text" name="cardnumber" size="30" required/></td> 
</tr>
<tr>
<th>Enter Full Name : </th>
<td><input type="text" name="name" size="30" required/></td> 
</tr>
<tr>
<th>Expiry Date : </th>
<td><input type="text" name="expiry" placeholder="MM/YY"  size="30" required/></td> 
</tr>
<tr>
<th>CVV : </th>
<td><input type="password" name="password" size="30" required/></td> 
</tr>
</table>
<br>
<input type="submit" value="PAY"/>
 </form>
 </center>
</body>
</html>