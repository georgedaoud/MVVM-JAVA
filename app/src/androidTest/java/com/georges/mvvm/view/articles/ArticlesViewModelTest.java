package com.georges.mvvm.view.articles;

import android.os.SystemClock;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.georges.mvvm.repository.Repository;
import com.georges.mvvm.repository.model.resp.Articles;
import com.georges.mvvm.room.Apis;
import com.georges.mvvm.room.CachingRepository;
import com.georges.mvvm.room.model.CachingModel;
import com.georges.mvvm.rule.RxSchedulersOverrideRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArticlesViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Rule
    public RxSchedulersOverrideRule schedulersOverrideRule = new RxSchedulersOverrideRule();

    @Mock
    Repository repository;
    @Mock
    CachingRepository cachingRepository;
    @Mock
    Observer<Articles> dataObserver;
    @Mock
    Observer<Boolean> errorObserver;
    @Mock
    Observer<Boolean> loadingObserver;
    @Mock
    Observer<Boolean> apiCachedObserver;

    private ArticlesViewModel viewModel;


    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        viewModel = new ArticlesViewModel(repository, cachingRepository);
    }

    @Test
    public void testGetCachedArticlesData_whenReturnsData() {
        //Assemble
        Articles apiArticles = new Articles();
        when(repository.getArticles(any(), any())).thenReturn(Single.just(apiArticles));

        Articles cachingArticles = new Articles();
        CachingModel cachingModel = new CachingModel(Apis.ARTICLES, "", new Date());
        when(cachingRepository.getCachingByApi(Apis.ARTICLES)).thenReturn(Single.just(cachingModel));
        ArticlesViewModel viewModel1 = Mockito.spy(viewModel);
        Mockito.doReturn(cachingArticles).when(viewModel1).stringToArticles(Mockito.any());

        //Act
        viewModel1.getApiCachedLiveData().observeForever(apiCachedObserver);
        viewModel1.getArticleResponseLiveData().observeForever(dataObserver);

        viewModel1.getCachedArticles();
        SystemClock.sleep(2000);

        //Verify
        verify(apiCachedObserver).onChanged(true);
        verify(dataObserver).onChanged(cachingArticles);
        verify(dataObserver).onChanged(apiArticles);
    }

    @Test
    public void testGetCachedArticlesData_whenThrowsError() {
        //Assemble
        Articles apiArticles = new Articles();
        when(repository.getArticles(any(), any())).thenReturn(Single.just(apiArticles));

        Throwable throwable = new Throwable("Exception");
        when(cachingRepository.getCachingByApi(Apis.ARTICLES)).thenReturn(Single.error(throwable));

        //Act
        viewModel.getApiCachedLiveData().observeForever(apiCachedObserver);
        viewModel.getArticleResponseLiveData().observeForever(dataObserver);

        viewModel.getCachedArticles();
        SystemClock.sleep(2000);

        //Verify
        verify(apiCachedObserver).onChanged(false);
        verify(dataObserver).onChanged(apiArticles);
    }

    @Test
    public void testGetArticlesData_whenReturnsData() {
        //Assemble
        Articles articles = new Articles();
        when(repository.getArticles(any(), any())).thenReturn(Single.just(articles));

        //Act
        viewModel.getErrorLiveData().observeForever(errorObserver);
        viewModel.getLoadingLiveData().observeForever(loadingObserver);
        viewModel.getArticleResponseLiveData().observeForever(dataObserver);

        viewModel.getArticles();
        SystemClock.sleep(2000);

        //Verify
        verify(loadingObserver).onChanged(true);
        verify(dataObserver).onChanged(articles);
        verify(loadingObserver).onChanged(false);
    }

    @Test
    public void testGetArticlesData_whenThrowsError() {

        //Assemble
        Throwable throwable = new Throwable("Exception");
        when(repository.getArticles(any(), any())).thenReturn(Single.error(throwable));

        //Act
        viewModel.getErrorLiveData().observeForever(errorObserver);
        viewModel.getLoadingLiveData().observeForever(loadingObserver);
        viewModel.getArticleResponseLiveData().observeForever(dataObserver);

        viewModel.getArticles();
        SystemClock.sleep(2000);

        //Verify
        verify(loadingObserver).onChanged(true);
        verify(errorObserver).onChanged(true);
        verify(loadingObserver).onChanged(false);
    }


    @After
    public void tearDown() {
        repository = null;
        cachingRepository = null;
    }
}
