package com.fangzhi.dafangzhi.activity.forget;

import com.fangzhi.dafangzhi.base.BaseBean;
import com.fangzhi.dafangzhi.base.BaseModel;
import com.fangzhi.dafangzhi.base.BasePresenter;
import com.fangzhi.dafangzhi.base.BaseView;

import rx.Observable;

/**
 * Created by smacr on 2016/8/30.
 */
public interface ForgetContract {
    interface Model extends BaseModel {
        Observable<BaseBean> queryVerCode(String phone, String code, String type);
        Observable<BaseBean> gainverify(String PHONE, String MD5);
    }

    interface View extends BaseView {
        String GetPhone();
        String GetVerification();
        String GetMD5();
        //获取验证码
        void GetSucceed(String msg);
        void GetFailed(String msg);
        //验证码
        void VerifySucceed(String msg);
        void VerifyFailed(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        abstract void gain();
        abstract void queryVerCode();

    }
}
