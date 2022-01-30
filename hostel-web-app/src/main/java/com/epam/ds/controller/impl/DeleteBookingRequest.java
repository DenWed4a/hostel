package com.epam.ds.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.ds.controller.Command;
import com.epam.ds.hostel.service.BookingRequestService;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.exception.ServiceException;

public class DeleteBookingRequest implements Command{
	private final static Logger log = Logger.getLogger(DeleteBookingRequest.class);
	private final static String GO_TO_NEW_BOOKING_REQUESTS = "Controller?command=GO_TO_BOOKING_REQUESTS_PAGE";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookingRequestId = request.getParameter("booking_request_id");
		BookingRequestService service = ServiceFactory.getInstance().getBookingRequestService();
		try {
			service.deleteBookingRequest(Integer.parseInt(bookingRequestId));
		} catch (NumberFormatException e) {
			log.error(e);
			response.sendRedirect("Controller?command=GO_TO_ERROR_PAGE");
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect("Controller?command=GO_TO_ERROR_PAGE");
		}
		response.sendRedirect(GO_TO_NEW_BOOKING_REQUESTS);
		
		
	}

}
