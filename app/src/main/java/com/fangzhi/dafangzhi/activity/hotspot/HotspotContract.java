package com.fangzhi.dafangzhi.activity.hotspot;

import com.fangzhi.dafangzhi.activity.hotspot.bean.HotspotBean;
import com.fangzhi.dafangzhi.base.BaseModel;
import com.fangzhi.dafangzhi.base.BasePresenter;
import com.fangzhi.dafangzhi.base.BaseView;

import rx.Observable;

/**
 * Created by smacr on 2016/8/30.
 */
public interface HotspotContract {
    interface Model extends BaseModel {
        Observable<HotspotBean> gain(String house_id, String user_id);
    }

    interface View extends BaseView {
        String gethouse_id();
        String getuser_id();
        //获取
        void gainSucceed(HotspotBean hotspotBean);
        void gainFailed(String msg);

    }

    abstract class Presenter extends BasePresenter<Model, View> {
        abstract void gain();
    }
}
