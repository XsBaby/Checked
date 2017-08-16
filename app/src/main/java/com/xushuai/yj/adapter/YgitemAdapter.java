package com.xushuai.yj.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xushuai.yj.R;
import com.xushuai.yj.bean.YgBean;

import java.util.List;

/**
 * date:2017/8/14
 * author:徐帅(acer)
 * funcation:月光茶人界面的商品信息适配器
 */

public class YgitemAdapter extends BaseAdapter {

    private Context context;
    List<YgBean.DataBean.CategoriesBean.ProductsBean> list;

    public YgitemAdapter(Context context, List<YgBean.DataBean.CategoriesBean.ProductsBean> list) {
        this.context = context;
        this.list = list;
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
            convertView = View.inflate(context, R.layout.yg_msg, null);

            viewHolder = new ViewHolder();
            viewHolder.yg_image = (ImageView) convertView.findViewById(R.id.yg_image);
            viewHolder.yg_name = (TextView) convertView.findViewById(R.id.yg_name);
            viewHolder.yg_price = (TextView) convertView.findViewById(R.id.yg_price);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(list.get(position).getImage_small()).into(viewHolder.yg_image);
        viewHolder.yg_name.setText(list.get(position).getName());
        if (list.get(position).getFeatured_price() != null) {
            viewHolder.yg_price.setText("￥" + list.get(position).getFeatured_price());
        } else {
        }
        return convertView;
    }

    //内部类
    class ViewHolder {
        ImageView yg_image;
        TextView yg_name, yg_price;
    }
}