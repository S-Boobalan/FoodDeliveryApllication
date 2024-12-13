package com.favery.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.favery.daoimpl.UserDaoImpl;
import com.favery.model.User;

@WebServlet("/updateProfile")
public class UpdateProfile extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
        HttpSession session = req.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        
        if (loggedInUser == null) {
            resp.sendRedirect("logout");
            return;
        }

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phoneNumber = req.getParameter("phoneNumber");
        String address = req.getParameter("address");

        loggedInUser.setUsername(username);
        loggedInUser.setEmail(email);
        loggedInUser.setPassword(password);
        loggedInUser.setPhoneNumber(phoneNumber);
        loggedInUser.setAddress(address);

        UserDaoImpl userDao = new UserDaoImpl();
        int updateStatus = userDao.updateUser(loggedInUser);

        if (updateStatus > 0) {
            session.setAttribute("loggedInUser", loggedInUser);
            resp.sendRedirect("home.jsp");
        }
	}
}
