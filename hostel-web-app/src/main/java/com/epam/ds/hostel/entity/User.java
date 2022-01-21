package com.epam.ds.hostel.entity;

public class User {
	private int id;
	private String login;
	private String password;
	private int idRole;
	private int status;
	private UserDetail detail;

	private User(Builder builder) {
		this.id = builder.id;
		this.login = builder.login;
		this.password = builder.password;
		this.idRole = builder.idRole;
		this.status = builder.status;
		this.detail = builder.detail;
	}

	public static class Builder{
		private int id;
		private String login;
		private String password;
		private int idRole;
		private int status;
		private UserDetail detail;
		
		public Builder() {}
		
		public Builder(User user) {
			id = user.getId();
			login = user.getLogin();
			password = user.password;
			idRole = user.idRole;
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

		public Builder idRole(int idRole) {
			this.idRole = idRole;
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
			this.idRole = 0;
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

	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
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
		result = prime * result + idRole;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		if (idRole != other.idRole)
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
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", idRole=" + idRole + ", status="
				+ status + ", detail=" + detail + "]";
	}

	
	

	
	
}
