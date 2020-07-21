package com.georges.mvvm.di;


import com.georges.mvvm.Application;
import com.georges.mvvm.view.articles.ArticlesListActivity;

import javax.inject.Singleton;

import dagger.Component;


@Component(modules = {AppModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {


    void doInjection(Application application);

    void doInjection(ArticlesListActivity articlesListActivity);
}
