package com.fangzhi.dafangzhi.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.activity.main.bean.PremiseList;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by smacr on 2017/3/17.
 */

public class MainViewHolder extends BaseViewHolder<PremiseList> {

    TextView name;
    TextView tv_count;
    ImageView ImageView;

    public MainViewHolder(ViewGroup parent) {
        super(parent, R.layout.main_item);
        AutoUtils.autoSize(itemView);
        name = $(R.id.name);
        tv_count = $(R.id.tv_count);
        ImageView = $(R.id.iv_image);
    }

    @Override
    public void setData(PremiseList data) {
        super.setData(data);
        tv_count.setText("户型数:"+data.getCount());
        name.setText(data.getPre_cname());
        Glide.with(getContext())
                .load(data.getPre_img())
                .placeholder(R.mipmap.zanwei)
                .crossFade()
                .into(ImageView);
        //ImageView.setImageResource(R.mipmap.bg);
    }
}
