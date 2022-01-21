package com.epam.ds.hostel.service;

import java.util.List;


import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.entity.criteria.Criteria;
import com.epam.ds.hostel.service.exception.ServiceException;

public interface UserService {
	
	void saveUser(User user) throws ServiceException;
	
	boolean validation(String login, String password) throws ServiceException;
	
	List<User> findAllUsers() throws ServiceException;
	
	User findById(int id) throws ServiceException;
	
	User findByLogin(String login) throws ServiceException;
	
	User findUserByCriteria(Criteria criteria) throws ServiceException;
	
	void updateUserDetail(int userId, User user) throws ServiceException;
	
	void updateUser(int userId, User user) throws ServiceException;
	
	void deleteUser(int id) throws ServiceException;

}
