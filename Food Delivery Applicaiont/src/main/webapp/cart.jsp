<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.favery.model.CartItem, com.favery.model.Cart, 
				java.util.Map, com.favery.daoimpl.RestaurantDaoImpl, 
				com.favery.model.Restaurant, com.favery.daoimpl.MenuDaoImpl, com.favery.model.Menu" %>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link rel="stylesheet" type="text/css" href="css/style.css">
	    <link rel="preconnect" href="https://fonts.googleapis.com">
	    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	    <link
	        href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&family=Roboto:wght@700&display=swap"
	        rel="stylesheet">
	    <title>Cart - Favery</title>
	</head>
	
	<body>
		<%
	        Cart cart = (Cart) session.getAttribute("cart");
	        Map<Integer, CartItem> cartItems = cart != null ? cart.getItems() : null;
	        RestaurantDaoImpl restaurantDao = new RestaurantDaoImpl();
	        MenuDaoImpl menuDao = new MenuDaoImpl();
	        boolean flag = true;
	    %>
	    <nav>
	        <ul class="navbar">
	            <div class="leftend">
	                <li><img src="assets/logo.png" alt="Logo" width="75px" height="75px"></li>
	                <li>Favery</li>
	            </div>
	            <div class="center">
	                <li><a href="home.jsp">Home</a></li>
	                <li><a href="updateProfile.jsp">Update Profile</a></li>
	                <li class="active"><a href="#">Cart</a></li>
	                <li><a href="orderHistoryServlet">Orders</a></li>
	            </div>
	            <div class="rightend">
	                <a href="logout"><button class="btn">Logout</button></a>
	            </div>
	        </ul>
	    </nav>
	
	    <header>
	        <h1>Cart</h1>
	    </header>
	
	    <div class="container">
	    	<%
	    		if(cartItems != null && !cartItems.isEmpty()) {
	    			for(CartItem item : cartItems.values()) {
	 
	    				Restaurant restaurant = restaurantDao.getRestaurant(item.getRestaurantId());
	    				
	    				Menu menu = menuDao.getMenu(item.getMenuId());
	    				if(restaurant != null && menu != null) {  
	    	%>
	        <div class="cart-item">
	            <div class="image-container">
	                <img src="<%= menu.getImgPath() %>" alt="Menu Image of <%= item.getMenuName() %> "> <!-- Ensure imgPath is correct -->
	            </div>
	            <div class="details">
	                <div class="restaurant-info">
	                    <div class="restaurant-name"><%= restaurant.getRestaurantName() %></div>
	                    <div class="location"><%= restaurant.getAddress() %></div>
	                </div>
	                <hr class="divider">
	                <div class="menu-info">
	                    <div class="menu-name"><%= item.getMenuName() %></div>
	                </div>
	                <form action="cart" method="post">
	                	<input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
	                	<div class="menu-info">
		                    <div class="price">₹<%= item.getPrice() %></div>
		                    <div class="quantity-container">
		                        <button type="button" class="quantity-btn minus">-</button>
		                        <input type="text" name="quantity" value="<%= item.getQuantity() %>" class="quantity-number" readonly>
		                        <button type="button" class="quantity-btn plus">+</button>
		                    </div>
		                </div>
		                <div class="sub-total">
		                    <div>
		                        <label for="sub-total">Sub Total:</label>
		                        <input type="text" id="sub-total" name="sub-total" value="<%= item.getSubTotal() %>" readonly>
		                    </div>
		                    <div class="rightend">
		                        <button type="submit" name="action" value="update" class="btn">Update</button>
		                        <button type="submit" name="action" value="remove" class="btn">Delete</button>
		                    </div>
		                </div>
	                </form>
	            </div>
	        </div>
		        <% 
	                }
	            }
	        } else { 
	        %>
	        	<p>Your cart is empty.</p>
       		<% flag = false;} %>
	    </div>
	    
	    <%
	    	if(session.getAttribute("cart") != null && flag == true) {
	    %>
		    <div class="proceed-to-checkout">
		    	<form action="menu.jsp" method="post" style="margin-right: 15px;">
		            <button type="submit" class="btn">Add more items</button>
		        </form>
		        <form action="checkout.jsp" method="post">
		            <button type="submit" class="btn">Proceed To Checkout</button>
		        </form>
		    </div>
		<% } %>
	    <script src="js/script.js"></script>
	</body>
</html>
