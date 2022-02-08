package com.epam.ds.controller.impl;

import java.io.IOException;
import java.sql.Date;
import java.util.PrimitiveIterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.eclipse.jdt.internal.compiler.ast.AND_AND_Expression;

import com.epam.ds.controller.Command;
import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.entity.UserDetail;
import com.epam.ds.hostel.entity.UserRole;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.UserService;
import com.epam.ds.hostel.service.exception.ServiceException;

public class SaveAccauntInfoChangesCommand implements Command {
	private final static Logger log = Logger.getLogger(SaveAccauntInfoChangesCommand.class);
	private final static String GO_TO_ACCAUNT_PAGE = "Controller?command=GO_TO_ACCAUNT_PAGE";
	private final static String GO_TO_ERROR_PAGE = "Controller?command=GO_TO_ERROR_PAGE";
	private final static String GO_TO_BOOKING_REQUESTS_PAGE = "Controller?command=GO_TO_BOOKING_REQUESTS_PAGE&table=booking&button=new";
	private final static String GO_TO_USER_LIST = "Controller?command=GO_TO_USER_LIST";

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
		String role;
		String imagePath;

		String previousPage = request.getParameter("p_page");

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
		address = request.getParameter("address");
		role = request.getParameter("role");
		imagePath = request.getParameter("image");

		UserDetail detail = new UserDetail();
		detail.setAddress(address);

		detail.setEmail(email);
		detail.setName(name);
		detail.setSurname(surname);
		if (!dateOfBirth.isEmpty() && dateOfBirth != null) {
			detail.setDateOfBirth(Date.valueOf(dateOfBirth));
		}

		if (!passportDateOfExpire.isEmpty() && passportDateOfExpire != null) {
			detail.setPassportDateOfExpire(Date.valueOf(passportDateOfExpire));
		}
		if (!passportDateOfIssue.isEmpty() && passportDateOfIssue != null) {
			detail.setPassportDateOfIssue(Date.valueOf(passportDateOfIssue));
		}
		detail.setNationality(nationality);
		detail.setPhoneNumber(phoneNumber);
		detail.setPassportNumber(passportNumber);
		detail.setImage(imagePath);

		User user = new User.Builder().login(login).id(Integer.parseInt(id)).idRole(UserRole.valueOf(role))
				.userDetail(detail).build();

		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();

		try {
			userService.updateUser(user);
			userService.updateUserDetail(user);
			if (previousPage.equals("user_info_page")) {
				response.sendRedirect(GO_TO_BOOKING_REQUESTS_PAGE);
			} else if (previousPage.equals("user_list")) {
				response.sendRedirect(GO_TO_USER_LIST);
			} else {
				response.sendRedirect(GO_TO_ACCAUNT_PAGE);
			}
		} catch (NumberFormatException e) {
			response.sendRedirect(GO_TO_ERROR_PAGE);
			e.printStackTrace();
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(GO_TO_ERROR_PAGE);
		}

	}

}
