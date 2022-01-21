package com.epam.ds.hostel.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.epam.ds.hostel.dao.BillDAO;
import com.epam.ds.hostel.dao.DAOFactory;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.entity.Bill;
import com.epam.ds.hostel.service.BillService;
import com.epam.ds.hostel.service.exception.ServiceException;

public class BillServiceImpl implements BillService{

	@Override
	public Bill findBill(int billID) throws ServiceException {
		Bill bill;
		DAOFactory factory = DAOFactory.getInstance();
		BillDAO billDAO = factory.getMySqlBillDAO();
		try {
			bill = billDAO.findBill(billID);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return bill;
	}

	@Override
	public List<Bill> findAllBills() throws ServiceException {
		List<Bill> result = new ArrayList<>();
		DAOFactory factory = DAOFactory.getInstance();
		BillDAO billDAO = factory.getMySqlBillDAO();
		
		try {
			result = billDAO.findAllBills();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public void saveBill(Bill bill) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		BillDAO billDAO = factory.getMySqlBillDAO();
		
		try {
			billDAO.saveBill(bill);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public void updateBill(Bill bill) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		BillDAO billDAO = factory.getMySqlBillDAO();
		
		try {
			billDAO.updateBill(bill);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public void deleteBill(int billId) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		BillDAO billDAO = factory.getMySqlBillDAO();
		
		try {
			billDAO.deleteBill(billId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
