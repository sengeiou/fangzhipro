package com.fangzhi.dafangzhi.fragment.shoppingcart.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.fragment.shoppingcart.bean.ShopCartList;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by smacr on 2016/9/1.
 */
public class ShopingViewHolder extends BaseViewHolder<ShopCartList> {
    TextView name;
    TextView money;
    TextView standard;
    ImageView img;
    ImageView selectimg;
    AutoLinearLayout select;

    public ShopingViewHolder(ViewGroup parent) {
        super(parent, R.layout.shopping_item);
        AutoUtils.autoSize(itemView);
        name = $(R.id.name);
        money = $(R.id.money);
        img = $(R.id.img);
        selectimg = $(R.id.selectimg);
        select = $(R.id.select);
        standard = $(R.id.standard);
    }

    @Override
    public void setData(final ShopCartList data) {
        name.setText(data.getGoods_name()+"");
        money.setText("￥"+data.getPrice()+"");
        standard.setText(data.getUniqueInfo()+"");
        Glide.with(getContext())
                .load(data.getGoods_icon())
                .placeholder(R.mipmap.zanwei)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(img);
        if (data.isSelect()) {
            selectimg.setImageResource(R.mipmap.red);
        } else {
            selectimg.setImageResource(R.mipmap.gray);
        }

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.setSelect(!data.isSelect());
                if (data.isSelect()) {
                    selectimg.setImageResource(R.mipmap.red);
                } else {
                    selectimg.setImageResource(R.mipmap.gray);
                }
            }
        });

/*        Glide.with(getContext())
                .load(data.getHouse_img())
                .placeholder(R.mipmap.zanwei)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(imageView);*/
    }
}
