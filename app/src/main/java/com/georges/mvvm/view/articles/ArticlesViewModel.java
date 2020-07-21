package com.georges.mvvm.view.articles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.georges.mvvm.base.BaseViewModel;
import com.georges.mvvm.repository.Repository;
import com.georges.mvvm.repository.model.resp.Articles;
import com.georges.mvvm.repository.model.resp.Result;
import com.georges.mvvm.room.CachingRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ArticlesViewModel extends BaseViewModel {

    private final MutableLiveData<Articles> articleResponseLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> errorLiveData = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>(false);

    ArticlesNavigator articlesNavigator;

    public ArticlesViewModel(Repository repository, CachingRepository cachingRepository) {
        super(repository, cachingRepository);
    }


    public void setArticlesNavigator(ArticlesNavigator articlesNavigator) {
        this.articlesNavigator = articlesNavigator;
        getArticles();
    }


    public void getArticles() {
        if (isNetworkConnected()) {
            errorLiveData.setValue(false);
            loadingLiveData.setValue(true);
            disposable.add(repository.getArticles("emailed", "7").subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<Articles>() {
                        @Override
                        public void onSuccess(Articles value) {
                            articleResponseLiveData.setValue(value);
                            loadingLiveData.setValue(false);
                            errorLiveData.setValue(false);
                        }

                        @Override
                        public void onError(Throwable e) {
                            errorLiveData.setValue(true);
                            loadingLiveData.setValue(false);

                        }
                    }));
        } else
            errorLiveData.setValue(true);
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

    public void clickArticle(Result result) {
        articlesNavigator.navigateToArticleDetail(result);
    }

}