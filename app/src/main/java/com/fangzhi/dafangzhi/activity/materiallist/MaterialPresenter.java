package com.fangzhi.dafangzhi.activity.materiallist;

import com.fangzhi.dafangzhi.activity.materiallist.bean.MaterialBean;
import com.fangzhi.dafangzhi.network.ErrorCode;
import com.fangzhi.dafangzhi.network.MySubscriber;

/**
 * Created by smacr on 2016/8/30.
 */
public class MaterialPresenter extends MaterialContract.Presenter {

    @Override
    public void onStart() {

    }


    @Override
    void gainMater() {
        mRxManager.add(mModel.gainMater(mView.getdesign_id(), mView.getuser_id())
                .subscribe(new MySubscriber<MaterialBean>() {
                    @Override
                    public void onNext(MaterialBean materialBean) {
                        if (materialBean.getError_code().equals(ErrorCode.SUCCEED)) {
                            mView.gainMaterSucceed(materialBean);
                        } else {
                            mView.gainMaterFailed(materialBean.getMsg());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onError("服务器连接失败");
                    }
                }));
    }
}
