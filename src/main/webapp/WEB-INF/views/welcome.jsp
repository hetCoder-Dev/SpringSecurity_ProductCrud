<%@page import="com.spring.prod.entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Page</title>
</head>
<body>

	<h2>Welcome to Spring MVC Product Application With Spring Security</h2>

	<jsp:include page="base.jsp"></jsp:include>
	<h4>${msg}</h4>

	<%
	User user = (User) session.getAttribute("auth");
	%>

	<%-- User:
	<h2>${user.userRole.name}</h2> --%>
	<h2>
		Welcome
		<%=user.getUserRole().get(0).getName()%></h2>

	<c:set value="<%=user.getUserRole().get(0).getName()%>" var="role"></c:set>
	<c:choose>
		<c:when test="${role == 'BUYER'}">
			<h4>
				<a href="buyer">Go To Buyer</a>
			</h4>
		</c:when>
		<c:when test="${role == 'SELLER'}">
			<h4>
				<a href="seller">Go To Seller</a>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<a href="/login">Login/Register</a>
			</h4>
		</c:otherwise>
	</c:choose>

	<h5>
		<a href="${pageContext.request.contextPath}/logout">Logout</a>
	</h5>


	<!-- <div class="text-center mt-3"> -->
	<h4>
		<a href="register">New User..Register Here!!</a>
	</h4>
	<!-- </div> -->
</body>
</html>