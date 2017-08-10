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
import com.xushuai.yj.adapter.YhAdapter;
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
 * funcation: 优惠界面TabLayout的Fragment界面
 */

public class TabLFragment extends Fragment {

    private View view;
    private List<YhBean.DataBean.ProductsBean> list;
    private ListView yh_lv;
    private YhAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.yh_title, null);
        //查找控件id
        initView();

        //添加数据
        initData();

        return view;
    }

    private void initView() {
        yh_lv = (ListView) view.findViewById(R.id.yh_lv);
    }

    private void initData() {
        list = new ArrayList<>();
        adapter = new YhAdapter(list, getActivity());
        yh_lv.setAdapter(adapter);
        String url = "http://api.eleteam.com/v1/product/list-featured-price";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                YhBean yhBean = gson.fromJson(response.body().string(), YhBean.class);
                list.addAll(yhBean.getData().getProducts());
            }
        });
    }
}