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
import com.epam.ds.hostel.dao.creator.BillCreator;
import com.epam.ds.hostel.entity.Bill;
import com.epam.ds.hostel.entity.BillStatus;
import com.epam.ds.hostel.entity.Locker;
import com.epam.ds.hostel.entity.LockerType;
import com.epam.ds.hostel.service.BillService;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.exception.ServiceException;
import com.epam.ds.util.BillTotalCalculator;
import com.mysql.cj.jdbc.result.ResultSetInternalMethods;



public class Main3 {
	private final static Logger log = Logger.getLogger(Main3.class);

	public static void main(String[] args) {
		

			ConnectionPool cPool = ConnectionPool.getInstance();
			
			Connection con = null;
			PreparedStatement pst = null;
			ResultSet resultSet2;
			String getBill = "SELECT * FROM bills where id = 1";
			Bill bill;
			BillService service = ServiceFactory.getInstance().getBillService();
				try {
					cPool.initPoolData();
					con = cPool.takeConnection();
					pst = con.prepareStatement(getBill);
					resultSet2 = pst.executeQuery();
					resultSet2.next();
					
					bill = new Bill();				
					bill.setId(resultSet2.getInt(1));
					bill.setTotalAmount(resultSet2.getDouble(2));
					bill.setBookingRequestID(resultSet2.getInt(4));
					bill.setStatus(BillStatus.values()[resultSet2.getInt(3)]);
					System.out.println(bill);
					bill = BillCreator.getInstance().create(resultSet2);
					System.out.println(bill);
					bill = service.findBill(2);
					System.out.println(bill);
					
				} catch (ConnectionPoolException | SQLException | ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	
			
	}
}
