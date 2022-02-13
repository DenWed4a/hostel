package com.epam.ds.controller.impl.gotocommand;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.ds.controller.AdminCommand;

import com.epam.ds.hostel.entity.BookingRequest;

import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.service.BookingRequestService;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.UserService;
import com.epam.ds.hostel.service.exception.ServiceException;

public class GoToUserList implements AdminCommand {
	private final static Logger log = Logger.getLogger(GoToUserList.class);
	private final static String GO_TO_USER_LIST_PAGE = "/WEB-INF/jsp/userList.jsp";
	private final static String GO_TO_ERROR_PAGE = "Controller?command=GO_TO_ERROR_PAGE";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		BookingRequestService bookingRequestService = factory.getBookingRequestService();
		String tableName = request.getParameter("table_name");
		

		request.setAttribute("table_name", tableName);

		try {
			List<BookingRequest> bRequests = bookingRequestService.findAllBookingRequest();
			Set<User> usersSet = new HashSet<>();
			for (BookingRequest element : bRequests) {
				System.out.println(element.getStatus());
				if (element.getStatus().equals("confirmed")) {
					usersSet.add(userService.findById(element.getClientId()));
				}
			}
			List<User> users = userService.findAllUsers();
			request.setAttribute("users", users);
			request.setAttribute("users_set", usersSet);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(GO_TO_USER_LIST_PAGE);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(GO_TO_ERROR_PAGE);
		}

		

	}

}
