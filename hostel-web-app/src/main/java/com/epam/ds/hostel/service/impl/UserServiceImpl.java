package com.epam.ds.hostel.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.epam.ds.hostel.dao.DAOFactory;
import com.epam.ds.hostel.dao.UserDAO;
import com.epam.ds.hostel.dao.exception.DAOException;

import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.service.UserService;
import com.epam.ds.hostel.service.exception.ServiceException;

public class UserServiceImpl implements UserService{

	@Override
	public int saveUser(User user) throws ServiceException {
		int isSaved;
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getMySqlUserDAO();
		try {
			isSaved =  userDAO.saveUser(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return isSaved;
		
		
	}

	@Override
	public boolean validation(String login, String password) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getMySqlUserDAO();
		boolean result;
		try {
			result = userDAO.validation(login, password);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return result;
	}

	@Override
	public List<User> findAllUsers() throws ServiceException {
		
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getMySqlUserDAO();
		List<User> resultList;
		try {
			resultList=userDAO.findAllUsers();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return resultList;
		
	}

	@Override
	public User findById(int id) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getMySqlUserDAO();
		User user;
		try {
			user = userDAO.findById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public User findByLogin(String login) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getMySqlUserDAO();
		User user;
		try {
			user = userDAO.findByLogin(login);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return user;
	}


	@Override
	public void updateUserDetail(User user) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getMySqlUserDAO();
		try {
			userDAO.updateUserDetail(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public void updateUser(User user) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getMySqlUserDAO();
		try {
			userDAO.updateUser(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public void deleteUser(int id) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getMySqlUserDAO();
		try {
			userDAO.deleteUser(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public Set<User> getAdmins() throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getMySqlUserDAO();
		Set<User> admins = new HashSet<>();
		try {
			List<User> users = userDAO.findAllUsers();
			for(User element : users) {
				if(element.getRole().getTitle() == 1 || element.getRole().getTitle() == 2) {
					admins.add(element);
				}
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return admins;
	}

}
