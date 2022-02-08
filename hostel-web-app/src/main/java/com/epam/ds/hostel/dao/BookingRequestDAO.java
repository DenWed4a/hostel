package com.epam.ds.hostel.dao;

import java.util.List;

import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.BookingRequest;


public interface BookingRequestDAO {
	void addNewRequest(BookingRequest request) throws DAOException;
	BookingRequest getBookingRequestById(int id) throws DAOException;
	List<BookingRequest> findAllBookingRequest() throws DAOException;
	List<BookingRequest> findBookingRequest(int userId) throws DAOException;
	List<BookingRequest> findAllUnconfirmedRequests() throws DAOException; 
	void updateBookingRequest(BookingRequest bookingRequest) throws DAOException;
	void deleteBookingRequest(int id) throws DAOException;
	 
	

}
