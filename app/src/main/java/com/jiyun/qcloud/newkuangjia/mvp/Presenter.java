package com.jiyun.qcloud.newkuangjia.mvp;

import android.util.Log;

import com.jiyun.qcloud.newkuangjia.api.IApi;
import com.jiyun.qcloud.newkuangjia.api.IApi2;
import com.jiyun.qcloud.newkuangjia.entity.ShiGuang;
import com.jiyun.qcloud.newkuangjia.entity.Test;
import com.jiyun.qcloud.newkuangjia.mvp.model.Card;
import com.jiyun.qcloud.newkuangjia.mvp.model.Info;
import com.jiyun.qcloud.newkuangjia.util.Util;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chj on 2017/8/30.
 */

public class Presenter {
    private IView mView;
    private static Info mInfo;
    public static Test test;
    public static ShiGuang shiGuang;

    @Inject
    IApi mIApi;

    @Inject
    IApi2 iApi2;

    @Inject
    public Presenter() {

    }

    //传递View的实现类
    public void setmView(IView mView) {
        this.mView = mView;
    }

    public void getData() {
        if (test == null) {
            mView.showProgress();
            Observable<Test> observable = mIApi.getTestData("c38d42416d604d97bba556438e459a2d");
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SelfDefineSubscriber<Test>() {
                        @Override
                        public void onNext(Test tests) {
                            test = tests;
                            Log.e("Presenter",test.getTitle());
                            //请求结果
                            mView.onTestData(test);
                            mView.hideProgress();
                        }
                    });
        }
    }

    public void getShiGuang(){
        if (shiGuang == null) {
            mView.showProgress();
            Observable<ShiGuang> observable = iApi2.getShiGuang();
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SelfDefineSubscriber<ShiGuang>() {
                        @Override
                        public void onNext(ShiGuang tests) {
                            shiGuang = tests;
                            Log.e("nnn----",shiGuang.getP().get(0).getN());
                            //请求结果
                            mView.onShiGuang(shiGuang);
                            mView.hideProgress();
                        }
                    });
        }
    }

    //请求网络
    public void getInfo() {
        if (mInfo == null) {
            mView.showProgress();
            Observable<Info> observable = mIApi.getInfo();
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SelfDefineSubscriber<Info>() {
                        @Override
                        public void onNext(Info info) {
                            mInfo = info;
                            //请求结果
                            mView.onUpData(mInfo);
                            mView.hideProgress();
                        }
                    });
        } else {
            mView.hideProgress();
            mView.onUpData(mInfo);
        }
    }

    /////////////////////////////////////这里是重复的代码
    public void getCardSet(String set) {
        mView.showProgress();

        Observable<List<Card>> observable = mIApi.getCardSet(set, getLocal());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SelfDefineSubscriber<List<Card>>() {
                    @Override
                    public void onNext(List<Card> cards) {
                        mView.onUpdate(cards);
                        mView.hideProgress();

                    }
                });
    }

    public void getCardByClass(String classes) {
        mView.showProgress();

        Observable<List<Card>> observable = mIApi.getCardsByClass(classes, getLocal());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SelfDefineSubscriber<List<Card>>() {
                    @Override
                    public void onNext(List<Card> cards) {
                        mView.onUpdate(cards);
                        mView.hideProgress();

                    }
                });
    }

    public void getCardByFaction(String faction) {
        mView.showProgress();

        Observable<List<Card>> observable = mIApi.getCardByFaction(faction, getLocal());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SelfDefineSubscriber<List<Card>>() {
                    @Override
                    public void onNext(List<Card> cards) {
                        mView.onUpdate(cards);
                        mView.hideProgress();

                    }
                });
    }

    public void getCardByQuality(String quality) {
        mView.showProgress();

        Observable<List<Card>> observable = mIApi.getCardByQuality(quality, getLocal());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SelfDefineSubscriber<List<Card>>() {
                    @Override
                    public void onNext(List<Card> cards) {
                        mView.onUpdate(cards);
                        mView.hideProgress();

                    }
                });
    }

    public void getCardByRace(String Race) {
        mView.showProgress();

        Observable<List<Card>> observable = mIApi.getCardByRace(Race, getLocal());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SelfDefineSubscriber<List<Card>>() {
                    @Override
                    public void onNext(List<Card> cards) {
                        mView.onUpdate(cards);
                        mView.hideProgress();

                    }
                });
    }

    public void getCardByType(String type) {
        mView.showProgress();

        Observable<List<Card>> observable = mIApi.getCardByType(type, getLocal());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SelfDefineSubscriber<List<Card>>() {
                    @Override
                    public void onNext(List<Card> cards) {
                        mView.onUpdate(cards);
                        mView.hideProgress();

                    }
                });
    }

    //系统语言信息获取
    private String getLocal() {
        if (mInfo != null && mInfo.locales != null) {
            String local = Util.getCurrentLauguage();
            for (String s : mInfo.locales) {
                if (s.contains(local)) {
                    return s;
                }
            }
        }

        return "enUS";
    }

    //自定义的subscribe
    class SelfDefineSubscriber<T> extends Subscriber<T> {

        @Override
        public void onCompleted() {
            mView.onComplete();
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("出现错误" + e);
            mView.onError(e.getMessage());

        }

        @Override
        public void onNext(T t) {

        }
    }
}
