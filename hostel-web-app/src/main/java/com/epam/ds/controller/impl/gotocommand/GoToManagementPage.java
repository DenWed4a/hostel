package com.epam.ds.controller.impl.gotocommand;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.ds.controller.AdminCommand;


public class GoToManagementPage implements AdminCommand{
	private final static String GO_TO_MANEGMENT_PAGE = "/WEB-INF/jsp/managementPage.jsp";
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(GO_TO_MANEGMENT_PAGE);
		dispatcher.forward(request, response);
		
	}

}
