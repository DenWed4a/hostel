package com.epam.ds.hostel.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class BookingRequest {
	
	private int id;
	private Date startDate;
	private Date endDate;
	private int numberOfPlaces;
	private int numberOfLockers;
	private String status;
	private int clientId;
	private Timestamp timeOfCreation;
	
	public BookingRequest() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public Timestamp getTimeOfCreation() {
		return timeOfCreation;
	}

	public void setTimeOfCreation(Timestamp timeOfCreation) {
		this.timeOfCreation = timeOfCreation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + clientId;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + id;
		result = prime * result + numberOfLockers;
		result = prime * result + numberOfPlaces;
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((timeOfCreation == null) ? 0 : timeOfCreation.hashCode());
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
		BookingRequest other = (BookingRequest) obj;
		if (clientId != other.clientId)
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id != other.id)
			return false;
		if (numberOfLockers != other.numberOfLockers)
			return false;
		if (numberOfPlaces != other.numberOfPlaces)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (timeOfCreation == null) {
			if (other.timeOfCreation != null)
				return false;
		} else if (!timeOfCreation.equals(other.timeOfCreation))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BookingRequest [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", numberOfPlaces="
				+ numberOfPlaces + ", numberOfLockers=" + numberOfLockers + ", status=" + status + ", clientId="
				+ clientId + ", timeOfCreation=" + timeOfCreation + "]";
	}

	

	

}