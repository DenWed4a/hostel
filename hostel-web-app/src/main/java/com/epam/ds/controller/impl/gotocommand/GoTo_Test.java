package com.epam.ds.controller.impl.gotocommand;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.ds.controller.Command;

public class GoTo_Test implements Command{
	private final static String GO_TO_TEST = "/WEB-INF/jsp/Test.jsp";


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("page", "booking_requests_page");
		RequestDispatcher dispatcher = request.getRequestDispatcher(GO_TO_TEST);
		dispatcher.forward(request, response);

}
}