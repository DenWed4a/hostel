package com.epam.ds.hostel.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.epam.ds.hostel.dao.ConfirmedRequestDAO;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPool;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPoolException;
import com.epam.ds.hostel.dao.creator.ConfirmedRequestCreator;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.ConfirmedRequest;


public class MySqlConfirmedRequestDAO implements ConfirmedRequestDAO {

	private ConnectionPool cp = ConnectionPool.getInstance();

	private final static String ADD_NEW_REQUEST = "INSERT INTO confirmed_requests (bills_id, administrator_id, confirmation_date, date_of_payment, booking_request_id) VALUES (?,?,?,?,?)";
	private final static String GET_ALL_REQUESTS = "SELECT * FROM confirmed_requests";
	private final static String FIND_REQUEST_BY_ADMINISTRATOR = "SELECT * FROM confirmed_requests WHERE (administrator_id = ?)";
	private final static String FIND_REQUEST_BY_ID = "SELECT * FROM confirmed_requests WHERE (booking_request_id = ?)";
	private final static String ADD_TO_PLACE_HAS_REQUEST = "INSERT INTO bed_place_has_confirmed_request (bed_place_id, confirmed_request_id) VALUES (?,?)";
	private final static String ADD_TO_LOCKER_HAS_REQUEST = "INSERT INTO lockers_has_confirmed_requests (lockers_id, confirmed_request_id) VALUES (?,?)";
	private final static String SET_CONFIRMED_TO_STATUS = "UPDATE  booking_requests SET status = 1 WHERE (id = ?)";
	private final static String UPDATE_CONFIRMED_REQUEST = "UPDATE confirmed_requests SET status = ? WHERE (booking_request_id = ?)";

	@Override
	public void addNewRequest(ConfirmedRequest request, int[] bedPlaceId, int[] lockerId) throws DAOException {

		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			pst = con.prepareStatement(ADD_NEW_REQUEST);
			pst.setInt(1, request.getBillId());
			pst.setInt(2, request.getAdministratorId());
			pst.setDate(3, request.getConfirmationDate());
			// pst.setInt(4, request.getStatus().getTitle());
			pst.setDate(4, request.getDateOfPayment());
			pst.setInt(5, request.getId());
			pst.executeUpdate();

			try {
				pst.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}

			pst = con.prepareStatement(ADD_TO_PLACE_HAS_REQUEST);

			for (int i = 0; i < bedPlaceId.length; i++) {
				pst.setInt(1, bedPlaceId[i]);
				pst.setInt(2, request.getId());
				pst.executeUpdate();
			}

			try {
				pst.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
			if (lockerId != null) {
				pst = con.prepareStatement(ADD_TO_LOCKER_HAS_REQUEST);
				for (int i = 0; i < lockerId.length; i++) {
					pst.setInt(1, lockerId[i]);
					pst.setInt(2, request.getId());
					pst.executeUpdate();
				}

				try {
					pst.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}

			pst = con.prepareStatement(SET_CONFIRMED_TO_STATUS);
			pst.setInt(1, request.getId());
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
				cp.closeConnection(con, pst);
			} catch (ConnectionPoolException | SQLException e) {
				throw new DAOException(e);
			}

		}
	}

	@Override
	public List<ConfirmedRequest> findAllRequests() throws DAOException {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		List<ConfirmedRequest> result = new ArrayList<>();
		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(GET_ALL_REQUESTS);
			resultSet = pst.executeQuery();
			ConfirmedRequest request;
			while (resultSet.next()) {
				request = ConfirmedRequestCreator.getInstance().create(resultSet);
				result.add(request);
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
	public List<ConfirmedRequest> findConfirmedRequestByAdmin(int adminId) throws DAOException {
		List<ConfirmedRequest> result = new ArrayList<>();

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		ConfirmedRequestCreator creator = ConfirmedRequestCreator.getInstance();
		ConfirmedRequest request;
		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(FIND_REQUEST_BY_ADMINISTRATOR);
			pst.setInt(1, adminId);
			resultSet = pst.executeQuery();
			while (resultSet.next()) {
				request = creator.create(resultSet);
				result.add(request);
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
	public void updateConfirmedRequest(ConfirmedRequest cRequest) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		int status = cRequest.getStatus().getTitle();

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(UPDATE_CONFIRMED_REQUEST);
			pst.setInt(1, status);
			pst.setInt(2, cRequest.getId());
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
	public ConfirmedRequest findConfirmedRequestById(int id) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		ConfirmedRequest result = null;

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(FIND_REQUEST_BY_ID);
			pst.setInt(1, id);
			resultSet = pst.executeQuery();
			
			if(resultSet.next()) {
			result = ConfirmedRequestCreator.getInstance().create(resultSet);
			}

		} catch (ConnectionPoolException | SQLException e) {
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



}
