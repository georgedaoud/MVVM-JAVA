package com.georges.mvvm.room.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.georges.mvvm.util.DateConverter;

import java.util.Date;

@Entity(tableName = "caching")
public class CachingModel {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "api")
    private long api;
    @ColumnInfo(name = "apiResponse")
    private String apiResponse;
    @TypeConverters(DateConverter.class)
    @ColumnInfo(name = "cachingDate")
    private Date cachingDate;

    public CachingModel(long api, String apiResponse, Date cachingDate) {
        this.api = api;
        this.apiResponse = apiResponse;
        this.cachingDate = cachingDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getApi() {
        return api;
    }

    public void setApi(long api) {
        this.api = api;
    }

    public String getApiResponse() {
        return apiResponse;
    }

    public void setApiResponse(String apiResponse) {
        this.apiResponse = apiResponse;
    }

    public Date getCachingDate() {
        return cachingDate;
    }

    public void setCachingDate(Date cachingDate) {
        this.cachingDate = cachingDate;
    }
}
