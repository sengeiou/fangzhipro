package com.fangzhi.dafangzhi.activity.room;

import com.fangzhi.dafangzhi.activity.room.bean.RestRoom;
import com.fangzhi.dafangzhi.activity.room.bean.RoomBean;
import com.fangzhi.dafangzhi.activity.room.bean.Sceneorderback;
import com.fangzhi.dafangzhi.base.BaseBean;
import com.fangzhi.dafangzhi.network.ErrorCode;
import com.fangzhi.dafangzhi.network.MySubscriber;

/**
 * Created by smacr on 2016/8/30.
 */
public class RoomPresenter extends RoomContract.Presenter {

    @Override
    public void onStart() {

    }

    @Override
    void gainMaterialList() {
        mRxManager.add(mModel.getMaterialList(mView.gethot_id(), mView.getis_collected(),mView.getuser_id())
                .subscribe(new MySubscriber<RoomBean>() {
                    @Override
                    public void onNext(RoomBean roomBean) {
                        if (roomBean.getError_code().equals(ErrorCode.SUCCEED)) {
                            mView.getMaterialListSucceed(roomBean);
                        } else {
                            mView.getMaterialListFailed(roomBean.getMsg());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onError("服务器连接失败");
                    }
                }));
    }

    @Override
    void getOtherScene() {
        mRxManager.add(mModel.getOtherScene(mView.getdesign_id(),mView.gethot_id(), mView.getuser_id())
                .subscribe(new MySubscriber<RestRoom>() {
                    @Override
                    public void onNext(RestRoom roomBean) {
                        if (roomBean.getError_code().equals(ErrorCode.SUCCEED)) {
                            mView.getOtherSceneSucceed(roomBean);
                        } else {
                            mView.getOtherSceneFailed(roomBean.getMsg());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onError("服务器连接失败");
                    }
                }));
    }

    @Override
    void collectDesign() {
        mRxManager.add(mModel.collectDesign(mView.getscene_id(),mView.getjsonString(), mView.getuser_id(),mView.getscene_img())
                .subscribe(new MySubscriber<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (baseBean.getError_code().equals(ErrorCode.SUCCEED)) {
                            mView.collectSucceed(baseBean);
                        } else {
                            mView.collectFailed(baseBean.getMsg());
                        }

                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.onError("服务器连接失败");
                    }
                }));
    }

    @Override
    void getmergePicture() {
        mRxManager.add(mModel.getmergePicture( mView.getPicturejsonString()).
                subscribe(new MySubscriber<Sceneorderback>() {
                    @Override
                    public void onNext(Sceneorderback sceneorderback) {
                        super.onNext(sceneorderback);
                        if (ErrorCode.TOKEN_INVALID.equals(sceneorderback.getError_code())) {
                            mView.tokenInvalid(sceneorderback.getMsg());
                        } else if (ErrorCode.SERVER_EXCEPTION.equals(sceneorderback.getError_code())) {
                            mView.onError(sceneorderback.getMsg());
                        } else if (ErrorCode.SUCCEED.equals(sceneorderback.getError_code())) {
                            mView.showbackSucceed(sceneorderback.getUrl());
                        } else {
                            mView.showbackSucceed(sceneorderback.getUrl());
                        }
                        //Log.i("TT","收到了不知道是什么"+sceneorderback.getUrl());
                        //mView.showSceneorderback(sceneorderback.getUrl());
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onError("连接服务器异常");
                    }
                }));
    }
}
