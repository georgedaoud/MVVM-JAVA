package com.georges.mvvm.util;

import com.georges.mvvm.BuildConfig;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiKeyInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder().addQueryParameter("api-key", BuildConfig.API_KEY).build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
