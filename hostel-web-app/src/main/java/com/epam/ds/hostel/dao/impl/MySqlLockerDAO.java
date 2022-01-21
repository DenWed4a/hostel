package com.epam.ds.hostel.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.ds.hostel.dao.LockerDAO;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPool;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPoolException;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.Locker;
import com.epam.ds.hostel.entity.LockerType;

public class MySqlLockerDAO implements LockerDAO{
	
	private ConnectionPool cp = ConnectionPool.getInstance();

	private final static String addLocker = "INSERT INTO lockers (type, image) VALUES (?)";
	private final static String getAllLockers = "SELECT * FROM lockers";
	private final static String getLockerById = "SELECT * FROM lockers WHERE (id = ?)";
	

	@Override
	public void addNewLocker(Locker locker) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(addLocker);
			pst.setString(1, locker.getSize().toString());
			pst.setString(2, locker.getImagePath());
			pst.executeUpdate();
	
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}finally {
			try {
				if (pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			}

		}
		
		
		
	}

	@Override
	public List<Locker> getAllLockers() throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		List<Locker> result = new ArrayList<>();
		
		
		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(getAllLockers);
			resultSet = pst.executeQuery();
			while(resultSet.next()) {
				
				int id = resultSet.getInt(1);
				String type = resultSet.getString(2);
				String imagePath = resultSet.getString(3);
				Locker locker = new Locker();
				
				locker.setId(id);
				locker.setImagePath(imagePath);
				locker.setSize(LockerType.valueOf(type));
				result.add(locker);
			}

			
		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		}finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			}
			try {
				if (pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
				
		return result;
	}

	@Override
	public Locker getLocker(int id) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		Locker locker;
		
		try {
			
			con = cp.takeConnection();
			pst = con.prepareStatement(getLockerById);
			pst.setInt(1, id);
			resultSet = pst.executeQuery();
			resultSet.next();
			
			
			String type = resultSet.getString(2);
			String imagePath = resultSet.getString(3);
			locker = new Locker();
			
			locker.setId(id);
			locker.setImagePath(imagePath);
			locker.setSize(LockerType.valueOf(type));
			
		}catch (SQLException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		}finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			}
			try {
				if (pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
		return locker;
	}

	@Override
	public void deleteLocker(int id) throws DAOException {
		// TODO Auto-generated method stub
		
	}

}
