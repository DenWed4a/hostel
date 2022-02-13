package com.epam.ds.hostel.service;

import java.util.List;
import java.util.Set;

import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.service.exception.ServiceException;

public interface UserService {
	
	int saveUser(User user) throws ServiceException;
	
	boolean validation(String login, String password) throws ServiceException;
	
	List<User> findAllUsers() throws ServiceException;
	
	User findById(int id) throws ServiceException;
	
	User findByLogin(String login) throws ServiceException;
		
	void updateUserDetail(User user) throws ServiceException;
	
	void updateUser(User user) throws ServiceException;
	
	void deleteUser(int id) throws ServiceException;
	Set<User> getAdmins() throws ServiceException;

}
