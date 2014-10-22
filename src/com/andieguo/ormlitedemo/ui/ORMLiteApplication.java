package com.andieguo.ormlitedemo.ui;

import android.app.Application;
import android.util.Log;

import com.andieguo.ormlitedemo.db.DatabaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;

public class ORMLiteApplication extends Application{
	private static ORMLiteApplication instance;
	private DatabaseHelper dataHelper;

    public DatabaseHelper getHelper() {
        if (dataHelper == null) {
        	Log.i("ORMLiteApplication","on create");
            dataHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return dataHelper;
    }
    
    public static ORMLiteApplication getInstance() {
        return instance;
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
    
    

}
