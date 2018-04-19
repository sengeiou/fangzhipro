package com.fangzhi.dafangzhi.activity.login;

import com.fangzhi.dafangzhi.activity.login.bean.LoginBean;
import com.fangzhi.dafangzhi.network.ErrorCode;
import com.fangzhi.dafangzhi.network.MySubscriber;

/**
 * Created by smacr on 2016/8/30.
 */
public class LoginPresenter extends LoginContract.Presenter {

    @Override
    public void onStart() {

    }

    @Override
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
    }


}
