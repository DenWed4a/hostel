package com.epam.ds.hostel.service;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.epam.ds.hostel.entity.Locker;
import com.epam.ds.hostel.service.exception.ServiceException;

public interface LockerService {
	void addNewLocker(Locker locker) throws ServiceException;
	List<Locker> getAllLockers() throws ServiceException;
	Set<Locker> getFreeLockers(Date startDate, Date endDate) throws ServiceException;
	Locker getLocker (int id) throws ServiceException;
	void updateLocker(Locker locker) throws ServiceException;

}
