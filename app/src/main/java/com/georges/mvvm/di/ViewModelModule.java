package com.georges.mvvm.di;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.georges.mvvm.util.ViewModelFactory;
import com.georges.mvvm.view.articles.ArticlesViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@SuppressWarnings("unused")
@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(ArticlesViewModel.class)
    protected abstract ViewModel articlesViewModel(ArticlesViewModel articlesViewModel);


}