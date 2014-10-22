package com.andieguo.ormlitedemo.bean;

import com.j256.ormlite.field.DatabaseField;

public class User {
	
	@DatabaseField(generatedId = true)
	private Integer id;
	
	@DatabaseField(canBeNull=false,width=20)
	private String name;
	
	@DatabaseField(canBeNull=false,defaultValue = "24")
	private int age;
	
	/**
	 * columnName="cardid" 指定列名，默认为idcard_id;
	 *　foreignAutoCreate = true 在插入一个有外键对象的对象时，是否自动插入这个外键对象
	 * foreignAutoRefresh = true  当查询到一个外键对象时，是否自动刷新 如 Order表中有Account外键对象，当返回Order的记录时是否也返回Account的记录， 默认为false
	 */
	@DatabaseField(foreign=true,useGetSet=true,columnName="cardid",foreignAutoRefresh = true,foreignAutoCreate=true)
	private IDcard idcard;

	public User() {
	}
	
	public User(String name) {
		this.name = name;
	}
	
	public User(String name,IDcard idcard) {
		this.name = name;
		this.idcard = idcard;
	}

	public IDcard getIdcard() {
		return idcard;
	}

	public void setIdcard(IDcard idcard) {
		this.idcard = idcard;
	}

	public Integer getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age
				+ ", idcard=" + idcard + "]";
	}

}
