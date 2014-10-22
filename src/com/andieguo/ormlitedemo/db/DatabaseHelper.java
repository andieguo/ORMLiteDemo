package com.andieguo.ormlitedemo.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.andieguo.ormlitedemo.bean.Cource;
import com.andieguo.ormlitedemo.bean.Department;
import com.andieguo.ormlitedemo.bean.Employee;
import com.andieguo.ormlitedemo.bean.IDcard;
import com.andieguo.ormlitedemo.bean.SimpleData;
import com.andieguo.ormlitedemo.bean.Student;
import com.andieguo.ormlitedemo.bean.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	private static final String DATABASE_NAME = "ormlite.db";
	private static final int DATABASE_VERSION = 1;
	
	private Dao<SimpleData, Integer> simpleDao = null;
	private RuntimeExceptionDao<SimpleData, Integer> simpleRuntimeDao = null;
	private RuntimeExceptionDao<User, Integer> userRuntimeDao = null;
	private RuntimeExceptionDao<IDcard, Integer> idcardRuntimeDao = null;
	private RuntimeExceptionDao<Department, Integer> departmentRuntimeDao = null;
	private RuntimeExceptionDao<Employee, Integer> employeeRuntimeDao = null;
	private RuntimeExceptionDao<Student, Integer> studentRuntimeDao = null;
	private RuntimeExceptionDao<Cource, Integer> courceRuntimeDao = null;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
		Log.i(DatabaseHelper.class.getName(), "onCreate");
		try {
			TableUtils.createTable(connectionSource, SimpleData.class);
			TableUtils.createTable(connectionSource, User.class);
			TableUtils.createTable(connectionSource, IDcard.class);
			TableUtils.createTable(connectionSource, Department.class);
			TableUtils.createTable(connectionSource, Employee.class);
//			TableUtils.createTable(connectionSource, Student.class);
//			TableUtils.createTable(connectionSource, Cource.class);
			
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource, int intoldVersion,
			int newVersion) {
		try {
			try {
				TableUtils.dropTable(connectionSource, User.class, true);
				TableUtils.dropTable(connectionSource, IDcard.class, true);
				TableUtils.dropTable(connectionSource, IDcard.class, true);
				TableUtils.dropTable(connectionSource, Department.class, true);
				TableUtils.dropTable(connectionSource, Employee.class, true);
				TableUtils.dropTable(connectionSource, Student.class, true);
				TableUtils.dropTable(connectionSource, Cource.class, true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Dao<SimpleData, Integer> getDao() throws SQLException {
		if (simpleDao == null) {
			simpleDao = getDao(SimpleData.class);
		}
		return simpleDao;
	}

	/**
	 * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our SimpleData class. It will
	 * create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
	 */
	public RuntimeExceptionDao<SimpleData, Integer> getSimpleDataDao() {
		if (simpleRuntimeDao == null) {
			simpleRuntimeDao = getRuntimeExceptionDao(SimpleData.class);
		}
		return simpleRuntimeDao;
	}
	
	public RuntimeExceptionDao<User, Integer> getUserDao() {
		if (userRuntimeDao == null) {
			userRuntimeDao = getRuntimeExceptionDao(User.class);
		}
		return userRuntimeDao;
	}

	public RuntimeExceptionDao<IDcard, Integer> getIDcardDao() {
		if (idcardRuntimeDao == null) {
			idcardRuntimeDao = getRuntimeExceptionDao(IDcard.class);
		}
		return idcardRuntimeDao;
	}
	
	public RuntimeExceptionDao<Department, Integer> getDepartmentDao() {
		if (departmentRuntimeDao == null) {
			departmentRuntimeDao = getRuntimeExceptionDao(Department.class);
		}
		return departmentRuntimeDao;
	}
	
	public RuntimeExceptionDao<Employee, Integer> getEmployeeDao() {
		if (employeeRuntimeDao == null) {
			employeeRuntimeDao = getRuntimeExceptionDao(Employee.class);
		}
		return employeeRuntimeDao;
	}
	
	public RuntimeExceptionDao<Student, Integer> getStudentDao() {
		if (studentRuntimeDao == null) {
			studentRuntimeDao = getRuntimeExceptionDao(Student.class);
		}
		return studentRuntimeDao;
	}
	
	public RuntimeExceptionDao<Cource, Integer> getCourceDao() {
		if (courceRuntimeDao == null) {
			courceRuntimeDao = getRuntimeExceptionDao(Cource.class);
		}
		return courceRuntimeDao;
	}

	@Override
	public void close() {
		super.close();
		userRuntimeDao = null;
		idcardRuntimeDao = null;
		departmentRuntimeDao = null;
		employeeRuntimeDao = null;
		studentRuntimeDao = null;
		courceRuntimeDao = null;
	}
}
