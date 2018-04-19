package com.fangzhi.dafangzhi.fragment.collect.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.base.RxBus;
import com.fangzhi.dafangzhi.fragment.collect.bean.GoodsList;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by smacr on 2016/9/1.
 */
public class CollectComViewHolder extends BaseViewHolder<GoodsList> {
    TextView name;
    TextView money;
    ImageView img;
    ImageView selectimg;
    AutoLinearLayout select;

    public CollectComViewHolder(ViewGroup parent) {
        super(parent, R.layout.collect_com_item);
        AutoUtils.autoSize(itemView);
        name = $(R.id.name);
        money = $(R.id.money);
        img = $(R.id.img);
        selectimg = $(R.id.selectimg);
        select = $(R.id.select);
    }

    @Override
    public void setData(final GoodsList data) {
        name.setText(data.getGoods_name());
        money.setText(data.getBasic_price() + "元");
        Glide.with(getContext())
                .load(data.getGoods_icon())
                .placeholder(R.mipmap.zanwei)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(img);
        if (data.isselect()) {
            selectimg.setImageResource(R.mipmap.red);
        } else {
            selectimg.setImageResource(R.mipmap.gray);
        }

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.setIsselect(!data.isselect());
                if (data.isselect()) {
                    selectimg.setImageResource(R.mipmap.red);
                } else {
                    selectimg.setImageResource(R.mipmap.gray);
                }
                RxBus.$().post("CollectFragment", "1");
            }
        });

    }
}
