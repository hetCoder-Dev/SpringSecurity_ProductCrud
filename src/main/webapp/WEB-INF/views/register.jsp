<%@page import="com.spring.prod.entity.User"%>
<%@page import="java.util.List"%>
<%@page import="com.spring.prod.entity.UserRole"%>
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
	${msg}
	<div class="container mt-5">
		<div class="row">
			<div class="col-md-7 offset-md-2">
				<div class="card">
					<div class="card-body">
						<h2 class="text-center">Register here</h2>
						<form action="registrationValid" method="post">
							<div class="form-group">
								<label>First Name:</label> <input type="text" name="firstName"
									class="form-control" required="required" />
							</div>
							<div class="form-group">
								<label>Last Name:</label> <input type="text" name="lastName"
									class="form-control" required="required" />
							</div>
							<div class="form-group">
								<label>Email:</label> <input type="email" name="email"
									class="form-control" required="required" />
							</div>
							<div class="form-group">
								<label>Password:</label> <input type="password" name="password"
									class="form-control" required="required" />
							</div>
							<div class="form-group">
								<label>Mobile:</label> <input type="number" name="mobile"
									class="form-control" required="required" />
							</div>
							<div class="form-group">
								<label>DoB:</label> <input type="date" name="dob"
									class="form-control" required="required" />
							</div>

							<div class="form-group mt-2">
								<label>Role Type :</label> <select class="form-select"
									name="userRole" required="required" onchange="showDiv(this)">
									
									<option value="">Select a Role</option>
									<c:forEach items="${roles}" var="r">
										<option value="${r.id}">${r.name}</option>
									</c:forEach>
								</select>
							</div>
							<div id="hidden_div" class="form-group mt-2">
								<label>Balance:</label> <input type="number" name="balance"
									class="form-control mt-1" value="0" required />
							</div>

							<div class="text-center mt-3">
								<input type="submit" name="register" value="Register"> 
							</div>
						</form>
						<div class="text-center mt-3">
							<h5><a href="${pageContext.request.contextPath}/login">Already a User..Login Here!!</a></h5>
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
	<script type="text/javascript">
		function showDiv(select) {
			if (select.value == 1) {
				document.getElementById('hidden_div').style.display = "block";
			} else {
				document.getElementById('hidden_div').style.display = "none";
			}
		}
	</script>
</body>
</html>