package com.xushuai.yj.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xushuai.yj.R;
import com.xushuai.yj.adapter.TabLayoutAdapter;

/**
 * date:2017/8/9
 * author:徐帅(acer)
 * funcation: 优惠界面的Fragment
 */
public class YouhuiFragment extends Fragment {

    private View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.youhui, null);

        //查找控件
        initView();
        //添加数据
        initData();
        return view;
    }

    private void initView() {
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
    }

    private void initData() {
        //实例化TabLayout的适配器
        TabLayoutAdapter adapter = new TabLayoutAdapter(getFragmentManager());
        //将适配器赋值给ViewPager
        viewPager.setAdapter(adapter);
        //设置和ViewPager滑动关联
        tabLayout.setupWithViewPager(viewPager);

        //设置tablayout显示的模型
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //显示的模型仅在窗体里
//        tablayout.setTabMode(TabLayout.MODE_FIXED);
    }
}