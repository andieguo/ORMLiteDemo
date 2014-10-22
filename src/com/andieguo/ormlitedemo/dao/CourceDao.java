package com.andieguo.ormlitedemo.dao;

import com.andieguo.ormlitedemo.bean.Cource;
import com.andieguo.ormlitedemo.ui.ORMLiteApplication;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class CourceDao {

	private static CourceDao instance;
	RuntimeExceptionDao<Cource, Integer> courceDao;
	
	public CourceDao() {
		courceDao = ORMLiteApplication.getInstance().getHelper().getCourceDao();
	}

	public static CourceDao getInstance(){
		if(instance == null){
			instance = new CourceDao();
		}
		return instance;
	}
}
