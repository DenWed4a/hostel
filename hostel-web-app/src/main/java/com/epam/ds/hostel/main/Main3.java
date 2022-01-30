package com.epam.ds.hostel.main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.epam.ds.hostel.dao.connectionpool.ConnectionPool;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPoolException;
import com.epam.ds.hostel.entity.Locker;
import com.epam.ds.hostel.entity.LockerType;
import com.mysql.cj.jdbc.result.ResultSetInternalMethods;



public class Main3 {
	private final static Logger log = Logger.getLogger(Main3.class);

	public static void main(String[] args) {
		
		log.log(org.apache.log4j.Level.ERROR, "karaul");
		
	
			
			Date date = Date.valueOf("2022-01-26");
			Date date2 = Date.valueOf("2022-01-30");
			
			String GET_FREE_LOCKERS = "SELECT lockers.id, lockers.type, lockers.image, lockers.status, booking_request_id "
					+ "FROM lockers JOIN lockers_has_confirmed_requests LHCR ON LHCR.lockers_id = lockers.id "
					+ "JOIN booking_requests BR ON BR.id = LHCR.confirmed_request_id "
					+ "JOIN confirmed_requests CR ON CR.booking_request_id = BR.id && CR.status = 0 "
					+ "WHERE NOT(start_date >= ? OR end_date <= ?)";
			Set<Locker> resultLockers = new HashSet<>();
			ConnectionPool cPool = ConnectionPool.getInstance();
			
			Connection con = null;
			PreparedStatement pst = null;
			ResultSet resultSet2;
			Locker locker;
			try {
				cPool.initPoolData();
				con = cPool.takeConnection();
				pst = con.prepareStatement(GET_FREE_LOCKERS);
				pst.setDate(1, date2);
				pst.setDate(2, date);
				resultSet2 = pst.executeQuery();
				while(resultSet2.next()) {
					int id = resultSet2.getInt(1);
					LockerType type = LockerType.valueOf(resultSet2.getString(2));
					String image = resultSet2.getString(3);
					locker = new Locker();
					locker.setId(id);
					locker.setSize(type);
					locker.setImagePath(image);	
					resultLockers.add(locker);
					System.out.println(resultSet2.getInt(5));
				}
				
			} catch (ConnectionPoolException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			for(Locker elementInteger : resultLockers) {
				System.out.println(elementInteger);
			}
			
			
	}

}
