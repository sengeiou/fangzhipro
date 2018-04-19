package com.fangzhi.dafangzhi.fragment.shoppingcart;

import com.fangzhi.dafangzhi.fragment.shoppingcart.bean.ShopBean;
import com.fangzhi.dafangzhi.network.ErrorCode;
import com.fangzhi.dafangzhi.network.MySubscriber;

/**
 * Created by smacr on 2016/8/30.
 */
public  class ShoppingPresenter extends ShoppingContract.Presenter {

    @Override
    public void onStart() {

    }


    @Override
    void gain() {
        String userId = mView.getUserId();
        mRxManager.add(mModel.gainlist( userId).subscribe(new MySubscriber<ShopBean>() {
            @Override
            public void onNext(ShopBean collectBean) {
                if (collectBean.getError_code().equals(ErrorCode.SUCCEED)) {
                    mView.gainSucceed(collectBean);
                } else {
                    mView.gainFailed(collectBean.getMsg());
                }
            }
            @Override
            public void onError(Throwable e) {
                mView.onError("服务器连接失败");
            }
        }));
    }
}
