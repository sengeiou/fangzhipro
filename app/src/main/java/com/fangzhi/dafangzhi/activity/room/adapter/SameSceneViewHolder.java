package com.fangzhi.dafangzhi.activity.room.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.activity.room.bean.SceneList;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by smacr on 2016/9/1.
 */
public class SameSceneViewHolder extends BaseViewHolder<SceneList> {
    TextView textView;
    ImageView imageView;


    public SameSceneViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_room_scene);
        AutoUtils.autoSize(itemView);
        textView = $(R.id.tv_name);
        imageView = $(R.id.iv_scene);

    }

    @Override
    public void setData(final SceneList data) {
        textView.setText(data.getScene_name());
        if(data.isSelected()){
           // imageView.setBackground(getContext().getResources().getDrawable(R.mipmap.sample_scene_bg_p));
            imageView.setBackgroundResource(R.mipmap.sample_scene_bg_p);
        }else{
            //imageView.setBackground(getContext().getResources().getDrawable(R.mipmap.sample_scene_bg_n));
            imageView.setBackgroundResource(R.mipmap.sample_scene_bg_n);
        }
        Glide.with(getContext())
                .load(data.getScene_img())
                .placeholder(R.mipmap.zanwei)
                .crossFade()
                .into(imageView);

    }

}
