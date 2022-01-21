package com.epam.ds.hostel.dao;

import java.util.List;

import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.Locker;

public interface LockerDAO {
	
	void addNewLocker(Locker locker) throws DAOException;
	List<Locker> getAllLockers() throws DAOException;
	Locker getLocker (int id) throws DAOException;
	void deleteLocker(int id) throws DAOException;
	


}
