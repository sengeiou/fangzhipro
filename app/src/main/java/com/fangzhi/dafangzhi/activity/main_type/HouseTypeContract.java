package com.fangzhi.dafangzhi.activity.main_type;


import com.fangzhi.dafangzhi.activity.main_type.bean.HouseTypes;
import com.fangzhi.dafangzhi.base.BaseModel;
import com.fangzhi.dafangzhi.base.BasePresenter;
import com.fangzhi.dafangzhi.base.BaseView;

import java.util.List;

import rx.Observable;

/**
 * Created by smacr on 2016/9/21.
 */
public interface HouseTypeContract {
    interface Model extends BaseModel {
        Observable<HouseTypes> getHouseTypes( String houseId);
    }

    interface View extends BaseView {
        String getToken();
        String getHouseId();

        void showHouseTypes(List<HouseTypes.HouseType> list);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        abstract public void getHouseTypes();
    }
}
