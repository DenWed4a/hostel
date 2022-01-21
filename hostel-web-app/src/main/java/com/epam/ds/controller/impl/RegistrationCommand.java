package com.epam.ds.controller.impl;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.ds.controller.Command;
import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.entity.UserDetail;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.UserService;
import com.epam.ds.hostel.service.exception.ServiceException;

public class RegistrationCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login;
		String password;
		String name;
		String surname;
		String email;
		String phoneNumber;
		
		login = request.getParameter("login");
		password = request.getParameter("password");
		name = request.getParameter("name");
		surname = request.getParameter("surname");
		email = request.getParameter("email");
		phoneNumber = request.getParameter("phoneNumber");
		
		UserDetail detail = new UserDetail();
		detail.setEmail(email);
		detail.setName(name);
		detail.setSurname(surname);
		detail.setPhoneNumber(phoneNumber);
		
		User user = new User.Builder().login(login).password(password).userDetail(detail).build();
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		
		
		try {
			userService.saveUser(user);
		} catch (ServiceException e) {
			
		}
		
		
	}

}
