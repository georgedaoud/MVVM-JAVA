package com.georges.mvvm.room;


import com.georges.mvvm.room.model.CachingModel;

import java.util.Date;

import io.reactivex.Single;

public interface CachingRepository {

    void insert(long api, String apiResponse, Date date);

    Single<CachingModel> getCachingByApi(long api);

    void updateCaching(long api, String apiResponse, Date date);
}