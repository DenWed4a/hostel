package com.epam.ds.hostel.dao;

import java.util.List;

import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.BedPlace;

public interface BedPlaceDAO {
	
	void addNewBedPlace(BedPlace place) throws DAOException;
	List<BedPlace> getAllBedPlaces() throws DAOException;
	BedPlace getBedPlace (int id) throws DAOException;
	void deleteBedPlace(int id) throws DAOException;
	

}
