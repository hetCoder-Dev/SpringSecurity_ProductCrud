<%@page import="java.util.ArrayList"%>
<%@page import="com.spring.prod.entity.Purchase"%>
<%@page import="java.util.List"%>
<%@page import="com.spring.prod.entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@page import="com.spring.prod.entity.*"%>
	<jsp:include page="base.jsp"></jsp:include>

	<%
	User user = (User) session.getAttribute("auth");
	%>

	<div class="container mt-5">
		<div class="row">
			<div class="col-md-7 offset-md-2">
				<div class="card m-4">
					<div class="card-body">
						<h5>${msg}</h5>
						<% session.removeAttribute("msg"); %>
						<h2 class="text-center">Product Cart</h2>

						<table class="table" width="50%">
							<thead>
								<tr>

									<th>Id</th>
									<th>Name</th>
									<th>Price</th>
									<th>Quantity</th>
									<th>Option</th>
									<th>Price Per Unit</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="total" value="0"></c:set>
								<c:forEach var="item" items="${cart}">
									<c:set var="total"
										value="${total + item.product.prodCostPrice * item.totalUnit }"></c:set>
									<tr>
									<tr>

										<td scope="row">${item.product.id}</td>
										<td scope="row">${item.product.prodName}</td>
										<td>${item.product.prodCostPrice}</td>
										<td style="margin-top: 2px;">
											<form action="${pageContext.request.contextPath}/orderNow"
												method="post" class="form-inline">
												<input type="hidden" name="id" value="${item.product.id }">
												<input type="hidden" name="purchaseFromUser"
													value="${item.product.user.id}">

												<div class="form-group d-flex justify-content-between">

													<input type="number" name="totalUnit"
														value="${item.totalUnit}">
												</div>
												<div class="text-center mt-3">
													<input type="submit" value="Buy">
												</div>
											</form>
										</td>
										<td><a href="RemoveFromCart?id=${item.product.id}"
											class="btn btn-sm btn-danger">Remove</a></td>
										<td>${total }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="text-center mt-3">
							<a href="${pageContext.request.contextPath}/buyer">Add More!!</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>