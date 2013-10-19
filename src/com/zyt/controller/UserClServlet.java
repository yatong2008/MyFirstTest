package com.zyt.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.If;

import com.zyt.domain.User;
import com.zyt.service.userService;

public class UserClServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		userService us = new userService();
		String type = request.getParameter("type");
		
		
		if (type.equals("del")) {
			String id = request.getParameter("id");
			if (us.delUser(id)) {
				request.getRequestDispatcher("/OK").forward(request, response);
			} else {
				request.getRequestDispatcher("/ERR").forward(request, response);
			}

		} else if(type.equals("gotoUpdView")){
			String id = request.getParameter("id");
			
			User user = us.getUserById(id);
			request.setAttribute("userinfo", user);
			
			request.getRequestDispatcher("/UpdateUserView").forward(request, response);
			
		} else if (type.equals("update")) {
//			out.println("HAHAHAHA");
			String id = request.getParameter("id");
			String username = request.getParameter("username");
			String email=request.getParameter("email");
			String grade = request.getParameter("grade");
			String password = request.getParameter("password");
			
//			System.out.println(String.format("%s %s %s %s %s", id, username, email, grade, password));
			
			User u = new User(Integer.parseInt(id), username, email, grade, password);
			
			if (us.updateUser(u)) {
				request.setAttribute("info", "Modified successfully");
				request.getRequestDispatcher("/OK").forward(request, response);
			} else {
				request.setAttribute("info", "Modified failed");
				request.getRequestDispatcher("/ERR").forward(request, response);
			}
			
		}
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
