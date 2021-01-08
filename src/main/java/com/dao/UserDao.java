package com.dao;

import com.model.User;


public interface UserDao {
	public void insertAccount(User user);
	public User checkLogin(String username, String password);
}
