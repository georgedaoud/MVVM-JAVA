package com.georges.mvvm.view.articles;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.georges.mvvm.R;
import com.georges.mvvm.databinding.ActivityArticlesListBinding;
import com.georges.mvvm.repository.model.resp.Result;
import com.georges.mvvm.util.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class ArticlesListActivity extends AppCompatActivity implements ArticlesNavigator {
    public static final String RESULT_EXTRA = "result";

    @Inject
    ViewModelFactory viewModelFactory;
    ArticlesViewModel viewModel;
    private ActivityArticlesListBinding activityBinding;
    private ArticlesAdapter adapter;
    private final ArrayList<Result> articleArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setupView();
        observeLiveData();

    }

    private void initDataBinding() {
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_articles_list);
        AndroidInjection.inject(this);
        viewModel = new ViewModelProvider(getViewModelStore(), viewModelFactory).get(ArticlesViewModel.class);
        viewModel.setArticlesNavigator(this);
        activityBinding.setViewModel(viewModel);
    }

    private void setupView() {
        //setup Toolbar
        setSupportActionBar(activityBinding.toolbar);
        activityBinding.toolbar.setTitle(getTitle());

        // adapter
        adapter = new ArticlesAdapter(articleArrayList, viewModel);
        activityBinding.rvArticles.setAdapter(adapter);

        // required for using databinding with livedata
        activityBinding.setLifecycleOwner(this);

    }

    private void observeLiveData() {
        viewModel.getArticleResponseLiveData().observe(this, articleResponse -> {
            if (articleResponse != null) {
                List<Result> articles = articleResponse.getResults();
                articleArrayList.clear();
                articleArrayList.addAll(articles);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void navigateToArticleDetail(Result result) {
        Intent intent = new Intent(this, ArticleDetailActivity.class);
        intent.putExtra(RESULT_EXTRA, result);
        startActivity(intent);
    }
}