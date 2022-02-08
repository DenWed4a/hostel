package com.epam.ds.controller.impl;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.ds.controller.Command;

import com.epam.ds.hostel.entity.ConfirmedRequest;
import com.epam.ds.hostel.service.ConfirmedRequestService;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.exception.ServiceException;

public class SaveConfirmedRequest implements Command {
	private final static Logger log = Logger.getLogger(SaveConfirmedRequest.class);
	private final static String GO_TO_BOOKING_REQUEST_PAGE = "WEB-INF/jsp/BookingRequestsPage.jsp";
	private final static String GO_TO_BOOKING_REQUEST_PAGE2 = "Controller?command=GO_TO_BOOKING_REQUESTS_PAGE";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String billId = request.getParameter("bill_id");
		int bookingRequestId = Integer.parseInt(request.getParameter("booking_request_id"));
		int adminId = (Integer) session.getAttribute("userId");
		String[] bedPlacesString = request.getParameterValues("bed_places_id");
		String[] lockersString = request.getParameterValues("lockers_id");

		int[] bedPlacesInt = new int[bedPlacesString.length];

		for (int i = 0; i < bedPlacesString.length; i++) {
			bedPlacesInt[i] = Integer.parseInt(bedPlacesString[i]);
		}
		int[] lockersInt = null;
		if (lockersString != null) {
			lockersInt = new int[lockersString.length];
			for (int i = 0; i < lockersString.length; i++) {
				lockersInt[i] = Integer.parseInt(lockersString[i]);
			}
		}

		Date confirmationDate = new Date(Calendar.getInstance().getTimeInMillis());
		ServiceFactory factory = ServiceFactory.getInstance();
		ConfirmedRequestService confirmedRequestService = factory.getConfirmedRequestService();

		ConfirmedRequest confirmedRequest;

		confirmedRequest = new ConfirmedRequest();
		confirmedRequest.setAdministratorId(adminId);
		confirmedRequest.setBillId(Integer.parseInt(billId));
		confirmedRequest.setConfirmationDate(confirmationDate);
		confirmedRequest.setId(bookingRequestId);

		try {
			confirmedRequestService.addConfirmedRequest(confirmedRequest, bedPlacesInt, lockersInt);
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect("Controller?command=GO_TO_ERROR_PAGE");
		}

		// RequestDispatcher dispatcher =
		// request.getRequestDispatcher(GO_TO_BOOKING_REQUEST_PAGE);
		// dispatcher.forward(request, response);
		response.sendRedirect(GO_TO_BOOKING_REQUEST_PAGE2);

	}

}
