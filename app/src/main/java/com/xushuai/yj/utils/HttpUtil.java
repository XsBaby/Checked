package com.xushuai.yj.utils;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * date:2017/8/9
 * author:徐帅(acer)
 * funcation:OKHttp网络请求的工具类
 */
public class HttpUtil {

    //网络请求OKHttp
    public static void sendOkHttpRequest(String address, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }
}