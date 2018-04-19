package com.fangzhi.dafangzhi.activity.address;

import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.base.BaseActivity;

/**
 * Created by smacr on 2017/4/12.
 */

public class AddressActivity extends BaseActivity<AddressPresenter, AddressModel> implements AddressContract.View  {


    @Override
    public void tokenInvalid(String msg) {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_take;
    }

    @Override
    public void initView() {

    }

}
