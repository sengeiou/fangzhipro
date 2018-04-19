package com.fangzhi.dafangzhi.activity.materiallist;

import com.fangzhi.dafangzhi.activity.materiallist.bean.MaterialBean;
import com.fangzhi.dafangzhi.base.BaseModel;
import com.fangzhi.dafangzhi.base.BasePresenter;
import com.fangzhi.dafangzhi.base.BaseView;

import rx.Observable;

/**
 * Created by smacr on 2016/8/30.
 */
public interface MaterialContract {
    interface Model extends BaseModel {
        Observable<MaterialBean> gainMater(String design_id, String user_id);
    }

    interface View extends BaseView {
        String getdesign_id();
        String getuser_id();
        //登录
        void gainMaterSucceed(MaterialBean loginBean);
        void gainMaterFailed(String msg);

    }

    abstract class Presenter extends BasePresenter<Model, View> {
        abstract void gainMater();
    }
}
