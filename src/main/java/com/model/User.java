package com.model;

public class User {
	private int user_id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private int user_role_id;
	
	
	//Constructor
	public User() {
		
	}

	//All-arg constructor
	public User(String username, String password, String firstname, String lastname, String email,
			int user_role_id) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.user_role_id = user_role_id;
	}
	
	
	//getters and setters
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUser_role_id() {
		return user_role_id;
	}

	public void setUser_role_id(int user_role_id) {
		this.user_role_id = user_role_id;
	}

	@Override
	public String toString() {
		return "user_id=" + user_id + ", username=" + username + ", firstname="
				+ firstname + ", lastname=" + lastname + ", email=" + email + ", user_role_id=" + user_role_id;
	}
	
	
	//toString
	
	
	
	
	
}
