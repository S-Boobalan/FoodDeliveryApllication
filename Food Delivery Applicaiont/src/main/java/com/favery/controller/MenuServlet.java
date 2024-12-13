package com.favery.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.favery.dao.MenuDao;
import com.favery.dao.RestaurantDao;
import com.favery.daoimpl.MenuDaoImpl;
import com.favery.daoimpl.RestaurantDaoImpl;
import com.favery.model.Menu;
import com.favery.model.Restaurant;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int restaurantId = Integer.parseInt(req.getParameter("restaurantId").trim());
		MenuDao menuDao = new MenuDaoImpl();
		List<Menu> menusList = menuDao.getAllMenusByRestaurant(restaurantId);

		RestaurantDao restaurantDao = new RestaurantDaoImpl();
        Restaurant restaurant = restaurantDao.getRestaurant(restaurantId);
		
        HttpSession session = req.getSession();
        session.setAttribute("restaurant", restaurant);
        
        if (menusList != null && !menusList.isEmpty()) {
            session.setAttribute("menusList", menusList);
        } 
        else {
            session.removeAttribute("menusList");
        }
		resp.sendRedirect("menu.jsp");
	}
}
