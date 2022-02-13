package com.epam.ds.controller.impl.gotocommand;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;

import com.epam.ds.controller.Command;

import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.entity.UserDetail;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.UserService;
import com.epam.ds.hostel.service.exception.ServiceException;

public class GoToLoginationPage implements Command{
	
	private final static String GO_TO_LOGINATION_PAGE = "/WEB-INF/jsp/logination.jsp";
	private final static Logger log = Logger.getLogger(GoToLoginationPage.class);
	private final static String GO_TO_ERROR_PAGE = "Controller?command=GO_TO_ERROR_PAGE";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		
		User user;
		try {
			user = userService.findById(1);
			UserDetail detail = user.getDetail();
			request.setAttribute("user", user);
			request.setAttribute("detail", detail);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(GO_TO_LOGINATION_PAGE);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(GO_TO_ERROR_PAGE);
			
		}
		
		
		
	}

}
