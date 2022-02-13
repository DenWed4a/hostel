package com.epam.ds.hostel.service;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.epam.ds.hostel.entity.BedPlace;
import com.epam.ds.hostel.service.exception.ServiceException;

public interface BedPlaceService {
	void addNewBedPlace(BedPlace place) throws ServiceException;
	List<BedPlace> getAllBedPlaces() throws ServiceException;
	Set<BedPlace> getFreeBedPlaces(Date startDate, Date endDate) throws ServiceException;
	BedPlace getBedPlace (int id) throws ServiceException;
	void updateBedPlace(BedPlace place) throws ServiceException;

}
