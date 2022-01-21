package com.epam.ds.hostel.entity;

import java.sql.Date;

public class BlackListUser {
	private int userId;
	private String reason;
	private Date sinceDate;
	
	public BlackListUser() {}
	
	public BlackListUser(int userId, String reason, Date sinceDate) {
		this.userId = userId;
		this.reason = reason;
		this.sinceDate = sinceDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getSinceDate() {
		return sinceDate;
	}

	public void setSinceDate(Date sinceDate) {
		this.sinceDate = sinceDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + ((sinceDate == null) ? 0 : sinceDate.hashCode());
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
		BlackListUser other = (BlackListUser) obj;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (sinceDate == null) {
			if (other.sinceDate != null)
				return false;
		} else if (!sinceDate.equals(other.sinceDate))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BlackListUser [userId=" + userId + ", reason=" + reason + ", sinceDate=" + sinceDate + "]";
	}
	
	
	

}
