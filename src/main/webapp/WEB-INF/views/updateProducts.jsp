<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="base.jsp"></jsp:include>
</head>
<body>
	<div class="container mt-5">
		<div class="row">
			<div class="col-md-7 offset-md-2">
				<div class="card">
					<div class="card-body">
						<h2 class="text-center">Product Details</h2>
						<form action="${pageContext.request.contextPath}/editProduct"
							method="post">
							<input type="hidden" name="id" value="${prod.id}">
							<div class="form-group">
								<label>Product Name: </label> <input type="text"
									class="form-control" name="prodName" value="${ prod.prodName}"
									readOnly="readonly">
							</div>
							<div class="form-group mt-3">
								<label>Product Description: </label> <input type="text"
									class="form-control" name="prodDesc" value="${ prod.prodDesc}">
							</div>
							<div class="form-group mt-3">
								<label>Product Image: </label> <input type="file"
									class="form-control" name="prodImg" value="${ prod.prodImg}">
							</div>
							<div class="form-group mt-3">
								<label>Product Sell Price: </label><input type="number"
									class="form-control" name="prodSellPrice"
									value="${prod.prodSellPrice}">
							</div>
							<div class="form-group mt-3">
								<label>Product Cost Price:</label><input type="number"
									class="form-control" name="prodCostPrice"
									value="${prod.prodCostPrice}">
							</div>
							<div class="form-group mt-3">
								<label>Stock Unit:</label><input type="number"
									class="form-control" name="stockUnit" value="${prod.stockUnit}">
							</div>
							<div class="form-group mt-3">

								<c:set var="sellerId" value="${prod.user.id}" />
								<fmt:parseNumber var="j" integerOnly="true" type="number"
									value="${sellerId}" />
								<input type="hidden" class="form-control" name="user"
									value="${j}">
							</div>
							<div class="text-center mt-3">
								<input type="submit" value="Edit Product">
							</div>
							<div class="text-center mt-3">
								<a href="${pageContext.request.contextPath}/viewProducts">Back
									To View</a>
							</div>
							<div class="text-center mt-3">
								<h5>
									<a href="${pageContext.request.contextPath}/logout">Logout</a>
								</h5>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>