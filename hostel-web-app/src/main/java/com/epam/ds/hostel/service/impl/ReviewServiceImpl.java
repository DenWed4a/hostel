package com.epam.ds.hostel.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.ds.hostel.dao.BookingRequestDAO;
import com.epam.ds.hostel.dao.DAOFactory;
import com.epam.ds.hostel.dao.ReviewDAO;
import com.epam.ds.hostel.dao.UserDAO;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.Review;
import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.service.ReviewService;
import com.epam.ds.hostel.service.exception.ServiceException;

public class ReviewServiceImpl implements ReviewService{

	@Override
	public void addNewReview(Review review) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		ReviewDAO reviewDAO = factory.getMySqlReviewDAO();
		
		try {
			reviewDAO.addNewReview(review);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public void updateReview(Review review) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		ReviewDAO reviewDAO = factory.getMySqlReviewDAO();
		
		try {
			reviewDAO.updateReview(review);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public void deleteReview(Review review) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		ReviewDAO reviewDAO = factory.getMySqlReviewDAO();
		
		try {
			reviewDAO.deleteReview(review);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public List<Review> getAllReviews() throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		ReviewDAO reviewDAO = factory.getMySqlReviewDAO();
		List<Review> result;
		try {
			 result = reviewDAO.getAllReviews();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public Review findReview(int id) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		ReviewDAO reviewDAO = factory.getMySqlReviewDAO();
		Review review;
		try {
			review = reviewDAO.findReview(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return review;
	}

	@Override
	public void addResponce(Review review) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		ReviewDAO reviewDAO = factory.getMySqlReviewDAO();
		
		try {
			reviewDAO.addResponce(review);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public Map<Review, User> getReviewsWithUsers() throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		ReviewDAO reviewDAO = factory.getMySqlReviewDAO();
		UserDAO userDAO = factory.getMySqlUserDAO();
		BookingRequestDAO bookingRequestDAO = factory.getMySqlBookingRequestDAO();
		User user;
		int userId;
		
		Map<Review, User> result = new HashMap<>();
		try {
			List<Review> reviews = reviewDAO.getAllReviews();
			for(Review element : reviews) {
				userId = bookingRequestDAO.getBookingRequestById(element.getConfirmedRequestId()).getClientId();
				user = userDAO.findById(userId);
				result.put(element, user);
			}
			
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return result;
	}

	@Override
	public void deleteReviewById(int reviewId) throws ServiceException  {
		DAOFactory factory = DAOFactory.getInstance();
		ReviewDAO reviewDAO = factory.getMySqlReviewDAO();
		try {
			reviewDAO.deleteReviewById(reviewId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}
	
}
