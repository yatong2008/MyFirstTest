package com.zyt.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainFrame extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<img src='imgs/hello_logo.jpg' />Welcome XXX <a href='UsersManager/LoginServlet'>Go back and log in again</a><hr/>");
		
		out.println("<h3>Please select your operation</h3>");
		out.println("<a href='/UsersManager/ManageUsers'>Manage users</a> <br />");
		out.println("<a href=''>Add new user</a> <br />");
		out.println("<a href=''>Find user</a> <br />");
		out.println("<a href=''>Log out</a> <br />");
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
