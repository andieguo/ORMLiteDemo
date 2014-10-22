package com.andieguo.ormlitedemo.test;


import java.sql.SQLException;
import java.util.List;

import com.andieguo.ormlitedemo.bean.Department;
import com.andieguo.ormlitedemo.bean.Employee;
import com.andieguo.ormlitedemo.bean.IDcard;
import com.andieguo.ormlitedemo.bean.SimpleData;
import com.andieguo.ormlitedemo.bean.User;
import com.andieguo.ormlitedemo.db.DatabaseHelper;
import com.andieguo.ormlitedemo.ui.ORMLiteApplication;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import android.test.AndroidTestCase;
import android.util.Log;

public class DatabaseHelperTest extends AndroidTestCase{
	private static final String TAG = "DatabaseHelperTest";
	/**------------------------------SimpleData CURD--------------------------------------------------**/
	public void testSaveSimpleData() {
		DatabaseHelper dbHelper = ORMLiteApplication.getInstance().getHelper();
		RuntimeExceptionDao<SimpleData, Integer> dao = dbHelper.getSimpleDataDao();
		long millis = System.currentTimeMillis();
		SimpleData simple = new SimpleData(millis);
		dao.create(simple);
		simple = new SimpleData(millis + 1);
		dao.create(simple);
		Log.i(DatabaseHelper.class.getName(), "created new entries in onCreate: " + millis);
	}
	
	public void testQuerySimpleData() {
		DatabaseHelper dbHelper = ORMLiteApplication.getInstance().getHelper();
		RuntimeExceptionDao<SimpleData, Integer> dao = dbHelper.getSimpleDataDao();
		for(SimpleData simpleData:dao.queryForAll()){
			Log.i(TAG,simpleData.toString());
		}
	}
	/**-----------------------------------User CURD---------------------------------------------**/
	/**
	 * 级联添加（添加User的同时，添加IDcard）
	 */
	public void testSaveUser() {
		DatabaseHelper dbHelper = ORMLiteApplication.getInstance().getHelper();
		RuntimeExceptionDao<User, Integer> dao = dbHelper.getUserDao();
		IDcard idcard = new IDcard();
		idcard.setName("421083199005062878");//设置idcard的Name属性
		User user = new User();
		user.setName("posly");//设置user的Name属性
		user.setIdcard(idcard);//绑定user与Idcard的一对一关系
		dao.create(user);//执行保存user对象操作，级联保存Idcard
	}
	
	public void testQueryUser() {
		DatabaseHelper dbHelper = ORMLiteApplication.getInstance().getHelper();
		RuntimeExceptionDao<User, Integer> dao = dbHelper.getUserDao();
		for(User user:dao.queryForAll()){
			Log.i(TAG,user.toString());//级联查询
		}
	}
	/**----------------------------------IDcard CURD----------------------------------------------**/
	public void testSaveCard() {
		DatabaseHelper dbHelper = ORMLiteApplication.getInstance().getHelper();
		RuntimeExceptionDao<IDcard, Integer> cardDao = dbHelper.getIDcardDao();
		IDcard idcard = new IDcard("421083199005062878");
		cardDao.create(idcard);
	}
	
	public void testQueryCard() {
		DatabaseHelper dbHelper = ORMLiteApplication.getInstance().getHelper();
		RuntimeExceptionDao<IDcard, Integer> cardDao = dbHelper.getIDcardDao();
		for(IDcard idcard:cardDao.queryForAll()){
			Log.i(TAG,idcard.toString());
		}
	}
	/**-------------------------------------Department CRUD-------------------------------------------**/
	public void testSaveDep() {
		DatabaseHelper dbHelper = ORMLiteApplication.getInstance().getHelper();
		RuntimeExceptionDao<Department, Integer> depDao = dbHelper.getDepartmentDao();
		Department department = new Department("研发部");
		depDao.create(department);
	}
	
