package com.xushuai.yj.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.xushuai.yj.R;
import com.xushuai.yj.adapter.YgAdapter;
import com.xushuai.yj.adapter.YgitemAdapter;
import com.xushuai.yj.bean.YgBean;
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
 * funcation: 月光茶人Fragment
 */
public class YueguangFragment extends Fragment {

    private View view;
    private ListView name;
    private ListView msg;
    private List<YgBean.DataBean.CategoriesBean> list;
    String url = "http://api.eleteam.com/v1/category/list-with-product";
    private YgitemAdapter iadapter;
    YgAdapter adapter;

    //开启一个线程，用于发送刷新适配器消息
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.yueguang, null);
        initView();
        //添加商品名称的数据
        initNameData();
        //添加商品信息的数据
        initMsgData();

        list = new ArrayList<>();
        //给商品名称设置适配器
        adapter = new YgAdapter(list, getActivity());
        name.setAdapter(adapter);

        return view;
    }

    //查找控件
    private void initView() {
        name = (ListView) view.findViewById(R.id.name);
        msg = (ListView) view.findViewById(R.id.msg);

        //第一个listview的点击事件
        name.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<YgBean.DataBean.CategoriesBean.ProductsBean> products = list.get(position).getProducts();
                //给商品信息设置适配器
                iadapter = new YgitemAdapter(getActivity(), products);
                msg.setAdapter(iadapter);
                iadapter.notifyDataSetChanged();
            }
        });
    }

    private void initNameData() {
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                gson.fromJson(response.body().string(), YgBean.class);
            }
        });
    }

    private void initMsgData() {
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                YgBean ygBean2 = gson.fromJson(response.body().string(), YgBean.class);
                List<YgBean.DataBean.CategoriesBean> categories = ygBean2.getData().getCategories();
                list.addAll(categories);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //刷新适配器
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}