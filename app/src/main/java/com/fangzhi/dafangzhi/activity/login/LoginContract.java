package com.fangzhi.dafangzhi.activity.login;

import com.fangzhi.dafangzhi.activity.login.bean.LoginBean;
import com.fangzhi.dafangzhi.base.BaseModel;
import com.fangzhi.dafangzhi.base.BasePresenter;
import com.fangzhi.dafangzhi.base.BaseView;

import rx.Observable;

/**
 * Created by smacr on 2016/8/30.
 */
public interface LoginContract {
    interface Model extends BaseModel {
        Observable<LoginBean> login(String phone, String password);
    }

    interface View extends BaseView {
        String GetPassword();
        String GetPhone();
        String GetMD5();

        //登录
        void LoginSucceed(LoginBean loginBean);
        void LoginFailed(String msg);

    }

    abstract class Presenter extends BasePresenter<Model, View> {
        abstract void login();
    }
}
