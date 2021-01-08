package com.service;

import com.dao.UserDaoImpl;
import com.model.User;

public class UserService {
	
	UserDaoImpl userDao = new UserDaoImpl();
	
	public User validateLogin(String username, String password) {
		return userDao.checkLogin(username, password);
	}
	
	public void registerAccount(User user) {
		userDao.insertAccount(user);
	}
}
