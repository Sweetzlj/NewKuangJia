package com.jiyun.qcloud.newkuangjia.api;

import com.jiyun.qcloud.newkuangjia.entity.Test;
import com.jiyun.qcloud.newkuangjia.mvp.model.Card;
import com.jiyun.qcloud.newkuangjia.mvp.model.Cardback;
import com.jiyun.qcloud.newkuangjia.mvp.model.Info;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by chj on 2017/8/30.
 */

public interface IApi {
    @GET("info")
    Observable<Info> getInfo();

    @GET("getVideoInfoForCBox.do")
    Observable<Test> getTestData(@Query("pid") String pid);


    @GET("cards")
    Observable<List<Card>> getAllCards(@Query("locale") String locale);

    @GET("cards/search/{name}")
    rx.Observable<List<Card>> searchCards(@Path("name") String classes, @Query("locale") String locale);

    @GET("cards/sets/{set}")
    rx.Observable<List<Card>> getCardSet(@Path("set") String classes, @Query("locale") String locale);

    @GET("cards/classes/{class}")
    rx.Observable<List<Card>> getCardsByClass(@Path("class") String classes, @Query("locale") String locale);

    @GET("cards/factions/{faction}")
    rx.Observable<List<Card>> getCardByFaction(@Path("faction") String classes, @Query("locale") String locale);

    @GET("cards/qualities/{quality}")
    rx.Observable<List<Card>> getCardByQuality(@Path("quality") String classes, @Query("locale") String locale);

    @GET("cards/races/{race}")
    rx.Observable<List<Card>> getCardByRace(@Path("race") String classes, @Query("locale") String locale);

    @GET("cards/types/{menuType}")
    rx.Observable<List<Card>> getCardByType(@Path("menuType") String classes, @Query("locale") String locale);

    @GET("cards/{name}")
    rx.Observable<List<Card>> getSingleCard(@Path("name") String classes, @Query("locale") String locale);

    @GET("cards/classes/cardbacks")
    rx.Observable<List<Cardback>> getCardsBacks(@Query("locale") String locale);


}