	public void testQueryDepByName() throws SQLException{
		DatabaseHelper dbHelper = ORMLiteApplication.getInstance().getHelper();
		RuntimeExceptionDao<Department, Integer> depDao = dbHelper.getDepartmentDao();
        // 类似 select * from Department where 'name' = 研发部;
        List<Department> users = depDao.queryBuilder().where().eq("name", "研发部").query();
        Log.i(TAG, users.get(0).toString());
        for(Employee emp : users.get(0).getEmployees()){
			Log.i(TAG,emp.toString());
		}
	}
	
	public void testQueryDepById(){
		DatabaseHelper dbHelper = ORMLiteApplication.getInstance().getHelper();
		RuntimeExceptionDao<Department, Integer> depDao = dbHelper.getDepartmentDao();
		Department dep = depDao.queryForId(1);
		Log.i(TAG,dep.toString());
		for(Employee emp : dep.getEmployees()){
			Log.i(TAG,emp.toString());
		}
	}
	
	public void testUpdatDep(){//更新普通属性，更新对象属性
		DatabaseHelper dbHelper = ORMLiteApplication.getInstance().getHelper();
		RuntimeExceptionDao<Department, Integer> depDao = dbHelper.getDepartmentDao();
		Department dep = depDao.queryForId(1);
		dep.setName("草帽海贼团");
		dep.getEmployees().add(new Employee("娜美"));
		depDao.update(dep);
	}
	
	public void testUpdatDep2(){//更新普通属性，更新对象属性
		DatabaseHelper dbHelper = ORMLiteApplication.getInstance().getHelper();
		RuntimeExceptionDao<Department, Integer> depDao = dbHelper.getDepartmentDao();
		Department dep = depDao.queryForId(1);
		dep.getEmployees().remove(new Employee("娜美"));
		depDao.update(dep);//会级联更新么？
	}
	
	public void testDelDep(){
		DatabaseHelper dbHelper = ORMLiteApplication.getInstance().getHelper();
		RuntimeExceptionDao<Department, Integer> depDao = dbHelper.getDepartmentDao();
		Department dep = depDao.queryForId(1);
		int result = depDao.delete(dep);
		if(result == 1){
			Log.i(TAG,"delete dep successfully");
		}else{
			Log.i(TAG,"delete dep failed");
		}
	}
	
	/**------------------------------------Employee CURD-------------------------------------------**/
	public void testSaveEmpl() {
		DatabaseHelper dbHelper = ORMLiteApplication.getInstance().getHelper();
		RuntimeExceptionDao<Employee, Integer> empDao = dbHelper.getEmployeeDao();
		RuntimeExceptionDao<Department, Integer> depDao = dbHelper.getDepartmentDao();
		//Department dep = depDao.queryForId(1);//查询到指定的Dempartment
		Department dep = new Department("人事部");
		for(int i=1;i<10;i++){
			Employee employee = new Employee();
			employee.setName("posly"+i);
			employee.setDepartment(dep);
			empDao.create(employee);//级联保存
		}
	}
	
	public void testQueryEmpl(){
		DatabaseHelper dbHelper = ORMLiteApplication.getInstance().getHelper();
		RuntimeExceptionDao<Employee, Integer> empDao = dbHelper.getEmployeeDao();
		for(Employee employee:empDao.queryForAll()){
			Log.i(TAG,employee.toString());
		}
	}
	
	public void testDelEmpl(){
		DatabaseHelper dbHelper = ORMLiteApplication.getInstance().getHelper();
		
		RuntimeExceptionDao<Employee, Integer> empDao = dbHelper.getEmployeeDao();
		RuntimeExceptionDao<Department, Integer> depDao = dbHelper.getDepartmentDao();
		Employee emp = empDao.queryForId(1);//查询该员工实体
		Department dep = emp.getDepartment();
		dep.getEmployees().remove(emp);
		depDao.update(dep);
		
	}
}
