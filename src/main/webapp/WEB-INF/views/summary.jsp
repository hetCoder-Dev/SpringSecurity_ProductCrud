<%@page import="com.spring.prod.entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="base.jsp"></jsp:include>

	<%
	User auth = (User) session.getAttribute("auth");
	%>

	<div class="container mt-5">
		<div class="row">
			<div class="col-md-7 offset-md-2">
				<div class="card ml-4">
					<div class="card-body">
						<h2 class="text-center" style="font-style: italic;">Order
							Summary!!</h2>
						<table class="table" width="90%">
							<thead>
								<tr>
									<th scope="col">ID</th>
									<th scope="col">Name</th>
									<th scope="col">Quantity</th>
									<th scope="col">Purchase Date</th>
									<th scope="col">Cost Price</th>
									<th scope="col">Total Price</th>
								</tr>
							</thead>
							<c:forEach items="${orders}" var="o">
								<tr>
									<td>${o.purchaseId}</td>
									<td>${o.product.prodName}</td>
									<td>${o.totalUnit}</td>
									<td>${o.purchaseDt}</td>
									<td>${o.product.prodCostPrice }</td>
									<td>${o.totalCostPrice}</td>
								</tr>
							</c:forEach>
						</table>
						<div class="text-center mt-3">
							<h5>
								<a href="${pageContext.request.contextPath}/buyer">Back To
									Home</a>&nbsp;&nbsp; <a
									href="${pageContext.request.contextPath}/logout">Logout</a>
							</h5>
						</div>
						<div class="text-center mt-3">
							<h4 style="font-style: italic; color: maroon;">Thank You For
								Shopping With us!!</h4>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>