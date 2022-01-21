package com.epam.ds.hostel.dao;

import java.util.List;

import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.BookingRequest;
import com.epam.ds.hostel.entity.criteria.Criteria;

public interface BookingRequestDAO {
	void addNewRequest(BookingRequest request) throws DAOException;
	List<BookingRequest> findAllBookingRequest() throws DAOException;
	List<BookingRequest> findBookingRequest(int userId) throws DAOException;
	List<BookingRequest> findAllUnconfirmedRequests() throws DAOException; 
	void updateBookingRequest(int id, Criteria criteria) throws DAOException;
	void deleteBookingRequest(int id) throws DAOException;
	 
	

}
