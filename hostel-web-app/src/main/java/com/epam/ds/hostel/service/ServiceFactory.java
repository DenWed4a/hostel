package com.epam.ds.hostel.service;

import com.epam.ds.hostel.service.impl.BookingRequestServiceImpl;
import com.epam.ds.hostel.service.impl.UserRoleServiceImpl;
import com.epam.ds.hostel.service.impl.UserServiceImpl;

public final class ServiceFactory {
	
	private final static ServiceFactory instance = new ServiceFactory();
	private final UserService userService = new UserServiceImpl();
	private final UserRoleService userRoleService = new UserRoleServiceImpl();
	private final BookingRequestService bookingRequestService = new BookingRequestServiceImpl();
	
	
	
	private ServiceFactory() {}

	public static ServiceFactory getInstance() {
		return instance;
	}

	public UserService getUserService() {
		return userService;
	}

	public UserRoleService getUserRoleService() {
		return userRoleService;
	}

	public BookingRequestService getBookingRequestService() {
		return bookingRequestService;
	}
	
	

}
