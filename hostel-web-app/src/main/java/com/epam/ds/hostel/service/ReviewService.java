package com.epam.ds.hostel.service;

import java.util.List;
import java.util.Map;


import com.epam.ds.hostel.entity.Review;
import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.service.exception.ServiceException;

public interface ReviewService {
	void addNewReview(Review review) throws ServiceException;
	void addResponce(Review review) throws ServiceException;
	void updateReview(Review review) throws ServiceException;
	void deleteReview(Review review) throws ServiceException;
	void deleteReviewById(int reviewId) throws ServiceException;
	List<Review> getAllReviews() throws ServiceException;
	Review findReview(int id) throws ServiceException;
	Map<Review, User> getReviewsWithUsers() throws ServiceException;
	

}
