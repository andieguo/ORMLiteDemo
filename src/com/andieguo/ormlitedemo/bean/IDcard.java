package com.andieguo.ormlitedemo.bean;

import com.j256.ormlite.field.DatabaseField;

public class IDcard {
	
	@DatabaseField(generatedId=true)
	private Integer id;
	
	@DatabaseField(canBeNull=false,width=20)
	private String name;
	
	@DatabaseField(foreign=true,useGetSet=true,columnName="userid"
			,foreignAutoRefresh = true,foreignAutoCreate=true)
	private User user;
	
	public IDcard(String name) {
		this.name = name;
	}
	public IDcard() {
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "IDcard [id=" + id + ", name=" + name + "]";
	}

}
