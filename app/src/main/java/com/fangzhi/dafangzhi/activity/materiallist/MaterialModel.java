package com.fangzhi.dafangzhi.activity.materiallist;

import com.fangzhi.dafangzhi.activity.materiallist.bean.MaterialBean;
import com.fangzhi.dafangzhi.network.Network;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by smacr on 2017/3/17.
 */

public class MaterialModel implements MaterialContract.Model {


    @Override
    public Observable<MaterialBean> gainMater(String design_id, String user_id) {
        return Network.getApiService().GETMATERIALLIST(design_id,user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
