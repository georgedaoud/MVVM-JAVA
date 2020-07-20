package com.georges.mvvm.room;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.georges.mvvm.room.roomModel.CachingModel;

import io.reactivex.Single;


@Dao
public interface NYTimesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CachingModel cashingModel);


    @Query("SELECT * from Caching where api=:api")
    Single<CachingModel> getCachingByApi(long api);

    @Query("UPDATE caching SET apiResponse=:apiResponse, cachingDate=:date WHERE api" +
            "=:api")
    void updateCaching(String apiResponse, long date, long api);

}
