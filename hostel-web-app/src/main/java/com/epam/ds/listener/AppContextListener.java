package com.epam.ds.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.epam.ds.hostel.dao.connectionpool.ConnectionPool;
import com.epam.ds.hostel.dao.connectionpool.ConnectionPoolException;

public class AppContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("destroy");
		ConnectionPool.getInstance().dispose();
		ServletContextListener.super.contextDestroyed(sce);
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("init");
		ConnectionPool connectionPool;
		try {
			connectionPool = ConnectionPool.getInstance();
			connectionPool.initPoolData();
		} catch (ConnectionPoolException e) {
			
		}
		
		ServletContextListener.super.contextInitialized(sce);
	}

}
