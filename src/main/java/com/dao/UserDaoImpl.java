package com.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.model.User;
import com.utility.ConnectionUtility;

public class UserDaoImpl implements UserDao {

	Logger loggy = Logger.getLogger(UserDaoImpl.class);
	Connection connection;
	
	public UserDaoImpl() {
		ConnectionUtility connectionUtil = new ConnectionUtility();
		connection = connectionUtil.getConn();
	}
	
	public UserDaoImpl(ConnectionUtility connectionUtil) {
		connection = connectionUtil.getConn();
	}

	// Registration
	@Override
	public void insertAccount(User user) {
		String sql = "INSERT INTO users(username, password, firstname, lastname, email, user_role_id) values (?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstname());
			ps.setString(4, user.getLastname());
			ps.setString(5, user.getEmail());
			ps.setInt(6, user.getUser_role_id());

			ps.executeUpdate(); // THIS line is what sends the information to the DB
			if(loggy.isInfoEnabled()) {
				loggy.info("---Account has successfully been created.---");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Logging in

	@Override
	public User checkLogin(String username, String password) {
		String sql = "SELECT * FROM users WHERE username = ? and password = ?";
		User user = null;

		try {
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = new User(rs.getString("username"), rs.getString("password"), rs.getString("firstname"),
						rs.getString("lastname"), rs.getString("email"), rs.getInt("user_role_id"));
				user.setUser_id(rs.getInt("user_id"));
			}
			if(loggy.isInfoEnabled()) {
				if(user != null) {
					loggy.info(" Logged in successfully");
				}
				else 
					loggy.error("Failed to login");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return user;
	}

}
