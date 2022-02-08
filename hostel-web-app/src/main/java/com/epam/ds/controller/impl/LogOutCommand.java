package com.epam.ds.controller.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.ds.controller.Command;

public class LogOutCommand implements Command{
	private final static String goToIndexPage = "/WEB-INF/jsp/mainPage.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session  = request.getSession();
		session.removeAttribute("login");
		session.removeAttribute("role");
		RequestDispatcher dispatcher = request.getRequestDispatcher(goToIndexPage);
		dispatcher.forward(request, response);
		
		
	}

}
