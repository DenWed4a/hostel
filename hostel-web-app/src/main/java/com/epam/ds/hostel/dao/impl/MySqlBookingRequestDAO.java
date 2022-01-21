package com.epam.ds.hostel.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.epam.ds.hostel.dao.BookingRequestDAO;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPool;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPoolException;
import com.epam.ds.hostel.dao.creator.BookingRequestCreator;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.BookingRequest;
import com.epam.ds.hostel.entity.criteria.Criteria;

public class MySqlBookingRequestDAO implements BookingRequestDAO {
	
	private ConnectionPool cp = ConnectionPool.getInstance();

	private final static String addNewRequest = "INSERT INTO BOOKING_REQUESTS (start_date, end_date, number_of_places, number_of_lockers, clients_id) "
			+ "VALUES (?,?,?,?,?)";
	private final static String findBookingRequestByUser = "SELECT * FROM booking_requests WHERE (clients_id = ?)";
	private final static String findUnconfirmedRequests = "SELECT * FROM booking_requests WHERE status = 0";
	private final static String deleteBookingRequest = "UPDATE booking_requests SET status = -1 WHERE (id = ?)";
	private final static String findAllRequests = "SELECT * FROM booking_requests";

	@Override
	public void addNewRequest(BookingRequest request) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(addNewRequest);
			pst.setDate(1, request.getStartDate());
			pst.setDate(2, request.getEndDate());
			pst.setInt(3, request.getNumberOfPlaces());
			pst.setInt(4, request.getNumberOfLockers());
			// pst.setInt(5, request.getStatus());
			pst.setInt(5, request.getClientId());
			pst.executeUpdate();

		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
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
	public List<BookingRequest> findAllBookingRequest() throws DAOException {
		List<BookingRequest> result = new ArrayList<BookingRequest>();

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		BookingRequest bRequest;
		BookingRequestCreator creator = BookingRequestCreator.getInstance();
		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(findAllRequests);

			resultSet = pst.executeQuery();
			while (resultSet.next()) {
				bRequest = creator.create(resultSet);
				result.add(bRequest);
			}

		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				cp.closeConnection(con, pst, resultSet);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}

		}

		return result;

	}

	@Override
	public List<BookingRequest> findBookingRequest(int userId) throws DAOException {
		List<BookingRequest> result = new ArrayList<BookingRequest>();
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		BookingRequestCreator creator = BookingRequestCreator.getInstance();
		BookingRequest bRequest;
		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(findBookingRequestByUser);
			pst.setInt(1, userId);
			resultSet = pst.executeQuery();
			while (resultSet.next()) {
				bRequest = creator.create(resultSet);
				result.add(bRequest);
			}

		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				cp.closeConnection(con, pst, resultSet);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}

		}

		return result;
	}

	@Override
	public List<BookingRequest> findAllUnconfirmedRequests() throws DAOException {
		List<BookingRequest> result = new ArrayList<BookingRequest>();
	
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		BookingRequest bRequest;
		BookingRequestCreator creator = BookingRequestCreator.getInstance();
		try {;
			con = cp.takeConnection();
			pst = con.prepareStatement(findUnconfirmedRequests);

			resultSet = pst.executeQuery();
			while (resultSet.next()) {
				bRequest = creator.create(resultSet);
				result.add(bRequest);
			}

		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				cp.closeConnection(con, pst, resultSet);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}

		}

		return result;

	}

	@Override
	public void updateBookingRequest(int id, Criteria criteria) throws DAOException {
		
		Connection con = null;
		PreparedStatement pst = null;
		String request;
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("UPDATE BOOKING_REQUESTS SET ");
		
		for (Map.Entry<String, Object> pair : criteria.getCriteria().entrySet()) {
			stringBuilder.append(stringBuilder.append(pair.getKey()).append("=").append(pair.getValue()).append(","));
		}
		
		stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
		stringBuilder.append(" WHERE USER_ID = ").append(id);
		request = stringBuilder.toString();
		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(request);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
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
	public void deleteBookingRequest(int id) throws DAOException {
		
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(deleteBookingRequest);
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				cp.closeConnection(con, pst);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}

		}

	}

}
