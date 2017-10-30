package com.maple.touriseguide;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by rrr on 2017/10/30.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 初始化OkHttpUtils
         */
        initOkHttpClient();
    }

    private void initOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS) //链接超时
                .readTimeout(10000L, TimeUnit.MILLISECONDS) //读取超时
                .build(); //其他配置
        OkHttpUtils.initClient(okHttpClient);
    }
}
