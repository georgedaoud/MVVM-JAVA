package com.georges.mvvm.room;

import android.os.AsyncTask;

import com.georges.mvvm.room.model.CachingModel;

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
    public void insert(long api, String apiResponse, Date date) {
        new InsertAsyncTask(dao).execute(new CachingModel(api, apiResponse, date));
    }

    @Override
    public Single<CachingModel> getCachingByApi(long api) {
        return dao.getCachingByApi(api);
    }

    @Override
    public void updateCaching(long api, String apiResponse, Date date) {
        new UpdateAsyncTask(dao).execute(new CachingModel(api, apiResponse, date));
    }

    private static class InsertAsyncTask extends AsyncTask<CachingModel, Void, Void> {

        private final NYTimesDao mAsyncTaskDao;

        InsertAsyncTask(NYTimesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CachingModel... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<CachingModel, Void, Void> {

        private final NYTimesDao mAsyncTaskDao;

        UpdateAsyncTask(NYTimesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CachingModel... params) {
            mAsyncTaskDao.updateCaching(params[0].getApi(), params[0].getApiResponse(), params[0].getCachingDate().getTime());
            return null;
        }
    }
}

