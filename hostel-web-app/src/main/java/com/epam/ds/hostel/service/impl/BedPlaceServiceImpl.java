package com.epam.ds.hostel.service.impl;


import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.epam.ds.hostel.dao.BedPlaceDAO;
import com.epam.ds.hostel.dao.DAOFactory;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.BedPlace;
import com.epam.ds.hostel.service.BedPlaceService;
import com.epam.ds.hostel.service.exception.ServiceException;

public class BedPlaceServiceImpl implements BedPlaceService{

	@Override
	public void addNewBedPlace(BedPlace place) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		BedPlaceDAO bedPlaceDAO = factory.getBedPlaceDAO();
		try {
			bedPlaceDAO.addNewBedPlace(place);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public List<BedPlace> getAllBedPlaces() throws ServiceException {
		List<BedPlace> result;
		DAOFactory factory = DAOFactory.getInstance();
		BedPlaceDAO bedPlaceDAO = factory.getBedPlaceDAO();
		
		try {
			result = bedPlaceDAO.getAllBedPlaces();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public BedPlace getBedPlace(int id) throws ServiceException {
		BedPlace bedPlace;
		DAOFactory factory = DAOFactory.getInstance();
		BedPlaceDAO bedPlaceDAO = factory.getBedPlaceDAO();
		
		try {
			bedPlace = bedPlaceDAO.getBedPlace(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return bedPlace;
	}

	@Override
	public void updateBedPlace(BedPlace place) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		BedPlaceDAO bedPlaceDAO = factory.getBedPlaceDAO();
		
		try {
			bedPlaceDAO.updateBedPlace(place);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public Set<BedPlace> getFreeBedPlaces(Date startDate, Date endDate) throws ServiceException {
		Set<BedPlace> result;
		DAOFactory factory = DAOFactory.getInstance();
		BedPlaceDAO bedPlaceDAO = factory.getBedPlaceDAO();
		
		try {
			result = bedPlaceDAO.getFreeBedPlaces(startDate, endDate);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

}
