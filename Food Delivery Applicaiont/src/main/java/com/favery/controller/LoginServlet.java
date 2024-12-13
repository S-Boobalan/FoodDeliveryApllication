package com.favery.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.favery.dao.UserDao;
import com.favery.daoimpl.UserDaoImpl;
import com.favery.model.User;

@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		UserDao userDao = new UserDaoImpl();
		User user = userDao.getUser(email);
		
		if(user != null && password.equals(user.getPassword())) {
			HttpSession session = req.getSession();
			session.setAttribute("loggedInUser", user);
			resp.sendRedirect("home");
		}
		else {
			resp.sendRedirect("failure.jsp");
		}
 	
	}

}
