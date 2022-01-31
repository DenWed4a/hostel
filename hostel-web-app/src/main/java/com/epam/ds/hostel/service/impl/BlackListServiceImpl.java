package com.epam.ds.hostel.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.epam.ds.hostel.dao.BlackListDAO;
import com.epam.ds.hostel.dao.DAOFactory;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.BlackListUser;
import com.epam.ds.hostel.service.BlackListService;
import com.epam.ds.hostel.service.exception.ServiceException;

public class BlackListServiceImpl implements BlackListService{

	@Override
	public void addToBlackList(int userId, String reason) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		BlackListDAO blackListDAO = factory.getBlackListDAO();
		
		try {
			blackListDAO.addToBlackList(userId, reason);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		
	}

	@Override
	public void removeFromBlackList(int userId) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		BlackListDAO blackListDAO = factory.getBlackListDAO();
		
		try {
			blackListDAO.removeFromBlackList(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public boolean checkBlackList(int userId) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		BlackListDAO blackListDAO = factory.getBlackListDAO();
		boolean answer;
		
		try {
			answer =  blackListDAO.checkBlackList(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return answer;
	}

	@Override
	public List<BlackListUser> allBlackListUsers() throws ServiceException {
		List<BlackListUser> result = new ArrayList<>();
		
		DAOFactory factory = DAOFactory.getInstance();
		BlackListDAO blackListDAO = factory.getBlackListDAO();
		
		try {
			result = blackListDAO.allBlackListUsers();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
			
		return result;
	}

}
