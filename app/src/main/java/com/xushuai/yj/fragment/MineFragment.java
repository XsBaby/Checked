package com.xushuai.yj.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xushuai.yj.LoginActivity;
import com.xushuai.yj.R;

/**
 * date:2017/8/9
 * author:徐帅(acer)
 * funcation: 我的界面的Fragment
 */
public class MineFragment extends Fragment implements View.OnClickListener {

    private View view;
    private ImageView right_jt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.mine, null);
        initView();
        return view;
    }

    private void initView() {
        //查找控件
        right_jt = (ImageView) view.findViewById(R.id.right_jt);

        //添加监听事件
        right_jt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_jt:
                //跳转到登录界面
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}