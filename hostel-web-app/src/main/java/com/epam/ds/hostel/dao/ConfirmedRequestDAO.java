package com.epam.ds.hostel.dao;

import java.util.List;

import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.ConfirmedRequest;


public interface ConfirmedRequestDAO {
	
	void addNewRequest(ConfirmedRequest request,  int[] bedPalaceId, int[] lockerId) throws DAOException;
	List<ConfirmedRequest> findAllRequests () throws DAOException;
	List<ConfirmedRequest> findConfirmedRequestByAdmin(int admin) throws DAOException;
	void updateConfirmedRequest(ConfirmedRequest request) throws DAOException;
	ConfirmedRequest findConfirmedRequestById(int id) throws DAOException;
	
	

}
