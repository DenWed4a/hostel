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

public class GoToAccauntPage implements Command{
	private final static String goToAccauntPage = "/WEB-INF/jsp/accauntPage.jsp";
	private final static Logger log = Logger.getLogger(GoToAccauntPage.class);

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
			request.setAttribute("page", "main_page");
		} catch (DAOException e) {
			log.error(e.getStackTrace(), e);
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(goToAccauntPage);
		dispatcher.forward(request, response);

		
	}

}
