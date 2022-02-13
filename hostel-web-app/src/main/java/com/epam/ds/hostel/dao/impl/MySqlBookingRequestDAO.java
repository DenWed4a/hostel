package com.epam.ds.hostel.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.ds.hostel.dao.BookingRequestDAO;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPool;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPoolException;
import com.epam.ds.hostel.dao.creator.BookingRequestCreator;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.BookingRequest;
import com.epam.ds.util.BillTotalCalculator;

public class MySqlBookingRequestDAO implements BookingRequestDAO {

	private ConnectionPool cp = ConnectionPool.getInstance();

	private final static String ADD_NEW_REQUEST = "INSERT INTO BOOKING_REQUESTS (start_date, end_date, number_of_places, number_of_lockers, clients_id) "
			+ "VALUES (?,?,?,?,?)";
	private final static String FIND_BOOKING_REQUEST_BY_USER = "SELECT * FROM booking_requests WHERE (clients_id = ?)";
	private final static String FIND_UNCONFIRMED_REQUESTS = "SELECT * FROM booking_requests WHERE status = 0";
	private final static String DELETE_BOOKING_REQUEST = "UPDATE booking_requests SET status = 2 WHERE (id = ?)";
	private final static String FIND_ALL_REQUESTS = "SELECT * FROM booking_requests";
	private final static String GET_REQUEST_BY_ID = "SELECT * FROM booking_requests WHERE (id = ?)";
	private final static String ADD_NEW_BILL = "INSERT INTO bills (total_amount, booking_request_id) VALUES (?,?)";
	private final static String UPDATE_BOOKING_REQUEST = "UPDATE booking_requests "
			+ "SET start_date=?,  end_date=?, number_of_places=?, number_of_lockers=?  WHERE id=?";
	private final static String UPDATE_BILL = "UPDATE bills SET total_amount = ? WHERE booking_request_id=?";

	@Override
	public void addNewRequest(BookingRequest request) throws DAOException {
		int requestId;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		BillTotalCalculator billTotalCalculator;
		Date startDate = request.getStartDate();
		Date endDate = request.getEndDate();
		int bedPlaces = request.getNumberOfPlaces();
		int lockers = request.getNumberOfLockers();

		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			pst = con.prepareStatement(ADD_NEW_REQUEST, Statement.RETURN_GENERATED_KEYS);
			pst.setDate(1, startDate);
			pst.setDate(2, endDate);
			pst.setInt(3, bedPlaces);
			pst.setInt(4, lockers);
			// pst.setInt(5, request.getStatus());
			pst.setInt(5, request.getClientId());
			pst.executeUpdate();

			resultSet = pst.getGeneratedKeys();
			resultSet.next();
			requestId = resultSet.getInt(1);

			try {
				pst.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
			pst = con.prepareStatement(ADD_NEW_BILL);
			billTotalCalculator = new BillTotalCalculator();
			double totalAmount = billTotalCalculator.getTotal(startDate, endDate, bedPlaces, lockers);
			pst.setDouble(1, totalAmount);
			pst.setInt(2, requestId);
			pst.executeUpdate();

			con.commit();

		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {

				throw new DAOException("Error in rollback method", e1);
			}
			throw new DAOException(e);
		} finally {
			try {
				cp.closeConnection(con, pst, resultSet);
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
			pst = con.prepareStatement(FIND_ALL_REQUESTS);

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
	public List<BookingRequest> findBookingRequestsByUserId(int userId) throws DAOException {
		List<BookingRequest> result = new ArrayList<BookingRequest>();

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		BookingRequestCreator creator = BookingRequestCreator.getInstance();
		BookingRequest bRequest;
		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(FIND_BOOKING_REQUEST_BY_USER);
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
		try {
			;
			con = cp.takeConnection();
			pst = con.prepareStatement(FIND_UNCONFIRMED_REQUESTS);

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
	public void updateBookingRequest(BookingRequest bookingRequest) throws DAOException {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		BillTotalCalculator billTotalCalculator;
		Date startDate = bookingRequest.getStartDate();
		Date endDate = bookingRequest.getEndDate();
		int bedPlaces = bookingRequest.getNumberOfPlaces();
		int lockers = bookingRequest.getNumberOfLockers();
		int brId = bookingRequest.getId();

		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			pst = con.prepareStatement(UPDATE_BOOKING_REQUEST);
			pst.setDate(1, startDate);
			pst.setDate(2, endDate);
			pst.setInt(3, bedPlaces);
			pst.setInt(4, lockers);
			pst.setInt(5, brId);
			pst.executeUpdate();

			try {
				pst.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}

			pst = con.prepareStatement(UPDATE_BILL);
			billTotalCalculator = new BillTotalCalculator();
			double totalAmount = billTotalCalculator.getTotal(startDate, endDate, bedPlaces, lockers);
			pst.setDouble(1, totalAmount);
			pst.setInt(2, brId);
			pst.executeUpdate();
			con.commit();

		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {

				throw new DAOException("Error in rollback method", e1);
			}
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.setAutoCommit(true);
				}
				cp.closeConnection(con, pst, resultSet);
			} catch (ConnectionPoolException | SQLException e) {
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
			pst = con.prepareStatement(DELETE_BOOKING_REQUEST);
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

	@Override
	public BookingRequest getBookingRequestById(int id) throws DAOException {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		BookingRequestCreator creator = BookingRequestCreator.getInstance();
		BookingRequest bRequest;
		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(GET_REQUEST_BY_ID);
			pst.setInt(1, id);
			resultSet = pst.executeQuery();
			resultSet.next();
			bRequest = creator.create(resultSet);

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

		return bRequest;
	}

}
