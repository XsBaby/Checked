package com.xushuai.yj.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * date:2017/8/16
 * author:徐帅(acer)
 * funcation: 商品详情页的图片轮播工具类
 */
public class BannerImageLoad extends ImageLoader {
    //在该方法内用Glide进行加载图片
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }
}