package com.zyt.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zyt.domain.User;

public class UpdateUserView extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		User user = (User) request.getAttribute("userinfo");
		
		out.println("<img src='imgs/hello_logo.jpg' /> <a href='MainFrame'>Go back to the main page</a><a href='LoginServlet'>Log out</a><hr/>");

		out.print("<h1>Modify User</h1>");
		out.print("<form action='/UsersManager/UserClServlet?type=update' method='post'>");
		System.out.println("heher");
		out.print("<table border=1px>");
		out.print("<tr><td>ID</td><td><input type='text' name='id' readonly value='"+user.getId()+"'></td></tr>");
		out.print("<tr><td>user name</td><td><input type='text' name='username' value='"+user.getUsername()+"'></td></tr>");
		out.print("<tr><td>email</td><td><input type='text' name='email' value='"+user.getEmail()+"'></td></tr>");
		out.print("<tr><td>grade</td><td><input type='text' name='grade' value='"+user.getGrade()+"'></td></tr>");
		out.print("<tr><td>password</td><td><input type='text' name='password' value='"+user.getPwd()+"'></td></tr>");
		out.print("<tr><td><input type='submit' value='Modify'/></td><td><input type='reset' value='reset'/></td></tr>");
		out.print("</table>");
		out.print("</from>");
		
		out.print("<hr/><img src='imgs/hellokitty_logo.jpg' />");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
