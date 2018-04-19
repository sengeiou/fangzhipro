package com.fangzhi.dafangzhi.activity.hotspot;

import com.fangzhi.dafangzhi.activity.hotspot.bean.HotspotBean;
import com.fangzhi.dafangzhi.network.ErrorCode;
import com.fangzhi.dafangzhi.network.MySubscriber;

/**
 * Created by smacr on 2016/8/30.
 */
public class HotspotPresenter extends HotspotContract.Presenter {

    @Override
    public void onStart() {

    }

 /*   @Override
    public void login() {
        mRxManager.add(mModel.login(mView.GetPhone(), mView.GetPassword())
                .subscribe(new MySubscriber<LoginBean>() {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean.getError_code().equals(ErrorCode.SUCCEED)){
                                mView.LoginSucceed(loginBean);
                        }else {
                            mView.LoginFailed(loginBean.getMsg());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onError("服务器连接失败");
                    }
                }));
    }*/


    @Override
    void gain() {
        mRxManager.add(mModel.gain(mView.gethouse_id(), mView.getuser_id())
                .subscribe(new MySubscriber<HotspotBean>() {
                    @Override
                    public void onNext(HotspotBean hotspotBean) {
                        if (hotspotBean.getError_code().equals(ErrorCode.SUCCEED)){
                            mView.gainSucceed(hotspotBean);
                        }else {
                            mView.gainFailed(hotspotBean.getMsg());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onError("服务器连接失败");
                    }
                }));
    }

}
