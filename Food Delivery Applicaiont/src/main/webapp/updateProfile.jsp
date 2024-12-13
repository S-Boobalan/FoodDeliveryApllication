<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.favery.model.User"%>
<%
User loggedInUser = (User) session.getAttribute("loggedInUser");
if (loggedInUser == null) {
	response.sendRedirect("logout");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- styles -->
<link rel="stylesheet" type="text/css" href="css/style.css">

<!-- google fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Roboto:wght@700&display=swap"
	rel="stylesheet">
<title>Update Profile - Favery</title>
</head>
<body>
	<nav>
		<ul class="navbar">
			<div class="leftend">
				<li><img src="assets/logo.png" alt="Logo" width="75px"
					height="75px"></li>
				<li>Favery</li>
			</div>
			<div class="center">
				<li><a href="home.jsp">Home</a></li>
				<li class="active"><a href="updateProfile.jsp">Update Profile</a></li>
				<li><a href="cart.jsp">Cart</a></li>
				<li><a href="orderHistoryServlet">Orders</a></li>
			</div>
			<div class="rightend">
				<a href="logout"><button class="btn">Logout</button></a>
			</div>
		</ul>
	</nav>
	<header>
		<h1>Update Profile</h1>
	</header>
	<div class="updateProfile">
		<form action="updateProfile">
			<label for="username">Username:</label> 
			<input type="text" name="username" id="username" value="<%=loggedInUser.getUsername()%>" required> 
			
			<label for="email">Email:</label> 
			<input type="email" name="email" id="email" value="<%=loggedInUser.getEmail()%>" required>

			<label for="password">Password:</label> 
			<input type="password" id="password" name="password" required> 
			
			<label for="phone">Phone Number:</label> 
			<input type="text" name="phoneNumber" id="phoneNumber" value="<%=loggedInUser.getPhoneNumber()%>" required> 
				
			<label for="address">Address:</label>
			<textarea name="address" id="address" rows="2" required><%=loggedInUser.getAddress()%></textarea>

			<input type="submit" value="Update Profile" class="btn">
		</form>
	</div>
</body>
</html>