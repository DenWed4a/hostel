package com.epam.ds.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.ds.controller.Command;
import com.epam.ds.hostel.service.ReviewService;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.exception.ServiceException;

public class DeleteReviewCommand implements Command{
	private final static Logger log = Logger.getLogger(DeleteBookingRequest.class);
	private final static String GO_TO_ERROR_PAGE = "Controller?command=GO_TO_ERROR_PAGE";
	private final static String GO_TO_REVIEWS_PAGE = "Controller?command=GO_TO_REVIEWS_PAGE";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		ReviewService reviewService = factory.getReviewService();
		String reviewId = request.getParameter("review_id");
		try {
			reviewService.deleteReviewById(Integer.parseInt(reviewId));
			response.sendRedirect(GO_TO_REVIEWS_PAGE);
		} catch (NumberFormatException | ServiceException e) {
			log.error(e);
			response.sendRedirect(GO_TO_ERROR_PAGE);
		}
		
	}

}
