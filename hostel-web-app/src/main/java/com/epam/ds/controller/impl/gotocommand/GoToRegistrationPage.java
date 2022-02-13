package com.epam.ds.controller.impl.gotocommand;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.ds.controller.Command;

public class GoToRegistrationPage implements Command{
	private final static String GO_TO_REGISTRATION =  "/WEB-INF/jsp/registration.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(GO_TO_REGISTRATION);
		dispatcher.forward(request, response);
	
	}
	
	

}
	