package com.georges.mvvm;


import android.app.Application;

import com.georges.mvvm.di.DaggerTestAppComponent;
import com.georges.mvvm.di.TestUtilsModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;


public class TestApplicationClass extends Application implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerTestAppComponent.builder()
                .application(this)
                .utilsModule(new TestUtilsModule())
                .build()
                .inject(this);
    }


    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}
