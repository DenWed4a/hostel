package com.epam.ds.controller.impl.gotocommand;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.ds.controller.Command;
import com.epam.ds.hostel.entity.BedPlace;
import com.epam.ds.hostel.service.BedPlaceService;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.exception.ServiceException;

public class GoToPlacesListPage implements Command{
	private final static Logger log = Logger.getLogger(GoToPlacesListPage.class);
	private final static String GO_TO_PLACES_LIST_PAGE = "/WEB-INF/jsp/placesList.jsp";
	private final static String GO_TO_ERROR_PAGE = "Controller?command=GO_TO_ERROR_PAGE";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory factory  = ServiceFactory.getInstance();
		BedPlaceService bedPlaceService = factory.getBedPlaceService();
		String button = request.getParameter("button");
		try {
			List<BedPlace> allPlaces = bedPlaceService.getAllBedPlaces();
			request.setAttribute("placesList", allPlaces);
			request.setAttribute("page", "places_list");
			request.setAttribute("button", button);
			RequestDispatcher dispatcher = request.getRequestDispatcher(GO_TO_PLACES_LIST_PAGE);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(GO_TO_ERROR_PAGE);
		} 
		
	}

}
