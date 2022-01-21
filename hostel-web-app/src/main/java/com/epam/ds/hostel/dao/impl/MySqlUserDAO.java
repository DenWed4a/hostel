package com.epam.ds.hostel.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.epam.ds.hostel.bean.builder.BuilderFactory;
import com.epam.ds.hostel.dao.UserDAO;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPool;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPoolException;
import com.epam.ds.hostel.dao.creator.UserCreator;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.entity.UserDetail;
import com.epam.ds.hostel.entity.criteria.Criteria;

public class MySqlUserDAO implements UserDAO {
	private ConnectionPool cp = ConnectionPool.getInstance();
	private final static String getUserData = "SELECT * FROM USERS LEFT OUTER JOIN USERS_DETAILS ON USER_ID = ID";
	private final static String addNewUser = "INSERT INTO USERS (login, roles_id, status, password) VALUES (?,?,?,?)";
	private final static String addUserDetail = "INSERT INTO USERS_DETAILS VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String updateUserDetail =
	   "UPDATE USERS_DETAILS SET name=?, surname=?, phone_number=? , email=?, reiting=?, passport_number=?, nationality=?,"
	     + "date_of_birth=?, passport_date_of_issue=?, passport_date_of_expire=?, address=? "
	     + "WHERE(USER_ID = ?)";
	private final static String updateUser = "UPDATE USERS SET login=?, password=?,  status=?, roles_id=? WHERE (ID = ?)";
	private final static String getUserById = "SELECT * FROM USERS LEFT OUTER JOIN USERS_DETAILS ON USER_ID = ID WHERE (ID = ?)";
	private final static String getUserByLogin = "SELECT * FROM USERS LEFT OUTER JOIN USERS_DETAILS ON USER_ID = ID WHERE (LOGIN = ?)";
	private final static String deleteUser = "UPDATE USERS SET STATUS = ? WHERE (ID = ?)";
	private final static String selectFromUsers = "SELECT * FROM USERS";

