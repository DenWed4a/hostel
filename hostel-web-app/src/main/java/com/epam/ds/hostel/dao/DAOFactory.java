package com.epam.ds.hostel.dao;

import com.epam.ds.hostel.dao.impl.MySqlBedPlaceDAO;
import com.epam.ds.hostel.dao.impl.MySqlBillDAO;
import com.epam.ds.hostel.dao.impl.MySqlBlackListDAO;
import com.epam.ds.hostel.dao.impl.MySqlBookingRequestDAO;
import com.epam.ds.hostel.dao.impl.MySqlConfirmedRequestDAO;
import com.epam.ds.hostel.dao.impl.MySqlLockerDAO;
import com.epam.ds.hostel.dao.impl.MySqlReviewDAO;
import com.epam.ds.hostel.dao.impl.MySqlUserDAO;
import com.epam.ds.hostel.dao.impl.MySqlUserRole;

public final class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();

	private final UserDAO mySqlUserDAO = new MySqlUserDAO();
	private final UserRoleDAO userRoleDAO = new MySqlUserRole();
	private final BookingRequestDAO mySqlBookingRequestDAO = new MySqlBookingRequestDAO();
	private final BillDAO mySqlBillDAO = new MySqlBillDAO();
	private final BlackListDAO blackListDAO = new MySqlBlackListDAO();
	private final BedPlaceDAO bedPlaceDAO = new MySqlBedPlaceDAO();
	private final ConfirmedRequestDAO confirmedRequestDAO = new MySqlConfirmedRequestDAO();
	private final LockerDAO MySqllockerDAO = new MySqlLockerDAO();
	private final ReviewDAO mySqlReviewDAO = new MySqlReviewDAO();

	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		return instance;
	}

	public UserRoleDAO getUserRoleDAO() {
		return userRoleDAO;
	}

	public BlackListDAO getBlackListDAO() {
		return blackListDAO;
	}

	public BedPlaceDAO getBedPlaceDAO() {
		return bedPlaceDAO;
	}

	public UserDAO getMySqlUserDAO() {
		return mySqlUserDAO;
	}

	public BookingRequestDAO getMySqlBookingRequestDAO() {
		return mySqlBookingRequestDAO;
	}

	public BillDAO getMySqlBillDAO() {
		return mySqlBillDAO;
	}

	public ConfirmedRequestDAO getConfirmedRequestDAO() {
		return confirmedRequestDAO;
	}

	public LockerDAO getMySqllockerDAO() {
		return MySqllockerDAO;
	}

	public ReviewDAO getMySqlReviewDAO() {
		return mySqlReviewDAO;
	}
	
	
	

}
