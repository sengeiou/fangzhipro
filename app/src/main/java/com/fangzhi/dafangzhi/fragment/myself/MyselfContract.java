package com.fangzhi.dafangzhi.fragment.myself;

import com.fangzhi.dafangzhi.base.BaseModel;
import com.fangzhi.dafangzhi.base.BasePresenter;
import com.fangzhi.dafangzhi.base.BaseView;

/**
 * Created by smacr on 2016/8/30.
 */
public interface MyselfContract {
    interface Model extends BaseModel {
    }

    interface View extends BaseView {

    }

    abstract class Presenter extends BasePresenter<Model, View> {

    }
}
