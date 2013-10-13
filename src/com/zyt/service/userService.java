package com.zyt.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zyt.domain.User;
import com.zyt.util.SqlHelper;

public class userService {
	
	//verify if the user is OK
	
	public boolean checkUser(User user) {
		
		boolean b = false;
		
		String sql = "SELECT * FROM users WHERE id=? and password=?";
		
		
		String[] parameters = {
				user.getId() +"",
				user.getPwd()
		};
		
		System.out.println(parameters[0] + " " +parameters[1]);
		
		ResultSet rs = SqlHelper.executeQuery(sql, parameters);
		
		try {
			if (rs.next()) {
				b = true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		
		return b;
	}

}
