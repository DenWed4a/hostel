package com.epam.ds.hostel.service;

import com.epam.ds.hostel.service.impl.BedPlaceServiceImpl;
import com.epam.ds.hostel.service.impl.BillServiceImpl;

import com.epam.ds.hostel.service.impl.BookingRequestServiceImpl;
import com.epam.ds.hostel.service.impl.ConfirmedRequestServiceImpl;
import com.epam.ds.hostel.service.impl.LockerServiceImpl;
import com.epam.ds.hostel.service.impl.ReviewServiceImpl;
import com.epam.ds.hostel.service.impl.UserRoleServiceImpl;
import com.epam.ds.hostel.service.impl.UserServiceImpl;

public final class ServiceFactory {
	
	private final static ServiceFactory instance = new ServiceFactory();
	private final UserService userService = new UserServiceImpl();
	private final UserRoleService userRoleService = new UserRoleServiceImpl();
	private final BookingRequestService bookingRequestService = new BookingRequestServiceImpl();
	private final ConfirmedRequestService confirmedRequestService = new ConfirmedRequestServiceImpl();
	private final BedPlaceService bedPlaceService = new BedPlaceServiceImpl();
	private final LockerService lockerService = new LockerServiceImpl(); 
	private final BillService billService = new BillServiceImpl();
	private final ReviewService reviewService = new ReviewServiceImpl();
	
	
	
	
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

	public ConfirmedRequestService getConfirmedRequestService() {
		return confirmedRequestService;
	}

	public BedPlaceService getBedPlaceService() {
		return bedPlaceService;
	}

	public LockerService getLockerService() {
		return lockerService;
	}



	public BillService getBillService() {
		return billService;
	}

	public ReviewService getReviewService() {
		return reviewService;
	}
	
	
	
	
	
	
	

}
