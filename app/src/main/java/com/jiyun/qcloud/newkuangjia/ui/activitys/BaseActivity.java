package com.jiyun.qcloud.newkuangjia.ui.activitys;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jiyun.qcloud.newkuangjia.MainApp;
import com.jiyun.qcloud.newkuangjia.entity.ShiGuang;
import com.jiyun.qcloud.newkuangjia.entity.Test;
import com.jiyun.qcloud.newkuangjia.mvp.IView;
import com.jiyun.qcloud.newkuangjia.mvp.Presenter;
import com.jiyun.qcloud.newkuangjia.mvp.model.BaseCard;
import com.jiyun.qcloud.newkuangjia.mvp.model.Card;
import com.jiyun.qcloud.newkuangjia.mvp.model.Info;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

public class BaseActivity extends AppCompatActivity implements IView {
    @Inject
    protected Presenter mPresenter;
    @Inject
    protected Picasso picasso;
    @Inject
    Context mContext;


    protected ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MainApp)getApplication()).getmAppComponent().inject(this);

        mPresenter.setmView(this);

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        hideProgress();
    }

    @Override
    public void onUpdate(BaseCard card) {

    }

    @Override
    public void onUpdate(List<Card> card) {

    }

    @Override
    public void onUpData(Info info) {
      //  System.out.println("成功"+info.patch);
    }

    @Override
    public void onTestData(Test test) {
        Log.e("成功", test.getTitle());
    }

    @Override
    public void onShiGuang(ShiGuang shiGuang) {

    }

    @Override
    public void showProgress() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
    }
}
