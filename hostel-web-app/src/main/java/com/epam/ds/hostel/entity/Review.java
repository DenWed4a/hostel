package com.epam.ds.hostel.entity;

import java.sql.Date;

import com.epam.ds.hostel.entity.status.EntityStatus.ReviewStatus;

public class Review {
	private int id;
	private int confirmedRequestId;
	private int moderatorId;
	private double mark;
	private String text;
	private Date date;
	private String responceToReview;
	private ReviewStatus status;
	private String topic;
	
	
	public Review() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getConfirmedRequestId() {
		return confirmedRequestId;
	}


	public void setConfirmedRequestId(int confirmedRequestId) {
		this.confirmedRequestId = confirmedRequestId;
	}


	public int getModeratorId() {
		return moderatorId;
	}


	public void setModeratorId(int moderatorId) {
		this.moderatorId = moderatorId;
	}


	public double getMark() {
		return mark;
	}


	public void setMark(double mark) {
		this.mark = mark;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getResponceToReview() {
		return responceToReview;
	}


	public void setResponceToReview(String responceToReview) {
		this.responceToReview = responceToReview;
	}


	public ReviewStatus getStatus() {
		return status;
	}


	public void setStatus(ReviewStatus status) {
		this.status = status;
	}


	public String getTopic() {
		return topic;
	}


	public void setTopic(String topic) {
		this.topic = topic;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + confirmedRequestId;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(mark);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + moderatorId;
		result = prime * result + ((responceToReview == null) ? 0 : responceToReview.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
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
		Review other = (Review) obj;
		if (confirmedRequestId != other.confirmedRequestId)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(mark) != Double.doubleToLongBits(other.mark))
			return false;
		if (moderatorId != other.moderatorId)
			return false;
		if (responceToReview == null) {
			if (other.responceToReview != null)
				return false;
		} else if (!responceToReview.equals(other.responceToReview))
			return false;
		if (status != other.status)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.topic))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Review [id=" + id + ", confirmedRequestId=" + confirmedRequestId + ", moderatorId=" + moderatorId
				+ ", mark=" + mark + ", text=" + text + ", date=" + date + ", responceToReview=" + responceToReview
				+ ", status=" + status + ", topic=" + topic + "]";
	}
	
	
	
	
	
	
	
	

}
