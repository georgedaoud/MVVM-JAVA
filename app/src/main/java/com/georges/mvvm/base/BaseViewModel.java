package com.georges.mvvm.base;


import androidx.lifecycle.ViewModel;
import com.georges.mvvm.repository.Repository;
import com.georges.mvvm.room.CachingRepository;

import io.reactivex.disposables.CompositeDisposable;


public class BaseViewModel extends ViewModel {

    public final CachingRepository cachingRepository;
    public final Repository repository;
    protected CompositeDisposable disposable;

    public BaseViewModel(Repository repository, CachingRepository cachingRepository) {
        this.cachingRepository = cachingRepository;
        this.repository = repository;
        this.disposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
