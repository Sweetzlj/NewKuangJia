package com.jiyun.qcloud.newkuangjia.api;

import com.jiyun.qcloud.newkuangjia.entity.ShiGuang;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by my301s on 2017/9/6.
 */

public interface IApi2 {
    @GET("Showtime/HotCitiesByCinema.api")
    Observable<ShiGuang> getShiGuang();
}
