package com.georges.mvvm;

import android.content.Context;

import com.georges.mvvm.di.AppComponent;
import com.georges.mvvm.di.AppModule;
import com.georges.mvvm.di.DaggerAppComponent;
import com.georges.mvvm.di.UtilsModule;


public class Application extends android.app.Application {
    public static Application applicationContext;
    AppComponent appComponent;

    public static synchronized Application getInstance() {
        return applicationContext;
    }

    public static Context getContext() {
        return applicationContext;
    }


    public AppComponent getAppComponent() {
        return appComponent;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).utilsModule(new UtilsModule()).build();
        appComponent.doInjection(this);


    }
}
