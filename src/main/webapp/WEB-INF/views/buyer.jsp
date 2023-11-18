<%@page import="com.spring.prod.entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="base.jsp"></jsp:include>
</head>
<body>

	<%
	User u = (User) session.getAttribute("auth");
	%>

	<h2>
		Welcome
		<%=u.getUserRole().get(0).getName()%></h2>

	<h2>Total Balance: INR ${auth.balance}</h2>
	<div class="container mt-5">
		<div class="row">
			<div class="col-md-7 offset-md-2">
				<div class="card">
					<div class="card-body">
						<h2 class="text-center">View Product</h2>

						<table class="table" border="1" width="100%">
							<thead>
								<tr>
									<th scope="col">Id</th>
									<th scope="col">Product Name</th>
									<th scope="col">Product Description</th>
									<th scope="col">Product Sell Price</th>
									<th scope="col">Product Cost Price</th>
									<th scope="col">Product Stock</th>
									<th scope="col">Seller Id</th>
									<th scope="col">Add To Cart</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach items="${products}" var="p">
									<tr>
										<th scope="row">${p.id}</th>
										<td>${p.prodName}</td>
										<td>${p.prodDesc}</td>
										<td>${p.prodSellPrice}</td>
										<td>${p.prodCostPrice}</td>
										<td>${p.stockUnit}</td>
										<td>${p.user.id}</td>
										<td><a href="addToCart/${p.id}">AddToCart</a></td>

									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="text-center mt-3">
							<h5><a href="${pageContext.request.contextPath}/viewPurchase">Go To
								Cart!!</a></h5>
						</div>
						<div class="text-center mt-3">
							<h5>
								<a href="${pageContext.request.contextPath}/logout">Logout</a>
							</h5>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>