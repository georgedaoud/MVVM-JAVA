package com.georges.mvvm.repository;

import com.georges.mvvm.repository.model.resp.Articles;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {


    //GLOBAL APIS

    @GET("mostpopular/v2/{section}/{period}.json")
    Single<Articles> getArticles(@Path("section") String section, @Path("period") String period);

}

