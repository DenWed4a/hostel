package com.epam.ds.controller.impl;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


import com.epam.ds.controller.Command;
import com.epam.ds.hostel.entity.BookingRequest;
import com.epam.ds.hostel.service.BookingRequestService;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.exception.ServiceException;

public class SaveBookingChangesCommand implements Command{
	private final static Logger log = Logger.getLogger(SaveBookingChangesCommand.class);
	private final static String GO_TO_BOOKING_REQUESTS_PAGE = "Controller?command=GO_TO_BOOKING_REQUESTS_PAGE";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		ServiceFactory factory = ServiceFactory.getInstance();
		BookingRequestService bService = factory.getBookingRequestService();
		BookingRequest bookingRequest;
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String numberOfPlaces = request.getParameter("numberPlaces");
		String numberOfLocker = request.getParameter("numberLokers");
		bookingRequest = (BookingRequest) session.getAttribute("booking_request");
		
		
		try {
			bookingRequest.setStartDate(Date.valueOf(startDate));
			bookingRequest.setEndDate(Date.valueOf(endDate));
			bookingRequest.setNumberOfPlaces(Integer.parseInt(numberOfPlaces));
			bookingRequest.setNumberOfLockers(Integer.parseInt(numberOfLocker));
			bService.updateBookingRequest(bookingRequest);
			session.removeAttribute("booking_request");
			//request.setAttribute("done", "");
		} catch (NumberFormatException | ServiceException e) {
			log.error(e);
			response.sendRedirect("Controller?command=GO_TO_ERROR_PAGE");
			e.printStackTrace();
		}
		
		response.sendRedirect(GO_TO_BOOKING_REQUESTS_PAGE);
		
	}

}
