package com.epam.ds.hostel.service;

import com.epam.ds.hostel.entity.ConfirmedRequest;
import com.epam.ds.hostel.service.exception.ServiceException;

public interface ConfirmedRequestService {
	
	void addConfirmedRequest(ConfirmedRequest request) throws ServiceException;
	ConfirmedRequest findConfirmedRequest(int bookingRequestId) throws ServiceException;
	void updateConfirmedRequest(ConfirmedRequest request) throws ServiceException;
	void deleteConfirmedRequest(int bookingRequestId) throws ServiceException;

}
