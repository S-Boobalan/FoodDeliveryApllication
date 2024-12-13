<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.favery.model.OrderItem, com.favery.model.Menu, com.favery.model.Restaurant" %>
<%@ page import="com.favery.daoimpl.MenuDaoImpl, com.favery.daoimpl.RestaurantDaoImpl" %>
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
	    <title>Home - Favery</title>
	</head>
	<body>
		<%
	        
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
	                <li class="active"><a href="orderHistory.jsp">Orders</a></li>
	            </div>
	            <div class="rightend">
	                <a href="logout"><button class="btn">Logout</button></a>
	            </div>
	        </ul>
	    </nav>
	
	    <header>
	        <h1>Order Details</h1>
	    </header>
	
	    <div class="order-container">
	    	<%
	            List<OrderItem> orderItemList = (List<OrderItem>) session.getAttribute("orderItemList");
	
	            if (orderItemList != null && !orderItemList.isEmpty()) {
	                MenuDaoImpl menuDao = new MenuDaoImpl();
	                RestaurantDaoImpl restaurantDao = new RestaurantDaoImpl();
	
	                for (OrderItem item : orderItemList) {
	                    int menuId = item.getMenuId();
	                    Menu menu = menuDao.getMenu(menuId);
	                    Restaurant restaurant = restaurantDao.getRestaurant(menu.getRestaurantId());
	        %>
	        <div class="order-item-card">
	            <div class="order-image-container">
	                <img src="<%= menu.getImgPath() %>" 
                        alt="Menu Image of <%= menu.getMenuName() %> ">
	            </div>
	            <div class="order-details">
	                <div>
	                    <div class="order-restaurant-name"><%= restaurant.getRestaurantName() %></div>
	                    <div class="order-location"><%= restaurant.getAddress() %></div>
	                </div>
	                <hr class="divider">
	                <div class="order-menu-info">
	                    <div class="order-menu-name"><%= menu.getMenuName() %></div>
	                </div>
	                <div class="order-menu-info">
	                    <div class="order-price">Price: ₹ <%= item.getSubTotal() / item.getQuantity() %></div>
	                    <div class="order-quantity">Quantity: <%= item.getQuantity() %></div>
	                    <div class="order-sub-total">Sub-Total: ₹<%= item.getSubTotal() %></div>
	                </div>
	            </div>
	        </div>
	        <%
                }
            } else {
	        %>
	        	<p style="text-align: center;">No order items found</p>
	        <%
	            }
	        %>
	    </div>
	</body>
</html>