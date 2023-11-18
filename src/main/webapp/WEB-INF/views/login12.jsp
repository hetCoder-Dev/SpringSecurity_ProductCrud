<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
						<h2 class="text-center">Login here</h2>
						<h4>${msg}</h4>
						<form action="validate" method="post">
							<div class="form-group">
								<label>Email:</label> <input type="email" class="form-control"
									name="email" required>
							</div>
							<div class="form-group mt-3">
								<label>Password:</label> <input type="password"
									class="form-control" name="password" required>
							</div>
							<div class="text-center mt-3">
								<input type="submit" value="Login">
							</div>
						</form>
						<div class="text-center mt-3">
							<a href="register">New User..Register Here!!</a>
						</div>
						<div class="text-center mt-3">
							<a href="${pageContext.request.contextPath}">Go To Home</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html> --%>