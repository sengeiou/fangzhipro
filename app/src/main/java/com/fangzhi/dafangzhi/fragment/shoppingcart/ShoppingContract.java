package com.fangzhi.dafangzhi.fragment.shoppingcart;

import com.fangzhi.dafangzhi.base.BaseModel;
import com.fangzhi.dafangzhi.base.BasePresenter;
import com.fangzhi.dafangzhi.base.BaseView;
import com.fangzhi.dafangzhi.fragment.shoppingcart.bean.ShopBean;

import rx.Observable;

/**
 * Created by smacr on 2016/8/30.
 */
public interface ShoppingContract {
    interface Model extends BaseModel {
        Observable<ShopBean> gainlist(String user_id);
    }

    interface View extends BaseView {
        String getUserId();

        //获取
        void gainSucceed(ShopBean collectBean);
        void gainFailed(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        abstract void gain();
    }
}
