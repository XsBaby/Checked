package com.xushuai.yj.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xushuai.yj.fragment.TabLFragment;

/**
 * date:2017/8/9
 * author:徐帅(acer)
 * funcation: 优惠界面TabLayout的适配器
 */

public class TabLayoutAdapter extends FragmentPagerAdapter {
    String[] title = new String[]{"天天优惠", "为你精选", "亲的最爱"};

    public TabLayoutAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        TabLFragment fragment = new TabLFragment();
        return fragment;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}