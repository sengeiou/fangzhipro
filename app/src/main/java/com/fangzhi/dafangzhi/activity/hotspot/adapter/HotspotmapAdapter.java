package com.fangzhi.dafangzhi.activity.hotspot.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.fangzhi.dafangzhi.activity.hotspot.bean.DesignList;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by smacr on 2016/9/1.
 */
public class HotspotmapAdapter extends RecyclerArrayAdapter<DesignList> {
    public HotspotmapAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotspotmapViewHolder(parent);
    }
}
