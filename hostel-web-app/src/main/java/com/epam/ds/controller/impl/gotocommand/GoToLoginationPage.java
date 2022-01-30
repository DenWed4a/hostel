package com.epam.ds.controller.impl.gotocommand;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.ds.controller.Command;
import com.epam.ds.hostel.dao.UserDAO;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.dao.impl.MySqlUserDAO;
import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.entity.UserDetail;

public class GoToLoginationPage implements Command{
	
	private final static String goToLogination = "/WEB-INF/jsp/logination.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		UserDAO userDAO = new MySqlUserDAO();
		User user;
		try {
			user = userDAO.findById(1);
			UserDetail detail = user.getDetail();
			request.setAttribute("user", user);
			request.setAttribute("detail", detail);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(goToLogination);
		dispatcher.forward(request, response);
		
		
	}

}
