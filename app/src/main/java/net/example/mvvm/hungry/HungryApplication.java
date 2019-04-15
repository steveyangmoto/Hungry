package net.example.mvvm.hungry;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.webkit.URLUtil;

import net.example.mvvm.hungry.di.AppInjector;
import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class HungryApplication extends Application implements HasActivityInjector{
    private static Context appContext;
    private static HungryApplication app;
    private String baseUrl = Constants.SERVER_URL;
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        app = this;
        AppInjector.init(this);
    }

    public String getBaseUrl() {//TODO retrive url from sharedpref
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        //TODO save to pref
    }

    public static HungryApplication getApplication() {
        return app;
    }

    public static Context getAppContext() {
        return appContext;
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
