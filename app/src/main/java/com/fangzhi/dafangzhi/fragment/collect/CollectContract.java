package com.fangzhi.dafangzhi.fragment.collect;

import com.fangzhi.dafangzhi.base.BaseModel;
import com.fangzhi.dafangzhi.base.BasePresenter;
import com.fangzhi.dafangzhi.base.BaseView;
import com.fangzhi.dafangzhi.fragment.collect.bean.CollectBean;

import rx.Observable;

/**
 * Created by smacr on 2016/8/30.
 */
public interface CollectContract {
    interface Model extends BaseModel {
        Observable<CollectBean> gainlist(String user_id);
    }

    interface View extends BaseView {
        String getUserId();

        //获取
        void gainSucceed(CollectBean collectBean);
        void gainFailed(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        abstract void gain();
    }
}
