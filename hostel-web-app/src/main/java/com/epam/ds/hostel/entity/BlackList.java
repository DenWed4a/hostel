package com.epam.ds.hostel.entity;



import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BlackList {
	
	private final static List<BlackListUser> blackList = new CopyOnWriteArrayList<>();
	
	public void addToBlackList(BlackListUser user) {
		blackList.add(user);
	}
	
	public void removeFromBlackList(int userId) {
		int position = -1;
		
		for(int i = 0; i < blackList.size(); i++) {
			if(blackList.get(i).getUserId() == userId) {
				position = i;
			}
		}
		
		if(position!=-1) {
		blackList.remove(position);
		}
	}
	



}
