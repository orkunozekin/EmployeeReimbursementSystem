package com.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.User;
import com.service.UserService;

public class LoginController {
	
	static UserService userService = new UserService();
	
	public static String login(HttpServletRequest request, HttpServletResponse resp) throws JsonProcessingException, IOException {
		
		if (!request.getMethod().equals("POST")) {
			return "index.html";
		}

		// extracting the form data that user provided
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		//get the user from dao via service
		User user = userService.validateLogin(username, password);
		
	
		if(user != null) {
			request.getSession().setAttribute("user", user);
			resp.getWriter().write(new ObjectMapper().writeValueAsString(user));
			if(user.getUser_role_id() == 2) {
				return "/api/user/employeeHome";	
			} else if(user.getUser_role_id() == 1) {
				return "/api/user/managerHome";
			} else {
				return null;
			}
		}
		else {
			
			System.out.println("couldn't log in");
			return "/api/user/incorrectcredentials";
		}
	
	}
	
	
}
