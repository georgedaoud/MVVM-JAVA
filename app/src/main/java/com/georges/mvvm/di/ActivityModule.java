package com.georges.mvvm.di;

import com.georges.mvvm.view.articles.ArticlesListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@SuppressWarnings("unused")
@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract ArticlesListActivity contributeArticlesListActivity();

}