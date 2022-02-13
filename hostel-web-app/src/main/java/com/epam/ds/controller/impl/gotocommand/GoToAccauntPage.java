package com.epam.ds.controller.impl.gotocommand;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import com.epam.ds.controller.UsersCommand;
import com.epam.ds.hostel.entity.BookingRequest;
import com.epam.ds.hostel.entity.ConfirmedRequest;
import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.entity.UserDetail;
import com.epam.ds.hostel.service.BookingRequestService;
import com.epam.ds.hostel.service.ConfirmedRequestService;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.UserService;
import com.epam.ds.hostel.service.exception.ServiceException;

public class GoToAccauntPage implements UsersCommand{
	private final static String GO_TO_ACCAUNT_PAGE = "/WEB-INF/jsp/accauntPage.jsp";
	private final static Logger log = Logger.getLogger(GoToAccauntPage.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		ConfirmedRequestService confirmedRequestService = factory.getConfirmedRequestService();
		BookingRequestService bookingRequestService = factory.getBookingRequestService();
		
		String buttonString = request.getParameter("button");
		String login = (String)session.getAttribute("login");
		Map<BookingRequest, ConfirmedRequest> historyMap = new HashMap<>();
				
		User user;
		try {
			user = userService.findByLogin(login);
			UserDetail userDetail = user.getDetail();
			request.setAttribute("user", user);
			request.setAttribute("detail", userDetail);			
			request.setAttribute("page", "main_page");
			request.setAttribute("button", buttonString);
						
			List<BookingRequest> bRequests = bookingRequestService.findBookingRequestsByUserId(user.getId());			
			for(int i = 0; i < bRequests.size(); i++) {
				ConfirmedRequest confirmedRequest = confirmedRequestService.findConfirmedRequest((bRequests.get(i).getId()));
				if(confirmedRequest!=null) {
					historyMap.put(bRequests.get(i), confirmedRequest);
				}
			}
			
			request.setAttribute("history", historyMap);
			
		
			RequestDispatcher dispatcher = request.getRequestDispatcher(GO_TO_ACCAUNT_PAGE);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			log.error(e.getStackTrace(), e);
			
		}	
		

		
	}

}
