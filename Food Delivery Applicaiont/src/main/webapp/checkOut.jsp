<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.favery.model.CartItem, com.favery.model.Cart, java.util.Map"%>
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
	    <title>Checkout - Favery</title>
	</head>
	<body>
		<%
	        Cart cart = (Cart) session.getAttribute("cart");
	        Map<Integer, CartItem> cartItems = cart != null ? cart.getItems() : null;
	        int grandTotal = 0;
	    %> 
		<nav>
	        <ul class="navbar">
	            <div class="leftend">
	                <li><img src="assets/logo.png" alt="Logo" width="75px" height="75px"></li>
	                <li>Favery</li>
	            </div>
	            <div class="center">
	                <li><a href="home.jsp">Home</a></li>
	                <li><a href="cart.jsp">Cart</a></li>
	                <li><a href="#">Orders</a></li>
	            </div>
	            <div class="rightend">
	                <a href="logout"><button class="btn">Logout</button></a>
	            </div>
	        </ul>
	    </nav>
	
	    <header>
	        <h1>Check-Out</h1>
	    </header>
	    <div class="checkout-container">
	        <table class="checkout-table">
	            <thead>
	                <tr>
	                    <th>Menu Name</th>
	                    <th>Price</th>
	                    <th>Quantity</th>
	                    <th>Subtotal</th>
	                </tr>
	            </thead>
	            <tbody>
				<%
			    	if(cartItems != null && !cartItems.isEmpty()) {
			    		for(CartItem item : cartItems.values()) {
			    			int subTotal = 0;
			    			subTotal += (item.getPrice() * item.getQuantity());
			    %>
	                <tr>
	                    <td><%= item.getMenuName() %></td>
	                    <td><%= item.getPrice() %></td>
	                    <td><%= item.getQuantity() %></td>
	                    <td><%= subTotal %></td>
	                </tr>
	                <% grandTotal += subTotal; %>
	            <% } } %>
	            </tbody>
	        </table>
	        
	
	        <div class="grand-total">
	            <h2>Grand Total : ₹ <%= grandTotal %></h2>
	        </div>
	
	        <form action="OrderServlet" method="post">
	        	<input type="hidden" name="grandTotal" value="<%= grandTotal %>">
	            <div class="form-group">
	                <label for="address">Address:</label>
	                <textarea id="address" name="address" required></textarea>
	            </div>
	
	            <div class="form-group">
	                <label for="payment-method">Payment Method:</label>
	                <select id="payment-method" name="payment-method" required>
	                    <option value="Cash on delivery">Cash on Delivery</option>
	                    <option value="Upi">UPI</option>
	                    <option value="Card">Credit/Debit Card</option>
	                </select>
	            </div>
	
	            <div class="confirm-order">
	                <button type="submit" class="btn">Confirm Order</button>
	            </div>
	        </form>
	    </div>
	</body>
</html>