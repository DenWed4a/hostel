package com.epam.ds.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.ds.controller.Command;
import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.entity.UserRole;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.UserService;
import com.epam.ds.hostel.service.exception.ServiceException;

public class ChangeUserRole implements Command{
	private final static Logger log = Logger.getLogger(ChangeUserRole.class); 
	private final static String GO_TO_USER_LIST = "Controller?command=GO_TO_USER_LIST";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("user_id");
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		String userRole = request.getParameter("select_role");
		User user;
		try {
			user = userService.findById(Integer.parseInt(userId));
			user.setRole(UserRole.values()[Integer.parseInt(userRole)]);
			userService.updateUser(user);
			response.sendRedirect(GO_TO_USER_LIST);
		} catch (NumberFormatException | ServiceException e) {
			log.error(e);
			e.printStackTrace();
		}
		
	}

}
