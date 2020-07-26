package com.georges.mvvm.di;

import android.app.Application;

import androidx.room.Room;

import com.georges.mvvm.BuildConfig;
import com.georges.mvvm.repository.Api;
import com.georges.mvvm.repository.Repository;
import com.georges.mvvm.room.CachingRepository;
import com.georges.mvvm.room.NYTimesDao;
import com.georges.mvvm.room.NYTimesDataSource;
import com.georges.mvvm.room.database.CashingDatabase;
import com.georges.mvvm.util.ApiKeyInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class TestUtilsModule {


    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    Api getApiCallInterface(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }

    @Provides
    @Singleton
    OkHttpClient getRequestHeader() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(30, TimeUnit.MINUTES);
        httpClient.readTimeout(30, TimeUnit.MINUTES);
        httpClient.writeTimeout(30, TimeUnit.MINUTES);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }
        httpClient.addInterceptor(new ApiKeyInterceptor());
        return httpClient.build();
    }

    @Provides
    @Singleton
    Repository getRepository(Api api) {
        return new Repository(api);
    }

    @Singleton
    @Provides
    CashingDatabase providesRoomDatabase(Application application) {
        return Room.databaseBuilder(application, CashingDatabase.class, "cashing-db").build();

    }

    @Singleton
    @Provides
    NYTimesDao providesCashingDao(CashingDatabase cashingDatabase) {
        return cashingDatabase.homeDao();
    }

    @Singleton
    @Provides
    CachingRepository cashingRepository(NYTimesDao nyTimesDao) {
        return new NYTimesDataSource(nyTimesDao);
    }
}
