package com.favery.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.favery.dao.OrderHistoryDao;
import com.favery.daoimpl.OrderHistoryDaoImpl;
import com.favery.model.OrderHistory;
import com.favery.model.User;

@WebServlet("/orderHistoryServlet")
public class OrderHistoryServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("loggedInUser");
		int userId = user.getUserId();
		OrderHistoryDao orderHistoryDao = new OrderHistoryDaoImpl();
		List<OrderHistory> historyList = orderHistoryDao.getAllOrderHistoriesByUserId(userId);
		session.setAttribute("historyList", historyList);
		resp.sendRedirect("orderHistory.jsp");
	}
}









