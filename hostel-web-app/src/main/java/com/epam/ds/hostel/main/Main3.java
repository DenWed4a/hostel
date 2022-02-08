package com.epam.ds.hostel.main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.epam.ds.hostel.dao.UserDAO;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPool;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPoolException;
import com.epam.ds.hostel.dao.creator.BillCreator;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.dao.impl.MySqlUserDAO;
import com.epam.ds.hostel.entity.Bill;
import com.epam.ds.hostel.entity.BillStatus;
import com.epam.ds.hostel.entity.Locker;
import com.epam.ds.hostel.entity.LockerType;
import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.entity.UserDetail;
import com.epam.ds.hostel.entity.UserRole;
import com.epam.ds.hostel.service.BillService;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.exception.ServiceException;
import com.epam.ds.util.BillTotalCalculator;
import com.mysql.cj.jdbc.result.ResultSetInternalMethods;



public class Main3 {
	private final static Logger log = Logger.getLogger(Main3.class);

	public static void main(String[] args) throws ConnectionPoolException, SQLException {
		

			ConnectionPool cPool = ConnectionPool.getInstance();
			cPool.initPoolData();
			Connection con = null;
			PreparedStatement pst = null;
			ResultSet resultSet2;
			UserDAO userDAO = new MySqlUserDAO();
			UserDetail userDetail = new UserDetail();
			userDetail.setName("Pasha");
			User user = new User.Builder().login("d1").userDetail(userDetail).password("123").idRole(UserRole.CLIENT).build();
			
			String addUser = "INSERT INTO USERS (login, password) VALUE (?,?)";
			int i = 0;
			try {
				cPool.initPoolData();
				con = cPool.takeConnection();
				pst = con.prepareStatement(addUser);
				pst.setString(1, "d");
				pst.setString(2, "123123");
				pst.executeUpdate();
				
			} catch (SQLIntegrityConstraintViolationException e) {
				e.printStackTrace();
				System.out.println(e.getErrorCode());
				System.out.println(e.getMessage());
			}
			
				
		
			
			
	
			
	}
}
