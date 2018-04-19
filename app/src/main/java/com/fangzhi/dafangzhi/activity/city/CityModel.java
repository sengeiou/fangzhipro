package com.fangzhi.dafangzhi.activity.city;


import com.fangzhi.dafangzhi.activity.city.bean.Area;
import com.fangzhi.dafangzhi.network.Network;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by smacr on 2016/9/20.
 */
public class CityModel implements CityContract.Model {
    @Override
    public Observable<Area> getCities(String token) {
       // return NetWorkRequest.getCities(token);
        return Network.getApiService().FzAction_GETAREA()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
