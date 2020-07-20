package com.georges.mvvm.util;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.georges.mvvm.repository.Repository;
import com.georges.mvvm.room.CachingRepository;

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
        throw new IllegalArgumentException("Unknown class name");
    }
}
