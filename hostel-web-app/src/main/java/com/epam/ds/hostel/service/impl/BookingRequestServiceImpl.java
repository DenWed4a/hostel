package com.epam.ds.hostel.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.epam.ds.hostel.dao.BookingRequestDAO;
import com.epam.ds.hostel.dao.DAOFactory;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.BookingRequest;
import com.epam.ds.hostel.entity.criteria.Criteria;
import com.epam.ds.hostel.service.BookingRequestService;
import com.epam.ds.hostel.service.exception.ServiceException;

public class BookingRequestServiceImpl implements BookingRequestService{

	@Override
	public void addNewRequest(BookingRequest request) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		BookingRequestDAO bookingRequestDAO = factory.getMySqlBookingRequestDAO();
		try {
			bookingRequestDAO.addNewRequest(request);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public List<BookingRequest> findAllBookingRequest() throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		BookingRequestDAO bookingRequestDAO = factory.getMySqlBookingRequestDAO();
		List<BookingRequest> result = new ArrayList<>();
		try {
			result =  bookingRequestDAO.findAllBookingRequest();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public List<BookingRequest> findBookingRequest(int userId) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		BookingRequestDAO bookingRequestDAO = factory.getMySqlBookingRequestDAO();
		List<BookingRequest> result = new ArrayList<>();
		try {
			result =  bookingRequestDAO.findBookingRequest(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public List<BookingRequest> findAllUnconfirmedRequests() throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		BookingRequestDAO bookingRequestDAO = factory.getMySqlBookingRequestDAO();
		List<BookingRequest> result = new ArrayList<>();
		try {
			result =  bookingRequestDAO.findAllUnconfirmedRequests();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public void updateBookingRequest(BookingRequest bookingRequest) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		BookingRequestDAO bookingRequestDAO = factory.getMySqlBookingRequestDAO();
		
		try {
			bookingRequestDAO.updateBookingRequest(bookingRequest);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public void deleteBookingRequest(int id) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		BookingRequestDAO bookingRequestDAO = factory.getMySqlBookingRequestDAO();
		try {
			bookingRequestDAO.deleteBookingRequest(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public BookingRequest getBookingRequestById(int id) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		BookingRequestDAO bookingRequestDAO = factory.getMySqlBookingRequestDAO();
		BookingRequest bookingRequest;
		try {
			bookingRequest = bookingRequestDAO.getBookingRequestById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return bookingRequest;
	}

}
