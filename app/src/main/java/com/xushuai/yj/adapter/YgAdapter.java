package com.xushuai.yj.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xushuai.yj.R;
import com.xushuai.yj.bean.YgBean;

import java.util.List;

/**
 * date:2017/8/9
 * author:徐帅(acer)
 * funcation: 月光茶人界面的适配器
 */

public class YgAdapter extends BaseAdapter {
    private List<YgBean.DataBean.CategoriesBean> list;
    private Context context;

    public YgAdapter(List<YgBean.DataBean.CategoriesBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.list_item1, null);
            viewHolder = new ViewHolder();
            viewHolder.title__tean = (TextView) convertView.findViewById(R.id.title_tean);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title__tean.setText(list.get(position).getName());
        return convertView;
    }

    public class ViewHolder {
        TextView title__tean;
    }
}