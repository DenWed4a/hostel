package com.epam.ds.hostel.service;

import com.epam.ds.hostel.service.exception.ServiceException;

public interface UserRoleService {
	public String getRole(int id) throws ServiceException;

}
