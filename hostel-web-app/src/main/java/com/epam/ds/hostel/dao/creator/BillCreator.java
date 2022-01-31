package com.epam.ds.hostel.dao.creator;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.ds.hostel.entity.Bill;
import com.epam.ds.hostel.entity.BillStatus;


public class BillCreator {
	private final static BillCreator instance = new BillCreator();
	
	private  BillCreator() {
		
	}
	
	public static BillCreator getInstance() {
		return instance;
	}
	
	
	public Bill create(ResultSet resultSet) throws SQLException {
		Bill bill = new Bill();
		
		int id = resultSet.getInt(1);
		double totalAmount = resultSet.getDouble(2);
		BillStatus status = BillStatus.values()[resultSet.getInt(3)];
		int requestId = resultSet.getInt(4);
		
		bill.setId(id);
		bill.setTotalAmount(totalAmount);
		bill.setStatus(status);
		bill.setBookingRequestID(requestId);
		
		return bill;
	}

}
