package com.example.nomankhan.mvpwithdaggerdemo.ui.main;

import android.content.Context;
import com.example.nomankhan.mvpwithdaggerdemo.mvp.BasePresenter;
import com.example.nomankhan.mvpwithdaggerdemo.utils.CommonUtilities;
import com.example.nomankhan.mvpwithdaggerdemo.utils.GlobalVariables.Globals;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Func0;

/**
 * Created by Noman Khan on 19/09/17.
 */

public class MainPresenter extends BasePresenter<MainMvpView> {


    @Inject
    @Named(Globals.MAIN_THREAD)
    Scheduler mainThread;

    @Inject
    @Named(Globals.NEW_THREAD)
    Scheduler newThread;

    @Inject
    public MainPresenter() {
    }

    void getContactList() {

        Context context = (Context) getMvpView();
        getMvpView().showProgress();
        getData(context)
                .subscribeOn(newThread)
                .observeOn(mainThread)
                .subscribe(new Subscriber<List<MyContact>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().hideProgress();
                    }

                    @Override
                    public void onNext(List<MyContact> list) {
                        getMvpView().hideProgress();
                        getMvpView().fetchedContactList(list);
                    }
                });

    }


    rx.Observable<List<MyContact>> getData(final Context ctx) {
        return rx.Observable.defer(new Func0<Observable<List<MyContact>>>() {
            @Override
            public rx.Observable<List<MyContact>> call() {
                return rx.Observable.just(CommonUtilities.getAllContacts(ctx));
            }
        });
    }

}
