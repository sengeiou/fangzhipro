package com.fangzhi.dafangzhi.activity.hotspot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.activity.hotspot.adapter.HotspotAdapter;
import com.fangzhi.dafangzhi.activity.hotspot.adapter.HotspotmapAdapter;
import com.fangzhi.dafangzhi.activity.hotspot.bean.DesignList;
import com.fangzhi.dafangzhi.activity.hotspot.bean.HotspotBean;
import com.fangzhi.dafangzhi.activity.hotspot.bean.StyleList;
import com.fangzhi.dafangzhi.base.BaseActivity;
import com.fangzhi.dafangzhi.base.RxBus;
import com.fangzhi.dafangzhi.config.SpKey;
import com.fangzhi.dafangzhi.utils.SPUtils;
import com.fangzhi.dafangzhi.views.DialogDelegate;
import com.fangzhi.dafangzhi.views.SweetAlertDialogDelegate;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by smacr on 2017/3/28.
 */

public class HotspotActivity extends BaseActivity<HotspotPresenter, HotspotModel> implements HotspotContract.View {

    Context context = HotspotActivity.this;

    @Bind(R.id.recycler_type)
    EasyRecyclerView recycler_type;

    @Bind(R.id.recycler_view)
    EasyRecyclerView recycler_view;

    List<StyleList> list = new ArrayList<>();
    HotspotAdapter hotspotAdapter = null;
    HotspotmapAdapter hotspotmapAdapter = null;
    String house_id = "";

    List<DesignList> designL = new ArrayList<>();
    HotspotBean hotspotBean = null;
    DialogDelegate dialogDelegate;

    MyBaseReceiver receiver = new MyBaseReceiver();

    @Override
    public int getLayoutId() {
        return R.layout.activity_hotspot;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        house_id = intent.getStringExtra("id");
        dialogDelegate = new SweetAlertDialogDelegate(this);

        IntentFilter intentFilter = new IntentFilter("HotspotActivity");
        registerReceiver(receiver, intentFilter);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        recycler_type.setLayoutManager(staggeredGridLayoutManager);
        recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        hotspotAdapter = new HotspotAdapter(context);
        hotspotmapAdapter = new HotspotmapAdapter(context);
        hotspotAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                for (int i = 0; i < list.size(); i++) {
                    if (i == position) {
                        if (list.get(position).isture()) {

                        } else {
                            list.get(position).setIsture(true);
                            StyleList roomProductType = hotspotAdapter.getAllData().get(position);
                            roomProductType.setIsture(true);
                            RxBus.$().post("Ids", "1");
                            hotspotmapAdapter.clear();
                            hotspotmapAdapter.addAll(hotspotBean.getStyleList().get(position).getDesignList());
                        }
                    } else {
                        list.get(i).setIsture(false);
                    }
                }
                hotspotAdapter.notifyDataSetChanged();

            }
        });
        recycler_type.setAdapter(hotspotAdapter);
        recycler_view.setAdapter(hotspotmapAdapter);
        dialogDelegate.showProgressDialog(true, "正在获取数据...");
        mPresenter.gain();

 /*       RxBus.$().register("RoomActivity")
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        String mCurrentPartId = (String) o;
                        if (mCurrentPartId.equals("100")) {
                            dialogDelegate.showProgressDialog(true, "正在获取数据...");
                            mPresenter.gain();
                        }
                    }
                });*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            dialogDelegate.showProgressDialog(true, "正在获取数据...");
            mPresenter.gain();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public void tokenInvalid(String msg) {

    }

    @Override
    public void onError(String msg) {
        dialogDelegate.showErrorDialog(msg, "获取失败请重新获取", new DialogDelegate.OnDialogListener() {
            @Override
            public void onClick() {
                dialogDelegate.showProgressDialog(true, "正在获取数据...");
                mPresenter.gain();
            }
        });
    }

    @Override
    public String gethouse_id() {
        return house_id;
    }

    @Override
    public String getuser_id() {
        return SPUtils.getString(this, SpKey.USER_ID, "");
    }

    @Override
    public void gainSucceed(HotspotBean hotspotB) {
        dialogDelegate.clearDialog();
        list.clear();
        hotspotAdapter.clear();
        hotspotmapAdapter.clear();
        RxBus.$().post("Ids", "1");
        this.hotspotBean = hotspotB;
        if (hotspotBean.getStyleList() != null && hotspotBean.getStyleList().size() != 0) {
            for (StyleList bean : hotspotBean.getStyleList()) {
                list.add(bean);
            }
        }
        if (list.size() != 0 && list != null) {
            list.get(0).setIsture(true);
            hotspotAdapter.addAll(list);
            hotspotmapAdapter.addAll(hotspotBean.getStyleList().get(0).getDesignList());
        }
    }

    @Override
    public void gainFailed(String msg) {
        dialogDelegate.showErrorDialog(msg, "获取失败请重新获取", new DialogDelegate.OnDialogListener() {
            @Override
            public void onClick() {
                dialogDelegate.showProgressDialog(true, "正在获取数据...");
                mPresenter.gain();
            }
        });
    }

    @OnClick(R.id.iv_back)
    public void back() {
        finish();
    }


    public class MyBaseReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            //finish();
            //Toast.makeText(HotspotActivity.this, "我收到了", Toast.LENGTH_SHORT).show();
            dialogDelegate.showProgressDialog(true, "正在获取数据...");
            mPresenter.gain();
            //unregisterReceiver(receiver);
        }

    }

}
