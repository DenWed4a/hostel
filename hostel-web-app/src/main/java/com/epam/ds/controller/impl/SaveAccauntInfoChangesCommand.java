package com.epam.ds.controller.impl;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.ds.controller.Command;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.dao.impl.MySqlUserDAO;
import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.entity.UserDetail;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.UserService;
import com.epam.ds.hostel.service.exception.ServiceException;

public class SaveAccauntInfoChangesCommand implements Command{
	private final static Logger log = Logger.getLogger(SaveAccauntInfoChangesCommand.class);
	private final static String GO_TO_ACCAUNT_PAGE = "Controller?command=GO_TO_ACCAUNT_PAGE";
	private final static String GO_TO_ERROR_PAGE = "Controller?command=GO_TO_ERROR_PAGE";

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
			userService.updateUser(user);
			userService.updateUserDetail(user);
		} catch (NumberFormatException e) {
			response.sendRedirect(GO_TO_ERROR_PAGE);
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(GO_TO_ERROR_PAGE);
			
		}
		
		response.sendRedirect(GO_TO_ACCAUNT_PAGE);
		
		
		
		
		
		

		
	}

}
