package com.epam.ds.hostel.dao;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.BedPlace;
import com.epam.ds.hostel.entity.Locker;

public interface LockerDAO {
	
	void addNewLocker(Locker locker) throws DAOException;
	List<Locker> getAllLockers() throws DAOException;
	Set<Locker> getFreeLockers(Date startDate, Date endDate) throws DAOException;
	Locker getLocker (int id) throws DAOException;
	void updateLocker(Locker locker) throws DAOException;
	

	


}