	@Override
	public List<User> findAllUsers() throws DAOException {

		PreparedStatement pst = null;
		Connection con = null;
		ResultSet resultSet = null;
		List<User> userList = new ArrayList<User>();

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(getUserData);
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
	public void saveUser(User user) throws DAOException {
		int userId;
		UserDetail userDetail = user.getDetail();
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet resultSet = null;

		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			pst = con.prepareStatement(addNewUser, Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, user.getLogin());
			pst.setInt(2, user.getIdRole());
			pst.setInt(3, user.getStatus());
			pst.setInt(4, user.getPassword().hashCode());
			pst.executeUpdate();

			resultSet = pst.getGeneratedKeys();
			resultSet.next();
			userId = (resultSet.getInt(1));

			con.commit();

			try {
				pst.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}

			pst = con.prepareStatement(addUserDetail);
			pst.setInt(1, userId);
			pst.setString(2, userDetail.getName());
			pst.setString(3, userDetail.getSurname());
			pst.setString(4, userDetail.getPhoneNumber());
			pst.setString(5, userDetail.getEmail());
			pst.setDouble(6, userDetail.getReiting());
			pst.setString(7, userDetail.getPassportNumber());
			pst.setString(8, userDetail.getNationality());
			pst.setDate(9, userDetail.getDateOfBirth());
			pst.setDate(10, userDetail.getPassportDateOfIssue());
			pst.setDate(11, userDetail.getPassportDateOfExpire());
			pst.setString(12, userDetail.getAddress());
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
	public User findById(int id) throws DAOException {
		User user = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		Connection con = null;

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(getUserById);
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
			pst = con.prepareStatement(getUserByLogin);
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
			pst = con.prepareStatement(deleteUser);
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
	public void updateUser(int userId, User user) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		boolean needUpdate = false;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE  USERS SET ");
		if(user.getLogin()!=null) {
			sBuilder.append("login='").append(user.getLogin()).append("' ,");
			needUpdate = true;
		}
		if(user.getIdRole()!=0) {
			sBuilder.append("roles_id=").append(user.getIdRole()).append(" ,");
			needUpdate = true;
		}
		
		if(user.getStatus()!=0) {
			sBuilder.append("status=").append(user.getStatus()).append(" ,");
			needUpdate = true;
		}
		
		sBuilder.delete(sBuilder.length()-1, sBuilder.length());
		
		sBuilder.append("WHERE ID =").append(userId);
		System.out.println(sBuilder.toString());
		/*if(user.getPassword()!=null) {
			sBuilder.append("login=").append(user.getLogin()).append(",");
		}*/
		
		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(sBuilder.toString());
			if(needUpdate) {
			pst.executeUpdate();
			}
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
	public User findUserByCriteria(Criteria criteria) throws DAOException {
		User user = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		Connection con = null;
		int userId;
		StringBuilder stringBuilder = new StringBuilder();
		String request;
		stringBuilder.append("SELECT * FROM ").append(criteria.getTablehName().toUpperCase()).append(" WHERE ");
		for (Map.Entry<String, Object> pair : criteria.getCriteria().entrySet()) {
			pair.getKey();
			if (pair.getValue() != null) {
				stringBuilder.append(pair.getKey()).append("= ").append(pair.getValue()).append(" AND ");
			}
		}
		stringBuilder.delete(stringBuilder.length() - 4, stringBuilder.length());
		request = stringBuilder.toString();
		System.out.println(request);
		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(request);
			resultSet = pst.executeQuery();
			resultSet.next();
			userId = resultSet.getInt(1);
			user = findById(userId);

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
	public void updateUserDetail(int id, User user) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		UserDetail userDetail = user.getDetail();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("UPDATE USERS_DETAILS SET ");
		if(userDetail.getName() != null) {
			stringBuilder.append("name='").append(userDetail.getName()).append("' ,");
		}
		if(userDetail.getSurname() != null) {
			stringBuilder.append("surname='").append(userDetail.getSurname()).append("' ,");
		}
		if(userDetail.getPhoneNumber() != null) {
			stringBuilder.append("phone_number='").append(userDetail.getPhoneNumber()).append("' ,");
		}
		if(userDetail.getEmail() != null) {
			stringBuilder.append("email='").append(userDetail.getEmail()).append("' ,");
		}
		if(userDetail.getReiting() != 0) {
			stringBuilder.append("reiting=").append(userDetail.getReiting()).append(" ,");
		}
		
		if(userDetail.getPassportNumber() != null) {
			stringBuilder.append("passport_number='").append(userDetail.getPassportNumber()).append("' ,");
		}
		
		if(userDetail.getNationality() != null) {
			stringBuilder.append("nationality='").append(userDetail.getNationality()).append("' ,");
		}
		
		if(userDetail.getDateOfBirth() != null) {
			stringBuilder.append("date_of_birth='").append(userDetail.getDateOfBirth()).append("' ,");
		}
		if(userDetail.getPassportDateOfExpire() != null) {
			stringBuilder.append("passport_date_of_expire='").append(userDetail.getPassportDateOfExpire()).append("' ,");
		}
		if(userDetail.getPassportDateOfIssue() != null) {
			stringBuilder.append("passport_date_of_issue='").append(userDetail.getPassportDateOfIssue()).append("' ,");
		}
		if(userDetail.getAddress() != null) {
			stringBuilder.append("address='").append(userDetail.getAddress()).append("' ,");
		}
		stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
		
		stringBuilder.append("WHERE USER_ID =").append(id);
		System.out.println(stringBuilder.toString());
		
		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(stringBuilder.toString());
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
			pst = con.prepareStatement(selectFromUsers);
			resultSet = pst.executeQuery();
			while (resultSet.next()) {
				String loginDB = resultSet.getString(2);
				if (loginDB.equals(login)) {
					int passwordDB = resultSet.getInt(3);
					if (passwordDB==password.hashCode()) {
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
