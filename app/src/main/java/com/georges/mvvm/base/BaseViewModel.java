package com.georges.mvvm.base;

import android.content.Context;
import android.net.ConnectivityManager;

import androidx.lifecycle.ViewModel;

import com.georges.mvvm.Application;
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

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) Application.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        assert cm != null;
        return cm.isDefaultNetworkActive();
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
