package com.favery.controller;

import java.io.IOException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.favery.dao.RestaurantDao;
import com.favery.daoimpl.RestaurantDaoImpl;
import com.favery.model.Restaurant;


@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RestaurantDao restaurantDao = new RestaurantDaoImpl();
		List<Restaurant> restaurantList = restaurantDao.getAllRestaurants();
		HttpSession session = req.getSession();
		session.setAttribute("restaurantList", restaurantList);
		resp.sendRedirect("home.jsp");
	}
}
