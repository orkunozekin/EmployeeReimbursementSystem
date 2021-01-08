package com.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDaoImpl;
import com.model.User;


public class MasterServlet extends HttpServlet {

	
	static UserDaoImpl userDao = new UserDaoImpl();

	
    public MasterServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		request.getRequestDispatcher(RequestHelper.process(request, response)).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(RequestHelper.process(request, response)).forward(request, response);
	}

}
