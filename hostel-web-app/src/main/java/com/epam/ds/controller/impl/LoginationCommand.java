package com.epam.ds.controller.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.ds.controller.Command;
import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.main.Main3;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.UserRoleService;
import com.epam.ds.hostel.service.UserService;
import com.epam.ds.hostel.service.exception.ServiceException;
import com.epam.ds.hostel.service.impl.UserRoleServiceImpl;
import com.epam.ds.hostel.service.impl.UserServiceImpl;
import com.mysql.cj.Session;
import com.mysql.cj.log.Log;

public class LoginationCommand implements Command{
	private final static Logger log = Logger.getLogger(LoginationCommand.class);
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login;
		String password;
		String role;
		HttpSession session = request.getSession();
		
		login = request.getParameter("login");
		password = request.getParameter("password");
		
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		UserRoleService roleService = factory.getUserRoleService();
		boolean loginIsValid = false;
		User user;
		
		try {
			
			if(userService.validation(login, password)) {
				loginIsValid = true;
				password = null;
				user = userService.findByLogin(login);
				int userId = user.getId();
				int idRole = user.getRole().getTitle();
				role = roleService.getRole(idRole);
				
				session.setAttribute("role", role);
				session.setAttribute("login", login);
				session.setAttribute("userId", userId);
			}
		} catch (ServiceException e) {
			log.log(Level.ERROR, e);
			response.sendRedirect("Controller?command=GO_TO_ERROR_PAGE");
		}
		
		if(loginIsValid) {
			
			response.sendRedirect("Controller?command=GO_TO_INDEX_PAGE");
		}else {
			response.sendRedirect("Controller?command=GO_TO_ERROR_PAGE");
		}
		
		
		
		
		
	}

}
