package com.epam.ds.hostel.service;

import java.util.List;


import com.epam.ds.hostel.entity.Bill;
import com.epam.ds.hostel.service.exception.ServiceException;

public interface BillService {
	Bill findBill(int billID) throws ServiceException;
	Bill findBillByRequestId(int id) throws ServiceException;
	List<Bill> findAllBills() throws ServiceException;
	void saveBill(Bill bill) throws ServiceException;
	void updateBill(Bill bill) throws ServiceException;
	void deleteBill(int billId) throws ServiceException;

}
