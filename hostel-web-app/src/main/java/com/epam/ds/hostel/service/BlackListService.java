package com.epam.ds.hostel.service;

import java.util.List;


import com.epam.ds.hostel.entity.BlackListUser;
import com.epam.ds.hostel.service.exception.ServiceException;

public interface BlackListService {
	void addToBlackList(int userId, String reason) throws ServiceException;
	void removeFromBlackList(int userId) throws ServiceException;
	boolean checkBlackList(int userId) throws ServiceException;
	List<BlackListUser> allBlackListUsers() throws ServiceException;

}
