package com.georges.mvvm.di;


import android.app.Application;

import com.georges.mvvm.ApplicationClass;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(modules = {UtilsModule.class, ViewModelModule.class,
        AndroidSupportInjectionModule.class, ActivityModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        Builder utilsModule(UtilsModule utilsModule);

        AppComponent build();
    }

    void inject(ApplicationClass applicationClass);
}

