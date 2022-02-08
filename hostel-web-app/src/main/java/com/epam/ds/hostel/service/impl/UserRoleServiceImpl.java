package com.epam.ds.hostel.service.impl;

import com.epam.ds.hostel.dao.DAOFactory;
import com.epam.ds.hostel.dao.UserRoleDAO;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.service.UserRoleService;
import com.epam.ds.hostel.service.exception.ServiceException;

public class UserRoleServiceImpl implements UserRoleService{

	@Override
	public String getRole(int id) throws ServiceException {
		
		DAOFactory factory = DAOFactory.getInstance();
		UserRoleDAO userRoleDAO = factory.getUserRoleDAO();
		String role;
		try {
			role = userRoleDAO.getRole(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return role;
		
	}

}
