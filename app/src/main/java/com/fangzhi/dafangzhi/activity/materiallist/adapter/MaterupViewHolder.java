package com.fangzhi.dafangzhi.activity.materiallist.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.activity.materiallist.bean.SceneList;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by smacr on 2016/9/1.
 */
public class MaterupViewHolder extends BaseViewHolder<SceneList> {
    TextView tvName;
    TextView tvxia;

    public MaterupViewHolder(ViewGroup parent) {
        super(parent, R.layout.hotspot_item);
        AutoUtils.autoSize(itemView);
        tvName = $(R.id.name);
        tvxia = $(R.id.xia);
    }

    @Override
    public void setData(SceneList data) {
        tvName.setText(data.getCode_desc());
        if (data.isture()) {
            tvName.setTextColor(getContext().getResources().getColor(R.color.login_text));
            tvxia.setVisibility(View.VISIBLE);
        } else {
            tvName.setTextColor(getContext().getResources().getColor(R.color.login_tint));
            tvxia.setVisibility(View.GONE);
        }
/*        Glide.with(getContext())
                .load(data.getHouse_img())
                .placeholder(R.mipmap.zanwei)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(imageView);*/
    }
}
