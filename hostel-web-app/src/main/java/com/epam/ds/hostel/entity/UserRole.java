package com.epam.ds.hostel.entity;

public enum UserRole {
	CLIENT(0), ADMINISTRATOR(1), MODERATOR(2); 

		private int title;

		private UserRole(int title) {
			this.title = title;
		}

		public int getTitle() {
			return title;
		}

		public void setTitle(int title) {
			this.title = title;
		}
	}

