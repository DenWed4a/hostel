package com.epam.ds.controller.impl.gotocommand;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.pattern.LogEvent;

import com.epam.ds.controller.Command;

public class GoToGalleryPage implements Command{
	private final static Logger log = Logger.getLogger(GoToGalleryPage.class);
	private final static String GO_TO_GALLERY = "/WEB-INF/jsp/galleryPage.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(GO_TO_GALLERY);
		dispatcher.forward(request, response);
	}

}
