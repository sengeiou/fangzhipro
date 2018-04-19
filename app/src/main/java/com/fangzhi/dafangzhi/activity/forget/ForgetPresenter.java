package com.fangzhi.dafangzhi.activity.forget;

import com.fangzhi.dafangzhi.base.BaseBean;
import com.fangzhi.dafangzhi.network.ErrorCode;
import com.fangzhi.dafangzhi.network.MySubscriber;

/**
 * Created by smacr on 2016/8/30.
 */
public class ForgetPresenter extends ForgetContract.Presenter {

    @Override
    public void onStart() {

    }

    @Override
    void gain() {
        mRxManager.add(mModel.gainverify(mView.GetPhone(), mView.GetMD5())
                .subscribe(new MySubscriber<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (baseBean.getError_code().equals(ErrorCode.SUCCEED)){
                            mView.GetSucceed(baseBean.getMsg());
                        }else {
                            mView.GetFailed(baseBean.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //mView.loginFailed("服务器连接失败");
                        mView.onError("服务器连接失败");
                    }
                }));
    }

    @Override
    void queryVerCode() {
        mRxManager.add(mModel.queryVerCode(mView.GetPhone(), mView.GetVerification(),"1")
                .subscribe(new MySubscriber<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (baseBean.getError_code().equals(ErrorCode.SUCCEED)){
                            mView.VerifySucceed(baseBean.getMsg());
                        }else {
                            mView.VerifyFailed(baseBean.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //mView.loginFailed("服务器连接失败");
                        mView.onError("服务器连接失败");
                    }
                }));
    }

}
