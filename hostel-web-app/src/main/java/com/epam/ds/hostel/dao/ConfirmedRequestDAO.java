package com.epam.ds.hostel.dao;

import java.util.List;

import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.ConfirmedRequest;
import com.epam.ds.hostel.entity.criteria.Criteria;

public interface ConfirmedRequestDAO {
	
	void addNewRequest(ConfirmedRequest request,  int[] bedPalaceId, int[] lockerId) throws DAOException;
	List<ConfirmedRequest> findAllRequests () throws DAOException;
	List<ConfirmedRequest> findConfirmedRequestByAdmin(int admin) throws DAOException;
	void updateConfirmedRequest(int id, Criteria criteria) throws DAOException;
	
	

}