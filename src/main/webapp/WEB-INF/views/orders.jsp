<%@page import="com.spring.prod.entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<%
	User auth = (User) session.getAttribute("auth");
	request.getAttribute("msg");
	%>

	<div class="container mt-5">
		<div class="row">
			<div class="col-md-7 offset-md-2">
				<div class="card ml-4">
					<div class="card-body">
						<h2 class="text-center">All Purchases</h2>
						${msg}
						<%
						session.removeAttribute("msg");
						%>
						<table class="table" border="1" width="90%"
							style="margin-left: 4%;">
							<thead>
								<tr>
									<th scope="col">ID</th>
									<th scope="col">Name</th>
									<th scope="col">Quantity</th>
									<th scope="col">Purchase Date</th>
									<th scope="col">Cost Price</th>
									<th scope="col">Total Price</th>
									<th scope="col">Cancel</th>

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
									<td><a class="btn btn-sm btn-danger"
										href="cancelOrder/${o.purchaseId}/${o.product.id}">Cancel Order</a></td>
								</tr>
							</c:forEach>
						</table>
						<div class="text-center mt-3">
							<h4>
								<a href="buyer">Checkout Products!!</a>
							</h4>
						</div>
						<div class="text-center mt-3">
							<h3>
								<a href="${pageContext.request.contextPath}/logout">Logout</a>
							</h3>

							<div class="text-center mt-3">
								<h4>
									<a href="summary">Order Summary!!</a>
								</h4>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>