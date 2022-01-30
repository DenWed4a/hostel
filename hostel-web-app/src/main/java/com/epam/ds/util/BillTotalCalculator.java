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
		TimeUnit timeUnit = TimeUnit.DAYS;
		days = (int) timeUnit.convert(checkOut.getTime()-checkIn.getTime(), TimeUnit.MILLISECONDS);
		if(days == 0) {
			days = 1;
		}
		result = placesRate*places*days + lockersRate * lockers * days;
		
		return result;
	}
	
	public double getTotalWithDiscount(Date checkIn, Date checkOut, int places, int lockers, double discount) {
		double result;
		
		result = getTotal(checkIn, checkOut, places, lockers);
		result = result - result*discount/100;	
		return result;
	}
	

}
