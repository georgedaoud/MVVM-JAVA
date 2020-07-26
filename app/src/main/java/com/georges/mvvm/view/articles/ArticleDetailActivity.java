package com.georges.mvvm.view.articles;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.georges.mvvm.R;
import com.georges.mvvm.databinding.ActivityArticleDetailBinding;
import com.georges.mvvm.repository.model.resp.Result;

import static com.georges.mvvm.view.articles.ArticlesListActivity.RESULT_EXTRA;

public class ArticleDetailActivity extends AppCompatActivity {//NOSONAR
    private ActivityArticleDetailBinding activityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setupView();


    }

    private void initDataBinding() {
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_article_detail);
        Intent i = getIntent();
        Result result = (Result) i.getSerializableExtra(RESULT_EXTRA);
        if (result != null)
            activityBinding.setModel(result);
    }

    private void setupView() {
        setSupportActionBar(activityBinding.detailToolbar);
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}