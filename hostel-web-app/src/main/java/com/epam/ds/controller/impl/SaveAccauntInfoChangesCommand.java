package com.epam.ds.controller.impl;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.ds.controller.Command;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.dao.impl.MySqlUserDAO;
import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.entity.UserDetail;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.UserService;
import com.epam.ds.hostel.service.exception.ServiceException;

public class SaveAccauntInfoChangesCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id;
		String login;
		String name;
		String surname;
		String email;
		String phoneNumber;
		String passportNumber;
		String nationality;
		String dateOfBirth;
		String passportDateOfIssue;
		String passportDateOfExpire;
		String address;
		
		
		id = request.getParameter("id");
		login = request.getParameter("login");
		name = request.getParameter("name");
		surname = request.getParameter("surname");
		email = request.getParameter("email");
		phoneNumber = request.getParameter("phoneNumber");
		passportNumber = request.getParameter("passportNumber");
		nationality = request.getParameter("nationality");
		dateOfBirth = request.getParameter("dateOfBirth");
		passportDateOfExpire = request.getParameter("expire");
		passportDateOfIssue = request.getParameter("issue");
		address  = request.getParameter("address");
		UserDetail detail = new UserDetail();
		detail.setAddress(address);

		
		
		detail.setEmail(email);
		detail.setName(name);
		detail.setSurname(surname);
		
		if(dateOfBirth!=null) {
		detail.setDateOfBirth(Date.valueOf(dateOfBirth));
		}
		
		if(passportDateOfExpire != null) {
		detail.setPassportDateOfExpire(Date.valueOf(passportDateOfExpire));
		}
		if(passportDateOfIssue != null) {
		detail.setPassportDateOfIssue(Date.valueOf(passportDateOfIssue));
		}
		detail.setNationality(nationality);
		detail.setPhoneNumber(phoneNumber);
		detail.setPassportNumber(passportNumber);
		
		
		
		User user = new User.Builder().login(login).userDetail(detail).build();
		
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		
		try {
			userService.updateUser(Integer.parseInt(id), user);
			userService.updateUserDetail(Integer.parseInt(id), user);
		} catch (NumberFormatException e) {
			
		} catch (ServiceException e) {
			
		}
		
		response.sendRedirect("Controller?command=GO_TO_ACCAUNT_PAGE");
		
		
		
		
		
		

		
	}

}
