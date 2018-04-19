package com.fangzhi.dafangzhi.activity.room.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.activity.room.bean.PartList;
import com.fangzhi.dafangzhi.config.GlideRoundTransform;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by smacr on 2016/9/1.
 */
public class RoomViewHolder extends BaseViewHolder<PartList> {
    TextView view_cover;
    ImageView iv_product;

    public RoomViewHolder(ViewGroup parent) {
        super(parent, R.layout.room_item);
        view_cover = $(R.id.view_cover);
        iv_product = $(R.id.iv_product);
    }

    @Override
    public void setData(PartList data) {
        view_cover.setText(data.getPart_name());
        if (data.isture()) {
            view_cover.setBackgroundResource(R.drawable.iv_cover_green);
        } else {
            view_cover.setBackgroundResource(R.drawable.iv_room_not);
        }
        Glide.with(getContext())
                .load(data.getPart_img_short())
                .placeholder(R.mipmap.zanwei)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideRoundTransform(getContext(), 10))
                .crossFade()
                .into(iv_product);
    }
}
