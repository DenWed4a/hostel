package com.epam.ds.hostel.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.epam.ds.hostel.dao.LockerDAO;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPool;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPoolException;
import com.epam.ds.hostel.dao.exception.DAOException;

import com.epam.ds.hostel.entity.Locker;
import com.epam.ds.hostel.entity.LockerType;

public class MySqlLockerDAO implements LockerDAO {

	private ConnectionPool cp = ConnectionPool.getInstance();

	private final static String addLocker = "INSERT INTO lockers (type, image) VALUES (?,?)";
	private final static String getAllLockers = "SELECT * FROM lockers";
	private final static String getLockerById = "SELECT * FROM lockers WHERE (id = ?)";
	private final static String getLockersWithoutRequest = "SELECT lockers.id, lockers.type, lockers.image "
			+ "FROM lockers LEFT JOIN lockers_has_confirmed_requests ON lockers_has_confirmed_requests.lockers_id=lockers.id "
			+ "WHERE (confirmed_request_id is null)";
	private final static String getFreeLockers1 = "SELECT lockers.id, lockers.type, lockers.image, lockers.status "
			+ "FROM lockers LEFT JOIN lockers_has_confirmed_requests ON lockers_has_confirmed_requests.lockers_id=lockers.id "
			+ "JOIN confirmed_requests ON lockers_has_confirmed_requests.confirmed_request_id=confirmed_requests.booking_request_id "
			+ "JOIN booking_requests ON booking_requests.id=confirmed_requests.booking_request_id "
			+ "WHERE (end_date != ?) OR (start_date != ?)";
	
	
	private final static String GET_FREE_LOCKERS = "SELECT lockers.id, lockers.type, lockers.image, lockers.status "
			+ "FROM lockers JOIN lockers_has_confirmed_requests LHCR ON LHCR.lockers_id = lockers.id "
			+ "JOIN booking_requests BR ON BR.id = LHCR.confirmed_request_id "
			+ "JOIN confirmed_requests CR ON CR.booking_request_id = BR.id && CR.status = 0 "
			+ "WHERE NOT(start_date > ? OR end_date < ?) OR NOT (start_date < ? AND end_date > ?)";
	private final static String updateLocker = "UPDATE lockers SET (type, image) VALUES (?,?) WHERE id=? ";
			
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
		} finally {
			try {
				cp.closeConnection(con, pst);
			} catch (ConnectionPoolException e) {
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
			while (resultSet.next()) {

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
		} finally {
			try {
				cp.closeConnection(con, pst, resultSet);
			} catch (ConnectionPoolException e) {
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

		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				cp.closeConnection(con, pst, resultSet);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
		return locker;
	}

	@Override
	public Set<Locker> getFreeLockers(Date startDate, Date endDate) throws DAOException {
		Set<Locker> result = new HashSet<>();

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		Locker locker;

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(getAllLockers);
			resultSet = pst.executeQuery();
			while (resultSet.next()) {				
				locker = new Locker();
				int id = resultSet.getInt(1);
				String type = resultSet.getString(2);
				String image = resultSet.getString(3);
				locker.setId(id);
				locker.setSize(LockerType.valueOf(type));
				locker.setImagePath(image);
				result.add(locker);
			}

			resultSet.close();
			pst.close();

			pst = con.prepareStatement(GET_FREE_LOCKERS);
			pst.setDate(1, startDate);
			pst.setDate(2, endDate);
			pst.setDate(3, startDate);
			pst.setDate(4, endDate);
			

			resultSet = pst.executeQuery();

			while (resultSet.next()) {
				System.out.println("free lokers");
				locker = new Locker();
				int id = resultSet.getInt(1);
				String type = resultSet.getString(2);
				String image = resultSet.getString(3);
				locker.setId(id);
				locker.setSize(LockerType.valueOf(type));
				locker.setImagePath(image);
				result.remove(locker);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				cp.closeConnection(con, pst, resultSet);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}

		return result;
	}

	@Override
	public void updateLocker(Locker locker) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(updateLocker);
			pst.setString(1, locker.getSize().toString());
			pst.setString(2, locker.getImagePath());
			pst.setInt(3, locker.getId());
			pst.executeUpdate();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				cp.closeConnection(con, pst);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}

	}

}
