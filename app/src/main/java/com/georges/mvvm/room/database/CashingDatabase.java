package com.georges.mvvm.room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.georges.mvvm.room.NYTimesDao;
import com.georges.mvvm.room.model.CachingModel;


@Database(entities = {CachingModel.class}, version = CashingDatabase.VERSION, exportSchema = false)
public abstract class CashingDatabase extends RoomDatabase {
    static final int VERSION = 1;

    public abstract NYTimesDao homeDao();

}
