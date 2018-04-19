package com.fangzhi.dafangzhi.activity.city;


import com.fangzhi.dafangzhi.activity.city.bean.Area;
import com.fangzhi.dafangzhi.activity.city.bean.City;
import com.fangzhi.dafangzhi.base.BaseModel;
import com.fangzhi.dafangzhi.base.BasePresenter;
import com.fangzhi.dafangzhi.base.BaseView;

import java.util.List;

import rx.Observable;

/**
 * Created by smacr on 2016/9/20.
 */
public interface CityContract {
    interface Model extends BaseModel {
        Observable<Area> getCities(String token);
    }

    interface View extends BaseView {
        void setCities(List<City> list);
        String getToken();

    }

    abstract class Presenter extends BasePresenter<Model, View> {
        abstract void getCityList();

    }
}
