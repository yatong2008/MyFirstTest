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
		
		out.println("<script type='text/javascript' language='javascript'>");
		out.println("function gotoPage(){var pageNow=document.getElementById('pageNow'); window.open('?pageNow='+pageNow.value,'_self')}");
		out.println("</script>");
		
		out.println("<img src='imgs/hello_logo.jpg' />Welcome XXX <a href='MainFrame'>Go back to the main page</a><a href='LoginServlet'>Log out</a><hr/>");

		out.print("<h1>Manage Users</h1>");
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		int pageNow = 1;
		String sPageNow = request.getParameter("pageNow");
			if (!"".equals(sPageNow))
				pageNow = sPageNow == null ? 1 :Integer.parseInt(sPageNow);
			
		
		int pageSize = 3;
		int pageCount;
		int rowCount;
		
		try {
			//load driver
			Class.forName("com.mysql.jdbc.Driver");
			//get connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jps_cms","root", "911922");
			
			//get the number of the row
			ps = con.prepareStatement("SELECT count(*) FROM users");
			rs = ps.executeQuery();
			rs.next();
			rowCount = rs.getInt(1);
			
			pageCount = (rowCount - 1) / pageSize + 1;
			
			if(pageNow > pageCount)
				pageNow = pageCount;
			
			ps = con.prepareStatement("SELECT * FROM users limit ?, ?");
			
			ps.setInt(1, (pageNow - 1) * pageSize);
			ps.setInt(2, pageSize);
			
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
			
			if (pageNow != 1)
				out.print("<a href='?pageNow=" + (pageNow - 1) +"'>Previous page<a>");
			
			for (int i = 1; i <= pageCount; i++) {
				out.print("<a href='?pageNow=" + i +"'><" + i + "><a>");
			}
			
			if (pageNow != pageCount)
				out.print("<a href='?pageNow=" + (pageNow + 1) +"'>Next page<a>");
			
			out.println("&nbsp;&nbsp;&nbsp;" + pageNow + "/" +pageCount +"<br />");
			
			out.println("jump to <input type='text' id='pageNow' name='pageNow'/><input type='button' onClick='gotoPage()' value='Jump'>");
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
