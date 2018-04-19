package com.fangzhi.dafangzhi.fragment.shoppingcart;

import com.fangzhi.dafangzhi.fragment.shoppingcart.bean.ShopBean;
import com.fangzhi.dafangzhi.network.Network;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by smacr on 2017/3/17.
 */

public class ShoppingModel implements ShoppingContract.Model {


    @Override
    public Observable<ShopBean> gainlist(String user_id) {
        return Network.getApiService().GETSHOPCARTLIST(user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
