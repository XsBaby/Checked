package com.xushuai.yj.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xushuai.yj.R;

/**
 * date:2017/8/8
 * author:徐帅(acer)
 * funcation: 我的界面的Fragment
 */
public class MineFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.mine, null);
        return view;
    }
}