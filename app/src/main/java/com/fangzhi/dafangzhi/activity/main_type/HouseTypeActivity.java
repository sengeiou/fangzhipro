package com.fangzhi.dafangzhi.activity.main_type;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.widget.TextView;

import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.activity.hotspot.HotspotActivity;
import com.fangzhi.dafangzhi.activity.login.LoginActivity;
import com.fangzhi.dafangzhi.activity.main.MainActivity;
import com.fangzhi.dafangzhi.activity.main_type.adapter.HouseTypeAdapter;
import com.fangzhi.dafangzhi.activity.main_type.bean.HouseTypes;
import com.fangzhi.dafangzhi.base.BaseActivity;
import com.fangzhi.dafangzhi.config.SpKey;
import com.fangzhi.dafangzhi.listener.NoDoubleClickListener;
import com.fangzhi.dafangzhi.utils.SPUtils;
import com.fangzhi.dafangzhi.utils.T;
import com.fangzhi.dafangzhi.views.DialogDelegate;
import com.fangzhi.dafangzhi.views.SweetAlertDialogDelegate;
import com.jude.easyrecyclerview.EasyRecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by smacr on 2016/9/21.
 */
public class HouseTypeActivity extends BaseActivity<HouseTypePresenter,HouseTypeModel> implements HouseTypeContract.View
,SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.recycler_view)
    EasyRecyclerView recyclerView;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    private String mHouseId;
    private HouseTypeAdapter mAdapter;
    DialogDelegate dialogDelegate;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main_type;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        //获取楼盘id
        mHouseId = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        //设置标题
        tvTitle.setText(name);

        recyclerView.setRefreshListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        mAdapter = new HouseTypeAdapter(this);
        mAdapter.setOnItemClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(int position) {
                String imgUrl = mAdapter.getItem(position).getHouse_img();
                String id = mAdapter.getItem(position).getId();
                String name = mAdapter.getItem(position).getHouse_name();
                Intent intent = new Intent();
                intent.putExtra("imgUrl",imgUrl);
                intent.putExtra("id",id);
                intent.putExtra("name",name);
                intent.setClass(HouseTypeActivity.this, HotspotActivity.class);
                startActivity(intent);
            }
        });
        dialogDelegate = new SweetAlertDialogDelegate(this);
        recyclerView.setAdapterWithProgress(mAdapter);
        onRefresh();
    }

    @Override
    public String getToken() {
        return SPUtils.getString(this, SpKey.TOKEN,"");
    }

    @Override
    public String getHouseId() {
        return mHouseId;
    }

    @Override
    public void showHouseTypes(List<HouseTypes.HouseType> list) {
        mAdapter.clear();
        mAdapter.addAll(list);
        recyclerView.setRefreshing(false);
    }
    @OnClick(R.id.iv_back)
    public void onFinish(){
        dialogDelegate.clearDialog();
        finish();
    }

    @OnClick(R.id.gohome)
    public void onHome(){
        Intent intent = new Intent(HouseTypeActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    @Override
    public void onRefresh() {
        recyclerView.setRefreshing(true);
        mPresenter.getHouseTypes();
    }

    @Override
    public void tokenInvalid(String msg) {
        dialogDelegate.showErrorDialog(msg, msg, new DialogDelegate.OnDialogListener() {
            @Override
            public void onClick() {
                Intent intent = new Intent(HouseTypeActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onError(String msg) {
        T.showShort(this,msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialogDelegate.clearDialog();
    }
}
