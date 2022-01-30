package com.epam.ds.controller.impl.gotocommand;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.ds.controller.Command;
import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.UserService;
import com.epam.ds.hostel.service.exception.ServiceException;

public class GoToUserList implements Command{
	private final static Logger log = Logger.getLogger(GoToUserList.class);
	private final static String GO_TO_USER_LIST_PAGE = "/WEB-INF/jsp/userList.jsp";
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		String tableName = request.getParameter("table_name");
		//String userId = request.getParameter("user_id");
		//System.out.println(userId);
		request.setAttribute("table_name", tableName);
		//request.setAttribute("user_id", userId);
		try {
			List<User> users = userService.findAllUsers();
			request.setAttribute("users", users);						
		} catch (ServiceException e) {
			log.error(e);
			e.printStackTrace();
		}
		System.out.println("request send");
		RequestDispatcher dispatcher = request.getRequestDispatcher(GO_TO_USER_LIST_PAGE);
		dispatcher.forward(request, response);
				
		
	}

}
