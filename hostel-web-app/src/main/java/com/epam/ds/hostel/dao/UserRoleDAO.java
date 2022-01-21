package com.epam.ds.hostel.dao;

import com.epam.ds.hostel.dao.exception.DAOException;

public interface UserRoleDAO {
	public String getRole(int id) throws DAOException;

}
