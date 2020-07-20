package com.georges.mvvm.repository;

import com.georges.mvvm.repository.model.resp.Articles;

import io.reactivex.Single;

public class Repository {

    private final Api api;

    public Repository(Api apiCallInterface) {
        this.api = apiCallInterface;
    }


    public Single<Articles> getArticles(String section, String period) {
        return api.getArticles(section, period);
    }
}
