package com.xushuai.yj.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.xushuai.yj.R;
import com.xushuai.yj.adapter.YgAdapter;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.yueguang, null);
        initView();
        initData();
        list = new ArrayList<>();
        YgAdapter adapter = new YgAdapter(list, getActivity());
        name.setAdapter(adapter);
        return view;
    }

    //查找控件
    private void initView() {
        name = (ListView) view.findViewById(R.id.name);
        msg = (ListView) view.findViewById(R.id.msg);
    }

    //添加数据
    private void initData() {
        String url = "http://api.eleteam.com/v1/category/list-with-product";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                YgBean ygBean = gson.fromJson(response.body().string(), YgBean.class);
                list.addAll(ygBean.getData().getCategories());
            }
        });
    }
}