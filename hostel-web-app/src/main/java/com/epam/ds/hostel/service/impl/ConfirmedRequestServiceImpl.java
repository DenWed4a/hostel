package com.epam.ds.hostel.service.impl;

import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.ThrowStatement;

import com.epam.ds.hostel.dao.ConfirmedRequestDAO;
import com.epam.ds.hostel.dao.DAOFactory;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.ConfirmedRequest;
import com.epam.ds.hostel.service.ConfirmedRequestService;
import com.epam.ds.hostel.service.exception.ServiceException;

public class ConfirmedRequestServiceImpl implements ConfirmedRequestService{

	@Override
	public void addConfirmedRequest(ConfirmedRequest request, int[] bedPalaceId, int[] lockerId) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		ConfirmedRequestDAO confirmedRequestDAO = factory.getConfirmedRequestDAO();
		
		try {
			confirmedRequestDAO.addNewRequest(request, bedPalaceId, lockerId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public ConfirmedRequest findConfirmedRequest(int bookingRequestId) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		ConfirmedRequestDAO confirmedRequestDAO = factory.getConfirmedRequestDAO();
		ConfirmedRequest result;
		try {
			result = confirmedRequestDAO.findConfirmedRequestById(bookingRequestId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
		
		
	}

	@Override
	public void updateConfirmedRequest(ConfirmedRequest request) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		ConfirmedRequestDAO confirmedRequestDAO = factory.getConfirmedRequestDAO();
		
		try {
			confirmedRequestDAO.updateConfirmedRequest(request);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public void deleteConfirmedRequest(int bookingRequestId) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ConfirmedRequest> findAllRequests() throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		ConfirmedRequestDAO confirmedRequestDAO = factory.getConfirmedRequestDAO();
		List<ConfirmedRequest> result;
		
		try {
			result = confirmedRequestDAO.findAllRequests();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

}
