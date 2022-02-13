package com.epam.ds.controller.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.epam.ds.controller.Command;
import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.entity.UserDetail;
import com.epam.ds.hostel.service.ServiceFactory;
import com.epam.ds.hostel.service.UserService;
import com.epam.ds.hostel.service.exception.ServiceException;

public class SaveUserImageCommand implements Command{
	private final static Logger log = Logger.getLogger(SaveUserImageCommand.class);
	private final static String GO_TO_ERROR_PAGE = "Controller?command=GO_TO_ERROR_PAGE";
	private final static String GO_TO_ACCAUNT_PAGE = "Controller?command=GO_TO_ACCAUNT_PAGE";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String login = (String)session.getAttribute("login");
		try {
			User user = userService.findByLogin(login);
			UserDetail userDetail = user.getDetail();
			
			if (isMultipart) {
				String fileName = null;
				ServletFileUpload upload = new ServletFileUpload();
				FileItemIterator iter = upload.getItemIterator(request);								
				while (iter.hasNext()) {
					FileItemStream item = iter.next();
					InputStream stream = item.openStream();
					if (!item.isFormField()){						
						fileName = item.getName();
						BufferedInputStream bStream = new BufferedInputStream(stream);
						byte[] array = bStream.readAllBytes();
						bStream.close();
						
						FileOutputStream fileOutputStream = 
								new FileOutputStream(
										new File("C:\\Users\\yello\\OneDriveDenWed\\OneDrive\\eclipse-workspace\\hostel-web-app\\src\\main\\webapp\\images\\usersImages\\"
								+user.getId()+"id"+item.getName()));
						for (byte element : array) {
							fileOutputStream.write(element);
						}
						fileOutputStream.close();
					}
				}
				
				String imagePuthForDB = "/images/usersImages/"+user.getId()+"id"+fileName;
				userDetail.setImage(imagePuthForDB);
				user.setDetail(userDetail);
				userService.updateUserDetail(user);
			}
			
			response.sendRedirect(GO_TO_ACCAUNT_PAGE);
			
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(GO_TO_ERROR_PAGE);
		}
		
		
	}

}
