package com.epam.ds.hostel.entity;

public enum BookingRequestStatus {
	NEW(0), CONFIRMED(1), DELETED(2);
	
	private int title;
	
	private BookingRequestStatus(int title) {
		this.title = title;
	}

	public int getTitle() {
		return title;
	}

	public void setTitle(int title) {
		this.title = title;
	}
	

}
