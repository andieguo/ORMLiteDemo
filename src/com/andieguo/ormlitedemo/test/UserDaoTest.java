package com.andieguo.ormlitedemo.test;

import com.andieguo.ormlitedemo.bean.IDcard;
import com.andieguo.ormlitedemo.bean.User;
import com.andieguo.ormlitedemo.dao.UserDao;

import android.test.AndroidTestCase;
import android.util.Log;

public class UserDaoTest extends AndroidTestCase implements InterfaceTester{

	private UserDao userdao = null;
	private static final String TAG = "UserDaoTest";
	
	@Override
	protected void setUp() throws Exception {
		Log.i(TAG,"执行了setup方法");
		userdao = UserDao.getInstance();
	}

	@Override
	public void testSave() {
		User user = new User("andy");
		//user.setIdcard(new IDcard("421878900999993939"));
		userdao.create(user);
	}
	
	@Override
	public void testUpdate() {
		User user = userdao.findUserById(1);
		user.setAge(23);
		user.setName("posly");
		user.setIdcard(new IDcard("111"));
		userdao.update(user);
	}

	@Override
	public void testFindById() {
		User user = userdao.findUserById(1);
		Log.i(TAG,user.toString());
	}
	
	public void testFindByName() {
		User user = userdao.findUserByName("posly");
		Log.i(TAG,user.toString());
	}
	
	public void testList() {
		for(User user:userdao.findAllUser()){
			Log.i(TAG,user.toString());
		}
	}

	

	@Override
	public void testDelete() {
		// TODO Auto-generated method stub
		
	}

}
