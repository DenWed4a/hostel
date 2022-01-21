package com.epam.ds.hostel.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.epam.ds.hostel.dao.ConfirmedRequestDAO;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPool;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPoolException;
import com.epam.ds.hostel.dao.creator.ConfirmedRequestCreator;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.BookingRequest;
import com.epam.ds.hostel.entity.ConfirmedRequest;
import com.epam.ds.hostel.entity.criteria.Criteria;

public class MySqlConfirmedRequestDAO implements ConfirmedRequestDAO {
	
	private ConnectionPool cp = ConnectionPool.getInstance();

	private final static String addNewRequest = "INSERT INTO confirmed_requests (bills_id, administrator_id, confirmation_date, status, date_of_payment, booking_request_id) VALUES (?,?,?,?,?,?)";
	private final static String getAllRequests = "SELECT * FROM confirmed_requests";
	private final static String findRequestByAdministator = "SELECT * FROM conrirmed_requests WHERE (administrator_id = ?)";
	private final static String addToPlaceHasRequest = "INSERT INTO bed_place_has_confirmed_request (bed_place_id, booking_request_id) VALUES (?,?)";
	private final static String addToLockerHasRequest = "INSERT INTO lockers_has_confirmed_request (lokers_id, booking_request_id) VALUES (?,?)";

	@Override
	public void addNewRequest(ConfirmedRequest request, int[] bedPlaceId, int[] lockerId) throws DAOException {
		
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			pst = con.prepareStatement(addNewRequest);
			pst.setInt(1, request.getBillId());
			pst.setInt(2, request.getAdministratorId());
			pst.setDate(3, request.getConfirmationDate());
			pst.setInt(4, request.getStatus());
			pst.setDate(5, request.getDateOfPayment());
			pst.setInt(6, request.getId());
			pst.executeUpdate();

			try {
				pst.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}

			pst = con.prepareStatement(addToPlaceHasRequest);

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

			pst = con.prepareStatement(addToLockerHasRequest);
			for (int i = 0; i < lockerId.length; i++) {
				pst.setInt(1, lockerId[i]);
				pst.setInt(2, request.getId());
				pst.executeUpdate();
			}

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
				cp.closeConnection(con, pst);
			} catch (ConnectionPoolException e) {
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
			pst = con.prepareStatement(getAllRequests);
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
			pst = con.prepareStatement(findRequestByAdministator);
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
	public void updateConfirmedRequest(int id, Criteria criteria) throws DAOException {
	
		Connection con = null;
		PreparedStatement pst = null;
		String request;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("UPDATE CONFIRMED_REQUESTS SET ");
		for (Map.Entry<String, Object> pair : criteria.getCriteria().entrySet()) {
			stringBuilder.append(stringBuilder.append(pair.getKey()).append("=").append(pair.getValue()).append(","));
		}
		stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
		stringBuilder.append(" WHERE ADMINIST = ").append(id);
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

}
