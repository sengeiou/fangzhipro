package com.fangzhi.dafangzhi.activity.room;

import com.fangzhi.dafangzhi.activity.room.bean.RestRoom;
import com.fangzhi.dafangzhi.activity.room.bean.RoomBean;
import com.fangzhi.dafangzhi.activity.room.bean.Sceneorderback;
import com.fangzhi.dafangzhi.base.BaseBean;
import com.fangzhi.dafangzhi.base.BaseModel;
import com.fangzhi.dafangzhi.base.BasePresenter;
import com.fangzhi.dafangzhi.base.BaseView;

import rx.Observable;

/**
 * Created by smacr on 2016/8/30.
 */
public interface RoomContract {
    interface Model extends BaseModel {
        Observable<RoomBean> getMaterialList(String design_id, String is_collected,String user_id);
        Observable<RestRoom> getOtherScene(String design_id, String hot_id ,String user_id);
        Observable<BaseBean> collectDesign(String scene_id, String plainText,String user_id,String scene_img);
        Observable<Sceneorderback> getmergePicture(String json);
    }

    interface View extends BaseView {

        String getdesign_id();

        String getuser_id();

        String gethot_id();

        String getscene_img();

        String getis_collected();

        String getjsonString();

        String getPicturejsonString();

        String getscene_id();

        //获取
        void getMaterialListSucceed(RoomBean baseBean);
        void getMaterialListFailed(String msg);

        //获取相关房间
        void getOtherSceneSucceed(RestRoom restRoom);
        void getOtherSceneFailed(String msg);

        //收藏
        void collectSucceed(BaseBean baseBean);
        void collectFailed(String msg);

        //高清大图
        void showbackSucceed(String sceneorderback);
        void showbackFailed(String msg);

    }

    abstract class Presenter extends BasePresenter<Model, View> {
        abstract void gainMaterialList();
        abstract void getOtherScene();
        abstract void collectDesign();
        abstract void getmergePicture();
    }
}
