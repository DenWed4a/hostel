package com.epam.ds.controller.impl.gotocommand;

import java.io.BufferedInputStream;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.util.Streams;

import com.epam.ds.controller.Command;

public class GoTo_Test implements Command {
	private final static String GO_TO_TEST = "/WEB-INF/jsp/Test.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		ServletFileUpload upload = new ServletFileUpload();
		
		
		

		if (isMultipart) {
			FileItemIterator iter = upload.getItemIterator(request);
			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				String name = item.getFieldName();
				InputStream stream = item.openStream();
				if (item.isFormField()) {
					System.out.println("Form field " + name + " with value " + Streams.asString(stream) + " detected.");
				} else {
					System.out.println("File field " + name + " with file name " + item.getName() + " detected.");

					BufferedInputStream bStream = new BufferedInputStream(stream);
					byte[] array = bStream.readAllBytes();
					bStream.close();
					
					FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\yello\\OneDriveDenWed\\OneDrive\\eclipse-workspace\\hostel-web-app\\src\\main\\webapp\\images\\usersImages\\23433.jpg");
					for (byte element : array) {
						fileOutputStream.write(element);
					}
					fileOutputStream.close();
				}
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(GO_TO_TEST);
		dispatcher.forward(request, response);

	}
}