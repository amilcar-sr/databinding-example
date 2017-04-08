package com.androidsv.databinding;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by amilcar on 4/8/17.
 */

public class DatabindingApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //TODO: Persistence in 1 line
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        //TODO: Don't forget Firebase Dynamic Link & Auth feature
    }
}
