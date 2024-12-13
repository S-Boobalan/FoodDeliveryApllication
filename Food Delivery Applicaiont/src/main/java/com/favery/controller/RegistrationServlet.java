package com.favery.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.favery.dao.UserDao;
import com.favery.daoimpl.UserDaoImpl;
import com.favery.model.User;

@SuppressWarnings("serial")
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet{
	private PrintWriter pw;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		pw=resp.getWriter();
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String phoneNumber = req.getParameter("phoneNumber");
		String address = req.getParameter("address");
		
		User user = new User(username, email, password, phoneNumber, address);
		UserDao userDao = new UserDaoImpl();
		int status = userDao.addUser(user);
		if(status == 1) {
			pw.println("Hiiiiiiiiiiiiiiiiii");
			resp.sendRedirect("success.jsp");
		}
		else {
			pw.println("Hiiiiiiiiiiiiiiiiii");
			resp.sendRedirect("failure.jsp");
		}
	}
}















