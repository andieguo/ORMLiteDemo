package com.andieguo.ormlitedemo.dao;

import com.andieguo.ormlitedemo.bean.Employee;
import com.andieguo.ormlitedemo.ui.ORMLiteApplication;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class EmployeeDao {

	private static EmployeeDao instance;
	RuntimeExceptionDao<Employee, Integer> employeeDao;
	
	public EmployeeDao() {
		employeeDao = ORMLiteApplication.getInstance().getHelper().getEmployeeDao();
	}

	public static EmployeeDao getInstance(){
		if(instance == null){
			instance = new EmployeeDao();
		}
		return instance;
	}
}
