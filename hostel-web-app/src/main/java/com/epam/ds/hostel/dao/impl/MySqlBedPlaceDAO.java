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

import com.epam.ds.hostel.dao.BedPlaceDAO;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPool;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPoolException;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.BedPlace;

import com.epam.ds.hostel.entity.status.EntityStatus.BedPlaceStatus;

public class MySqlBedPlaceDAO implements BedPlaceDAO {
	private ConnectionPool cp = ConnectionPool.getInstance();

	private final static String ADD_BED_PLACE = "INSERT INTO bed_places (tier, status, room) VALUES (?,?,?)";
	private final static String GET_ALL_PLACES = "SELECT * FROM bed_places";
	private final static String GET_PLACE_BY_ID = "SELECT * FROM bed_places WHERE (id = ?)";	
	private final static String GET_FREE_PLACES = 
			"SELECT * FROM bed_places JOIN bed_place_has_confirmed_request BHR ON BHR.bed_place_id=bed_places.id "
			+ "JOIN	booking_requests BR ON BR.id = BHR.confirmed_request_id "
			+ "JOIN confirmed_requests CR ON CR.booking_request_id = BR.id && CR.status = 0 "
			+ "WHERE NOT(start_date > ? OR end_date < ?) OR NOT (start_date < ? AND end_date > ?)";
	private final static String UPDATE_BED_PLACE = "UPDATE bed_places SET (tier, image, room, status) VALUES(?,?,?,?) WHERE id=?";
	private final static String ID = "id";
	private final static String TIER = "tier";
	private final static String IMAGE = "image";
	private final static String STATUS = "status";
	private final static String ROOM = "room";

	@Override
	public void addNewBedPlace(BedPlace place) throws DAOException {

		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(ADD_BED_PLACE);
			pst.setInt(1, place.getTier());
			pst.setInt(2, place.getStatus().getTitle());
			pst.setInt(3, place.getRoom());
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
	public List<BedPlace> getAllBedPlaces() throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		List<BedPlace> result = new ArrayList<>();

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(GET_ALL_PLACES);
			resultSet = pst.executeQuery();
			while (resultSet.next()) {

				int id = resultSet.getInt(ID);
				int tier = resultSet.getInt(TIER);
				String imagePath = resultSet.getString(IMAGE);
				int status  = resultSet.getInt(STATUS);
				int room = resultSet.getInt(ROOM); 
				BedPlace bedPlace = new BedPlace();

				bedPlace.setId(id);
				bedPlace.setImagePath(imagePath);
				bedPlace.setTier(tier);
				bedPlace.setStatus(BedPlaceStatus.values()[status]);
				bedPlace.setRoom(room);
				result.add(bedPlace);
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
	public BedPlace getBedPlace(int id) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		BedPlace bedPlace;

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(GET_PLACE_BY_ID);
			pst.setInt(1, id);
			resultSet = pst.executeQuery();
			resultSet.next();

			int tier = resultSet.getInt(2);
			String imagePath = resultSet.getString(3);
			int status  = resultSet.getInt(4);
			int room = resultSet.getInt(5); 
			
			bedPlace = new BedPlace();
			bedPlace.setId(id);
			bedPlace.setImagePath(imagePath);
			bedPlace.setTier(tier);
			bedPlace.setStatus(BedPlaceStatus.values()[status]);
			bedPlace.setRoom(room);

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
		return bedPlace;
	}

	@Override
	public void updateBedPlace(BedPlace place) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(UPDATE_BED_PLACE);
			pst.setInt(1, place.getTier());
			pst.setString(2, place.getImagePath());
			pst.setInt(3, place.getRoom());
			pst.setInt(4, place.getStatus().getTitle());
			pst.setInt(5, place.getId());
			
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

	public Set<BedPlace> getFreeBedPlaces(Date startDate, Date endDate) throws DAOException {
		Set<BedPlace> result = new HashSet<>();

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		BedPlace bedPlace;

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(GET_ALL_PLACES);
			resultSet = pst.executeQuery();
			while (resultSet.next()) {

				bedPlace = new BedPlace();
				int id = resultSet.getInt(ID);
				int tier = resultSet.getInt(TIER);
				String image = resultSet.getString(IMAGE);
				int status = resultSet.getInt(STATUS);
				int room = resultSet.getInt(ROOM);
				bedPlace.setId(id);
				bedPlace.setTier(tier);
				bedPlace.setImagePath(image);
				bedPlace.setRoom(room);
				bedPlace.setStatus(BedPlaceStatus.values()[status]);
				result.add(bedPlace);
			}

			resultSet.close();
			pst.close();

			pst = con.prepareStatement(GET_FREE_PLACES);
			pst.setDate(1, startDate);
			pst.setDate(2, endDate);
			pst.setDate(3, startDate);
			pst.setDate(4, endDate);

			resultSet = pst.executeQuery();

			while (resultSet.next()) {
				bedPlace = new BedPlace();
				int id = resultSet.getInt(ID);
				int tier = resultSet.getInt(TIER);
				String image = resultSet.getString(IMAGE);
				int status = resultSet.getInt(STATUS);
				int room = resultSet.getInt(ROOM);
				bedPlace.setId(id);
				bedPlace.setTier(tier);
				bedPlace.setImagePath(image);
				bedPlace.setRoom(room);
				bedPlace.setStatus(BedPlaceStatus.values()[status]);
				result.remove(bedPlace);
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

}
