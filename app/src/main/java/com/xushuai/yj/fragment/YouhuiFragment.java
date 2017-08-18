package com.xushuai.yj.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.xushuai.yj.R;
import com.xushuai.yj.adapter.JxAdapter;
import com.xushuai.yj.adapter.TabLayoutAdapter;
import com.xushuai.yj.adapter.YhAdapter;
import com.xushuai.yj.bean.JxBean;
import com.xushuai.yj.bean.YhBean;
import com.xushuai.yj.utils.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * date:2017/8/9
 * author:徐帅(acer)
 * funcation: 优惠界面的Fragment
 */
public class YouhuiFragment extends Fragment {

    private View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabLayoutAdapter tabLayoutAdapter;
    private List<View> mlist = new ArrayList<>();
    private ListView yh_lv;
    private ListView jx_lv;
    private View wnjx;
    private View ttyh;
    private View qdza;
    private Handler handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.youhui, null);

        //查找控件
        initView();

        initHandler();

        //添加界面
        initData();

        return view;
    }

    private void initHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        YhBean yhBean = (YhBean) msg.obj;

                        YhAdapter adapter = new YhAdapter(yhBean.getData().getProducts(), getActivity());
                        yh_lv.setAdapter(adapter);
                        break;
                    case 1:
                        JxBean jxBean = (JxBean) msg.obj;
                        JxAdapter jxadapter = new JxAdapter(jxBean.getData().getProducts(), getActivity());
                        jx_lv.setAdapter(jxadapter);
                        break;
                }
            }
        };
    }

    private void initView() {
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
    }

    private void initData() {
        //设置tablayout每个界面的Tab标题
        tabLayout.addTab(tabLayout.newTab().setText("天天优惠"));
        tabLayout.addTab(tabLayout.newTab().setText("为你精选"));
        tabLayout.addTab(tabLayout.newTab().setText("亲的最爱"));
        //得到各个ViewPager的布局
        LayoutInflater lf = LayoutInflater.from(getActivity());
        ttyh = lf.inflate(R.layout.ttyh, null);
        wnjx = lf.inflate(R.layout.wnjx, null);
        qdza = lf.inflate(R.layout.qdza, null);

        yh_lv = (ListView) ttyh.findViewById(R.id.yh_lv);
        jx_lv = (ListView) wnjx.findViewById(R.id.jx_lv);

        //请求第一个接口的数据
        postOne();
        //请求第二个接口的数据
        postTwo();

        //将布局添加进list集合中
        mlist.add(ttyh);
        mlist.add(wnjx);
        mlist.add(qdza);

        //将集合放进TabLayoutAdapter适配器中
        tabLayoutAdapter = new TabLayoutAdapter(mlist);
        //再将适配器添加进viewpager中
        viewPager.setAdapter(tabLayoutAdapter);

        //Viewpager的监听事件：
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //TableLayout的监听事件
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    //请求第一个界面的数据
    private void postOne() {
        String url = "http://api.eleteam.com/v1/product/list-featured-price";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                YhBean yhBean = gson.fromJson(response.body().string(), YhBean.class);
                System.out.println("yhBean.toString() = " + yhBean.toString());
                Message me = new Message();
                me.what = 0;
                me.obj = yhBean;
                handler.sendMessage(me);
            }
        });
    }

    //请求第二个界面的数据
    private void postTwo() {
        String url2 = "http://api.eleteam.com/v1/product/list-featured-topic";
        HttpUtil.sendOkHttpRequest(url2, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                JxBean jxBean = gson.fromJson(response.body().string(), JxBean.class);
                System.out.println("jxBean.toString() = " + jxBean.toString());
                Message me = new Message();
                me.what = 1;
                me.obj = jxBean;
                handler.sendMessage(me);
            }
        });
    }
}