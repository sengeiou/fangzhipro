package com.fangzhi.dafangzhi.activity.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.activity.main.bean.MainBean;
import com.fangzhi.dafangzhi.adapter.MainAdapter;
import com.fangzhi.dafangzhi.listener.NoDoubleClickListener;
import com.fangzhi.dafangzhi.network.MySubscriber;
import com.fangzhi.dafangzhi.network.Network;
import com.fangzhi.dafangzhi.utils.T;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity1 extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {

    @Bind(R.id.recycler_view)
    EasyRecyclerView easyRecyclerView;
    MainAdapter mainAdapter = null;
    List<MainBean> list = new ArrayList<>();
    private Subscription mDownloadSp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        ButterKnife.bind(this);
        easyRecyclerView.setRefreshListener(this);
        easyRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mainAdapter = new MainAdapter(this);
        mainAdapter.setMore(R.layout.view_more, this);
        //gain();
        easyRecyclerView.setAdapterWithProgress(mainAdapter);
        mainAdapter.setOnItemClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(int position) {
                T.showString(MainActivity1.this, "点击了" + position);
            }
        });
        easyRecyclerView.setRefreshing(true);
        requestVersionCode();
    }

    @Override
    public void onRefresh() {
        T.showString(MainActivity1.this, "下拉了");
        easyRecyclerView.setRefreshing(false);
    }

    @Override
    public void onLoadMore() {
        T.showString(MainActivity1.this, "上拉了");
       // mainAdapter.addAll(list);
        easyRecyclerView.setRefreshing(false);
       // mainAdapter.stopMore();  //这里是没有数据了停止
       // easyRecyclerView.setRefreshing(false);
    }

  /*  public void gain() {
        for (int i = 0; i < 10; i++) {
            MainBean mainBean = new MainBean();
            mainBean.setHelp(i + "");
            list.add(mainBean);
        }
    }*/


    /**
     * 请求版本号
     */
    private void requestVersionCode() {
        mDownloadSp = Network.getApiService().updateVersion("AC").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new MySubscriber<MainBean>() {
                    @Override
                    public void onNext(MainBean updateVersion) {
                      //  mainAdapter.addAll(list);
                        easyRecyclerView.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // startActivity(new Intent(LoadingActivity.this, LoginActivity.class));
                        //finish();
                        T.showString(MainActivity1.this, "出错了" + e);
                    }
                });
    }

}
