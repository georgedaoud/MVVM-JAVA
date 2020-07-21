package com.georges.mvvm.util;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.georges.mvvm.repository.Repository;
import com.georges.mvvm.room.CachingRepository;
import com.georges.mvvm.view.articles.ArticlesViewModel;

import javax.inject.Inject;


public class ViewModelFactory implements ViewModelProvider.Factory {

    public final Repository repository;
    public final CachingRepository cachingRepository;

    @Inject
    public ViewModelFactory(Repository repository, CachingRepository cashingRepository) {
        this.repository = repository;
        this.cachingRepository = cashingRepository;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ArticlesViewModel.class))
            return (T) new ArticlesViewModel(repository, cachingRepository);
        throw new IllegalArgumentException("Unknown class name");
    }
}
