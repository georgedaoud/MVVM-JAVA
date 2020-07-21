package com.georges.mvvm.view.articles;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.georges.mvvm.R;
import com.georges.mvvm.databinding.ItemArticleBinding;
import com.georges.mvvm.repository.model.resp.Result;

import java.util.ArrayList;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {
    private final ArrayList<Result> arrayList;
    private LayoutInflater layoutInflater;
    final ArticlesViewModel viewModel;

    public ArticlesAdapter(ArrayList<Result> arrayList, ArticlesViewModel viewModel) {
        this.arrayList = arrayList;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemArticleBinding itemArticleBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_article, parent, false);
        return new ViewHolder(itemArticleBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result myListViewModel = arrayList.get(position);
        holder.bind(myListViewModel, viewModel);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemArticleBinding itemArticleBinding;

        public ViewHolder(@NonNull ItemArticleBinding itemArticleBinding) {
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
