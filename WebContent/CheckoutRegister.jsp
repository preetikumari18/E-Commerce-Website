<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<script src="js/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	    
	
    </script>
</head>
<body>
<center>
<a href="Index.jsp"><img src="images/newlogo.png" alt="" width="350" height="90" /></a>
<form action="CheckoutRegisterController" method="post" style="width:500px;height:600px;border:1px solid #ccc"  >
<table cellpadding="3pt" >
<tr><td><h1>Create Account</h1></td></tr>
<c:out value="${registermessage}"/>
<tr>
<td>First Name :</td>
<td><input type="text" name="firstName" size="30" placeholder="Enter first name" required/></td>
</tr>
<tr>
<td>Last Name :</td>
<td><input type="text" name="lastName" size="30" placeholder="Enter last name" required/></td>
</tr>
<tr>
<td>Password :</td>
<td><input type="password" name="password1" size="30"  required/></td>
</tr>
<tr>
<td>Confirm Password :</td>
<td><input type="password" name="password2" size="30" required/></td>
</tr>
<tr>
<td>email :</td>
<td><input type="text" name="email" size="30" placeholder="Enter E-mail address" required/></td>
</tr>
<tr>
<td>Phone :</td>
<td><input type="text" name="phone" size="30" required/></td>
</tr>
<tr>
<td>Address :</td>
<td><input type="text" name="apartment" size="30" placeholder="Enter apartment no."/></td>
</tr>
<tr>
<td>Street :</td>
<td><input type="text" name="street" size="30" required/></td>
</tr>
<tr>
<td>Country :</td>
<td><input type="text" name="country" size="30" required/></td>
</tr>
<tr>
<td>Province :</td>
<td><input type="text" name="province" size="30" required/></td>
</tr>
<tr>
<td>Zip :</td>
<td><input type="text" name="pincode" size="30" required/></td>
</tr>
<tr>
<td>Default Payment:</td>
<td><select name="payment">
  <option value="None" selected="Selected">Select Payment Mode</option>
  <option value="Credit">Credit Card</option>
  <option value="Debit">Debit Card</option>
  <option value="COD">Cash on Delivery</option>
</select></td>
</tr>
</table>
<br>
<p>By creating an account, you agree to Consonant's <a href="#">Condition of Use</a> and <a href="#">Privacy Notice</a>.</p><br>
<input type="submit" value="Create your Consonant account" />
</form>
<div id="footer">
<p>Copyright &copy;. All rights reserved. 
</div>
</center>
</body>
</html>