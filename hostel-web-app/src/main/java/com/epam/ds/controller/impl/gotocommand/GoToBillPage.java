package com.epam.ds.controller.impl.gotocommand;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.ds.controller.Command;
import com.epam.ds.hostel.entity.Bill;
import com.epam.ds.hostel.entity.BookingRequest;
import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.service.BillService;
import com.epam.ds.hostel.service.BookingRequestService;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.UserService;
import com.epam.ds.hostel.service.exception.ServiceException;
import com.epam.ds.util.BillTotalCalculator;


public class GoToBillPage implements Command{
	private final static Logger log = Logger.getLogger(GoToBillPage.class);
	private final static String GO_TO_BILL_PAGE = "/WEB-INF/jsp/billPage.jsp";
	private final static String GO_TO_ERROR_PAGE = "Controller?command=GO_TO_ERROR_PAGE";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bookingRequestId = Integer.parseInt(request.getParameter("booking_id"));
		ServiceFactory factory = ServiceFactory.getInstance();
		BillService billService = factory.getBillService();
		BookingRequestService bookingRequestService = factory.getBookingRequestService();
		UserService userService = factory.getUserService();
		
		
		User user;
		Bill bill;
		BookingRequest bookingRequest;
		try {
			
			
			bill =  billService.findBillByRequestId(bookingRequestId);			
			request.setAttribute("bill", bill);		
			bookingRequest = bookingRequestService.getBookingRequestById(bill.getBookingRequestID());		
			request.setAttribute("bookingRequest", bookingRequest);			
			user = userService.findById(bookingRequest.getClientId());			
			request.setAttribute("user", user);			
			request.setAttribute("page", "booking_requests_page");			
			BillTotalCalculator billTotalCalculator = new BillTotalCalculator();			
			request.setAttribute("calculator", billTotalCalculator);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(GO_TO_BILL_PAGE);
			dispatcher.forward(request, response);
			
			
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(GO_TO_ERROR_PAGE);
		}
		
		
	}

}
