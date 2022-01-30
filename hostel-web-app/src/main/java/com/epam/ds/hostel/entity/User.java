package com.epam.ds.hostel.entity;

public class User {
	private int id;
	private String login;
	private String password;
	private UserRole role;
	private int status;
	private UserDetail detail;

	private User(Builder builder) {
		this.id = builder.id;
		this.login = builder.login;
		this.password = builder.password;
		this.role = builder.role;
		this.status = builder.status;
		this.detail = builder.detail;
	}

	public static class Builder{
		private int id;
		private String login;
		private String password;
		private UserRole role;
		private int status;
		private UserDetail detail;
		
		public Builder() {}
		
		public Builder(User user) {
			id = user.getId();
			login = user.getLogin();
			password = user.getPassword();
			role = user.getRole();
			status = user.getStatus();
			detail = user.getDetail();
		}

		public Builder id(int id) {
			this.id = id;
			return this;
		}

		public Builder login(String login) {
			this.login = login;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder idRole(UserRole role) {
			this.role = role;
			return this;
		}

		public Builder status(int status) {
			this.status = status;
			return this;
		}

		public Builder userDetail(UserDetail detail) {
			this.detail = detail;
			return this;
		}

		public User build() {
			return new User(this);
		}
		
		public void clear() {
			this.id = 0;
			this.login = null;
			this.password = null;
			this.status = 0;
			this.detail = null;
		}

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public UserDetail getDetail() {
		return detail;
	}

	public void setDetail(UserDetail detail) {
		this.detail = detail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((detail == null) ? 0 : detail.hashCode());
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		User other = (User) obj;
		if (detail == null) {
			if (other.detail != null)
				return false;
		} else if (!detail.equals(other.detail))
			return false;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role != other.role)
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", role=" + role + ", status="
				+ status + ", detail=" + detail + "]";
	}



	
	

	
	
}
