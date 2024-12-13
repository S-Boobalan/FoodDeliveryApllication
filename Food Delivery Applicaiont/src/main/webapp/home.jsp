<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.favery.model.User, com.favery.model.Restaurant, java.util.List" %>
<!DOCTYPE html>
<html lang="en">

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
	    	User user = (User)session.getAttribute("loggedInUser");
	    	List<Restaurant> restaurantList = (List<Restaurant>)session.getAttribute("restaurantList");
	    %>
	    <% if(user == null) {%>
		    <h3>You haven't logged in...!</h3>
		<%} else {%>
		    <nav>
		        <ul class="navbar">
		            <div class="leftend">
		                <li><img src="assets/logo.png" alt="Logo" width="75px" height="75px"></li>
		                <li>Favery</li>
		            </div>
		            <div class="center">
		                <li class="active"><a href="#">Home</a></li>
		                <li><a href="updateProfile.jsp">Update Profile</a></li>
		                <li><a href="cart.jsp">Cart</a></li>
		                <li><a href="orderHistoryServlet">Orders</a></li>
		            </div>
		            <div class="rightend">
		                <a href="logout"><button class="btn">Logout</button></a>
		            </div>
		        </ul>
		    </nav>
		
		    <header>
		        <h1>Restaurant List</h1>
		    </header>
			<% if(restaurantList != null) {%>
			    <div class="container">
			        <% for(Restaurant restaurant : restaurantList) {%>
			            <div class="card">
			                <div class="image-container">
				                <img src="<%= restaurant.getImagePath() %>" 
	                                alt="Image of <%= restaurant.getRestaurantName() %>">
			                </div>
			                <div class="content">
			                    <div>
			                        <div class="title"><% out.println(restaurant.getRestaurantName()); %></div>
			                        <div class="rating-container">
			                            <div class="rating-title">Rating:</div>
			                            <div class="rating"><% out.println(restaurant.getRatings()); %></div>
			                            <div class="time-title">Delivery Time:</div>
			                            <div class="time"><% out.println(restaurant.getDeliveryTime()); %> mins</div>
			                        </div>
			                        <div class="description"><% out.println(restaurant.getCuisineType()); %></div>
			                        <div class="location"><% out.println(restaurant.getAddress()); %></div>
			                    </div>
			                    <a href="menu?restaurantId=<% out.println(restaurant.getRestaurantId()); %>" class="btn view-menu">View Menu</a>
			                </div>
			            </div>
			        <% } %>
			    </div>
			<%}%>
		<%}%>
	</body>
</html>