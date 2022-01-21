package com.epam.ds.hostel.entity;

public enum BillStatus {
	NEW(0), PAID(1), DELETED(2);
	
	private int title;
	
	private BillStatus(int title) {
		this.title = title;
	}

	public int getTitle() {
		return title;
	}

	public void setTitle(int title) {
		this.title = title;
	}

}
