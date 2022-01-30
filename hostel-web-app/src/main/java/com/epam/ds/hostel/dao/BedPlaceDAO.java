package com.epam.ds.hostel.dao;

import java.sql.Date;
import java.util.List;

import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.BedPlace;

public interface BedPlaceDAO {
	
	void addNewBedPlace(BedPlace place) throws DAOException;
	List<BedPlace> getAllBedPlaces() throws DAOException;
	List<BedPlace> getFreeBedPlaces(Date startDate, Date endDate) throws DAOException;
	BedPlace getBedPlace (int id) throws DAOException;
	void updateBedPlace(BedPlace place) throws DAOException;
	
	

}
