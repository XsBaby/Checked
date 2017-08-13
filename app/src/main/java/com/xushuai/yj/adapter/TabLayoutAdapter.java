package com.xushuai.yj.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * date:2017/8/9
 * author:徐帅(acer)
 * funcation: 优惠界面TabLayout的适配器
 */

public class TabLayoutAdapter extends PagerAdapter {
    private List<View> mlist;

    public TabLayoutAdapter(List<View> mlist) {
        this.mlist = mlist;
    }

    @Override
    public int getCount() {
        return mlist != null ? mlist.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mlist.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mlist.get(position));
    }
}