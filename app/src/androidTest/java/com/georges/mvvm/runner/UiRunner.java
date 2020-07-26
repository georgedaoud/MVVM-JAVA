package com.georges.mvvm.runner;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;

import androidx.test.runner.AndroidJUnitRunner;

import com.georges.mvvm.TestApplicationClass;

@SuppressWarnings("unused")
public class UiRunner extends AndroidJUnitRunner {
    @Override
    public void onCreate(Bundle arguments) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        super.onCreate(arguments);
    }

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return super.newApplication(cl, TestApplicationClass.class.getName(), context);
    }
}
