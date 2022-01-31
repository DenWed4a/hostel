package com.epam.ds.hostel.entity;

import java.io.Serializable;
import java.util.List;

public class BedPlace implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int tier;
	private String imagePath;
	private List<ConfirmedRequest> bedPlaceHasRequests;
	
	public BedPlace() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTier() {
		return tier;
	}

	public void setTier(int tier) {
		this.tier = tier;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public List<ConfirmedRequest> getBedPlaceHasRequests() {
		return bedPlaceHasRequests;
	}

	public void setBedPlaceHasRequests(List<ConfirmedRequest> bedPlaceHasRequests) {
		this.bedPlaceHasRequests = bedPlaceHasRequests;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bedPlaceHasRequests == null) ? 0 : bedPlaceHasRequests.hashCode());
		result = prime * result + id;
		result = prime * result + ((imagePath == null) ? 0 : imagePath.hashCode());
		result = prime * result + tier;
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
		BedPlace other = (BedPlace) obj;
		if (bedPlaceHasRequests == null) {
			if (other.bedPlaceHasRequests != null)
				return false;
		} else if (!bedPlaceHasRequests.equals(other.bedPlaceHasRequests))
			return false;
		if (id != other.id)
			return false;
		if (imagePath == null) {
			if (other.imagePath != null)
				return false;
		} else if (!imagePath.equals(other.imagePath))
			return false;
		if (tier != other.tier)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BedPlace [id=" + id + ", tier=" + tier + ", imagePath=" + imagePath + ", bedPlaceHasRequests="
				+ bedPlaceHasRequests + "]";
	}
	



	

}