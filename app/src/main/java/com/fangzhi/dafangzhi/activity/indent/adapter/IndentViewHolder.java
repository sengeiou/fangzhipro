package com.fangzhi.dafangzhi.activity.indent.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import com.fangzhi.dafangzhi.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by smacr on 2016/9/1.
 */
public class IndentViewHolder extends BaseViewHolder<String> {
    TextView tvName;
    TextView tvxia;

    public IndentViewHolder(ViewGroup parent) {
        super(parent, R.layout.indent_item);
        AutoUtils.autoSize(itemView);
    }

    @Override
    public void setData(String data) {
/*        Glide.with(getContext())
                .load(data.getHouse_img())
                .placeholder(R.mipmap.zanwei)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(imageView);*/
    }
}
