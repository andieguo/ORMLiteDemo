package com.andieguo.ormlitedemo.ui;

import com.andieguo.ormlitedemo.R;
import com.andieguo.ormlitedemo.bean.User;
import com.andieguo.ormlitedemo.db.DatabaseHelper;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends OrmLiteBaseActivity<DatabaseHelper> {
	private RuntimeExceptionDao<User, Integer> userDao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		userDao = getHelper().getUserDao();
		userDao.createIfNotExists(new User("andy"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
