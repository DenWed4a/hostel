package com.epam.ds.controller.impl.gotocommand;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.epam.ds.controller.Command;
import com.epam.ds.util.BillTotalCalculator;


public class GoToProformaInvoicePage implements Command{
	private final static String GO_TO_PROFORMA_INVOICE_PAGE = "/WEB-INF/jsp/proformaInvoicePage.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String startDate;
		String endDate;
		String numberOfPlaces;
		String numberOfLockers;
		double totalBill;
		
		startDate =  request.getParameter("startDate");
		endDate =  request.getParameter("endDate");
		numberOfPlaces = request.getParameter("numberPlaces");
		numberOfLockers = request.getParameter("numberLokers");
		
		
		BillTotalCalculator calculator = new BillTotalCalculator();		 
		totalBill = calculator.getTotal(Date.valueOf(startDate), Date.valueOf(endDate), Integer.parseInt(numberOfPlaces), Integer.parseInt(numberOfLockers));
		
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("numberPlaces", numberOfPlaces);
		request.setAttribute("numberLockers", numberOfLockers);
		request.setAttribute("totalBill", totalBill);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(GO_TO_PROFORMA_INVOICE_PAGE);
		dispatcher.forward(request, response);
		
	}

}
