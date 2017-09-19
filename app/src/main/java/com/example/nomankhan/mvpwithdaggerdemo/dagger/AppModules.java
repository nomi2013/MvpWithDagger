package com.example.nomankhan.mvpwithdaggerdemo.dagger;

import com.example.nomankhan.mvpwithdaggerdemo.core.MyApplication;
import com.example.nomankhan.mvpwithdaggerdemo.utils.GlobalVariables;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Noman Khan on 19/09/17.
 */

@Module
public class AppModules {

    MyApplication myApplication;

    public AppModules(MyApplication myApplication) {
        this.myApplication = myApplication;
    }

    @Provides
    @Singleton
    @Named(GlobalVariables.Globals.MAIN_THREAD)
    Scheduler provideMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @Named(GlobalVariables.Globals.NEW_THREAD)
    Scheduler provideNewThreadScheduler() {
        return Schedulers.newThread();
    }
}
