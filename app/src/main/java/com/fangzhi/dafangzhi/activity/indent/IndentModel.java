package com.fangzhi.dafangzhi.activity.indent;

import com.fangzhi.dafangzhi.activity.forget.ForgetContract;
import com.fangzhi.dafangzhi.base.BaseBean;
import com.fangzhi.dafangzhi.network.Network;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by smacr on 2017/3/17.
 */

public class IndentModel implements ForgetContract.Model {

    @Override
    public Observable<BaseBean> queryVerCode(String phone, String code, String type) {
        return  Network.getApiService().queryVerCode(phone,code,type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseBean> gainverify(String PHONE, String MD5) {
        return  Network.getApiService().gainverify(PHONE,MD5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
