package com.epam.ds.hostel.entity.status;



public final class EntityStatus {
	public static enum ConfirmedRequestStatus{
		ACTIVE(0), COMPLITED(1), DELETED(2);
		
		private int title;
		
		private ConfirmedRequestStatus(int title) {
			this.title = title;
		}

		public int getTitle() {
			return title;
		}

		public void setTitle(int title) {
			this.title = title;
		}
	}
	
	public static enum BedPlaceStatus {
		ACTIVE(0), FROZEN(1), DELETED(3);
		
		private int title;
		
		private BedPlaceStatus(int title) {
			this.title = title;
		}

		public int getTitle() {
			return title;
		}

		public void setTitle(int title) {
			this.title = title;
		}
	}
	
	public static enum LockerStatus {
		ACTIVE(0), FROZEN(1), DELETED(3);
		
		private int title;
		
		private LockerStatus(int title) {
			this.title = title;
		}

		public int getTitle() {
			return title;
		}

		public void setTitle(int title) {
			this.title = title;
		}
	}
	
	public static enum ReviewStatus {
		NEW(0), READ(1), DELETED(3);
		
		private int title;
		
		private ReviewStatus(int title) {
			this.title = title;
		}

		public int getTitle() {
			return title;
		}

		public void setTitle(int title) {
			this.title = title;
		}
	}

}
