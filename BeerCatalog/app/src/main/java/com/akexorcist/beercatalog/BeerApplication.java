package com.akexorcist.beercatalog;

import android.app.Application;

import com.akexorcist.beercatalog.db.DatabaseManager;

/**
 * Created by Akexorcist on 6/18/2017 AD.
 */

public class BeerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseManager.getInstance().init(getApplicationContext());
    }
}
