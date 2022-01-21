package com.epam.ds.hostel.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.epam.ds.hostel.entity.BookingRequest;
import com.epam.ds.hostel.entity.BookingRequestStatus;

public class BookingRequestCreator {
	
	private final static BookingRequestCreator instance = new BookingRequestCreator();
	
	private BookingRequestCreator() {}
	
	public static BookingRequestCreator getInstance() {
		return instance;
	}
	
	public BookingRequest create(ResultSet resultSet) throws SQLException {
		BookingRequest bRequest = new BookingRequest();
		int id = resultSet.getInt(1);
		Date startDate = resultSet.getDate(2);
		Date endDate = resultSet.getDate(3);
		int numberOfPlaces = resultSet.getInt(4);
		int numberOfLockers = resultSet.getInt(5);
		int status = resultSet.getInt(6);
		int clientId = resultSet.getInt(7);
		Timestamp timeOfCreation = resultSet.getTimestamp(8);
		bRequest.setClientId(clientId);
		bRequest.setEndDate(endDate);
		bRequest.setStartDate(startDate);
		bRequest.setNumberOfLockers(numberOfLockers);
		bRequest.setNumberOfPlaces(numberOfPlaces);
		bRequest.setId(id);
		bRequest.setStatus(BookingRequestStatus.values()[status].toString().toLowerCase());
		bRequest.setTimeOfCreation(timeOfCreation);
		return bRequest;
	}
	
	

}
