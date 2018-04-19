package com.fangzhi.dafangzhi.fragment.collect;

import com.fangzhi.dafangzhi.fragment.collect.bean.CollectBean;
import com.fangzhi.dafangzhi.network.Network;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by smacr on 2017/3/17.
 */

public class CollectModel implements CollectContract.Model {



    @Override
    public Observable<CollectBean> gainlist(String user_id) {
        return Network.getApiService().GETCOLLECTLIST(user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
