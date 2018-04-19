package com.fangzhi.dafangzhi.activity.main_type;


import com.fangzhi.dafangzhi.activity.main_type.bean.HouseTypes;
import com.fangzhi.dafangzhi.network.ErrorCode;
import com.fangzhi.dafangzhi.network.MySubscriber;

/**
 * Created by smacr on 2016/9/21.
 */
public class HouseTypePresenter extends HouseTypeContract.Presenter {
    @Override
    public void getHouseTypes() {
        String token = mView.getToken();
        String id = mView.getHouseId();
        mRxManager.add(mModel.getHouseTypes(id).subscribe(new MySubscriber<HouseTypes>(){
            @Override
            public void onNext(HouseTypes houseTypes) {
                if (ErrorCode.TOKEN_INVALID.equals(houseTypes.getError_code())) {
                    mView.tokenInvalid(houseTypes.getMsg());
                } else if (ErrorCode.SERVER_EXCEPTION.equals(houseTypes.getError_code())) {
                    mView.onError(houseTypes.getMsg());
                } else if (ErrorCode.SUCCEED.equals(houseTypes.getError_code())) {
                    mView.showHouseTypes(houseTypes.getHouseList());
                } else {
                    mView.showHouseTypes(null);
                }
            }

            @Override
            public void onError(Throwable e) {
                mView.showHouseTypes(null);
            }
        }));
    }

    @Override
    public void onStart() {

    }
}
