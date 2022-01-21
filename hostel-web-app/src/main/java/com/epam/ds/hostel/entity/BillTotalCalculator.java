package com.epam.ds.hostel.entity;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

public class BillTotalCalculator {
	private int numberOfPlaces;
	private int numberOfLockers;
	private double placesRate;
	private double lockersRate;
	private double discont;
	
	public BillTotalCalculator() {
		this.placesRate = 20.00;
		this.lockersRate = 5.00;				
	}
	
	public double getTotal(Date checkIn, Date checkOut, int places, int lockers) {
		double result;
		int days;
		TimeUnit timeUnit = TimeUnit.DAYS;
		days = (int) timeUnit.convert(checkOut.getTime()-checkIn.getTime(), TimeUnit.MILLISECONDS);
		if(days == 0) {
			days = 1;
		}
		result = placesRate * days + lockersRate * days;
		
		return result;
	}
	
	public double getTotalWithDiscount(Date checkIn, Date checkOut, int places, int lockers, double discount) {
		double result;
		
		result = getTotal(checkIn, checkOut, places, lockers);
		result = result - result*discount/100;	
		return result;
	}

	public int getNumberOfPlaces() {
		return numberOfPlaces;
	}

	public void setNumberOfPlaces(int numberOfPlaces) {
		this.numberOfPlaces = numberOfPlaces;
	}

	public int getNumberOfLockers() {
		return numberOfLockers;
	}

	public void setNumberOfLockers(int numberOfLockers) {
		this.numberOfLockers = numberOfLockers;
	}

	public double getPlacesRate() {
		return placesRate;
	}

	public void setPlacesRate(double placesRate) {
		this.placesRate = placesRate;
	}

	public double getLockersRate() {
		return lockersRate;
	}

	public void setLockersRate(double lockersRate) {
		this.lockersRate = lockersRate;
	}

	public double getDiscont() {
		return discont;
	}

	public void setDiscont(double discont) {
		this.discont = discont;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(discont);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(lockersRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + numberOfLockers;
		result = prime * result + numberOfPlaces;
		temp = Double.doubleToLongBits(placesRate);
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
		BillTotalCalculator other = (BillTotalCalculator) obj;
		if (Double.doubleToLongBits(discont) != Double.doubleToLongBits(other.discont))
			return false;
		if (Double.doubleToLongBits(lockersRate) != Double.doubleToLongBits(other.lockersRate))
			return false;
		if (numberOfLockers != other.numberOfLockers)
			return false;
		if (numberOfPlaces != other.numberOfPlaces)
			return false;
		if (Double.doubleToLongBits(placesRate) != Double.doubleToLongBits(other.placesRate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BillTotalCalculator [numberOfPlaces=" + numberOfPlaces + ", numberOfLockers=" + numberOfLockers
				+ ", placesRate=" + placesRate + ", lockersRate=" + lockersRate + ", discont=" + discont + "]";
	}
	

}
