package com.fangzhi.dafangzhi.activity.hotspot.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.fangzhi.dafangzhi.activity.hotspot.bean.StyleList;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by smacr on 2016/9/1.
 */
public class HotspotAdapter extends RecyclerArrayAdapter<StyleList> {
    public HotspotAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotspotViewHolder(parent);
    }
}
