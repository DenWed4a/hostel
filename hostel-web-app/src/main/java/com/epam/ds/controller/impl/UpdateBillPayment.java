package com.epam.ds.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.ds.controller.Command;
import com.epam.ds.hostel.entity.Bill;
import com.epam.ds.hostel.service.BillService;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.exception.ServiceException;

public class UpdateBillPayment implements Command{
	private final static Logger log = Logger.getLogger(UpdateBillPayment.class); 
	private final static String GO_TO_ERROR_PAGE = "Controller?command=GO_TO_ERROR_PAGE";
	private final static String GO_TO_CONFIRMED_REQUESTS = "Controller?command=GO_TO_BOOKING_REQUESTS_PAGE&table=confirmed&button=active";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String billId = request.getParameter("bill_id");
		ServiceFactory factory = ServiceFactory.getInstance();
		BillService billService = factory.getBillService();
		
		Bill bill;
		try {
			bill = billService.findBill(Integer.parseInt(billId));
			billService.confirmPayment(bill);
			response.sendRedirect(GO_TO_CONFIRMED_REQUESTS);
		}  catch (ServiceException | NumberFormatException e) {
			log.error(e);
			response.sendRedirect(GO_TO_ERROR_PAGE);
		}
		
		
		
	}

}
