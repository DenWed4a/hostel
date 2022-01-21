package com.epam.ds.hostel.dao;


import java.util.List;

import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.entity.criteria.Criteria;

public interface UserDAO {
	void saveUser(User user) throws DAOException;
	
	boolean validation(String login, String password) throws DAOException;
	
	List<User> findAllUsers() throws DAOException;
	
	User findById(int id) throws DAOException;
	
	User findByLogin(String login) throws DAOException;
	
	User findUserByCriteria(Criteria criteria) throws DAOException;
	
	void updateUserDetail(int userId, User user) throws DAOException;
	
	void updateUser(int userId, User user) throws DAOException;
	
	void deleteUser(int id) throws DAOException;
	
	
	

}
