package com.epam.ds.util;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

public class BillTotalCalculator {
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
		days = getNumberDays(checkIn, checkOut);
		if(days == 0) {
			days = 1;
		}
		result = placesCost(places, days) + lockersCost(lockers, days);
		
		return result;
	}
	
	public double getTotalWithDiscount(Date checkIn, Date checkOut, int places, int lockers, double discount) {
		double result;
		
		result = getTotal(checkIn, checkOut, places, lockers);
		result = result - result*discount/100;	
		return result;
	}
	
	public int getNumberDays(Date checkIn, Date checkOut) {
		int result;
		TimeUnit timeUnit = TimeUnit.DAYS;
		result = (int) timeUnit.convert(checkOut.getTime()-checkIn.getTime(), TimeUnit.MILLISECONDS);
		
		return result;
	}
	
	public double placesCost(int places, int days) {
		double result;
		result = days * places * placesRate;
		return result;
	}
	
	public double lockersCost(int lockers, int days) {
		double result;
		result = days * lockers * lockersRate;
		return result;
		
	}

	public double getPlacesRate() {
		return placesRate;
	}

	public double getLockersRate() {
		return lockersRate;
	}

	public double getDiscont() {
		return discont;
	}
	

}
