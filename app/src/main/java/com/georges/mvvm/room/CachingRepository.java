package com.georges.mvvm.room;


import androidx.lifecycle.LiveData;

import com.georges.mvvm.room.roomModel.CachingModel;

import java.util.Date;

import io.reactivex.Single;

public interface CachingRepository {

    void insert(CachingModel cashingModel);

    Single<CachingModel> getCachingByApi(long api);

    void updateCaching(String apiResponse, Date date, long api);
}