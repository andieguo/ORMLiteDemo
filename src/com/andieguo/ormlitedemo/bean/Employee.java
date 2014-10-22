package com.andieguo.ormlitedemo.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tb_employee")
public class Employee {
	
	@DatabaseField(generatedId=true)
	private Integer id;
	
	@DatabaseField(canBeNull=false,width=20)
	private String name;
	/**
	 * foreign = true;说明这是一个外部引用关系
	 * foreignAutoRefresh =true;当对象被查询时，自动根据外键获取到employee的所有属性(级联查询)
	 * foreignAutoCreate=true;在插入一个有外键对象的对象时，是否自动插入这个外键对象（建联添加）
	 * 
	 */
	@DatabaseField(foreign = true,columnName="depid",foreignAutoRefresh = true,foreignAutoCreate=true)
	private Department department;
	
	public Employee() {
		super();
	}

	public Employee( String name) {
		this.name = name;
	}
	
	public Employee( String name, Department department) {
		this.name = name;
		this.department = department;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", department="
				+ department + "]";
	}
	
	
	
}
