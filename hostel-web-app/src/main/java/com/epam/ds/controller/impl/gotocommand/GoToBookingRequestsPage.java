package com.epam.ds.controller.impl.gotocommand;

import java.io.IOException;



import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.ds.controller.AdminCommand;

import com.epam.ds.hostel.entity.BookingRequest;
import com.epam.ds.hostel.entity.ConfirmedRequest;
import com.epam.ds.hostel.entity.status.EntityStatus.ConfirmedRequestStatus;
import com.epam.ds.hostel.service.BookingRequestService;
import com.epam.ds.hostel.service.ConfirmedRequestService;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.exception.ServiceException;

public class GoToBookingRequestsPage implements AdminCommand{
	private final static Logger log = Logger.getLogger(GoToBookingRequestsPage.class);
	private final static String GO_TO_REQUESTS_PAGE = "/WEB-INF/jsp/bookingRequestsPage.jsp";
	private final static String GO_TO_ERROR_PAGE = "Controller?command=GO_TO_ERROR_PAGE";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		BookingRequestService service = ServiceFactory.getInstance().getBookingRequestService();
		ConfirmedRequestService confirmedRequestService = ServiceFactory.getInstance().getConfirmedRequestService();
		String table = request.getParameter("table");
		String button = request.getParameter("button");
		String newStatus = request.getParameter("status_name");
		String bookingIdForImport = request.getParameter("b_info_id");
		
		
 		
		request.setAttribute("table", table);
		request.setAttribute("button", button);
		request.setAttribute("b_info_id", bookingIdForImport);
		
		
		String confirmedRequestId = request.getParameter("confirmed_request_id");
		try {
			
			if(newStatus != null) {			
				ConfirmedRequest confirmedRequest = confirmedRequestService.findConfirmedRequest(Integer.parseInt(confirmedRequestId));				
				confirmedRequest.setStatus(ConfirmedRequestStatus.values()[Integer.parseInt(newStatus)]);								
				confirmedRequestService.updateConfirmedRequest(confirmedRequest);				
			}
			List<BookingRequest> requests = service.findAllBookingRequest();
			request.setAttribute("requests", requests);
			
			List<ConfirmedRequest> confirmedRequests = confirmedRequestService.findAllRequests();
			request.setAttribute("confirmedRequests", confirmedRequests);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(GO_TO_REQUESTS_PAGE);
			dispatcher.forward(request, response);
			
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(GO_TO_ERROR_PAGE);
		}
		
		
		
		
	}

}
