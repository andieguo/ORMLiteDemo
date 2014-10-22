package com.andieguo.ormlitedemo.test;

import com.andieguo.ormlitedemo.bean.IDcard;
import com.andieguo.ormlitedemo.bean.User;
import com.andieguo.ormlitedemo.dao.IDcardDao;
import com.andieguo.ormlitedemo.dao.UserDao;

import android.test.AndroidTestCase;
import android.util.Log;

public class IDcardTest extends AndroidTestCase implements InterfaceTester{

	private UserDao userDao = null;
	private IDcardDao cardDao = null;
	private static final String TAG = "IDcardTest";
	
	@Override
	protected void setUp() throws Exception {
		Log.i(TAG,"执行了setup方法");
		userDao = UserDao.getInstance();
		cardDao = IDcardDao.getInstance();
	}
	
	@Override
	public void testSave() {
		User user = userDao.findUserById(1);
		user.setIdcard(new IDcard("421023199004032878"));
		userDao.update(user);
	}

	@Override
	public void testFindById() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testDelete() {
		// TODO Auto-generated method stub
		
	}

}
