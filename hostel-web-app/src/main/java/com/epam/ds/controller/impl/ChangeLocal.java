package com.epam.ds.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.ds.controller.Command;

public class ChangeLocal implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String locale = request.getParameter("local");
		request.getSession().setAttribute("local", locale);
		
		//String url  = (String) request.getSession().getAttribute("url");
		//System.out.println(url);
		String url = request.getHeader("referer");
		response.sendRedirect(url);
		
	}

}