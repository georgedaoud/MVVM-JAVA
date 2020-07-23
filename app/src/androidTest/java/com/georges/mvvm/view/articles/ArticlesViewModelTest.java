package com.georges.mvvm.view.articles;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.georges.mvvm.repository.Repository;
import com.georges.mvvm.repository.model.resp.Articles;
import com.georges.mvvm.room.CachingRepository;
import com.georges.mvvm.rule.RxSchedulersOverrideRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    private ArticlesViewModel viewModel;


    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        viewModel = new ArticlesViewModel(repository, cachingRepository);
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
