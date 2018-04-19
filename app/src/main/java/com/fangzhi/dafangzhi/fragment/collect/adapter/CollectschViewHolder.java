package com.fangzhi.dafangzhi.fragment.collect.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.base.RxBus;
import com.fangzhi.dafangzhi.fragment.collect.bean.SceneList;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by smacr on 2016/9/1.
 */
public class CollectschViewHolder extends BaseViewHolder<SceneList> {

    TextView name;
    TextView site;
    ImageView img;
    ImageView selectimg;
    AutoLinearLayout select;

    public CollectschViewHolder(ViewGroup parent) {
        super(parent, R.layout.collect_scheme_item);
        AutoUtils.autoSize(itemView);
        name = $(R.id.name);
        site = $(R.id.site);
        img = $(R.id.img);
        selectimg = $(R.id.selectimg);
        select = $(R.id.select);
    }

    @Override
    public void setData(final SceneList data) {
        name.setText(data.getDesign_name());
        site.setText(data.getCity_name() + data.getProvince_name() + data.getCounty_name());
        Glide.with(getContext())
                .load(data.getScene_img())
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
