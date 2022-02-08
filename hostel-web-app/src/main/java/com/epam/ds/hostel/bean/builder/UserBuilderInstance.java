package com.epam.ds.hostel.bean.builder;

import com.epam.ds.hostel.entity.User;

public class UserBuilderInstance {
	private final static UserBuilderInstance instance = new UserBuilderInstance();
	private final User.Builder userBuilder = new User.Builder();
	
	private UserBuilderInstance() {}
	
	public static UserBuilderInstance getInstance() {
		return instance;
	}
	
	public User.Builder getUserBuilder(){
		return userBuilder;
	}
	
	

}
