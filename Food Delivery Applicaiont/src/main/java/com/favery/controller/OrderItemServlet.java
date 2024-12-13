package com.favery.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.favery.daoimpl.OrderItemDaoImpl;
import com.favery.model.OrderItem;

@WebServlet("/orderItemServlet")
public class OrderItemServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int orderId = Integer.parseInt(req.getParameter("orderId"));
		OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();
		List<OrderItem> orderItemList = orderItemDao.getItemsByOrderId(orderId);
		HttpSession session = req.getSession();
		session.setAttribute("orderItemList", orderItemList);
		resp.sendRedirect("displayList.jsp");
	}
}
