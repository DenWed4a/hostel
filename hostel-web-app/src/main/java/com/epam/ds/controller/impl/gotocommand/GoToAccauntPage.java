package com.epam.ds.controller.impl.gotocommand;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.util.Streams;

import com.epam.ds.controller.Command;
import com.epam.ds.hostel.dao.exception.DAOException;
import com.epam.ds.hostel.dao.impl.MySqlUserDAO;
import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.entity.UserDetail;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.UserService;
import com.epam.ds.hostel.service.exception.ServiceException;

public class GoToAccauntPage implements Command{
	private final static String goToAccauntPage = "/WEB-INF/jsp/accauntPage.jsp";
	private final static Logger log = Logger.getLogger(GoToAccauntPage.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		
		String buttonString = request.getParameter("button");
		String login = (String)session.getAttribute("login");
				
		User user;
		try {
			user = userService.findByLogin(login);
			UserDetail userDetail = user.getDetail();
			request.setAttribute("user", user);
			request.setAttribute("detail", userDetail);			
			request.setAttribute("page", "main_page");
			request.setAttribute("button", buttonString);
			
			
			
		} catch (ServiceException e) {
			log.error(e.getStackTrace(), e);
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(goToAccauntPage);
		dispatcher.forward(request, response);

		
	}

}
