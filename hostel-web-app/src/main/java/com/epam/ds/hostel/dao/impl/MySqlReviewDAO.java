package com.epam.ds.hostel.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.ds.hostel.dao.ReviewDAO;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPool;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPoolException;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.Review;
import com.epam.ds.hostel.entity.status.EntityStatus.ReviewStatus;

public class MySqlReviewDAO implements ReviewDAO {
	private ConnectionPool cp = ConnectionPool.getInstance();
	private final static String ADD_NEW_REVIEW = "INSERT INTO reviews "
			+ "(confirmed_requests_booking_request_id, mark, text, date) " + "VALUES (?,?,?,?,?)";
	private final static String UPDATE_REVIEW = "UPDATE reviews SET mark=?, text=?, moderator_id=?, responce_to_reviews=?, status=? WHERE id=?";
	private final static String DELETE_REVIEW = "UPDATE reviews SET status=3 WHERE id=?";
	private final static String GET_ALL_REVIEWS = "SELECT * FROM reviews";
	private final static String GET_REVIEW_BY_ID = "SELECT * FROM reviews WHERE id=?";
	private final static String ID = "id";
	private final static String CONFIRMED_REQUEST_ID = "confirmed_requests_booking_request_id";
	private final static String MODERATOR_ID = "moderator_id";
	private final static String MARK = "mark";
	private final static String TEXT = "text";
	private final static String DATE = "date";
	private final static String RESPONCE_TO_REVIEW = "respose_to_reviews";
	private final static String STATUS = "status";
	
	
	@Override
	public void addNewReview(Review review) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(ADD_NEW_REVIEW);
			pst.setInt(1, review.getConfirmedRequestId());
			pst.setDouble(2, review.getMark());
			pst.setString(3, review.getText());
			pst.setDate(4, review.getDate());
			
			pst.executeUpdate();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				cp.closeConnection(con, pst);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}

	}

	@Override
	public void updateReview(Review review) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(UPDATE_REVIEW);
			pst.setDouble(1, review.getMark());
			pst.setString(2, review.getText());
			pst.setInt(3, review.getModeratorId());
			pst.setString(4, review.getResponceToReview());
			pst.setInt(5, review.getStatus().getTitle());
			pst.setInt(6, review.getId());
			pst.executeUpdate();
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				cp.closeConnection(con, pst);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}

	}

	@Override
	public void deleteReview(Review review) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(DELETE_REVIEW);
			pst.setInt(1, review.getId());		
			pst.executeUpdate();
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				cp.closeConnection(con, pst);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}

	}

	

	@Override
	public List<Review> getAllReviews() throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		List<Review> result = new ArrayList<>();
		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(GET_ALL_REVIEWS);
			resultSet = pst.executeQuery();
			while (resultSet.next()){
				int id = resultSet.getInt(ID);
				int confirmedRequestId = resultSet.getInt(CONFIRMED_REQUEST_ID);
				int moderatorId = resultSet.getInt(MODERATOR_ID);
				double mark = resultSet.getDouble(MARK);
				String text = resultSet.getString(TEXT);
				Date date = resultSet.getDate(DATE);
				String responceToReview = resultSet.getString(RESPONCE_TO_REVIEW);
				int status = resultSet.getInt(STATUS);
				
				Review review = new Review();
				review.setId(id);
				review.setConfirmedRequestId(confirmedRequestId);
				review.setModeratorId(moderatorId);
				review.setMark(mark);
				review.setText(text);
				review.setDate(date);
				review.setResponceToReview(responceToReview);
				review.setStatus(ReviewStatus.values()[status]);
				result.add(review);				
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		}finally {
			try {
				cp.closeConnection(con, pst, resultSet);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
		return result;
	}

	@Override
	public Review findReview(int id) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		Review review = null;
		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(GET_REVIEW_BY_ID);
			pst.setInt(1, id);
			resultSet = pst.executeQuery();
			while (resultSet.next()){
				
				int confirmedRequestId = resultSet.getInt(CONFIRMED_REQUEST_ID);
				int moderatorId = resultSet.getInt(MODERATOR_ID);
				double mark = resultSet.getDouble(MARK);
				String text = resultSet.getString(TEXT);
				Date date = resultSet.getDate(DATE);
				String responceToReview = resultSet.getString(RESPONCE_TO_REVIEW);
				int status = resultSet.getInt(STATUS);
				
				review = new Review();
				review.setId(id);
				review.setConfirmedRequestId(confirmedRequestId);
				review.setModeratorId(moderatorId);
				review.setMark(mark);
				review.setText(text);
				review.setDate(date);
				review.setResponceToReview(responceToReview);
				review.setStatus(ReviewStatus.values()[status]);							
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		}finally {
			try {
				cp.closeConnection(con, pst, resultSet);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
		return review;
	}

}
