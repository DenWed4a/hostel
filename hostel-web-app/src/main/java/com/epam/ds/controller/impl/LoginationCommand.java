package com.epam.ds.controller.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.ds.controller.Command;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.UserRoleService;
import com.epam.ds.hostel.service.UserService;
import com.epam.ds.hostel.service.exception.ServiceException;
import com.epam.ds.hostel.service.impl.UserRoleServiceImpl;
import com.epam.ds.hostel.service.impl.UserServiceImpl;

public class LoginationCommand implements Command{
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login;
		String password;
		String role;
		PrintWriter pr = response.getWriter();
		
		login = request.getParameter("login");
		password = request.getParameter("password");
		
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		UserRoleService roleService = factory.getUserRoleService();
		
		
		try {
			
			if(userService.validation(login, password)) {
				pr.println("Validation succeded!");
				int idRole = userService.findByLogin(login).getIdRole();
				role = roleService.getRole(idRole);
				HttpSession session = request.getSession();
				session.setAttribute("role", role);
				session.setAttribute("login", login);
				
			}else {
				pr.println("Wrong login or password.");
			}
		} catch (ServiceException e) {
			
		}
		
		response.sendRedirect("Controller?command=GO_TO_INDEX_PAGE");
		
		
		
		
	}

}
