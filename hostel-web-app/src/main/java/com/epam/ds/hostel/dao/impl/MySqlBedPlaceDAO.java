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
import com.epam.ds.hostel.entity.status.EntityStatus.BedPlaceStatus;

public class MySqlBedPlaceDAO implements BedPlaceDAO {
	private ConnectionPool cp = ConnectionPool.getInstance();

	private final static String ADD_BED_PLACE = "INSERT INTO bed_places (tier, image, room) VALUES (?,?,?)";
	private final static String GET_ALL_PLACES = "SELECT * FROM bed_places";
	private final static String GET_PLACE_BY_ID = "SELECT * FROM bed_places WHERE (id = ?)";
	private final static String GET_PLACES_WITHOUT_REQUEST = "SELECT bed_places.id, bed_places.tier, bed_places.image "
			+ "FROM bed_places LEFT JOIN bed_place_has_confirmed_request ON bed_place_has_confirmed_request.bed_place_id=bed_places.id "
			+ "WHERE (confirmed_request_id is null)";
	private final static String GET_FREE_PLACES = "SELECT DISTINCTROW bed_places.id, bed_places.tier, bed_places.image "
			+ "FROM bed_places  JOIN bed_place_has_confirmed_request ON bed_place_has_confirmed_request.bed_place_id=bed_places.id "
			+ "JOIN confirmed_requests ON bed_place_has_confirmed_request.confirmed_request_id=confirmed_requests.booking_request_id "
			+ "JOIN booking_requests ON booking_requests.id=confirmed_requests.booking_request_id "
			+ "WHERE (start_date >= ?) OR (end_date <= ?)";
	private final static String UPDATE_BED_PLACE = "UPDATE bed_places SET (tier, image, room, status) VALUES(?,?,?,?) WHERE id=?";

	@Override
	public void addNewBedPlace(BedPlace place) throws DAOException {

		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(ADD_BED_PLACE);
			pst.setInt(1, place.getTier());
			pst.setString(2, place.getImagePath());
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

				int id = resultSet.getInt(1);
				int tier = resultSet.getInt(2);
				String imagePath = resultSet.getString(3);
				int status  = resultSet.getInt(4);
				int room = resultSet.getInt(5); 
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

	public List<BedPlace> getFreeBedPlaces(Date startDate, Date endDate) throws DAOException {
		List<BedPlace> result = new ArrayList<>();

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		BedPlace bedPlace;

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(GET_PLACES_WITHOUT_REQUEST);
			resultSet = pst.executeQuery();
			while (resultSet.next()) {

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

			pst = con.prepareStatement(GET_FREE_PLACES);
			pst.setDate(2, startDate);
			pst.setDate(1, endDate);

			resultSet = pst.executeQuery();

			while (resultSet.next()) {

				bedPlace = new BedPlace();
				int id = resultSet.getInt(1);
				int tier = resultSet.getInt(2);
				String image = resultSet.getString(3);
				bedPlace.setId(id);
				bedPlace.setTier(tier);
				bedPlace.setImagePath(image);
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

}