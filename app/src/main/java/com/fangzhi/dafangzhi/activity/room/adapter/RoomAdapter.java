package com.fangzhi.dafangzhi.activity.room.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.fangzhi.dafangzhi.activity.room.bean.PartList;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by smacr on 2016/9/1.
 */
public class RoomAdapter extends RecyclerArrayAdapter<PartList> {
    public RoomAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RoomViewHolder(parent);
    }
}
