package com.fangzhi.dafangzhi.fragment.myself;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.base.BaseFragment;

/**
 * Created by smacr on 2017/4/6.
 */

public class MyselfFragment extends BaseFragment<MyselfPresenter, MyselfModel> implements MyselfContract.View {

    View view;



    private static MyselfFragment myselfFragment=null;

    public static MyselfFragment getMainFragment(){

        if (myselfFragment==null){
            myselfFragment=new MyselfFragment();
        }
        return myselfFragment;
    }

    @Override
    public void tokenInvalid(String msg) {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.myself_activity, container, false);
        return view;
    }

    @Override
    public void init() {

    }

}
