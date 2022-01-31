package com.epam.ds.hostel.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.epam.ds.hostel.dao.BillDAO;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPool;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPoolException;
import com.epam.ds.hostel.dao.creator.BillCreator;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.Bill;
import com.epam.ds.hostel.entity.BillStatus;

public class MySqlBillDAO implements BillDAO {
	private ConnectionPool cp = ConnectionPool.getInstance();
	private final static String ADD_NEW_BILL = "INSERT INTO BILLS (total_amount, booking_request_id) VALUES (?,?)";
	private final static String FIND_BILL_BY_ID = "SELECT * FROM BILLS WHERE ID = ?";
	private final static String FIND_ALL_BILLS = "SELECT * FROM BILLS";
	private final static String UPDATE_BILL = "UPDATE BILLS SET total_amount=?, status=? WHERE(ID = ?)";
	private final static String DELETE_BILL = "UPDATE BILLS SET status=3 WHERE(ID = ?)";
	private final static String FIND_BILL_BY_REQUEST = "SELECT * FROM bills WHERE(booking_request_id = ?)";
	private final static String CONFIRM_PAYMENT = "UPDATE bills SET status = 1 WHERE id = ?";
	private final static String SET_DATE_OF_PAYMENT = "UPDATE confirmed_requests SET date_of_payment = ? WHERE bills_id = ?";

	@Override
	public Bill findBill(int billID) throws DAOException {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		Bill bill;
		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(FIND_BILL_BY_ID);
			pst.setInt(1, billID);
			resultSet = pst.executeQuery();
			resultSet.next();
			bill = BillCreator.getInstance().create(resultSet);

		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				cp.closeConnection(con, pst, resultSet);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}

		}

		return bill;
	}

	@Override
	public List<Bill> findAllBills() throws DAOException {
		List<Bill> result = new ArrayList<>();

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		Bill bill;

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(FIND_ALL_BILLS);
			resultSet = pst.executeQuery();

			while (resultSet.next()) {
				bill = BillCreator.getInstance().create(resultSet);
				result.add(bill);
			}

		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
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
	public void saveBill(Bill bill) throws DAOException {

		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(ADD_NEW_BILL);
			pst.setDouble(1, bill.getTotalAmount());
			pst.setInt(2, bill.getBookingRequestID());
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
	public void updateBill(Bill bill) throws DAOException {
		PreparedStatement pst = null;
		Connection con = null;
		int id = bill.getId();
		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(UPDATE_BILL);
			pst.setDouble(1, bill.getTotalAmount());
			pst.setInt(2, bill.getStatus().getTitle());
			pst.setInt(3, id);
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
	public void deleteBill(int billId) throws DAOException {
		PreparedStatement pst = null;
		Connection con = null;

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(DELETE_BILL);
			pst.setInt(1, billId);
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
	public Bill findBillByBookingRequestId(int id) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		Bill bill = null;

		try {
			con = cp.takeConnection();
			pst = con.prepareStatement(FIND_BILL_BY_REQUEST);
			pst.setInt(1, id);
			resultSet = pst.executeQuery();
			while (resultSet.next()) {
				int billId = resultSet.getInt(1);
				double tatalAmount = resultSet.getDouble(2);
				int status = resultSet.getInt(3);
				int bookingRequestId = id;
				bill = new Bill();
				bill.setBookingRequestID(bookingRequestId);
				bill.setId(billId);
				bill.setTotalAmount(tatalAmount);
				bill.setStatus(BillStatus.values()[status]);
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				cp.closeConnection(con, pst, resultSet);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}

		}

		return bill;
	}

	@Override
	public void confirmPayment(Bill bill) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = cp.takeConnection();
			con.setAutoCommit(false);
			pst = con.prepareStatement(CONFIRM_PAYMENT);
			pst.setInt(1, bill.getId());
			pst.executeUpdate();

			try {
				pst.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}

			pst = con.prepareStatement(SET_DATE_OF_PAYMENT);
			pst.setDate(1, new Date(Calendar.getInstance().getTime().getTime()));
			pst.setInt(2, bill.getId());
			pst.executeUpdate();

			con.commit();

		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {

				throw new DAOException("Error in rollback method", e1);
			}
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.setAutoCommit(true);
				}
				cp.closeConnection(con, pst);
			} catch (ConnectionPoolException | SQLException e) {
				throw new DAOException(e);
			}

		}

	}

}
