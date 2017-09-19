package com.example.nomankhan.mvpwithdaggerdemo.ui.main;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import com.example.nomankhan.mvpwithdaggerdemo.R;
import com.example.nomankhan.mvpwithdaggerdemo.core.BaseActivity;
import com.example.nomankhan.mvpwithdaggerdemo.core.MyApplication;
import com.example.nomankhan.mvpwithdaggerdemo.utils.CommonUtilities;
import java.util.List;
import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainMvpView {

    private static final int MY_PERMISSIONS = 3400;

    @Inject
    MainPresenter presenter;

    @Inject
    ContactsAdapter adapter;

    @BindView(R.id.rcv)
    RecyclerView rcv;


    private String[] permissions = new String[]{
            Manifest.permission.READ_CONTACTS};
    private LinearLayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter.attachView(this);

        if (!CommonUtilities.hasPermission(context, Manifest.permission.READ_CONTACTS)) {
            ActivityCompat.requestPermissions(this, permissions, MY_PERMISSIONS);
        } else {
            presenter.getContactList();
        }

        layoutManager = new LinearLayoutManager(context);
        rcv.setLayoutManager(layoutManager);
        rcv.addItemDecoration(new DividerItemDecoration(context, layoutManager.getOrientation()));
        rcv.setAdapter(adapter);
    }

    @Override
    public void injectDependency() {
        MyApplication.getAppComponents().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void showProgress() {
            showProgressDialog();
    }

    @Override
    public void hideProgress() {
        hideProgressDialog();
    }

    @Override
    public void showError(Exception arg0) {

    }

    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void fetchedContactList(List<MyContact> list) {
            adapter.updateAdapter(list);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
            String permissions[], int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSIONS: {
                presenter.getContactList();
            }
            break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.detachView();
    }

}
