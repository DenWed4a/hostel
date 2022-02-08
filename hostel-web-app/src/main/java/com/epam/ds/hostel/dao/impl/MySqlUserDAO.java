package com.epam.ds.hostel.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.epam.ds.hostel.dao.UserDAO;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPool;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPoolException;
import com.epam.ds.hostel.dao.creator.UserCreator;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.entity.UserDetail;

public class MySqlUserDAO implements UserDAO {
	private ConnectionPool cp = ConnectionPool.getInstance();
	private final static String GET_USER_DATA = "SELECT * FROM USERS LEFT OUTER JOIN USERS_DETAILS ON USER_ID = ID";
	private final static String ADD_NEW_USER = "INSERT INTO USERS (login, roles_id, status, password) VALUES (?,?,?,?)";
	private final static String ADD_USER_DETAIL = "INSERT INTO USERS_DETAILS VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String UPDATE_USER_DETAIL = "UPDATE USERS_DETAILS SET name=?, surname=?, phone_number=? , email=?, rating=?, passport_number=?, nationality=?,"
			+ "date_of_birth=?, passport_date_of_issue=?, passport_date_of_expire=?, address=?, image=? WHERE(USER_ID = ?)";
	private final static String UPDATE_USER = "UPDATE USERS SET status=?, roles_id=? WHERE (ID = ?)";
	private final static String GET_USER_BY_ID = "SELECT * FROM USERS LEFT OUTER JOIN USERS_DETAILS ON USER_ID = ID WHERE (ID = ?)";
	private final static String GET_USER_BY_LOGIN = "SELECT * FROM USERS LEFT OUTER JOIN USERS_DETAILS ON USER_ID = ID WHERE (LOGIN = ?)";
	private final static String DELETE_USER = "UPDATE USERS SET STATUS = ? WHERE (ID = ?)";
	private final static String SELECT_FROM_USERS = "SELECT * FROM USERS";

	@Override
	public List<User> findAllUsers() throws DAOException {

		PreparedStatement pst = null;
		Connection con = null;
		ResultSet resultSet = null;
		List<User> userList = new ArrayList<User>();

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(GET_USER_DATA);
			resultSet = pst.executeQuery();

			while (resultSet.next()) {
				User user = UserCreator.getInstance().create(resultSet);
				userList.add(user);
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

		return userList;
	}

	@Override
	public int saveUser(User user) throws DAOException {
		int userId;
		int isSaved = 0;
		UserDetail userDetail = user.getDetail();
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet resultSet = null;

		try {

			con = cp.takeConnection();
			con.setAutoCommit(false);
			pst = con.prepareStatement(ADD_NEW_USER, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, user.getLogin());
			pst.setInt(2, user.getRole().getTitle());
			pst.setInt(3, user.getStatus());
			pst.setInt(4, user.getPassword().hashCode());
			try {
				isSaved = pst.executeUpdate();
				resultSet = pst.getGeneratedKeys();
				resultSet.next();
				userId = (resultSet.getInt(1));
			} catch (SQLIntegrityConstraintViolationException e) {
				throw new DAOException(e);
			}

			try {
				pst.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}

			pst = con.prepareStatement(ADD_USER_DETAIL);
			pst.setInt(1, userId);
			pst.setString(2, userDetail.getName());
			pst.setString(3, userDetail.getSurname());
			pst.setString(4, userDetail.getPhoneNumber());
			pst.setString(5, userDetail.getEmail());
			pst.setDouble(6, userDetail.getRating());
			pst.setString(7, userDetail.getPassportNumber());
			pst.setString(8, userDetail.getNationality());
			pst.setDate(9, userDetail.getDateOfBirth());
			pst.setDate(10, userDetail.getPassportDateOfIssue());
			pst.setDate(11, userDetail.getPassportDateOfExpire());
			pst.setString(12, userDetail.getAddress());
			pst.setString(13, userDetail.getImage());
			pst.executeUpdate();

			con.commit();

		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			try {
				System.out.println("roll back");
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

		return isSaved;
	}

	@Override
	public User findById(int id) throws DAOException {
		User user = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		Connection con = null;

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(GET_USER_BY_ID);
			pst.setInt(1, id);
			resultSet = pst.executeQuery();
			resultSet.next();
			user = UserCreator.getInstance().create(resultSet);

		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				cp.closeConnection(con, pst, resultSet);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}

		}

		return user;
	}

	@Override
	public User findByLogin(String login) throws DAOException {
		User user = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		Connection con = null;

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(GET_USER_BY_LOGIN);
			pst.setString(1, login);
			resultSet = pst.executeQuery();
			resultSet.next();
			user = UserCreator.getInstance().create(resultSet);

		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				cp.closeConnection(con, pst, resultSet);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}

		}

		return user;
	}

	@Override
	public void deleteUser(int id) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = ConnectionPool.getInstance().takeConnection();
			pst = con.prepareStatement(DELETE_USER);
			pst.setInt(1, 0);
			pst.setInt(2, id);
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
	public void updateUser(User user) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(UPDATE_USER);
			pst.setInt(1, user.getStatus());
			pst.setInt(2, user.getRole().getTitle());
			pst.setInt(3, user.getId());
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
	public void updateUserDetail(User user) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		UserDetail userDetail = user.getDetail();

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(UPDATE_USER_DETAIL);
			pst.setString(1, userDetail.getName());
			pst.setString(2, userDetail.getSurname());
			pst.setString(3, userDetail.getPhoneNumber());
			pst.setString(4, userDetail.getEmail());
			pst.setDouble(5, userDetail.getRating());
			pst.setString(6, userDetail.getPassportNumber());
			pst.setString(7, userDetail.getNationality());
			pst.setDate(8, userDetail.getDateOfBirth());
			pst.setDate(9, userDetail.getPassportDateOfIssue());
			pst.setDate(10, userDetail.getPassportDateOfExpire());
			pst.setString(11, userDetail.getAddress());
			pst.setString(12, user.getDetail().getImage());
			pst.setInt(13, user.getId());
			
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
	public boolean validation(String login, String password) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		boolean result = false;
		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(SELECT_FROM_USERS);
			resultSet = pst.executeQuery();
			while (resultSet.next()) {
				String loginDB = resultSet.getString(2);
				if (loginDB.equals(login)) {
					int passwordDB = resultSet.getInt(3);
					if (passwordDB == password.hashCode()) {
						result = true;
					}
				}
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

}
