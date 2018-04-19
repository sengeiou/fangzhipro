package com.fangzhi.dafangzhi.activity.login;

import com.fangzhi.dafangzhi.activity.login.bean.LoginBean;
import com.fangzhi.dafangzhi.network.Network;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by smacr on 2017/3/17.
 */

public class LoginModel implements LoginContract.Model {


    @Override
    public Observable<LoginBean> login(String phone, String password) {
        return Network.getApiService().FzAction_LOGIN(phone,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
