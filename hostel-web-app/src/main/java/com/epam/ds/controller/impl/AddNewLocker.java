package com.epam.ds.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.ds.controller.AdminCommand;
import com.epam.ds.hostel.entity.BedPlace;
import com.epam.ds.hostel.entity.Locker;
import com.epam.ds.hostel.entity.LockerType;
import com.epam.ds.hostel.entity.status.EntityStatus.BedPlaceStatus;
import com.epam.ds.hostel.entity.status.EntityStatus.LockerStatus;
import com.epam.ds.hostel.service.BedPlaceService;
import com.epam.ds.hostel.service.LockerService;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.exception.ServiceException;

public class AddNewLocker implements AdminCommand{

	private final static Logger log = Logger.getLogger(AddNewLocker.class);
	private final static String GO_TO_ERROR_PAGE = "Controller?command=GO_TO_ERROR_PAGE";
	private final static String GO_TO_LOCKER_LIST = "Controller?command=GO_TO_LOCKERS_LIST_PAGE";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = request.getParameter("type");
		String status = request.getParameter("status");
		
		ServiceFactory factory = ServiceFactory.getInstance();
		LockerService lockerService = factory.getLockerService();
		Locker locker = new Locker();
		locker.setSize(LockerType.valueOf(type));
		locker.setStatus(LockerStatus.values()[Integer.parseInt(status)]);
		
		try {
			lockerService.addNewLocker(locker);
			response.sendRedirect(GO_TO_LOCKER_LIST);
		} catch (ServiceException | NumberFormatException e) {
			log.error(e);
			response.sendRedirect(GO_TO_ERROR_PAGE);
		}
		
		
	}
}
