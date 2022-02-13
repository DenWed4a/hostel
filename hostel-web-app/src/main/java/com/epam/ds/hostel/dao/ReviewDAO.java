package com.epam.ds.hostel.dao;

import java.util.List;

import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.Review;

public interface ReviewDAO {
	void addNewReview(Review review) throws DAOException;
	void addResponce(Review review) throws DAOException;
	void updateReview(Review review) throws DAOException;
	void deleteReview(Review review) throws DAOException;
	void deleteReviewById(int reviewId) throws DAOException;
	List<Review> getAllReviews() throws DAOException;
	Review findReview(int id) throws DAOException;

}
