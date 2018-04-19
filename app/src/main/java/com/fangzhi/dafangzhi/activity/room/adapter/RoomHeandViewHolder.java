package com.fangzhi.dafangzhi.activity.room.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.activity.room.bean.StairTypeList;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by smacr on 2016/9/1.
 */
public class RoomHeandViewHolder extends BaseViewHolder<StairTypeList> {
    TextView view_cover;

    public RoomHeandViewHolder(ViewGroup parent) {
        super(parent, R.layout.roomhead_item);
        view_cover = $(R.id.view_cover);
    }

    @Override
    public void setData(StairTypeList data) {
        view_cover.setText(data.getType_name());
        if (data.isture()){
            view_cover.setBackgroundResource(R.drawable.text_textbg);
            view_cover.setTextColor(getContext().getResources().getColor(R.color.white));
        }else {
            view_cover.setBackgroundResource(R.drawable.iv_roomyin_not);
            view_cover.setTextColor(getContext().getResources().getColor(R.color.login_text));
        }

    }
}
