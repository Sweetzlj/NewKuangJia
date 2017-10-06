package com.jiyun.qcloud.newkuangjia.mvp;

import com.jiyun.qcloud.newkuangjia.entity.ShiGuang;
import com.jiyun.qcloud.newkuangjia.entity.Test;
import com.jiyun.qcloud.newkuangjia.mvp.model.BaseCard;
import com.jiyun.qcloud.newkuangjia.mvp.model.Card;
import com.jiyun.qcloud.newkuangjia.mvp.model.Info;

import java.util.List;

/**
 * Created by chj on 2017/8/30.
 */

public interface IView {
    void onComplete();
    void onError();
    public void onError(String error);

    public void onUpdate(BaseCard card);
    void onUpdate(List<Card> card);

    public void onUpData(Info info);

    public void onTestData(Test test);

    public void onShiGuang(ShiGuang shiGuang);

    public void showProgress();

    public void hideProgress();
}
