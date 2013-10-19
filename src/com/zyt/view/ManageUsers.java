package com.zyt.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zyt.domain.User;
import com.zyt.service.userService;

public class ManageUsers extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<script type='text/javascript' language='javascript'>");
		out.println("function gotoPage(){var pageNow=document.getElementById('pageNow'); window.open('?pageNow='+pageNow.value,'_self')}"
				+ "function confirmDel(){return  window.confirm('Are you sure to delete the client? ');} ");
		out.println("</script>");
		
		out.println("<img src='imgs/hello_logo.jpg' />Welcome XXX <a href='MainFrame'>Go back to the main page</a><a href='LoginServlet'>Log out</a><hr/>");

		out.print("<h1>Manage Users</h1>");
		
		int pageNow = 1;
		String sPageNow = request.getParameter("pageNow");
			if (!"".equals(sPageNow))
				pageNow = sPageNow == null ? 1 :Integer.parseInt(sPageNow);
			
		
		int pageSize = 3;
		int pageCount;
		int rowCount;
		
		try {
			
			userService us= new userService();

			pageCount = us.getPageCount(pageSize);//(rowCount - 1) / pageSize + 1;
			
			ArrayList<User> al = us.getUsersByPage(pageNow, pageSize);
			
			out.print("<table border=1px bordercolor=green cellspacing=0>");
			out.print("<tr><th>ID</th><th>User name</th><th>email</th><th>level</th><th>op1</th><th>op2</th></tr>");

			for (User u:al){
				out.print("<tr><td>"+ u.getId() +
					"</td><td>"+ u.getUsername() +
					"</td><td>"+ u.getEmail() +
					"</td><td>"+ u.getGrade()  +
					"</td><td><a onClick='return confirmDel(); ' href='/UsersManager/UserClServlet?type=del&id="+ u.getId() +"'>Delete user </a>" +
					"</td><td><a href='/UsersManager/UserClServlet?type=gotoUpdView&id="+ u.getId() +"'>Modify user</a>" +
					"</td></tr>");
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
		} 
		out.print("<hr/><img src='imgs/hellokitty_logo.jpg' />");

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
