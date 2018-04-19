package com.fangzhi.dafangzhi.activity.room;

import com.fangzhi.dafangzhi.activity.room.bean.RestRoom;
import com.fangzhi.dafangzhi.activity.room.bean.RoomBean;
import com.fangzhi.dafangzhi.activity.room.bean.Sceneorderback;
import com.fangzhi.dafangzhi.base.BaseBean;
import com.fangzhi.dafangzhi.network.Network;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by smacr on 2017/3/17.
 */

public class RoomModel implements RoomContract.Model {


    @Override
    public Observable<RoomBean> getMaterialList(String design_id, String is_collected,String user_id) {
        return Network.getApiService().FzAction_getScene(design_id, is_collected,user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<RestRoom> getOtherScene(String design_id, String hot_id, String user_id) {
        return Network.getApiService().FzAction_getOtherScene(design_id, user_id, hot_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseBean> collectDesign(String scene_id, String plainText,String user_id,String scene_img) {
        return Network.getApiService().FzAction_collectDesign(scene_id, plainText,user_id,scene_img)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Sceneorderback> getmergePicture( String json) {
        return Network.getApiService().getMergePicture(json)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
