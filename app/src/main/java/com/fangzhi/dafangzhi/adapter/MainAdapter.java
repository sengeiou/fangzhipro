package com.fangzhi.dafangzhi.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.fangzhi.dafangzhi.activity.main.bean.PremiseList;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by smacr on 2017/3/17.
 */

public class MainAdapter extends RecyclerArrayAdapter<PremiseList> {

    public MainAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(parent);
    }
}
