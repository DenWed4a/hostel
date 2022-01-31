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

}
