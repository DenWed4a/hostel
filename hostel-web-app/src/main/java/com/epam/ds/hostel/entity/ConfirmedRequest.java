package com.epam.ds.hostel.entity;

import java.sql.Date;

public class ConfirmedRequest {
	
	private int id;
	private int billId;
	private int administratorId;
	private Date confirmationDate;
	private int status;
	private Date dateOfPayment;
	
	
	public ConfirmedRequest() {}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getBillId() {
		return billId;
	}


	public void setBillId(int billId) {
		this.billId = billId;
	}


	public int getAdministratorId() {
		return administratorId;
	}


	public void setAdministratorId(int administratorId) {
		this.administratorId = administratorId;
	}


	public Date getConfirmationDate() {
		return confirmationDate;
	}


	public void setConfirmationDate(Date confirmationDate) {
		this.confirmationDate = confirmationDate;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public Date getDateOfPayment() {
		return dateOfPayment;
	}


	public void setDateOfPayment(Date dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + administratorId;
		result = prime * result + billId;
		result = prime * result + ((confirmationDate == null) ? 0 : confirmationDate.hashCode());
		result = prime * result + ((dateOfPayment == null) ? 0 : dateOfPayment.hashCode());
		result = prime * result + id;
		result = prime * result + status;
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
		ConfirmedRequest other = (ConfirmedRequest) obj;
		if (administratorId != other.administratorId)
			return false;
		if (billId != other.billId)
			return false;
		if (confirmationDate == null) {
			if (other.confirmationDate != null)
				return false;
		} else if (!confirmationDate.equals(other.confirmationDate))
			return false;
		if (dateOfPayment == null) {
			if (other.dateOfPayment != null)
				return false;
		} else if (!dateOfPayment.equals(other.dateOfPayment))
			return false;
		if (id != other.id)
			return false;
		if (status != other.status)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "ConfirmedRequest [id=" + id + ", billId=" + billId + ", administratorId=" + administratorId
				+ ", confirmationDate=" + confirmationDate + ", status=" + status + ", dateOfPayment=" + dateOfPayment
				+ "]";
	}



	

}
