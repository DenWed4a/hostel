package com.epam.ds.hostel.service;

import java.util.List;


import com.epam.ds.hostel.entity.Review;
import com.epam.ds.hostel.service.exception.ServiceException;

public interface ReviewService {
	void addNewReview(Review review) throws ServiceException;
	void updateReview(Review review) throws ServiceException;
	void deleteReview(Review review) throws ServiceException;
	List<Review> getAllReviews() throws ServiceException;
	Review findReview(int id) throws ServiceException;
	

}
