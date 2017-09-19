package com.example.nomankhan.mvpwithdaggerdemo.ui.main;

import com.example.nomankhan.mvpwithdaggerdemo.mvp.MvpView;
import java.util.List;

/**
 * Created by Noman Khan on 19/09/17.
 */

interface MainMvpView extends MvpView{

    void fetchedContactList(List<MyContact> list);
}
