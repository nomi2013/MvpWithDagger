package com.example.nomankhan.mvpwithdaggerdemo.mvp;

/**
 * Base interface that any class that wants to act as a View in the MVP (Model View Presenter)
 * pattern must implement. Generally this interface will be extended by a more specific interface
 * that then usually will be implemented by an Activity or Fragment.
 */
public interface MvpView {
    void showProgress();

    void hideProgress();

    void showError(Exception arg0);

    void showError(Throwable e);
}