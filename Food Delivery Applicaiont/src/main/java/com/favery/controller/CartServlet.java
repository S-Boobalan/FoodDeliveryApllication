package com.favery.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.favery.dao.MenuDao;
import com.favery.daoimpl.MenuDaoImpl;
import com.favery.model.Cart;
import com.favery.model.CartItem;
import com.favery.model.Menu;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Cart cart = (Cart) session.getAttribute("cart");

		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}

		String action = req.getParameter("action");
		if ("add".equalsIgnoreCase(action)) {
			addItemtoCart(req, cart);
		} else if ("update".equalsIgnoreCase(action)) {
			updateCartItem(req, cart);
		} else if ("remove".equalsIgnoreCase(action)) {
			removeItemFromCart(req, cart);
		}
		
		session.setAttribute("cart", cart);
		resp.sendRedirect("cart.jsp");
	}

	private void addItemtoCart(HttpServletRequest req, Cart cart) {
		int menuId = Integer.parseInt(req.getParameter("menuId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		
		MenuDao menuDao = new MenuDaoImpl();
		Menu menu = menuDao.getMenu(menuId);
		
		if(menu != null) {
			CartItem item = new CartItem(
					menu.getMenuId(), 
					menu.getRestaurantId(), 
					menu.getMenuName(), 
					menu.getPrice(), 
					quantity, 
					(menu.getPrice() * quantity)
				);
			cart.addItem(item);
		}
		
	}

	private void updateCartItem(HttpServletRequest req, Cart cart) {
		int menuId = Integer.parseInt(req.getParameter("menuId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		cart.updateItem(menuId, quantity);
	}

	private void removeItemFromCart(HttpServletRequest req, Cart cart) {
		int menuId = Integer.parseInt(req.getParameter("menuId"));
		cart.removeItem(menuId);
	}
}
