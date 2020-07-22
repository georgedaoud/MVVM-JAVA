package com.georges.mvvm;


import com.georges.mvvm.di.AppComponent;
import com.georges.mvvm.di.AppModule;
import com.georges.mvvm.di.DaggerAppComponent;
import com.georges.mvvm.di.UtilsModule;


public class ApplicationClass extends android.app.Application {
    AppComponent appComponent;


    public AppComponent getAppComponent() {
        return appComponent;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).utilsModule(new UtilsModule()).build();
        appComponent.doInjection(this);


    }
}
