package com.georges.mvvm.room;

import android.os.AsyncTask;

import com.georges.mvvm.room.roomModel.CachingModel;

import java.util.Date;

import javax.inject.Inject;

import io.reactivex.Single;


public class NYTimesDataSource implements CachingRepository {
    private final NYTimesDao dao;

    @Inject
    public NYTimesDataSource(NYTimesDao dao) {
        this.dao = dao;

    }

    @Override
    public void insert(CachingModel cashingModel) {
        new insertAsyncTask(dao).execute(cashingModel);
    }

    @Override
    public Single<CachingModel> getCachingByApi(long api) {
        return dao.getCachingByApi(api);
    }

    @Override
    public void updateCaching(String apiResponse, Date date, long api) {
        new updateAsyncTask(dao).execute(new CachingModel(api, apiResponse, date));
    }

    private static class insertAsyncTask extends AsyncTask<CachingModel, Void, Void> {

        private final NYTimesDao mAsyncTaskDao;

        insertAsyncTask(NYTimesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CachingModel... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<CachingModel, Void, Void> {

        private final NYTimesDao mAsyncTaskDao;

        updateAsyncTask(NYTimesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CachingModel... params) {
            mAsyncTaskDao.updateCaching(params[0].getApiResponse(), params[0].getCachingDate().getTime(), params[0].getApi());
            return null;
        }
    }
}

