package com.epam.ds.hostel.dao.creator;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.ds.hostel.entity.ConfirmedRequest;



public class ConfirmedRequestCreator {
private final static ConfirmedRequestCreator instance = new ConfirmedRequestCreator();
	
	private ConfirmedRequestCreator() {}
	
	public static ConfirmedRequestCreator getInstance() {
		return instance;
	}
	
	public ConfirmedRequest create(ResultSet resultSet) throws SQLException {
		ConfirmedRequest request = new ConfirmedRequest();
		int id = resultSet.getInt(1);
		int billsId = resultSet.getInt(2);
		int administatorId = resultSet.getInt(3);
		Date confirmationDate = resultSet.getDate(4);
		int status = resultSet.getInt(5);
		Date dateOfPayment = resultSet.getDate(6);

		request.setAdministratorId(administatorId);
		request.setBillId(billsId);
		request.setConfirmationDate(confirmationDate);
		request.setDateOfPayment(dateOfPayment);
		request.setId(id);
		request.setStatus(status);
		
		return request;
	}

}
