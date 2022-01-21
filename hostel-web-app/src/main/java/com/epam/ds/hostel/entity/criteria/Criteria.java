package com.epam.ds.hostel.entity.criteria;

import java.util.HashMap;
import java.util.Map;

public class Criteria {
	private String tableName;
	private Map<String, Object> criteria  = new HashMap<String, Object>();
	
	public Criteria() {}
	
	public Criteria(String tableName) {
		this.tableName = tableName;
	}
	
	public String getTablehName() {
		return tableName;
	}
	
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setCriteria(Map<String, Object> criteria) {
		this.criteria = criteria;
	}
	
	public void clearCriteria() {
		this.criteria.clear();
		this.tableName = null;
	}

	public Map<String, Object> getCriteria() {
		return criteria;
	}

	public void add(String searchCriteria, Object value) {
		criteria.put(searchCriteria, value);
	}

}
