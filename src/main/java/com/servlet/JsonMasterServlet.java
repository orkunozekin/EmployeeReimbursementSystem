package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JsonMasterServlet extends HttpServlet {
       
    
    public JsonMasterServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In JsonServlet doGet");
		JsonRequestHelper.process(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In JsonServlet doPost");
		JsonRequestHelper.process(request, response);
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonRequestHelper.process(request, response);
		System.out.println("In JsonServlet doPut");
	}
	


}
