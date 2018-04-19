package com.fangzhi.dafangzhi.fragment.collect;

import com.fangzhi.dafangzhi.fragment.collect.bean.CollectBean;
import com.fangzhi.dafangzhi.network.ErrorCode;
import com.fangzhi.dafangzhi.network.MySubscriber;

/**
 * Created by smacr on 2016/8/30.
 */
public class CollectPresenter extends CollectContract.Presenter {

    @Override
    public void onStart() {

    }

    @Override
    void gain() {
        String userId = mView.getUserId();
        mRxManager.add(mModel.gainlist( userId).subscribe(new MySubscriber<CollectBean>() {
            @Override
            public void onNext(CollectBean collectBean) {
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
