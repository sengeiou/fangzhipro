package com.fangzhi.dafangzhi.activity.main;

import com.fangzhi.dafangzhi.activity.main.bean.MainBean;
import com.fangzhi.dafangzhi.base.BaseModel;
import com.fangzhi.dafangzhi.base.BasePresenter;
import com.fangzhi.dafangzhi.base.BaseView;

import rx.Observable;

/**
 * Created by smacr on 2016/8/30.
 */
public interface MainContract {
    interface Model extends BaseModel {
        Observable<MainBean> gain(String areaid, int pageNO, int pageSize, String user_id);
        Observable<MainBean> gainnew(String areaid, int pageNO, int pageSize, String user_id);
        Observable<MainBean> gainSEARCHPREMISE(String premiseName, int pageNO, int pageSize, String areaid);
        Observable<MainBean> GETCOUNTYPRIMISE(String countyID);
    }

    interface View extends BaseView {
        String getDeviceId();
        int getPageSize();
        int getCurrentPage();
        String getUserId();
        String getAreaCode();
        String getcounty();
        String getpremiseName();

        //获取
        void gainSucceed(MainBean mainBean);
        void gainFailed(String msg);
        //二次获取
        void gainnewSucceed(MainBean mainBean);
        void gainnewFailed(String msg);
        //搜索
        void searchpremiseSucceed(MainBean mainBean);
        void searchpremisefailed(String msg);
        //区县
        void countyprimiseSucceed(MainBean mainBean);
        void countyprimiseFailed(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        abstract void gain();
        abstract void gainnew();
        abstract void gainSEARCHPREMISE();
        abstract void GETCOUNTYPRIMISE();

    }
}
