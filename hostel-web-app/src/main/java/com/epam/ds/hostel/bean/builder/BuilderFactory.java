package com.epam.ds.hostel.bean.builder;

import com.epam.ds.hostel.entity.User;

public class BuilderFactory {
	private final static BuilderFactory instance = new BuilderFactory();
	private final User.Builder userBuilder = new User.Builder();
	
	private BuilderFactory() {}
	
	public static BuilderFactory getInstance() {
		return instance;
	}
	
	public User.Builder getUserBuilder(){
		return userBuilder;
	}
	
	

}
