package com.epam.ds.hostel.entity;

import java.io.Serializable;

public class Bill implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private double totalAmount;
	private BillStatus status;
	private int bookingRequestID;
	
	public Bill() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BillStatus getStatus() {
		return status;
	}

	public void setStatus(BillStatus status) {
		this.status = status;
	}

	public int getBookingRequestID() {
		return bookingRequestID;
	}

	public void setBookingRequestID(int bookingRequestID) {
		this.bookingRequestID = bookingRequestID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookingRequestID;
		result = prime * result + id;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bill other = (Bill) obj;
		if (bookingRequestID != other.bookingRequestID)
			return false;
		if (id != other.id)
			return false;
		if (status != other.status)
			return false;
		if (Double.doubleToLongBits(totalAmount) != Double.doubleToLongBits(other.totalAmount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bill [id=" + id + ", totalAmount=" + totalAmount + ", status=" + status + ", bookingRequestID="
				+ bookingRequestID + "]";
	}
	

}