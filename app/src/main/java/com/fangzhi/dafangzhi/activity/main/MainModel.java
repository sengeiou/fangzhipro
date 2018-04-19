package com.fangzhi.dafangzhi.activity.main;

import com.fangzhi.dafangzhi.activity.main.bean.MainBean;
import com.fangzhi.dafangzhi.network.Network;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by smacr on 2017/3/17.
 */

public class MainModel implements MainContract.Model {

    @Override
    public Observable<MainBean> gain(String areaid, int pageNO, int pageSize, String user_id) {
        return Network.getApiService().FzAction_GETHOMEPAGE(areaid,pageNO,pageSize,user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    @Override
    public Observable<MainBean> gainnew(String areaid, int pageNO, int pageSize, String user_id) {
        return Network.getApiService().FzAction_GETHOMEPAGE(areaid,pageNO,pageSize,user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<MainBean> gainSEARCHPREMISE(String premiseName, int pageNO, int pageSize, String areaid) {
        return Network.getApiService().FzAction_SEARCHPREMISE(premiseName,pageNO,pageSize,areaid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<MainBean> GETCOUNTYPRIMISE(String countyID) {
        return Network.getApiService().FzAction_GETCOUNTYPRIMISE(countyID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
