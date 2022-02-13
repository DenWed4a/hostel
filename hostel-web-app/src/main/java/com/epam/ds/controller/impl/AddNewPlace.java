package com.epam.ds.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.ds.controller.AdminCommand;
import com.epam.ds.hostel.entity.BedPlace;
import com.epam.ds.hostel.entity.status.EntityStatus.BedPlaceStatus;
import com.epam.ds.hostel.service.BedPlaceService;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.exception.ServiceException;

public class AddNewPlace implements AdminCommand{
	private final static Logger log = Logger.getLogger(AddNewPlace.class);
	private final static String GO_TO_ERROR_PAGE = "Controller?command=GO_TO_ERROR_PAGE";
	private final static String GO_TO_PLACES_LIST = "Controller?command=GO_TO_PLACES_LIST_PAGE";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String room = request.getParameter("room");
		String tier = request.getParameter("tier");
		String status = request.getParameter("status");
		
		ServiceFactory factory = ServiceFactory.getInstance();
		BedPlaceService bedPlaceService = factory.getBedPlaceService();
		BedPlace bedPlace = new BedPlace();
		bedPlace.setRoom(Integer.parseInt(room));
		bedPlace.setStatus(BedPlaceStatus.values()[Integer.parseInt(status)]);
		bedPlace.setTier(Integer.parseInt(tier));
		
		try {
			bedPlaceService.addNewBedPlace(bedPlace);
			response.sendRedirect(GO_TO_PLACES_LIST);
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(GO_TO_ERROR_PAGE);
		}
		
		
	}

}
