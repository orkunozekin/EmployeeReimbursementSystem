package com.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.controller.ReimbursementController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.User;

public class JsonRequestHelper {

	public static void process(HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException, IOException {
		System.out.println(request.getRequestURI());

		switch (request.getRequestURI()) {
		case "/ERS/json/retreiveReimbursement":
			System.out.println("Attempting to get all reimbursement requests...");
			response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
			ReimbursementController.getAllReimbursements(request, response);
			break;
			
		case "/ERS/json/submitRequest":
			System.out.println("Attempting to submit a reimbursement request...");
			response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
			ReimbursementController.submitRequest(request, response);
			break;
		
		case "/ERS/json/employeesRequests":
			System.out.println("Attempting to get the employee's reimbursement requests...");
			response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
			ReimbursementController.getEmployeeRequests(request, response);
			break;
		
		case "/ERS/json/getUserFromSession":
			System.out.println("Attempting to get user from the session...");
			response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
			ReimbursementController.getUserFromSession(request, response);
			break;
		case "/ERS/json/updateReimbursement":
			System.out.println("Attempting to update reimbursement...");
			response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
			ReimbursementController.updateReimbursement(request, response);
			break;
		
		default:
			System.out.println("Issue! This URI doesn't match any of the ones in JsonRequestHelper");
			User user = null;
			response.getWriter().write(new ObjectMapper().writeValueAsString(user));
		}
	}
}
