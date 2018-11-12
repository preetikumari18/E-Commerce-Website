<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<a href="Index.jsp"><img src="images/newlogo.png" alt="" width="400" height="90" /></a>
<h1><c:out value="${odermessage}"/></h1>
<input type="button" onclick="location.href='Index.jsp';" value="Go To Home Page" />
</center>
</body>
</html>