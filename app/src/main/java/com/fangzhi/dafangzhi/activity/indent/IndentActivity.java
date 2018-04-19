package com.fangzhi.dafangzhi.activity.indent;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.activity.address.AddressActivity;
import com.fangzhi.dafangzhi.activity.indent.adapter.IndentAdapter;
import com.fangzhi.dafangzhi.base.BaseActivity;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by smacr on 2017/4/11.
 */

public class IndentActivity extends BaseActivity<IndentPresenter, IndentModel> implements IndentContract.View {


    @Bind(R.id.none)
    AutoRelativeLayout none;  //没有

    @Bind(R.id.site)
    AutoRelativeLayout site;  //有

    @Bind(R.id.yrecycler)
    EasyRecyclerView yrecycler;  //列表

    IndentAdapter indentAdapter = null;

    List<String> list = new ArrayList<>();

    @Override
    public void tokenInvalid(String msg) {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.indent_activity;
    }

    @Override
    public void initView() {
        String one = getIntent().getStringExtra("one");

        list.add("1");
        list.add("1");
        list.add("1");
        yrecycler.setLayoutManager(new LinearLayoutManager(IndentActivity.this, LinearLayoutManager.VERTICAL, false));
        indentAdapter = new IndentAdapter(this);
        yrecycler.setAdapter(indentAdapter);
        indentAdapter.addAll(list);
        none.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndentActivity.this, AddressActivity.class);
                startActivity(intent);
            }
        });
    }
}
