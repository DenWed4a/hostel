package com.epam.ds.hostel.service;

import java.util.List;


import com.epam.ds.hostel.entity.BookingRequest;
import com.epam.ds.hostel.entity.criteria.Criteria;
import com.epam.ds.hostel.service.exception.ServiceException;

public interface BookingRequestService {
	
	void addNewRequest(BookingRequest request) throws ServiceException;
	List<BookingRequest> findAllBookingRequest() throws ServiceException;
	List<BookingRequest> findBookingRequest(int userId) throws ServiceException;
	List<BookingRequest> findAllUnconfirmedRequests() throws ServiceException; 
	void updateBookingRequest(BookingRequest bookingRequest) throws ServiceException;
	void deleteBookingRequest(int id) throws ServiceException;
	BookingRequest getBookingRequestById(int id) throws ServiceException;

}
