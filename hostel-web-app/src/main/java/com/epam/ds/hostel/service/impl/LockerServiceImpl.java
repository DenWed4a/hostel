package com.epam.ds.hostel.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.epam.ds.hostel.dao.DAOFactory;
import com.epam.ds.hostel.dao.LockerDAO;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.Locker;
import com.epam.ds.hostel.service.LockerService;
import com.epam.ds.hostel.service.exception.ServiceException;

public class LockerServiceImpl implements LockerService{

	@Override
	public void addNewLocker(Locker locker) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		LockerDAO lockerDAO = factory.getMySqllockerDAO();
		try {
			lockerDAO.addNewLocker(locker);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public List<Locker> getAllLockers() throws ServiceException {
		List<Locker> result;
		DAOFactory factory = DAOFactory.getInstance();
		LockerDAO lockerDAO = factory.getMySqllockerDAO();
		
		try {
			result = lockerDAO.getAllLockers();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public Set<Locker> getFreeLockers(Date startDate, Date endDate) throws ServiceException {
		Set<Locker> result;
		DAOFactory factory = DAOFactory.getInstance();
		LockerDAO lockerDAO = factory.getMySqllockerDAO();
		
		try {
			result = lockerDAO.getFreeLockers(startDate, endDate);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public Locker getLocker(int id) throws ServiceException {
		Locker locker;
		DAOFactory factory = DAOFactory.getInstance();
		LockerDAO lockerDAO = factory.getMySqllockerDAO();
		try {
			locker = lockerDAO.getLocker(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return locker;
	}

	@Override
	public void updateLocker(Locker locker) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		LockerDAO lockerDAO = factory.getMySqllockerDAO();
		
		try {
			lockerDAO.updateLocker(locker);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

}
