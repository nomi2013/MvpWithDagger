package com.example.nomankhan.mvpwithdaggerdemo.core;

import android.app.Application;
import com.example.nomankhan.mvpwithdaggerdemo.dagger.AppComponents;
import com.example.nomankhan.mvpwithdaggerdemo.dagger.AppModules;
import com.example.nomankhan.mvpwithdaggerdemo.dagger.DaggerAppComponents;

/**
 * Created by Noman Khan on 19/09/17.
 */

public class MyApplication extends Application {

    public static AppComponents appComponents;
    public static MyApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;
        injectDependencies(INSTANCE);
    }

    public static void injectDependencies(MyApplication context) {
        appComponents = DaggerAppComponents.builder()
                .appModules(new AppModules(context))
                .build();
        appComponents.inject(context);
    }

    public static AppComponents getAppComponents() {
        return appComponents;
    }

}
