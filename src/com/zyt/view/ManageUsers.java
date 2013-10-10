package com.zyt.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManageUsers extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<img src='imgs/hello_logo.jpg' />Welcome XXX <a href='MainFrame'>Go back to the main page</a><a href='LoginServlet'>Log out</a><hr/>");

		out.print("<h1>Manage Users</h1>");
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int pageNow;
		int pageSize;
		int pageCount;
		int rowCount;
		
		try {
			System.out.println(request.getParameter("username"));

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jps_cms","root", "911922");
//			out.println("connect successful");
			
			System.out.println("connect<br />");

			
			ps = con.prepareStatement("SELECT * FROM users");
			rs = ps.executeQuery();
			
			out.print("<table border=1px bordercolor=green cellspacing=0>");
			out.print("<tr><th>ID</th><th>User name</th><th>email</th><th>level</th></tr>");
			while (rs.next())  {
				out.print("<tr><td>"+ rs.getInt(1) +
						"</td><td>"+ rs.getString(2) +
						"</td><td>"+ rs.getString(3) +
						"</td><td>"+ rs.getString(4) +
						"</t></tr>");

			}
			
			out.print("</table>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rs = null;
			}
			
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ps = null;
			}
			
			if (con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				con = null;
			}
		}
		out.print("<hr/><img src='imgs/hellokitty_logo.jpg' />");

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
