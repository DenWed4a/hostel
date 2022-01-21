package com.epam.ds.controller.impl.gotocommand;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.ds.controller.Command;
import com.epam.ds.hostel.entity.BookingRequest;
import com.epam.ds.hostel.service.BookingRequestService;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.exception.ServiceException;

public class GoToBookingRequestsPage implements Command{
	private final static String goToRequestsPage = "/WEB-INF/jsp/BookingRequestsPage.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		BookingRequestService service = ServiceFactory.getInstance().getBookingRequestService();
		try {
			List<BookingRequest> requests = service.findAllBookingRequest();

			request.setAttribute("requests", requests);
			
		} catch (ServiceException e) {
			
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(goToRequestsPage);
		dispatcher.forward(request, response);
		
	}

}
