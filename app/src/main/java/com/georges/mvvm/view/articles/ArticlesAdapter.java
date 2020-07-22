package com.georges.mvvm.view.articles;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.georges.mvvm.R;
import com.georges.mvvm.databinding.ItemArticleBinding;
import com.georges.mvvm.repository.model.resp.Result;

import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder> {
    private final List<Result> arrayList;
    private LayoutInflater layoutInflater;
    final ArticlesViewModel viewModel;

    public ArticlesAdapter(List<Result> arrayList, ArticlesViewModel viewModel) {
        this.arrayList = arrayList;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemArticleBinding itemArticleBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_article, parent, false);
        return new ArticlesViewHolder(itemArticleBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder holder, int position) {
        Result myListViewModel = arrayList.get(position);
        holder.bind(myListViewModel, viewModel);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static class ArticlesViewHolder extends RecyclerView.ViewHolder {
        private final ItemArticleBinding itemArticleBinding;

        public ArticlesViewHolder(@NonNull ItemArticleBinding itemArticleBinding) {
            super(itemArticleBinding.getRoot());
            this.itemArticleBinding = itemArticleBinding;
        }

        public void bind(Result result, ArticlesViewModel articlesViewModel) {
            this.itemArticleBinding.setModel(result);
            this.itemArticleBinding.setViewModel(articlesViewModel);
            itemArticleBinding.executePendingBindings();
        }
    }
}
