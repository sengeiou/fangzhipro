package com.fangzhi.dafangzhi.activity.main;

import com.fangzhi.dafangzhi.activity.main.bean.MainBean;
import com.fangzhi.dafangzhi.network.ErrorCode;
import com.fangzhi.dafangzhi.network.MySubscriber;

/**
 * Created by smacr on 2016/8/30.
 */
public class MainPresenter extends MainContract.Presenter {

    @Override
    public void onStart() {

    }


    @Override
    void gain() {
        String userId = mView.getUserId();
        int pageSize = mView.getPageSize();
        String areaCode = mView.getAreaCode();
        int currentPage = mView.getCurrentPage();
        mRxManager.add(mModel.gain(areaCode, currentPage, pageSize, userId).subscribe(new MySubscriber<MainBean>() {
            @Override
            public void onNext(MainBean baseBean) {
                if (baseBean.getError_code().equals(ErrorCode.SUCCEED)) {
                    mView.gainSucceed(baseBean);
                } else {
                    mView.gainFailed(baseBean.getMsg());
                }
            }
            @Override
            public void onError(Throwable e) {
                mView.onError("服务器连接失败");
            }
        }));
    }
    @Override
    void gainnew() {
        String userId = mView.getUserId();
        int pageSize = mView.getPageSize();
        String areaCode = mView.getAreaCode();
        int currentPage = mView.getCurrentPage();
        mRxManager.add(mModel.gain(areaCode, currentPage, pageSize, userId).subscribe(new MySubscriber<MainBean>() {
            @Override
            public void onNext(MainBean baseBean) {
                if (baseBean.getError_code().equals(ErrorCode.SUCCEED)) {
                    mView.gainnewSucceed(baseBean);
                } else {
                    mView.gainnewFailed(baseBean.getMsg());
                }

            }

            @Override
            public void onError(Throwable e) {
                mView.onError("服务器连接失败");
            }
        }));
    }

    @Override
    void gainSEARCHPREMISE() {
        int pageSize = mView.getPageSize();
        String areaCode = mView.getAreaCode();
        int currentPage = mView.getCurrentPage();
        String getpremiseName = mView.getpremiseName();

        mRxManager.add(mModel.gainSEARCHPREMISE(getpremiseName, currentPage, pageSize, areaCode).subscribe(new MySubscriber<MainBean>() {
            @Override
            public void onNext(MainBean baseBean) {
                if (baseBean.getError_code().equals(ErrorCode.SUCCEED)) {
                    mView.searchpremiseSucceed(baseBean);
                } else {
                    mView.searchpremisefailed(baseBean.getMsg());
                }

            }

            @Override
            public void onError(Throwable e) {
                mView.onError("服务器连接失败");
            }
        }));

    }

    @Override
    void GETCOUNTYPRIMISE() {
        String getcounty = mView.getcounty();
        mRxManager.add(mModel.GETCOUNTYPRIMISE(getcounty).subscribe(new MySubscriber<MainBean>() {
            @Override
            public void onNext(MainBean baseBean) {
                if (baseBean.getError_code().equals(ErrorCode.SUCCEED)) {
                    mView.countyprimiseSucceed(baseBean);
                } else {
                    mView.gainFailed(baseBean.getMsg());
                }

            }

            @Override
            public void onError(Throwable e) {
                mView.onError("服务器连接失败");
            }
        }));

    }
}
