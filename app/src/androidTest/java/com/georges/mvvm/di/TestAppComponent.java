package com.georges.mvvm.di;


import android.app.Application;

import com.georges.mvvm.TestApplicationClass;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(modules = {TestUtilsModule.class, ViewModelModule.class,
        AndroidSupportInjectionModule.class, ActivityModule.class})
public interface TestAppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        Builder utilsModule(TestUtilsModule utilsModule);

        TestAppComponent build();
    }

    void inject(TestApplicationClass applicationClass);
}

