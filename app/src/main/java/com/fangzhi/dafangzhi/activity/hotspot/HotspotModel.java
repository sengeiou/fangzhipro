package com.fangzhi.dafangzhi.activity.hotspot;

import com.fangzhi.dafangzhi.activity.hotspot.bean.HotspotBean;
import com.fangzhi.dafangzhi.network.Network;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by smacr on 2017/3/17.
 */

public class HotspotModel implements HotspotContract.Model {

    @Override
    public Observable<HotspotBean> gain(String house_id, String user_id) {
        return Network.getApiService().FzAction_getHouseDesign(house_id,user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
