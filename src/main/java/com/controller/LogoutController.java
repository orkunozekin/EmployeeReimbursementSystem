package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController {

	public static String endSession(HttpServletRequest req) {
		System.out.println("in endSession method on logout controller");
		HttpSession session = req.getSession(false);
		
		if (session != null) {
			System.out.println("SESSION :::: " + session);
			session.removeAttribute("user");
			session.invalidate();
			System.out.println("SESSION :::: " + session);
			return "/resources/login.html";
		}
		return "/ERS/";
	}
}
