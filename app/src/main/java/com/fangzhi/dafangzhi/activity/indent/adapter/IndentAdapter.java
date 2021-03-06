package com.fangzhi.dafangzhi.activity.indent.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by smacr on 2016/9/1.
 */
public class IndentAdapter extends RecyclerArrayAdapter<String> {
    public IndentAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new IndentViewHolder(parent);
    }
}
