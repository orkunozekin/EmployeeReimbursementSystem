package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Reimbursement;
import com.model.UpdatedReimb;
import com.model.User;
import com.service.ReimbursementService;

public class ReimbursementController {

	static ReimbursementService reimbService = new ReimbursementService();
	
	
	

	public static void getAllReimbursements(HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException, IOException {

		List<Reimbursement> reimbRequests = reimbService.getAllRequests();

		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
		PrintWriter printer = response.getWriter();
		System.out.println("the list of all requests: " + reimbRequests);
		// the "new ObjectMapper" stuff is jackson databind at work
		printer.write(new ObjectMapper().writeValueAsString(reimbRequests));

	}
	
	
	

	public static void submitRequest(HttpServletRequest req, HttpServletResponse resp)
			throws JsonParseException, JsonMappingException, IOException {
		System.out.println("in submit request");
		resp.setContentType("application/json");
		resp.setStatus(HttpServletResponse.SC_CREATED);
		resp.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		resp.addHeader("Pragma", "no-cache");
		resp.addIntHeader("Expires", 0);
		
		
		// lets get user from the session to set the reimbursement's author to userId.
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");

		// let's read a JSON from the user
		ObjectMapper mapper = new ObjectMapper();
		Reimbursement reimb = mapper.readValue(req.getInputStream(), Reimbursement.class); // user's json
		// Set user's id as the reimbursement author.
		reimb.setAuthor(user.getUser_id());

		// send the user a status message to let them know everything went okay

		reimbService.submitRequest(reimb);

		String myMessage = "Reimbursement Request has been created!";
		resp.getWriter().write(new ObjectMapper().writeValueAsString(myMessage));
	}
	
	
	
	

	public static void getEmployeeRequests(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		resp.addHeader("Pragma", "no-cache");
		resp.addIntHeader("Expires", 0);
		
		HttpSession session = req.getSession(false);

		User user = null;
		if (session != null) {
			user = (User) session.getAttribute("user");
			// System.out.println("user from controller " +user);

			if (user != null) {
				List<Reimbursement> reimbRequests = reimbService.getEmployeeRequests(user.getUser_id());

				PrintWriter printer = resp.getWriter();
				// System.out.println("the list of all requests: " + reimbRequests);
				// the "new ObjectMapper" stuff is jackson databind at work
				printer.write(new ObjectMapper().writeValueAsString(reimbRequests));

			} else {
				System.out.println("user: " + user);
			}
		} else {
			System.out.println("Session: " + session);
		}
	}

	
	
	
	
	public static User getUserFromSession(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		PrintWriter printer = resp.getWriter();
		// System.out.println("the list of all requests: " + reimbRequests);
		// the "new ObjectMapper" stuff is jackson databind at work
		printer.write(new ObjectMapper().writeValueAsString(user));
		return user;
	}
	
	
	

	

	public static void updateReimbursement(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.setContentType("application/json");
		resp.setStatus(HttpServletResponse.SC_ACCEPTED);
		
		
		ObjectMapper mapper = new ObjectMapper();
		UpdatedReimb reimbJson = mapper.readValue(req.getInputStream(), UpdatedReimb.class); // user's json
		System.out.println("Json from the user: " + reimbJson);
		//Our Reimbursement object to send to the DB.
		int id = reimbJson.getId();
		Reimbursement reimbToDao = reimbService.getReimbursementById(id);
		
		// get the user's id from the session and set that as the resolver(int) as the
		// user won't be sending their id themselves.
		User user = getUserFromSession(req, resp);
		
		//Update the necessary fields before sending to the DB
		reimbToDao.setResolver(user.getUser_id());
		reimbToDao.setStatus(reimbJson.getStatus());
		reimbToDao.setStatusId(reimbJson.getStatus_id());

	
		reimbService.updateReimbursement(reimbToDao);
	}

}
