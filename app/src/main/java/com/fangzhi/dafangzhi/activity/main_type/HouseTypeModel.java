package com.fangzhi.dafangzhi.activity.main_type;

import com.fangzhi.dafangzhi.activity.main_type.bean.HouseTypes;
import com.fangzhi.dafangzhi.network.Network;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by smacr on 2016/9/21.
 */
public class HouseTypeModel implements HouseTypeContract.Model {
    @Override
    public Observable<HouseTypes> getHouseTypes( String houseId) {
       //return NetWorkRequest.getHouseTypes(token,houseId);
        return Network.getApiService().FzAction_getHouseType(houseId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
