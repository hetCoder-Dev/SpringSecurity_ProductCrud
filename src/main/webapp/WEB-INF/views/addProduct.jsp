<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="base.jsp"></jsp:include>
</head>
<body>
	<div class="container mt-5">
		<div class="row">
			<div class="col-md-7 offset-md-2">
				<div class="card">
					<div class="card-body">
						<h2 class="text-center">Add Product here</h2>
						<form action="addProduct" method="post">
							<div class="form-group">
								<label>Product Name:</label> <input type="text"
									class="form-control" name="prodName">
							</div>
							<div class="form-group mt-3">
								<label>Product Description:</label> <input type="text"
									class="form-control" name="prodDesc">
							</div>
							<div class="form-group mt-3">
								<label>Product Image:</label> <input type="file"
									class="form-control" name="prodImg">
							</div>
							<div class="form-group mt-3">
								<label>Product Sell Price:</label> <input type="text"
									class="form-control" name="prodSellPrice">
							</div>
							<div class="form-group mt-3">
								<label>Product Cost Price:</label> <input type="text"
									class="form-control" name="prodCostPrice">
							</div>
							<div class="form-group mt-3">
								<label>Stock Unit:</label> <input type="text"
									class="form-control" name="stockUnit">
							</div>
							
							<div class="form-group mt-3">
								<input type="hidden"
									class="form-control" name="user" value="${auth.id}">
							</div>
							<div class="text-center mt-3">
								<input type="submit" value="Add Product">
							</div>
						</form>
						<div class="text-center mt-3">
							<a href="viewProducts">View Product</a>
						</div>
						<div class="text-center mt-3">
							<a href="${pageContext.request.contextPath}/logout">Logout</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>