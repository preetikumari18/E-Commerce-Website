<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<center>
<a href="Index.jsp"><img src="images/newlogo.png" alt="" width="350" height="90" /></a>
<form action="CheckoutLoginController" method="post" style="width:400px;height:270px;border:1px solid #ccc">
<c:out value="${LoginDenied}"/>
<table cellpadding="3pt" >
<tr><td><h1>Sign in</h1></td></tr>
<tr>
<td>E-mail address :</td>
<td><input type="text" name="email" size="30" placeholder="Enter Email Address" required/></td>
</tr>
<tr>
<td>Password :</td>
<td><input type="password" name="password" size="30" placeholder="Enter Password" required/></td>
</tr>
</table><br>
<input type="submit" value="Sign in"/><br><br>
New to Consonant? <a href ="CheckoutRegister.jsp">Click here to create an account</a>
</form>
<div id="footer">
<p>Copyright &copy;. All rights reserved. 
</div>
</center>
</body>
</html>