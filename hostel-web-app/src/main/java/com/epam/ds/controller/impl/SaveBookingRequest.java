package com.epam.ds.controller.impl;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.ds.controller.Command;
import com.epam.ds.hostel.dao.BookingRequestDAO;
import com.epam.ds.hostel.dao.UserDAO;

import com.epam.ds.hostel.dao.impl.MySqlBookingRequestDAO;
import com.epam.ds.hostel.dao.impl.MySqlUserDAO;
import com.epam.ds.hostel.entity.Bill;
import com.epam.ds.hostel.entity.BookingRequest;
import com.epam.ds.hostel.service.BillService;

import com.epam.ds.hostel.service.BookingRequestService;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.UserService;
import com.epam.ds.hostel.service.exception.ServiceException;


public class SaveBookingRequest implements Command{
	
	private final String goToBookingPage = "/WEB-INF/jsp/bookingPage.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("login");
		
		String numberOfplaces;
		String numberOfLockers;
		String checkInDate;
		String checkOutDate;
		
		String bookingMessage = "itSent";
		
		numberOfplaces = request.getParameter("numberPlaces");
		numberOfLockers = request.getParameter("numberLokers");
		checkInDate = request.getParameter("startDate");
		checkOutDate = request.getParameter("endDate");
		
		System.out.println(numberOfLockers+" "+numberOfplaces+" "+checkInDate+" "+checkOutDate);
		
		
		ServiceFactory factory = ServiceFactory.getInstance();
		
		BookingRequestService bookingService = factory.getBookingRequestService();	
		UserService userService = factory.getUserService();
		
		BookingRequest bookingRequest = new BookingRequest();
		BillService billService = factory.getBillService();
		
		Bill bill = new Bill();
		try {
			
			int id = userService.findByLogin(login).getId();
			bookingRequest.setClientId(id);
			bookingRequest.setNumberOfPlaces(Integer.parseInt(numberOfplaces));
			bookingRequest.setNumberOfLockers(Integer.parseInt(numberOfLockers));
			bookingRequest.setStartDate(Date.valueOf(checkInDate));
			bookingRequest.setEndDate(Date.valueOf(checkOutDate));
			bookingService.addNewRequest(bookingRequest);
			bill.setBookingRequestID(id);
			
			session.setAttribute("bookingMessage", bookingMessage);
			
		} catch (ServiceException e) {
			
		}
		
		//request.getRequestDispatcher("Controller?command=GO_TO_BOOKING_PAGE").forward(request, response);
		
		response.sendRedirect("Controller?command=GO_TO_BOOKING_PAGE");
		
		
	}

}
