package com.epam.ds.hostel.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.ds.hostel.dao.BedPlaceDAO;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPool;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPoolException;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.BedPlace;

public class MySqlBedPlaceDAO implements BedPlaceDAO{
	private ConnectionPool cp = ConnectionPool.getInstance();
	
	private final static String addBedPlace = "INSERT INTO bed-places (tier, image) VALUES (?)";
	private final static String getAllPlaces = "SELECT * FROM bed-places";
	private final static String getPlaceById = "SELECT * FROM bed-places WHERE (id = ?)";
	private final static String getPlacesWithoutRequests = "SELECT bed_places.id, bed_places.tier, bed_places.image "
			+ "FROM bed_places LEFT JOIN bed_place_has_confirmed_request ON bed_place_has_confirmed_request.bed_place_id=bed_places.id "
			+ "WHERE (confirmed_request_id is null)";
	private final static String getFreePlaces = "SELECT bed_places.id, bed_places.tier, bed_places.image "
			+ "FROM bed_places  JOIN bed_place_has_confirmed_request ON bed_place_has_confirmed_request.bed_place_id=bed_places.id "
			+ "JOIN confirmed_requests ON bed_place_has_confirmed_request.confirmed_request_id=confirmed_requests.booking_request_id "
			+ "JOIN booking_requests ON booking_requests.id=confirmed_requests.booking_request_id "
			+ "WHERE (end_date <= ?) OR (start_date >= ?);
	

	@Override
	public void addNewBedPlace(BedPlace place) throws DAOException {
		
		Connection con = null;
		PreparedStatement pst = null;
		try { 
			con = cp.takeConnection();
			pst = con.prepareStatement(addBedPlace);
			pst.setInt(1, place.getTier());
			pst.setString(2, place.getImagePath());
			pst.executeUpdate();
	
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}finally {
			try {
				cp.closeConnection(con, pst);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}

		}
		
		
		
	}

	@Override
	public List<BedPlace> getAllBedPlaces() throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		List<BedPlace> result = new ArrayList<>();
		
		
		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(getAllPlaces);
			resultSet = pst.executeQuery();
			while(resultSet.next()) {
				
				int id = resultSet.getInt(1);
				int tier = resultSet.getInt(2);
				String imagePath = resultSet.getString(3);
				BedPlace bedPlace = new BedPlace();
				
				bedPlace.setId(id);
				bedPlace.setImagePath(imagePath);
				bedPlace.setTier(tier);
				result.add(bedPlace);
			}

			
		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		}finally {
			try {
				cp.closeConnection(con, pst, resultSet);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
				
		return result;
	}

	@Override
	public BedPlace getBedPlace(int id) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		BedPlace bedPlace;
		
		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(getPlaceById);
			pst.setInt(1, id);
			resultSet = pst.executeQuery();
			resultSet.next();
			
			
			int tier = resultSet.getInt(2);
			String imagePath = resultSet.getString(3);
			bedPlace = new BedPlace();
			
			bedPlace.setId(id);
			bedPlace.setImagePath(imagePath);
			bedPlace.setTier(tier);
			
		}catch (SQLException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		}finally {
			try {
				cp.closeConnection(con, pst, resultSet);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
		return bedPlace;
	}

	@Override
	public void deleteBedPlace(int id) throws DAOException {
		// TODO Auto-generated method stub
		
	}
	
	public List<BedPlace> findFreeBedPlaces(Date startDate, Date endDate) throws DAOException{
		List<BedPlace> result = new ArrayList<>();
		 
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		BedPlace bedPlace;
		
		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(getPlacesWithoutRequests);
			resultSet = pst.executeQuery();
			while(resultSet.next()) {
				
				bedPlace = new BedPlace();
				int id = resultSet.getInt(1);
				int tier = resultSet.getInt(2);
				String image = resultSet.getString(3);
				bedPlace.setId(id);
				bedPlace.setTier(tier);
				bedPlace.setImagePath(image);
				result.add(bedPlace);
			}
			
			resultSet.close();
			pst.close();
			
			pst = con.prepareStatement(getFreePlaces);
			pst.setDate(1, startDate);
			pst.setDate(2, endDate);
			
			resultSet = pst.executeQuery();
			
			while(resultSet.next()) {
				
				bedPlace = new BedPlace();
				int id = resultSet.getInt(1);
				int tier = resultSet.getInt(2);
				String image = resultSet.getString(3);
				bedPlace.setId(id);
				bedPlace.setTier(tier);
				bedPlace.setImagePath(image);
				result.add(bedPlace);
			}
			
			
			
		}catch (SQLException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		}finally {
			try {
				cp.closeConnection(con, pst, resultSet);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
		
		return result;
		
		
	}

}
