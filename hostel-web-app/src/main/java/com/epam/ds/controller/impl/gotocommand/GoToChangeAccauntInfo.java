package com.epam.ds.controller.impl.gotocommand;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.ds.controller.Command;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.dao.impl.MySqlUserDAO;
import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.entity.UserDetail;

public class GoToChangeAccauntInfo implements Command{
	
	private final static String goToChangeAccauntInfo ="/WEB-INF/jsp/changeAccauntInfoPage.jsp";
	private final static Logger log = Logger.getLogger(GoToChangeAccauntInfo.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		HttpSession session = request.getSession();
		MySqlUserDAO dao = new MySqlUserDAO();
		String login = (String)session.getAttribute("login");
		User user;
		try {
			user = dao.findByLogin(login);
			UserDetail userDetail = user.getDetail();
			request.setAttribute("user", user);
			request.setAttribute("detail", userDetail);
		RequestDispatcher dispatcher = request.getRequestDispatcher(goToChangeAccauntInfo);
		dispatcher.forward(request, response);
		} catch (DAOException e) {
			log.error(e);
			
		}
		
	}

}
