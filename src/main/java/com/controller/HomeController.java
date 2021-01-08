package com.controller;
import javax.servlet.http.HttpServletRequest;

public class HomeController {
	
	
	public static String employeeHome(HttpServletRequest request) {
		return "/resources/employeeHome.html";
	}
	
	public static String managerHome(HttpServletRequest request) {
		return "/resources/managerHome.html";
	}
}
