package com.epam.ds.hostel.dao;

import com.epam.ds.hostel.dao.impl.MySqlBillDAO;
import com.epam.ds.hostel.dao.impl.MySqlBlackListDAO;
import com.epam.ds.hostel.dao.impl.MySqlBookingRequestDAO;
import com.epam.ds.hostel.dao.impl.MySqlUserDAO;
import com.epam.ds.hostel.dao.impl.MySqlUserRole;

public final class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();

	private final MySqlUserDAO mySqlUserDAO = new MySqlUserDAO();
	private final UserRoleDAO userRoleDAO = new MySqlUserRole();
	private final MySqlBookingRequestDAO mySqlBookingRequestDAO = new MySqlBookingRequestDAO();
	private final MySqlBillDAO mySqlBillDAO = new MySqlBillDAO(); 
	private final BlackListDAO blackListDAO = new MySqlBlackListDAO();
	
	
	private DAOFactory() {}
	
	public static DAOFactory getInstance() {
		return instance;
	}
	
	public MySqlBookingRequestDAO getMySqlBookingRequestDAO() {
		return mySqlBookingRequestDAO;
	}

	public MySqlUserDAO getMySqlUserDAO() {
		return mySqlUserDAO;
	}
	
	public UserRoleDAO  getUserRoleDAO() {
		return userRoleDAO;
	}

	public MySqlBillDAO getMySqlBillDAO() {
		return mySqlBillDAO;
	}

	public BlackListDAO getBlackListDAO() {
		return blackListDAO;
	}
	
	



}
