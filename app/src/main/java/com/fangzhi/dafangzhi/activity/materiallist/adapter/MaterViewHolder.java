package com.fangzhi.dafangzhi.activity.materiallist.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.activity.materiallist.bean.GoodsList;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by smacr on 2016/9/1.
 */
public class MaterViewHolder extends BaseViewHolder<GoodsList> {
    TextView name;
    TextView money;
    ImageView image;

    public MaterViewHolder(ViewGroup parent) {
        super(parent, R.layout.activity_material_item);
        AutoUtils.autoSize(itemView);
        name = $(R.id.name);
        money = $(R.id.money);
        image = $(R.id.image);
    }

    @Override
    public void setData(GoodsList data) {
        money.setText(data.getBasic_price()+"元");
        name.setText(data.getGoods_name());
        Glide.with(getContext())
                .load(data.getGoods_img())
                .placeholder(R.mipmap.zanwei)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(image);
    }
}
