package com.epam.ds.hostel.entity;

import java.sql.Date;

public class UserDetail {
	private int userId;
	private String name;
	private String surname;
	private String phoneNumber;
	private String email;
	private double reiting;
	private String passportNumber;
	private String nationality;
	private Date dateOfBirth;
	private Date passportDateOfIssue;
	private Date passportDateOfExpire;
	private String address;
	
	public UserDetail() {}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getReiting() {
		return reiting;
	}

	public void setReiting(double reiting) {
		this.reiting = reiting;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getPassportDateOfIssue() {
		return passportDateOfIssue;
	}

	public void setPassportDateOfIssue(Date passportDateOfIssue) {
		this.passportDateOfIssue = passportDateOfIssue;
	}

	public Date getPassportDateOfExpire() {
		return passportDateOfExpire;
	}

	public void setPassportDateOfExpire(Date passportDateOfExpire) {
		this.passportDateOfExpire = passportDateOfExpire;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nationality == null) ? 0 : nationality.hashCode());
		result = prime * result + ((passportDateOfExpire == null) ? 0 : passportDateOfExpire.hashCode());
		result = prime * result + ((passportDateOfIssue == null) ? 0 : passportDateOfIssue.hashCode());
		result = prime * result + ((passportNumber == null) ? 0 : passportNumber.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		long temp;
		temp = Double.doubleToLongBits(reiting);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + userId;
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
		UserDetail other = (UserDetail) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nationality == null) {
			if (other.nationality != null)
				return false;
		} else if (!nationality.equals(other.nationality))
			return false;
		if (passportDateOfExpire == null) {
			if (other.passportDateOfExpire != null)
				return false;
		} else if (!passportDateOfExpire.equals(other.passportDateOfExpire))
			return false;
		if (passportDateOfIssue == null) {
			if (other.passportDateOfIssue != null)
				return false;
		} else if (!passportDateOfIssue.equals(other.passportDateOfIssue))
			return false;
		if (passportNumber == null) {
			if (other.passportNumber != null)
				return false;
		} else if (!passportNumber.equals(other.passportNumber))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (Double.doubleToLongBits(reiting) != Double.doubleToLongBits(other.reiting))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserDetail [userId=" + userId + ", name=" + name + ", surname=" + surname + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", reiting=" + reiting + ", passportNumber=" + passportNumber
				+ ", nationality=" + nationality + ", dateOfBirth=" + dateOfBirth + ", passportDateOfIssue="
				+ passportDateOfIssue + ", passportDateOfExpire=" + passportDateOfExpire + ", address=" + address + "]";
	}

	
}
