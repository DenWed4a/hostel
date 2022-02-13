package com.epam.ds.controller.impl.gotocommand;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.epam.ds.controller.Command;

public class GoToIndexPage implements Command{
	
	private final static String goIndexPage = "/WEB-INF/jsp/mainPage.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(goIndexPage);
		dispatcher.forward(request, response);
		
		
	}

}
