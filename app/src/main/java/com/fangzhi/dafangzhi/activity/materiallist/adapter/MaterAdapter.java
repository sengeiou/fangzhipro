package com.fangzhi.dafangzhi.activity.materiallist.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.fangzhi.dafangzhi.activity.materiallist.bean.GoodsList;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by smacr on 2016/9/1.
 */
public class MaterAdapter extends RecyclerArrayAdapter<GoodsList> {
    public MaterAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MaterViewHolder(parent);
    }
}
