package com.epam.ds.hostel.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.ds.hostel.dao.BlackListDAO;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPool;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPoolException;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.BlackListUser;

public class MySqlBlackListDAO implements BlackListDAO {
	
	private ConnectionPool cp = ConnectionPool.getInstance();
	
	private final static String ADD_USER = "INSERT INTO black_list (users_id, reason, since_date) VALUES(?,?, CURRENT_DATE())";
	private final static String DELETE_FROM_BLACK_LIST = "DELETE FROM black_list WHERE users_id = ?";
	private final static String CHECK_USER = "SELECT * FROM black_list WHERE users_id = ?";
	private final static String GET_BLACK_LIST = "SELECT * FROM black_list";

	@Override
	public void addToBlackList(int userID, String reason) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(ADD_USER);
			pst.setInt(1, userID);
			pst.setString(2, reason);
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
	public void removeFromBlackList(int userId) throws DAOException {

		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(DELETE_FROM_BLACK_LIST);
			pst.setInt(1, userId);
			pst.executeUpdate();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException();
		} finally {
			try {
				cp.closeConnection(con, pst);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}

		}

	}

	@Override
	public boolean checkBlackList(int userID) throws DAOException {
	
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		boolean result = false;

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(CHECK_USER);
			pst.setInt(1, userID);
			resultSet = pst.executeQuery();
			if (resultSet.next()) {
				result = true;
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


	@Override
	public List<BlackListUser> allBlackListUsers() throws DAOException {
		List<BlackListUser> result = new ArrayList<>();;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		BlackListUser bListUser;

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(GET_BLACK_LIST);
			resultSet = pst.executeQuery();
			while (resultSet.next()) {
				int userId = resultSet.getInt(1);
				String reason = resultSet.getString(2);
				Date sinceDate = resultSet.getDate(3);
				bListUser = new BlackListUser();
				bListUser.setUserId(userId);
				bListUser.setReason(reason);
				bListUser.setSinceDate(sinceDate);
				result.add(bListUser);
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
