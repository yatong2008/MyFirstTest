package com.zyt.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

public class LoginControlServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int username = Integer.parseInt(request.getParameter("username"));
		String password = request.getParameter("password");
		
//		System.out.println("here");
		
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jps_cms","root", "911922");
//			out.println("connect successful");
			
			ps = con.prepareStatement("SELECT * FROM users where id=? AND password=?");
			
			ps.setObject(1, username);
			ps.setObject(2, password);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				System.out.println("true");
//				response.sendRedirect("/UsersManager/MainFrame");
				request.getRequestDispatcher("MainFrame").forward(request, response);
			} else {
				System.out.println("fuck");
//				response.sendRedirect("/UsersManager/LoginServlet");
				request.getRequestDispatcher("LoginServlet").forward(request, response);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
//		System.out.println(username);
//		System.out.println(password);
		
		
		
//		out.println(username+"<BR />");
//		out.println(password);
//		System.out.println("reach here");

//		if ("yatong2008".equals(username) && "911922".equals(password)) {
//			System.out.println("correct");
//			response.sendRedirect("/UsersManager/MainFrame");
//		} else {
//			response.sendRedirect("/UsersManager/LoginServlet");
//		}
	}

}