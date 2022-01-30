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

public class GoToUserInfoPage implements Command{
	private final static Logger log = Logger.getLogger(GoToUserInfoPage.class);
	private final static String GO_TO_USER_INFO_PAGE = "/WEB-INF/jsp/userInfoPage.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		UserService userService = ServiceFactory.getInstance().getUserService();
		User user;
		UserDetail userDetail;
		try {
			user = userService.findById(Integer.parseInt(id));
			userDetail = user.getDetail();
			request.setAttribute("user", user);
			request.setAttribute("userDetail", userDetail);
			
		} catch (NumberFormatException e) {
			
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect("Controller?command=GO_TO_ERROR_PAGE");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(GO_TO_USER_INFO_PAGE);
		dispatcher.forward(request, response);
				
	}

}
