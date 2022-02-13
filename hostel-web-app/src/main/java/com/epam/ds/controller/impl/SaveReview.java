package com.epam.ds.controller.impl;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.ds.controller.Command;
import com.epam.ds.hostel.entity.Review;
import com.epam.ds.hostel.service.ReviewService;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.exception.ServiceException;

public class SaveReview implements Command{
	private final static Logger log = Logger.getLogger(SaveReview.class);
	private final static String GO_TO_REVIEWS_PAGE = "Controller?command=GO_TO_REVIEWS_PAGE";
	private final static String GO_TO_ERROR_PAGE = "Controller?command=GO_TO_ERROR_PAGE";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String topic = request.getParameter("topic");
		String text = request.getParameter("text");
		String confirmed_request_id = request.getParameter("confirmed_request_id");
		String mark = request.getParameter("mark");
		ServiceFactory factory = ServiceFactory.getInstance();
		ReviewService reviewService = factory.getReviewService();
		String reviewId = request.getParameter("id");
		String button = request.getParameter("button");
		String adminId = request.getParameter("administrator_id");
		String answerToReview = request.getParameter("answer");
		Review review ;
		
		try {
			
			if(reviewId!=null && button==null) {				
				review = reviewService.findReview(Integer.parseInt(reviewId));
				review.setTopic(topic);
				review.setText(text);
				review.setMark(Double.parseDouble(mark));
				review.setDate(new Date(Calendar.getInstance().getTime().getTime()));
				reviewService.updateReview(review);
			}else if(button!=null) {
				review = reviewService.findReview(Integer.parseInt(reviewId));
				review.setModeratorId(Integer.parseInt(adminId));
				review.setResponceToReview(answerToReview);
				reviewService.addResponce(review);								
			}else {				
				review = new Review();
				review.setConfirmedRequestId(Integer.parseInt(confirmed_request_id));
				review.setTopic(topic);
				review.setText(text);
				review.setMark(Double.parseDouble(mark));
				review.setDate(new Date(Calendar.getInstance().getTime().getTime()));
				reviewService.addNewReview(review);
			}
			
			response.sendRedirect(GO_TO_REVIEWS_PAGE);
		} catch (ServiceException | NumberFormatException e) {
			log.error(e);
			e.printStackTrace();
			response.sendRedirect(GO_TO_ERROR_PAGE);
		}
		
		
	}

}
