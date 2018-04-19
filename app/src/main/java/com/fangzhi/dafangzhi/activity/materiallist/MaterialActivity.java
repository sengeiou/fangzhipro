package com.fangzhi.dafangzhi.activity.materiallist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.StringSignature;
import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.activity.gooddetails.GoodDetails;
import com.fangzhi.dafangzhi.activity.materiallist.adapter.MaterAdapter;
import com.fangzhi.dafangzhi.activity.materiallist.adapter.MaterupAdapter;
import com.fangzhi.dafangzhi.activity.materiallist.bean.GoodsList;
import com.fangzhi.dafangzhi.activity.materiallist.bean.MaterialBean;
import com.fangzhi.dafangzhi.activity.materiallist.bean.SceneList;
import com.fangzhi.dafangzhi.base.BaseActivity;
import com.fangzhi.dafangzhi.config.SpKey;
import com.fangzhi.dafangzhi.listener.NoDoubleClickListener;
import com.fangzhi.dafangzhi.utils.SPUtils;
import com.fangzhi.dafangzhi.utils.StringHelp;
import com.fangzhi.dafangzhi.views.DialogDelegate;
import com.fangzhi.dafangzhi.views.SweetAlertDialogDelegate;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by smacr on 2017/4/5.
 */

public class MaterialActivity extends BaseActivity<MaterialPresenter, MaterialModel> implements MaterialContract.View {

    Context context = MaterialActivity.this;
    String design_id = "";
    DialogDelegate dialogDelegate;

    @Bind(R.id.recycler_type)
    EasyRecyclerView recycler_type;  //上方

    @Bind(R.id.recycler)
    EasyRecyclerView recycler; //下面

    @Bind(R.id.iamg)
    ImageView iamg; //图片

    @Bind(R.id.money)
    TextView money; //总价

    @Bind(R.id.typename)
    TextView typename; //总价名称

    @Bind(R.id.name)
    TextView name; //名字

    @Bind(R.id.ying)
    TextView ying; //点击硬装

    @Bind(R.id.ruan)
    TextView ruan; //点击软装

    MaterAdapter materAdapter = null;
    MaterupAdapter materupAdapter = null;

    MaterialBean materialBean;
    List<SceneList> sceneList = new ArrayList<>();
    List<GoodsList> goodsList = new ArrayList<>();

    int upnumber = 0;  //点击的第几个

    int yrnumber = 1;  //点击的硬装还是软装

    @Override
    public int getLayoutId() {
        return R.layout.activity_material;
    }

