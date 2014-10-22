package com.andieguo.ormlitedemo.dao;

import com.andieguo.ormlitedemo.bean.Department;
import com.andieguo.ormlitedemo.ui.ORMLiteApplication;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class DepartmentDao {

	private static DepartmentDao instance;
	RuntimeExceptionDao<Department, Integer> departmentDao;
	
	public DepartmentDao() {
		departmentDao = ORMLiteApplication.getInstance().getHelper().getDepartmentDao();
	}

	public static DepartmentDao getInstance(){
		if(instance == null){
			instance = new DepartmentDao();
		}
		return instance;
	}
}
