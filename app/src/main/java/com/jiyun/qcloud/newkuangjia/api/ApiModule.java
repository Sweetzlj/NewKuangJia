package com.jiyun.qcloud.newkuangjia.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jiyun.qcloud.newkuangjia.mvp.model.Card;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chj on 2017/8/30.
 */
//http://115.182.35.91/api/getVideoInfoForCBox.do?pid=c38d42416d604d97bba556438e459a2d
@Module
public class ApiModule {
    public static final String BASE_URL="https://omgvamp-hearthstone-v1.p.mashape.com";
    public static final String TEST_URL="http://115.182.35.91/api/";
    public static final String SHIGUANG="https://api-m.mtime.cn/";
    @Provides
    @Singleton//单例
    public IApi getCardsApi(OkHttpClient client){
        //这是一个Gson的万能解析工具
        Gson gson=new GsonBuilder()
                .registerTypeAdapter(new TypeToken<List<Card>>() {}.getType(),new CardsDeserializer()).create();
        //创建一个Retrofit对象
        Retrofit CardsApiAdapter=new Retrofit.Builder()
                .baseUrl(TEST_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        //返回Retrofit接口的实现类
        return CardsApiAdapter.create(IApi.class);
    }

    @Provides
    @Singleton//单例
    public IApi2 getCardsApi2(OkHttpClient client){
        //这是一个Gson的万能解析工具
        Gson gson=new GsonBuilder()
                .registerTypeAdapter(new TypeToken<List<Card>>() {}.getType(),new CardsDeserializer()).create();
        //创建一个Retrofit对象
        Retrofit CardsApiAdapter=new Retrofit.Builder()
                .baseUrl(SHIGUANG)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        //返回Retrofit接口的实现类
        return CardsApiAdapter.create(IApi2.class);
    }
}
