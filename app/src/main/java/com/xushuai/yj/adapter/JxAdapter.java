package com.xushuai.yj.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xushuai.yj.R;
import com.xushuai.yj.bean.JxBean;

import java.util.List;

/**
 * date:2017/8/10
 * author:徐帅(acer)
 * funcation:优惠界面 第二个Tab(为你精选)的数据适配器
 */

public class JxAdapter extends BaseAdapter {

    private List<JxBean.DataBean.ProductsBean> list;
    private Context context;

    public JxAdapter(List<JxBean.DataBean.ProductsBean> list, Context context) {
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
            convertView = View.inflate(context, R.layout.yh_item, null);
            viewHolder = new ViewHolder();
            viewHolder.yh_image = (ImageView) convertView.findViewById(R.id.yh_image);
            viewHolder.yh_name = (TextView) convertView.findViewById(R.id.yh_name);
            viewHolder.yh_des = (TextView) convertView.findViewById(R.id.yh_des);
            viewHolder.yh_price = (TextView) convertView.findViewById(R.id.yh_price);
            viewHolder.yh_count = (TextView) convertView.findViewById(R.id.yh_count);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //为图片赋值
        Glide.with(context).load(list.get(position).getImage_small()).into(viewHolder.yh_image);
        viewHolder.yh_name.setText(list.get(position).getName());
        viewHolder.yh_des.setText(list.get(position).getShort_description());
        viewHolder.yh_price.setText("￥" + list.get(position).getFeatured_price());
        viewHolder.yh_count.setText("已售出" + list.get(position).getSort() + "份");
        return convertView;
    }

    class ViewHolder {
        private ImageView yh_image;
        private TextView yh_name;
        private TextView yh_des;
        private TextView yh_price;
        private TextView yh_count;
    }
}