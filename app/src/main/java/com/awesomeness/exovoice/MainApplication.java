package com.awesomeness.exovoice;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());

    }
}
