package com.epam.ds.controller.impl.gotocommand;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;

import com.epam.ds.controller.Command;
import com.epam.ds.hostel.entity.Review;
import com.epam.ds.hostel.service.ReviewService;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.exception.ServiceException;

public class GoToReviewForm implements Command{
	private final static Logger log = Logger.getLogger(GoToReviewForm.class);
	private final static String GO_TO_REVIEW_FORM = "/WEB-INF/jsp/reviewFormPage.jsp";
	private final static String GO_TO_ERROR_PAGE = "Controller?command=GO_TO_ERROR_PAGE";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		ReviewService reviewService = factory.getReviewService();
		String confirmedRequestId = request.getParameter("bk_request_id");
		request.setAttribute("bk_request_id", confirmedRequestId);
		String button = request.getParameter("button");
		request.setAttribute("button", button);
		
		String reviewId = request.getParameter("review_id");
		
		try {
			if(reviewId!=null) {		
				Review review = reviewService.findReview(Integer.parseInt(reviewId));
				String topic = review.getTopic();
				String text = review.getText();
				String answer = review.getResponceToReview();
				double mark = review.getMark();
				request.setAttribute("topic_edit", topic);
				request.setAttribute("text_edit", text);
				request.setAttribute("mark_edit", mark);
				request.setAttribute("it_edit", "true");
				request.setAttribute("review_id", reviewId);
				request.setAttribute("admins_responce", answer);							
				
			}
				
			} catch (NumberFormatException e) {
				log.error(e);
				response.sendRedirect(GO_TO_ERROR_PAGE);
			} catch (ServiceException e) {
				log.error(e);
				response.sendRedirect(GO_TO_ERROR_PAGE);
			}
		RequestDispatcher dispatcher = request.getRequestDispatcher(GO_TO_REVIEW_FORM);
		dispatcher.forward(request, response);
			
		}
		
}
	