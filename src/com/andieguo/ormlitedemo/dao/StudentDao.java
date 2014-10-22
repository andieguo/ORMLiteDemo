package com.andieguo.ormlitedemo.dao;

import com.andieguo.ormlitedemo.bean.Student;
import com.andieguo.ormlitedemo.ui.ORMLiteApplication;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class StudentDao {

	private static StudentDao instance;
	RuntimeExceptionDao<Student, Integer> studentDao;
	
	public StudentDao() {
		studentDao = ORMLiteApplication.getInstance().getHelper().getStudentDao();
	}

	public static StudentDao getInstance(){
		if(instance == null){
			instance = new StudentDao();
		}
		return instance;
	}
}
