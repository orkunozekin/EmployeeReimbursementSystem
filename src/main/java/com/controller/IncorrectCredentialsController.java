package com.controller;

import javax.servlet.http.HttpServletRequest;

public class IncorrectCredentialsController {

	public static String incorrectCredentials(HttpServletRequest request) {
		return "/resources/incorrectCredentials.html";
	}
	
}
