package com.fangzhi.dafangzhi.activity.materiallist.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.fangzhi.dafangzhi.activity.materiallist.bean.SceneList;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by smacr on 2016/9/1.
 */
public class MaterupAdapter extends RecyclerArrayAdapter<SceneList> {
    public MaterupAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MaterupViewHolder(parent);
    }
}
