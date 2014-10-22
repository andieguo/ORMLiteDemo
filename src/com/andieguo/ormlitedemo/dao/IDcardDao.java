package com.andieguo.ormlitedemo.dao;

import java.sql.SQLException;
import java.util.List;

import com.andieguo.ormlitedemo.bean.IDcard;
import com.andieguo.ormlitedemo.ui.ORMLiteApplication;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;

public class IDcardDao {

	private static IDcardDao instance;
	RuntimeExceptionDao<IDcard, Integer> dao;
	
	public IDcardDao() {
		dao = ORMLiteApplication.getInstance().getHelper().getIDcardDao();
	}

	public static IDcardDao getInstance(){
		if(instance == null){
			instance = new IDcardDao();
		}
		return instance;
	}
	
	public long createOrUpdate(IDcard IDcard) {
		try {
			dao.createOrUpdate(IDcard);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	}
	
	public List<IDcard> findAllIDcard(){
		return dao.queryForAll();
	}
	
	public IDcard findIDcardById(Integer id){
		return dao.queryForId(id);
	}
	
	public IDcard finIDcardByName(String name){
		try {
			QueryBuilder<IDcard, Integer> qb = dao.queryBuilder();
			qb.where().eq("name", name);
			return qb.queryForFirst();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
