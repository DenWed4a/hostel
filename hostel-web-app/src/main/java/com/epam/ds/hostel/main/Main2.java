package com.epam.ds.hostel.main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.epam.ds.hostel.dao.connectionpool.ConnectionPool;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPoolException;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.dao.impl.MySqlBookingRequestDAO;
import com.epam.ds.hostel.dao.impl.MySqlUserDAO;
import com.epam.ds.hostel.entity.BookingRequest;
import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.entity.UserDetail;
import com.epam.ds.hostel.entity.criteria.Criteria;
import com.epam.ds.hostel.entity.criteria.SearchCriteria;

public class Main2 {
	

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ConnectionPoolException, ConnectionPoolException, DAOException {
		
		//ConnectionPool cp  = ConnectionPool.getInstance();
		//cp.initPoolData();
		ResourceBundle bundle = ResourceBundle.getBundle("config");
		MySqlUserDAO dao = new MySqlUserDAO();
		
		
		List<User> list = dao.findAllUsers();
		for(User element : list) {
			System.out.println(element);
		}
		
		UserDetail detail = new UserDetail();
		detail.setName("Михаил");
		detail.setSurname("Камушкин");
		detail.setDateOfBirth(Date.valueOf("1965-05-19"));
		detail.setEmail("Mixa@.gmail.com");
		detail.setNationality("Belarus");
		detail.setPassportDateOfExpire(Date.valueOf("2025-06-07"));
		detail.setPassportDateOfIssue(Date.valueOf("2015-06-08"));
		detail.setPassportNumber("MP3456344");
		detail.setPhoneNumber("+375335674451");
		detail.setReiting(5.0);
		detail.setAddress("Minsk, Kirova 12");
		
		
		Criteria criteria = new Criteria(SearchCriteria.Users.class.getSimpleName());
		criteria.add(SearchCriteria.UserDetail.TELEPHONE_NUMBER.toString(), "'+375290000000'");
		criteria.add(SearchCriteria.UserDetail.EMAIL.toString(), "'newMail@mail.new'");
		
		//System.out.println(dao.findUserByCriteria(criteria));
		//dao.updateUserDetail(31, criteria);
		
		//System.out.println(dao.findById(2));
		
		BookingRequest bk = new BookingRequest();
		bk.setStartDate(Date.valueOf("2021-12-07"));
		bk.setEndDate(Date.valueOf("2021-12-08"));
		bk.setNumberOfLockers(1);
		bk.setNumberOfPlaces(1);
		bk.setClientId(5);
		MySqlBookingRequestDAO dao1 = new MySqlBookingRequestDAO();
		dao1.deleteBookingRequest(8);
		//dao.deleteUser(7);
		for(BookingRequest element : dao1.findAllBookingRequest()) {
			System.out.println(element);
		}
		
		
		User user = new User.Builder().login("154542322").status(1).userDetail(detail).build();
		
		//dao.saveUser(user);
		//dao.updateUserDetail(38, user);
		System.out.println(dao.findById(100));

		
	}

}
