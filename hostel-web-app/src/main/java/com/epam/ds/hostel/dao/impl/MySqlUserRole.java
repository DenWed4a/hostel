package com.epam.ds.hostel.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.ds.hostel.dao.UserRoleDAO;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPool;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPoolException;
import com.epam.ds.hostel.dao.exception.DAOException;

public class MySqlUserRole implements UserRoleDAO {
	private ConnectionPool cp = ConnectionPool.getInstance();
	private final static String GET_ROLE_BY_ID = "SELECT * FROM ROLES WHERE ID = ?";

	public String getRole(int id) throws DAOException {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		String role;

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(GET_ROLE_BY_ID);
			pst.setInt(1, id);
			resultSet = pst.executeQuery();
			resultSet.next();
			role = resultSet.getString(2);
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

		return role;

	}

}
