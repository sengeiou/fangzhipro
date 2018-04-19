package com.fangzhi.dafangzhi.fragment.collect;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.activity.home.HomeActivity;
import com.fangzhi.dafangzhi.base.BaseFragment;
import com.fangzhi.dafangzhi.base.RxBus;
import com.fangzhi.dafangzhi.config.SpKey;
import com.fangzhi.dafangzhi.fragment.collect.adapter.CollectComAdapter;
import com.fangzhi.dafangzhi.fragment.collect.adapter.CollectschAdapter;
import com.fangzhi.dafangzhi.fragment.collect.bean.CollectBean;
import com.fangzhi.dafangzhi.fragment.collect.bean.GoodsList;
import com.fangzhi.dafangzhi.fragment.collect.bean.SceneList;
import com.fangzhi.dafangzhi.utils.SPUtils;
import com.fangzhi.dafangzhi.utils.T;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Created by smacr on 2017/4/6.
 */

public class CollectFragment extends BaseFragment<CollectPresenter, CollectModel> implements CollectContract.View {

    View view;

    @Bind(R.id.name)
    TextView name;

    @Bind(R.id.xia)
    TextView xia;

    @Bind(R.id.compile)
    TextView compile;

    @Bind(R.id.commodityname)
    TextView commodityname;

    @Bind(R.id.commodityxia)
    TextView commodityxia;

    @Bind(R.id.check)
    ImageView check;

    @Bind(R.id.delete)
    TextView delete;

    @Bind(R.id.none)
    AutoLinearLayout none;

    @Bind(R.id.deleteview)
    AutoLinearLayout deleteview;

    @Bind(R.id.schemerecycler)
    EasyRecyclerView schemerecycler; //方案列表

    @Bind(R.id.commodityrecycler)
    EasyRecyclerView commodityrecycler; //商品列表

    CollectschAdapter collectschAdapter; //方案adapter
    CollectComAdapter collectComAdapter; //商品adapter

    CollectBean collectBean = null;
    List<GoodsList> goodsList = new ArrayList<>();
    List<SceneList> sceneList = new ArrayList<>();

    String type = "0";

    boolean isfa = false;
    boolean issp = false;

    private static CollectFragment collectFragment = null;

    public static CollectFragment getMainFragment() {

        if (collectFragment == null) {
            collectFragment = new CollectFragment();
        }
        return collectFragment;
    }


    public void alter() {
        if (type.equals("0")) {
            for (int i = 0; i < sceneList.size(); i++) {
                if (!sceneList.get(i).isselect()) {
                    check.setImageResource(R.mipmap.gray);
                    isfa = false;
                    return;
                }
            }
            check.setImageResource(R.mipmap.red);
            isfa = true;
        } else {
            for (int i = 0; i < goodsList.size(); i++) {
                if (!goodsList.get(i).isselect()) {
                    check.setImageResource(R.mipmap.gray);
                    issp = false;
                    return;
                }
            }
            check.setImageResource(R.mipmap.red);
            issp = true;
        }

    }

    @Override
    public void tokenInvalid(String msg) {

    }

    @Override
    public void onError(String msg) {
        ((HomeActivity) getActivity()).showErrorDialog(msg);
        schemerecycler.setRefreshing(false);
    }

    @Override
    public View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.collect_activity, container, false);
        return view;
    }

    @Override
    public void init() {
        schemerecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        commodityrecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        collectschAdapter = new CollectschAdapter(getActivity());
        collectComAdapter = new CollectComAdapter(getActivity());
        schemerecycler.setAdapter(collectschAdapter);
        commodityrecycler.setAdapter(collectComAdapter);

        RxBus.$().register("CollectFragment")
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        String mCurrentPartId = (String) o;
                        if (mCurrentPartId.equals("1")) {
                            alter();
                        }
                    }
                });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals("0")) {
                    for (int i = 0; i < sceneList.size(); i++) {
                        if (isfa) {
                            sceneList.get(i).setIsselect(false);
                        } else {
                            sceneList.get(i).setIsselect(true);
                        }
                    }
                    collectschAdapter.notifyDataSetChanged();
                    alter();
                } else {
                    for (int i = 0; i < goodsList.size(); i++) {
                        if (issp) {
                            goodsList.get(i).setIsselect(false);
                        } else {
                            goodsList.get(i).setIsselect(true);
                        }
                    }
                    collectComAdapter.notifyDataSetChanged();
                    alter();
                }
            }
        });

        schemerecycler.setRefreshing(true);
        mPresenter.gain();
    }

    @Override
    public String getUserId() {
        return SPUtils.getString(getActivity(), SpKey.USER_ID, "");
    }

    @Override
    public void gainSucceed(CollectBean collect) {
        collectBean = collect;
        schemerecycler.setRefreshing(false);
        goodsList.clear();
        sceneList.clear();
        goodsList.addAll(collectBean.getGoodsList());
        sceneList.addAll(collectBean.getSceneList());
        collectComAdapter.addAll(goodsList);
        collectschAdapter.addAll(sceneList);
        T.showString(getActivity(), "成功");
    }

    @Override
    public void gainFailed(String msg) {
        T.showString(getActivity(), "失败");
        schemerecycler.setRefreshing(false);
    }


    @OnClick(R.id.name)
    public void name() {
        name.setTextColor(getResources().getColor(R.color.login_text));
        commodityname.setTextColor(getResources().getColor(R.color.login_tint));
        if (sceneList.size() == 0 && sceneList == null) {
            schemerecycler.setVisibility(View.GONE);
            none.setVisibility(View.VISIBLE);
        } else {
            none.setVisibility(View.GONE);
            schemerecycler.setVisibility(View.VISIBLE);
        }
        xia.setVisibility(View.VISIBLE);
        commodityxia.setVisibility(View.GONE);
        commodityrecycler.setVisibility(View.GONE);
        type = "0";
        compile.setText("编辑");
        deleteview.setVisibility(View.GONE);
        alter();
    }

    @OnClick(R.id.commodityname)
    public void commodityname() {
        commodityname.setTextColor(getResources().getColor(R.color.login_text));
        name.setTextColor(getResources().getColor(R.color.login_tint));
        xia.setVisibility(View.GONE);
        type = "1";
        if (goodsList.size() == 0 && goodsList == null) {
            none.setVisibility(View.VISIBLE);
            commodityrecycler.setVisibility(View.GONE);
        } else {
            none.setVisibility(View.GONE);
            commodityrecycler.setVisibility(View.VISIBLE);
        }
        commodityxia.setVisibility(View.VISIBLE);
        schemerecycler.setVisibility(View.GONE);
        compile.setText("编辑");
        deleteview.setVisibility(View.GONE);
        alter();
    }

    @OnClick(R.id.compile)
    public void compile() {
        if (compile.getText().toString().equals("编辑")) {
            compile.setText("完成");
            deleteview.setVisibility(View.VISIBLE);
        } else {
            compile.setText("编辑");
            deleteview.setVisibility(View.GONE);
            alter();
        }

    }

}
