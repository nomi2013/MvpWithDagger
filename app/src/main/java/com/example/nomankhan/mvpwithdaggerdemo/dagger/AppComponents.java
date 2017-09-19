package com.example.nomankhan.mvpwithdaggerdemo.dagger;

import com.example.nomankhan.mvpwithdaggerdemo.core.MyApplication;
import com.example.nomankhan.mvpwithdaggerdemo.ui.main.MainActivity;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by Noman Khan on 19/09/17.
 */

@Component(modules = AppModules.class)
@Singleton
public interface AppComponents {

    void inject(MyApplication context);
    void inject(MainActivity mainActivity);

}
