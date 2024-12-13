package com.favery.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.favery.dao.OrderHistoryDao;
import com.favery.dao.OrderItemDao;
import com.favery.dao.OrderTableDao;
import com.favery.daoimpl.OrderHistoryDaoImpl;
import com.favery.daoimpl.OrderItemDaoImpl;
import com.favery.daoimpl.OrderTableDaoImpl;
import com.favery.model.Cart;
import com.favery.model.CartItem;
import com.favery.model.OrderHistory;
import com.favery.model.OrderItem;
import com.favery.model.OrderTable;
import com.favery.model.Restaurant;
import com.favery.model.User;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// OrderTable updation in database

		HttpSession session = req.getSession();
		Restaurant restaurant = (Restaurant) session.getAttribute("restaurant");
		User user = (User) session.getAttribute("loggedInUser");
		
		int restaurantId = restaurant.getRestaurantId();
		int userId = user.getUserId();
		float grandTotal = Float.parseFloat(req.getParameter("grandTotal"));
		String status = "pending";
		String paymentMethod = req.getParameter("payment-method");
		
		OrderTable orderTable = new OrderTable(restaurantId, userId, grandTotal, status, paymentMethod);
		OrderTableDao orderTableDao = new OrderTableDaoImpl();
		int orderId = orderTableDao.addOrder(orderTable);
		
		// OrderItem updation in database
		
		Cart cart = (Cart) session.getAttribute("cart");
		
		Map<Integer, CartItem> cartItems = cart != null ? cart.getItems() : null;
		
		for(CartItem item : cartItems.values()) {
			int menuId = item.getMenuId();
			int quantity = item.getQuantity();
			float subTotal = (item.getPrice()) * quantity;
			OrderItem orderItem = new OrderItem(orderId, menuId, quantity, subTotal);
			OrderItemDao orderItemDao = new OrderItemDaoImpl();
			orderItemDao.addOrderItem(orderItem);
		}
		
		// OrderHistory updation in database
		
		OrderHistory orderHistory = new OrderHistory(orderId, userId, grandTotal, status);
		OrderHistoryDao orderHistoryDao = new OrderHistoryDaoImpl();
		orderHistoryDao.addOrderHistory(orderHistory);
		
		resp.sendRedirect("orderConfirm.jsp");
	}
}
