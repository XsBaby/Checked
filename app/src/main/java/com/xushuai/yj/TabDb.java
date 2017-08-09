package com.xushuai.yj;

import com.xushuai.yj.fragment.MineFragment;
import com.xushuai.yj.fragment.ShoppingFragment;
import com.xushuai.yj.fragment.YouhuiFragment;
import com.xushuai.yj.fragment.YueguangFragment;

public class TabDb {
    /***
     * 获得底部所有项
     */
    public static String[] getTabsTxt() {
        String[] tabs = {"月光茶人", "优惠", "购物车", "我的"};
        return tabs;
    }

    /***
     * 获得所有Fragment
     */
    public static Class[] getFramgent() {
        Class[] cls = {YueguangFragment.class, YouhuiFragment.class, ShoppingFragment.class, MineFragment.class};
        return cls;
    }

    /***
     * 获得所有点击前的图片
     */
    public static int[] getTabsImg() {
        int[] img = {R.mipmap.tab_home, R.mipmap.tab_topic, R.mipmap.main_index_cart_normal, R.mipmap.main_index_my_normal};
        return img;
    }

    /***
     * 获得所有点击后的图片
     */
    public static int[] getTabsImgLight() {
        int[] img = {R.mipmap.tab_home_s, R.mipmap.tab_topic_s, R.mipmap.main_index_cart_pressed, R.mipmap.main_index_my_pressed};
        return img;
    }
}