package com.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
//
//import com.example.controller.HomeController;
//import com.example.controller.LoginController;
import javax.servlet.http.HttpServletResponse;

import com.controller.HomeController;
import com.controller.IncorrectCredentialsController;
import com.controller.LoginController;
import com.controller.LogoutController;
import com.controller.ReimbursementController;
import com.fasterxml.jackson.core.JsonProcessingException;

public class RequestHelper {

	public static String process(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, IOException {
		System.out.println("\t\tIn RequestHelper");
		System.out.println("this request's URI: " + request.getRequestURI());

		switch (request.getRequestURI()) {
		case "/ERS/api/user/login":
			System.out.println("case1");
			response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
			return LoginController.login(request, response);

		case "/ERS/api/user/employeeHome":
			System.out.println("case 2");
			response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
			return HomeController.employeeHome(request);

		case "/ERS/api/user/managerHome":
			System.out.println("case 3");
			response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
			return HomeController.managerHome(request);

		case "/ERS/api/user/incorrectcredentials":
			System.out.println("case 4");
			response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
			return IncorrectCredentialsController.incorrectCredentials(request);
		
		case "/ERS/api/user/logout":
			System.out.println("Attempting to end session and logout...");
			response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
			return LogoutController.endSession(request);
		default:
			System.out.println("default case");
			return "resources/invalid.html";
		}
	}
}
