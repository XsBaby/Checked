package com.xushuai.yj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xushuai.yj.utils.BannerImageLoad;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;

/**
 * date:2017/8/17
 * author:徐帅(acer)
 * funcation: 详情页的Activity
 */
public class DetailActivity extends Activity implements View.OnClickListener {

    private ImageView xq_back;
    private TextView xq_name, xq_xj, xq_yj, xq_des;
    private String name, xj, yj, des, image1, image2, image3, image4, image5;
    private Banner banner;
    private ArrayList<String> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //获得控件id
        initView();

        //获取月光茶人界面点击第二个listview(商品)传过来的值
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        xj = intent.getStringExtra("xj");
        yj = intent.getStringExtra("yj");
        des = intent.getStringExtra("des");
        image1 = intent.getStringExtra("image1");
        image2 = intent.getStringExtra("image2");
        image3 = intent.getStringExtra("image3");
        image4 = intent.getStringExtra("image4");
        image5 = intent.getStringExtra("image5");

        //设置图片加载器
        BannerImageLoad bil = new BannerImageLoad();
        banner.setImageLoader(bil);
        //设置显示样式(显示数字指示器和标题)
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        //添加数据
        initData();
    }

    private void initView() {
        xq_back = (ImageView) findViewById(R.id.xq_back);
        xq_name = (TextView) findViewById(R.id.xq_name);
        xq_xj = (TextView) findViewById(R.id.xq_xj);
        xq_yj = (TextView) findViewById(R.id.xq_yj);
        xq_des = (TextView) findViewById(R.id.xq_des);
        banner = (Banner) findViewById(R.id.banner);

        xq_back.setOnClickListener(this);
    }

    private void initData() {
        xq_name.setText(name);
        xq_xj.setText(xj);
        xq_yj.setText(yj);
        xq_des.setText(des);
        images.add(image1);
        images.add(image2);
        images.add(image3);
        images.add(image4);
        images.add(image5);
        banner.setImages(images);
        banner.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xq_back:
                finish();
                break;
        }
    }
}