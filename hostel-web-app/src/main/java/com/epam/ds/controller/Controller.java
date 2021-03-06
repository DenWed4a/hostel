package com.epam.ds.controller;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final static String COMMAND = "command";
	private final CommandProvider provider = new CommandProvider();

	public Controller() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String commandName = request.getParameter(COMMAND);
		String redirectCommand = (String)request.getAttribute("redirect");
		if(redirectCommand!=null) {
			provider.getCommand(redirectCommand).execute(request, response);
		}
		else {
		provider.getCommand(commandName).execute(request, response);
		}
	}

}