    @Override
    public void initView() {
        design_id = getIntent().getStringExtra("design_id");
        dialogDelegate = new SweetAlertDialogDelegate(this);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        recycler_type.setLayoutManager(staggeredGridLayoutManager);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        materAdapter = new MaterAdapter(context);
        materupAdapter = new MaterupAdapter(context);
        recycler.setAdapter(materAdapter);
        recycler_type.setAdapter(materupAdapter);
        materAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                GoodsList goodsList = materAdapter.getAllData().get(position);
                Intent intent = new Intent(MaterialActivity.this, GoodDetails.class);
                intent.putExtra("goods_id",goodsList.getGoods_id());
                startActivity(intent);
            }
        });
        materupAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                for (int i = 0; i < sceneList.size(); i++) {
                    if (i == position) {
                        if (sceneList.get(position).isture()) {

                        } else {
                            sceneList.get(position).setIsture(true);
                            SceneList sceneLists = materupAdapter.getAllData().get(position);
                            sceneLists.setIsture(true);
                            upnumber = position;
                            materAdapter.clear();
                            goodsList.clear();
                            yrnumber = 1;
                            change(0);
                            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
                            String date = sDateFormat.format(new java.util.Date());
                            Glide.with(context)
                                    .load(materialBean.getDesignInfo().getSceneList().get(upnumber).getDesign_img())
                                    .placeholder(R.mipmap.zanwei)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .signature(new StringSignature(date))
                                    .crossFade()
                                    .into(iamg);
                            if (sceneLists.getTypeList() != null && sceneLists.getTypeList().size() != 0) {
                                if (StringHelp.checkNull(materialBean.getDesignInfo().getSceneList().get(upnumber).getTypeList().get(yrnumber).getTatal_price())) {
                                    money.setText(materialBean.getDesignInfo().getSceneList().get(upnumber).getTypeList().get(yrnumber).getTatal_price() + "万元");
                                } else {
                                    money.setText("0元");
                                }
                                if (sceneLists.getTypeList().get(yrnumber).getGoodsList() != null) {
                                    goodsList.addAll(sceneLists.getTypeList().get(yrnumber).getGoodsList());
                                }
                                materAdapter.addAll(goodsList);
                            }
                        }
                    } else {
                        sceneList.get(i).setIsture(false);
                    }
                }
                materupAdapter.notifyDataSetChanged();

            }
        });
        iamg.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(int position) {
                Intent intent = new Intent(context, ShowImageActivity.class);
                intent.putExtra("url", materialBean.getDesignInfo().getSceneList().get(upnumber).getScene_img());
                startActivity(intent);
                overridePendingTransition(R.anim.activity_anim_in, R.anim.activity_anim_out);
            }
        });

        dialogDelegate.showProgressDialog(true, "正在获取材料详情...");
        mPresenter.gainMater();
    }


    public void change(int num) {
        if (num == 0) {
            ying.setBackgroundResource(R.drawable.text_yesbg);
            ying.setTextColor(getResources().getColor(R.color.white));
            ruan.setBackgroundResource(R.drawable.text_nobg);
            ruan.setTextColor(getResources().getColor(R.color.login_text));
            typename.setText("硬装材料：");
        } else {
            ying.setBackgroundResource(R.drawable.text_nobg);
            ying.setTextColor(getResources().getColor(R.color.login_text));
            ruan.setBackgroundResource(R.drawable.text_yesbg);
            ruan.setTextColor(getResources().getColor(R.color.white));
            typename.setText("软装材料：");
        }
    }

    @Override
    public void tokenInvalid(String msg) {

    }

    @Override
    public void onError(String msg) {
        dialogDelegate.stopProgressWithFailed(msg, "");
    }

    @Override
    public String getdesign_id() {
        return design_id;
    }

    @Override
    public String getuser_id() {
        return SPUtils.getString(this, SpKey.USER_ID, "");
    }

    @Override
    public void gainMaterSucceed(MaterialBean materialB) {
        dialogDelegate.clearDialog();
        goodsList.clear();
        sceneList.clear();
        this.materialBean = materialB;
        if (materialBean.getDesignInfo().getSceneList().get(upnumber).getTypeList().get(yrnumber).getGoodsList()!=null){
            goodsList.addAll(materialBean.getDesignInfo().getSceneList().get(upnumber).getTypeList().get(yrnumber).getGoodsList());
        }
        materAdapter.addAll(goodsList);
        if (materialBean.getDesignInfo().getSceneList() != null && materialBean.getDesignInfo().getSceneList().size() != 0) {
            materialBean.getDesignInfo().getSceneList().get(upnumber).setIsture(true);
            sceneList.addAll(materialBean.getDesignInfo().getSceneList());
            materupAdapter.addAll(sceneList);
            if (StringHelp.checkNull(materialBean.getDesignInfo().getSceneList().get(upnumber).getTypeList().get(yrnumber).getTatal_price())) {
                money.setText(materialBean.getDesignInfo().getSceneList().get(upnumber).getTypeList().get(yrnumber).getTatal_price() + "元");
            } else {
                money.setText("0元");
            }
            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
            String date = sDateFormat.format(new java.util.Date());
            Glide.with(context)
                    .load(materialBean.getDesignInfo().getSceneList().get(upnumber).getDesign_img())
                    .placeholder(R.mipmap.zanwei)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .signature(new StringSignature(date))
                    .crossFade()
                    .into(iamg);
        }
        name.setText(materialBean.getDesignInfo().getName());

    }

    @Override
    public void gainMaterFailed(String msg) {
        dialogDelegate.stopProgressWithFailed(msg, "");
    }


    @OnClick(R.id.ying)
    public void yingclick() {
        yrnumber = 1;
        change(0);
        goodsList.clear();
        materAdapter.clear();
        if (materialBean.getDesignInfo().getSceneList().get(upnumber).getTypeList() != null && materialBean.getDesignInfo().getSceneList().get(upnumber).getTypeList().size() != 0) {
            if (StringHelp.checkNull(materialBean.getDesignInfo().getSceneList().get(upnumber).getTypeList().get(yrnumber).getTatal_price())) {
                money.setText(materialBean.getDesignInfo().getSceneList().get(upnumber).getTypeList().get(yrnumber).getTatal_price() + "元");
            } else {
                money.setText("0元");
            }
            if (materialBean.getDesignInfo().getSceneList().get(upnumber).getTypeList()!=null){
                if (materialBean.getDesignInfo().getSceneList().get(upnumber).getTypeList().get(yrnumber).getGoodsList() != null) {
                    goodsList.addAll(materialBean.getDesignInfo().getSceneList().get(upnumber).getTypeList().get(yrnumber).getGoodsList());
                }
            }
        }
        materAdapter.addAll(goodsList);
    }

    @OnClick(R.id.ruan)
    public void ruanclick() {
        yrnumber = 0;
        change(1);
        goodsList.clear();
        materAdapter.clear();
        if (materialBean.getDesignInfo().getSceneList().get(upnumber).getTypeList() != null && materialBean.getDesignInfo().getSceneList().get(upnumber).getTypeList().size() != 0) {
            if (StringHelp.checkNull(materialBean.getDesignInfo().getSceneList().get(upnumber).getTypeList().get(yrnumber).getTatal_price())) {
                money.setText(materialBean.getDesignInfo().getSceneList().get(upnumber).getTypeList().get(yrnumber).getTatal_price() + "元");
            } else {
                money.setText("0元");
            }
            if (materialBean.getDesignInfo().getSceneList().get(upnumber).getTypeList().get(yrnumber).getGoodsList() != null) {
                goodsList.addAll(materialBean.getDesignInfo().getSceneList().get(upnumber).getTypeList().get(yrnumber).getGoodsList());
            }
        }
        materAdapter.addAll(goodsList);
    }

    @OnClick(R.id.tv_title)
    public void back() {
        dialogDelegate.clearDialog();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialogDelegate.clearDialog();
    }

}
