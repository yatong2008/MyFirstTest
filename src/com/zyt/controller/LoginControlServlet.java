package com.zyt.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zyt.domain.User;
import com.zyt.service.userService;

import java.sql.*;

public class LoginControlServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//initial userService obj, verify
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		userService uService = new userService();
		
		User user = new User();
		user.setId(Integer.parseInt(id));
		user.setPwd(password);
		
			
			if(uService.checkUser(user)) {
				request.getRequestDispatcher("MainFrame").forward(request, response);
			} else {
				request.setAttribute("err", "Your Login ID or password is incorrect!");
				request.getRequestDispatcher("LoginServlet").forward(request, response);
			}
		

	}

}
