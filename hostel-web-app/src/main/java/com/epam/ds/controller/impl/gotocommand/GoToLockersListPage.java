package com.epam.ds.controller.impl.gotocommand;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.ds.controller.AdminCommand;

import com.epam.ds.hostel.entity.Locker;
import com.epam.ds.hostel.service.LockerService;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.exception.ServiceException;

public class GoToLockersListPage implements AdminCommand{
	private final static Logger log = Logger.getLogger(GoToLockersListPage.class);
	private final static String GO_TO_LOCKER_LIST_PAGE = "/WEB-INF/jsp/lockersList.jsp";
	private final static String GO_TO_ERROR_PAGE = "Controller?command=GO_TO_ERROR_PAGE";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		LockerService lockerService = factory.getLockerService();
		String button = request.getParameter("button");
		try {
			List<Locker> allLockers = lockerService.getAllLockers();
			request.setAttribute("lockerList", allLockers);
			request.setAttribute("page", "lockers_list");
			request.setAttribute("button", button);
			RequestDispatcher dispatcher = request.getRequestDispatcher(GO_TO_LOCKER_LIST_PAGE);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(GO_TO_ERROR_PAGE);
		}
		
	}

}
