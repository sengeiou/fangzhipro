package com.fangzhi.dafangzhi.fragment.shoppingcart.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.fangzhi.dafangzhi.fragment.shoppingcart.bean.ShopCartList;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by smacr on 2016/9/1.
 */
public class ShopingAdapter extends RecyclerArrayAdapter<ShopCartList> {
    public ShopingAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShopingViewHolder(parent);
    }
}
