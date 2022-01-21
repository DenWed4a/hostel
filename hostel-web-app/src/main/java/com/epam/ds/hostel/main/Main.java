package com.epam.ds.hostel.main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.epam.ds.hostel.dao.BillDAO;
import com.epam.ds.hostel.dao.BlackListDAO;
import com.epam.ds.hostel.dao.UserDAO;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPool;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPoolException;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.dao.impl.MySqlBedPlaceDAO;
import com.epam.ds.hostel.dao.impl.MySqlBillDAO;
import com.epam.ds.hostel.dao.impl.MySqlBlackListDAO;
import com.epam.ds.hostel.dao.impl.MySqlConfirmedRequestDAO;
import com.epam.ds.hostel.dao.impl.MySqlUserDAO;
import com.epam.ds.hostel.dao.impl.MySqlUserRole;
import com.epam.ds.hostel.entity.BedPlace;
import com.epam.ds.hostel.entity.Bill;
import com.epam.ds.hostel.entity.BillStatus;
import com.epam.ds.hostel.entity.BookingRequest;
import com.epam.ds.hostel.entity.BookingRequestStatus;
import com.epam.ds.hostel.entity.ConfirmedRequest;
import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.entity.UserDetail;
import com.mysql.cj.util.TestUtils;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, DAOException, ConnectionPoolException, SQLException {
		/*String command = "SELECT date_of_creation FROM BOOKING_REQUESTS";
		Connection connection = ConnectionPool.getInstance().takeConnection();
		PreparedStatement pStatement = connection.prepareStatement(command);
		
		ResultSet resultSet = pStatement.executeQuery();
		Timestamp timestamp = null;
		Date date;
		while(resultSet.next()) {
			timestamp = resultSet.getTimestamp(1);
			System.out.println(timestamp);
		}*/
		
		/*BookingRequestStatus status = BookingRequestStatus.values()[0];
		String string = status.toString();
		System.out.println(string);
		int sd = status.getTitle();
		
		Bill bill = new Bill();
		bill.setBookingRequestID(54);
		bill.setTotalAmount(155.55);
		BillDAO dao = new MySqlBillDAO();
		dao.saveBill(bill);
		System.out.println(test(1));
		
		Date date = Date.valueOf("2022-01-22");
		Date date2 = Date.valueOf("2022-01-25");*/
		BillStatus billStatus = BillStatus.valueOf("NEW");
		System.out.println(billStatus.getTitle());
		
		/*TimeUnit timeUnit = TimeUnit.DAYS;
		System.out.println(timeUnit.convert(date2.getTime()-date.getTime(), TimeUnit.MILLISECONDS));*/
		
		/*BlackListDAO dao = new MySqlBlackListDAO();
		dao.addToBlackList(4, "Обрыгалась на кровать соседа");
		System.out.println(dao.checkBlackList(4));*/

		/*MySqlConfirmedRequestDAO dao = new MySqlConfirmedRequestDAO();
		ConfirmedRequest request = new ConfirmedRequest();
		request.setId(55);
		request.setAdministratorId(1);
		request.setBillId(3);
		dao.addNewRequest(request, new int[] {4,2,3}, new int[] {});*/
		
		
		/*String s = "SELECT roles.title, users.login, users_details.name "
				+ "FROM users JOIN roles ON users.roles_id=roles.id JOIN users_details ON users_details.user_id=users.id";
		Date date = Date.valueOf("2022-01-09");
		Date date2 = Date.valueOf("2022-01-10");
		
		
		String ss2 ="SELECT bed_places.id "
				+ "FROM bed_places LEFT JOIN bed_place_has_confirmed_request ON bed_place_has_confirmed_request.bed_place_id=bed_places.id "
				+ "WHERE (booking_request_id is null)";
		
	
		String requeString = "SELECT bed_place_id, administrator_id, booking_requests.end_date "
				+ "FROM bed_places LEFT JOIN bed_place_has_confirmed_request ON bed_place_has_confirmed_request.bed_place_id=bed_places.id "
				+ "LEFT JOIN confirmed_requests ON bed_place_has_confirmed_request.confirmed_request_id=confirmed_requests.booking_request_id "
				+ "LEFT JOIN booking_requests ON booking_requests.id=confirmed_requests.booking_request_id "
				+ "WHERE (end_date <='"+date+"') OR (start_date >= '"+date2+"') OR (confirmed_request_id is null)";*/
				
		
		/*ConnectionPool cPool = ConnectionPool.getInstance();
		Connection con = cPool.takeConnection();
		
		PreparedStatement pst = con.prepareStatement(requeString);*/
		//pst.setDate(1, date);
		//pst.setDate(2, date2);

		
		
		
		/*ResultSet resultSet = pst.executeQuery();
		while(resultSet.next()) {
			System.out.println(resultSet.getInt(1));//+" "+resultSet.getInt(2)+" "+resultSet.getDate(3));
		}
		*/
		
		Date date = Date.valueOf("2022-01-19");
		Date date2 = Date.valueOf("2022-02-02");
		
		MySqlBedPlaceDAO dao = new MySqlBedPlaceDAO();
		List<BedPlace> list =  dao.findFreeBedPlaces(date, date2);
		
		for(BedPlace element : list) {
			System.out.println(element);
		}
		
		
		
	
		

	}
	
	public static int test(int start) {
		
		int i = 0;
		
		System.out.println(i+++"i++");
		System.out.println(i+"i");
		
		return start++;
	}

}
