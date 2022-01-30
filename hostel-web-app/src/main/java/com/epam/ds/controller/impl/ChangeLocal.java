package com.epam.ds.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.ds.controller.Command;

public class ChangeLocal implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String locale = request.getParameter("local");
		HttpSession session = request.getSession();
		session.setAttribute("local", locale);
		
		String url;
		
		url = (String) session.getAttribute("url");
		System.out.println(url+"          changelocal");
		
		response.sendRedirect(url);
		
	}

}
