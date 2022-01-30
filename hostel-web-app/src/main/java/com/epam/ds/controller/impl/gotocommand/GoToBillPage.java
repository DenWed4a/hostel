package com.epam.ds.controller.impl.gotocommand;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.ds.controller.Command;
import com.epam.ds.hostel.entity.Bill;
import com.epam.ds.hostel.service.BillService;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.exception.ServiceException;


public class GoToBillPage implements Command{
	private final static Logger log = Logger.getLogger(GoToBillPage.class);
	private final static String GO_TO_BILL_PAGE = "/WEB-INF/jsp/billPage.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int billId = Integer.parseInt(request.getParameter("booking_id"));
		ServiceFactory factory = ServiceFactory.getInstance();
		BillService billService = factory.getBillService();
		Bill bill;
		try {
			bill =  billService.findBill(billId);
			request.setAttribute("bill", bill);
		} catch (ServiceException e) {
			log.error(e);
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(GO_TO_BILL_PAGE);
		dispatcher.forward(request, response);
	}

}
