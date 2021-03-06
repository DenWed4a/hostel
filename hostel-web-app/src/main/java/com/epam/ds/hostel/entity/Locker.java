package com.epam.ds.hostel.entity;

import java.io.Serializable;

import com.epam.ds.hostel.entity.status.EntityStatus.LockerStatus;

public class Locker implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private LockerType size;
	private String imagePath;
	private LockerStatus status;
	
	public Locker() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LockerType getSize() {
		return size;
	}

	public void setSize(LockerType size) {
		this.size = size;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public LockerStatus getStatus() {
		return status;
	}

	public void setStatus(LockerStatus status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((imagePath == null) ? 0 : imagePath.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Locker other = (Locker) obj;
		if (id != other.id)
			return false;
		if (imagePath == null) {
			if (other.imagePath != null)
				return false;
		} else if (!imagePath.equals(other.imagePath))
			return false;
		if (size != other.size)
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Locker [id=" + id + ", size=" + size + ", imagePath=" + imagePath + ", status=" + status + "]";
	}

	

}
