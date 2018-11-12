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

<script type="text/javascript" src="js/jquery/jquery.js"></script>

 <script type='text/javascript'>
 var value = "";
 
 function reply_click(clicked_id)
 {
    value = clicked_id;
    $.ajax({
    	   type: "GET",
    	   url: 'ProductsDetailsController',
    	   data: { 'id' : value},
    	   success: function(responseJson){
    		   alert(responseJson);
	          //  $.each(responseJson, function(key, value) {
	           //     $("#${item.getCdid()}display").append(responseJson);
	           // });
	         }});
 } 
 
 </script>
</head>
<body>
<center>
<a href="Index.jsp"><img src="images/newlogo.png" alt="" width="400" height="90" /></a>
<form name="modelDetail1" method="POST" action="CartController">
<h2>Product List</h2>
<table>
<tr>
<th colspan="5">Image</th> 
<th>Name</th>
<th>Title</th>
<th>Quantity</th>
<th>Price</th>
<th>Add to Cart</th>
</tr>
 <c:forEach items="${productList}" var="item">
 <tr>
 <td colspan="5"><img id="productsImg" src="images/${item.getCdid()}.jpg" alt="" width="124" height="90"><br><center><a id="${item.getCdid()}" href="#"  onClick="reply_click(this.id)">details</a></center>
 </td>
<td>${item.getCdid()} <input type="hidden" name="modelNo" value= "${item.getCdid()}"></td>
<td>${item.getTitle()} <input type="hidden" name="description" value="${item.getTitle()}"></td>
<td><input type="text" size="2" value="1" name="quantity"></td>
<td>$${item.getPrice()} <input type="hidden" name="price" value="${item.getPrice()}"></td>
<td><input type="submit" name="action" value="Add"></td>
</tr>
<tr>
<td id="${item.getCdid()}display" colspan="10"></td>
</tr> 
</c:forEach>
 </table>
 </form>
</center>
</body>
</html>