package com.epam.ds.controller.impl.gotocommand;

import java.io.IOException;
import java.sql.Date;

import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.ds.controller.AdminCommand;

import com.epam.ds.hostel.entity.BedPlace;
import com.epam.ds.hostel.entity.Bill;
import com.epam.ds.hostel.entity.BookingRequest;
import com.epam.ds.hostel.entity.Locker;
import com.epam.ds.hostel.service.BedPlaceService;
import com.epam.ds.hostel.service.BillService;
import com.epam.ds.hostel.service.BookingRequestService;
import com.epam.ds.hostel.service.LockerService;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.exception.ServiceException;


public class GoToBookingProcessingPage implements AdminCommand{
	private final static Logger log = Logger.getLogger(GoToBookingProcessingPage.class);
	private final static String GO_TO_BOOKING_PROCESSING_PAGE = "/WEB-INF/jsp/bookingProcessingPage.jsp"; 
	private final static String GO_TO_ERROR_PAGE = "Controller?command=GO_TO_ERROR_PAGE";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Date startDate;
		Date endDate;
		BookingRequest bookingRequest;		
		String booking_requst_id = request.getParameter("booking_request_id");
		session.setAttribute("booking_request_id", booking_requst_id);
		Bill bill;		
		Set<BedPlace> freeBedPlaces;
		Set<Locker> freeLockers;
		
		
		ServiceFactory factory = ServiceFactory.getInstance();
		BedPlaceService bedPlaceService = factory.getBedPlaceService();
		LockerService lockerService = factory.getLockerService();
		BookingRequestService bookingRequestService = factory.getBookingRequestService();
		BillService billService = factory.getBillService();
		
		try {
			
			bookingRequest = bookingRequestService.getBookingRequestById(Integer.parseInt(booking_requst_id));
			startDate = bookingRequest.getStartDate();
			endDate = bookingRequest.getEndDate();
			bill = billService.findBillByRequestId(bookingRequest.getId());
			
			freeBedPlaces = bedPlaceService.getFreeBedPlaces(startDate, endDate);
			freeLockers = lockerService.getFreeLockers(startDate, endDate);
			System.out.println(freeLockers);
			request.setAttribute("bedPlaces", freeBedPlaces);
			request.setAttribute("lockers", freeLockers);
			request.setAttribute("bill", bill);
			request.setAttribute("bookingRequest", bookingRequest);
			request.setAttribute("page", "booking_requests_page");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(GO_TO_BOOKING_PROCESSING_PAGE);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(GO_TO_ERROR_PAGE);
		}
		
		
		
		
		
	}

}
