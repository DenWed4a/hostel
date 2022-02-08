package com.epam.ds.controller.impl.gotocommand;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.ds.controller.Command;
import com.epam.ds.hostel.entity.BookingRequest;
import com.epam.ds.hostel.service.BookingRequestService;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.exception.ServiceException;

public class GoToChangeBookingRequest implements Command{
	private final static Logger log = Logger.getLogger(GoToChangeBookingRequest.class);
	private final static String GO_TO_CHANGE_BOOKING_REQUEST_PAGE = "/WEB-INF/jsp/changeBookingRequestPage.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String id = request.getParameter("booking_request_id");
		ServiceFactory factory = ServiceFactory.getInstance();
		BookingRequestService bService = factory.getBookingRequestService();
		BookingRequest bookingRequest;
		request.setAttribute("page", "booking_requests_page");
		
		try {
			bookingRequest = bService.getBookingRequestById(Integer.parseInt(id));
			request.setAttribute("bookRequest", bookingRequest);
			//bService.updateBookingRequest(bookingRequest);
		} catch (NumberFormatException | ServiceException e) {
			log.error(e);
			response.sendRedirect("Controller?command=GO_TO_ERROR_PAGE");
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(GO_TO_CHANGE_BOOKING_REQUEST_PAGE);
		dispatcher.forward(request, response);
		
		
	}

}
