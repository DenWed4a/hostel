package com.epam.ds.hostel.dao;

import java.util.List;

import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.Bill;

public interface BillDAO {
	Bill findBill(int billID) throws DAOException;
	Bill findBillByBookingRequestId(int id) throws DAOException;
	List<Bill> findAllBills() throws DAOException;
	void saveBill(Bill bill) throws DAOException;
	void updateBill(Bill bill) throws DAOException;
	void deleteBill(int billId) throws DAOException;
	

}
