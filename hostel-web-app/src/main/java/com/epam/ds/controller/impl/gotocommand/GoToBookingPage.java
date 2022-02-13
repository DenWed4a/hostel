package com.epam.ds.controller.impl.gotocommand;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.epam.ds.controller.UsersCommand;

public class GoToBookingPage implements UsersCommand{
	private final static String goToBookingPage = "/WEB-INF/jsp/bookingPage.jsp";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("login");
	
		if(login!=null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(goToBookingPage);
			dispatcher.forward(request, response);
		}/*else {
			CommandProvider commandProvider  = new CommandProvider();
			commandProvider.getCommand("GO_TO_LOGINATION_PAGE").execute(request, response);
		}*/
		

		
	}

}
