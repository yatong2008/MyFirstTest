package com.zyt.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<h1>User Login</h1>");
		out.println("<form action='/UsersManager/LoginControlServlet' method='post'>");
		out.println("User ID: <input type='text' name='username' /><br/>");
		out.println("Password: <input type='password' name='password' /><br/>");
		out.println("<input type='submit' value='Login' /><br/>");
		out.println("</form>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
