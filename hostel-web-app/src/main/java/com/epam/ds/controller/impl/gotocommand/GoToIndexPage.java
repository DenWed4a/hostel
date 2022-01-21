package com.epam.ds.controller.impl.gotocommand;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.ds.controller.Command;

public class GoToIndexPage implements Command{
	
	private final static String goIndexPage = "/WEB-INF/jsp/mainPage.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String urlString = request.getHeader("referer");
		
		System.out.println("HEADER---"+urlString);
		System.out.println("http://localhost:8080/hostel-web-app/Controller?command=GO_TO_INDEX_PAGE");
		session.setAttribute("url", "http://localhost:8080/hostel-web-app/Controller?command=GO_TO_INDEX_PAGE");
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(goIndexPage);
		dispatcher.forward(request, response);
		
		
	}

}
