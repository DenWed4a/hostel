package com.epam.ds.controller.impl.gotocommand;

import java.io.IOException;
import java.util.HashMap;

import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.ds.controller.Command;
import com.epam.ds.hostel.entity.Review;
import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.service.ReviewService;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.UserService;
import com.epam.ds.hostel.service.exception.ServiceException;

public class GoToReviewsPage implements Command {
	private final static Logger log = Logger.getLogger(GoToReviewsPage.class);
	private final static String GO_TO_REVIEWS_PAGE = "/WEB-INF/jsp/reviewsPage.jsp";
	private final static String GO_TO_ERROR_PAGE = "Controller?command=GO_TO_ERROR_PAGE";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		ReviewService reviewService = factory.getReviewService();
		UserService userService = factory.getUserService();
			
		Set<User> admins;
		
		Map<Review, User> reviews = new HashMap<>();
		try {

			reviews = reviewService.getReviewsWithUsers();
			admins = userService.getAdmins();
			request.setAttribute("admins", admins);
			request.setAttribute("reviews", reviews);
			RequestDispatcher dispatcher = request.getRequestDispatcher(GO_TO_REVIEWS_PAGE);
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
			log.error(e);
			e.printStackTrace();
			response.sendRedirect(GO_TO_ERROR_PAGE);
		}
	}

}
