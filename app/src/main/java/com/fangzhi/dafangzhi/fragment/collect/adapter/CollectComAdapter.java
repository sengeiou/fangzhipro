package com.fangzhi.dafangzhi.fragment.collect.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.fangzhi.dafangzhi.fragment.collect.bean.GoodsList;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by smacr on 2016/9/1.
 */
public class CollectComAdapter extends RecyclerArrayAdapter<GoodsList> {
    public CollectComAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CollectComViewHolder(parent);
    }
}
