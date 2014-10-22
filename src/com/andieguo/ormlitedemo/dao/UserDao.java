package com.andieguo.ormlitedemo.dao;

import java.sql.SQLException;
import java.util.List;

import com.andieguo.ormlitedemo.bean.User;
import com.andieguo.ormlitedemo.ui.ORMLiteApplication;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;

public class UserDao {

	private static UserDao instance;
	RuntimeExceptionDao<User, Integer> dao;

	public UserDao() {
		dao = ORMLiteApplication.getInstance().getHelper().getUserDao();
	}

	public static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
	}

	public long update(User user) {
		return dao.update(user);
	}

	public long create(User user) {
		return dao.create(user);
	}

	public List<User> findAllUser() {
		return dao.queryForAll();
	}

	public User findUserById(Integer id) {
		return dao.queryForId(id);
	}

	public User findUserByName(String name) {
		try {
			QueryBuilder<User, Integer> qb = dao.queryBuilder();
			qb.where().eq("name", name);
			return qb.queryForFirst();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
