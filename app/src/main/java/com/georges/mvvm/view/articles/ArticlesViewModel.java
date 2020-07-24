package com.georges.mvvm.view.articles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.georges.mvvm.base.BaseViewModel;
import com.georges.mvvm.repository.Repository;
import com.georges.mvvm.repository.model.resp.Articles;
import com.georges.mvvm.repository.model.resp.Result;
import com.georges.mvvm.room.Apis;
import com.georges.mvvm.room.CachingRepository;
import com.georges.mvvm.room.model.CachingModel;
import com.google.gson.Gson;

import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class ArticlesViewModel extends BaseViewModel {

    private final MutableLiveData<Articles> articleResponseLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> errorLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> apiCached = new MutableLiveData<>();
    ArticlesNavigator articlesNavigator;

    public ArticlesViewModel(Repository repository, CachingRepository cachingRepository) {
        super(repository, cachingRepository);
    }


    public void setArticlesNavigator(ArticlesNavigator articlesNavigator) {
        this.articlesNavigator = articlesNavigator;
        getCachedArticles();
    }

    public void getCachedArticles() {
        disposable.add(cachingRepository.getCachingByApi(Apis.ARTICLES).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<CachingModel>() {
                    @Override
                    public void onSuccess(CachingModel value) {
                        apiCached.setValue(true);
                        articleResponseLiveData.setValue(stringToArticles(value.getApiResponse()));
                        getArticles();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.i(e);
                        apiCached.setValue(false);
                        getArticles();
                    }
                }));
    }

    Articles stringToArticles(String string) {
        return new Gson().fromJson(string, Articles.class);
    }


    public void getArticles() {
        errorLiveData.setValue(false);
        loadingLiveData.setValue(true);
        disposable.add(repository.getArticles("emailed", "7").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<Articles>() {
                    @Override
                    public void onSuccess(Articles value) {
                        articleResponseLiveData.setValue(value);
                        loadingLiveData.setValue(false);
                        errorLiveData.setValue(false);
                        if (apiCached.getValue() != null && apiCached.getValue())
                            cachingRepository.updateCaching(Apis.ARTICLES, new Gson().toJson(value), new Date());
                        else
                            cachingRepository.insert(Apis.ARTICLES, new Gson().toJson(value), new Date());
                    }

                    @Override
                    public void onError(Throwable e) {
                        errorLiveData.setValue(true);
                        loadingLiveData.setValue(false);

                    }
                }));
    }

    public LiveData<Articles> getArticleResponseLiveData() {
        return articleResponseLiveData;
    }

    public LiveData<Boolean> getErrorLiveData() {
        return errorLiveData;
    }

    public LiveData<Boolean> getLoadingLiveData() {
        return loadingLiveData;
    }

    public LiveData<Boolean> getApiCachedLiveData() {
        return apiCached;
    }

    public void clickArticle(Result result) {
        articlesNavigator.navigateToArticleDetail(result);
    }

}