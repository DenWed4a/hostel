package com.epam.ds.hostel.dao;



import java.util.List;

import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.BlackListUser;

public interface BlackListDAO {
	void addToBlackList(int userId, String reason) throws DAOException;
	void removeFromBlackList(int userId) throws DAOException;
	boolean checkBlackList(int userId) throws DAOException;
	List<BlackListUser> allBlackListUsers() throws DAOException; 
}
