<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.favery.model.OrderHistory" %>
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
	    <title>Order History - Favery</title>
	</head>
	<body>
		<nav>
	        <ul class="navbar">
	            <div class="leftend">
	                <li><img src="assets/logo.png" alt="Logo" width="75px" height="75px"></li>
	                <li>Favery</li>
	            </div>
	            <div class="center">
	                <li><a href="home.jsp">Home</a></li>
	                <li><a href="updateProfile.jsp">Update Profile</a></li>
	                <li><a href="cart.jsp">Cart</a></li>
	                <li class="active"><a href="#">Orders</a></li>
	            </div>
	            <div class="rightend">
	                <a href="logout"><button class="btn">Logout</button></a>
	            </div>
	        </ul>
	    </nav>
	
	    <header>
	        <h1>Order History</h1>
	    </header>
	
	    <div class="checkout-container">
	    	<%
	            List<OrderHistory> historyList = (List<OrderHistory>) session.getAttribute("historyList");

	            if (historyList != null && !historyList.isEmpty()) {
	        %>
	        <table class="checkout-table">
	            <thead>
	                <tr>
	                    <th>User ID</th>
	                    <th>Order ID</th>
	                    <th>Order Date</th>
	                    <th>Total Amount</th>
	                    <th>Status</th>
	                    <th>View Items</th>
	                </tr>
	            </thead>
	            <tbody>
	                <%
	                    for (OrderHistory order : historyList) {
	                %>
	                <tr>
	                    <td><%= order.getUserId() %></td>
	                    <td><%= order.getOrderId() %></td>
	                    <td><%= order.getOrderDate() %></td>
	                    <td>₹ <%= order.getTotalAmount() %></td>
	                    <td><%= order.getStatus() %></td>
	                    <td>
	                        <a href="orderItemServlet?orderId=<%= order.getOrderId() %>" class="btn view-btn">View</a>
	                    </td>
	                </tr>
	                <%
	                    }
	                %>
	            </tbody>
	        </table>
	        <%
	            } else {
	        %>
	        <p style="text-align: center;">No history found</p>
	        <%
	        	}
	        %>
	    </div>
	</body>
</html>