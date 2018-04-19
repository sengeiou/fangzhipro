package com.fangzhi.dafangzhi.fragment.shoppingcart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.activity.home.HomeActivity;
import com.fangzhi.dafangzhi.base.BaseFragment;
import com.fangzhi.dafangzhi.config.SpKey;
import com.fangzhi.dafangzhi.fragment.shoppingcart.adapter.ShopingAdapter;
import com.fangzhi.dafangzhi.fragment.shoppingcart.bean.ShopBean;
import com.fangzhi.dafangzhi.fragment.shoppingcart.bean.ShopCartList;
import com.fangzhi.dafangzhi.utils.SPUtils;
import com.fangzhi.dafangzhi.utils.T;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by smacr on 2017/4/6.
 */

public class ShoppingFragment extends BaseFragment<ShoppingPresenter, ShoppingModel> implements ShoppingContract.View {

    View view;

    @Bind(R.id.shoptyrecycler)
    EasyRecyclerView shoptyrecycler;

    @Bind(R.id.none)
    AutoLinearLayout none;

    ShopingAdapter shopingAdapter;

    List<String> list = new ArrayList<>();
    ShopBean shopBean = null;
    List<ShopCartList> shopCartList = new ArrayList<>();

    private static ShoppingFragment shoppingFragment = null;

    public static ShoppingFragment getMainFragment() {

        if (shoppingFragment == null) {
            shoppingFragment = new ShoppingFragment();
        }
        return shoppingFragment;
    }

    @Override
    public void tokenInvalid(String msg) {

    }

    @Override
    public void onError(String msg) {
        ((HomeActivity) getActivity()).showErrorDialog(msg);
        shoptyrecycler.setRefreshing(false);
    }


    @Override
    public View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shoppingcart_activity, container, false);
        return view;
    }

    @Override
    public void init() {
        shoptyrecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        shopingAdapter = new ShopingAdapter(getActivity());
        shoptyrecycler.setAdapter(shopingAdapter);
        shoptyrecycler.setRefreshing(true);
        mPresenter.gain();
    }

    @Override
    public String getUserId() {
        return SPUtils.getString(getActivity(), SpKey.USER_ID, "");
    }

    @Override
    public void gainSucceed(ShopBean shop) {
        shopBean = shop;
        //shopCartList.clear();
        //shopingAdapter.clear();
        T.showString(getActivity(), "请求成功");
        shoptyrecycler.setRefreshing(false);
        shopCartList.addAll(shopBean.getShopCartList());
        shopingAdapter.addAll(shopCartList);
        if (shopCartList!=null&&shopCartList.size()!=0){
            shoptyrecycler.setVisibility(View.VISIBLE);
            none.setVisibility(View.GONE);
        }else {
            shoptyrecycler.setVisibility(View.GONE);
            none.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void gainFailed(String msg) {
        T.showString(getActivity(), "请求失败");
        shoptyrecycler.setRefreshing(false);
    }

}
