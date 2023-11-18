<%@page import="com.spring.prod.entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
	User u = (User) session.getAttribute("auth");
	%>

	<h2>
		Welcome
		 <%=u.getUserRole().get(0).getName()%>
	</h2> 

	<h3>
		<a href="product">Add Product</a>
	</h3>
	<h3>
		<a href="viewProducts">View Products</a>
	</h3>
	<h3>
		<a href="sellSummary">View Sale Summary!!</a>
	</h3>
	<div class="text-center mt-3">
		<h3>
			<a href="${pageContext.request.contextPath}/logout">Logout</a>
		</h3>
	</div>
</body>
</html>